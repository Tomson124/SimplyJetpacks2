package stormedpanda.simplyjetpacks.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class PilotGogglesItem extends ArmorItem {

    public PilotGogglesItem() {
        super(JetpackArmorMaterial.PILOT_GOGGLES, EquipmentSlotType.HEAD, new Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
    }
}
