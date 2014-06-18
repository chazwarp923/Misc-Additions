/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.TileEnergyHandler;

public class TileEntityMobTeleporter extends TileEnergyHandler {
	
	protected EnergyStorage storage = new EnergyStorage(400000);

	int teleX;
	int teleY;
	int teleZ;
	int teleDist;
	
	public TileEntityMobTeleporter() {
		
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}

	/* IEnergyHandler */
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public boolean canInterface(ForgeDirection from) {
		return true;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getMaxEnergyStored();
	}
	
	public void teleportEntity(double x, double y, double z, Entity entity) {
		teleDist = (int) entity.getDistanceSq(x, y, z);
		entity.setPosition(teleX, teleY+10, teleZ);
		this.extractEnergy(ForgeDirection.UP, teleDist * 10, false);
	}
}
