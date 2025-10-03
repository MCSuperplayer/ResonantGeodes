package io.github.mcsuperplayer.datagen;

import com.klikli_dev.modonomicon.api.datagen.AbstractModonomiconLanguageProvider;

import io.github.mcsuperplayer.ResonantGeodes;
import io.github.mcsuperplayer.registry.Registry;
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
		this.addBlock(Registry.MACHINE_ITEM_OUTPUT, "Machine Item Output ");
		this.addBlock(Registry.CRYSTAL_BURNER_MACHINE_BLOCK, "Crystalline Resonance Diffuser");
		this.addBlock(Registry.MACHINE_ITEM_INPUT, "Machine Item Input");
		this.add("container.resonantgeodes.drill_controller", "Geode Drill Controller");
		this.add("container.resonantgeodes.resonance_diffuser", "Resonance Diffuser");
		this.add("text.resonantgeodes.structure_invalid", "Structure Invalid!");
		this.add("text.resonantgeodes.structure_valid", "Structure Complete!");
		this.add("text.resonantgeodes.low_resonance", "Not Enough Resonance!");
		this.add("text.resonantgeodes.high_resonance", "Too Much Resonance");
	}
}
