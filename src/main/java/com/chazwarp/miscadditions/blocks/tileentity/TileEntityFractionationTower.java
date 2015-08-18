/**
@author Chaz Kerby
*/

package com.chazwarp.miscadditions.blocks.tileentity;

import com.chazwarp.miscadditions.MiscAdditions;
import com.chazwarp.miscadditions.fluid.Fluids;
import com.chazwarp.miscadditions.networking.SyncFractionationTowerPacket000;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityFractionationTower extends TileEntity implements IDebuggable{
	
	int gasolineTankX = 0, gasolineTankY = 0, gasolineTankZ = 0;
	int dieselTankX = 0, dieselTankY = 0, dieselTankZ = 0;
	int liquifiedPetroleumGasTankX = 0, liquifiedPetroleumGasTankY = 0, liquifiedPetroleumGasTankZ = 0;
	int keroseneTankX = 0, keroseneTankY = 0, keroseneTankZ = 0;	
	int powerInputX = 0, powerInputY = 0, powerInputZ = 0;
	
	
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
		locCompound.setInteger("GasolineTankX", gasolineTankX);
		locCompound.setInteger("GasolineTankY", gasolineTankY);
		locCompound.setInteger("GasolineTankZ", gasolineTankZ);
		locCompound.setInteger("DieselTankX", dieselTankX);
		locCompound.setInteger("DieselTankY", dieselTankY);
		locCompound.setInteger("DieselTankZ", dieselTankZ);
		locCompound.setInteger("LiquifiedPetroleumGasX", liquifiedPetroleumGasTankX);
		locCompound.setInteger("LiquifiedPetroleumGasY", liquifiedPetroleumGasTankY);
		locCompound.setInteger("LiquifiedPetroleumGasZ", liquifiedPetroleumGasTankZ);
		locCompound.setInteger("KeroseneTankX", keroseneTankX);
		locCompound.setInteger("KeroseneTankY", keroseneTankY);
		locCompound.setInteger("KeroseneTankZ", keroseneTankZ);
		locCompound.setInteger("PowerInputX", powerInputX);
		locCompound.setInteger("PowerInputY", powerInputY);
		locCompound.setInteger("PowerInputZ", powerInputZ);
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
		gasolineTankX = locCompound.getInteger("GasolineTankX");
		gasolineTankY = locCompound.getInteger("GasolineTankY");
		gasolineTankZ = locCompound.getInteger("GasolineTankZ");
		dieselTankX = locCompound.getInteger("DieselTankX");
		dieselTankY = locCompound.getInteger("DieselTankY");
		dieselTankZ = locCompound.getInteger("DieselTankZ");
		liquifiedPetroleumGasTankX = locCompound.getInteger("LiquifiedPetroleumGasX");
		liquifiedPetroleumGasTankY = locCompound.getInteger("LiquifiedPetroleumGasY");
		liquifiedPetroleumGasTankZ = locCompound.getInteger("LiquifiedPetroleumGasZ");
		keroseneTankX = locCompound.getInteger("KeroseneTankX");
		keroseneTankY = locCompound.getInteger("KeroseneTankY");
		keroseneTankZ = locCompound.getInteger("KeroseneTankZ");
		powerInputX = locCompound.getInteger("PowerInputX");
		powerInputY = locCompound.getInteger("PowerInputY");
		powerInputZ = locCompound.getInteger("PowerInputZ");
	}
	
	private void sendUpdatesToServer() {
		NBTTagCompound compound = new NBTTagCompound();
		writeToNBT(compound);
		MiscAdditions.network.sendToServer(new SyncFractionationTowerPacket000(compound, this.xCoord, this.yCoord, this.zCoord));
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		writeToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
	}
	
	@Override
	public String[] getDebugStatus() {
		String[] debugInfo = new String[10];
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
		return debugInfo;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(this.isMasterBlock) {
			if(!worldObj.isRemote) {
				//Checks to make sure the tanks arent null before going any further
				if(gasolineTankY != 0 && dieselTankY != 0 && liquifiedPetroleumGasTankY != 0 && keroseneTankY != 0) {
					//Checks to see if there is space in the tanks
					TileEntityFractionationTowerFluidIO gasolineFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(gasolineTankX, gasolineTankY, gasolineTankZ);
					TileEntityFractionationTowerFluidIO dieselFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(dieselTankX, dieselTankY, dieselTankZ);
					TileEntityFractionationTowerFluidIO liquiefiedPetroleumGasFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(liquifiedPetroleumGasTankX, liquifiedPetroleumGasTankY, liquifiedPetroleumGasTankZ);
					TileEntityFractionationTowerFluidIO keroseneFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(keroseneTankX, keroseneTankY, keroseneTankZ);
					if(gasolineFluidIO.gasolineTank.fill(new FluidStack(Fluids.fluidGasoline, 1), false) >= 1 && dieselFluidIO.dieselTank.fill(new FluidStack(Fluids.fluidDiesel, 1), false) >= 1 && liquiefiedPetroleumGasFluidIO.liquifiedPetroleumGasTank.fill(new FluidStack(Fluids.fluidLiquifiedPetroleumGas, 1), false) >= 1 && keroseneFluidIO.keroseneTank.fill(new FluidStack(Fluids.fluidKerosene, 1), false) >= 1) {
						//Checks to make sure there is enough power
						TileEntityFractionationTowerPowerInput powerInput = (TileEntityFractionationTowerPowerInput)worldObj.getTileEntity(powerInputX, powerInputY, powerInputZ);
						if(powerInput != null) {
							int tempInt = powerInput.extractEnergy(ForgeDirection.UP, 200, true);
							if(tempInt == 200) {
								//Checks to make sure there is enough oil
								TileEntityFractionationTowerFluidIO mainBlock = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord); 
								if(mainBlock != null) {
									FluidStack tempstack = mainBlock.oilTank.drain(4, false);
									if(tempstack.amount == 4) {
										mainBlock.oilTank.drain(4, true);
										powerInput.extractEnergy(ForgeDirection.UP, 200, false);
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
			for(int y = yCoord; y < yCoord+5; y++) {
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
			for(int y = yCoord; y < yCoord+5; y++) {
				for(int z = zCoord-2; z < zCoord+2; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile != null && (tile instanceof TileEntityFractionationTower)) {
						((TileEntityFractionationTower)tile).setHasMasterBlock(xCoord, yCoord, zCoord);
						((TileEntityFractionationTower)tile).sendUpdatesToServer();
					}
				}
			}
		}
	}
	
	public void addTankLoc(int x, int y, int z, int type) {
		switch(type) {
			case 1:
				gasolineTankX = x;
				gasolineTankY = y;
				gasolineTankZ = z;
				break;
			case 2:
				dieselTankX = x;
				dieselTankY = y;
				dieselTankZ = z;
				break;
			case 3:
				liquifiedPetroleumGasTankX = x;
				liquifiedPetroleumGasTankY = y;
				liquifiedPetroleumGasTankZ = z;
				break;
			case 4:
				keroseneTankX = x;
				keroseneTankY = y;
				keroseneTankZ = z;
				break;
		}
	}
	
	public void addPowerLoc(int x, int y, int z) {
		powerInputX = x;
		powerInputY = y;
		powerInputZ = z;
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
