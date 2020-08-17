package tonius.simplyjetpacks.crafting;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import tonius.simplyjetpacks.item.Fluxpack;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.Jetpack;
import tonius.simplyjetpacks.setup.ModItems;

public class PlatingReturnHandler {

    @SubscribeEvent
    public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        Item craftedItem = event.crafting.getItem();
        if (event.player.world.isRemote) return;
        if (craftedItem instanceof ItemJetpack) {
            Jetpack outputJetpack = Jetpack.getTypeFromMeta(craftedItem.getMetadata(event.crafting));
            if (outputJetpack.getIsArmored()) return;
            for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
                ItemStack input = event.craftMatrix.getStackInSlot(i);
                if (!(input.getItem() instanceof ItemJetpack)) continue;
                Jetpack inputJetpack = Jetpack.getTypeFromMeta(craftedItem.getMetadata(input));
                if (inputJetpack.isArmored) {
                    EntityItem item = event.player.entityDropItem(getPlatingItem(craftedItem, input), 0.0F);
                    item.setNoPickupDelay();
                    break;
                }
            }
        } else if (craftedItem instanceof ItemFluxpack) {
            Fluxpack outputFluxpack = Fluxpack.getTypeFromMeta(craftedItem.getMetadata(event.crafting));
            if (outputFluxpack.getIsArmored()) return;
            for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
                ItemStack input = event.craftMatrix.getStackInSlot(i);
                if (!(input.getItem() instanceof ItemFluxpack)) continue;
                Fluxpack inputFluxpack = Fluxpack.getTypeFromMeta(craftedItem.getMetadata(input));
                if (inputFluxpack.isArmored) {
                    EntityItem item = event.player.entityDropItem(getPlatingItem(craftedItem, input), 0.0F);
                    item.setNoPickupDelay();
                    break;
                }
            }
        }
    }

    private ItemStack getPlatingItem(Item craftedItem, ItemStack input) {
        if (craftedItem instanceof ItemJetpack) {
            Jetpack inputJetpack = Jetpack.getTypeFromMeta(craftedItem.getMetadata(input));
            switch (inputJetpack.getBaseName()) {
                case "jetpack_vanilla1_armored":
                    return new ItemStack(Items.IRON_CHESTPLATE);
                case "jetpack_vanilla2_armored":
                    return new ItemStack(Items.GOLDEN_CHESTPLATE);
                case "jetpack_vanilla3_armored":
                    return new ItemStack(Items.DIAMOND_CHESTPLATE);
                default:
                    return new ItemStack(ModItems.metaItemMods, 1, inputJetpack.getPlatingMeta());
            }
        } else if (craftedItem instanceof ItemFluxpack) {
            Fluxpack inputFluxpack = Fluxpack.getTypeFromMeta(craftedItem.getMetadata(input));
            return new ItemStack(ModItems.metaItemMods, 1, inputFluxpack.getPlatingMeta());
        }
        return new ItemStack(Items.DIAMOND, 64);
    }
}