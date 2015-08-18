/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.chazwarp.miscadditions.fluid.FluidTankMA;
import com.chazwarp.miscadditions.fluid.Fluids;

import cpw.mods.fml.common.Loader;

public class TileEntityFractionationTowerFluidIO extends TileEntityFractionationTower implements IFluidHandler {

	private FluidStack oil;
	private FluidStack gasoline = new FluidStack(Fluids.fluidGasoline, 0);
	private FluidStack diesel = new FluidStack(Fluids.fluidDiesel, 0);
	private FluidStack liquifiedPetroleumGas = new FluidStack(Fluids.fluidLiquifiedPetroleumGas, 0);
	private FluidStack kerosene = new FluidStack(Fluids.fluidKerosene, 0);

	protected FluidTankMA oilTank = new FluidTankMA(oil, FluidContainerRegistry.BUCKET_VOLUME * 8, "OilTank");
	protected FluidTankMA gasolineTank = new FluidTankMA(gasoline, FluidContainerRegistry.BUCKET_VOLUME * 2, "GasolineTank");
	protected FluidTankMA dieselTank = new FluidTankMA(diesel, FluidContainerRegistry.BUCKET_VOLUME * 2, "Diesel Tank");
	protected FluidTankMA liquifiedPetroleumGasTank = new FluidTankMA(liquifiedPetroleumGas, FluidContainerRegistry.BUCKET_VOLUME * 2, "LiquifiedPetroleumGasTank");
	protected FluidTankMA keroseneTank = new FluidTankMA(kerosene, FluidContainerRegistry.BUCKET_VOLUME * 2, "KeroseneTank");

	protected int mode = 0;

