package io.github.mcsuperplayer.registry;

import com.mojang.serialization.Codec;

import io.github.mcsuperplayer.ResonantGeodes;
import io.github.mcsuperplayer.network.ResonantGeodesPackets;
import io.github.mcsuperplayer.registry.block.CrystalBurnerBlock;
import io.github.mcsuperplayer.registry.block.DrillMachineBlock;
import io.github.mcsuperplayer.registry.block.GeodeCoreBlock;
import io.github.mcsuperplayer.registry.block.GeodeCrystalBud;
import io.github.mcsuperplayer.registry.block.ItemInputBlock;
import io.github.mcsuperplayer.registry.block.ItemOutputBlock;
import io.github.mcsuperplayer.registry.block.MaterializedBlock;
import io.github.mcsuperplayer.registry.entity.CrystalBurnerBlockEntity;
import io.github.mcsuperplayer.registry.entity.DrillControllerBlockEntity;
import io.github.mcsuperplayer.registry.entity.ItemInputBlockEntity;
import io.github.mcsuperplayer.registry.entity.ItemOutputBlockEntity;
import io.github.mcsuperplayer.registry.entity.MaterialBlockEntity;
import io.github.mcsuperplayer.registry.item.GuideBookItem;
import io.github.mcsuperplayer.registry.item.MaterializedBlockItem;
import io.github.mcsuperplayer.registry.item.MaterializedItem;
import io.github.mcsuperplayer.registry.menu.CrystalBurnerMenu;
import io.github.mcsuperplayer.registry.menu.DrillControllerMenu;
import io.github.mcsuperplayer.world.GeodeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registry {
	static IEventBus bus;

	public static void registerMod(IEventBus modEventBus) {
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
		HIDDEN_ITEMS.register(modEventBus);
		CREATIVE_MODE_TABS.register(modEventBus);
		BLOCK_ENTITIES.register(modEventBus);
		LOOT_MODIFIERS.register(modEventBus);
		MENUS.register(modEventBus);
		FEATURES.register(modEventBus);
		// CONFIGURED_FEATURES.register(modEventBus);
		// PLACED_FEATURES.register(modEventBus);
		ResonantGeodesPackets.register();
	}
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister
			.create(ForgeRegistries.BLOCKS, ResonantGeodes.MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister
			.create(ForgeRegistries.ITEMS, ResonantGeodes.MODID);
	public static final DeferredRegister<Item> HIDDEN_ITEMS = DeferredRegister
			.create(ForgeRegistries.ITEMS, ResonantGeodes.MODID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
			.create(Registries.CREATIVE_MODE_TAB, ResonantGeodes.MODID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
			.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ResonantGeodes.MODID);
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister
			.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ResonantGeodes.MODID);
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister
			.create(ForgeRegistries.MENU_TYPES, ResonantGeodes.MODID);

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister
			.create(ForgeRegistries.FEATURES, ResonantGeodes.MODID);


	// ITEMS
	public static final RegistryObject<GuideBookItem> BOOK = HIDDEN_ITEMS
			.register("book_of_geodes", () -> new GuideBookItem(new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> RESONATOR_ITEM = ITEMS
			.register("geode_resonator", () -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> CRYSTAL_FRAGMENT = ITEMS
			.register("crystal_fragment", () -> new MaterializedItem(new Item.Properties()));

	// BLOCKS
	public static final RegistryObject<Block> GEODE_CORE = BLOCKS
			.register("geode_core",
					() -> new GeodeCoreBlock(Properties.of()
							.mapColor(MapColor.COLOR_CYAN)
							.strength(50.0f)
							.requiresCorrectToolForDrops()
							.randomTicks()
							.lightLevel(state -> {return 8;})));

	public static final RegistryObject<Block> GEODE_CRYSTAL_BLOCK_HIGH = BLOCKS
			.register("pure_crystal_block", () -> new MaterializedBlock(Properties.copy(Blocks.AMETHYST_BLOCK)));

	public static final RegistryObject<Block> GEODE_CRYSTAL_BLOCK_LOW = BLOCKS
			.register("impure_crystal_block", () -> new MaterializedBlock(Properties.copy(Blocks.AMETHYST_BLOCK)));

	public static final RegistryObject<Block> CRYSTAL_BUD_SMALL = BLOCKS
			.register("crystal_bud_small", () -> new GeodeCrystalBud(3, 4, Properties.copy(Blocks.SMALL_AMETHYST_BUD)));
	
	public static final RegistryObject<Block> CRYSTAL_BUD_MEDIUM = BLOCKS
			.register("crystal_bud_medium", () -> new GeodeCrystalBud(4, 3, Properties.copy(Blocks.MEDIUM_AMETHYST_BUD)));

	public static final RegistryObject<Block> CRYSTAL_BUD_LARGE = BLOCKS
			.register("crystal_bud_large", () -> new GeodeCrystalBud(5, 3, Properties.copy(Blocks.LARGE_AMETHYST_BUD)));

	public static final RegistryObject<Block> CRYSTAL_CLUSTER = BLOCKS
			.register("crystal_cluster", () -> new GeodeCrystalBud(7, 3, Properties.copy(Blocks.AMETHYST_CLUSTER)));
	
	public static final RegistryObject<Block> DRILL_MACHINE_BLOCK = BLOCKS
			.register("geode_drill_block", () -> new DrillMachineBlock(Properties.of().strength(1.5F).sound(SoundType.AMETHYST)));
	
	public static final RegistryObject<Block> MACHINE_ITEM_OUTPUT = BLOCKS
			.register("machine_item_output", () -> new ItemOutputBlock(Properties.of().strength(1.5F).sound(SoundType.AMETHYST)));
	
	public static final RegistryObject<Block> CRYSTAL_BURNER_MACHINE_BLOCK = BLOCKS
			.register("diffusion_energizer", () -> new CrystalBurnerBlock(Properties.of().strength(1.5F).sound(SoundType.AMETHYST)));

	public static final RegistryObject<Block> MACHINE_ITEM_INPUT = BLOCKS
			.register("machine_item_input",
					() -> new ItemInputBlock(Properties.of().strength(1.5F).sound(SoundType.AMETHYST)));

	// BLOCKITEMS
	public static final RegistryObject<BlockItem> GEODE_CORE_ITEM = ITEMS
			.register("geode_core", () -> new MaterializedBlockItem(GEODE_CORE.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> GEODE_CRYSTAL_BLOCK_HIGH_ITEM = ITEMS
			.register("pure_crystal_block", () -> new MaterializedBlockItem(GEODE_CRYSTAL_BLOCK_HIGH.get(), new Item.Properties()));
	
	public static final RegistryObject<BlockItem> GEODE_CRYSTAL_BLOCK_LOW_ITEM = ITEMS
			.register("impure_crystal_block", () -> new MaterializedBlockItem(GEODE_CRYSTAL_BLOCK_LOW.get(), new Item.Properties()));
	
	public static final RegistryObject<BlockItem> CRYSTAL_BUD_SMALL_ITEM = ITEMS
			.register("small_crystal_bud", () -> new MaterializedBlockItem(CRYSTAL_BUD_SMALL.get(), new Item.Properties()));
	
	public static final RegistryObject<BlockItem> CRYSTAL_BUD_MEDIUM_ITEM = ITEMS
			.register("medium_crystal_bud", () -> new MaterializedBlockItem(CRYSTAL_BUD_MEDIUM.get(), new Item.Properties()));
	
	public static final RegistryObject<BlockItem> CRYSTAL_BUD_LARGE_ITEM = ITEMS
			.register("large_crystal_bud", () -> new MaterializedBlockItem(CRYSTAL_BUD_LARGE.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> CRYSTAL_CLUSTER_ITEM = ITEMS
			.register("crystal_cluster", () -> new MaterializedBlockItem(CRYSTAL_CLUSTER.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> DRILL_MACHINE_ITEM = ITEMS
			.register("geode_drill_block", () -> new BlockItem(DRILL_MACHINE_BLOCK.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> MACHINE_ITEM_OUTPUT_ITEM = ITEMS
			.register("machine_item_output", () -> new BlockItem(MACHINE_ITEM_OUTPUT.get(), new Item.Properties()));
	
	public static final RegistryObject<BlockItem> CRYSTAL_BURNER_ITEM = ITEMS
			.register("diffusion_energizer", () -> new BlockItem(CRYSTAL_BURNER_MACHINE_BLOCK.get(), new Item.Properties()));

	public static final RegistryObject<BlockItem> MACHINE_ITEM_INPUT_ITEM = ITEMS
			.register("machine_item_input", () -> new BlockItem(MACHINE_ITEM_INPUT.get(), new Item.Properties()));

	// TAB
	public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(RESONATOR_ITEM.get()::getDefaultInstance)
			.title(Component.translatable("itemGroup.resonantgeodes_tab"))
			.displayItems((displayParams, output) -> {
				ITEMS.getEntries().forEach(item -> {
					output.accept(item.get());
				});
				output.accept(BOOK.get().getCreativeModeTabDisplayStack());
			})
			.build());
	
	
	// BLOCKENTITY
	public static final RegistryObject<BlockEntityType<MaterialBlockEntity>> MATERIAL_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("material_block_entity",
					() -> BlockEntityType.Builder
							.of(MaterialBlockEntity::new, 
									GEODE_CORE.get(), 
									GEODE_CRYSTAL_BLOCK_HIGH.get(),
									GEODE_CRYSTAL_BLOCK_LOW.get(), 
									CRYSTAL_CLUSTER.get(), 
									CRYSTAL_BUD_LARGE.get(),
									CRYSTAL_BUD_MEDIUM.get(), 
									CRYSTAL_BUD_SMALL.get(),
									CRYSTAL_CLUSTER.get())
							.build(null));
	
	public static final RegistryObject<BlockEntityType<DrillControllerBlockEntity>> DRILL_CONTROLLER_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("drill_controller_block_entity",
					() -> BlockEntityType.Builder
							.of(DrillControllerBlockEntity::new, DRILL_MACHINE_BLOCK.get())
							.build(null));
	
	public static final RegistryObject<BlockEntityType<ItemOutputBlockEntity>> MACHINE_ITEM_OUTPUT_ENTITY = BLOCK_ENTITIES
			.register("machine_item_output_entity", 
					() -> BlockEntityType.Builder
							.of(ItemOutputBlockEntity::new, MACHINE_ITEM_OUTPUT.get())
							.build(null));

	public static final RegistryObject<BlockEntityType<CrystalBurnerBlockEntity>> CRYSTAL_BURNER_BLOCK_ENTITY = BLOCK_ENTITIES
			.register("diffusion_energizer_block_entity",
					() -> BlockEntityType.Builder
							.of(CrystalBurnerBlockEntity::new, CRYSTAL_BURNER_MACHINE_BLOCK.get())
							.build(null));

	public static final RegistryObject<BlockEntityType<ItemInputBlockEntity>> MACHINE_ITEM_INPUT_ENTITY = BLOCK_ENTITIES
			.register("machine_item_input_entity",
					() -> BlockEntityType.Builder
					.of(ItemInputBlockEntity::new, MACHINE_ITEM_INPUT.get())
					.build(null));

	// LOOT MODIFIER
	public static final RegistryObject<Codec<? extends IGlobalLootModifier>> MATERIALDATA_LOOT_MODIFIER = LOOT_MODIFIERS
			.register("material_data", MaterialLootModifier.CODEC);
	
	// MENU
	public static final RegistryObject<MenuType<DrillControllerMenu>> DRILL_CONTROLLER_MENU = MENUS
			.register("drill_controller_menu",
					() -> IForgeMenuType.create((id, inv, buf) -> {
						BlockPos pos = buf.readBlockPos();
						Level level = inv.player.level();
						DrillControllerBlockEntity be = (DrillControllerBlockEntity) level.getBlockEntity(pos);
						return new DrillControllerMenu(id, inv, be);
					}));
	
	public static final RegistryObject<MenuType<CrystalBurnerMenu>> CRYSTAL_BURNER_MENU = MENUS
			.register("crystal_burner_menu", () -> IForgeMenuType.create((id, inv, buf) -> {
				BlockPos pos = buf.readBlockPos();
				Level level = inv.player.level();
				CrystalBurnerBlockEntity be = (CrystalBurnerBlockEntity) level.getBlockEntity(pos);
				return new CrystalBurnerMenu(id, inv, be);
			}));


	// FEATURES
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> GEODE_FEATURE = FEATURES
	 .register("geode", () -> new GeodeFeature(NoneFeatureConfiguration.CODEC));
	 
}