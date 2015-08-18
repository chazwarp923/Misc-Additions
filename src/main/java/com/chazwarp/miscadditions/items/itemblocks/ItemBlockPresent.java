/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions.items.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockPresent extends ItemBlock {

	public ItemBlockPresent(Block block) {
		super(block);
		setHasSubtypes(true);
	}
}