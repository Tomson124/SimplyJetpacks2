package stormedpanda.simplyjetpacks.util;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import top.theillusivec4.curios.api.CuriosApi;

public class JetpackUtil {

    public static ItemStack getFromBothSlots(Player player) {
        ItemStack jetpackItem = ItemStack.EMPTY;
        if (ModList.get().isLoaded("curios")) {
            jetpackItem = CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof JetpackItem, player).map(ImmutableTriple::getRight).orElse(ItemStack.EMPTY);
        }
        return jetpackItem == ItemStack.EMPTY ? getFromChest(player) : jetpackItem;
    }

    public static ItemStack getFromChest(Player player) {
        return player.getItemBySlot(EquipmentSlot.CHEST);
    }

    public static void removeFromBothSlots(Player player) {
        if (ModList.get().isLoaded("curios")) {
            ItemStack itemStack = CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof JetpackItem, player).map(ImmutableTriple::getRight).orElse(ItemStack.EMPTY);
            CuriosApi.getCuriosHelper().getCurio(itemStack).ifPresent(p -> p.curioBreak(itemStack, player));
        } else {
            player.getInventory().removeItem(getFromChest(player));
        }
    }
}
