package com.mcsuperplayer.resonantgeodes.registry;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.mcsuperplayer.resonantgeodes.Config;

import net.minecraftforge.fml.ModList;

public class MaterialRegistry {
	private static final Set<String> MATERIALS = new HashSet<>();
	private static final Random RANDOM = new Random();

	public static boolean contains(String material) {
		return MATERIALS.contains(material);
	}

	public static Set<String> get() {
		return Collections.unmodifiableSet(MATERIALS);
	}

	public static void add(String... ids) {
		for (String id : ids) {
			MATERIALS.add(id);
		}
	}

	public static void remove(String... ids) {
		for (String id : ids) {
			MATERIALS.remove(id);
		}
	}

	public static void load() {
		ModList mods = ModList.get();
		MATERIALS.clear();
		if (Config.vanillaOres) {
			add("minecraft:iron", "minecraft:gold", "minecraft:diamond", "minecraft:redstone", "minecraft:lapis",
					"minecraft:copper", "minecraft:coal", "minecraft:emerald", "minecraft:quartz",
					"minecraft:ancient_debris");
		}
		if (Config.mekanismOres && (mods.isLoaded("mekanism") || Config.forceIntegration)) {
			add("mekanism:osmium", "mekanism:fluorite", "mekanism:tin", "mekanism:lead", "mekanism:uranium");
		}
		if (Config.thermalOres && (mods.isLoaded("thermal") || Config.forceIntegration)) {
			add("thermal:apatite", "thermal:cinnabar", "thermal:lead", "thermal:nickel",
					"thermal:niter", "thermal:silver", "thermal:sulfur", "thermal:tin");
		}
		if (Config.IEOres && (mods.isLoaded("immersiveengineering") || Config.forceIntegration)) {
			add("immersiveengineering:lead", "immersiveengineering:silver", "immersiveengineering:nickel",
					"immersiveengineering:uranium");
		}
		if (Config.TConOres && (mods.isLoaded("tconstruct") || Config.forceIntegration)) {
			add("tconstruct:cobalt", "tconstruct:ardite");
		}
		if (Config.createOres && (mods.isLoaded("create") || Config.forceIntegration)) {
			add("create:zinc");
		}
	}

	public static String randomMaterial() {
		return MATERIALS.toArray(new String[0])[RANDOM.nextInt(MATERIALS.size())];
	}
}
