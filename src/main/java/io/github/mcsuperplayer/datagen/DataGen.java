package io.github.mcsuperplayer.datagen;

import java.util.concurrent.CompletableFuture;

import io.github.mcsuperplayer.ResonantGeodes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ResonantGeodes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

	@SuppressWarnings("static-access")
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		PackOutput output = gen.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		
		gen.addProvider(event.includeClient(), new ModBlockStateProvider(output, helper));
		gen.addProvider(event.includeClient(), new ModItemModelProvider(output, helper));
		gen.addProvider(event.includeServer(), new ModLootTableProvider(output).create(output));
		gen.addProvider(event.includeServer(), new ModBlockTagsProvider(output, event.getLookupProvider(), helper));
		gen.addProvider(event.includeServer(), new StructureProvider(gen));
		var enUSProvider = new ENUSProvider(output);
		gen.addProvider(event.includeClient(), new ResonantGeodesBookProvider(output, ResonantGeodes.MODID, enUSProvider));
		gen.addProvider(event.includeClient(), enUSProvider);
		gen.addProvider(event.includeServer(), new WorldGenProvider(output, lookupProvider));
	}
}
