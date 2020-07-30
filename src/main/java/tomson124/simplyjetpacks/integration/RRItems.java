package tomson124.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RRItems {
    @GameRegistry.ItemStackHolder(value = "redstonerepository:material", meta = 7)
    public static ItemStack plateArmorGelidEnderium = null;
    @GameRegistry.ItemStackHolder(value = "redstonerepository:armor.plate_gelid")
    public static ItemStack fluxGelidChestplate = null;

}
