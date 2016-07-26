/**
@author Chaz Kerby
 */
package tech.chazwarp923.miscadditions;

import java.io.File;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import tech.chazwarp923.miscadditions.lib.Reference;
import tech.chazwarp923.miscadditions.proxy.CommonProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MiscAdditions {

	public static File configFile;

	//The instance of the mod that Forge uses.
	@Mod.Instance(Reference.MOD_ID)
	public static MiscAdditions instance;
	
	public static Logger logger;

	//Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "tech.chazwarp923.miscadditions.proxy.ClientProxy", serverSide = "tech.chazwarp923.miscadditions.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preInitEvent) {
		logger = preInitEvent.getModLog();
		proxy.preInit(preInitEvent);
	}

	@EventHandler
	public void init(FMLInitializationEvent initEvent) {
		proxy.init(initEvent);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postInitEvent) {
		proxy.postInit(postInitEvent);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent serverStartEvent) {
		proxy.serverLoad(serverStartEvent);
	}
}
