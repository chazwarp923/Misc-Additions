/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.items;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.ItemInfo;

import net.minecraft.item.Item;

public class ItemDebug extends Item {
	
	public ItemDebug() {
		setMaxStackSize(1);
		setCreativeTab(MiscTab.tab);
		setUnlocalizedName(ItemInfo.DEBUG_ITEM_UNLOCALIZED_NAME);
	}
}
