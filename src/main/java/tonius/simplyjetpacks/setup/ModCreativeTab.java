package tonius.simplyjetpacks.setup;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCreativeTab extends CreativeTabs {
	public static final ModCreativeTab instance = new ModCreativeTab();

	private ModCreativeTab() {
		super("tabSimplyJetpacks");
		this.setRelevantEnchantmentTypes(ModEnchantments.enchantType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.itemJetpack);
	}
}
