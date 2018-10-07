package tonius.simplyjetpacks.client.handler;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import tonius.simplyjetpacks.SimplyJetpacks;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = SimplyJetpacks.MODID)
public final class ModelHandler  {
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent evt) {
		for(Item item : Item.REGISTRY) {
			if(item instanceof IModelRegister)
				((IModelRegister) item).registerModels();
		}
	}
}
