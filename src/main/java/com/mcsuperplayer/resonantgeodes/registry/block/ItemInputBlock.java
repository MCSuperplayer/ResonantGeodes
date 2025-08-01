package com.mcsuperplayer.resonantgeodes.registry.block;

import com.mcsuperplayer.resonantgeodes.registry.entity.ItemInputBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ItemInputBlock extends Block implements EntityBlock {

	public ItemInputBlock(Properties p_49795_) {
		super(p_49795_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new ItemInputBlockEntity(p_153215_, p_153216_);
	}

}
