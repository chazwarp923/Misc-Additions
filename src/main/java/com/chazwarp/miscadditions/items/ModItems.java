package com.chazwarp.miscadditions.items;

import com.chazwarp.miscadditions.lib.ItemInfo;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems {

	public static Item axeLumberIron = new ItemLumberAxe(Item.ToolMaterial.IRON);
	public static Item axeLumberDiamond = new ItemLumberAxe(Item.ToolMaterial.EMERALD);
	public static Item debugItem = new ItemDebug();
	
	public static void registerItems() {
		GameRegistry.registerItem(axeLumberIron, "Iron " + ItemInfo.LUMBER_AXE_KEY);
		GameRegistry.registerItem(axeLumberDiamond, "Diamond " + ItemInfo.LUMBER_AXE_KEY);
		
		GameRegistry.registerItem(debugItem, ItemInfo.DEBUG_ITEM_KEY);
	}
}
