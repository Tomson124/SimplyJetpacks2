package tonius.simplyjetpacks.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.util.SJStringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemPilotGoggles extends ItemArmor {

    private final String name;

    public ItemPilotGoggles(String name) {
        super(EnumHelper.addArmorMaterial("SJ_GOGGLES", "pilot_goggles", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0), 1, EntityEquipmentSlot.HEAD);
        this.name = name;
        this.setUnlocalizedName(SimplyJetpacks.PREFIX + name);
        this.setHasSubtypes(false);
        this.setMaxDamage(0);
        this.setCreativeTab(SimplyJetpacks.tabSimplyJetpacks);
        this.setRegistryName(name);
        this.setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        information(stack, tooltip);
        if (SJStringUtil.canShowDetails()) {
            shiftInformation(stack, tooltip);
        } else {
            tooltip.add(SJStringUtil.getShiftText());
        }
    }

    @SideOnly(Side.CLIENT)
    public void information(ItemStack stack, @Nonnull List<String> list) {
        //list.add(SJStringUtil.getFuelText(this.fuelType, item.getFuelStored(stack), item.getMaxFuelStored(stack), !Jetpack.values()[i].usesFuel));
    }

    @SideOnly(Side.CLIENT)
    public void shiftInformation(ItemStack stack, @Nonnull List<String> list) {
        //list.add(SJStringUtil.getHoverModeText(this.isHoverModeOn(stack)));
        SJStringUtil.addDescriptionLines(list, "pilot_goggles", TextFormatting.GREEN.toString());
    }

}
