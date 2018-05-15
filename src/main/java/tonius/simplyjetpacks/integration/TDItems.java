package tonius.simplyjetpacks.integration;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.Log;

public abstract class TDItems {

	public static Block ductFlux = null;
	public static ItemStack ductFluxLeadstone = null;
	public static ItemStack ductFluxHardened = null;
	public static ItemStack ductFluxRedstoneEnergy = null;
	public static ItemStack ductFluxResonant = null;

	public static void init() {
		Log.info("Stealing Thermal Dynamics's items");

		ductFlux = Block.REGISTRY.getObject(new ResourceLocation("thermaldynamics", "duct_0"));
		if (ductFlux != null) {
			ductFluxLeadstone = new ItemStack(ductFlux, 1, 0);
			ductFluxHardened = new ItemStack(ductFlux, 1, 1);
			ductFluxRedstoneEnergy = new ItemStack(ductFlux, 1, 2);
			ductFluxResonant = new ItemStack(ductFlux, 1, 4);
		}
	}
}
