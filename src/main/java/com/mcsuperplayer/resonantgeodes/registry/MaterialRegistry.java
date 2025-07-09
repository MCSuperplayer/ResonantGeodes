package com.mcsuperplayer.resonantgeodes.registry;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.mcsuperplayer.resonantgeodes.Config;

public class MaterialRegistry {
	private static final Set<String> MATERIALS = new HashSet<>();

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
		MATERIALS.clear();
		if (Config.mekanismOres) {
			add("mekanism:osmium", "mekanism:fluorite", "mekanism:tin", "mekanism:lead", "mekanism:uranium",
					"mekanism:copper");
		}
		if (Config.thermalOres) {
			add("thermal:apatite", "thermal:cinnabar", "thermal:copper", "thermal:lead", "thermal:nickel",
					"thermal:niter", "thermal:silver", "thermal:sulfur", "thermal:tin");
		}
	}
}
