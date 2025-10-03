package io.github.mcsuperplayer;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import io.github.mcsuperplayer.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ResonantGeodes.MODID)
public class ResonantGeodes
{
    // Define mod id in a common place for everything to reference
	public static final String MODID = "resonantgeodes";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ResonantGeodes(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
		Registry.registerMod(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

	public static void info(String msg) {
		LOGGER.info(msg);
	}

	public static void error(String msg) {
		LOGGER.error(msg);
	}

	public static void debug(String msg) {
		LOGGER.debug(msg);
	}

	public static void warn(String msg) {
		LOGGER.warn(msg);
	}
}
