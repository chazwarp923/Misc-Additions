/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.blocks.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityFractionationTowerPowerInput extends TileEntityFractionationTower implements IEnergyHandler {

	protected EnergyStorage storage = new EnergyStorage(100000);
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		storage.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		storage = storage.readFromNBT(tag);
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.writeMultiBlockDataToNBT(tagCompound);
		this.storage.writeToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readMultiBlockDataFromNBT(pkt.func_148857_g());
		this.storage.readFromNBT(pkt.func_148857_g());
	}
	
	@Override
	public String[] getDebugStatus() {
		String[] debugInfo = new String[5];
		debugInfo[0] = Boolean.toString(this.hasMasterBlock);
		debugInfo[1] = Integer.toString(this.masterX);
		debugInfo[2] = Integer.toString(this.masterY);
		debugInfo[3] = Integer.toString(this.masterZ);
		debugInfo[4] = Integer.toString(storage.getEnergyStored());
		return debugInfo;
	}
	
	@Override
	public void setHasMasterBlock(int x, int y, int z) {
		super.setHasMasterBlock(x, y, z);
		
		addReferenceToMaster();
	}
	
	public void addReferenceToMaster() {
		TileEntityFractionationTower masterTile = (TileEntityFractionationTower)worldObj.getTileEntity(masterX, masterY, masterZ);
		masterTile.powerInputX = xCoord;
		masterTile.powerInputY = yCoord;
		masterTile.powerInputZ = zCoord;
		masterTile.markDirty();
		sendUpdatesToServer(masterX, masterY, masterZ);
	}

	/** IEnergyReceiver */
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}
	
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getMaxEnergyStored();
	}
}
