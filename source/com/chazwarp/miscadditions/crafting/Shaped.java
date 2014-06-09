/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.chazwarp.miscadditions.blocks.Blocks;
import com.chazwarp.miscadditions.compat.CheckInstalled;
import com.chazwarp.miscadditions.items.Items;

import cpw.mods.fml.common.registry.GameRegistry;

public class Shaped {

	public static void addCrafting() {
		
		ItemStack lumberAxe = new ItemStack(Items.axeLumber);
		
		GameRegistry.addShapedRecipe(lumberAxe, new Object[] {"BI", "IS", " S", 'B', Block.blockIron, 'I', Item.ingotIron, 'S', Item.stick});
		
		if(CheckInstalled.ThermalExpansion() == true) {
			
			ItemStack mobTele = new ItemStack(Blocks.mobTele);
			ItemStack ingotInvar = GameRegistry.findItemStack("ThermalExpansion", "ingotInvar", 1);
			ItemStack tesseractFrameFull = GameRegistry.findItemStack("ThermalExpansion", "tesseractFrameFull", 1);
			ItemStack leadstoneCell = GameRegistry.findItemStack("ThermalExpansion", "cellBasicFrame", 1);
			ItemStack receptionCoil = GameRegistry.findItemStack("ThermalExpansion", "powerCoilGold", 1);
			
			if(leadstoneCell != null) {
				GameRegistry.addShapedRecipe(mobTele, new Object[] {"ITI", "DLD", "ICI", 'I', ingotInvar, 'T', tesseractFrameFull, 'D', Item.diamond, 'L',leadstoneCell, 'C', receptionCoil});
			}
		}
		
		ItemStack logWall = new ItemStack(Blocks.wallBase, 6, 0);
		
		GameRegistry.addShapedRecipe(logWall, new Object[] {"LLL", "LLL", 'L', Block.wood});
		
	}
}
