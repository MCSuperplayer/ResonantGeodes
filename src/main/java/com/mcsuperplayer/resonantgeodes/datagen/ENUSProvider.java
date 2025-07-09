package com.mcsuperplayer.resonantgeodes.datagen;

import com.klikli_dev.modonomicon.api.datagen.AbstractModonomiconLanguageProvider;
import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.data.PackOutput;

public class ENUSProvider extends AbstractModonomiconLanguageProvider {

	public ENUSProvider(PackOutput output) {
		super(output, ResonantGeodes.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add("itemGroup.resonantgeodes", "Resonant Geodes");

		this.addItem(Registry.RESONATOR_ITEM, "Geode Resonator");
		this.addItem(Registry.BOOK, "Book of Geodes");
		this.addBlock(Registry.GEODE_CORE, "Geode Core");
		this.addBlock(Registry.GEODE_CRYSTAL_BLOCK_HIGH, "Pure Crystal Block");
		this.addBlock(Registry.GEODE_CRYSTAL_BLOCK_LOW, "Impure Crystal Block");
		this.addBlock(Registry.CRYSTAL_CLUSTER, "Crystal Cluster");
		this.addBlock(Registry.CRYSTAL_BUD_LARGE, "Large Crystal Bud");
		this.addBlock(Registry.CRYSTAL_BUD_MEDIUM, "Medium Crystal Bud");
		this.addBlock(Registry.CRYSTAL_BUD_SMALL, "Small Crystal Bud");
		this.addItem(Registry.CRYSTAL_FRAGMENT, "Crystal Fragment");
		this.addBlock(Registry.DRILL_MACHINE_BLOCK, "Geode Drill Controller");
	}
}
