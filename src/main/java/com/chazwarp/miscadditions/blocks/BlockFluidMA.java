/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.blocks;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidMA extends BlockFluidClassic {

	protected String fluidName;
	private boolean flammable;
	private Fluid fluid;

	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon flowingIcon;

	public BlockFluidMA(Fluid fluid, Material material, String unlocalizedName, String key, boolean flammable) {
		super(fluid, material);
		setCreativeTab(MiscTab.tab);
		setBlockName(unlocalizedName);
		fluidName = unlocalizedName;
		this.flammable = flammable;
		this.fluid = fluid;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return (side == 0 || side == 1) ? stillIcon : flowingIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		stillIcon = register.registerIcon(Reference.RESOURCE_PREFIX + fluidName + "_still");
		flowingIcon = register.registerIcon(Reference.RESOURCE_PREFIX + fluidName + "_flow");
		fluid.setStillIcon(stillIcon);
		fluid.setFlowingIcon(flowingIcon);
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

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return flammable;
	}
}
