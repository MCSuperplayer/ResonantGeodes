package com.mcsuperplayer.resonantgeodes.registry.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ItemOutputBlockEntity extends BlockEntity {
	private final ItemStackHandler outputInventory = new ItemStackHandler(1) {
		public int getSlotLimit(int slot) {
			return Integer.MAX_VALUE;
		};
	};

	private final IItemHandler extractOnlyHandler = new IItemHandler() {
		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			return stack;
		}

		@Override
		public int getSlots() {
			return outputInventory.getSlots();
		}

		@Override
		public @NotNull ItemStack getStackInSlot(int slot) {
			return outputInventory.getStackInSlot(slot);
		}

		@Override
		public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
			return outputInventory.extractItem(slot, amount, simulate);
		}

		@Override
		public int getSlotLimit(int slot) {
			return outputInventory.getSlotLimit(slot);
		}

		@Override
		public boolean isItemValid(int slot, @NotNull ItemStack stack) {
			return false;
		};
	};

	private LazyOptional<IItemHandler> extractOnlyCap = LazyOptional.of(() -> extractOnlyHandler);

	public ItemOutputBlockEntity(BlockPos pos, BlockState state) {
		super(Registry.MACHINE_ITEM_OUTPUT_ENTITY.get(), pos, state);
	}

	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (cap == ForgeCapabilities.ITEM_HANDLER && side == Direction.DOWN) {
			return extractOnlyCap.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		tag.put("inventory", outputInventory.serializeNBT());
		super.saveAdditional(tag);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		outputInventory.deserializeNBT(tag.getCompound("inventory"));
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		extractOnlyCap.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		extractOnlyCap = LazyOptional.of(() -> extractOnlyHandler);
	}

	public void insertOutput(ItemStack stack) {
		outputInventory.insertItem(0, stack, false);
	}

}
