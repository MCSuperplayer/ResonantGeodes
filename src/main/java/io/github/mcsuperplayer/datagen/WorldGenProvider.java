package io.github.mcsuperplayer.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import io.github.mcsuperplayer.ResonantGeodes;
import io.github.mcsuperplayer.world.RGBiomeModifiers;
import io.github.mcsuperplayer.world.RGConfiguredFeatures;
import io.github.mcsuperplayer.world.RGPlacedFeatures;
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
