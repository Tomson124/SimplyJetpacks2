package tonius.simplyjetpacks.integration;

import tonius.simplyjetpacks.SimplyJetpacks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class TDItems {
	public static Item ductFlux = null;
	public static ItemStack ductFluxLeadstone = null;
	public static ItemStack ductFluxHardened = null;
	public static ItemStack ductFluxRedstoneEnergy = null;
	public static ItemStack ductFluxResonant = null;

	public static void init() {
		SimplyJetpacks.logger.info("Stealing Thermal Dynamics's items");

		ductFlux = Item.REGISTRY.getObject(new ResourceLocation("ThermalDynamics", "ThermalDynamics_0"));
		if (ductFlux != null) {
			ductFluxLeadstone = new ItemStack(ductFlux, 1, 0);
			ductFluxHardened = new ItemStack(ductFlux, 1, 1);
			ductFluxRedstoneEnergy = new ItemStack(ductFlux, 1, 2);
			ductFluxResonant = new ItemStack(ductFlux, 1, 4);
		}
	}
}
