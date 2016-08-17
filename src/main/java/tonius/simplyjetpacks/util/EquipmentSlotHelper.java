package tonius.simplyjetpacks.util;

import net.minecraft.inventory.EntityEquipmentSlot;

public class EquipmentSlotHelper
{
	public static EntityEquipmentSlot fromSlot(int slot)
	{

		for(EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values())
		{
			if(entityequipmentslot.getSlotIndex() == slot)
			{
				return entityequipmentslot;
			}
		}
		throw new IllegalArgumentException("Invalid slot \'" + slot + "\'");
	}
}
