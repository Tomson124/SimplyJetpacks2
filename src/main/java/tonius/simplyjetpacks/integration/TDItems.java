package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class TDItems {
    @GameRegistry.ItemStackHolder(value = "thermaldynamics:duct_0", meta = 0)
    public static ItemStack ductFluxLeadstone = null;
    @GameRegistry.ItemStackHolder(value = "thermaldynamics:duct_0", meta = 1)
    public static ItemStack ductFluxHardened = null;
    @GameRegistry.ItemStackHolder(value = "thermaldynamics:duct_0", meta = 2)
    public static ItemStack ductFluxRedstoneEnergy = null;
    @GameRegistry.ItemStackHolder(value = "thermaldynamics:duct_0", meta = 4)
    public static ItemStack ductFluxResonant = null;
}
