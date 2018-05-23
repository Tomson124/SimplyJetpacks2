package tonius.simplyjetpacks.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tonius.simplyjetpacks.SimplyJetpacks;

public abstract class RAItems {
	@GameRegistry.ItemStackHolder("redstonearsenal:armor.plate_flux")
	public static ItemStack armorChestPlate;
	@GameRegistry.ItemStackHolder(value = "redstonearsenal:material", meta = 224)
	public static ItemStack fluxPlating;
}
