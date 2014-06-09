/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.blocks;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockMossyLog extends Block{

	public BlockMossyLog(int id, Material material) {
		super(id, material);

		setCreativeTab(MiscTab.tab);
		setHardness(1.5F);
		setUnlocalizedName(BlockInfo.MOSSY_LOG_UNLOCALIZED_NAME);
		setStepSound(Block.soundWoodFootstep);
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
		topIcon = register.registerIcon("minecraft" + ":" + "grass_top");
		sideIcon = register.registerIcon(Reference.TEXTURE_LOC + ":" + "mossyLogSide");
		botIcon = Block.wood.getBlockTextureFromSide(0);
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
    	else{
    		return sideIcon;
    	}
    }
}
