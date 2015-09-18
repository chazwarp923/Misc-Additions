/**
@author Chaz Kerby
*/

package com.chazwarp.miscadditions.blocks.tileentity;

import com.chazwarp.miscadditions.MiscAdditions;
import com.chazwarp.miscadditions.fluid.Fluids;
import com.chazwarp.miscadditions.networking.SyncFractionationTowerPacket000;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityFractionationTower extends TileEntity implements IDebuggable {
	
	public boolean isMasterBlock = false;
	public boolean hasMasterBlock = false;
	public int masterX = 0, masterY = 0, masterZ = 0;
	
	protected int gasolineTankX = 0, gasolineTankY = 0, gasolineTankZ = 0;
	protected int dieselTankX = 0, dieselTankY = 0, dieselTankZ = 0;
	protected int liquifiedPetroleumGasTankX = 0, liquifiedPetroleumGasTankY = 0, liquifiedPetroleumGasTankZ = 0;
	protected int keroseneTankX = 0, keroseneTankY = 0, keroseneTankZ = 0;	
	protected int powerInputX = 0, powerInputY = 0, powerInputZ = 0;
	
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
	
	protected void sendUpdatesToServer() {
		NBTTagCompound compound = new NBTTagCompound();
		writeToNBT(compound);
		MiscAdditions.network.sendToServer(new SyncFractionationTowerPacket000(compound, xCoord, yCoord, zCoord, 0));
	}
	
	protected void sendUpdatesToServer(int x, int y, int z) {
		NBTTagCompound compound = new NBTTagCompound();
		writeToNBT(compound);
		MiscAdditions.network.sendToServer(new SyncFractionationTowerPacket000(compound, x, y, z, 0));
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.writeMultiBlockDataToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readMultiBlockDataFromNBT(pkt.func_148857_g());
	}
	
	@Override
	public String[] getDebugStatus() {
		String[] debugInfo = new String[4];
		debugInfo[0] = Boolean.toString(this.hasMasterBlock);
		debugInfo[1] = Integer.toString(this.masterX);
		debugInfo[2] = Integer.toString(this.masterY);
		debugInfo[3] = Integer.toString(this.masterZ);
		return debugInfo;
	}
	
	@Override
	public void updateEntity() {		
		if(this.isMasterBlock) {
			if(!worldObj.isRemote) {
				//Checks to make sure the tanks arent null before going any further
				if(gasolineTankY != 0 && dieselTankY != 0 && liquifiedPetroleumGasTankY != 0 && keroseneTankY != 0) {
					//Checks to see if there is space in the tanks
					TileEntityFractionationTowerFluidIO gasolineFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(gasolineTankX, gasolineTankY, gasolineTankZ);
					TileEntityFractionationTowerFluidIO dieselFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(dieselTankX, dieselTankY, dieselTankZ);
					TileEntityFractionationTowerFluidIO liquiefiedPetroleumGasFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(liquifiedPetroleumGasTankX, liquifiedPetroleumGasTankY, liquifiedPetroleumGasTankZ);
					TileEntityFractionationTowerFluidIO keroseneFluidIO = (TileEntityFractionationTowerFluidIO)worldObj.getTileEntity(keroseneTankX, keroseneTankY, keroseneTankZ);
					if(gasolineFluidIO.gasolineTank.fill(new FluidStack(Fluids.fluidGasoline, 1), false) == 1 && dieselFluidIO.dieselTank.fill(new FluidStack(Fluids.fluidDiesel, 1), false) == 1 && liquiefiedPetroleumGasFluidIO.liquifiedPetroleumGasTank.fill(new FluidStack(Fluids.fluidLiquifiedPetroleumGas, 1), false) == 1 && keroseneFluidIO.keroseneTank.fill(new FluidStack(Fluids.fluidKerosene, 1), false) == 1) {
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
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.markDirty();
	}
	
	public void setHasMasterBlock(int x, int y, int z) {
		this.hasMasterBlock = true;
		this.masterX = x;
		this.masterY = y;
		this.masterZ = z;
	}
	
	public boolean isMultiblockValid(int mastX, int mastY, int mastZ) {
		int correctBlocks = 0;
		
		for(int x = mastX-2; x < mastX+2; x++) {
			for(int y = mastY; y < mastY+6; y++) {
				for(int z = mastZ-2; z < mastZ+2; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile!= null && tile instanceof TileEntityFractionationTower) {
						if(!((TileEntityFractionationTower)tile).hasMasterBlock()) {
							correctBlocks++;
						}
					}
				}
			}
		}
		if(worldObj.isRemote == false) {
			correctBlocks++;
		}
		System.out.println(Boolean.toString(worldObj.isRemote) + " " + Integer.toString(correctBlocks));
		return correctBlocks == 50 && worldObj.isAirBlock(mastX, mastY + 1, mastZ) && worldObj.isAirBlock(mastX, mastY + 2, mastZ) && worldObj.isAirBlock(mastX, mastY + 3, mastZ) && worldObj.isAirBlock(mastX, mastY + 4, mastZ);
	}

	private void setupMultiBlock() {
		for(int x = xCoord-2; x < xCoord+2; x++) {
			for(int y = yCoord; y < yCoord+6; y++) {
				for(int z = zCoord-2; z < zCoord+2; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile != null && (tile instanceof TileEntityFractionationTower)) {
						((TileEntityFractionationTower)tile).setHasMasterBlock(xCoord, yCoord, zCoord);
						//((TileEntityFractionationTower)tile).sendUpdatesToServer();
						worldObj.markBlockForUpdate(x, y, z);
						((TileEntityFractionationTower)tile).markDirty();
					}
				}
			}
		}
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
		this.markDirty();
	}
	
	public void resetStructure() {
		for(int x = xCoord-2; x < xCoord+2; x++) {
			for(int y = yCoord; y < yCoord+6; y++) {
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
