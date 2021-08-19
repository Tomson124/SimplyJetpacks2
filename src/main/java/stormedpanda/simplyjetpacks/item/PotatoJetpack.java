package stormedpanda.simplyjetpacks.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

// TODO: re-implement Potato Jetpack.
public class PotatoJetpack extends ArmorItem {

    public PotatoJetpack() {
        super(JetpackArmorMaterial.POTATO, EquipmentSlotType.CHEST, new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
    }
}
