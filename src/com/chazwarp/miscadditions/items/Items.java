package com.chazwarp.miscadditions.items;

import net.minecraft.item.Item;

import com.chazwarp.miscadditions.lib.ItemInfo;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {
	
	public static Item axeLumber;
	
		public static void initAxe() {
			axeLumber = new ItemLumberAxe(ItemInfo.LUMBER_AXE_ID);
			GameRegistry.registerItem(axeLumber, ItemInfo.LUMBER_AXE_KEY);
		}

		public static void addAxeName() {
			LanguageRegistry.addName(axeLumber, ItemInfo.LUMBER_AXE_NAME);	
		}	
}
