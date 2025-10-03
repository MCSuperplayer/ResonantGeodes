package io.github.mcsuperplayer.registry.block;

import io.github.mcsuperplayer.registry.MaterialRegistry;
import io.github.mcsuperplayer.registry.Registry;
import io.github.mcsuperplayer.registry.entity.MaterialBlockEntity;
import io.github.mcsuperplayer.world.GeodeLocationStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class GeodeCoreBlock extends MaterializedBlock {

	public GeodeCoreBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (random.nextInt(5) == 0) {
			Direction[] directions = Direction.values();
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
			if (crystalState.is(Registry.CRYSTAL_BUD_MEDIUM.get())) {
				block = Registry.CRYSTAL_BUD_LARGE.get();
			}
			if (crystalState.is(Registry.CRYSTAL_BUD_LARGE.get())) {
				block = Registry.CRYSTAL_CLUSTER.get();
			}
			
			if (block != null) {
				BlockState newState = block.defaultBlockState().setValue(BlockStateProperties.FACING, direction);
				level.setBlockAndUpdate(crystalPos, newState);
				MaterialBlockEntity be = (MaterialBlockEntity) level.getBlockEntity(crystalPos);
				MaterialBlockEntity c = (MaterialBlockEntity) level.getBlockEntity(pos);
				be.setMaterial(c.getMaterial());
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newstate, boolean moving) {
		if (!state.is(newstate.getBlock())) {
			MaterialBlockEntity be = (MaterialBlockEntity) level.getBlockEntity(pos);
			if (be != null) {
				String material = be.getMaterial();
				GeodeLocationStorage storage = GeodeLocationStorage.get(level.getServer().overworld());
				storage.removeGeode(pos.getX(), pos.getY(), pos.getZ(), material);
			}
		}
		super.onRemove(state, level, pos, newstate, moving);
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (level.isClientSide)
			return;
		super.setPlacedBy(level, pos, state, placer, stack);
		String mat = stack.getOrCreateTag().getString("geode_material");
		if (mat == "") {
			mat = MaterialRegistry.randomMaterial();
			MaterialBlockEntity mbe = (MaterialBlockEntity) level.getBlockEntity(pos);
			mbe.setMaterial(mat);
		}
		GeodeLocationStorage
				.get(level.getServer().overworld())
				.addGeode(pos.getX(), pos.getY(), pos.getZ(), mat);
	}
}
