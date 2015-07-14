/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class FluidTankMA implements IFluidTank {

	protected FluidStack fluid;
	protected int capacity;
	protected TileEntity tile;
	protected String name;

	public FluidTankMA(FluidStack stack, int capacity, String name) {
		this.fluid = stack;
		this.capacity = capacity;
		this.name = name;
	}

	public FluidTankMA(Fluid fluid, int amount, int capacity, String name) {
		this(new FluidStack(fluid, amount), capacity, name);
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagCompound subCompound = nbt.getCompoundTag(name);
		if (fluid != null) {
			fluid.writeToNBT(subCompound);
			nbt.setTag(name, subCompound);
		} else {
			subCompound.setString("Empty", "");
		}
		return nbt;
	}

	public FluidTankMA readFromNBT(NBTTagCompound nbt) {
		NBTTagCompound subCompound = nbt.getCompoundTag(name);
		if (!subCompound.hasKey("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(subCompound);
			setFluid(fluid);
		}
		return this;
	}

	public void setFluid(FluidStack fluid) {
		this.fluid = fluid;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/* IFluidTank */
	@Override
	public FluidStack getFluid() {
		return fluid;
	}

	@Override
	public int getFluidAmount() {
		if (fluid == null) {
			return 0;
		}
		return fluid.amount;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null) {
			return 0;
		}

		if (!doFill) {
			if (fluid == null) {
				return Math.min(capacity, resource.amount);
			}

			if (!fluid.isFluidEqual(resource)) {
				return 0;
			}

			return Math.min(capacity - fluid.amount, resource.amount);
		}

		if (fluid == null) {
			fluid = new FluidStack(resource,
					Math.min(capacity, resource.amount));

			if (tile != null) {
				FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid,
						tile.getWorldObj(), tile.xCoord, tile.yCoord,
						tile.zCoord, this, fluid.amount));
			}
			return fluid.amount;
		}

		if (!fluid.isFluidEqual(resource)) {
			return 0;
		}
		int filled = capacity - fluid.amount;

		if (resource.amount < filled) {
			fluid.amount += resource.amount;
			filled = resource.amount;
		} else {
			fluid.amount = capacity;
		}

		if (tile != null) {
			FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, tile
					.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord,
					this, filled));
		}
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (fluid == null) {
			return null;
		}

		int drained = maxDrain;
		if (fluid.amount < drained) {
			drained = fluid.amount;
		}

		FluidStack stack = new FluidStack(fluid, drained);
		if (doDrain) {
			fluid.amount -= drained;
			if (fluid.amount <= 0) {
				fluid = null;
			}

			if (tile != null) {
				FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid,
						tile.getWorldObj(), tile.xCoord, tile.yCoord,
						tile.zCoord, this, drained));
			}
		}
		return stack;
	}
}
