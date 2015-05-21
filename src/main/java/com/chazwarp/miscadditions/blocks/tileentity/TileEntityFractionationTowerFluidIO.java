/**
@author Chaz Kerby
*/

package com.chazwarp.miscadditions.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import buildcraft.BuildCraftEnergy;

import com.chazwarp.miscadditions.fluid.FluidTankMA;
import com.chazwarp.miscadditions.fluid.Fluids;

import cpw.mods.fml.common.Loader;

public class TileEntityFractionationTowerFluidIO extends TileEntity implements IFluidHandler {

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
	
	private boolean isMainBlock = false;
	private int mode = 0;
	
	public TileEntityFractionationTowerFluidIO() {
		if(Loader.isModLoaded("BuildCraft|Energy")) {
			oil = new FluidStack(BuildCraftEnergy.fluidOil, 0);
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
    }
	
	public int getMode() {
		return mode;
	}
	
	public void cycleFluid() {
		//Mode 0 is Oil
		if(mode == 0) {
			mode = 1;
		}
		//Mode 1 is Gasoline
		else if(mode == 1) {
			mode = 2;
		}
		//Mode 2 is Diesel
		else if(mode == 2) {
			mode = 3;
		}
		//Mode 3 is Liquified Petroleum Gas
		else if(mode == 3) {
			mode = 4;
		}
		//Mode 4 is Kerosene
		else if(mode == 4) {
			mode = 0;
		}
	}
	
	/** IFluidHandler */
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(oil != null && resource.isFluidEqual(oil) && mode == 0) {
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
		else if(resource.isFluidEqual(gasolineTank.getFluid()) && mode == 1) {
			return gasolineTank.drain(resource.amount, doDrain);
		}
		else if(resource.isFluidEqual(dieselTank.getFluid()) && mode == 2) {
			return dieselTank.drain(resource.amount, doDrain);
		}
		else if(resource.isFluidEqual(liquifiedPetroleumGasTank.getFluid()) && mode == 3) {
			return liquifiedPetroleumGasTank.drain(resource.amount, doDrain);
		}
		else if(resource.isFluidEqual(keroseneTank.getFluid()) && mode == 4) {
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
		if(oil != null && fluid == oil.getFluid()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(fluid == Fluids.fluidGasoline || fluid == Fluids.fluidDiesel || fluid == Fluids.fluidLiquifiedPetroleumGas || fluid == Fluids.fluidKerosene) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		FluidTankInfo[] temp = new FluidTankInfo[4];
		
		temp[0] = new FluidTankInfo(gasolineTank);
		temp[1] = new FluidTankInfo(dieselTank);
		temp[2] = new FluidTankInfo(liquifiedPetroleumGasTank);
		temp[3] = new FluidTankInfo(keroseneTank);
		
		return temp;
	}
}
