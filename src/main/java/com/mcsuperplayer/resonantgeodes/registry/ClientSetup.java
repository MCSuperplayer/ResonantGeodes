package com.mcsuperplayer.resonantgeodes.registry;

import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.registry.menu.DrillControllerScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ResonantGeodes.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class ClientSetup {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		MenuScreens.register(Registry.DRILL_CONTROLLER_MENU.get(), DrillControllerScreen::new);
	}
}
