package com.mcsuperplayer.resonantgeodes.registry.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MaterializedItem extends Item {

	public MaterializedItem(Properties properties) {
		super(properties);
	}

	@Override
	public Component getName(ItemStack stack) {
		return materializedGetName(stack);
	}

	// For both MaterializedItem and MaterializedBlockItem
	public static Component materializedGetName(ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		String materialId = tag.getString("geode_material");
		ResourceLocation id = ResourceLocation.parse(materialId);
		String materialName = id.getPath();
		if (materialName == "") materialName = "unknown";
		materialName = Character.toUpperCase(materialName.charAt(0)) + materialName.substring(1);
		return Component.literal(Component.translatable(stack.getDescriptionId()).getString() + " (" + materialName + ")");

	}

}
