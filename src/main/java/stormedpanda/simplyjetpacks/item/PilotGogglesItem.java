package stormedpanda.simplyjetpacks.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.util.SJTextUtil;

import javax.annotation.Nullable;
import java.util.List;

public class PilotGogglesItem extends ArmorItem {

    private final String type;

    public PilotGogglesItem(String type) {
        super(JetpackArmorMaterial.PILOT_GOGGLES, EquipmentSlot.HEAD, new Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_" + this.type + ".png").toString();
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level levelIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(SJTextUtil.translate("tooltip", "pilot_goggles"));
    }
}
