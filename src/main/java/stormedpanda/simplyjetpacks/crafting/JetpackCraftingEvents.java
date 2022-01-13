package stormedpanda.simplyjetpacks.crafting;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import stormedpanda.simplyjetpacks.datagen.SJTags;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.item.JetpackType;
import stormedpanda.simplyjetpacks.util.NBTUtil;

public class JetpackCraftingEvents {

    // TODO: improve this
    @SubscribeEvent
    public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        ItemStack craftedStack = event.getCrafting();
        Item craftedItem = event.getCrafting().getItem();
        int storedEnergy;
        CompoundNBT tags;
        boolean particleRecipe = false; // if particle as ingredient, then true

        if (craftedItem instanceof JetpackItem) {
            for (int i = 0; i < event.getInventory().getContainerSize(); i++) {
                ItemStack input = event.getInventory().getItem(i);
                if (input.getItem().getTags().contains(SJTags.PARTICLES.getName())) {
                    particleRecipe = true;
                    break;
                }
            }
            if (!particleRecipe) {
                for (int i = 0; i < event.getInventory().getContainerSize(); i++) {
                    ItemStack input = event.getInventory().getItem(i);
                    if (!(input.getItem() instanceof JetpackItem)) {
                        continue;
                    }
                    if (input.getItem() instanceof JetpackItem) {
                        JetpackType inputJetpack = ((JetpackItem) input.getItem()).getJetpackType();
                        // ENERGY/PARTICLES:
                        tags = NBTUtil.getTagCompound(input).copy();
                        storedEnergy = NBTUtil.getInt(input, "Energy");
                        // TODO: transfer enchantments
//                        CompoundNBT enchantments = NBTUtil.getString()
                        craftedStack.setTag(tags);
                        int energyToTransfer = Math.min(storedEnergy, ((JetpackItem) craftedItem).getCapacity(input));
                        //JetpackParticleType particleType = inputJetpack.getParticleType(input);
                        int particleId = JetpackItem.getParticleId(input);
                        //((JetpackItem) craftedItem).setParticleType(craftedStack, particleType);
                        JetpackItem.setParticleId(craftedStack, particleId);
                        // ARMORPLATING:
                        if (inputJetpack.isArmored()) {
                            Item itemToReturn = getPlating(inputJetpack.getPlatingId());
                            if (itemToReturn != null) {
                                ItemEntity item = event.getPlayer().drop(new ItemStack(itemToReturn, 1), false);
                                if (item != null) {
                                    item.setNoPickUpDelay();
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    // TODO: improve this
    public Item getPlating(int id) {
        switch (id) {
            case 0:
                return Items.IRON_CHESTPLATE;
            case 1:
                return Items.GOLDEN_CHESTPLATE;
            case 2:
                return Items.DIAMOND_CHESTPLATE;
            case 3:
                return Items.NETHERITE_CHESTPLATE;
            case 4:
                return RegistryHandler.ARMORPLATING_IE1.get();
            case 5:
                return RegistryHandler.ARMORPLATING_IE2.get();
            case 6:
                return RegistryHandler.ARMORPLATING_IE3.get();
            case 7:
                return RegistryHandler.ARMORPLATING_MEK1.get();
            case 8:
                return RegistryHandler.ARMORPLATING_MEK2.get();
            case 9:
                return RegistryHandler.ARMORPLATING_MEK3.get();
            case 10:
                return RegistryHandler.ARMORPLATING_MEK4.get();
            case 11:
                return RegistryHandler.ARMORPLATING_TE1.get();
            case 12:
                return RegistryHandler.ARMORPLATING_TE2.get();
            case 13:
                return RegistryHandler.ARMORPLATING_TE3.get();
            case 14:
                return RegistryHandler.ARMORPLATING_TE4.get();
            default:
                return null;
        }
    }
}
