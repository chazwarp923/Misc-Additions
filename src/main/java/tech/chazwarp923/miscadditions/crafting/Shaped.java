/**
 *@author Chaz Kerby
 */
package tech.chazwarp923.miscadditions.crafting;

import java.util.Calendar;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tech.chazwarp923.miscadditions.blocks.MABlocks;

public class Shaped {

	public static void addCrafting() {

		int month = Calendar.getInstance().get(Calendar.MONTH);
		
		//Hanging Ladder
		GameRegistry.addShapedRecipe(new ItemStack(MABlocks.hangingLadder), "L L", "LSL", "L L", 'L', Blocks.LOG, 'S', Items.STRING);
		
		/*GameRegistry.addShapedRecipe(new ItemStack(MAItems.axeLumberIron), new Object[] { "BI", "IS", " S", 'B', Blocks.IRON_BLOCK, 'I', Items.IRON_INGOT, 'S', Items.STICK });
		GameRegistry.addShapedRecipe(new ItemStack(MAItems.axeLumberDiamond), new Object[] { "BD", "DS", " S", 'B', Blocks.DIAMOND_BLOCK, 'D', Items.DIAMOND, 'S', Items.STICK });
		
		if(month == 11) {
			GameRegistry.addShapedRecipe(new ItemStack(MABlocks.present), new Object[] { "O", "C", 'O', Items.COOKIE, 'C', Blocks.CHEST});
		}*/
	}
}
