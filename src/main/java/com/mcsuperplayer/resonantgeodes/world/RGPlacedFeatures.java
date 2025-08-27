package com.mcsuperplayer.resonantgeodes.world;

import java.util.List;

import com.mcsuperplayer.resonantgeodes.ResonantGeodes;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class RGPlacedFeatures {
	public static final ResourceKey<PlacedFeature> GEODE_PLACED_KEY = registerKey("geode_placed");
	
	
	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		ResonantGeodes.debug("placed feature bootstrap");
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
		register(context, GEODE_PLACED_KEY, configuredFeatures.getOrThrow(RGConfiguredFeatures.GEODE_KEY), List
				.of(CountPlacement.of(1),
						HeightRangePlacement.uniform(VerticalAnchor.absolute(-50), VerticalAnchor.absolute(40))));
	}
	
	
	public static ResourceKey<PlacedFeature> registerKey(String name) {
		return ResourceKey
				.create(Registries.PLACED_FEATURE,
						ResourceLocation.fromNamespaceAndPath(ResonantGeodes.MODID, name));
	}

	private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
			Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
		context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
	}
}
