package tonius.simplyjetpacks.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class StackUtil {

	public static Item getItem(ItemStack stack)
	{
		if(stack.isEmpty())
		{
			return null;
		}

		return stack.getItem();
	}

	/*gets the enchantment ID on an item when passed a portion of the enchantment's name
	// returns -1 if enchantment is not applied or stack is empty
	public static int getEnchantmentIdByName(String name, ItemStack stack) {
		Enchantment ench = Enchantment.getEnchantmentByLocation(name);

		if (!stack.isEmpty()) {
			if (EnchantmentHelper.getEnchantments(stack).containsKey(ench)) {
				return Enchantment.REGISTRY.getIDForObject(ench);
			}
			else {
				return -1;
			}
		}
		else {
			return -1;
		}
	}*/
}
