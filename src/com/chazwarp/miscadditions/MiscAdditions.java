package com.chazwarp.miscadditions;

import com.chazwarp.miscadditions.config.ConfigHandler;
import com.chazwarp.miscadditions.lib.Reference;
import com.chazwarp.miscadditions.network.PacketHandler;
import com.chazwarp.server.HomeCommand;
import com.chazwarp.server.SetHomeCommand;

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
        	
        	ConfigHandler.init(event.getSuggestedConfigurationFile());
        	
        	//Registers Things With The Proxy
        	proxy.registerRenderers();
        }
        
        @EventHandler
        public void Init(FMLInitializationEvent event) {
        	
        }
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
        	
        }
        
        @EventHandler
        public void serverLoad(FMLServerStartingEvent event)
        {
          event.registerServerCommand(new SetHomeCommand());
          event.registerServerCommand(new HomeCommand());
        }
}
