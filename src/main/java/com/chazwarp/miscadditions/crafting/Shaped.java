/**
 *@author Chaz Kerby
 */
package com.chazwarp.miscadditions.crafting;

import java.util.Calendar;

import com.chazwarp.miscadditions.blocks.ModBlocks;
import com.chazwarp.miscadditions.compat.CheckInstalled;
import com.chazwarp.miscadditions.items.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Shaped {

	public static void addCrafting() {

		int month = Calendar.getInstance().get(Calendar.MONTH);
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.axeLumberIron), new Object[] { "BI", "IS", " S", 'B', Blocks.iron_block, 'I', Items.iron_ingot, 'S', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.axeLumberDiamond), new Object[] { "BD", "DS", " S", 'B', Blocks.diamond_block, 'D', Items.diamond, 'S', Items.stick });
		
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.hoppingBuffer), new Object[] { "H", "C", 'H', Blocks.hopper, 'C', Blocks.chest});
		
		if(month == 11) {
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.present), new Object[] { "O", "C", 'O', Items.cookie, 'C', Blocks.chest});
		}

		// Thermal Expansion Dependent Recipes
		if (CheckInstalled.ThermalExpansion() == true) {

			ItemStack mobTele = new ItemStack(ModBlocks.mobTele);
			ItemStack ingotInvar = GameRegistry.findItemStack("ThermalExpansion", "ingotInvar", 1);
			ItemStack tesseractFrameFull = GameRegistry.findItemStack("ThermalExpansion", "tesseractFrameFull", 1);
			ItemStack leadstoneCell = GameRegistry.findItemStack("ThermalExpansion", "cellBasicFrame", 1);
			ItemStack receptionCoil = GameRegistry.findItemStack("ThermalExpansion", "powerCoilGold", 1);

			if (leadstoneCell != null) {
				GameRegistry.addShapedRecipe(mobTele, new Object[] { "ITI", "DLD", "ICI", 'I', ingotInvar, 'T', tesseractFrameFull, 'D', Items.diamond, 'L', leadstoneCell, 'C', receptionCoil });
			}
		}
	}
}
