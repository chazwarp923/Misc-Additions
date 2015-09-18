/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class FluidMA extends Fluid {

	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon flowingIcon;
	
	public FluidMA(String fluidName, String unlocalizedName, int luminosity, int density, int temperature, int viscosity, boolean gaseous) {
		super(fluidName);
		
		setUnlocalizedName(unlocalizedName);
		setLuminosity(luminosity);
		setDensity(density);
		setTemperature(temperature);
		setViscosity(viscosity);
		setGaseous(gaseous);
	}
}
