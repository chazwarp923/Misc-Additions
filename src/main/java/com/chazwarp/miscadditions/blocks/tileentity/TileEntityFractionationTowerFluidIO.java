/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
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

	private int mode = 0;
	
	public enum EnumModes {
		OIL(0), GASOLINE(1), DIESEL(2), LIQUIFIED_PETROLEUM_GAS(3), KEROSENE(4);
		private int value;
		
		private EnumModes(int value) {
			this.value = value;
		}
	}

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
		tag.setInteger("mode", mode);
		writeMultiBlockDataToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		oilTank = oilTank.readFromNBT(tag);
		gasolineTank = gasolineTank.readFromNBT(tag);
		dieselTank = dieselTank.readFromNBT(tag);
		liquifiedPetroleumGasTank = liquifiedPetroleumGasTank.readFromNBT(tag);
		keroseneTank = keroseneTank.readFromNBT(tag);
		mode = tag.getInteger("mode");
		readMultiBlockDataFromNBT(tag);
	}
	
	@Override
	public void setHasMasterBlock(int x, int y, int z) {
		super.setHasMasterBlock(x, y, z);
		
		addTankReferenceToMaster();
	}

	public int getMode() {
		return mode;
	}

	public void cycleMode() {
		if (mode == EnumModes.OIL.value) {
			mode = 1;
		}
		else if (mode == EnumModes.GASOLINE.value) {
			mode = 2;
		}
		else if (mode == EnumModes.DIESEL.value) {
			mode = 3;
		}
		else if (mode == EnumModes.LIQUIFIED_PETROLEUM_GAS.value) {
			mode = 4;
		}
		else if (mode == EnumModes.KEROSENE.value) {
			mode = 0;
		}
	}
	
	public void addTankReferenceToMaster() {
		TileEntityFractionationTower masterTile = (TileEntityFractionationTower)worldObj.getTileEntity(masterX, masterY, masterZ);
		
		switch(mode) {
			case 1:
				masterTile.addTankLoc(this.xCoord, this.yCoord, this.zCoord, EnumModes.GASOLINE.value);
				break;
			case 2:
				masterTile.addTankLoc(this.xCoord, this.yCoord, this.zCoord, EnumModes.DIESEL.value);
				break;
			case 3:
				masterTile.addTankLoc(this.xCoord, this.yCoord, this.zCoord, EnumModes.LIQUIFIED_PETROLEUM_GAS.value);
				break;
			case 4:
				masterTile.addTankLoc(this.xCoord, this.yCoord, this.zCoord, EnumModes.KEROSENE.value);
				break;
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
