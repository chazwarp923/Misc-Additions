/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.TileEnergyHandler;

public class TileEntityMaglevPower extends TileEnergyHandler {

	protected EnergyStorage storage = new EnergyStorage(5000000);

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

	//IEnergyReceiver
		@Override
		public boolean canConnectEnergy(ForgeDirection from) {
			if(from != ForgeDirection.UP) {
				return true;
			}
			else {
				return false;
			}
		}

		@Override
		public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
			return storage.receiveEnergy(maxReceive, simulate);
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
