package tonius.simplyjetpacks.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import tonius.simplyjetpacks.Log;

public final class StackUtil {

	public static Item getItem(ItemStack stack)
	{
		if(stack.isEmpty())
		{
			return null;
		}

		return stack.getItem();
	}


	//thx to cofh for parts of this method
	public static int getEnchantmentLevel(int id, ItemStack stack) {
		if (stack.isEmpty()) {
			return 0;
		}
		else {
			NBTTagList nbttaglist = stack.getEnchantmentTagList();

			for (int i = 0; i < nbttaglist.tagCount(); ++i)	{
				NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
				int stackId = nbttagcompound.getShort("id");
				int j = nbttagcompound.getShort("lvl");

				if (id == stackId) {
					return j;
				}
			}
			return 0;
		}
	}

	//gets the enchantment ID on an item when passed a portion of the enchantment's name
	// returns -1 if enchantment is not applied or stack is empty
	public static int getEnchantmentIdByName(String name, ItemStack stack) {
		if (stack.isEmpty()) {
			return -1;
		}
		else {
			NBTTagList nbttaglist = stack.getEnchantmentTagList();

			for (int i = 0; i < nbttaglist.tagCount(); ++i)	{
				NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
				if(Enchantment.getEnchantmentByID(nbttagcompound.getShort("id")).getName().contains(name)){
					return nbttagcompound.getShort("id");
				}
			}
			return -1;
		}
	}
}
