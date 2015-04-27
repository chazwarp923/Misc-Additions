/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions;

import java.io.File;

import com.chazwarp.miscadditions.blocks.ModBlocks;
import com.chazwarp.miscadditions.crafting.Shaped;
import com.chazwarp.miscadditions.items.ModItems;
import com.chazwarp.miscadditions.lib.Reference;
import com.chazwarp.miscadditions.server.HomeCommand;
import com.chazwarp.miscadditions.server.SetHomeCommand;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies="required-after:ThermalExpansion")

public class MiscAdditions {
	
		public static File configFile;

		// The instance of your mod that Forge uses.
		@Mod.Instance(Reference.MOD_ID)
    	public static MiscAdditions instance;
    	
    	// Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="com.chazwarp.miscadditions.client.ClientProxy", serverSide="com.chazwarp.miscadditions.CommonProxy")
        public static CommonProxy proxy;
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	
        	//Adds Blocks And Items
        		ModBlocks.registerBlocks();
            	ModItems.registerItems();
        		
        	//Registers Things With The Proxy
        		proxy.registerRenderers();
        	
        	//Hard Codes The Mod Data
	        	event.getModMetadata().credits = "";
	        	event.getModMetadata().description = "A Collection of Random Things That Don't Need Their Own Mods";
	        	event.getModMetadata().logoFile = "assets/" + Reference.TEXTURE_LOC +"/textures/logo.png";
	        	event.getModMetadata().modId = Reference.MOD_ID;
	        	event.getModMetadata().name = Reference.MOD_NAME;
	        	event.getModMetadata().version = Reference.VERSION;
        }
        
        @EventHandler
        public void Init(FMLInitializationEvent event) {
        	
        	//Adds Crafting
    		Shaped.addCrafting();
    		
    		//Registers TileEntities
    		ModBlocks.registerTileEntities();
        
        }
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
        	
        }
        
        @EventHandler
        public void serverLoad(FMLServerStartingEvent event)
        {
        	//Registers Comands
		    event.registerServerCommand(new SetHomeCommand());
		    event.registerServerCommand(new HomeCommand());
        }
}
