/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.Reference;

public class BlockMA extends Block {

	public static String SWORD = "sword";
	public static String AXE = "axe";
	public static String PICKAXE = "pickaxe";
	public static String SHOVEL = "spade";

	public static int WOOD = 0;
	public static int STONE = 1;
	public static int IRON = 2;
	public static int DIAMOND = 3;

	protected BlockMA(Material mat, float hardness, SoundType sound,
			String unlocalizedName, String harvestTool, int harvestLevel) {
		super(mat);

		setCreativeTab(MiscTab.tab);
		setHardness(hardness);
		setStepSound(sound);
		setBlockName(unlocalizedName);
		setBlockTextureName(Reference.RESOURCE_PREFIX + unlocalizedName);
		setHarvestLevel(harvestTool, harvestLevel);
	}
}