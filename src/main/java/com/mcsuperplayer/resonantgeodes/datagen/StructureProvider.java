package com.mcsuperplayer.resonantgeodes.datagen;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.klikli_dev.modonomicon.data.MultiblockDataManager;
import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

public class StructureProvider implements DataProvider {

	private final Map<String, JsonElement> toSerialize = new HashMap<>();
	private final DataGenerator generator;

	public StructureProvider(DataGenerator generator) {
		this.generator = generator;
	}

	private void start() {
		this.addStructure("geode_drill", this.createPattern(
				List.of(
						List.of("   ", "   ", "   ", " B ", "   ", "   ", "   "),
						List.of("   ", "   ", " H ", " D ", " H ", "   ", "   "),
						List.of("   ", " H ", "HHH", "HHH", "HHH", " H ", "   "),
						List.of(" H ", "HHH", "TTT", "THT", "TTT", "HHH", " H "),
						List.of(" H ", "HHH", "THT", "T0T", "THT", "HHH", " H "),
						List.of(" H ", "HHH", "TTT", "TTT", "TTT", "HHH", " H "),
						List.of("   ", " H ", "HHH", "HHH", "HHH", " H ", "   "),
						List.of("   ", "   ", " H ", " H ", " H ", "   ", "   "))
				),new MappingBuilder()
					.geodeCore()
					.drillController()
					.crystalBlockHigh()
					.crystalCluster()
					.tintedGlass()
					.build());
	}

	private List<List<String>> createPattern(List<List<String>> layers) {
		List<List<String>> fullPattern = new ArrayList<>();
		for (List<String> layer : layers) {
			List<String> layerPattern = new ArrayList<>();
			for (String row : layer) {
				layerPattern.add(row.replace(" ", "_"));
			}
			fullPattern.add(layerPattern);
		}
		return fullPattern;
	}

	private void addStructure(String name, List<List<String>> pattern, Map<Character, JsonElement> mappings) {
		this.addStructure(ResourceLocation.parse(ResonantGeodes.MODID + ":" + name), pattern, mappings);
	}

	private void addStructure(ResourceLocation rl, List<List<String>> pattern, Map<Character, JsonElement> mappings) {
		JsonObject json = new JsonObject();

		json.addProperty("type", "modonomicon:dense");

		JsonArray outerPattern = new JsonArray();
		for (List<String> layer : pattern) {
			JsonArray innerPattern = new JsonArray();
			for (String row : layer) {
				innerPattern.add(row);
			}
			outerPattern.add(innerPattern);
		}

		json.add("pattern", outerPattern);

		JsonObject jsonMapping = new JsonObject();
		for (Entry<Character, JsonElement> entry : mappings.entrySet())
			jsonMapping.add(String.valueOf(entry.getKey()), entry.getValue());
		json.add("mapping", jsonMapping);

		this.toSerialize.put(rl.getPath(), json);
	}

	@Override
	public CompletableFuture<?> run(CachedOutput cache) {
		List<CompletableFuture<?>> futures = new ArrayList<>();

		Path folder = this.generator.getPackOutput().getOutputFolder();

		this.start();

		this.toSerialize.forEach((name, json) -> {
			Path path = folder
					.resolve(
							"data/" + ResonantGeodes.MODID + "/" + MultiblockDataManager.FOLDER + "/" + name + ".json");
			futures.add(DataProvider.saveStable(cache, json, path));
		});

		return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
	}

	@Override
	public String getName() {
		return "Multiblocks: " + ResonantGeodes.MODID;
	}

	private static class MappingBuilder {
		private final Map<Character, JsonElement> mappings = new HashMap<>();

		public MappingBuilder() {
		}

		private MappingBuilder element(char c, JsonElement e) {
			this.mappings.put(c, e);
			return this;
		}

		private Map<Character, JsonElement> build() {
			return this.mappings;
		}

		private MappingBuilder block(char c, Supplier<? extends Block> b) {
			JsonObject json = new JsonObject();
			json.addProperty("type", "modonomicon:block");
			json.addProperty("block", ForgeRegistries.BLOCKS.getKey(b.get()).toString());
			return this.element(c, json);
		}

		@SuppressWarnings("unused")
		private MappingBuilder blockDisplay(char c, Supplier<? extends Block> b, Supplier<? extends Block> display) {
			JsonObject json = new JsonObject();
			json.addProperty("type", "modonomicon:block");
			json.addProperty("block", ForgeRegistries.BLOCKS.getKey(b.get()).toString());
			json.addProperty("display", ForgeRegistries.BLOCKS.getKey(display.get()).toString());
			return this.element(c, json);
		}

		@SuppressWarnings("unused")
		private MappingBuilder display(char c, Supplier<? extends Block> display) {
			JsonObject json = new JsonObject();
			json.addProperty("type", "modonomicon:display");
			json.addProperty("display", ForgeRegistries.BLOCKS.getKey(display.get()).toString());
			return this.element(c, json);
		}

		@SuppressWarnings("unused")
		private MappingBuilder tag(char c, TagKey<Block> tag) {
			JsonObject json = new JsonObject();
			json.addProperty("type", "modonomicon:tag");
			json.addProperty("tag", "#" + tag.location());
			return this.element(c, json);
		}

		private MappingBuilder drillController() {
			return this.block('D', Registry.DRILL_MACHINE_BLOCK);
		}

		private MappingBuilder crystalBlockHigh() {
			return this.block('H', Registry.GEODE_CRYSTAL_BLOCK_HIGH);
		}

		private MappingBuilder crystalCluster() {
			return this.block('B', Registry.CRYSTAL_CLUSTER);
		}

		private MappingBuilder tintedGlass() {
			return this.block('T', () -> Blocks.TINTED_GLASS);
		}

		private MappingBuilder geodeCore() {
			return this.block('0', Registry.GEODE_CORE);
		}
	}
}
