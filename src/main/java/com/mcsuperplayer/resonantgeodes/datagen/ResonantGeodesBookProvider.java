package com.mcsuperplayer.resonantgeodes.datagen;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.datagen.BookProvider;
import com.klikli_dev.modonomicon.api.datagen.CategoryEntryMap;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconLanguageProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookEntryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mcsuperplayer.resonantgeodes.registry.Registry;

import net.minecraft.data.PackOutput;

@SuppressWarnings("deprecation")
public class ResonantGeodesBookProvider extends BookProvider {

	public ResonantGeodesBookProvider(PackOutput packOutput, String modid, ModonomiconLanguageProvider defaultLang) {
		super("book_of_geodes", packOutput, modid, defaultLang);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void registerDefaultMacros() {
		// TODO Auto-generated method stub

	}

	@Override
	protected BookModel generateBook() {
		this.context().book("book_of_geodes");
		this.lang().add(this.context().bookName(), "Book of Geodes and Crystals");
		this.lang().add(this.context().bookTooltip(), "About Geodes and Resource Crystals");
		
		int sortNum = 1;
		var mainCategory = this.makeAboutCategory().withSortNumber(sortNum++);
		var geodesCategory = this.makeGeodesCategory().withSortNumber(sortNum++);
		
		var doneBook = BookModel
				.create(this.modLoc("book_of_geodes"), this.context().bookName())
				.withModel(this.modLoc("book_of_geodes"))
				.withTooltip(this.context().bookTooltip())
				.withCategories(mainCategory, geodesCategory)
				.withGenerateBookItem(false)
				.withCustomBookItem(this.modLoc("book_of_geodes"))
				.withAllowOpenBooksWithInvalidLinks(true)
				;
		return doneBook;
	}

	// ======================
	// 	BOOK CATEGORIES
	// ======================
	
	
	private BookCategoryModel makeAboutCategory() {
		this.context().category("about");
		this.lang().add(this.context().categoryName(), "About this Mod");

		var entryMap = ModonomiconAPI.get().getEntryMap();
		entryMap
				.setMap("__________________________________",
						"__________________________________",
						"__________________________________",
						"__________________________________",
						"________________a_________________",
						"__________________________________",
						"__________________________________",
						"__________________________________",
						"__________________________________");
		var aboutEntry = this.makeAboutEntry(entryMap, 'a');

		return BookCategoryModel
				.create(this.modLoc(this.context().categoryId()), this.context().categoryName())
				.withEntries(aboutEntry)
				.withIcon(Registry.BOOK.get());
	}

	private BookCategoryModel makeGeodesCategory() {
		this.context().category("geodes");
		this.lang().add(this.context().categoryName(), "Geodes");

		var entryMap = ModonomiconAPI.get().getEntryMap();
		entryMap
				.setMap("__________________________________",
						"__________________________________",
						"__________________________________",
						"__________________________________",
						"______________a_f_c_e_d___________",
						"__________________________________",
						"__________________________________",
						"__________________________________",
						"__________________________________");

		var aboutGeodesEntry = this.makeAboutGeodesEntry(entryMap, 'a');
		var findingGeodesEntry = this.makeFindGeodesEntry(entryMap, 'f');
		var crystalsEntry = this.makeCrystalsEntry(entryMap, 'c');
		var energizerEntry = this.makeEnergizerEntry(entryMap, 'e');
		var drillEntry = this.makeDrillEntry(entryMap, 'd');

		return BookCategoryModel
				.create(this.modLoc(this.context().categoryId()), this.context().categoryName())
				.withEntries(aboutGeodesEntry, findingGeodesEntry, crystalsEntry, energizerEntry, drillEntry)
				.withIcon(Registry.GEODE_CORE.get());
	}
	
	// ======================
	// 	BOOK ENTRIES
	// ======================
	private BookEntryModel makeAboutEntry(CategoryEntryMap entryMap, char c) {
		this.context().entry("intro");
		this.lang().add(this.context().entryName(), "About");
		this.lang().add(this.context().entryDescription(), "About the Book of Geodes");
		
		this.context().page("about");
		var about = BookTextPageModel
				.builder()
				.withTitle(this.context().pageTitle())
				.withText(this.context().pageText())
				.build();
		this.lang().add(this.context().pageTitle(), "About");
		this.lang
		.add(this.context().pageText(),
				"""
				This Book exists to inform about the secrets of Resource Geodes, and how to utillize them properly.
				The author recommends careful examination of the Crystals, to avoid injury with any sharp edges.
				""");
		
		this.context().page("help");
		var help = BookTextPageModel.builder()
				.withTitle(this.context().pageTitle())
				.withText(this.context().pageText())
				.build();
		this.lang().add(this.context().pageTitle(), "Getting Help");
		this.lang
		.add(this.context().pageText(),
				"""
				If you run into any trouble while playing with Resonant Geodes, please join the Discord server and ask for help.
				//
				//
				[Join us on Discord](https://discord.gg/Dv7cmCGSyT)
				""");
		
		return BookEntryModel
				.create(this.modLoc(this.context().categoryId() + "/" + this.context().entryId()),
						this.context().entryName())
				.withDescription(this.context().entryDescription())
				.withIcon(Registry.BOOK.get())
				.withLocation(entryMap.get(c))
				.withEntryBackground(0, 1)
				.withPages(about, help);
	}


	private BookEntryModel makeFindGeodesEntry(CategoryEntryMap entryMap, char c) {
		this.context().entry("find_geodes");
		this.lang().add(this.context().entryName(), "Finding Geodes");
		this.lang().add(this.context().entryDescription(), "Geode Locations");

		this.context().page("find_geodes");
		var geodesPage = BookTextPageModel.builder()
				.withTitle(this.context().pageTitle())
				.withText(this.context().pageText())
				.build();
		this.lang().add(this.context().pageTitle(), "Finding Geodes");
		this.lang
				.add(this.context().pageText(),
				"""
				Geodes, being hidden underground, are not that easy to find.
				Once you have found one, though, you can use the Crystals found there to make a tool that can detect their Aura distortions.
                """);

		return BookEntryModel
				.create(this.modLoc(this.context().categoryId() + "/" + this.context().entryId()),
						this.context().entryName())
				.withDescription(this.context().entryDescription())
				.withLocation(entryMap.get(c))
				.withEntryBackground(0, 1)
				.withIcon(Registry.RESONATOR_ITEM.get())
				.withPages(geodesPage);
	}

	private BookEntryModel makeAboutGeodesEntry(CategoryEntryMap entryMap, char c) {
		this.context().entry("about_geodes");
		this.lang().add(this.context().entryName(), "About Geodes");
		this.lang().add(this.context().entryDescription(), "Underground Treasure");
		
		this.context().page("geodes");
		var geodesPage = BookTextPageModel.builder()
				.withTitle(this.context().pageTitle())
				.withText(this.context().pageText())
				.build();
		this.lang().add(this.context().pageTitle(), "About Geodes");
		this.lang
				.add(this.context().pageText(),
				"""
				Hidden deep in the ground exist mineralic formations, commonly referred to as "Geodes".
				For a long time, not much was known about them, and people simply saw every Geode's minerals as "Amethyst".
                """);

		return BookEntryModel
				.create(this.modLoc(this.context().categoryId() + "/" + this.context().entryId()),
						this.context().entryName())
				.withDescription(this.context().entryDescription())
				.withLocation(entryMap.get(c))
				.withEntryBackground(0, 1)
				.withIcon(Registry.GEODE_CORE.get())
				.withPages(geodesPage);
	}
	
	private BookEntryModel makeCrystalsEntry(CategoryEntryMap entryMap, char c) {
		this.context().entry("crystals");
		this.lang().add(this.context().entryName(), "Geode Crystals");
		this.lang().add(this.context().entryDescription(), "Precious Minerals");

		this.context().page("crystals");
		var geodesPage = BookTextPageModel
				.builder()
				.withTitle(this.context().pageTitle())
				.withText(this.context().pageText())
				.build();
		this.lang().add(this.context().pageTitle(), "Geode Crystals");
		this.lang.add(this.context().pageText(), """
				Found as blocks within Geodes, and growing from Geode Cores, one can collect precious Crystals.
				Although not much is known about them, you are quite sure they will be very useful and valuable.
				""");

		return BookEntryModel
				.create(this.modLoc(this.context().categoryId() + "/" + this.context().entryId()),
						this.context().entryName())
				.withDescription(this.context().entryDescription())
				.withLocation(entryMap.get(c))
				.withEntryBackground(0, 1)
				.withIcon(Registry.CRYSTAL_FRAGMENT.get())
				.withPages(geodesPage);
	}

	private BookEntryModel makeEnergizerEntry(CategoryEntryMap entryMap, char c) {
		this.context().entry("energizer");
		this.lang().add(this.context().entryName(), "Crystallic Diffusion Energizer");
		this.lang().add(this.context().entryDescription(), "The Machine to power the World");

		this.context().page("energizer_info");
		var info = BookTextPageModel
				.builder()
				.withTitle(this.context().pageTitle())
				.withText(this.context().pageText())
				.build();
		this.lang().add(this.context().pageTitle(), "Crystallic Diffusion Energizer");
		this.lang.add(this.context().pageText(),
						"""
								This Structure is quite an interesting one.
								There is a form of magical "Aura" of some kind.
								This magical setup is able to burn any kind of Geode-related Crystals (including mundane Amethyst),
								and release their crystalline essence into this Aura.
								You have a feeling that this charge might be useful to you.
								""");

		return BookEntryModel
				.create(this.modLoc(this.context().categoryId() + "/" + this.context().entryId()),this.context().entryName())
				.withDescription(this.context().entryDescription())
				.withIcon(Registry.CRYSTAL_CLUSTER.get())
				.withLocation(entryMap.get(c))
				.withEntryBackground(0, 1)
				.withPages(info);
	}
	
	private BookEntryModel makeDrillEntry(CategoryEntryMap entryMap, char c) {
		this.context().entry("drill");
		this.lang().add(this.context().entryName(), "Crystal Drill");
		this.lang().add(this.context().entryDescription(), "The Machine to harness the World");
		
		this.context().page("drill_info");
		var info = BookTextPageModel
				.builder()
				.withTitle(this.context().pageTitle())
				.withText(this.context().pageText())
				.build();
		this.lang().add(this.context().pageTitle(), "Crystal Drill");
		this.lang.add(this.context().pageText(),
						"""
								This Structure is exactly what you were looking for.
								It is powered by the Crystal Aura, and uses that energy to carefully extract a steady supply of Crystals from a Geode Core.
								This is much better than just hoping that the Geode Core grows a Crystal Bud on one of its sides, and waiting for it to grow to full size.
								""");

		this.context().page("drill_info_2");
		var info2 = BookTextPageModel.builder().withText(this.context().pageText()).build();
		this.lang().add(this.context().pageText(),
						"""
								And it also means that the Geode Core is no longer bound by this realm's presence requirements.
								(The activeness requirement still remains, however, so you will have to figure out something about that.)
								""");
		
		this.context().page("drill_structure");
		var structure = BookMultiblockPageModel
				.builder()
				.withVisualizeButton(true)
				.withMultiblockId(this.modLoc("geode_drill"))
				.withMultiblockName("multiblocks.resonantgeodes.geode_drill_multiblock")
				.withText(this.context().pageText())
				.build();
		this.lang().add(this.context().pageText(),
				"To line up the preview with the Core, place a block underneath it and place the preview against it.");
		this.lang().add("multiblocks.resonantgeodes.geode_drill_multiblock", "Geode Drill");
		return BookEntryModel
				.create(this.modLoc(this.context().categoryId() + "/" + this.context().entryId()),this.context().entryName())
				.withDescription(this.context().entryDescription())
				.withIcon(Registry.DRILL_MACHINE_BLOCK.get())
				.withLocation(entryMap.get(c))
				.withEntryBackground(0, 1)
				.withPages(info, info2, structure);
	}
}


