package tonius.simplyjetpacks.setup;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.SimplyJetpacks;

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
