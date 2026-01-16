package tomson124.simplyjetpacks.util;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import tomson124.simplyjetpacks.compat.CuriosCompat;
import tomson124.simplyjetpacks.config.SimplyJetpacksConfig;
import tomson124.simplyjetpacks.init.SJDataComponentTypes;
import tomson124.simplyjetpacks.item.Jetpack;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.item.JetpackRegistry;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class JetpackUtil {

    public static final IEnergyStorage EMPTY_ENERGY_STORAGE = new EnergyStorage(0);

    public static IEnergyStorage getEnergyStorage(ItemStack stack) {
        var energy = stack.getCapability(Capabilities.EnergyStorage.ITEM);
        return energy == null ? EMPTY_ENERGY_STORAGE : energy;
    }

    public static ItemStack getFromChest(Player player) {
        return player.getItemBySlot(EquipmentSlot.CHEST);
    }

    public static ItemStack getFromBothSlots(Player player) {
        ItemStack jetpackItem = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!jetpackItem.isEmpty() && jetpackItem.getItem() instanceof JetpackItem) {
            return jetpackItem;
        }

        if (SimplyJetpacksConfig.isCuriosEnabled()) {
            return CuriosCompat.getJetpackCurio(player);
        }

        return ItemStack.EMPTY;
    }

    public static void removeFromBothSlots(SlotContext slotContext, Player player) {
        if (SimplyJetpacksConfig.isCuriosEnabled()) {
            ItemStack itemStack = CuriosCompat.getJetpackCurio(player);
            CuriosApi.getCurio(itemStack).ifPresent(p -> p.curioBreak(slotContext));
        } else {
            player.getInventory().removeItem(getFromChest(player));
        }
    }

    /*
    * Train of thought:
    * Curios slot has priority
    * - If a jetpack is in the curios slot, only use this jetpack
    * - Jetpack must be identified by item stack match
    *
    * */
    public static boolean checkTickForEquippedSlot(int index, ItemStack which, Player player) {
        boolean isNormalChestSlotCorrect = index == EquipmentSlot.CHEST.getIndex() && player.getItemBySlot(EquipmentSlot.CHEST) == which;
        int checkCuriosFlag = checkCuriosSlot(which, player);

        if (checkCuriosFlag >= 0) {
            if (checkCuriosFlag > 0) {
                return checkCuriosFlag > 1;
            } else {
                return isNormalChestSlotCorrect;
            }
        } else {
            return isNormalChestSlotCorrect;
        }
    }

    // -1 if no curios
    // 0 if curios but not correct slot
    // 1 if curios and any jetpack item in slot
    // 2 if curios and correct slot
    private static int checkCuriosSlot(ItemStack which, Player player) {
        if (ModList.get().isLoaded("curios")) {
            ItemStack curioStack = CuriosCompat.findMatchingItem(which.getItem(), player);
            if (curioStack.isEmpty()) {
                return 0;
            } else {
                if (curioStack == which) {
                    return 2;
                } else {
                    return 1;
                }
            }
        } else {
            return -1;
        }
    }

    public static Jetpack getJetpack(ItemStack stack) {
        var id = stack.get(SJDataComponentTypes.JETPACK_ID);
        if (id != null) {
            return JetpackRegistry.getInstance().getJetpackById(id);
        }

        return Jetpack.UNDEFINED;
    }
}
