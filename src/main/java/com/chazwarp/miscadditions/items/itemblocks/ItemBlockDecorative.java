/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.items.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockDecorative extends ItemBlock {
	
	public ItemBlockDecorative(Block block) {
		super(block);
		setHasSubtypes(true);
	}
}