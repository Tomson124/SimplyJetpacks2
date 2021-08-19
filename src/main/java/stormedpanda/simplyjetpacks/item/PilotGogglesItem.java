package stormedpanda.simplyjetpacks.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.util.SJTextUtil;

import javax.annotation.Nullable;
import java.util.List;

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
        return new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_" + this.type).toString();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(SJTextUtil.translate("tooltip", "pilot_goggles"));
    }
}
