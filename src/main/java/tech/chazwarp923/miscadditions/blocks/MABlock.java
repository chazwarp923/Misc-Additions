/**
@author Chaz Kerby
 */

package tech.chazwarp923.miscadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.chazwarp923.miscadditions.MiscAdditionsTab;
import tech.chazwarp923.miscadditions.items.MAItemBlock;

public class MABlock extends Block {

	public MABlock(Material mat, float hardness, SoundType sound, String unlocalizedName, HarvestType harvestTool, HarvestLevel harvestLevel) {
		super(mat);

		setCreativeTab(MiscAdditionsTab.tab);
		setHardness(hardness);
		setHarvestLevel(harvestTool.type, harvestLevel.level);
		setRegistryName(unlocalizedName);
		setSoundType(sound);
		setUnlocalizedName(unlocalizedName);
		
		GameRegistry.register(this);
		GameRegistry.register(new MAItemBlock(this), getRegistryName());
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}