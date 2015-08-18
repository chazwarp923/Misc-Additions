/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.networking;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class SyncFractionationTowerPacket000 implements IMessage {

	NBTTagCompound compound;
	Integer x;
	Integer y;
	Integer z;
	
	//Required so FML can use this packet
	public SyncFractionationTowerPacket000() {}
	
	public SyncFractionationTowerPacket000(NBTTagCompound compound, int x, int y, int z) {
		this.compound = compound;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, compound);
		ByteBufUtils.writeVarInt(buf, x, 5);
		ByteBufUtils.writeVarInt(buf, y, 5);
		ByteBufUtils.writeVarInt(buf, z, 5);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.compound = ByteBufUtils.readTag(buf);
		this.x = ByteBufUtils.readVarInt(buf, 5);
		this.y = ByteBufUtils.readVarInt(buf, 5);
		this.z = ByteBufUtils.readVarInt(buf, 5);
	}
}
