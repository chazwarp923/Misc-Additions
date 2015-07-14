/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.chazwarp.miscadditions.blocks.ModBlocks;

public class MiscTab extends CreativeTabs {
	public static MiscTab tab = new MiscTab();

	public MiscTab() {
		super("miscTab");
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ModBlocks.mobTele);
	}

	@Override
	public Item getTabIconItem() {
		return Items.apple;
	}
}
