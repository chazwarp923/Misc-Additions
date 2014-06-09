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
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.Reference;
import com.chazwarp.miscadditions.tileentity.TileEntityMaglevPower;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMaglevPower extends BlockContainer {

	private static int inputSide;
	
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

	@SideOnly(Side.CLIENT)
	private Icon topIcon;
	@SideOnly(Side.CLIENT)
	private Icon botIcon;
	@SideOnly(Side.CLIENT)
	private Icon inputIcon;
	@SideOnly(Side.CLIENT)
	private Icon sideIcon;
	
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
		topIcon = register.registerIcon("minecraft" + ":" + "anvil_base");
		sideIcon = register.registerIcon("minecraft" + ":" + "anvil_base");
		inputIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + "maglevPowerActive");
		botIcon = topIcon;
	}
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int meta) {
    	if (side == 0) {
    		return botIcon;
    	}
    	else if (side == 1) {
    		return topIcon;
    	}
    	else if (side == inputSide) {
    		return inputIcon;
    	}
    	else{
    		return sideIcon;
    	}
    }
    
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                b0 = 4;
            }

            inputSide = b0;
        }
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
    	super.onBlockAdded(world, x, y, z);
    	this.setDefaultDirection(world, x, y, z);
    }
}
