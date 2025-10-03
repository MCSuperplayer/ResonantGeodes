package io.github.mcsuperplayer.registry.menu;

import io.github.mcsuperplayer.ResonantGeodes;
import net.minecraft.ChatFormatting;
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
		graphics.blit(GUI_BG, i, j, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		
	}

	@Override
	protected void renderLabels(GuiGraphics graphics, int mouse_x, int mouse_y) {
		String[] progressCount = menu.getInfoMsg().getString().split("/");
		int cur = Integer.parseInt(progressCount[0]);
		int max = Integer.parseInt(progressCount[1]);
		int progress = (max == 0) ? 0 : cur * 100 / max;
		graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
		graphics.drawCenteredString(this.font, menu.getStatusMsg(), this.imageWidth / 2, (this.imageHeight / 2) - 5, ChatFormatting.WHITE.getColor());
		graphics.drawCenteredString(this.font, menu.getInfoMsg(), this.imageWidth / 2, (this.imageHeight / 2) + 5, ChatFormatting.WHITE.getColor());
		graphics.fill((this.imageWidth/2)-51, (this.imageHeight/2)+20, (this.imageWidth/2)+51, (this.imageHeight/2)+24, 0xFF555555);
		graphics.fill((this.imageWidth/2)-50, (this.imageHeight/2)+21, (this.imageWidth/2)-50+progress, (this.imageHeight/2)+23, 0xFF888888);
	}

	@Override
	public void render(GuiGraphics graphics, int mouse_x, int mouse_y, float floatnum) {
		super.render(graphics, mouse_x, mouse_y, floatnum);
		renderBackground(graphics);
	}
}
