package com.mcsuperplayer.resonantgeodes.world;

import com.mcsuperplayer.resonantgeodes.ResonantGeodes;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class RGBiomeModifiers {

	public static final ResourceKey<BiomeModifier> ADD_GEODES = registerKey("add_geodes");

	public static void bootstrap(BootstapContext<BiomeModifier> context) {
		ResonantGeodes.debug("biome modifier bootstrap");
		var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		var biomes = context.lookup(Registries.BIOME);

		context
				.register(ADD_GEODES,
						new ForgeBiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
								HolderSet.direct(placedFeatures.getOrThrow(RGPlacedFeatures.GEODE_PLACED_KEY)),
								GenerationStep.Decoration.UNDERGROUND_DECORATION));
	}

	private static ResourceKey<BiomeModifier> registerKey(String name) {
		return ResourceKey
				.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
						ResourceLocation.fromNamespaceAndPath(ResonantGeodes.MODID, name));
	}
}
