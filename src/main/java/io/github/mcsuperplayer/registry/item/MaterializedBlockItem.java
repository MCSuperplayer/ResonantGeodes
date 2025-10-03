package io.github.mcsuperplayer.registry.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class MaterializedBlockItem extends BlockItem {

	public MaterializedBlockItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public Component getName(ItemStack stack) {
		return MaterializedItem.materializedGetName(stack);
	}
}
