package io.github.mcsuperplayer.registry.block;

import io.github.mcsuperplayer.registry.entity.MaterialBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class MaterializedBlock extends Block implements EntityBlock {

	public MaterializedBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new MaterialBlockEntity(pos, state);
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(level, pos, state, placer, stack);

		if (!level.isClientSide) {
			BlockEntity be = level.getBlockEntity(pos);
			if (be instanceof MaterialBlockEntity mbe) {
				CompoundTag tag = stack.getOrCreateTag();
				String material = tag.getString("geode_material");
				mbe.setMaterial(material);
			}
		}
	}
	
	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		ItemStack stack = this.asItem().getDefaultInstance();
		CompoundTag tag = stack.getOrCreateTag();
		MaterialBlockEntity mbe = (MaterialBlockEntity) level.getBlockEntity(pos);
		String material = mbe.getMaterial();
		tag.putString("geode_material", material);
		return stack;
	}

	@Override
	public MutableComponent getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}
}
