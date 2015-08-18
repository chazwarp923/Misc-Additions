/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions.items.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockHoppingBuffer extends ItemBlock {

	public ItemBlockHoppingBuffer(Block block) {
		super(block);
		setHasSubtypes(true);
	}
}