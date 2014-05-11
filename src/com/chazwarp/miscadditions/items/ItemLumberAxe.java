package com.chazwarp.miscadditions.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.chazwarp.miscadditions.MiscTab;
import com.chazwarp.miscadditions.lib.ItemInfo;
import com.chazwarp.miscadditions.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLumberAxe extends ItemAxe{

	public ItemLumberAxe(int id) {
		super(id, EnumToolMaterial.IRON);
		setCreativeTab(MiscTab.tab);
		setMaxStackSize(1);
        setUnlocalizedName(ItemInfo.LUMBER_AXE_UNLOCALIZED_NAME);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

    	this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + ItemInfo.LUMBER_AXE_UNLOCALIZED_NAME);
    }

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, int id, int x, int y, int z, EntityLivingBase ELB) {
		for(int i=0; i < 16; i++) {
			if(world.blockExists(x, y + i, z)) {
				if(Block.blocksList[id].blockMaterial == Material.wood) {
					if(world.getBlockId(x, y + i, z) == id)
						world.destroyBlock(x, y + i, z, true);
				}
			}
		}
		stack.damageItem(1, ELB);
		return true;
	}
	
	@Override
	public int getItemEnchantability()
	{
		return Item.axeIron.getItemEnchantability();
	}
}
