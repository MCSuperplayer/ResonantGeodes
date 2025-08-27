package com.mcsuperplayer.resonantgeodes.registry.entity;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.multiblock.Multiblock;
import com.mcsuperplayer.resonantgeodes.Config;
import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.network.BurnerStatusPacket;
import com.mcsuperplayer.resonantgeodes.network.ResonantGeodesPackets;
import com.mcsuperplayer.resonantgeodes.registry.Registry;
import com.mcsuperplayer.resonantgeodes.registry.menu.CrystalBurnerMenu;
import com.mcsuperplayer.resonantgeodes.world.CrystalResonanceStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

public class CrystalBurnerBlockEntity extends BlockEntity implements MenuProvider {
	private boolean structureValid;
	private boolean freeResonance;
	private int runTimer = 0;
	private int checkTimer = 0;
	@OnlyIn(Dist.CLIENT)
	private Component clientStatus = Component.empty();
	@OnlyIn(Dist.CLIENT)
	private Component clientInfo = Component.literal("0/0");

	public CrystalBurnerBlockEntity(BlockPos pos, BlockState state) {
		super(Registry.CRYSTAL_BURNER_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
		return new CrystalBurnerMenu(id, playerInv, this);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.resonantgeodes.resonance_diffuser");
	}

	private Component getStatusMsg() {
		if (level.isClientSide) {
			return Component.literal("Clientside");
		}
		if (!structureValid) {
			return Component.translatable("text.resonantgeodes.structure_invalid");
		}
		if (!freeResonance) {
			return Component.translatable("text.resonantgeodes.high_resonance");
		}
		return Component.translatable("text.resonantgeodes.structure_valid");
	}

	public Component getInfoMsg() {
		return Component.literal(String.valueOf(runTimer) + "/" + Config.burnerWorkTime);
	}
	
	private void structureCheck() {
		this.structureValid = false;
		Rotation r = Rotation.NONE;
		switch (getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING)) {
		case NORTH:
			r = Rotation.CLOCKWISE_90;
			break;
		case EAST:
			r = Rotation.CLOCKWISE_180;
			break;
		case SOUTH:
			r = Rotation.COUNTERCLOCKWISE_90;
			break;
		default:
			break;
		}
		Multiblock mb = ModonomiconAPI.get().getMultiblock(ResourceLocation.fromNamespaceAndPath(ResonantGeodes.MODID, "crystal_burner"));
		this.structureValid = mb.validate(level, worldPosition, r);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		structureCheck();
	}

	@OnlyIn(Dist.CLIENT)
	public void setClientStatus(Component status, Component info) {
		this.clientStatus = status;
		this.clientInfo = info;
	}

	@OnlyIn(Dist.CLIENT)
	public Component getClientStatusMsg() {
		return clientStatus;
	}

	@OnlyIn(Dist.CLIENT)
	public Component getClientInfoMsg() {
		return clientInfo;
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		// TODO Auto-generated method stub
		super.saveAdditional(tag);
		tag.putInt("progress", runTimer);
	}

	@Override
	public void load(CompoundTag tag) {
		// TODO Auto-generated method stub
		super.load(tag);
		runTimer = tag.getInt("progress");
		this.setChanged();
	}

	public void tick() {
		if (level.isClientSide)
			return;
		if (level instanceof ServerLevel sLevel) {
			Component status = getStatusMsg();
			Component info = getInfoMsg();
			BurnerStatusPacket packet = new BurnerStatusPacket(worldPosition, status, info);

			for (ServerPlayer player : sLevel.players()) {
				if (player.containerMenu instanceof CrystalBurnerMenu menu
						&& menu.getBlockEntity().getBlockPos().equals(worldPosition)) {
					ResonantGeodesPackets.sendToClient(player, packet);
				}
			}
		}
		this.checkTimer++;
		if (this.checkTimer >= Config.structureCheckTime) {
			structureCheck();
			this.checkTimer = 0;
		}

		if (structureValid) {
			freeResonance = (CrystalResonanceStorage.get(level.getServer().overworld()).get() < Config.maxResonance);
			if (freeResonance) {
				if (this.runTimer == 0) {
					ItemInputBlockEntity be = (ItemInputBlockEntity) level
							.getBlockEntity(worldPosition
									.below()
									.relative(level
											.getBlockState(worldPosition)
											.getValue(BlockStateProperties.HORIZONTAL_FACING), 2));
					LazyOptional<IItemHandler> inv = be.getCapability(ForgeCapabilities.ITEM_HANDLER);
					IItemHandler inventory = inv.orElse(null);
					if (inventory == null)
						return;
					if (inventory.getStackInSlot(0).isEmpty())
						return;
					inventory.extractItem(0, 1, false);
				}
				this.runTimer++;
				if (this.runTimer >= Config.burnerWorkTime) {
					this.runTimer = 0;
					CrystalResonanceStorage.get((ServerLevel) level).add(Config.burnerResonanceProd);
				}
			}
		}
	}
}
