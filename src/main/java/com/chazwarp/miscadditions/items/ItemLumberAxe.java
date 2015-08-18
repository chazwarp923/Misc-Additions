package com.chazwarp.miscadditions.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.ItemInfo;
import com.chazwarp.miscadditions.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLumberAxe extends ItemAxe {

	ToolMaterial ETM;

	public ItemLumberAxe(ToolMaterial mat) {
		super(mat);
		setCreativeTab(MiscTab.tab);
		setMaxStackSize(1);
		setUnlocalizedName(ItemInfo.LUMBER_AXE_UNLOCALIZED_NAME);
		ETM = mat;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		if (ETM == ToolMaterial.IRON)
			this.itemIcon = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "iron" + ItemInfo.LUMBER_AXE_UNLOCALIZED_NAME);
		else
			this.itemIcon = iconRegister.registerIcon(Reference.RESOURCE_PREFIX + "diamond" + ItemInfo.LUMBER_AXE_UNLOCALIZED_NAME);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block,
			int x, int y, int z, EntityLivingBase ELB) {
		if (ELB.isSneaking() == false) {
			for (int i = 1; i < 16; i++) {
				if (world.blockExists(x, y + i, z)) {
					if (world.getBlock(x, y, z).getMaterial() == Material.wood) {
						if (world.getBlock(x, y + i, z) == block) {
							world.setBlockToAir(x, y + i, z);

							ArrayList<ItemStack> stackArrayList = block
									.getDrops(world, x, y, z,
											world.getBlockMetadata(x, y, z), 3);

							for (int i1 = 0; i1 < stackArrayList.size(); i1++) {
								world.spawnEntityInWorld(new EntityItem(world,
										x, y, z, stackArrayList.get(i1)));
							}
						}
					}
				}
			}
		}
		stack.damageItem(1, ELB);

		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer ep, List par3List,
			boolean bool) {
		super.addInformation(stack, ep, par3List, bool);

		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			par3List.add("Hold " + EnumChatFormatting.RED
					+ EnumChatFormatting.ITALIC + "Shift"
					+ EnumChatFormatting.GRAY + " For Details");
		} else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			par3List.add(EnumChatFormatting.ITALIC
					+ "Deforest The World! -Chazwarp923");
		}
	}

	@Override
	public int getItemEnchantability() {
		return Items.iron_axe.getItemEnchantability();
	}
}
