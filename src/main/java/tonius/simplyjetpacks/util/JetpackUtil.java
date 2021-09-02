package tonius.simplyjetpacks.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tonius.simplyjetpacks.integration.ModType;

public class JetpackUtil {

    public static ItemStack getFromBothSlots(EntityPlayer player) {
        ItemStack jetpackItem = ItemStack.EMPTY;
        if (ModType.BAUBLES.loaded) {
            //jetpackItem = CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof ItemJetpack, player).map(ImmutableTriple::getRight).orElse(ItemStack.EMPTY);
        }
        return jetpackItem == ItemStack.EMPTY ? getFromChest(player) : jetpackItem;
    }

    public static ItemStack getFromChest(EntityPlayer player) {
        return player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
    }

    public static void removeFromBothSlots(EntityPlayer player) {
        if (ModType.BAUBLES.loaded) {
            //ItemStack itemStack = CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof ItemJetpack, player).map(ImmutableTriple::getRight).orElse(ItemStack.EMPTY);
            //CuriosApi.getCuriosHelper().getCurio(itemStack).ifPresent(p -> p.curioBreak(itemStack, player));
        } else {
            player.inventory.removeStackFromSlot(EntityEquipmentSlot.CHEST.getIndex());
        }
    }

    public static EntityEquipmentSlot fromSlot(int slot) {
        for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values()) {
            if (entityequipmentslot.getSlotIndex() == slot) {
                return entityequipmentslot;
            }
        }
        throw new IllegalArgumentException("Invalid slot '" + slot + "'");
    }

    public static ItemStack setDefaultEnergyTag(ItemStack container, int energy) {
        if (!container.hasTagCompound()) {
            container.setTagCompound(new NBTTagCompound());
        }
        container.getTagCompound().setInteger(Constants.TAG_ENERGY, energy);

        return container;
    }
}
