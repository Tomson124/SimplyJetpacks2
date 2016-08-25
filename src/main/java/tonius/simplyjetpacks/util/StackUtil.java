package tonius.simplyjetpacks.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class StackUtil {

	public static Item getItem(ItemStack stack)
	{
		if(stack == null)
		{
			return null;
		}

		return stack.getItem();
	}
}
