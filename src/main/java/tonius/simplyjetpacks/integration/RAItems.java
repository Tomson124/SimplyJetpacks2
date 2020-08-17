package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class RAItems {
	@GameRegistry.ItemStackHolder("redstonearsenal:armor.plate_flux")
	public static ItemStack fluxPlate;
	@GameRegistry.ItemStackHolder(value = "redstonearsenal:material", meta = 224)
	public static ItemStack fluxArmorplating;
}
