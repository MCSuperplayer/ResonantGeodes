package com.mcsuperplayer.resonantgeodes.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.world.RGBiomeModifiers;
import com.mcsuperplayer.resonantgeodes.world.RGConfiguredFeatures;
import com.mcsuperplayer.resonantgeodes.world.RGPlacedFeatures;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class WorldGenProvider extends DatapackBuiltinEntriesProvider {
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, RGConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, RGPlacedFeatures::bootstrap)
			.add(ForgeRegistries.Keys.BIOME_MODIFIERS, RGBiomeModifiers::bootstrap);

	public WorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of(ResonantGeodes.MODID));
		ResonantGeodes.debug("worldgen provider ran");
	}

}
