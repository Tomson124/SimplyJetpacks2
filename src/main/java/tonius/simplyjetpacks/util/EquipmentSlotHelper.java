package tonius.simplyjetpacks.util;

import net.minecraft.inventory.EquipmentSlotType;

public class EquipmentSlotHelper {
	public static EquipmentSlotType fromSlot(int slot) {

		for (EquipmentSlotType entityequipmentslot : EquipmentSlotType.values()) {
			if (entityequipmentslot.getSlotIndex() == slot) {
				return entityequipmentslot;
			}
		}
		throw new IllegalArgumentException("Invalid slot \'" + slot + "\'");
	}
}
