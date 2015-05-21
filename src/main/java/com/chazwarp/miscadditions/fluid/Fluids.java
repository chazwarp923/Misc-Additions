/**
@author Chaz Kerby
*/

package com.chazwarp.miscadditions.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.chazwarp.miscadditions.lib.BlockInfo;

public class Fluids {
	
	public static Fluid fluidGasoline = new FluidMA("gasoline", 0, 720, 300, 720, false);
	public static Fluid fluidDiesel = new FluidMA("diesel", 0, 820, 300, 820, false);
	public static Fluid fluidLiquifiedPetroleumGas = new FluidMA("liquifiedPetroleumGas", 0, 720, 300, 750, false);
	public static Fluid fluidKerosene = new FluidMA("kerosene", 0, 494, 300, 494, false);
	
	public static void registerFluids() {
		fluidGasoline.setUnlocalizedName(BlockInfo.FLUID_GASOLINE_UNLOCALIZED_NAME);
		FluidRegistry.registerFluid(fluidGasoline);
		fluidDiesel.setUnlocalizedName(BlockInfo.FLUID_DIESEL_UNLOCALIZED_NAME);
		FluidRegistry.registerFluid(fluidDiesel);
		fluidLiquifiedPetroleumGas.setUnlocalizedName(BlockInfo.FLUID_LIQUIFIED_PETROLEUM_GAS_UNLOCALIZED_NAME);
		FluidRegistry.registerFluid(fluidLiquifiedPetroleumGas);
		fluidKerosene.setUnlocalizedName(BlockInfo.FLUID_KEROSENE_UNLOCALIZED_NAME);
		FluidRegistry.registerFluid(fluidKerosene);
	}
}
