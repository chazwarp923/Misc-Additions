package com.chazwarp.miscadditions.items;

import net.minecraft.item.Item;

import com.chazwarp.miscadditions.lib.ItemInfo;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item axeLumberIron = new ItemLumberAxe(Item.ToolMaterial.IRON);
	public static Item axeLumberDiamond = new ItemLumberAxe(Item.ToolMaterial.EMERALD);		
	
	public static void registerItems() {
		GameRegistry.registerItem(axeLumberIron, "Iron " + ItemInfo.LUMBER_AXE_KEY);
		GameRegistry.registerItem(axeLumberDiamond, "Diamond " + ItemInfo.LUMBER_AXE_KEY);
	}
}
