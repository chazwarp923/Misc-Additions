/**
 *@author Chaz Kerby
 */
package tech.chazwarp923.miscadditions.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MABlocks {

	//public static final Block present = new BlockPresent();
	public static final BlockHangingLadder hangingLadder = new BlockHangingLadder(Material.WOOD, 1f, SoundType.WOOD, "hangingLadder", HarvestType.AXE, HarvestLevel.WOOD);

	@SideOnly(Side.CLIENT)
	public static void preInitClient() {
		hangingLadder.initModel();
	}
}
