/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.tileentity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;

public class TileEntityMobTeleporter extends TileEntity implements IEnergyReceiver {
	
	protected EnergyStorage storage = new EnergyStorage(400000);

	int teleDist;

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
	
	public void teleportEntity(double x, double y, double z, EntityLivingBase entity) {
		
		teleDist = (int)entity.getDistance(x, y, z);
		
        if(storage.extractEnergy(teleDist * 10, true) == teleDist * 10) {
        	entity.setPositionAndUpdate(x+0.5, y + 10, z+0.5);
            storage.extractEnergy(teleDist * 10, false);
        }
	}
}
