package com.chazwarp.miscadditions.items;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;

import com.chazwarp.miscadditions.lib.ItemInfo;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {
	
	public static Item axeLumberIron;
	
		public static void initIronAxe() {
			axeLumberIron = new ItemLumberAxe(ItemInfo.LUMBER_AXE_ID, EnumToolMaterial.IRON);
			GameRegistry.registerItem(axeLumberIron, "Iron " + ItemInfo.LUMBER_AXE_KEY);
		}

		public static void addIronAxeName() {
			LanguageRegistry.addName(axeLumberIron, ItemInfo.LUMBER_AXE_UNLOCALIZED_NAME);	
		}
		
	public static Item axeLumberDiamond;
		
		public static void initDiamondAxe() {
			axeLumberDiamond = new ItemLumberAxe(ItemInfo.LUMBER_AXE_ID + 1, EnumToolMaterial.EMERALD);
			GameRegistry.registerItem(axeLumberDiamond, "Diamond " + ItemInfo.LUMBER_AXE_KEY);
		}

		public static void addAxeDiamondName() {
			LanguageRegistry.addName(axeLumberDiamond, ItemInfo.LUMBER_AXE_UNLOCALIZED_NAME);	
		}		
	
	public static void initItems() {
		initIronAxe();
		initDiamondAxe();
	}
	
	public static void addNames() {
		addIronAxeName();
		addAxeDiamondName();
	}
}
