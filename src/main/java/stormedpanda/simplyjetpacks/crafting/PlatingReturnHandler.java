package stormedpanda.simplyjetpacks.crafting;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;
import stormedpanda.simplyjetpacks.items.JetpackItem;
import stormedpanda.simplyjetpacks.items.JetpackType;

public class PlatingReturnHandler {

    @SubscribeEvent
    public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        Item craftedItem = event.getCrafting().getItem();
        if (craftedItem instanceof JetpackItem) {
            for (int i = 0; i < event.getInventory().getContainerSize(); i++) {
                ItemStack input = event.getInventory().getItem(i);
                if (!(input.getItem() instanceof JetpackItem)) { continue; }
                if (input.getItem() instanceof JetpackItem) {
                    JetpackType inputJetpack = ((JetpackItem) input.getItem()).getType();
                    if (inputJetpack.getIsArmored()) {
                        Item itemToReturn = getPlating(inputJetpack.getPlatingID());
                        if (itemToReturn != null) {
                            ItemEntity item = event.getPlayer().drop(new ItemStack(itemToReturn, 1), false);
                            if (item != null) {
                                item.setNoPickUpDelay();
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public Item getPlating(int id) {
        return switch (id) {
            case 0 -> Items.IRON_CHESTPLATE;
            case 1 -> Items.GOLDEN_CHESTPLATE;
            case 2 -> Items.DIAMOND_CHESTPLATE;
            case 3 -> Items.NETHERITE_CHESTPLATE;
            case 4 -> RegistryHandler.ARMORPLATING_IE1.get();
            case 5 -> RegistryHandler.ARMORPLATING_IE2.get();
            case 6 -> RegistryHandler.ARMORPLATING_IE3.get();
            case 7 -> RegistryHandler.ARMORPLATING_MEK1.get();
            case 8 -> RegistryHandler.ARMORPLATING_MEK2.get();
            case 9 -> RegistryHandler.ARMORPLATING_MEK3.get();
            case 10 -> RegistryHandler.ARMORPLATING_MEK4.get();
            case 11 -> RegistryHandler.ARMORPLATING_TE1.get();
            case 12 -> RegistryHandler.ARMORPLATING_TE2.get();
            case 13 -> RegistryHandler.ARMORPLATING_TE3.get();
            case 14 -> RegistryHandler.ARMORPLATING_TE4.get();
            default -> null;
        };
    }
}
