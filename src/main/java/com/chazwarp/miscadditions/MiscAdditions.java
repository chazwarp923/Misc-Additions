/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions;

import java.io.File;

import com.chazwarp.miscadditions.blocks.ModBlocks;
import com.chazwarp.miscadditions.crafting.Shaped;
import com.chazwarp.miscadditions.fluid.Fluids;
import com.chazwarp.miscadditions.items.ModItems;
import com.chazwarp.miscadditions.lib.Reference;
import com.chazwarp.miscadditions.networking.SyncFractionationTowerPacket000;
import com.chazwarp.miscadditions.networking.SyncFractionationTowerPacketHandler000;
import com.chazwarp.miscadditions.server.HomeCommand;
import com.chazwarp.miscadditions.server.SetHomeCommand;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:CoFHCore")
public class MiscAdditions {

	public static File configFile;

	//The instance of your mod that Forge uses.
	@Mod.Instance(Reference.MOD_ID)
	public static MiscAdditions instance;

	//Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "com.chazwarp.miscadditions.client.ClientProxy", serverSide = "com.chazwarp.miscadditions.CommonProxy")
	public static CommonProxy proxy;
	
	public static SimpleNetworkWrapper network;

	@EventHandler
	public void preInit(FMLPreInitializationEvent preInitEvent) {

		//Does packet related things
		network = NetworkRegistry.INSTANCE.newSimpleChannel("MiscAdditions");
		
		network.registerMessage(SyncFractionationTowerPacketHandler000.class, SyncFractionationTowerPacket000.class, 1, Side.SERVER);
		
		//Adds blocks and items
		Fluids.registerFluids();
		ModBlocks.registerBlocks();
		ModItems.registerItems();

		//Hard codes the mod meta data
		preInitEvent.getModMetadata().credits = "Reika";
		preInitEvent.getModMetadata().description = "A Collection of Random Things That Don't Need Their Own Mods";
		preInitEvent.getModMetadata().logoFile = "assets/" + Reference.TEXTURE_LOC + "/textures/logo.png";
		preInitEvent.getModMetadata().modId = Reference.MOD_ID;
		preInitEvent.getModMetadata().name = Reference.MOD_NAME;
		preInitEvent.getModMetadata().version = Reference.VERSION;
	}

	@EventHandler
	public void Init(FMLInitializationEvent initEvent) {

		//Adds crafting
		Shaped.addCrafting();
		
		//Registers things with the proxy
		proxy.registerRenderers();
		proxy.registerNetworking();

		//Registers TileEntities
		ModBlocks.registerTileEntities();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postInitEvent) {

	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent serverStartEvent) {
		//Registers commands
		serverStartEvent.registerServerCommand(new SetHomeCommand());
		serverStartEvent.registerServerCommand(new HomeCommand());
	}
}
