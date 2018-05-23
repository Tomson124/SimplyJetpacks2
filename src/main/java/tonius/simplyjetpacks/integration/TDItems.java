package tonius.simplyjetpacks.integration;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tonius.simplyjetpacks.Log;

public abstract class TDItems {

	@GameRegistry.ItemStackHolder(value = "thermaldynamics:duct_0", meta = 0)
	public static ItemStack ductFluxLeadstone = null;
	@GameRegistry.ItemStackHolder(value = "thermaldynamics:duct_0", meta = 1)
	public static ItemStack ductFluxHardened = null;
	@GameRegistry.ItemStackHolder(value = "thermaldynamics:duct_0", meta = 2)
	public static ItemStack ductFluxRedstoneEnergy = null;
	@GameRegistry.ItemStackHolder(value = "thermaldynamics:duct_0", meta = 4)
	public static ItemStack ductFluxResonant = null;

	public static void init() {
		Log.info("Stealing Thermal Dynamics's items");
	}
}
