/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions;

import com.chazwarp.miscadditions.client.gui.GuiHandler;

import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void registerRenderers() {
		// Nothing here as the server doesn't render graphics or entities!
	}
	
	public void registerNetworking() {
		NetworkRegistry.INSTANCE.registerGuiHandler(MiscAdditions.instance, new GuiHandler());
	}
}
