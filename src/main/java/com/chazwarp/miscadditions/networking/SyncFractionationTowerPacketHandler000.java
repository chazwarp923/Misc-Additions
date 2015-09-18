/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.networking;

import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTower;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTowerFluidIO;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTowerPowerInput;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.nbt.NBTTagCompound;

public class SyncFractionationTowerPacketHandler000 implements IMessageHandler<SyncFractionationTowerPacket000, IMessage> {
	
	NBTTagCompound compound;
	int x;
	int y;
	int z;
	int tileType;
	
	@Override
	public IMessage onMessage(SyncFractionationTowerPacket000 message, MessageContext ctx) {
		this.compound = message.compound;
		this.x = message.x;
		this.y = message.y;
		this.z = message.z;
		this.tileType = message.tileType;
		
		switch(tileType) {
			case 0:
				TileEntityFractionationTower te = (TileEntityFractionationTower)ctx.getServerHandler().playerEntity.worldObj.getTileEntity(x, y, z);
				te.readFromNBT(compound);
				break;
			case 1:
				TileEntityFractionationTowerFluidIO te1 = (TileEntityFractionationTowerFluidIO)ctx.getServerHandler().playerEntity.worldObj.getTileEntity(x, y, z);
				te1.readFromNBT(compound);
				break;
			case 2:
				TileEntityFractionationTowerPowerInput te2 = (TileEntityFractionationTowerPowerInput)ctx.getServerHandler().playerEntity.worldObj.getTileEntity(x, y, z);
				te2.readFromNBT(compound);
				break;
			default:
				System.out.println("Something done fucked up");
				break;
		}		
		return null;
	}
}
