package tonius.simplyjetpacks.setup;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.enchantment.EnchantmentFuelEfficiency;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ModEnchantments {
	public static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();
	public static Enchantment FUEL_EFFICIENCY = null;

	public static void init() {
		if (Config.enableFuelEfficiencyEnchantment) {
			FUEL_EFFICIENCY = new EnchantmentFuelEfficiency();
		}
	}
}
