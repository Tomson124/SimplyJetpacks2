package tomson124.simplyjetpacks.setup;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.config.Config;
import tomson124.simplyjetpacks.enchantment.EnchantmentFuelEfficiency;

public abstract class ModEnchantments {

	//public static final EnumEnchantmentType enchantType = EnumHelper.addEnchantmentType(SimplyJetpacks.MODID, delegate);
	public static Enchantment fuelEffeciency = null;

	public static void init() {
		int id = Config.enchantFuelEfficiencyID;

		if (id > 0) {
			fuelEffeciency = new EnchantmentFuelEfficiency();
		}
	}

	private static com.google.common.base.Predicate<Item> delegate = null;

	public static boolean canEnchantItem(Item itemIn)
	{
		return delegate == null ? false : delegate.test(itemIn);
	}

	public static final EnumEnchantmentType enchantType = EnumHelper.addEnchantmentType(SimplyJetpacks.MODID, delegate);
}
