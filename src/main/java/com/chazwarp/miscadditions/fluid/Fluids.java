/**
@author Chaz Kerby
*/

package com.chazwarp.miscadditions.fluid;

import com.chazwarp.miscadditions.lib.BlockInfo;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class Fluids {
	
	public static Fluid fluidGasoline = new FluidMA("fluidGasoline", 0, 720, 300, 720, false);
	public static Fluid fluidDiesel = new FluidMA("fluidDiesel", 0, 820, 300, 820, false);
	public static Fluid fluidPropane = new FluidMA("fluidPropane", 0, 720, 300, 750, true);
	public static Fluid fluidKerosene = new FluidMA("fluidKerosene", 0, 494, 300, 494, false);
	
	public static void registerFluids() {
		fluidGasoline.setUnlocalizedName(BlockInfo.FLUID_GASOLINE_UNLOCALIZED_NAME);
		FluidRegistry.registerFluid(fluidGasoline);
		fluidDiesel.setUnlocalizedName(BlockInfo.FLUID_DIESEL_UNLOCALIZED_NAME);
		FluidRegistry.registerFluid(fluidDiesel);
		fluidPropane.setUnlocalizedName(BlockInfo.FLUID_PROPANE_UNLOCALIZED_NAME);
		FluidRegistry.registerFluid(fluidPropane);
		fluidKerosene.setUnlocalizedName(BlockInfo.FLUID_KEROSENE_UNLOCALIZED_NAME);
		FluidRegistry.registerFluid(fluidKerosene);
	}
}
