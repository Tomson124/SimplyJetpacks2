package tonius.simplyjetpacks.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;

public abstract class RAItems {

	public static Item material;
	public static Item armorPlate;
	public static ItemStack armorChestPlate;
	public static ItemStack fluxPlating;

	public static void init() {
		SimplyJetpacks.logger.info("Stealing Redstone Arsenal's items");

		material = Item.REGISTRY.getObject(new ResourceLocation("redstonearsenal", "material"));
		if (material != null) {
			fluxPlating = new ItemStack(material, 1, 224);
		}

		armorPlate = Item.REGISTRY.getObject(new ResourceLocation("redstonearsenal", "armor.plate_flux"));
		if (armorPlate != null) {
			armorChestPlate = new ItemStack(armorPlate, 1, 0);
		}
	}
}
