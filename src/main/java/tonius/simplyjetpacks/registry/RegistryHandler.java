package tonius.simplyjetpacks.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.util.reference.Reference;

public final class RegistryHandler {

	public static ItemGroup creativeTab = new ItemGroup(Reference.MODID) {
		@Override
		public ItemStack createIcon() {
			return null;
		}
	};
}
