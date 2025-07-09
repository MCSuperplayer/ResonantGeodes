package com.mcsuperplayer.resonantgeodes.datagen;

import java.util.List;
import java.util.Set;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class ModLootTableProvider extends LootTableProvider {

	public ModLootTableProvider(PackOutput output) {
		super(output, Set.of(), List.of(new SubProviderEntry(ModBlockLootProvider::new, LootContextParamSets.BLOCK)));
	}

	public static LootTableProvider create(PackOutput output) {
		return new LootTableProvider(output, Set.of(),
				List.of(new LootTableProvider.SubProviderEntry(ModBlockLootProvider::new, LootContextParamSets.BLOCK)));
	}

}
