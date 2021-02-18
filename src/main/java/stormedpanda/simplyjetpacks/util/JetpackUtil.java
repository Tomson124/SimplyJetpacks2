package stormedpanda.simplyjetpacks.util;

import stormedpanda.simplyjetpacks.items.JetpackItem;
import top.theillusivec4.curios.api.CuriosApi;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class JetpackUtil {

	public static ItemStack getJetpackFromBothSlots(LivingEntity entity) {
		ItemStack jetpackItem = CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof JetpackItem, entity)
				.map(stringIntegerItemStackImmutableTriple -> stringIntegerItemStackImmutableTriple.right).orElse(ItemStack.EMPTY);
		if (jetpackItem == ItemStack.EMPTY) {
			return entity.getItemStackFromSlot(EquipmentSlotType.CHEST);
		}
		return jetpackItem;

	}

}
