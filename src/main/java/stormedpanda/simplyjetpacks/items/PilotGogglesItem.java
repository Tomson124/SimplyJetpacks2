package stormedpanda.simplyjetpacks.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.lists.ArmorMaterialList;

public class PilotGogglesItem extends ArmorItem {

    public PilotGogglesItem() {
        super(ArmorMaterialList.PILOT_GOGGLES, EquipmentSlot.HEAD, new Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
    }
}
