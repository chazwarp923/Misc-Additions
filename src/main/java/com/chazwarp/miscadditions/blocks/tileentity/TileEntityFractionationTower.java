/**
@author Chaz Kerby
*/

package com.chazwarp.miscadditions.blocks.tileentity;

import com.chazwarp.miscadditions.fluid.Fluids;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityFractionationTower extends TileEntity {
	
	int[] gasolineTankLoc = new int[3];
	int[] dieselTankLoc = new int[3];
	int[] liquifiedPetroleumGasTankLoc = new int[3];
	int[] keroseneTankLoc = new int[3];	
	int[] powerInputLoc = new int[3];
	
	
	public boolean isMasterBlock = false;
	public boolean hasMasterBlock = false;
	public int masterX = 0, masterY = 0, masterZ = 0;
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		writeMultiBlockDataToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		readMultiBlockDataFromNBT(tag);
	}
	
	public void writeMultiBlockDataToNBT(NBTTagCompound nbt) {
		NBTTagCompound multiblockCompound = nbt.getCompoundTag("MultiBlockData");
		multiblockCompound.setBoolean("isMasterBlock", isMasterBlock);
		multiblockCompound.setBoolean("hasMasterBlock", hasMasterBlock);
		multiblockCompound.setInteger("masterX", masterX);
		multiblockCompound.setInteger("masterY", masterY);
		multiblockCompound.setInteger("masterZ", masterZ);
		NBTTagCompound locCompound = nbt.getCompoundTag("LocationData");
		locCompound.setIntArray("GasolineTankLocation", gasolineTankLoc);
		System.out.println(gasolineTankLoc[0] + "_" + gasolineTankLoc[1] + "_" + gasolineTankLoc[2]);
		locCompound.setIntArray("DieselTankLocation", dieselTankLoc);
		locCompound.setIntArray("LiquifiedPetroleumGasLocation", liquifiedPetroleumGasTankLoc);
		locCompound.setIntArray("KeroseneTankLocation", keroseneTankLoc);
		locCompound.setIntArray("PowerInputLocation", powerInputLoc);
		multiblockCompound.setTag("LocationData", locCompound);
		nbt.setTag("MultiBlockData", multiblockCompound);
	}
	
	public void readMultiBlockDataFromNBT(NBTTagCompound nbt) {
		NBTTagCompound multiblockCompound = nbt.getCompoundTag("MultiBlockData");
		isMasterBlock = multiblockCompound.getBoolean("isMasterBlock");
		hasMasterBlock = multiblockCompound.getBoolean("hasMasterBlock");
		masterX = multiblockCompound.getInteger("masterX");
		masterY = multiblockCompound.getInteger("masterY");
		masterZ = multiblockCompound.getInteger("masterZ");
		NBTTagCompound locCompound = nbt.getCompoundTag("LocationData");
		gasolineTankLoc = locCompound.getIntArray("GasolineTankLocation");
		System.out.println(gasolineTankLoc[0] + "_" + gasolineTankLoc[1] + "_" + gasolineTankLoc[2]);
		dieselTankLoc = locCompound.getIntArray("DieselTankLocation");
		liquifiedPetroleumGasTankLoc = locCompound.getIntArray("LiquifiedPetroleumGasLocation");
		keroseneTankLoc = locCompound.getIntArray("KeroseneTankLocation");
		powerInputLoc = locCompound.getIntArray("PowerInputLocation");
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(this.isMasterBlock) {
			if(!worldObj.isRemote) {
				//Checks to make sure the tanks arent null before going any further
				if(gasolineTankLoc.length == 3 && dieselTankLoc.length == 3 && liquifiedPetroleumGasTankLoc.length == 3 && keroseneTankLoc.length == 3 && gasolineTankLoc[1] != 0 && dieselTankLoc[1] != 0 && liquifiedPetroleumGasTankLoc[1] != 0 && keroseneTankLoc[1] != 0) {
					//Checks to see if there is space in the tanks
					TileEntityFractionationTowerFluidIO gasolineFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(gasolineTankLoc[0], gasolineTankLoc[1], gasolineTankLoc[2]);
					TileEntityFractionationTowerFluidIO dieselFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(dieselTankLoc[0], dieselTankLoc[1], dieselTankLoc[2]);
					TileEntityFractionationTowerFluidIO liquiefiedPetroleumGasFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(liquifiedPetroleumGasTankLoc[0], liquifiedPetroleumGasTankLoc[1], liquifiedPetroleumGasTankLoc[2]);
					TileEntityFractionationTowerFluidIO keroseneFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(keroseneTankLoc[0], keroseneTankLoc[1], keroseneTankLoc[2]);
					if(gasolineFluidIO.gasolineTank.fill(new FluidStack(Fluids.fluidGasoline, 1), false) >= 1 && dieselFluidIO.dieselTank.fill(new FluidStack(Fluids.fluidDiesel, 1), false) >= 1 && liquiefiedPetroleumGasFluidIO.liquifiedPetroleumGasTank.fill(new FluidStack(Fluids.fluidLiquifiedPetroleumGas, 1), false) >= 1 && keroseneFluidIO.keroseneTank.fill(new FluidStack(Fluids.fluidKerosene, 1), false) >= 1) {
						//Checks to make sure there is enough power
						TileEntityFractionationTowerPowerInput tempTE = (TileEntityFractionationTowerPowerInput)worldObj.getTileEntity(powerInputLoc[0], powerInputLoc[1], powerInputLoc[2]);
						if(tempTE != null) {
							int tempInt = tempTE.extractEnergy(ForgeDirection.UP, 200, true);
							if(tempInt == 200) {
								tempTE.extractEnergy(ForgeDirection.UP, 200, false);
								
								//Checks to make sure there is enough oil
								TileEntityFractionationTowerFluidIO tempTE1 = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord); 
								if(tempTE1 != null) {
									FluidStack tempstack = tempTE1.oilTank.drain(4, false);
									if(tempstack.amount != 0) {
										tempTE1.oilTank.drain(2, true);
										
										gasolineFluidIO.fill(ForgeDirection.UP, new FluidStack(Fluids.fluidGasoline, 1), true);
										dieselFluidIO.fill(ForgeDirection.UP, new FluidStack(Fluids.fluidDiesel, 1), true);
										liquiefiedPetroleumGasFluidIO.fill(ForgeDirection.UP, new FluidStack(Fluids.fluidLiquifiedPetroleumGas, 1), true);
										keroseneFluidIO.fill(ForgeDirection.UP, new FluidStack(Fluids.fluidKerosene, 1), true);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public boolean isMasterBlock() {
		return isMasterBlock;
	}
	
	public boolean hasMasterBlock() {
		return hasMasterBlock;
	}
	
	public void setIsMasterBlock() {
		this.isMasterBlock = true;
		this.setupMultiBlock();
	}
	
	public void setHasMasterBlock(int x, int y, int z) {
		this.hasMasterBlock = true;
		this.masterX = x;
		this.masterY = y;
		this.masterZ = z;
	}
	
	public boolean isMultiblockValid() {
		int correctBlocks = 0;
		
		for(int x = xCoord-2; x < xCoord+2; x++) {
			for(int y = yCoord-5; y < yCoord+5; y++) {
				for(int z = zCoord-2; z < zCoord+2; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile!= null && tile instanceof TileEntityFractionationTower) {
						if(((TileEntityFractionationTower)tile).isMasterBlock()) {
							if(((TileEntityFractionationTower)tile).hasMasterBlock()) {
								correctBlocks++;
							}
						}
						else if(!((TileEntityFractionationTower)tile).hasMasterBlock()) {
							correctBlocks++;
						}
					}
				}
			}
		}		
		return correctBlocks > 50 && worldObj.isAirBlock(xCoord, yCoord + 1, zCoord) && worldObj.isAirBlock(xCoord, yCoord + 2, zCoord) && worldObj.isAirBlock(xCoord, yCoord + 3, zCoord) && worldObj.isAirBlock(xCoord, yCoord + 4, zCoord);
	}
	
	public boolean hasBlocksAround(int x, int y, int z) {
		return worldObj.getTileEntity(x+1, y, z) instanceof TileEntityFractionationTower && worldObj.getTileEntity(x-1, y, z) instanceof TileEntityFractionationTower && worldObj.getTileEntity(x, y, z+1) instanceof TileEntityFractionationTower && worldObj.getTileEntity(x, y, z-1) instanceof TileEntityFractionationTower && worldObj.getTileEntity(x+1, y+1, z) instanceof TileEntityFractionationTower;
	}

	private void setupMultiBlock() {
		for(int x = xCoord-2; x < xCoord+2; x++) {
			for(int y = yCoord-5; y < yCoord+5; y++) {
				for(int z = zCoord-2; z < zCoord+2; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile != null && (tile instanceof TileEntityFractionationTower)) {
						((TileEntityFractionationTower)tile).setHasMasterBlock(xCoord, yCoord, zCoord);
					}
				}
			}
		}
	}
	
	public void addTankLoc(int x, int y, int z, int type) {
		switch(type) {
			case 1:
				gasolineTankLoc[0] = x;
				gasolineTankLoc[1] = y;
				gasolineTankLoc[2] = z;
				break;
			case 2:
				dieselTankLoc[0] = x;
				dieselTankLoc[1] = y;
				dieselTankLoc[2] = z;
				break;
			case 3:
				liquifiedPetroleumGasTankLoc[0] = x;
				liquifiedPetroleumGasTankLoc[1] = y;
				liquifiedPetroleumGasTankLoc[2] = z;
				break;
			case 4:
				keroseneTankLoc[0] = x;
				keroseneTankLoc[1] = y;
				keroseneTankLoc[2] = z;
				break;
		}
	}
	
	public void addPowerLoc(int x, int y, int z) {
		powerInputLoc[0] = x;
		powerInputLoc[1] = y;
		powerInputLoc[2] = z;
	}
	
	public boolean checkForMaster() {
		TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
		return (tile != null && (tile instanceof TileEntityFractionationTower));
	}
	
	private void reset() {
		masterX = 0;
		masterY = 0;
		masterZ = 0;
		hasMasterBlock = false;
		isMasterBlock = false;
	}
	
	public void resetStructure() {
		for(int x = xCoord-2; x < xCoord+2; x++) {
			for(int y = yCoord-5; y < yCoord+5; y++) {
				for(int z = zCoord-2; z < zCoord+2; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile != null && tile instanceof TileEntityFractionationTower) {
						((TileEntityFractionationTower)tile).reset();
					}
				}
			}
		}
	}
}
