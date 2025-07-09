package com.mcsuperplayer.resonantgeodes.registry;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mcsuperplayer.resonantgeodes.ResonantGeodes;
import com.mcsuperplayer.resonantgeodes.registry.entity.MaterialBlockEntity;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class MaterialLootModifier extends LootModifier {
	public static final Supplier<Codec<MaterialLootModifier>> CODEC = Suppliers
			.memoize(() -> RecordCodecBuilder.<MaterialLootModifier>create(inst -> codecStart(inst).apply(inst, MaterialLootModifier::new)));

	protected MaterialLootModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		ResonantGeodes.debug("doapply run start");
		BlockEntity be = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
		if (be == null)
			return generatedLoot;
		if (!(be instanceof MaterialBlockEntity))
			return generatedLoot;
		CompoundTag beTag = be.saveWithoutMetadata();

		String material = beTag.getString("geode_material");

		generatedLoot.forEach(stack -> {
			CompoundTag stackTag = stack.getOrCreateTag();
			stackTag.putString("geode_material", material);
		});
		ResonantGeodes.debug("doapply run end");
		return generatedLoot;
	}

}
