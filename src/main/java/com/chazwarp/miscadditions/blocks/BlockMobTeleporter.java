/**
 *@author Chaz Kerby
*/
package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityMobTeleporter;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.Reference;

public class BlockMobTeleporter extends BlockContainer {

	public BlockMobTeleporter() {
		super(Material.iron);
		setCreativeTab(MiscTab.tab);
		setHardness(2F);
		setStepSound(Block.soundTypeMetal);
		setBlockName(BlockInfo.BLOCK_MOB_TELEPORTER_UNLOCALIZED_NAME);
		setBlockTextureName(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_MOB_TELEPORTER_UNLOCALIZED_NAME);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int Integer) {
		return new TileEntityMobTeleporter();
	}
    
    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float f) {
    	
    	TileEntity te;
        if( entity instanceof EntityLivingBase) {
        	te = world.getTileEntity(x, y, z);
        	((TileEntityMobTeleporter)te).teleportEntity(x, y, z, (EntityLivingBase)entity);
        }
    }
}
