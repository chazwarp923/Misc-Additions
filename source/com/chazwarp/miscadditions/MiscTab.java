/**
@author Chaz Kerby
*/
package com.chazwarp.miscadditions;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.chazwarp.miscadditions.items.Items;

public class MiscTab extends CreativeTabs
{
    public static MiscTab tab = new MiscTab();
    static boolean hasInit;
    static int icon;

    public MiscTab()
    {
        super("miscTab");
    }

    public static void init (int index)
    {
        if (!hasInit)
        {
            hasInit = true;
            icon = index;
        }
    }

    public int getTabIconItemIndex ()
    {
        return icon;
        
    	}
    @Override
    public ItemStack getIconItemStack() {
    	return new ItemStack(Items.axeLumber, 1, 0);
    }
}
