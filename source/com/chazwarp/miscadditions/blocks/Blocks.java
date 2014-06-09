/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.chazwarp.miscadditions.items.ItemDecorative;
import com.chazwarp.miscadditions.items.ItemMaglevPower;
import com.chazwarp.miscadditions.items.ItemMobTeleporter;
import com.chazwarp.miscadditions.items.ItemMossyLog;
import com.chazwarp.miscadditions.items.ItemWall;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.tileentity.TileEntityMaglevPower;
import com.chazwarp.miscadditions.tileentity.TileEntityMobTeleporter;

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
		
	public static Block mobTele;
		
		public static void initMobTele() {
			mobTele = new BlockMobTeleporter(BlockInfo.MOB_TELEPORTER_ID);
			GameRegistry.registerBlock(mobTele, ItemMobTeleporter.class, BlockInfo.MOB_TELEPORTER_KEY);
		}
		
		public static void addMobTeleName() {
			LanguageRegistry.addName(mobTele, BlockInfo.MOB_TELEPORTER_UNLOCALIZED_NAME);
		}
		
	public static Block wallBase;
	
		public static void initWallBase() {
			wallBase = new BlockWallBase(BlockInfo.WALL_ID, Block.wood);
			GameRegistry.registerBlock(wallBase, ItemWall.class, BlockInfo.WALL_KEY);
		}
		
		public static void addWallBaseName() {
			LanguageRegistry.addName(wallBase, BlockInfo.WALL_UNLOCALIZED_NAME);
		}
		
	public static Block mossyLog;
	
		public static void initMossyLog() {
			mossyLog = new BlockMossyLog(BlockInfo.MOSSY_LOG_ID, Material.wood);
			GameRegistry.registerBlock(mossyLog, ItemMossyLog.class, BlockInfo.MOSSY_LOG_UNLOCALIZED_NAME);
		}
		
		public static void addMossyLogName() {
			LanguageRegistry.addName(mossyLog, BlockInfo.MOSSY_LOG_UNLOCALIZED_NAME);
		}
	
	public static void initBlocks() {
		initDecorative();
		initMaglevPower();
		initMobTele();
		initWallBase();
		initMossyLog();
	}
	
	public static void addNames() {
		addDecorativeName();
		addMaglevPowerName();
		addMobTeleName();
		addWallBaseName();
		addMossyLogName();
	}
		
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityMaglevPower.class, BlockInfo.MAGLEV_POWER_KEY);
		GameRegistry.registerTileEntity(TileEntityMobTeleporter.class, BlockInfo.MOB_TELEPORTER_KEY);
	}
}
