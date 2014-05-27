/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;

import com.chazwarp.miscadditions.items.ItemDecorative;
import com.chazwarp.miscadditions.items.ItemMaglevPower;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.tileentity.TileEntityMaglevPower;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static Block decorative;
	
		public static void initDecorative() {
			decorative = new BlockDecorative(BlockInfo.DECORATIVE_ID);
			GameRegistry.registerBlock(decorative, ItemDecorative.class, BlockInfo.DECORATIVE_KEY);
		}
		
		public static void addDecorativeName() {
			LanguageRegistry.addName(decorative, BlockInfo.DECORATIVE_UNLOCALIZED_NAME);
		}
	
	public static Block maglevPower;
	
		public static void initMaglevPower() {
			maglevPower = new BlockMaglevPower(BlockInfo.MAGLEV_POWER_ID);
			GameRegistry.registerBlock(maglevPower, ItemMaglevPower.class, BlockInfo.MAGLEV_POWER_KEY);
		}
		
		public static void addMaglevPowerName() {
			LanguageRegistry.addName(maglevPower, BlockInfo.MAGLEV_POWER_UNLOCALIZED_NAME);
		}
	
	public static void initBlocks() {
		initDecorative();
		initMaglevPower();
	}
	
	public static void addNames() {
		addDecorativeName();
		addMaglevPowerName();
	}
		
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityMaglevPower.class, BlockInfo.MAGLEV_POWER_KEY);
	}
}
