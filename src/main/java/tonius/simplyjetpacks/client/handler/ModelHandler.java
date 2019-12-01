package tonius.simplyjetpacks.client.handler;

import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tonius.simplyjetpacks.util.reference.Reference;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Reference.MODID)
public final class ModelHandler  {
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent evt) {
		for(Item item : GameRegistry.findRegistry(Item.class)) {
			if(item instanceof IModelRegister)
				((IModelRegister) item).registerModels();
		}
	}
}
