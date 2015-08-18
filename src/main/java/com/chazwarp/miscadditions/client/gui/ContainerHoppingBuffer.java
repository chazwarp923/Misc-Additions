/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions.client.gui;

import com.chazwarp.miscadditions.blocks.tileentity.TileEntityHoppingBuffer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHoppingBuffer extends Container {

	private TileEntityHoppingBuffer hoppingBuffer;

	public ContainerHoppingBuffer(InventoryPlayer invPlayer, TileEntityHoppingBuffer hoppingBuffer) {
		this.hoppingBuffer = hoppingBuffer;

		// Adds The Players Hotbar
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 117));
		}

		// Adds The Players Main Inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9,
						8 + 18 * x, 59 + y * 18));
			}
		}

		// Adds The Hopping Buffer's Inventory
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(hoppingBuffer, x, 8 + 18 * x, 13));
		}

		for (int x = 9; x < hoppingBuffer.getSizeInventory(); x++) {
			addSlotToContainer(new Slot(hoppingBuffer, x, 8 + 18 * (x - 9), 31));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return hoppingBuffer.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}

	public TileEntityHoppingBuffer getChest() {
		return hoppingBuffer;
	}
}