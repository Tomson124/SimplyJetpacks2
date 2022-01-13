package stormedpanda.simplyjetpacks.item;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.energy.EnergyStorageImpl;
import stormedpanda.simplyjetpacks.energy.IEnergyContainer;
import stormedpanda.simplyjetpacks.handlers.CommonJetpackHandler;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;
import stormedpanda.simplyjetpacks.hud.IHUDInfoProvider;
import stormedpanda.simplyjetpacks.model.JetpackModel;
import stormedpanda.simplyjetpacks.particle.JetpackParticleType;
import stormedpanda.simplyjetpacks.util.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class JetpackItem extends ArmorItem implements IHUDInfoProvider, IEnergyContainer {

    private final JetpackType jetpackType;
    public final int tier;

    public JetpackItem(JetpackType jetpackType) {
        super(JetpackArmorMaterial.JETPACK, EquipmentSlotType.CHEST, new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
        this.jetpackType = jetpackType;
        this.tier = jetpackType.getTier();
    }

    public JetpackItem(JetpackType jetpackType, JetpackArmorMaterial material) {
        super(material, EquipmentSlotType.CHEST, new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
        this.jetpackType = jetpackType;
        this.tier = jetpackType.getTier();
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (!player.isSpectator() && stack == JetpackUtil.getFromBothSlots(player)) {
            flyUser(player, stack, this, false);
            if (this.jetpackType.getChargerMode() && this.isChargerOn(stack)) {
                chargeInventory(player, stack);
            }
        }
    }

    public JetpackType getJetpackType() {
        return jetpackType;
    }

    public boolean isCreative() {
        return jetpackType.getName().contains("creative");
    }

    @Override
    public int getEnchantmentValue() {
        return super.getEnchantmentValue() + jetpackType.getEnchantability();
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return super.isFoil(stack) || isCreative();
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return jetpackType.getRarity();
    }

    public String getModId() {
        String name = jetpackType.getName();
        if (name.contains("mek")) {
            return "mek";
        }
        if (name.contains("ie")) {
            return "ie";
        }
        return "sj";
    }

    public boolean isEngineOn(ItemStack stack) {
        return NBTUtil.getBoolean(stack, Constants.TAG_ENGINE);
    }

    public void toggleEngine(ItemStack stack, PlayerEntity player) {
        boolean current = NBTUtil.getBoolean(stack, Constants.TAG_ENGINE);
        NBTUtil.flipBoolean(stack, Constants.TAG_ENGINE);
        ITextComponent msg = SJTextUtil.getStateToggle("engineMode", !current);
        player.displayClientMessage(msg, true);
    }

    public boolean isHoverOn(ItemStack stack) {
        return NBTUtil.getBoolean(stack, Constants.TAG_HOVER);
    }

    public void toggleHover(ItemStack stack, PlayerEntity player) {
        if (jetpackType.getHoverMode()) {
            boolean current = NBTUtil.getBoolean(stack, Constants.TAG_HOVER);
            NBTUtil.flipBoolean(stack, Constants.TAG_HOVER);
            ITextComponent msg = SJTextUtil.getStateToggle("hoverMode", !current);
            player.displayClientMessage(msg, true);
        }
    }

    public boolean isEHoverOn(ItemStack stack) {
        return NBTUtil.getBoolean(stack, Constants.TAG_E_HOVER);
    }

    public void toggleEHover(ItemStack stack, PlayerEntity player) {
        if (jetpackType.getEmergencyHoverMode()) {
            boolean current = NBTUtil.getBoolean(stack, Constants.TAG_E_HOVER);
            NBTUtil.flipBoolean(stack, Constants.TAG_E_HOVER);
            ITextComponent msg = SJTextUtil.getStateToggle("emergencyHoverMode", !current);
            player.displayClientMessage(msg, true);
        }
    }

    private void doEHover(ItemStack stack, PlayerEntity player) {
        if (jetpackType.getHoverMode()) {
            NBTUtil.setBoolean(stack, Constants.TAG_ENGINE, true);
            NBTUtil.setBoolean(stack, Constants.TAG_HOVER, true);
            ITextComponent msg = SJTextUtil.getEmergencyText();
            player.displayClientMessage(msg, true);
        }
    }

    public boolean isChargerOn(ItemStack stack) {
        return NBTUtil.getBoolean(stack, Constants.TAG_CHARGER);
    }

    public void toggleCharger(ItemStack stack, PlayerEntity player) {
        if (jetpackType.getChargerMode()) {
            boolean current = NBTUtil.getBoolean(stack, Constants.TAG_CHARGER);
            NBTUtil.flipBoolean(stack, Constants.TAG_CHARGER);
            ITextComponent msg = SJTextUtil.getStateToggle("chargerMode", !current);
            player.displayClientMessage(msg, true);
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
        SJTextUtil.addBaseInfo(stack, tooltip);
        if (KeyboardUtil.isHoldingShift()) {
            SJTextUtil.addShiftInfo(stack, tooltip);
        } else {
            tooltip.add(SJTextUtil.getShiftText());
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return !isCreative() && getEnergy(stack) > 0;
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

    private void setEnergyStored(ItemStack container, int value) {
        NBTUtil.setInt(container, Constants.TAG_ENERGY, MathHelper.clamp(value, 0, getCapacity(container)));
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
        NBTUtil.setInt(stack, Constants.TAG_PARTICLE, id);
        return stack;
    }

    public static void setParticleId(ItemStack stack, int id) {
        NBTUtil.setInt(stack, Constants.TAG_PARTICLE, id);
    }

    public static int getParticleId(ItemStack stack) {
        return stack.getOrCreateTag().contains(Constants.TAG_PARTICLE) ? stack.getOrCreateTag().getInt(Constants.TAG_PARTICLE) : JetpackType.getDefaultParticles(stack);
    }

    public void setThrottle(ItemStack stack, int value) {
        NBTUtil.setInt(stack, Constants.TAG_THROTTLE, value);
    }

    public int getThrottle(ItemStack stack) {
        return stack.getOrCreateTag().contains(Constants.TAG_THROTTLE) ? stack.getOrCreateTag().getInt(Constants.TAG_THROTTLE) : 100;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return jetpackType.getArmorTexture();
    }

    @OnlyIn(Dist.CLIENT)
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) new JetpackModel();
    }

    public void useEnergy(ItemStack container, int amount) {
        if (container.getTag() != null || container.getTag().contains(Constants.TAG_ENERGY)) {
            int stored = Math.min(container.getTag().getInt(Constants.TAG_ENERGY), getCapacity(container));
            stored -= amount;
            if (stored < 0) stored = 0;
            container.getTag().putInt(Constants.TAG_ENERGY, stored);
        }
    }

    public int getEnergyUsage(ItemStack stack) {
        int baseUsage = jetpackType.getEnergyUsage();
        int level = EnchantmentHelper.getItemEnchantmentLevel(RegistryHandler.FUEL_EFFICIENCY.get(), stack);
        return level != 0 ? (int) Math.round(baseUsage * (5 - level) / 5.0D) : baseUsage;
    }

    public void chargeInventory(PlayerEntity player, ItemStack stack) {
        if (!player.getCommandSenderWorld().isClientSide) {
            if (getEnergy(stack) > 0 || isCreative()) {
                for (int i = 0; i < player.inventory.getContainerSize(); i++) {
                    ItemStack itemStack = player.inventory.getItem(i);
                    if (!itemStack.equals(stack) && itemStack.getCapability(CapabilityEnergy.ENERGY).isPresent()) {
                        LazyOptional<IEnergyStorage> optional = itemStack.getCapability(CapabilityEnergy.ENERGY);
                        if (optional.isPresent()) {
                            IEnergyStorage energyStorage = optional.orElseThrow(IllegalStateException::new);
                            if (isCreative()) {
                                energyStorage.receiveEnergy(1000, false);
                            } else {
                                useEnergy(stack, energyStorage.receiveEnergy(getEnergyUsage(stack),false));
                            }
                        }
                    }
                }
            }
        }
    }

    private void fly(PlayerEntity player, double y) {
        Vector3d motion = player.getDeltaMovement();
        player.setDeltaMovement(motion.get(Direction.Axis.X), y, motion.get(Direction.Axis.Z));
    }

    public void flyUser(PlayerEntity player, ItemStack stack, JetpackItem item, Boolean force) {
        if (isEngineOn(stack)) {
            boolean hoverMode = isHoverOn(stack);
            double hoverSpeed = SimplyJetpacksConfig.invertHoverSneakingBehavior.get() == CommonJetpackHandler.isHoldingDown(player) ? jetpackType.getSpeedVerticalHoverSlow() : jetpackType.getSpeedVerticalHover();
            boolean flyKeyDown = force || CommonJetpackHandler.isHoldingUp(player);
            boolean descendKeyDown = CommonJetpackHandler.isHoldingDown(player);
            double currentAccel = jetpackType.getAccelVertical() * (player.getDeltaMovement().get(Direction.Axis.Y) < 0.3D ? 2.5D : 1.0D);
            double currentSpeedVertical = jetpackType.getSpeedVertical() * (player.isInWater() ? 0.4D : 1.0D);
            double speedVerticalHover = jetpackType.getSpeedVerticalHover();
            double speedVerticalHoverSlow = jetpackType.getSpeedVerticalHoverSlow();

            if ((flyKeyDown || hoverMode && !player.isOnGround())) {
                if (!isCreative()) {
                    int amount = (int) (player.isSprinting() ? Math.round(getEnergyUsage(stack) * jetpackType.getSprintEnergyModifier()) : getEnergyUsage(stack));
                    useEnergy(stack, amount);
                }
                if (getEnergy(stack) > 0 || isCreative()) {
                    if (flyKeyDown) {
                        if (!hoverMode) {
                            fly(player, Math.min(player.getDeltaMovement().get(Direction.Axis.Y) + currentAccel, currentSpeedVertical));
                        } else {
                            if (descendKeyDown) {
                                fly(player, Math.min(player.getDeltaMovement().get(Direction.Axis.Y) + currentAccel, -speedVerticalHoverSlow));
                            } else {
                                fly(player, Math.min(player.getDeltaMovement().get(Direction.Axis.Y) + currentAccel, speedVerticalHover));
                            }
                        }
                    } else {
                        fly(player, Math.min(player.getDeltaMovement().get(Direction.Axis.Y) + currentAccel, -hoverSpeed));
                    }

                    double baseSpeedSideways = jetpackType.getSpeedSideways() ;
                    double sprintSpeedModifier = jetpackType.getSprintSpeedModifier();
                    float speedSideways = (float) (player.isCrouching() ? baseSpeedSideways * 0.5F : baseSpeedSideways) * (getThrottle(stack) / 100.0F);
                    float speedForward = (float) (player.isSprinting() ? speedSideways * sprintSpeedModifier : speedSideways) * (getThrottle(stack) / 100.0F);

                    if (CommonJetpackHandler.isHoldingForwards(player)) {
                        player.moveRelative(1, new Vector3d(0, 0, speedForward));
                    }
                    if (CommonJetpackHandler.isHoldingBackwards(player)) {
                        player.moveRelative(1, new Vector3d(0, 0, -speedSideways * 0.8F));
                    }
                    if (CommonJetpackHandler.isHoldingLeft(player)) {
                        player.moveRelative(1, new Vector3d(speedSideways, 0, 0));
                    }
                    if (CommonJetpackHandler.isHoldingRight(player)) {
                        player.moveRelative(1, new Vector3d(-speedSideways, 0, 0));
                    }
                    if (!player.getCommandSenderWorld().isClientSide()) {
                        player.fallDistance = 0.0F;
                        if (player instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity) player).connection.aboveGroundTickCount = 0;
                        }
                    }
                }
            }
        }
        if (!player.level.isClientSide && this.isEHoverOn(stack)) {
            if ((item.getEnergy(stack) > 0 || this.isCreative()) && (!this.isHoverOn(stack) || !this.isEngineOn(stack))) {
                if (player.position().get(Direction.Axis.Y) < -5) {
                    this.doEHover(stack, player);
                } else {
                    if (!player.isCreative() && player.fallDistance - 1.2F >= player.getHealth()) {
                        for (int j = 0; j <= 16; j++) {
                            if (!player.isOnGround() && !player.isSwimming()) {
                                this.doEHover(stack, player);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addHUDInfo(ItemStack stack, List<ITextComponent> list) {
        SJTextUtil.addHUDInfoText(stack, list);
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (getEnergyReceive() == 0) return 0;
        int energyStored = getEnergy(container);
        int energyReceived = Math.min(getCapacity(container) - energyStored, Math.min(getEnergyReceive(), maxReceive));
        if (!simulate) setEnergyStored(container, energyStored + energyReceived);
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if (getEnergyExtract() == 0) return 0;
        int energyStored = getEnergy(container);
        int energyExtracted = Math.min(energyStored, Math.min(getEnergyExtract(), maxExtract));
        if (!simulate) setEnergyStored(container, energyStored - energyExtracted);
        return energyExtracted;
    }

    @Override
    public int getEnergy(ItemStack container) {
        return container.getOrCreateTag().getInt(Constants.TAG_ENERGY);
    }

    @Override
    public int getCapacity(ItemStack container) {
        return jetpackType.getEnergyCapacity();
    }

}
