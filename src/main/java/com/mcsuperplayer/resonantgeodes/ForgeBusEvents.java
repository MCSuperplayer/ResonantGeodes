package com.mcsuperplayer.resonantgeodes;
import com.mcsuperplayer.resonantgeodes.registry.command.ResonantGeodesCommand;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ResonantGeodes.MODID, bus = Bus.FORGE)
public class ForgeBusEvents {
	@SubscribeEvent
	public static void onRegisterCommands(RegisterCommandsEvent event) {
		ResonantGeodesCommand.register(event.getDispatcher());
	}
}