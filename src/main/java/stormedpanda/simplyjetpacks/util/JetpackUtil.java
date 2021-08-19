package stormedpanda.simplyjetpacks.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import stormedpanda.simplyjetpacks.items.JetpackItem;
import top.theillusivec4.curios.api.CuriosApi;

public class JetpackUtil {

    public static ItemStack getFromBothSlots(LivingEntity livingEntity) {
        ItemStack jetpackItem = ItemStack.EMPTY;
        if (ModList.get().isLoaded("curios")) {
             jetpackItem = CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof JetpackItem, livingEntity).map(ImmutableTriple::getRight).orElse(ItemStack.EMPTY);
        }
        return jetpackItem == ItemStack.EMPTY ? getFromChest(livingEntity) : jetpackItem;
    }

    public static ItemStack getFromChest(LivingEntity livingEntity) {
        return livingEntity.getItemBySlot(EquipmentSlotType.CHEST);
    }

}
