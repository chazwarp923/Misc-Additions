/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.ItemInfo;

public class ConfigHandler {

	
	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		BlockInfo.DECORATIVE_ID = config.getBlock(BlockInfo.DECORATIVE_KEY, BlockInfo.DECORATIVE_DEFAULT).getInt();
		BlockInfo.MAGLEV_POWER_ID = config.getBlock(BlockInfo.MAGLEV_POWER_KEY, BlockInfo.MAGLEV_POWER_DEFAULT).getInt();
		BlockInfo.MOB_TELEPORTER_ID = config.getBlock(BlockInfo.MOB_TELEPORTER_KEY, BlockInfo.MOB_TELEPORTER_DEFAULT).getInt();
		BlockInfo.WALL_ID = config.getBlock(BlockInfo.WALL_KEY, BlockInfo.WALL_DEFAULT).getInt();
		BlockInfo.MOSSY_LOG_ID = config.getBlock(BlockInfo.MOSSY_LOG_KEY, BlockInfo.MOSSY_LOG_DEFAULT).getInt();
		
		ItemInfo.LUMBER_AXE_ID = config.getItem(ItemInfo.LUMBER_AXE_KEY, ItemInfo.LUMBER_AXE_DEFAULT).getInt() - 256;
		
		
		config.save();
		
	}
}
