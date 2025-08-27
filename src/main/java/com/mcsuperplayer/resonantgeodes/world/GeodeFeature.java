package com.mcsuperplayer.resonantgeodes.world;

import com.mcsuperplayer.resonantgeodes.Config;
import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.registry.MaterialRegistry;
import com.mcsuperplayer.resonantgeodes.registry.Registry;
import com.mcsuperplayer.resonantgeodes.registry.block.MaterializedBlock;
import com.mcsuperplayer.resonantgeodes.registry.entity.MaterialBlockEntity;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GeodeFeature extends Feature<NoneFeatureConfiguration> {

	public GeodeFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		RandomSource random = context.random();
		WorldGenLevel level = context.level();
		BlockPos origin = context.origin().offset(7, 0, 7); // center of chunk
		if (!(level instanceof ServerLevel)) {		// Worldgen uses chance, command always places
			if (random.nextDouble() >= Config.geodeChance) {
				return false;
			}
		}
		ResonantGeodes.debug("Geode Placement...");
		String material = MaterialRegistry.randomMaterial();
		ResonantGeodes.debug("Material: " + material);
		placeGeode(origin, level, material);
		ResonantGeodes.debug("Geode Placed.");
		return true;
	}

	private static void placeGeode(BlockPos origin, WorldGenLevel level, String material) {
		ResonantGeodes.debug("Placing Geode now.");
		placeGeodeInnerCore(origin, level, material);
		placeGeodeOuterCore(origin, level, material);
		placeGeodeCalciteShell(origin, level);
		placeGeodeBasaltShell(origin, level);
	}

	private static void placeGeodeInnerCore(BlockPos origin, WorldGenLevel level, String material) {
		Block block = Registry.GEODE_CRYSTAL_BLOCK_HIGH.get();
		ResonantGeodes.debug("block set");
		fillArea(origin, level, -1, -1, -1, +1, +1, +1, block, material);
		ResonantGeodes.debug("first area fill");
		fillArea(origin, level, +2, -1, -1, +2, +1, +1, block, material);
		fillArea(origin, level, -2, -1, -1, -2, +1, +1, block, material);
		fillArea(origin, level, -1, +2, -1, +1, +2, +1, block, material);
		fillArea(origin, level, -1, -2, -1, +1, -2, +1, block, material);
		fillArea(origin, level, -1, -1, +2, +1, +1, +2, block, material);
		fillArea(origin, level, -1, -1, -2, +1, +1, +2, block, material);
		placeBlock(origin, level, 0, 0, 0, Registry.GEODE_CORE.get(), material);
		ResonantGeodes.debug("core assembled");
	}

	private static void placeGeodeOuterCore(BlockPos origin, WorldGenLevel level, String material) {
		Block block = Registry.GEODE_CRYSTAL_BLOCK_LOW.get();
		// top 3x3
		fillArea(origin, level, -1, +4, -1, +1, +4, +1, block, material);
		// top 5x5
		fillArea(origin, level, -2, +3, -2, +2, +3, +2, block, material);
		// first hollow layer (core +2)
		fillArea(origin, level, +2, +2, -2, +3, +2, +2, block, material);
		fillArea(origin, level, -2, +2, +2, +2, +2, +3, block, material);
		fillArea(origin, level, -3, +2, -2, -2, +2, +2, block, material);
		fillArea(origin, level, -2, +2, -3, +2, +2, -2, block, material);
		// second hollow layer (core +1)
		fillArea(origin, level, +3, +1, -2, +3, +1, +2, block, material);
		fillArea(origin, level, +4, +1, -1, +4, +1, +1, block, material);
		fillArea(origin, level, -2, +1, +3, +2, +1, +3, block, material);
		fillArea(origin, level, -1, +1, +4, +1, +1, +4, block, material);
		fillArea(origin, level, -3, +1, -2, -3, +1, +2, block, material);
		fillArea(origin, level, -4, +1, -1, -4, +1, +1, block, material);
		fillArea(origin, level, -2, +1, -3, +2, +1, -3, block, material);
		fillArea(origin, level, -1, +1, -4, +1, +1, -4, block, material);
		placeBlock(origin, level, -2, +1, -2, block, material);
		placeBlock(origin, level, -2, +1, +2, block, material);
		placeBlock(origin, level, +2, +1, -2, block, material);
		placeBlock(origin, level, +2, +1, +2, block, material);
		// middle layer (core)
		fillArea(origin, level, +3, +0, -2, +3, +0, +2, block, material);
		fillArea(origin, level, +4, +0, -1, +4, +0, +1, block, material);
		fillArea(origin, level, -2, +0, +3, +2, +0, +3, block, material);
		fillArea(origin, level, -1, +0, +4, +1, +0, +4, block, material);
		fillArea(origin, level, -3, +0, -2, -3, +0, +2, block, material);
		fillArea(origin, level, -4, +0, -1, -4, +0, +1, block, material);
		fillArea(origin, level, -2, +0, -3, +2, +0, -3, block, material);
		fillArea(origin, level, -1, +0, -4, +1, +0, -4, block, material);
		placeBlock(origin, level, -2, +0, -2, block, material);
		placeBlock(origin, level, -2, +0, +2, block, material);
		placeBlock(origin, level, +2, +0, -2, block, material);
		placeBlock(origin, level, +2, +0, +2, block, material);
		// second hollow layer (core -1)
		fillArea(origin, level, +3, -1, -2, +3, -1, +2, block, material);
		fillArea(origin, level, +4, -1, -1, +4, -1, +1, block, material);
		fillArea(origin, level, -2, -1, +3, +2, -1, +3, block, material);
		fillArea(origin, level, -1, -1, +4, +1, -1, +4, block, material);
		fillArea(origin, level, -3, -1, -2, -3, -1, +2, block, material);
		fillArea(origin, level, -4, -1, -1, -4, -1, +1, block, material);
		fillArea(origin, level, -2, -1, -3, +2, -1, -3, block, material);
		fillArea(origin, level, -1, -1, -4, +1, -1, -4, block, material);
		placeBlock(origin, level, -2, -1, -2, block, material);
		placeBlock(origin, level, -2, -1, +2, block, material);
		placeBlock(origin, level, +2, -1, -2, block, material);
		placeBlock(origin, level, +2, -1, +2, block, material);
		// first hollow layer (core -2)
		fillArea(origin, level, +2, -2, -2, +3, -2, +2, block, material);
		fillArea(origin, level, -2, -2, +2, +2, -2, +3, block, material);
		fillArea(origin, level, -3, -2, -2, -2, -2, +2, block, material);
		fillArea(origin, level, -2, -2, -3, +2, -2, -2, block, material);
		// bottom 5x5
		fillArea(origin, level, -2, -3, -2, +2, -3, +2, block, material);
		// bottom 3x3
		fillArea(origin, level, -1, -4, -1, +1, -4, +1, block, material);
	}

	private static void placeGeodeCalciteShell(BlockPos origin, WorldGenLevel level) {
		Block block = Blocks.CALCITE;
		// top 3x3
		fillArea(origin, level, -1, +5, -1, +1, +5, +1, block, null);
		// 5x5 ring
		fillArea(origin, level, -2, +4, -2, -2, +4, +2, block, null);
		fillArea(origin, level, +2, +4, -2, +2, +4, +2, block, null);
		fillArea(origin, level, -1, +4, -2, +1, +4, -2, block, null);
		fillArea(origin, level, -1, +4, +2, +1, +4, +2, block, null);
		// 7x7 cornerless ring
		fillArea(origin, level, +3, +3, -2, +3, +3, +2, block, null);
		fillArea(origin, level, -3, +3, -2, -3, +3, +2, block, null);
		fillArea(origin, level, -2, +3, -3, +2, +3, -3, block, null);
		fillArea(origin, level, -2, +3, +3, +2, +3, +3, block, null);
		// 9x9 circle
		fillArea(origin, level, +4, +2, -2, +4, +2, +2, block, null);
		fillArea(origin, level, -4, +2, -2, -4, +2, +2, block, null);
		fillArea(origin, level, -2, +2, +4, +2, +2, +4, block, null);
		fillArea(origin, level, -2, +2, -4, +2, +2, -4, block, null);
		placeBlock(origin, level, -3, +2, -3, block, null);
		placeBlock(origin, level, -3, +2, +3, block, null);
		placeBlock(origin, level, +3, +2, -3, block, null);
		placeBlock(origin, level, +3, +2, +3, block, null);
		// core rings
		fillArea(origin, level, +5, -1, -1, +5, +1, +1, block, null);
		fillArea(origin, level, -5, -1, -1, -5, +1, +1, block, null);
		fillArea(origin, level, -1, -1, +5, +1, +1, +5, block, null);
		fillArea(origin, level, -1, -1, -5, +1, +1, -5, block, null);
		fillArea(origin, level, +4, -1, -2, +4, +1, -2, block, null);
		fillArea(origin, level, +4, -1, +2, +4, +1, +2, block, null);
		fillArea(origin, level, -4, -1, -2, -4, +1, -2, block, null);
		fillArea(origin, level, -4, -1, +2, -4, +1, +2, block, null);
		fillArea(origin, level, -2, -1, -4, -2, +1, -4, block, null);
		fillArea(origin, level, +2, -1, -4, +2, +1, -4, block, null);
		fillArea(origin, level, -2, -1, +4, -2, +1, +4, block, null);
		fillArea(origin, level, +2, -1, +4, +2, +1, +4, block, null);
		fillArea(origin, level, -3, -1, -3, -3, +1, -3, block, null);
		fillArea(origin, level, -3, -1, +3, -3, +1, +3, block, null);
		fillArea(origin, level, +3, -1, -3, +3, +1, -3, block, null);
		fillArea(origin, level, +3, -1, +3, +3, +1, +3, block, null);
		// 9x9 circle
		fillArea(origin, level, +4, -2, -2, +4, -2, +2, block, null);
		fillArea(origin, level, -4, -2, -2, -4, -2, +2, block, null);
		fillArea(origin, level, -2, -2, +4, +2, -2, +4, block, null);
		fillArea(origin, level, -2, -2, -4, +2, -2, -4, block, null);
		placeBlock(origin, level, -3, -2, -3, block, null);
		placeBlock(origin, level, -3, -2, +3, block, null);
		placeBlock(origin, level, +3, -2, -3, block, null);
		placeBlock(origin, level, +3, -2, +3, block, null);
		// 7x7 cornerless ring
		fillArea(origin, level, +3, -3, -2, +3, -3, +2, block, null);
		fillArea(origin, level, -3, -3, -2, -3, -3, +2, block, null);
		fillArea(origin, level, -2, -3, -3, +2, -3, -3, block, null);
		fillArea(origin, level, -2, -3, +3, +2, -3, +3, block, null);
		// 5x5 ring
		fillArea(origin, level, -2, -4, -2, -2, -4, +2, block, null);
		fillArea(origin, level, +2, -4, -2, +2, -4, +2, block, null);
		fillArea(origin, level, -1, -4, -2, +1, -4, -2, block, null);
		fillArea(origin, level, -1, -4, +2, +1, -4, +2, block, null);
		// bottom 3x3
		fillArea(origin, level, -1, -5, -1, +1, -5, +1, block, null);

	}

	private static void placeGeodeBasaltShell(BlockPos origin, WorldGenLevel level) {
		Block block = Blocks.SMOOTH_BASALT;
		// top 3x3
		fillArea(origin, level, -1, +6, -1, +1, +6, +1, block, null);
		// 5x5 ring
		fillArea(origin, level, -2, +5, -2, -2, +5, +2, block, null);
		fillArea(origin, level, +2, +5, -2, +2, +5, +2, block, null);
		fillArea(origin, level, -1, +5, -2, +1, +5, -2, block, null);
		fillArea(origin, level, -1, +5, +2, +1, +5, +2, block, null);
		// 7x7 cornerless ring
		fillArea(origin, level, +3, +4, -2, +3, +4, +2, block, null);
		fillArea(origin, level, -3, +4, -2, -3, +4, +2, block, null);
		fillArea(origin, level, -2, +4, -3, +2, +4, -3, block, null);
		fillArea(origin, level, -2, +4, +3, +2, +4, +3, block, null);
		// 9x9 circle
		fillArea(origin, level, +4, +3, -2, +4, +3, +2, block, null);
		fillArea(origin, level, -4, +3, -2, -4, +3, +2, block, null);
		fillArea(origin, level, -2, +3, +4, +2, +3, +4, block, null);
		fillArea(origin, level, -2, +3, -4, +2, +3, -4, block, null);
		placeBlock(origin, level, -3, +3, -3, block, null);
		placeBlock(origin, level, -3, +3, +3, block, null);
		placeBlock(origin, level, +3, +3, -3, block, null);
		placeBlock(origin, level, +3, +3, +3, block, null);
		// 11x11 ring
		fillArea(origin, level, +5, +2, -2, +5, +2, +2, block, null);
		fillArea(origin, level, -5, +2, -2, -5, +2, +2, block, null);
		fillArea(origin, level, -2, +2, +5, +2, +2, +5, block, null);
		fillArea(origin, level, -2, +2, -5, +2, +2, -5, block, null);
		placeBlock(origin, level, -3, +2, -4, block, null);
		placeBlock(origin, level, -4, +2, -3, block, null);
		placeBlock(origin, level, +3, +2, +4, block, null);
		placeBlock(origin, level, +4, +2, +3, block, null);
		placeBlock(origin, level, -3, +2, +4, block, null);
		placeBlock(origin, level, -4, +2, +3, block, null);
		placeBlock(origin, level, +3, +2, -4, block, null);
		placeBlock(origin, level, +4, +2, -3, block, null);
		// core rings
		fillArea(origin, level, +6, -1, -1, +6, +1, +1, block, null);
		fillArea(origin, level, -6, -1, -1, -6, +1, +1, block, null);
		fillArea(origin, level, -1, -1, +6, +1, +1, +6, block, null);
		fillArea(origin, level, -1, -1, -6, +1, +1, -6, block, null);
		fillArea(origin, level, +5, -1, -2, +5, +1, -2, block, null);
		fillArea(origin, level, +5, -1, +2, +5, +1, +2, block, null);
		fillArea(origin, level, -5, -1, -2, -5, +1, -2, block, null);
		fillArea(origin, level, -5, -1, +2, -5, +1, +2, block, null);
		fillArea(origin, level, -2, -1, +5, -2, +1, +5, block, null);
		fillArea(origin, level, +2, -1, +5, +2, +1, +5, block, null);
		fillArea(origin, level, -2, -1, -5, -2, +1, -5, block, null);
		fillArea(origin, level, +2, -1, -5, +2, +1, -5, block, null);
		fillArea(origin, level, +4, -1, -3, +4, +1, -3, block, null);
		fillArea(origin, level, +4, -1, +3, +4, +1, +3, block, null);
		fillArea(origin, level, -4, -1, -3, -4, +1, -3, block, null);
		fillArea(origin, level, -4, -1, +3, -4, +1, +3, block, null);
		fillArea(origin, level, +3, -1, +4, +3, +1, +4, block, null);
		fillArea(origin, level, -3, -1, +4, -3, +1, +4, block, null);
		fillArea(origin, level, +3, -1, -4, +3, +1, -4, block, null);
		fillArea(origin, level, -3, -1, -4, -3, +1, -4, block, null);
		// 11x11 ring
		fillArea(origin, level, +5, -2, -2, +5, -2, +2, block, null);
		fillArea(origin, level, -5, -2, -2, -5, -2, +2, block, null);
		fillArea(origin, level, -2, -2, +5, +2, -2, +5, block, null);
		fillArea(origin, level, -2, -2, -5, +2, -2, -5, block, null);
		placeBlock(origin, level, -3, -2, -4, block, null);
		placeBlock(origin, level, -4, -2, -3, block, null);
		placeBlock(origin, level, +3, -2, +4, block, null);
		placeBlock(origin, level, +4, -2, +3, block, null);
		placeBlock(origin, level, -3, -2, +4, block, null);
		placeBlock(origin, level, -4, -2, +3, block, null);
		placeBlock(origin, level, +3, -2, -4, block, null);
		placeBlock(origin, level, +4, -2, -3, block, null);
		// 9x9 circle
		fillArea(origin, level, +4, -3, -2, +4, -3, +2, block, null);
		fillArea(origin, level, -4, -3, -2, -4, -3, +2, block, null);
		fillArea(origin, level, -2, -3, +4, +2, -3, +4, block, null);
		fillArea(origin, level, -2, -3, -4, +2, -3, -4, block, null);
		placeBlock(origin, level, -3, -3, -3, block, null);
		placeBlock(origin, level, -3, -3, +3, block, null);
		placeBlock(origin, level, +3, -3, -3, block, null);
		placeBlock(origin, level, +3, -3, +3, block, null);
		// 7x7 cornerless ring
		fillArea(origin, level, +3, -4, -2, +3, -4, +2, block, null);
		fillArea(origin, level, -3, -4, -2, -3, -4, +2, block, null);
		fillArea(origin, level, -2, -4, -3, +2, -4, -3, block, null);
		fillArea(origin, level, -2, -4, +3, +2, -4, +3, block, null);
		// 5x5 ring
		fillArea(origin, level, -2, -5, -2, -2, -5, +2, block, null);
		fillArea(origin, level, +2, -5, -2, +2, -5, +2, block, null);
		fillArea(origin, level, -1, -5, -2, +1, -5, -2, block, null);
		fillArea(origin, level, -1, -5, +2, +1, -5, +2, block, null);
		// bottom 3x3
		fillArea(origin, level, -1, -6, -1, +1, -6, +1, block, null);
	}

	private static void placeBlock(BlockPos origin, WorldGenLevel level, int x, int y, int z, Block block, String material) {
		ResonantGeodes.debug("place block call");
		BlockPos pos = origin.offset(x, y, z);
		level.setBlock(pos, block.defaultBlockState(), 3);
		if (block instanceof MaterializedBlock) {
			MaterialBlockEntity be = (MaterialBlockEntity) level.getBlockEntity(pos);
			be.setMaterial(material);
		}
	}

	private static void fillArea(BlockPos origin, WorldGenLevel level, int x1, int y1, int z1, int x2, int y2, int z2,
			Block block, String material) {
		ResonantGeodes.debug("fill area call");
		if ((x2 < x1) || (y2 < y1) || (z2 < z1)) {
			ResonantGeodes
					.debug("Mistake in Geode placement, x1;" + x1 + ", x2:" + x2 + ", y1:" + y1 + ", y2:" + y2 + ", z1:"
							+ z1 + ", z2:" + z2);
		}
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				for (int z = z1; z <= z2; z++) {
					placeBlock(origin, level, x, y, z, block, material);
				}
			}
		}
	}
}
