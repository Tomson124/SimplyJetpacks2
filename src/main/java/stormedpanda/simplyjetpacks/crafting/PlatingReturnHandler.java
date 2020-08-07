package stormedpanda.simplyjetpacks.crafting;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
            for (int i = 0; i < event.getInventory().getSizeInventory(); i++) {
                ItemStack input = event.getInventory().getStackInSlot(i);
                if (!(input.getItem() instanceof JetpackItem)) { continue; }
                if (input.getItem() instanceof JetpackItem) {
                    JetpackType inputJetpack = ((JetpackItem) input.getItem()).getType();
                    if (inputJetpack.getIsArmored()) {
                        Item itemToReturn = getPlating(inputJetpack.getPlatingID());
                        ItemEntity item = event.getPlayer().entityDropItem(new ItemStack(itemToReturn, 1), 0.0F);
                        if (item != null) {
                            item.setNoPickupDelay();
                        }
                        //item.setNoPickupDelay();
                        break;
                    }
                }
            }
        }
    }

    // TODO: make this better
    public Item getPlating(int id) {
        if (id == 0) { return Items.IRON_CHESTPLATE; }
        if (id == 1) { return Items.GOLDEN_CHESTPLATE; }
        if (id == 2) { return Items.DIAMOND_CHESTPLATE; }
        if (id == 3) { return Items.NETHERITE_CHESTPLATE; }
        if (id == 4) { return RegistryHandler.ARMORPLATING_IE1.get(); }
        if (id == 5) { return RegistryHandler.ARMORPLATING_IE2.get(); }
        if (id == 6) { return RegistryHandler.ARMORPLATING_IE3.get(); }
        if (id == 7) { return RegistryHandler.ARMORPLATING_MEK1.get(); }
        if (id == 8) { return RegistryHandler.ARMORPLATING_MEK2.get(); }
        if (id == 9) { return RegistryHandler.ARMORPLATING_MEK3.get(); }
        if (id == 10) { return RegistryHandler.ARMORPLATING_MEK4.get(); }

        return Items.DIAMOND.getItem();
    }
}
