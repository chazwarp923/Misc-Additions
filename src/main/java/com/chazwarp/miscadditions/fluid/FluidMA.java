/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.fluid;

import net.minecraftforge.fluids.Fluid;

public class FluidMA extends Fluid {

	public FluidMA(String fluidName, int luminosity, int density,
			int temperature, int viscosity, boolean gaseous) {
		super(fluidName);
		setLuminosity(luminosity);
		setDensity(density);
		setTemperature(temperature);
		setViscosity(viscosity);
		setGaseous(gaseous);
	}
}
