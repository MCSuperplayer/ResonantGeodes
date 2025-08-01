package com.mcsuperplayer.resonantgeodes.registry.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ItemInputBlockEntity extends BlockEntity {
	private final ItemStackHandler inv = new ItemStackHandler() {

		@Override
		public boolean isItemValid(int slot, @NotNull ItemStack stack) {
			if (stack.is(Registry.CRYSTAL_FRAGMENT.get()) || stack.is(Items.AMETHYST_SHARD))
				return true;
			return false;
		}
	};

	public ItemInputBlockEntity(BlockPos pos, BlockState state) {
		super(Registry.MACHINE_ITEM_INPUT_ENTITY.get(), pos, state);
	}

	private LazyOptional<IItemHandler> invCap = LazyOptional.of(() -> inv);

	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (cap == ForgeCapabilities.ITEM_HANDLER) {
			return invCap.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		tag.put("inventory", inv.serializeNBT());
		super.saveAdditional(tag);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		inv.deserializeNBT(tag.getCompound("inventory"));
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		invCap.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		invCap = LazyOptional.of(() -> inv);
	}


}
