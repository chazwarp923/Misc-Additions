/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.chazwarp.miscadditions.items.Items;

import cpw.mods.fml.common.registry.GameRegistry;

public class Shaped {

	public static void addCrafting() {
		
		ItemStack lumberAxe = new ItemStack(Items.axeLumber);
		
		GameRegistry.addShapedRecipe(lumberAxe, new Object[] {"BI", "IS", " S", 'B', Block.blockIron, 'I', Item.ingotIron, 'S', Item.stick});
	}
}
