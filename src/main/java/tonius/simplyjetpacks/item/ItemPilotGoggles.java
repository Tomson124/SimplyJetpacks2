package tonius.simplyjetpacks.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.util.SJStringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemPilotGoggles extends ItemArmor {

    private final String type;

    public ItemPilotGoggles(String name, String type) {
        super(ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.HEAD);
        this.setUnlocalizedName(SimplyJetpacks.PREFIX + name);
        this.setHasSubtypes(false);
        this.setMaxDamage(0);
        this.setCreativeTab(SimplyJetpacks.tabSimplyJetpacks);
        this.setRegistryName(name);
        this.setMaxStackSize(1);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (SJStringUtil.canShowDetails()) {
            shiftInformation(stack, tooltip);
        } else {
            tooltip.add(SJStringUtil.getShiftText());
        }
    }

    @SideOnly(Side.CLIENT)
    public void shiftInformation(ItemStack stack, @Nonnull List<String> list) {
        SJStringUtil.addDescriptionLines(list, "pilot_goggles", TextFormatting.GREEN.toString());
    }

    public void registerItemModel() {
        SimplyJetpacks.PROXY.registerItemRenderer(this, 0, "pilot_goggles_" + this.type);
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, @Nonnull Entity entity, @Nonnull EntityEquipmentSlot slot, @Nonnull String type) {
        return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/pilot_goggles_" + this.type + ".png";
    }
}
