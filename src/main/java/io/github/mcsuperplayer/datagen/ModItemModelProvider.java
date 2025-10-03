package io.github.mcsuperplayer.datagen;

import io.github.mcsuperplayer.ResonantGeodes;
import io.github.mcsuperplayer.registry.Registry;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, ResonantGeodes.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		withExistingParent(Registry.GEODE_CORE.get().asItem().toString(), modLoc("block/geode_core"));
		withExistingParent(Registry.GEODE_CRYSTAL_BLOCK_HIGH.get().asItem().toString(), modLoc("block/pure_crystal_block"));
		withExistingParent(Registry.GEODE_CRYSTAL_BLOCK_LOW.get().asItem().toString(), modLoc("block/impure_crystal_block"));
		withExistingParent(Registry.DRILL_MACHINE_BLOCK.get().asItem().toString(), modLoc("block/drill_machine_block"));
		withExistingParent(Registry.MACHINE_ITEM_OUTPUT.get().asItem().toString(), modLoc("block/machine_item_output"));
		//withExistingParent(Registry.CRYSTAL_BURNER_MACHINE_BLOCK.get().asItem().toString(), modLoc("block/resonance_diffuser"));
		withExistingParent(Registry.MACHINE_ITEM_INPUT.get().asItem().toString(), modLoc("block/machine_item_input"));
		basicItem(Registry.RESONATOR_ITEM.get());
		basicItem(Registry.BOOK.get());
		basicItem(Registry.CRYSTAL_CLUSTER_ITEM.get());
		basicItem(Registry.CRYSTAL_BUD_LARGE_ITEM.get());
		basicItem(Registry.CRYSTAL_BUD_MEDIUM_ITEM.get());
		basicItem(Registry.CRYSTAL_BUD_SMALL_ITEM.get());
		basicItem(Registry.CRYSTAL_FRAGMENT.get());
	}

}
