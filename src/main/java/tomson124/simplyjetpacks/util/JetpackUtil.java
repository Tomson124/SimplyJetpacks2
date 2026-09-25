package tomson124.simplyjetpacks.util;

import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;
import tomson124.simplyjetpacks.compat.CuriosCompat;
import tomson124.simplyjetpacks.config.SimplyJetpacksConfig;
import tomson124.simplyjetpacks.handlers.RegistryHandler;
import tomson124.simplyjetpacks.init.SJDataComponentTypes;
import tomson124.simplyjetpacks.init.SJEnchantments;
import tomson124.simplyjetpacks.item.Jetpack;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.item.JetpackRegistry;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

public class JetpackUtil {

    public static final IEnergyStorage EMPTY_ENERGY_STORAGE = new EnergyStorage(0);

    public static IEnergyStorage getEnergyStorage(ItemStack stack) {
        var energy = stack.getCapability(Capabilities.EnergyStorage.ITEM);
        return energy == null ? EMPTY_ENERGY_STORAGE : energy;
    }

    public static double getThrottle(ItemStack stack) {
        return stack.getOrDefault(SJDataComponentTypes.JETPACK_THROTTLE, 1.0D);
    }

    public static double setThrottle(ItemStack stack, double value) {
        double throttle = Mth.clamp(value, 0.0D, 1.0D);
        stack.set(SJDataComponentTypes.JETPACK_THROTTLE, throttle);
        return throttle;
    }

    public static int getFuelEfficiencyLevel(ItemStack stack) {
        var enchantments = stack.getEnchantments();
        for (var entry : enchantments.entrySet()) {
            if (entry.getKey().is(SJEnchantments.FUEL_EFFICIENCY)) {
                return entry.getIntValue();
            }
        }
        return 0;
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
        // Identity alone is enough, and deliberately ignores `index`: NeoForge ticks every inventory
        // compartment with a *global* slot number, so the chest slot arrives as 38 (36 main slots + the
        // chest's armour index of 2), not as EquipmentSlot.CHEST.getIndex(). A given stack instance
        // occupies exactly one slot, so this matches on precisely the tick for the worn jetpack.
        boolean isNormalChestSlotCorrect = player.getItemBySlot(EquipmentSlot.CHEST) == which;
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

    /**
     * An ingredient matching one specific jetpack. Plain {@code Ingredient.of} would match every jetpack,
     * since they all share a single registered item and differ only by component.
     */
    public static Ingredient ingredientFor(Jetpack jetpack) {
        return DataComponentIngredient.of(false,
                DataComponentPredicate.builder()
                        .expect(SJDataComponentTypes.JETPACK_ID.get(), jetpack.getId())
                        .build(),
                RegistryHandler.JETPACK.get());
    }

    public static Jetpack getJetpack(ItemStack stack) {
        var id = stack.get(SJDataComponentTypes.JETPACK_ID);
        if (id != null) {
            return JetpackRegistry.getInstance().getJetpackById(id);
        }

        return Jetpack.UNDEFINED;
    }
}
