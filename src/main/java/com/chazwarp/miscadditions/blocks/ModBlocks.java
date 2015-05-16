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
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFluidLiquifiedPetroleumGas;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFractionationTowerBlock;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockMaglevPower;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockMobTeleporter;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.tileentity.TileEntityMaglevPower;
import com.chazwarp.miscadditions.tileentity.TileEntityMobTeleporter;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block decorative = new BlockMA(Material.wood, 2F, Block.soundTypeWood, BlockInfo.BLOCK_DECORATIVE_UNLOCALIZED_NAME, BlockMA.AXE, BlockMA.STONE);
	public static Block maglevPower = new BlockMaglevPower();
	public static Block mobTele = new BlockMobTeleporter();
	public static BlockFluidClassic fluidGasoline = new BlockFluidMA(Fluids.fluidGasoline, Material.water, BlockInfo.FLUID_GASOLINE_UNLOCALIZED_NAME, BlockInfo.FLUID_GASOLINE_KEY);
	public static BlockFluidClassic fluidDiesel = new BlockFluidMA(Fluids.fluidDiesel, Material.water, BlockInfo.FLUID_DIESEL_UNLOCALIZED_NAME, BlockInfo.FLUID_DIESEL_KEY);
	public static BlockFluidClassic fluidLiquifiedPetroleumGas = new BlockFluidMA(Fluids.fluidLiquifiedPetroleumGas, Material.water, BlockInfo.FLUID_LIQUIFIED_PETROLEUM_GAS_UNLOCALIZED_NAME, BlockInfo.FLUID_LIQUIFIED_PETROLEUM_GAS_KEY);
	public static BlockFluidClassic fluidKerosene = new BlockFluidMA(Fluids.fluidKerosene, Material.water, BlockInfo.FLUID_KEROSENE_UNLOCALIZED_NAME, BlockInfo.FLUID_KEROSENE_KEY);
	public static Block fractionationTower = new BlockFractionationTower();
	
	public static void registerBlocks() {
		GameRegistry.registerBlock(decorative, ItemBlockDecorative.class, BlockInfo.BLOCK_DECORATIVE_KEY);
		GameRegistry.registerBlock(maglevPower, ItemBlockMaglevPower.class, BlockInfo.BLOCK_MAGLEV_POWER_INPUT_KEY);
		GameRegistry.registerBlock(mobTele, ItemBlockMobTeleporter.class, BlockInfo.BLOCK_MOB_TELEPORTER_KEY);
		GameRegistry.registerBlock(fluidGasoline, ItemBlockFluidGasoline.class, BlockInfo.FLUID_GASOLINE_KEY);
		GameRegistry.registerBlock(fluidDiesel, ItemBlockFluidDiesel.class, BlockInfo.FLUID_DIESEL_KEY);
		GameRegistry.registerBlock(fluidLiquifiedPetroleumGas, ItemBlockFluidLiquifiedPetroleumGas.class, BlockInfo.FLUID_LIQUIFIED_PETROLEUM_GAS_KEY);
		GameRegistry.registerBlock(fluidKerosene, ItemBlockFluidKerosene.class, BlockInfo.FLUID_KEROSENE_KEY);
		GameRegistry.registerBlock(fractionationTower, ItemBlockFractionationTowerBlock.class, BlockInfo.BLOCK_FRACTIONATION_TOWER_KEY);
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityMaglevPower.class, BlockInfo.BLOCK_MAGLEV_POWER_INPUT_KEY);
		GameRegistry.registerTileEntity(TileEntityMobTeleporter.class, BlockInfo.BLOCK_MOB_TELEPORTER_KEY);
	}
}
