package stormedpanda.simplyjetpacks.item;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

import javax.annotation.Nullable;

public class PilotGogglesItem extends ArmorItem {

    private final String type;

    public PilotGogglesItem(String type) {
        super(JetpackArmorMaterial.PILOT_GOGGLES, EquipmentSlotType.HEAD, new Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_" + this.type + "_layer_1.png").toString();
    }
}