	public TileEntityFractionationTowerFluidIO() {
		if (Loader.isModLoaded("BuildCraft|Energy")) {
			oil = new FluidStack(FluidRegistry.getFluid("oil"), 0);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		oilTank.writeToNBT(tag);
		gasolineTank.writeToNBT(tag);
		dieselTank.writeToNBT(tag);
		liquifiedPetroleumGasTank.writeToNBT(tag);
		keroseneTank.writeToNBT(tag);
		tag.setInteger("Mode", this.mode);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		oilTank = oilTank.readFromNBT(tag);
		gasolineTank = gasolineTank.readFromNBT(tag);
		dieselTank = dieselTank.readFromNBT(tag);
		liquifiedPetroleumGasTank = liquifiedPetroleumGasTank.readFromNBT(tag);
		keroseneTank = keroseneTank.readFromNBT(tag);
		this.mode = tag.getInteger("Mode");
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		writeToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
	}
	
	@Override
	public String[] getDebugStatus() {
		String[] debugInfo = new String[16];
		debugInfo[0] = Boolean.toString(this.isMasterBlock);
		debugInfo[1] = Boolean.toString(this.hasMasterBlock);
		debugInfo[2] = Integer.toString(this.masterX);
		debugInfo[3] = Integer.toString(this.masterY);
		debugInfo[4] = Integer.toString(this.masterZ);
		debugInfo[5] = this.gasolineTankX + "_" + this.gasolineTankY + "_" + this.gasolineTankZ;
		debugInfo[6] = this.dieselTankX + "_" + this.dieselTankY + "_" + this.dieselTankZ;
		debugInfo[7] = this.liquifiedPetroleumGasTankX + "_" + this.liquifiedPetroleumGasTankY + "_" + this.liquifiedPetroleumGasTankZ;
		debugInfo[8] = this.keroseneTankX + "_" + this.keroseneTankY + "_" + this.keroseneTankZ;
		debugInfo[9] = this.powerInputX + "_" + this.powerInputY + "_" + this.powerInputZ;
		debugInfo[10] = Integer.toString(oilTank.getFluidAmount());
		debugInfo[11] = Integer.toString(gasolineTank.getFluidAmount());
		debugInfo[12] = Integer.toString(dieselTank.getFluidAmount());
		debugInfo[13] = Integer.toString(liquifiedPetroleumGasTank.getFluidAmount());
		debugInfo[14] = Integer.toString(keroseneTank.getFluidAmount());
		debugInfo[15] = Integer.toString(this.mode);
		return debugInfo;
	}
	
	@Override
	public void setHasMasterBlock(int x, int y, int z) {
		super.setHasMasterBlock(x, y, z);
		
		if(!worldObj.isRemote) {
			addReferenceToMaster();
		}
	}

	public int getMode() {
		return this.mode;
	}

	public void cycleMode() {
		switch(this.mode) {
		case 0:
			this.mode = 1;
			this.markDirty();
			break;
		case 1:
			this.mode = 2;
			this.markDirty();
			break;
		case 2:
			this.mode = 3;
			this.markDirty();
			break;
		case 3:
			this.mode = 4;
			this.markDirty();
			break;
		case 4:
			this.mode = 0;
			this.markDirty();
			break;
		default:
			System.out.println("Something done fucked up");
		}
	}
	
	public void addReferenceToMaster() {
		TileEntityFractionationTower masterTile = (TileEntityFractionationTower)worldObj.getTileEntity(this.masterX, this.masterY, this.masterZ);
		
		switch(this.mode) {
			case 0:
				break;	
			case 1:
				masterTile.gasolineTankX = this.xCoord;
				masterTile.gasolineTankX = this.yCoord;
				masterTile.gasolineTankX = this.zCoord;
				masterTile.markDirty();
				sendUpdatesToServer(masterX, masterY, masterZ);
				break;
			case 2:
				masterTile.dieselTankX = this.xCoord;
				masterTile.dieselTankX = this.yCoord;
				masterTile.dieselTankX = this.zCoord;
				masterTile.markDirty();
				sendUpdatesToServer(masterX, masterY, masterZ);
				break;
			case 3:
				masterTile.liquifiedPetroleumGasTankX = this.xCoord;
				masterTile.liquifiedPetroleumGasTankY = this.yCoord;
				masterTile.liquifiedPetroleumGasTankZ = this.zCoord;
				masterTile.markDirty();
				sendUpdatesToServer(masterX, masterY, masterZ);
				break;
			case 4:
				masterTile.keroseneTankX = this.xCoord;
				masterTile.keroseneTankY = this.yCoord;
				masterTile.keroseneTankZ = this.zCoord;
				masterTile.markDirty();
				sendUpdatesToServer(masterX, masterY, masterZ);
				break;
			default:
				System.out.println("Something done fucked up");
		}
	}

	/** IFluidHandler */

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (oil != null && resource.isFluidEqual(oil) && mode == 0) {
			return oilTank.fill(resource, doFill);
		}
		else {
			return 0;
		}
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null) {
			return null;
		} 
		else if (resource.isFluidEqual(gasolineTank.getFluid()) && mode == 1) {
			return gasolineTank.drain(resource.amount, doDrain);
		} 
		else if (resource.isFluidEqual(dieselTank.getFluid()) && mode == 2) {
			return dieselTank.drain(resource.amount, doDrain);
		} 
		else if (resource.isFluidEqual(liquifiedPetroleumGasTank.getFluid()) && mode == 3) {
			return liquifiedPetroleumGasTank.drain(resource.amount, doDrain);
		} 
		else if (resource.isFluidEqual(keroseneTank.getFluid()) && mode == 4) {
			return keroseneTank.drain(resource.amount, doDrain);
		} 
		else {
			return null;
		}
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if (oil != null && fluid == oil.getFluid()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if (fluid == Fluids.fluidGasoline || fluid == Fluids.fluidDiesel || fluid == Fluids.fluidLiquifiedPetroleumGas || fluid == Fluids.fluidKerosene) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		FluidTankInfo[] temp = new FluidTankInfo[5];

		temp[0] = new FluidTankInfo(oilTank);
		temp[1] = new FluidTankInfo(gasolineTank);
		temp[2] = new FluidTankInfo(dieselTank);
		temp[3] = new FluidTankInfo(liquifiedPetroleumGasTank);
		temp[4] = new FluidTankInfo(keroseneTank);

		return temp;
	}
}
