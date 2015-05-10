/**
@author Chaz Kerby
*/

package com.chazwarp.miscadditions.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class Fluids {
	
	public static Fluid fluidGasoline = new FluidMA("fluidGasoline", 0, 720, 295, 720, false);
	public static Fluid fluidDiesel = new FluidMA("fluidDiesel", 0, 820, 295, 820, false);
	public static Fluid fluidPropane = new FluidMA("fluidPropane", 0, 720, 295, 750, true);
	public static Fluid fluidKerosene = new FluidMA("fluidKerosene", 0, 494, 295, 494, false);
	
	public static void registerFluids() {
		FluidRegistry.registerFluid(fluidGasoline);
		FluidRegistry.registerFluid(fluidDiesel);
		FluidRegistry.registerFluid(fluidPropane);
		FluidRegistry.registerFluid(fluidKerosene);
	}
}
