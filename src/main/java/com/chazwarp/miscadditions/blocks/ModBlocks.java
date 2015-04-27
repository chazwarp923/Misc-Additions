/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.chazwarp.miscadditions.items.itemblocks.ItemBlockDecorative;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockMaglevPower;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockMobTeleporter;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.tileentity.TileEntityMaglevPower;
import com.chazwarp.miscadditions.tileentity.TileEntityMobTeleporter;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block decorative = new BlockMA(Material.wood, 2F, Block.soundTypeWood, BlockInfo.DECORATIVE_UNLOCALIZED_NAME, BlockMA.AXE, BlockMA.STONE);
	
	public static Block maglevPower = new BlockMaglevPower();
	public static Block mobTele = new BlockMobTeleporter();
	
	public static void registerBlocks() {
		GameRegistry.registerBlock(decorative, ItemBlockDecorative.class, BlockInfo.DECORATIVE_KEY);
		GameRegistry.registerBlock(maglevPower, ItemBlockMaglevPower.class, BlockInfo.MAGLEV_POWER_KEY);
		GameRegistry.registerBlock(mobTele, ItemBlockMobTeleporter.class, BlockInfo.MOB_TELEPORTER_KEY);
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityMaglevPower.class, BlockInfo.MAGLEV_POWER_KEY);
		GameRegistry.registerTileEntity(TileEntityMobTeleporter.class, BlockInfo.MOB_TELEPORTER_KEY);
	}
}
