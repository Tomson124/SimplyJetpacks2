package tonius.simplyjetpacks.crafting;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.item.Packs;

public class PlatingReturnHandler {
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent evt) {
		if (evt.player.world.isRemote || !(evt.crafting.getItem() instanceof ItemJetpack)) {
			if (!(evt.crafting.getItem() instanceof ItemFluxpack)) {
				return;
			}
		}
		if (evt.crafting.getItem() instanceof ItemPack) {
			Packs outputPack = Packs.getTypeFromName(((ItemPack) evt.crafting.getItem()).name);
			if (outputPack.getIsArmored()) {
				return;
			}
			for (int i = 0; i < evt.craftMatrix.getSizeInventory(); i++) {
				ItemStack input = evt.craftMatrix.getStackInSlot(i);
				if (input == null || !(input.getItem() instanceof ItemPack)) {
					continue;
				}
				Packs inputPack = Packs.getTypeFromName(( input.getItem()).getRegistryName().getResourcePath());
				if (inputPack != null && inputPack.getIsArmored()) {
					EntityItem item = evt.player.entityDropItem(new ItemStack(getArmorPlatingForJetpack(inputPack)), 0.0F);
					item.setNoPickupDelay();
					break;
				}
			}
		}
	}

	private Item getArmorPlatingForJetpack(Packs input) {
		String plating = input.getPlatingMeta();
		return ForgeRegistries.ITEMS.getValue(new ResourceLocation(SimplyJetpacks.MODID, plating));
	}
}