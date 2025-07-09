package com.mcsuperplayer.resonantgeodes.registry.menu;

import com.mcsuperplayer.resonantgeodes.ResonantGeodes;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DrillControllerScreen extends AbstractContainerScreen<DrillControllerMenu> {
	static final ResourceLocation GUI_BG = ResourceLocation.fromNamespaceAndPath(ResonantGeodes.MODID, "textures/gui/drill_controller_screen.png");

	public DrillControllerScreen(DrillControllerMenu menu, Inventory playerInv, Component title) {
		super(menu, playerInv, title);
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float floatnum, int x, int y) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		graphics.blit(GUI_BG, i, j, 0, 0, this.imageWidth, this.imageHeight);
		
	}

	@Override
	protected void renderLabels(GuiGraphics graphics, int p_282681_, int p_283686_) {
		graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
		menu.isStructureValid();
	}

	@Override
	public void render(GuiGraphics graphics, int mouse_x, int mouse_y, float floatnum) {
		super.render(graphics, mouse_x, mouse_y, floatnum);
		renderBackground(graphics);
	}
}
