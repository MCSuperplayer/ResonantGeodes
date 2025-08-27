package com.mcsuperplayer.resonantgeodes.world;

import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RGConfiguredFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> GEODE_KEY = registerKey("geode");

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		ResonantGeodes.debug("configured feature bootstrap");
		register(context, GEODE_KEY, Registry.GEODE_FEATURE.get(), NoneFeatureConfiguration.INSTANCE);
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey
				.create(Registries.CONFIGURED_FEATURE,
						ResourceLocation.fromNamespaceAndPath(ResonantGeodes.MODID, name));
	}

	private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
			BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
			FC configuration) {
		context.register(key, new ConfiguredFeature<>(feature, configuration));
	}
}
