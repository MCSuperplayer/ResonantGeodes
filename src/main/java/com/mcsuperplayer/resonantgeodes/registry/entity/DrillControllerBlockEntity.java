package com.mcsuperplayer.resonantgeodes.registry.entity;

import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.registry.Registry;
import com.mcsuperplayer.resonantgeodes.registry.menu.DrillControllerMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DrillControllerBlockEntity extends BlockEntity implements MenuProvider {
	private boolean structureValid;
	private int tickCounter = 0;

	public DrillControllerBlockEntity(BlockPos pos, BlockState state) {
		super(Registry.DRILL_CONTROLLER_BLOCK_ENTITY.get(), pos, state);
		this.structureCheck();
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
		ResonantGeodes.debug("Drill Controller Menu created");
		return new DrillControllerMenu(id, playerInv, this);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.resonantgeodes.drill_controller");
	}

	public boolean isStructureValid() {
		return this.structureValid;
	}

	private void structureCheck() {
		this.structureValid = false;
	}

	public void tick() {
		if (!structureValid) return;
		this.tickCounter++;
		if (this.tickCounter >= 200) {
				
		}
	}
	
}
