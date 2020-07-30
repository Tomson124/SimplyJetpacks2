package tomson124.simplyjetpacks.setup;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tomson124.simplyjetpacks.SimplyJetpacks;

public class ModCreativeTab extends CreativeTabs {
	public ModCreativeTab() {
		super(SimplyJetpacks.MODID);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.itemJetpack);
	}
}
