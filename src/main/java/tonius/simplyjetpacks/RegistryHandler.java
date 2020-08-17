package tonius.simplyjetpacks;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tonius.simplyjetpacks.integration.TEItems;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;

import java.util.ArrayList;
import java.util.List;

public class RegistryHandler {

	public static final List<IRecipe> RECIPES_TO_REGISTER = new ArrayList<>();

	@SubscribeEvent
	public void onRegisterRecipes(RegistryEvent.Register<IRecipe> event) {
		if (ModItems.integrateTE) {
			TEItems.initFluids();
		}
		ModItems.registerRecipes();
		for (IRecipe recipe : RECIPES_TO_REGISTER) {
			event.getRegistry().register(recipe);
		}
		RECIPES_TO_REGISTER.clear();
	}

	@SubscribeEvent
	public void onRegisterEnchantments(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().registerAll(ModEnchantments.ENCHANTMENTS.toArray(new Enchantment[0]));
	}
}
