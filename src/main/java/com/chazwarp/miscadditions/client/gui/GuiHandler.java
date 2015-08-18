/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.client.gui;

import com.chazwarp.miscadditions.blocks.tileentity.TileEntityHoppingBuffer;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity te = world.getTileEntity(x, y, z);
		
		switch(ID) {
			case 0:
				if(te != null && te instanceof TileEntityHoppingBuffer) {
					return new ContainerHoppingBuffer(player.inventory, (TileEntityHoppingBuffer)te);
				}
			break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity te = world.getTileEntity(x, y, z);
		
		switch(ID) {
			case 0:
				if(te != null && te instanceof TileEntityHoppingBuffer) {
					return new GuiHoppingBuffer(player.inventory, (TileEntityHoppingBuffer)te);
				}
			break;
		}
		return null;
	}

}
