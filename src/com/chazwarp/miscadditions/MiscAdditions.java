/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions;

import com.chazwarp.miscadditions.config.ConfigHandler;
import com.chazwarp.miscadditions.crafting.Shaped;
import com.chazwarp.miscadditions.items.Items;
import com.chazwarp.miscadditions.lib.Reference;
import com.chazwarp.miscadditions.network.PacketHandler;
import com.chazwarp.miscadditions.server.HomeCommand;
import com.chazwarp.miscadditions.server.SetHomeCommand;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = {Reference.CHANNEL}  ,clientSideRequired = false, serverSideRequired = true, packetHandler = PacketHandler.class)
public class MiscAdditions {

		// The instance of your mod that Forge uses.
    	@Instance(Reference.MOD_ID)
    	public static MiscAdditions instance;
    	
    	// Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="com.chazwarp.miscadditions.client.ClientProxy", serverSide="com.chazwarp.miscadditions.CommonProxy")
        public static CommonProxy proxy;
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	
        	//Makes a Config
        		ConfigHandler.init(event.getSuggestedConfigurationFile());
        	
        	//Adds Crafting
        		Shaped.addCrafting();
        		
        	//Registers Things With The Proxy
        		proxy.registerRenderers();
        	
        	//Hard Codes The Mod Data
	        	event.getModMetadata().credits = "";
	        	event.getModMetadata().description = "A Collection of Random Things That Don't Need Their Own Mods";
	        	event.getModMetadata().logoFile = "assets/" + Reference.MOD_ID +"/textures/logo.png";
	        	event.getModMetadata().modId = Reference.MOD_ID;
	        	event.getModMetadata().name = Reference.MOD_NAME;
	        	event.getModMetadata().version = Reference.VERSION;
        }
        
        @EventHandler
        public void Init(FMLInitializationEvent event) {
        	Items.initAxe();
        	Items.addAxeName();
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
