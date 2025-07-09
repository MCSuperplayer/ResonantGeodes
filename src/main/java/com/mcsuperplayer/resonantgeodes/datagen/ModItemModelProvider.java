package com.mcsuperplayer.resonantgeodes.datagen;

import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, ResonantGeodes.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		withExistingParent(Registry.GEODE_CORE.get().asItem().toString(), modLoc("block/geode_core"));
		withExistingParent(Registry.GEODE_CRYSTAL_BLOCK_HIGH.get().asItem().toString(), modLoc("block/pure_crystal_block"));
		withExistingParent(Registry.GEODE_CRYSTAL_BLOCK_LOW.get().asItem().toString(), modLoc("block/impure_crystal_block"));
		withExistingParent(Registry.DRILL_MACHINE_BLOCK.get().asItem().toString(), modLoc("block/drill_machine_block"));
		basicItem(Registry.RESONATOR_ITEM.get());
		basicItem(Registry.BOOK.get());
		basicItem(Registry.CRYSTAL_CLUSTER_ITEM.get());
		basicItem(Registry.CRYSTAL_BUD_LARGE_ITEM.get());
		basicItem(Registry.CRYSTAL_BUD_MEDIUM_ITEM.get());
		basicItem(Registry.CRYSTAL_BUD_SMALL_ITEM.get());
	}

}
