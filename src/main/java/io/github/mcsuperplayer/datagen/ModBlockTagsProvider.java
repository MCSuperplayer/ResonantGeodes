package io.github.mcsuperplayer.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import io.github.mcsuperplayer.ResonantGeodes;
import io.github.mcsuperplayer.registry.Registry;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
	public static final TagKey<Block> MATERIAL_BLOCKS = BlockTags.create(ResourceLocation.fromNamespaceAndPath(ResonantGeodes.MODID, "material_blocks"));

	public ModBlockTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, ResonantGeodes.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
				Registry.GEODE_CORE.get(),
				Registry.GEODE_CRYSTAL_BLOCK_HIGH.get(),
				Registry.GEODE_CRYSTAL_BLOCK_LOW.get(),
				Registry.CRYSTAL_CLUSTER.get(),
				Registry.CRYSTAL_BUD_LARGE.get(),
				Registry.CRYSTAL_BUD_MEDIUM.get(),
				Registry.CRYSTAL_BUD_SMALL.get(),
				Registry.DRILL_MACHINE_BLOCK.get(),
				Registry.CRYSTAL_BURNER_MACHINE_BLOCK.get(),
				Registry.MACHINE_ITEM_INPUT.get());

		tag(BlockTags.NEEDS_IRON_TOOL).add(
				Registry.GEODE_CORE.get(), 
				Registry.GEODE_CRYSTAL_BLOCK_HIGH.get(),
				Registry.GEODE_CRYSTAL_BLOCK_LOW.get(),
				Registry.CRYSTAL_CLUSTER.get(),
				Registry.CRYSTAL_BUD_LARGE.get(),
				Registry.CRYSTAL_BUD_MEDIUM.get(),
				Registry.CRYSTAL_BUD_SMALL.get(),
				Registry.DRILL_MACHINE_BLOCK.get(),
				Registry.CRYSTAL_BURNER_MACHINE_BLOCK.get(),
				Registry.MACHINE_ITEM_INPUT.get());

		tag(MATERIAL_BLOCKS).add(
				Registry.GEODE_CORE.get(),
				Registry.GEODE_CRYSTAL_BLOCK_HIGH.get(),
				Registry.GEODE_CRYSTAL_BLOCK_LOW.get(),
				Registry.CRYSTAL_CLUSTER.get(),
				Registry.CRYSTAL_BUD_LARGE.get(),
				Registry.CRYSTAL_BUD_MEDIUM.get(),
				Registry.CRYSTAL_BUD_SMALL.get());
	}
}
