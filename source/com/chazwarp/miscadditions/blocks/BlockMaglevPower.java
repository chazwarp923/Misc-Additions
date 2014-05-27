/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.tileentity.TileEntityMaglevPower;

public class BlockMaglevPower extends BlockContainer {

	public BlockMaglevPower(int id) {
		super(id, Material.iron);

		setCreativeTab(MiscTab.tab);
		setHardness(1.5F);
		setUnlocalizedName(BlockInfo.MAGLEV_POWER_UNLOCALIZED_NAME);
		setStepSound(Block.soundMetalFootstep);
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityMaglevPower();
	}

}
