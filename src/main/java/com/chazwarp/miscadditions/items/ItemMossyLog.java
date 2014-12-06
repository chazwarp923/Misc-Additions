/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.items;

import net.minecraft.item.ItemBlock;

public class ItemMossyLog extends ItemBlock {
	
	public ItemMossyLog(int id) {
		super(id);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int dmg) {
		return dmg;
	}

}