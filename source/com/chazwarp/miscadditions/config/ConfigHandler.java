/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

import com.chazwarp.miscadditions.lib.ItemInfo;

public class ConfigHandler {

	
	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		ItemInfo.LUMBER_AXE_ID = config.getItem(ItemInfo.LUMBER_AXE_KEY, ItemInfo.LUMBER_AXE_DEFAULT).getInt() - 256;
		
		
		config.save();
		
	}
}
