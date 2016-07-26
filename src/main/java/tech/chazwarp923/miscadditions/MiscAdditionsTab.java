/**
@author Chaz Kerby
 */
package tech.chazwarp923.miscadditions;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import tech.chazwarp923.miscadditions.lib.Reference;

public class MiscAdditionsTab extends CreativeTabs {
	public static final MiscAdditionsTab tab = new MiscAdditionsTab();

	public MiscAdditionsTab() {
		super(Reference.MOD_ID);
		setBackgroundImageName("item_search.png");
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}

	@Override
	public Item getTabIconItem() {
		return Items.APPLE;
	}
}
