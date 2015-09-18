/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.fluid;

import com.chazwarp.miscadditions.lib.BlockInfo;

import net.minecraftforge.fluids.FluidRegistry;

public class Fluids {

	public static FluidMA fluidGasoline = new FluidMA("gasoline", BlockInfo.FLUID_GASOLINE_UNLOCALIZED_NAME, 0, 720, 300, 720, false);
	public static FluidMA fluidDiesel = new FluidMA("diesel", BlockInfo.FLUID_DIESEL_UNLOCALIZED_NAME, 0, 820, 300, 820, false);
	public static FluidMA fluidLiquifiedPetroleumGas = new FluidMA("liquifiedPetroleumGas", BlockInfo.FLUID_LIQUIFIED_PETROLEUM_GAS_UNLOCALIZED_NAME, 0, 720, 300, 750, false);
	public static FluidMA fluidKerosene = new FluidMA("kerosene", BlockInfo.FLUID_KEROSENE_UNLOCALIZED_NAME, 0, 494, 300, 494, false);

	public static void registerFluids() {
		FluidRegistry.registerFluid(fluidGasoline);
		FluidRegistry.registerFluid(fluidDiesel);
		FluidRegistry.registerFluid(fluidLiquifiedPetroleumGas);
		FluidRegistry.registerFluid(fluidKerosene);
	}
}
