package com.mcsuperplayer.resonantgeodes.registry.entity;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.mcsuperplayer.resonantgeodes.Config;
import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.network.DrillStatusPacket;
import com.mcsuperplayer.resonantgeodes.network.ResonantGeodesPackets;
import com.mcsuperplayer.resonantgeodes.registry.Registry;
import com.mcsuperplayer.resonantgeodes.registry.menu.DrillControllerMenu;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DrillControllerBlockEntity extends BlockEntity implements MenuProvider {
	private boolean structureValid;
	private boolean enoughResonance;
	private int runTimer = 0;
	private int checkTimer = 0;
	@OnlyIn(Dist.CLIENT)
	private Component clientStatus = Component.empty();
	@OnlyIn(Dist.CLIENT)
	private Component clientInfo = Component.literal("0/0");

	public DrillControllerBlockEntity(BlockPos pos, BlockState state) {
		super(Registry.DRILL_CONTROLLER_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
		return new DrillControllerMenu(id, playerInv, this);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.resonantgeodes.drill_controller");
	}

	private Component getStatusMsg() {
		if (level.isClientSide) {
			return Component.literal("Clientside");
		}
		if (!structureValid) {
			return Component.translatable("text.resonantgeodes.structure_invalid");
		}
		if (!enoughResonance) {
			return Component.translatable("text.resonantgeodes.low_resonance");
		}
		return Component.translatable("text.resonantgeodes.structure_valid");
	}

	public Component getInfoMsg() {
		return Component.literal(String.valueOf(runTimer) + "/" + Config.drillWorkTime);
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
		this.structureValid = ModonomiconAPI
				.get()
				.getMultiblock(ResourceLocation.fromNamespaceAndPath(ResonantGeodes.MODID, "geode_drill"))
				.validate(level, worldPosition.relative(getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite(), 3), r);
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
		if (level.isClientSide) return;
		this.checkTimer++;
		if (this.checkTimer >= Config.structureCheckTime) {
			structureCheck();
			this.checkTimer = 0;
		}

		if (structureValid) {
			enoughResonance = (CrystalResonanceStorage.get(level.getServer().overworld()).get() >= Config.drillResonanceCost);
			if (enoughResonance) {
				this.runTimer++;
				if (this.runTimer >= Config.drillWorkTime) {
					this.runTimer = 0;
					CrystalResonanceStorage.get((ServerLevel) level).consume(Config.drillResonanceCost);
					BlockPos corePos = worldPosition
							.relative(level
									.getBlockState(worldPosition)
									.getValue(BlockStateProperties.HORIZONTAL_FACING)
									.getOpposite(), 3);
					BlockPos outputPos = corePos.below(3);
					MaterialBlockEntity core = (MaterialBlockEntity) level.getBlockEntity(corePos);
					ItemOutputBlockEntity output = (ItemOutputBlockEntity) level.getBlockEntity(outputPos);
					ItemStack stack = new ItemStack(Registry.CRYSTAL_FRAGMENT.get(), 1);
					stack.getOrCreateTag().putString("geode_material", core.getMaterial());
					output.insertOutput(stack);
				}
			}
		}
		if (level instanceof ServerLevel sLevel) {
			Component status = getStatusMsg();
			Component info = getInfoMsg();
			DrillStatusPacket packet = new DrillStatusPacket(worldPosition, status, info);

			for (ServerPlayer player : sLevel.players()) {
				if (player.containerMenu instanceof DrillControllerMenu menu
						&& menu.getBlockEntity().getBlockPos().equals(worldPosition)) {
					ResonantGeodesPackets.sendToClient(player, packet);
				}
			}
		}
	}
}
