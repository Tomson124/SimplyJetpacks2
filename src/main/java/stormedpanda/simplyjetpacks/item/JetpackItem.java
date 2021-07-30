package stormedpanda.simplyjetpacks.item;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.energy.EnergyStorageImpl;
import stormedpanda.simplyjetpacks.energy.IEnergyContainer;
import stormedpanda.simplyjetpacks.model.JetpackModel;
import stormedpanda.simplyjetpacks.particle.JetpackParticleType;
import stormedpanda.simplyjetpacks.util.Constants;
import stormedpanda.simplyjetpacks.util.NBTUtil;
import stormedpanda.simplyjetpacks.util.TextUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class JetpackItem extends ArmorItem implements IEnergyContainer {

    private final JetpackType jetpackType;

    public JetpackItem(JetpackType jetpackType) {
        super(JetpackArmorMaterial.JETPACK, EquipmentSlotType.CHEST, new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
        this.jetpackType = jetpackType;
        // TODO: set initial particle id.
        //jetpackType.getDefaultParticles();
    }

    public JetpackType getJetpackType() {
        return jetpackType;
    }

    public boolean isCreative() {
        return jetpackType.getName().contains("creative");
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.isEnchanted() || isCreative();
    }

    public String getModId() {
        String name = jetpackType.getName();
        if (name.contains("mek")) {
            return "mek";
        } else if (name.contains("ie")) {
            return "ie";
        } else {
            return "sj";
        }
    }

    public static float getChargeRatio(ItemStack stack) {
        LazyOptional<IEnergyStorage> optional = stack.getCapability(CapabilityEnergy.ENERGY);
        if (optional.isPresent()) {
            IEnergyStorage energyStorage = optional.orElseThrow(IllegalStateException::new);
            return (float) energyStorage.getEnergyStored() / energyStorage.getMaxEnergyStored();
        }
        return 0;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        IEnergyContainer container = this;
        return new ICapabilityProvider() {
            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                if (cap == CapabilityEnergy.ENERGY)
                    return LazyOptional.of(() -> new EnergyStorageImpl(stack, container)).cast();
                return LazyOptional.empty();
            }
        };
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (CapabilityEnergy.ENERGY == null) return;
        stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e ->
                tooltip.add(TextUtil.energyWithMax(e.getEnergyStored(), e.getMaxEnergyStored())));
        tooltip.add(new TranslationTextComponent("test.tooltip.particle", getParticleId(stack)));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return !isCreative();
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1 - getChargeRatio(stack);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        //return MathHelper.hsvToRgb((1 + getChargeRatio(stack)) / 3.0F, 1.0F, 1.0F);
        return 0x03fc49;
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            items.add(new ItemStack(this));
            if (!isCreative()) {
                ItemStack full = new ItemStack(this);
                //full.getOrCreateTag().putInt(Constants.TAG_ENERGY, jetpackType.getEnergyCapacity());
                NBTUtil.setInt(full, Constants.TAG_ENERGY, jetpackType.getEnergyCapacity());
                items.add(full);
            }
        }
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        //if (!canReceive()) return 0;
        int energyStored = getEnergy(container);
        int energyReceived = Math.min(getCapacity(container) - energyStored, Math.min(getEnergyReceive(), maxReceive));
        if (!simulate) setEnergyStored(container, energyStored + energyReceived);
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        //if (!canExtract()) return 0;
        int energyStored = getEnergy(container);
        int energyExtracted = Math.min(energyStored, Math.min(getEnergyExtract(), maxExtract));
        if (!simulate) setEnergyStored(container, energyStored - energyExtracted);
        return energyExtracted;
    }

    @Override
    public int getEnergy(ItemStack container) {
        return container.getOrCreateTag().getInt(Constants.TAG_ENERGY);
    }

    private void setEnergyStored(ItemStack container, int value) {
        //container.getOrCreateTag().putInt(Constants.TAG_ENERGY, MathHelper.clamp(value, 0, getCapacity(container)));
        NBTUtil.setInt(container, Constants.TAG_ENERGY, MathHelper.clamp(value, 0, getCapacity(container)));
    }

    @Override
    public int getCapacity(ItemStack container) {
        return jetpackType.getEnergyCapacity();
    }

    public int getEnergyReceive() {
        return jetpackType.getEnergyPerTickIn();
    }

    public int getEnergyExtract() {
        return jetpackType.getEnergyUsage();
    }

    public static ItemStack setParticleId(ItemStack stack, ItemStack particle) {
        String key = particle.getDescriptionId().split("item.simplyjetpacks.particle_")[1].toUpperCase();
        int id = JetpackParticleType.valueOf(key).ordinal();
        //stack.getOrCreateTag().putInt(Constants.TAG_PARTICLE, id);
        NBTUtil.setInt(stack, Constants.TAG_PARTICLE, id);
        return stack;
    }

    public static void setParticleId(ItemStack stack, int id) {
        NBTUtil.setInt(stack, Constants.TAG_PARTICLE, id);
    }

    public static int getParticleId(ItemStack stack) {
        //return stack.getOrCreateTag().contains(Constants.TAG_PARTICLE) ? stack.getOrCreateTag().getInt(Constants.TAG_PARTICLE) : 0;
        return NBTUtil.getInt(stack, Constants.TAG_PARTICLE);
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
