/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
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
	public void setHasMasterBlock(int x, int y, int z) {
		super.setHasMasterBlock(x, y, z);
		
		addReferenceToMaster();
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
