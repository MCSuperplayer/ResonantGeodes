package com.mcsuperplayer.resonantgeodes.registry.menu;

import com.mcsuperplayer.resonantgeodes.registry.Registry;
import com.mcsuperplayer.resonantgeodes.registry.entity.DrillControllerBlockEntity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DrillControllerMenu extends AbstractContainerMenu {
	private final DrillControllerBlockEntity blockEntity;

	public DrillControllerMenu(int id, Inventory playerInv, BlockEntity blockEntity) {
		super(Registry.DRILL_CONTROLLER_MENU.get(), id);
		this.blockEntity = (DrillControllerBlockEntity) blockEntity;
	}
	public DrillControllerMenu(int id, Inventory playerInv, FriendlyByteBuf buf) {
		this(id, playerInv, playerInv.player.level().getBlockEntity(buf.readBlockPos()));
	}

	@Override
	public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
		return null;
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	boolean isStructureValid() {
		return blockEntity.isStructureValid();
	}

}
