package io.github.mcsuperplayer;

import io.github.mcsuperplayer.registry.MaterialRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ResonantGeodes.MODID, bus = Bus.MOD)
public class ModBusEvents {

	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		MaterialRegistry.load();
	}
}
