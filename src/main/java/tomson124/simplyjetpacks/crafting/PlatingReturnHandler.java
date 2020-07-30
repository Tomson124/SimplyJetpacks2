package tomson124.simplyjetpacks.crafting;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import tomson124.simplyjetpacks.item.ItemJetpack;
import tomson124.simplyjetpacks.item.Fluxpack;
import tomson124.simplyjetpacks.item.ItemFluxpack;
import tomson124.simplyjetpacks.item.Jetpack;
import tomson124.simplyjetpacks.setup.ModItems;

public class PlatingReturnHandler {
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent evt) {
		if (evt.player.world.isRemote || !(evt.crafting.getItem() instanceof ItemJetpack)) {
			if (!(evt.crafting.getItem() instanceof ItemFluxpack)) {
				return;
			}
		}

		if (evt.crafting.getItem() instanceof ItemJetpack) {
			Jetpack outputPack = Jetpack.getTypeFromMeta(evt.crafting.getItem().getMetadata(evt.crafting));
			if (outputPack.getIsArmored()) {
				return;
			}
			for (int i = 0; i < evt.craftMatrix.getSizeInventory(); i++) {
				ItemStack input = evt.craftMatrix.getStackInSlot(i);
				if (input == null || !(input.getItem() instanceof ItemJetpack)) {
					continue;
				}
				Jetpack inputPack = Jetpack.getTypeFromMeta(evt.crafting.getItem().getMetadata(input));
				if (inputPack != null && inputPack.isArmored) {

					EntityItem item = evt.player.entityDropItem(new ItemStack(ModItems.metaItemMods, 1, inputPack.getPlatingMeta()), 0.0F);
					item.setNoPickupDelay();
					break;
				}
			}
		} else {
			Fluxpack outputFluxPack = Fluxpack.getTypeFromMeta(evt.crafting.getItem().getMetadata(evt.crafting));
			if (outputFluxPack.getIsArmored()) {
				return;
			}
			for (int i = 0; i < evt.craftMatrix.getSizeInventory(); i++) {
				ItemStack input = evt.craftMatrix.getStackInSlot(i);
				if (input == null || !(input.getItem() instanceof ItemFluxpack)) {
					continue;
				}
				Fluxpack inputFluxPack = Fluxpack.getTypeFromMeta(evt.crafting.getItem().getMetadata(input));
				if (inputFluxPack != null && inputFluxPack.isArmored) {

					EntityItem item = evt.player.entityDropItem(new ItemStack(ModItems.metaItemMods, 1, inputFluxPack.getPlatingMeta()), 0.0F);
					item.setNoPickupDelay();
					break;
				}
			}
		}
	}
}