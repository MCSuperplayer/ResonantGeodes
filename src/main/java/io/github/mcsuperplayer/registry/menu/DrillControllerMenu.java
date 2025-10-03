package io.github.mcsuperplayer.registry.menu;

import io.github.mcsuperplayer.registry.Registry;
import io.github.mcsuperplayer.registry.entity.DrillControllerBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
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

	Component getStatusMsg() {
		return blockEntity.getClientStatusMsg();
	}

	Component getInfoMsg() {
		return blockEntity.getClientInfoMsg();
	}

	public DrillControllerBlockEntity getBlockEntity() {
		return blockEntity;
	}
}
