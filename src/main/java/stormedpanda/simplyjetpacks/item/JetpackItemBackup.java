package stormedpanda.simplyjetpacks.item;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.model.JetpackModel;
import stormedpanda.simplyjetpacks.particle.JetpackParticleType;
import stormedpanda.simplyjetpacks.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class JetpackItemBackup extends ArmorItem {

    private final JetpackType jetpackType;

    private final Supplier<Integer> energyCapacity_;

    private int energyCapacity;
    private int energyUsage;
    private int energyStored;

    public JetpackItemBackup(JetpackType jetpackType) {
        super(JetpackArmorMaterial.JETPACK, EquipmentSlotType.CHEST, new Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
        this.jetpackType = jetpackType;
        this.energyCapacity = this.jetpackType.getEnergyCapacity();
        this.energyUsage = this.jetpackType.getEnergyUsage();
        this.energyCapacity_ = jetpackType::getEnergyCapacity;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable World world, @Nonnull List<ITextComponent> list, @Nonnull ITooltipFlag flag) {
        super.appendHoverText(stack, world, list, flag);
        //list.add(new TranslationTextComponent("test.tooltip.energyCapacity", energyCapacity));
        list.add(new TranslationTextComponent("test.tooltip.energyCapacity", jetpackType.getEnergyCapacity()));
        list.add(new TranslationTextComponent("test.tooltip.energyCapacity2", energyCapacity_.get()));
        list.add(new TranslationTextComponent("test.tooltip.energyUsage", jetpackType.getEnergyUsage()));
        list.add(new TranslationTextComponent("test.tooltip.particle", getParticleId(stack)));
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

    public static ItemStack setParticleId(ItemStack stack, ItemStack particle) {
        String key = particle.getDescriptionId().split("item.simplyjetpacks.particle_")[1].toUpperCase();
        int id = JetpackParticleType.valueOf(key).ordinal();
        stack.getOrCreateTag().putInt(Constants.TAG_PARTICLE, id);
        return stack;
    }

    public static int getParticleId(ItemStack stack) {
        return stack.getOrCreateTag().contains(Constants.TAG_PARTICLE) ? stack.getOrCreateTag().getInt(Constants.TAG_PARTICLE) : 0;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return jetpackType.getArmorTexture();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel _default) {
        return new JetpackModel().applyData(_default);
    }
}
