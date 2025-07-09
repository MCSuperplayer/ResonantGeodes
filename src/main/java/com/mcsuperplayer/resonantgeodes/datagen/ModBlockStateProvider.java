package com.mcsuperplayer.resonantgeodes.datagen;

import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, ResonantGeodes.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(Registry.GEODE_CORE.get());
		simpleBlock(Registry.GEODE_CRYSTAL_BLOCK_HIGH.get());
		simpleBlock(Registry.GEODE_CRYSTAL_BLOCK_LOW.get());
		directionalBlock(Registry.CRYSTAL_BUD_LARGE.get(), models().cross("crystal_bud_large", modLoc("block/crystal_bud_large")).renderType("cutout"));
		directionalBlock(Registry.CRYSTAL_BUD_MEDIUM.get(),models().cross("crystal_bud_medium", modLoc("block/crystal_bud_medium")).renderType("cutout"));
		directionalBlock(Registry.CRYSTAL_BUD_SMALL.get(), models().cross("crystal_bud_small", modLoc("block/crystal_bud_small")).renderType("cutout"));
		directionalBlock(Registry.CRYSTAL_CLUSTER.get(), models().cross("crystal_cluster", modLoc("block/crystal_cluster")).renderType("cutout"));
		horizontalBlock(Registry.DRILL_MACHINE_BLOCK.get(),models().orientable("drill_machine_block", modLoc("block/drill_machine_side"),modLoc("block/drill_machine_front"), modLoc("block/drill_machine_side")));
	}

}
