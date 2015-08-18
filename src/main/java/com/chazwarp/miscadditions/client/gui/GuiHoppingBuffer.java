/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions.client.gui;

import org.lwjgl.opengl.GL11;

import com.chazwarp.miscadditions.blocks.tileentity.TileEntityHoppingBuffer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiHoppingBuffer extends GuiContainer {

	public GuiHoppingBuffer(InventoryPlayer invPlayer, TileEntityHoppingBuffer te) {
		super(new ContainerHoppingBuffer(invPlayer, te));

		xSize = 175;
		ySize = 140;
	}

	private static final ResourceLocation texture = new ResourceLocation("miscadditions", "textures/gui/hoppingBuffer.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);

		this.mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		this.fontRendererObj.drawString("Inventory", 8, 49, 0x404040);
		this.fontRendererObj.drawString("Hopping Buffer", 8, 3, 0x404040);
	}
}