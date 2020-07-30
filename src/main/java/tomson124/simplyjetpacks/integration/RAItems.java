package tomson124.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class RAItems {
	@GameRegistry.ItemStackHolder("redstonearsenal:armor.plate_flux")
	public static ItemStack armorChestPlate;
	@GameRegistry.ItemStackHolder(value = "redstonearsenal:material", meta = 224)
	public static ItemStack fluxPlating;
}
