package tomson124.simplyjetpacks.crafting;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.datagen.SJTags;
import tomson124.simplyjetpacks.init.SJDataComponentTypes;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.util.JetpackUtil;

/**
 * Carries energy, particle choice and armour plating across when a jetpack is used as a crafting
 * ingredient to build a higher tier.
 */
@EventBusSubscriber(modid = SimplyJetpacks.MODID)
public class JetpackCraftingEvents {

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        ItemStack craftedStack = event.getCrafting();
        if (!(craftedStack.getItem() instanceof JetpackItem craftedItem)) {
            return;
        }

        var inventory = event.getInventory();

        // A particle swap is handled entirely by JetpackCustomRecipe; don't also run the upgrade path.
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            if (inventory.getItem(i).is(SJTags.PARTICLES)) {
                return;
            }
        }

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack input = inventory.getItem(i);
            if (!(input.getItem() instanceof JetpackItem)) {
                continue;
            }

            var inputJetpack = JetpackUtil.getJetpack(input);

            // ENERGY: never more than the new pack can hold.
            int storedEnergy = input.getOrDefault(SJDataComponentTypes.JETPACK_ENERGY, 0);
            craftedStack.set(SJDataComponentTypes.JETPACK_ENERGY,
                    Math.min(storedEnergy, craftedItem.getCapacity(craftedStack)));

            // PARTICLES:
            JetpackItem.setParticleId(craftedStack, JetpackItem.getParticleId(input));

            // ARMORPLATING: hand back the plating that was consumed with the old pack.
            if (inputJetpack.armored) {
                Item itemToReturn = getPlating(inputJetpack.platingId);
                if (itemToReturn != null) {
                    ItemEntity item = event.getEntity().drop(new ItemStack(itemToReturn, 1), false);
                    if (item != null) {
                        item.setNoPickUpDelay();
                    }
                }
            }
            break;
        }
    }

    public static Item getPlating(int id) {
        return switch (id) {
            case 0 -> Items.IRON_CHESTPLATE;
            case 1 -> Items.GOLDEN_CHESTPLATE;
            case 2 -> Items.DIAMOND_CHESTPLATE;
            case 3 -> Items.NETHERITE_CHESTPLATE;
            // Modded plating items are not registered yet; see RegistryHandler.
            default -> null;
        };
    }
}
