/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.chazwarp.miscadditions.blocks.ModBlocks;
import com.chazwarp.miscadditions.compat.CheckInstalled;
import com.chazwarp.miscadditions.items.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class Shaped {

	public static void addCrafting() {
		
		ItemStack lumberAxeIron = new ItemStack(ModItems.axeLumberIron);
		ItemStack lumberAxeDiamond = new ItemStack(ModItems.axeLumberDiamond);
		
		GameRegistry.addShapedRecipe(lumberAxeIron, new Object[] {"BI", "IS", " S", 'B', Blocks.iron_block, 'I', Items.iron_ingot, 'S', Items.stick});
		GameRegistry.addShapedRecipe(lumberAxeDiamond, new Object[] {"BD", "DS", " S", 'B', Blocks.diamond_block, 'D', Items.diamond, 'S', Items.stick});
		
		//Thermal Expansion Dependent Recipes
		if(CheckInstalled.ThermalExpansion() == true) {
			
			ItemStack mobTele = new ItemStack(ModBlocks.mobTele);
			ItemStack ingotInvar = GameRegistry.findItemStack("ThermalExpansion", "ingotInvar", 1);
			ItemStack tesseractFrameFull = GameRegistry.findItemStack("ThermalExpansion", "tesseractFrameFull", 1);
			ItemStack leadstoneCell = GameRegistry.findItemStack("ThermalExpansion", "cellBasicFrame", 1);
			ItemStack receptionCoil = GameRegistry.findItemStack("ThermalExpansion", "powerCoilGold", 1);
			
			if(leadstoneCell != null) {
				GameRegistry.addShapedRecipe(mobTele, new Object[] {"ITI", "DLD", "ICI", 'I', ingotInvar, 'T', tesseractFrameFull, 'D', Items.diamond, 'L',leadstoneCell, 'C', receptionCoil});
			}
		}
	}
}
