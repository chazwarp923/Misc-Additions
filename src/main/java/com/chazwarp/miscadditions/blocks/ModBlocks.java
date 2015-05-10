/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

import com.chazwarp.miscadditions.fluid.Fluids;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockDecorative;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFluidDiesel;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFluidGasoline;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFluidKerosene;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFluidPropane;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockMaglevPower;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockMobTeleporter;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.tileentity.TileEntityMaglevPower;
import com.chazwarp.miscadditions.tileentity.TileEntityMobTeleporter;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block decorative = new BlockMA(Material.wood, 2F, Block.soundTypeWood, BlockInfo.DECORATIVE_UNLOCALIZED_NAME, BlockMA.AXE, BlockMA.STONE);
	
	public static Block maglevPower = new BlockMaglevPower();
	public static Block mobTele = new BlockMobTeleporter();
	public static BlockFluidClassic fluidGasoline = new BlockFluidMA(Fluids.fluidGasoline, Material.water, BlockInfo.FLUID_GASOLINE_UNLOCALIZED_NAME, BlockInfo.FLUID_GASOLINE_KEY);
	public static BlockFluidClassic fluidDiesel = new BlockFluidMA(Fluids.fluidDiesel, Material.water, BlockInfo.FLUID_DIESEL_UNLOCALIZED_NAME, BlockInfo.FLUID_DIESEL_KEY);
	public static BlockFluidClassic fluidPropane = new BlockFluidMA(Fluids.fluidPropane, Material.water, BlockInfo.FLUID_PROPANE_UNLOCALIZED_NAME, BlockInfo.FLUID_PROPANE_KEY);
	public static BlockFluidClassic fluidKerosene = new BlockFluidMA(Fluids.fluidKerosene, Material.water, BlockInfo.FLUID_KEROSENE_UNLOCALIZED_NAME, BlockInfo.FLUID_KEROSENE_KEY);
	
	public static void registerBlocks() {
		GameRegistry.registerBlock(decorative, ItemBlockDecorative.class, BlockInfo.DECORATIVE_KEY);
		GameRegistry.registerBlock(maglevPower, ItemBlockMaglevPower.class, BlockInfo.MAGLEV_POWER_KEY);
		GameRegistry.registerBlock(mobTele, ItemBlockMobTeleporter.class, BlockInfo.MOB_TELEPORTER_KEY);
		GameRegistry.registerBlock(fluidGasoline, ItemBlockFluidGasoline.class, BlockInfo.FLUID_GASOLINE_KEY);
		GameRegistry.registerBlock(fluidDiesel, ItemBlockFluidDiesel.class, BlockInfo.FLUID_DIESEL_KEY);
		GameRegistry.registerBlock(fluidPropane, ItemBlockFluidPropane.class, BlockInfo.FLUID_PROPANE_KEY);
		GameRegistry.registerBlock(fluidKerosene, ItemBlockFluidKerosene.class, BlockInfo.FLUID_KEROSENE_KEY);
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityMaglevPower.class, BlockInfo.MAGLEV_POWER_KEY);
		GameRegistry.registerTileEntity(TileEntityMobTeleporter.class, BlockInfo.MOB_TELEPORTER_KEY);
	}
}
