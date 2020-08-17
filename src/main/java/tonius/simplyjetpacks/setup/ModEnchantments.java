package tonius.simplyjetpacks.setup;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import tonius.simplyjetpacks.enchantment.EnchantmentFuelEfficiency;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ModEnchantments {
	public static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();
	public static final Enchantment FUEL_EFFICIENCY = new EnchantmentFuelEfficiency();
}
