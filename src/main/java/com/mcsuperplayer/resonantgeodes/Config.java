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
			.comment("Whether to include Mekanism ores in the whitelist")
			.define("oresMekanism", true);

	private static final ForgeConfigSpec.BooleanValue INTEGRATION_THERMAL = BUILDER
			.comment("Whether to include Thermal's ores in the whitelist.")
			.define("oresThermal", true);

	private static final ForgeConfigSpec.ConfigValue<List<? extends String>> MATERIAL_WHITELIST = BUILDER
			.comment("A whitelist of materials to be added, for custom recipes and other mod's ores.")
			.defineList("whitelist", List.of(), o -> o instanceof String);

	private static final ForgeConfigSpec.ConfigValue<List<? extends String>> MATERIAL_BLACKLIST = BUILDER
			.comment("A blacklist of materials to be forcibly removed, after the whitelist and mod integration.")
			.defineList("blacklist", List.of(), o -> o instanceof String);

	private static final ForgeConfigSpec.IntValue STRUCTURE_CHECK_TIMER = BUILDER
			.comment("The time in ticks between structure validity checks")
			.defineInRange("structureCheckTimer", 100, 20, Integer.MAX_VALUE);

	private static final ForgeConfigSpec.IntValue DRILL_WORK_TIMER = BUILDER
			.comment("The time in ticks per operation of the Geode Drill")
			.defineInRange("drillWorkTime", 200, 1, Integer.MAX_VALUE);

	private static final ForgeConfigSpec.IntValue DRILL_RESONANCE_COST = BUILDER
			.comment("Amount of Crystal Resonance consumed per operation of the Drill")
			.defineInRange("drillResonanceCost", 10, 0, Integer.MAX_VALUE);

	private static final ForgeConfigSpec.IntValue BURNER_RESONANCE_PRODUCTION = BUILDER
			.comment("Amount of Crystal Resonance produced per operation of the Diffuser")
			.defineInRange("diffuserResonanceProduction", 20, 0, Integer.MAX_VALUE);

	private static final ForgeConfigSpec.IntValue BURNER_WORK_TIMER = BUILDER
			.comment("The time in ticks per operation of the Diffuser")
			.defineInRange("diffuserWorkTime", 10, 0, Integer.MAX_VALUE);

	private static final ForgeConfigSpec.IntValue MAX_RESONANCE = BUILDER
			.comment("Amount of Crystal Resonance present after which the Diffuser will stop producing more")
			.defineInRange("resonanceCap", 1000000, 0, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean mekanismOres;
	public static boolean thermalOres;
	public static List<? extends String> materialWhitelist;
	public static List<? extends String> materialBlacklist;
	public static int structureCheckTime;
	public static int drillWorkTime;
	public static int drillResonanceCost;
	public static int burnerResonanceProd;
	public static int burnerWorkTime;
	public static int maxResonance;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
		mekanismOres = INTEGRATION_MEKANISM.get();
		thermalOres = INTEGRATION_THERMAL.get();
		materialWhitelist = MATERIAL_WHITELIST.get();
		materialBlacklist = MATERIAL_BLACKLIST.get();
		structureCheckTime = STRUCTURE_CHECK_TIMER.get();
		drillWorkTime = DRILL_WORK_TIMER.get();
		drillResonanceCost = DRILL_RESONANCE_COST.get();
		burnerResonanceProd = BURNER_RESONANCE_PRODUCTION.get();
		burnerWorkTime = BURNER_WORK_TIMER.get();
		maxResonance = MAX_RESONANCE.get();
    }
}
