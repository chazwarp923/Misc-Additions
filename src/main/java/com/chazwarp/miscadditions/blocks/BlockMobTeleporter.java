/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.Reference;
import com.chazwarp.miscadditions.tileentity.TileEntityMobTeleporter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMobTeleporter extends BlockContainer {

	public BlockMobTeleporter(int id) {
		super(id, Material.iron);

		setCreativeTab(MiscTab.tab);
		setHardness(1.5F);
		setUnlocalizedName(BlockInfo.MOB_TELEPORTER_UNLOCALIZED_NAME);
		setStepSound(Block.soundMetalFootstep);
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityMobTeleporter();
	}
	
    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
    	TileEntity te;
        if (!world.isRemote)
        {
        	te = world.getBlockTileEntity(x, y-1, z);
        	if(te instanceof TileEntityMobTeleporter) {        		
        		((TileEntityMobTeleporter) te).teleportEntity(x+0.5, y, z+0.5, entity);
        	}
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + "mobTele");
		
    }
}
