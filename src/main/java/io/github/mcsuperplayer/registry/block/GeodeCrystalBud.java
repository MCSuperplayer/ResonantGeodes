package io.github.mcsuperplayer.registry.block;

import io.github.mcsuperplayer.ResonantGeodes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GeodeCrystalBud extends MaterializedBlock {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	protected final VoxelShape northShape;
	protected final VoxelShape southShape;
	protected final VoxelShape eastShape;
	protected final VoxelShape westShape;
	protected final VoxelShape upShape;
	protected final VoxelShape downShape;

	public GeodeCrystalBud(double height, double h_spacing, Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.UP));
		this.upShape = Block.box((double)h_spacing, 0.0D, (double)h_spacing, (double)(16 - h_spacing), (double)height, (double)(16 - h_spacing));
	      this.downShape = Block.box((double)h_spacing, (double)(16 - height), (double)h_spacing, (double)(16 - h_spacing), 16.0D, (double)(16 - h_spacing));
	      this.northShape = Block.box((double)h_spacing, (double)h_spacing, (double)(16 - height), (double)(16 - h_spacing), (double)(16 - h_spacing), 16.0D);
	      this.southShape = Block.box((double)h_spacing, (double)h_spacing, 0.0D, (double)(16 - h_spacing), (double)(16 - h_spacing), (double)height);
	      this.eastShape = Block.box(0.0D, (double)h_spacing, (double)h_spacing, (double)height, (double)(16 - h_spacing), (double)(16 - h_spacing));
	      this.westShape = Block.box((double)(16 - height), (double)h_spacing, (double)h_spacing, 16.0D, (double)(16 - h_spacing), (double)(16 - h_spacing));
	}

	public VoxelShape getShape(BlockState p_152021_, BlockGetter p_152022_, BlockPos p_152023_,
			CollisionContext p_152024_) {
		Direction direction = p_152021_.getValue(FACING);
		switch (direction) {
		case NORTH:
			return this.northShape;
		case SOUTH:
			return this.southShape;
		case EAST:
			return this.eastShape;
		case WEST:
			return this.westShape;
		case DOWN:
			return this.downShape;
		case UP:
		default:
			return this.upShape;
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getClickedFace());
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos support = pos.relative(direction.getOpposite());
		ResonantGeodes
				.debug(direction.getName() + " "
						+ level.getBlockState(support).isFaceSturdy(level, support, direction));
		return level.getBlockState(support).isFaceSturdy(level, support, direction);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor accessor,
			BlockPos pos, BlockPos pos2) {
		if (!accessor.getBlockState(pos).canSurvive(accessor, pos))
			return Blocks.AIR.defaultBlockState();
		return state;
	}

}
