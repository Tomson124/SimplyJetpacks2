package tonius.simplyjetpacks.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.reference.Reference;

import static tonius.simplyjetpacks.setup.ModItems.leatherStrap;

public final class SJItemGroups {

	public static ItemGroup creativeTab = new ItemGroup(Reference.MODID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.leatherStrap);
		}
	};
}
