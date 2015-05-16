/**
@author Chaz Kerby
*/

package com.chazwarp.miscadditions.blocks;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFractionationTower extends BlockContainer {

	protected BlockFractionationTower() {
		super(Material.iron);
		setCreativeTab(MiscTab.tab);
		setHardness(2F);
		setStepSound(Block.soundTypeMetal);
		setBlockName(BlockInfo.BLOCK_FRACTIONATION_TOWER_UNLOCALIZED_NAME);
		setBlockTextureName(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_FRACTIONATION_TOWER_UNLOCALIZED_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int integer) {
		return null;
	}
}
