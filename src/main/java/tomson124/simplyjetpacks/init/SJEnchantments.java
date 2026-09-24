package tomson124.simplyjetpacks.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import tomson124.simplyjetpacks.SimplyJetpacks;

/**
 * Enchantments became data-driven in 1.21, so there is nothing to register here -- the definition lives
 * in {@code data/simplyjetpacks/enchantment/fuel_efficiency.json}. This just names the key for lookups.
 */
public final class SJEnchantments {

    public static final ResourceKey<Enchantment> FUEL_EFFICIENCY = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "fuel_efficiency"));

    private SJEnchantments() {
    }
}
