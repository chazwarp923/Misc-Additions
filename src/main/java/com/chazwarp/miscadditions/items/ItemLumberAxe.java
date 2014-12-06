package com.chazwarp.miscadditions.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.ItemInfo;
import com.chazwarp.miscadditions.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLumberAxe extends ItemAxe{

	EnumToolMaterial ETM;
	
	public ItemLumberAxe(int id, EnumToolMaterial mat) {
		super(id, mat);
		setCreativeTab(MiscTab.tab);
		setMaxStackSize(1);
        setUnlocalizedName(ItemInfo.LUMBER_AXE_UNLOCALIZED_NAME);
        ETM = mat;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
		if(ETM == EnumToolMaterial.IRON)
			this.itemIcon = iconRegister.registerIcon(Reference.TEXTURE_LOC + ":" + "iron" + ItemInfo.LUMBER_AXE_UNLOCALIZED_NAME);
		else
			this.itemIcon = iconRegister.registerIcon(Reference.TEXTURE_LOC + ":" + "diamond" + ItemInfo.LUMBER_AXE_UNLOCALIZED_NAME);
    }

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, int id, int x, int y, int z, EntityLivingBase ELB) {
		if(ELB.isSneaking() == false) {
			for(int i=1; i < 16; i++) {
				if(world.blockExists(x, y + i, z)) {
					if(Block.blocksList[id].blockMaterial == Material.wood) {
						if(world.getBlockId(x, y + i, z) == id)
							world.destroyBlock(x, y + i, z, true);
					}
				}
			}
		}
		stack.damageItem(1, ELB);
		
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer ep, List par3List, boolean bool) {
		super.addInformation(stack, ep, par3List, bool);
		
		if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			par3List.add("Hold " + EnumChatFormatting.RED.toString() + "Shift" + EnumChatFormatting.GRAY + " For More Information");
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			par3List.add(EnumChatFormatting.ITALIC.toString() + "Deforest The World! -Chazwarp923");
		}
	}
	
	@Override
	public int getItemEnchantability()
	{
		return Item.axeIron.getItemEnchantability();
	}
}
