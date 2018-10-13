package tonius.simplyjetpacks.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.handler.IModelRegister;

public class ItemRegistered extends Item implements IModelRegister {

	public String name;

	public ItemRegistered(String registryName) {
		this.name = registryName;
		this.setUnlocalizedName(SimplyJetpacks.PREFIX + registryName);
		this.setRegistryName(registryName);
		this.setCreativeTab(SimplyJetpacks.creativeTab);
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack) {
		if (ItemsSJ2.getFromName(name).getRarity() != null) {
			return ItemsSJ2.getFromName(name).getRarity();
		}
		return super.getRarity(itemStack);
	}

	@Override
	public void registerModels() {
		SimplyJetpacks.proxy.registerItemRenderer(this, getRegistryName());
	}
}
