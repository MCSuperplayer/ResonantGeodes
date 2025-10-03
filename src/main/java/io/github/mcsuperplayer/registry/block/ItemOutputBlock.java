package io.github.mcsuperplayer.registry.block;

import io.github.mcsuperplayer.registry.entity.ItemOutputBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ItemOutputBlock extends Block implements EntityBlock {

	public ItemOutputBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ItemOutputBlockEntity(pos, state);
	}

}
