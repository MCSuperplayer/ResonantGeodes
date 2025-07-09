package com.mcsuperplayer.resonantgeodes.datagen;

import java.util.Set;
import java.util.function.BiConsumer;

import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ModBlockLootProvider extends BlockLootSubProvider {

	protected ModBlockLootProvider() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	protected void generate() {
		add(Registry.GEODE_CORE.get(), createSilkTouchOnlyTable(Registry.GEODE_CORE.get()));

		add(Registry.GEODE_CRYSTAL_BLOCK_HIGH.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem
								.lootTableItem(Registry.CRYSTAL_FRAGMENT.get())
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)))));
		add(Registry.GEODE_CRYSTAL_BLOCK_LOW.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem
								.lootTableItem(Registry.CRYSTAL_FRAGMENT.get())
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)))));
		add(Registry.CRYSTAL_CLUSTER.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem
								.lootTableItem(Registry.CRYSTAL_FRAGMENT.get())
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))));
		add(Registry.CRYSTAL_BUD_LARGE.get(), block -> createSilkTouchOnlyTable(Registry.CRYSTAL_BUD_LARGE_ITEM.get()));
		add(Registry.CRYSTAL_BUD_MEDIUM.get(),
				block -> createSilkTouchOnlyTable(Registry.CRYSTAL_BUD_MEDIUM_ITEM.get()));
		add(Registry.CRYSTAL_BUD_SMALL.get(), block -> createSilkTouchOnlyTable(Registry.CRYSTAL_BUD_SMALL_ITEM.get()));
		dropSelf(Registry.DRILL_MACHINE_BLOCK.get());
	}

	@Override
	public void generate(BiConsumer<ResourceLocation, Builder> consumer) {
		this.generate();
		this.map.forEach(consumer::accept);
	}
}
