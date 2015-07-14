/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTower;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.Reference;

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
	public TileEntity createNewTileEntity(World world, int Integer) {
		return new TileEntityFractionationTower();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer ep, int side, float par7, float par8, float par9) {
		ItemStack equipped = ep.getCurrentEquippedItem();

		if(equipped == null) {
			if(world.getTileEntity(x, y, z) instanceof TileEntityFractionationTower) {
				TileEntityFractionationTower tile = (TileEntityFractionationTower)world.getTileEntity(x, y, z);
				if(tile.hasBlocksAround(x, y, z)) {
					tile.setIsMasterBlock();
					System.out.println("Setup Fractionation Tower MultiBlock");
				}
			}
			return true;
		} 
		else {
			return false;
		}
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
