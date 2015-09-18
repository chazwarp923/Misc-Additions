/**
 *@author Chaz Kerby
 */
package com.chazwarp.miscadditions.blocks;

import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTower;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTowerFluidIO;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTowerPowerInput;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityHoppingBuffer;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityMaglevPower;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityMobTeleporter;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityPresent;
import com.chazwarp.miscadditions.fluid.Fluids;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockDecorative;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFluidDiesel;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFluidGasoline;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFluidKerosene;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFluidLiquifiedPetroleumGas;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFractionationTower;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFractionationTowerFluidIO;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockFractionationTowerPowerInput;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockHoppingBuffer;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockMaglevPower;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockMobTeleporter;
import com.chazwarp.miscadditions.items.itemblocks.ItemBlockPresent;
import com.chazwarp.miscadditions.lib.BlockInfo;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static Block decorative = new BlockMA(Material.wood, 2F, Block.soundTypeWood, BlockInfo.BLOCK_DECORATIVE_UNLOCALIZED_NAME, BlockMA.AXE, BlockMA.STONE);
	public static Block maglevPower = new BlockMaglevPower();
	public static Block mobTele = new BlockMobTeleporter();
	public static BlockFluidMA fluidGasoline = new BlockFluidMA(Fluids.fluidGasoline, Material.water, BlockInfo.FLUID_GASOLINE_UNLOCALIZED_NAME, BlockInfo.FLUID_GASOLINE_KEY, true);
	public static BlockFluidMA fluidDiesel = new BlockFluidMA(Fluids.fluidDiesel, Material.water, BlockInfo.FLUID_DIESEL_UNLOCALIZED_NAME, BlockInfo.FLUID_DIESEL_KEY, true);
	public static BlockFluidMA fluidLiquifiedPetroleumGas = new BlockFluidMA(Fluids.fluidLiquifiedPetroleumGas, Material.water, BlockInfo.FLUID_LIQUIFIED_PETROLEUM_GAS_UNLOCALIZED_NAME, BlockInfo.FLUID_LIQUIFIED_PETROLEUM_GAS_KEY, true);
	public static BlockFluidMA fluidKerosene = new BlockFluidMA(Fluids.fluidKerosene, Material.water, BlockInfo.FLUID_KEROSENE_UNLOCALIZED_NAME, BlockInfo.FLUID_KEROSENE_KEY, true);
	public static Block fractionationTower = new BlockFractionationTower();
	public static Block fractionationTowerFluidIO = new BlockFractionationTowerFluidIO();
	public static Block fractionationTowerPowerInput = new BlockFractionationTowerPowerInput();
	public static Block hoppingBuffer = new BlockHoppingBuffer();
	public static Block present = new BlockPresent();

	public static void registerBlocks() {
		GameRegistry.registerBlock(decorative, ItemBlockDecorative.class, BlockInfo.BLOCK_DECORATIVE_KEY);
		GameRegistry.registerBlock(maglevPower, ItemBlockMaglevPower.class, BlockInfo.BLOCK_MAGLEV_POWER_INPUT_KEY);
		GameRegistry.registerBlock(mobTele, ItemBlockMobTeleporter.class, BlockInfo.BLOCK_MOB_TELEPORTER_KEY);
		GameRegistry.registerBlock(fluidGasoline, ItemBlockFluidGasoline.class, BlockInfo.FLUID_GASOLINE_KEY);
		Fluids.fluidGasoline.setBlock(fluidGasoline);
		GameRegistry.registerBlock(fluidDiesel, ItemBlockFluidDiesel.class, BlockInfo.FLUID_DIESEL_KEY);
		Fluids.fluidDiesel.setBlock(fluidDiesel);
		GameRegistry.registerBlock(fluidLiquifiedPetroleumGas, ItemBlockFluidLiquifiedPetroleumGas.class, BlockInfo.FLUID_LIQUIFIED_PETROLEUM_GAS_KEY);
		Fluids.fluidLiquifiedPetroleumGas.setBlock(fluidLiquifiedPetroleumGas);
		GameRegistry.registerBlock(fluidKerosene, ItemBlockFluidKerosene.class, BlockInfo.FLUID_KEROSENE_KEY);
		Fluids.fluidKerosene.setBlock(fluidKerosene);
		GameRegistry.registerBlock(fractionationTower, ItemBlockFractionationTower.class, BlockInfo.BLOCK_FRACTIONATION_TOWER_KEY);
		GameRegistry.registerBlock(fractionationTowerFluidIO, ItemBlockFractionationTowerFluidIO.class, BlockInfo.BLOCK_FRACTIONATION_TOWER_FLUID_IO_KEY);
		GameRegistry.registerBlock(fractionationTowerPowerInput, ItemBlockFractionationTowerPowerInput.class, BlockInfo.BLOCK_FRACTIONATION_TOWER_POWER_INPUT_KEY);
		GameRegistry.registerBlock(hoppingBuffer, ItemBlockHoppingBuffer.class, BlockInfo.BLOCK_HOPPING_BUFFER_KEY);
		GameRegistry.registerBlock(present, ItemBlockPresent.class, BlockInfo.BLOCK_PRESENT_KEY);
	}

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityMaglevPower.class, BlockInfo.BLOCK_MAGLEV_POWER_INPUT_KEY);
		GameRegistry.registerTileEntity(TileEntityMobTeleporter.class, BlockInfo.BLOCK_MOB_TELEPORTER_KEY);
		GameRegistry.registerTileEntity(TileEntityFractionationTower.class, BlockInfo.BLOCK_FRACTIONATION_TOWER_KEY);
		GameRegistry.registerTileEntity(TileEntityFractionationTowerFluidIO.class, BlockInfo.BLOCK_FRACTIONATION_TOWER_FLUID_IO_KEY);
		GameRegistry.registerTileEntity(TileEntityFractionationTowerPowerInput.class, BlockInfo.BLOCK_FRACTIONATION_TOWER_POWER_INPUT_KEY);
		GameRegistry.registerTileEntity(TileEntityHoppingBuffer.class, BlockInfo.BLOCK_HOPPING_BUFFER_KEY);
		GameRegistry.registerTileEntity(TileEntityPresent.class, BlockInfo.BLOCK_PRESENT_KEY);
	}
}
