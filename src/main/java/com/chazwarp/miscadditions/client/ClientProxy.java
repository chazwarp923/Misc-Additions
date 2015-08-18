/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions.client;

import com.chazwarp.miscadditions.CommonProxy;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityHoppingBuffer;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityPresent;
import com.chazwarp.miscadditions.render.HoppingBufferRenderer;
import com.chazwarp.miscadditions.render.PresentRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPresent.class, new PresentRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHoppingBuffer.class, new HoppingBufferRenderer());
	}
}
