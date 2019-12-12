package tonius.simplyjetpacks.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHelper {

	private ItemHelper() {
	}

	public static ItemStack cloneStack(Item item, int stackSize) {
		if (item == null) {
			return null;
		}
		ItemStack stack = new ItemStack(item, stackSize);

		return stack;
	}

	public static ItemStack cloneStack(Block item, int stackSize) {
		if (item == null) {
			return null;
		}
		ItemStack stack = new ItemStack(item, stackSize);

		return stack;
	}

	public static ItemStack cloneStack(ItemStack stack, int stackSize) {
		if (stack == null) {
			return null;
		}
		ItemStack retStack = stack.copy();
		retStack.setCount(stackSize);

		return retStack;
	}

	public static ItemStack cloneStack(ItemStack stack) {
		if (stack == null) {
			return null;
		}
		ItemStack retStack = stack.copy();

		return retStack;
	}

	public static ItemStack copyTag(ItemStack container, ItemStack other) {

		if (!other.isEmpty() && other.hasTag()) {
			container.setTag(other.getTag().copy());
		}

		return container;
	}
}