/**
@author Chaz Kerby
*/

package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidMA extends BlockFluidClassic {

	protected String fluidName;
	
	@SideOnly(Side.CLIENT)
	protected static IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	protected static IIcon flowingIcon;
	
	public BlockFluidMA(Fluid fluid, Material material, String unlocalizedName, String key) {
		super(fluid, material);
		setCreativeTab(MiscTab.tab);
		setBlockName(unlocalizedName);
		fluidName = key;
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return (side == 0 || side == 1) ? stillIcon : flowingIcon;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		stillIcon = register.registerIcon(Reference.RESOURCE_PREFIX + fluidName + "Still");
		flowingIcon = register.registerIcon(Reference.RESOURCE_PREFIX + fluidName + "Flowing");
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if(world.getBlock(x, y, z).getMaterial().isLiquid()) {
			return false;
		}
		else {
			return super.canDisplace(world, x, y, z);
		}
	}
	
	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if(world.getBlock(x, y, z).getMaterial().isLiquid()) {
			return false;
		}
		else {
			return super.displaceIfPossible(world, x, y, z);
		}
	}
}
