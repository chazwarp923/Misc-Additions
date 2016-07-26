/**
@author Chaz Kerby
 */
package tech.chazwarp923.miscadditions.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import tech.chazwarp923.miscadditions.crafting.Shaped;
import tech.chazwarp923.miscadditions.lib.Reference;
import tech.chazwarp923.miscadditions.server.HomeCommand;
import tech.chazwarp923.miscadditions.server.SetHomeCommand;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent preInitEvent) {
		//Hard codes the mod meta data
		preInitEvent.getModMetadata().credits = "Reika";
		preInitEvent.getModMetadata().description = "A Collection of Random Things That Don't Need Their Own Mods";
		preInitEvent.getModMetadata().logoFile = "assets/" + Reference.TEXTURE_LOC + "/textures/logo.png";
		preInitEvent.getModMetadata().modId = Reference.MOD_ID;
		preInitEvent.getModMetadata().name = Reference.MOD_NAME;
		preInitEvent.getModMetadata().version = Reference.VERSION;
	}
	
	public void init(FMLInitializationEvent initEvent) {
		Shaped.addCrafting();
	}
	
	public void postInit(FMLPostInitializationEvent postInitEvent) {
		
	}
	
	public void serverLoad(FMLServerStartingEvent serverStartEvent) {
		//Registers commands
		serverStartEvent.registerServerCommand(new SetHomeCommand());
		serverStartEvent.registerServerCommand(new HomeCommand());
	}
}
