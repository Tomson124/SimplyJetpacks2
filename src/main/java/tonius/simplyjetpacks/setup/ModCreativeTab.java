package tonius.simplyjetpacks.setup;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCreativeTab extends CreativeTabs
{
	public static final ModCreativeTab instance = new ModCreativeTab();

	private ModCreativeTab()
	{
		super("tabSimplyJetpacks");
		this.setRelevantEnchantmentTypes(ModEnchantments.enchantType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return ModItems.itemJetpack;
	}
}
