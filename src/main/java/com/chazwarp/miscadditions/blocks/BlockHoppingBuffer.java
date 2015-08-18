/**
@author Chaz Kerby
 */
package com.chazwarp.miscadditions.blocks;

import com.chazwarp.miscadditions.MiscAdditions;
import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.blocks.tileentity.TileEntityHoppingBuffer;
import com.chazwarp.miscadditions.lib.BlockInfo;
import com.chazwarp.miscadditions.lib.Reference;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHoppingBuffer extends BlockContainer {

	public BlockHoppingBuffer() {
		super(Material.iron);
		setCreativeTab(MiscTab.tab);
		setHardness(1.5F);
		setStepSound(Block.soundTypeMetal);
		setBlockName(BlockInfo.BLOCK_HOPPING_BUFFER_UNLOCALIZED_NAME);
		setBlockTextureName(Reference.RESOURCE_PREFIX + BlockInfo.BLOCK_HOPPING_BUFFER_UNLOCALIZED_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int integer) {
		return new TileEntityHoppingBuffer();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, MiscAdditions.instance, 0, world, x, y, z);
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityHoppingBuffer) {
			TileEntityHoppingBuffer hopBuf = (TileEntityHoppingBuffer)te;

			for (int i = 0; i < hopBuf.getSizeInventory(); i++) {
				ItemStack stack = hopBuf.getStackInSlotOnClosing(i);

				if (stack != null) {
					float spawnX = x + world.rand.nextFloat();
					float spawnY = y + world.rand.nextFloat();
					float spawnZ = z + world.rand.nextFloat();

					EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);

					float mult = 0.05F;

					droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;
					droppedItem.motionY = (4 + world.rand.nextFloat()) * mult;
					droppedItem.motionZ = (-0.5F + world.rand.nextFloat()) * mult;

					world.spawnEntityInWorld(droppedItem);
				}
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}
}
