package tonius.simplyjetpacks.item;

import net.minecraft.item.Item;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.ClientProxy;
import tonius.simplyjetpacks.client.handler.IModelRegister;

public class ItemRegistered extends Item implements IModelRegister {

	public ItemRegistered(Properties builder) {
		super(builder);
	}

	@Override
	public void registerModels() {
		ClientProxy.registerItemRenderer(getRegistryName());
	}
}
