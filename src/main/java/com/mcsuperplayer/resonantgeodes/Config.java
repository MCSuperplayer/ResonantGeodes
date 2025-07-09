package com.mcsuperplayer.resonantgeodes;

import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = ResonantGeodes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

	private static final ForgeConfigSpec.BooleanValue INTEGRATION_MEKANISM = BUILDER
            .comment("Whether to log the dirt block on common setup")
			.define("oresMekanism", true);

	private static final ForgeConfigSpec.BooleanValue INTEGRATION_THERMAL = BUILDER
			.comment("Whether to enable Integration for Thermal's Ores.")
			.define("oresThermal", true);

	private static final ForgeConfigSpec.ConfigValue<List<? extends String>> MATERIAL_WHITELIST = BUILDER
			.comment("A whitelist of materials to be added, for custom recipes and other mod's ores.")
			.defineList("whitelist", List.of(), o -> o instanceof String);

	private static final ForgeConfigSpec.ConfigValue<List<? extends String>> MATERIAL_BLACKLIST = BUILDER
			.comment("A blacklist of materials to be forcibly removed, after the whitelist and mod integration.")
			.defineList("blacklist", List.of(), o -> o instanceof String);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean mekanismOres;
	public static boolean thermalOres;
	public static List<? extends String> materialWhitelist;
	public static List<? extends String> materialBlacklist;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
		mekanismOres = INTEGRATION_MEKANISM.get();
		thermalOres = INTEGRATION_THERMAL.get();
		materialWhitelist = MATERIAL_WHITELIST.get();
		materialBlacklist = MATERIAL_BLACKLIST.get();
    }
}
