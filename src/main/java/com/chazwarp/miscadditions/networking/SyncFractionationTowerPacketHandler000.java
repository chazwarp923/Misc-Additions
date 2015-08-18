/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.networking;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class SyncFractionationTowerPacketHandler000 implements IMessageHandler<SyncFractionationTowerPacket000, IMessage> {
	
	NBTTagCompound compound;
	Integer x;
	Integer y;
	Integer z;
	
	@Override
	public IMessage onMessage(SyncFractionationTowerPacket000 message, MessageContext ctx) {
		this.compound = message.compound;
		this.x = message.x;
		this.y = message.y;
		this.z = message.z;
		
		TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(x, y, z);
		te.readFromNBT(compound);
		
		return null;
	}
}
