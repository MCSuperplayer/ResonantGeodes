package com.mcsuperplayer.resonantgeodes.registry.block;

import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GeodeCoreBlock extends MaterializedBlock {
	private static final Direction[] directions = Direction.values();

	public GeodeCoreBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (random.nextInt(5) == 0) {
			Direction direction = directions[random.nextInt(directions.length)];
			BlockPos crystalPos = pos.relative(direction);
			BlockState crystalState = level.getBlockState(crystalPos);
			Block block = null;
			if (crystalState.isAir()) {
				block = Registry.CRYSTAL_BUD_SMALL.get();
			}
			if (crystalState.is(Registry.CRYSTAL_BUD_SMALL.get())) {
				block = Registry.CRYSTAL_BUD_MEDIUM.get();
			}
			
			if (block != null) {
				BlockState newState = block.defaultBlockState();
				level.setBlockAndUpdate(crystalPos, newState);
			}
		}
	}
}
