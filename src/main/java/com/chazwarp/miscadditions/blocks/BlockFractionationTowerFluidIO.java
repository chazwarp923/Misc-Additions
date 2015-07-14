/**
@author Chaz Kerby
 */

package com.chazwarp.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import buildcraft.api.tools.IToolWrench;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTower;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityFractionationTowerFluidIO;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFractionationTowerFluidIO extends BlockContainer {

	protected IIcon[] textures;

	protected BlockFractionationTowerFluidIO() {
		super(Material.iron);
		setCreativeTab(MiscTab.tab);
		setHardness(2F);
		setStepSound(Block.soundTypeMetal);
		setBlockName(BlockInfo.BLOCK_FRACTIONATION_TOWER_FLUID_IO_UNLOCALIZED_NAME);
		setBlockTextureName(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_FRACTIONATION_TOWER_FLUID_IO_UNLOCALIZED_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int integer) {
		return new TileEntityFractionationTowerFluidIO();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegistry) {
		textures = new IIcon[5];

		textures[0] = iconRegistry.registerIcon(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_FRACTIONATION_TOWER_FLUID_IO_UNLOCALIZED_NAME + "_Oil");
		textures[1] = iconRegistry.registerIcon(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_FRACTIONATION_TOWER_FLUID_IO_UNLOCALIZED_NAME + "_Gasoline");
		textures[2] = iconRegistry.registerIcon(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_FRACTIONATION_TOWER_FLUID_IO_UNLOCALIZED_NAME + "_Diesel");
		textures[3] = iconRegistry.registerIcon(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_FRACTIONATION_TOWER_FLUID_IO_UNLOCALIZED_NAME + "_LiquifiedPetroleumGas");
		textures[4] = iconRegistry.registerIcon(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_FRACTIONATION_TOWER_FLUID_IO_UNLOCALIZED_NAME + "_Kerosene");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		TileEntityFractionationTowerFluidIO te = (TileEntityFractionationTowerFluidIO) world
				.getTileEntity(x, y, z);

		switch (te.getMode()) {
		case 0:
			return textures[0];
		case 1:
			return textures[1];
		case 2:
			return textures[2];
		case 3:
			return textures[3];
		case 4:
			return textures[4];
		default:
			return textures[0];
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer ep, int side, float par7, float par8, float par9) {
		Item equipped = ep.getCurrentEquippedItem() != null ? ep.getCurrentEquippedItem().getItem() : null;

		if (equipped instanceof IToolWrench && ((IToolWrench)equipped).canWrench(ep, x, y, z)) {
			if (world.getTileEntity(x, y, z) instanceof TileEntityFractionationTowerFluidIO) {
				TileEntityFractionationTowerFluidIO te = (TileEntityFractionationTowerFluidIO) world.getTileEntity(x, y, z);
				te.cycleMode();
			}
			return true;
		}
		else if(equipped == null) {
			if(world.getTileEntity(x, y, z) instanceof TileEntityFractionationTower) {
				TileEntityFractionationTower tile = (TileEntityFractionationTower)world.getTileEntity(x, y, z);
				if(tile.hasBlocksAround(x, y, z)) {
					tile.setIsMasterBlock();
					System.out.println("[MiscAdd] Setup Fractionation Tower MultiBlock");
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
