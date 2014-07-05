/**
*@author Chaz Kerby
*/
package com.chazwarp.miscadditions.blocks;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.BlockInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockMiscWall extends BlockWall {

	public BlockMiscWall(int id, Block block) {
		super(id, block);
		
		setCreativeTab(MiscTab.tab);
		setHardness(1.0F);
		setUnlocalizedName(BlockInfo.WALL_UNLOCALIZED_NAME);
		setStepSound(Block.soundWoodFootstep);
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		Icon texture = Block.wood.getBlockTextureFromSide(side);
		if(meta == 0) texture = Block.wood.getBlockTextureFromSide(2);
		if(meta == 1) texture = Blocks.mossyLog.getBlockTextureFromSide(side);
		if(meta == 2) texture = Block.netherBrick.getBlockTextureFromSide(side);
		
		return texture;
	}

	@Override
    public boolean canConnectWallTo(IBlockAccess par1IBlockAccess, int x, int y, int z)
    {
        int l = par1IBlockAccess.getBlockId(x, y, z);

        if (l != this.blockID && l != Block.fenceGate.blockID)
        {
            Block block = Block.blocksList[l];
            return block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock() ? block.blockMaterial != Material.pumpkin : false;
        }
        else
        {
            return true;
        }
    }
}
