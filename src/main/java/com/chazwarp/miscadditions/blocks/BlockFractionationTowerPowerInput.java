/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTower;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTowerPowerInput;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.Reference;

public class BlockFractionationTowerPowerInput extends BlockContainer {

	protected BlockFractionationTowerPowerInput() {
		super(Material.iron);
		setCreativeTab(MiscTab.tab);
		setHardness(2F);
		setStepSound(Block.soundTypeMetal);
		setBlockName(BlockInfo.BLOCK_FRACTIONATION_TOWER_POWER_INPUT_UNLOCALIZED_NAME);
		setBlockTextureName(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_FRACTIONATION_TOWER_POWER_INPUT_UNLOCALIZED_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int Integer) {
		return new TileEntityFractionationTowerPowerInput();
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		TileEntityFractionationTower tile = (TileEntityFractionationTower)world.getTileEntity(x, y, z);
		
		if(tile != null && tile instanceof TileEntityFractionationTower) {
			if(tile.hasMasterBlock()) {
				TileEntityFractionationTower masterTile = (TileEntityFractionationTower)world.getTileEntity(tile.masterX, tile.masterY, tile.masterZ);
				masterTile.resetStructure();
			}
		}
	}
}
