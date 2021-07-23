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
import java.util.function.Supplier;

public class JetpackItem extends ArmorItem {

    private final JetpackType jetpackType;

    private final Supplier<Integer> energyCapacity_;

    private int energyCapacity;
    private int energyUsage;
    private int energyStored;

    public JetpackItem(JetpackType jetpackType) {
        super(JetpackArmorMaterial.JETPACK, EquipmentSlotType.CHEST, new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
        this.jetpackType = jetpackType;
        this.energyCapacity = this.jetpackType.getEnergyCapacity();
        this.energyUsage = this.jetpackType.getEnergyUsage();
        this.energyCapacity_ = jetpackType::getEnergyCapacity;
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable World world, @Nonnull List<ITextComponent> list, @Nonnull ITooltipFlag flag) {
        super.appendHoverText(stack, world, list, flag);
        //list.add(new TranslationTextComponent("test.tooltip.energyCapacity", energyCapacity));
        list.add(new TranslationTextComponent("test.tooltip.energyCapacity", jetpackType.getEnergyCapacity()));
        list.add(new TranslationTextComponent("test.tooltip.energyCapacity2", energyCapacity_.get()));
        list.add(new TranslationTextComponent("test.tooltip.energyUsage", jetpackType.getEnergyUsage()));
        list.add(new TranslationTextComponent("test.tooltip.particle", getParticles(stack)));
    }

    public int getEnergyCapacity() {
        return jetpackType.getEnergyCapacity();
    }

    public int getEnergyStored() {
        return jetpackType.getEnergyCapacity() / 2;
    }

    public JetpackType getJetpackType() {
        return jetpackType;
    }

    public boolean isCreative() {
        //return jetpackType.getName().equals("creative");
        return false;
    }

    public String getModId() {
        String name = jetpackType.getName();
        if (name.contains("mek")) {
            return "mek";
        } else if (name.contains("ie") || name.contains("armored")) {
            return "ie";
        } else {
            return "sj";
        }
    }

    public static String getParticles(ItemStack stack) {
        return stack.getOrCreateTag().contains("Particles") ? stack.getOrCreateTag().getString("Particles") : "none";
    }

    public static ItemStack setParticles(ItemStack stack, ItemStack particle) {
        stack.getOrCreateTag().putString("Particles", particle.getDescriptionId());
        return stack;
    }
}
