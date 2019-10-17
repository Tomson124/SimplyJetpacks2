package tonius.simplyjetpacks.client.handler;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelRegister {
	/**
	 * Does register the models for the items
	 */
	@SideOnly(Side.CLIENT)
	void registerModels();
}
