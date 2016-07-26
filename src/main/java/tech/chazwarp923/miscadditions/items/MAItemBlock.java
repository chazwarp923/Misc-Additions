/**
@author Chaz Kerby
 */

package tech.chazwarp923.miscadditions.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class MAItemBlock extends ItemBlock {

	public MAItemBlock(Block block) {
		super(block);
		setHasSubtypes(true);
	}
}