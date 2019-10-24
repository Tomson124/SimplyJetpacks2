package tonius.simplyjetpacks.client.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IModelRegister {
	/**
	 * Does register the models for the items
	 */
	@OnlyIn(Dist.CLIENT)
	void registerModels();
}
