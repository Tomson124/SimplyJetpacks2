package tonius.simplyjetpacks;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class RegistryHandler {

	public static final List<IRecipe> RECIPES_TO_REGISTER = new ArrayList<>();

	@SubscribeEvent
	public void onRegisterRecipes(RegistryEvent.Register<IRecipe> event) {
		Log.info("ListRecipes: " + RECIPES_TO_REGISTER);
		for(IRecipe recipe : RECIPES_TO_REGISTER) {
			event.getRegistry().register(recipe);
		}
		RECIPES_TO_REGISTER.clear();
	}


}
