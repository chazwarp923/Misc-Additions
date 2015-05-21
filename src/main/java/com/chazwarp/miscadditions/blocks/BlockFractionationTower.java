/**
@author Chaz Kerby
*/

package com.chazwarp.miscadditions.blocks;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockFractionationTower extends Block {

	protected BlockFractionationTower() {
		super(Material.iron);
		setCreativeTab(MiscTab.tab);
		setHardness(2F);
		setStepSound(Block.soundTypeMetal);
		setBlockName(BlockInfo.BLOCK_FRACTIONATION_TOWER_UNLOCALIZED_NAME);
		setBlockTextureName(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_FRACTIONATION_TOWER_UNLOCALIZED_NAME);
	}
}
