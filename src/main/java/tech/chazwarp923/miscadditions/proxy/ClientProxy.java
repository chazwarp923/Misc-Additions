/**
@author Chaz Kerby
 */
package tech.chazwarp923.miscadditions.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tech.chazwarp923.miscadditions.blocks.MABlocks;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent preInitEvent) {
		super.preInit(preInitEvent);
		MABlocks.preInitClient();
	}
}
