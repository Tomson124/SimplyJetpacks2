package stormedpanda.simplyjetpacks.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class JetpackItem extends ArmorItem {

    private int energyCapacity;
    private int energyUsage;
    private JetpackType jetpackType;

    public JetpackItem(JetpackType jetpackType) {
        super(JetpackArmorMaterial.JETPACK, EquipmentSlotType.CHEST, new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
        this.jetpackType = jetpackType;
        this.energyCapacity = jetpackType.getEnergyCapacity();
        this.energyUsage = jetpackType.getEnergyUsage();
    }


    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable World world, @Nonnull List<ITextComponent> list, @Nonnull ITooltipFlag flag) {
        super.appendHoverText(stack, world, list, flag);
        list.add(new TranslationTextComponent("test.tooltip.energyCapacity", this.energyCapacity));
        list.add(new TranslationTextComponent("test.tooltip.energyUsage", this.energyUsage));
    }
}
