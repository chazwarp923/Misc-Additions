/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileEntityFractionationTowerPowerInput extends TileEntityFractionationTower implements IEnergyHandler {

	protected EnergyStorage storage = new EnergyStorage(100000);
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		storage.writeToNBT(tag);
		writeMultiBlockDataToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		storage = storage.readFromNBT(tag);
		readMultiBlockDataFromNBT(tag);
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		writeToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
	}
	
	@Override
	public String[] getDebugStatus() {
		String[] debugInfo = new String[11];
		debugInfo[0] = Boolean.toString(this.isMasterBlock);
		debugInfo[1] = Boolean.toString(this.hasMasterBlock);
		debugInfo[2] = Integer.toString(this.masterX);
		debugInfo[3] = Integer.toString(this.masterY);
		debugInfo[4] = Integer.toString(this.masterZ);
		debugInfo[5] = gasolineTankX + "_" + gasolineTankY + "_" + gasolineTankZ;
		debugInfo[6] = dieselTankX + "_" + dieselTankY + "_" + dieselTankZ;
		debugInfo[7] = liquifiedPetroleumGasTankX + "_" + liquifiedPetroleumGasTankY + "_" + liquifiedPetroleumGasTankZ;
		debugInfo[8] = keroseneTankX + "_" + keroseneTankY + "_" + keroseneTankZ;
		debugInfo[9] = powerInputX + "_" + powerInputY + "_" + powerInputZ;
		debugInfo[10] = Integer.toString(storage.getEnergyStored());
		return debugInfo;
	}
	
	@Override
	public void setHasMasterBlock(int x, int y, int z) {
		super.setHasMasterBlock(x, y, z);
		
		if(!worldObj.isRemote) {
			addReferenceToMaster();
		}
	}
	
	public void addReferenceToMaster() {
		TileEntityFractionationTower masterTile = (TileEntityFractionationTower)worldObj.getTileEntity(masterX, masterY, masterZ);
		masterTile.addPowerLoc(this.xCoord, this.yCoord, this.zCoord);
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
