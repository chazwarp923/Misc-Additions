/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.BlockInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDecorative extends Block {
	
	public BlockDecorative(int id) {
		super(id, Material.wood);
		
		setCreativeTab(MiscTab.tab);
		setHardness(1.5F);
		setUnlocalizedName(BlockInfo.DECORATIVE_UNLOCALIZED_NAME);
		setStepSound(Block.soundWoodFootstep);
	}
	
	@SideOnly(Side.CLIENT)
	private Icon topIcon;
	@SideOnly(Side.CLIENT)
	private Icon botIcon;
	@SideOnly(Side.CLIENT)
	private Icon sideIcon;
	
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
		//topIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + Textures.INVENTORY_CHEST_TOP);
		//sideIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + Textures.INVENTORY_CHEST_FRONT);
		//botIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + Textures.INVENTORY_CHEST_TOP);
	}
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int meta) {
    	if (side == 0) {
    		return botIcon;
    	}else if (side == 1) {
    		return topIcon;
    	}else{
    		return sideIcon;
    	}
    }
}
