package stormedpanda.simplyjetpacks.items;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import stormedpanda.simplyjetpacks.capability.CapabilityProviderEnergy;
import stormedpanda.simplyjetpacks.capability.EnergyConversionStorage;
import stormedpanda.simplyjetpacks.capability.IEnergyContainerItem;
import stormedpanda.simplyjetpacks.client.hud.IHUDInfoProvider;
import stormedpanda.simplyjetpacks.client.model.JetpackModel;
import stormedpanda.simplyjetpacks.client.particle.JetpackParticleType;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;
import stormedpanda.simplyjetpacks.handlers.SyncHandler;
import stormedpanda.simplyjetpacks.integration.IntegrationList;
import stormedpanda.simplyjetpacks.util.KeyboardUtil;
import stormedpanda.simplyjetpacks.util.NBTHelper;
import stormedpanda.simplyjetpacks.util.SJTextUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class JetpackItem extends ArmorItem implements IHUDInfoProvider, IEnergyContainerItem {

    public static final String TAG_ENERGY = "Energy";
    public static final String TAG_ENGINE = "Engine";
    public static final String TAG_HOVER = "Hover";
    public static final String TAG_E_HOVER = "EmergencyHover";
    public static final String TAG_CHARGER = "Charger";
    public static final String TAG_PARTICLE = "ParticleType";

    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;

    private final JetpackType type;
    public String name;
    private final String armorTexture;
    public final int tier;

    public JetpackItem(JetpackType type) {
        super(type.getArmorMaterial(), EquipmentSlotType.CHEST, type.getProperties());
        this.name = type.getName();
        this.tier = type.getTier();
        this.armorTexture = type.getArmorTexture();
        this.type = type;
        this.capacity = type.getCapacity();
        this.maxReceive = type.getMaxReceive();
        this.maxExtract = type.getMaxExtract();
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return armorTexture;
    }

    @SuppressWarnings("rawtypes")
    @OnlyIn(Dist.CLIENT)
    @Override
    public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel _default) {
        return new JetpackModel().applyData(_default);
    }

    public String getBaseName() { return name; }

    public JetpackType getType() { return type; }

    public int getCapacity() { return capacity; }

    public boolean isCreative() { return getBaseName().contains("creative"); }

    @Override
    public boolean hasEffect(@Nonnull ItemStack stack) {
        return (isCreative() || stack.isEnchanted());
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        super.onArmorTick(stack, world, player);
        flyUser(player, stack, this);
        if (this.type.canCharge() && this.isChargerOn(stack)) {
            chargeInventory(player, stack);
        }
    }

    public int getEnergyUsage(ItemStack stack) {
        int baseUsage = type.getEnergyUsage();
        int level = EnchantmentHelper.getEnchantmentLevel(RegistryHandler.FUEL_EFFICIENCY.get(), stack);
        return level != 0 ? (int) Math.round(baseUsage * (5 - level) / 5.0D) : baseUsage;
    }

    public void useEnergy(ItemStack container, int amount) {
        if (container.getTag() != null || container.getTag().contains(TAG_ENERGY)) {
            int stored = Math.min(container.getTag().getInt(TAG_ENERGY), getMaxEnergyStored(container));
            stored -= amount;
            if (stored < 0) stored = 0;
            container.getTag().putInt(TAG_ENERGY, stored);
        }
    }

    public void setParticleType(ItemStack stack, JetpackParticleType particle) {
        NBTHelper.setInt(stack, TAG_PARTICLE, particle.ordinal());
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (!container.hasTag()) {
            container.setTag(new CompoundNBT());
        }
        int stored = Math.min(container.getTag().getInt(TAG_ENERGY), getMaxEnergyStored(container));
        int energyReceived = Math.min(capacity - stored, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            stored += energyReceived;
            container.getTag().putInt(TAG_ENERGY, stored);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if (container.getTag() == null || !container.getTag().contains(TAG_ENERGY)) {
            return 0;
        }
        int stored = Math.min(container.getTag().getInt(TAG_ENERGY), getMaxEnergyStored(container));
        int energyExtracted = Math.min(stored, Math.min(this.maxExtract, maxExtract));

        if (!simulate) {
            stored -= energyExtracted;
            container.getTag().putInt(TAG_ENERGY, stored);
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        if (container.getTag() == null || !container.getTag().contains(TAG_ENERGY)) {
            return 0;
        }
        return Math.min(container.getTag().getInt(TAG_ENERGY), getMaxEnergyStored(container));
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return capacity;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        return new CapabilityProviderEnergy(new EnergyConversionStorage(this, stack));
    }

    private static float getChargeRatio(ItemStack stack) {
        LazyOptional<IEnergyStorage> optional = stack.getCapability(CapabilityEnergy.ENERGY);
        if (optional.isPresent()) {
            IEnergyStorage energyStorage = optional.orElseThrow(IllegalStateException::new);
            return (float) energyStorage.getEnergyStored() / energyStorage.getMaxEnergyStored();
        }
        return 0;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (CapabilityEnergy.ENERGY == null) return;
        SJTextUtil.addBaseInfo(stack, tooltip);
        if (KeyboardUtil.isHoldingShift()) {
            SJTextUtil.addShiftInfo(stack, tooltip);
        } else {
            tooltip.add(SJTextUtil.getShiftText());
        }
    }

    @Override
    public void fillItemGroup(@Nonnull ItemGroup group, @Nonnull NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            if (isCreative()) {
                items.add(new ItemStack(this));
            }
            if (IntegrationList.integrateVanilla) {
                if (getBaseName().contains("vanilla")) {
                    items.add(new ItemStack(this));
                    ItemStack full = new ItemStack(this);
                    full.getOrCreateTag().putInt(TAG_ENERGY, capacity);
                    items.add(full);
                }
            }
            if (IntegrationList.integrateImmersiveEngineering) {
                if (getBaseName().contains("ie")) {
                    items.add(new ItemStack(this));
                    ItemStack full = new ItemStack(this);
                    full.getOrCreateTag().putInt(TAG_ENERGY, capacity);
                    items.add(full);
                }
            }
            if (IntegrationList.integrateMekanism) {
                if (getBaseName().contains("mek")) {
                    items.add(new ItemStack(this));
                    ItemStack full = new ItemStack(this);
                    full.getOrCreateTag().putInt(TAG_ENERGY, capacity);
                    items.add(full);
                }
            }
        }
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
        return 0x03fc49;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addHUDInfo(ItemStack stack, List<ITextComponent> list) {
        SJTextUtil.addHUDInfoText(stack, list);
    }

    public boolean isEngineOn(ItemStack stack) {
        return NBTHelper.getBoolean(stack, TAG_ENGINE);
    }
    public void toggleEngine(ItemStack stack, PlayerEntity player) {
        boolean current = NBTHelper.getBoolean(stack, TAG_ENGINE);
        NBTHelper.flipBoolean(stack, TAG_ENGINE);
        ITextComponent msg = SJTextUtil.getStateToggle("engineMode", !current);
        player.sendStatusMessage(msg, true);
    }

    public boolean isHoverOn(ItemStack stack) {
        return NBTHelper.getBoolean(stack, TAG_HOVER);
    }
    public void toggleHover(ItemStack stack, PlayerEntity player) {
        boolean current = NBTHelper.getBoolean(stack, TAG_HOVER);
        NBTHelper.flipBoolean(stack, TAG_HOVER);
        ITextComponent msg = SJTextUtil.getStateToggle("hoverMode", !current);
        player.sendStatusMessage(msg, true);
    }

    public boolean isEHoverOn(ItemStack stack) {
        return NBTHelper.getBoolean(stack, TAG_E_HOVER);
    }
    public void toggleEHover(ItemStack stack, PlayerEntity player) {
        if (type.canEHover()) {
            boolean current = NBTHelper.getBoolean(stack, TAG_E_HOVER);
            NBTHelper.flipBoolean(stack, TAG_E_HOVER);
            ITextComponent msg = SJTextUtil.getStateToggle("emergencyHoverMode", !current);
            player.sendStatusMessage(msg, true);
        }
    }
    private void doEHover(ItemStack stack, PlayerEntity player) {
        NBTHelper.setBoolean(stack, TAG_ENGINE, true);
        NBTHelper.setBoolean(stack, TAG_HOVER, true);
        ITextComponent msg = SJTextUtil.getEmergencyText();
        player.sendStatusMessage(msg, true);
    }

    public boolean isChargerOn(ItemStack stack) {
        return NBTHelper.getBoolean(stack, TAG_CHARGER);
    }
    public void toggleCharger(ItemStack stack, PlayerEntity player) {
        if (type.canCharge()) {
            boolean current = NBTHelper.getBoolean(stack, TAG_CHARGER);
            NBTHelper.flipBoolean(stack, TAG_CHARGER);
            ITextComponent msg = SJTextUtil.getStateToggle("chargerMode", !current);
            player.sendStatusMessage(msg, true);
        }
    }
    private void chargeInventory(PlayerEntity player, ItemStack stack) {
        if (!player.world.isRemote) {
            if (getEnergyStored(stack) > 0 || isCreative()) {
                for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                    ItemStack itemStack = player.inventory.getStackInSlot(i);
                    if (!itemStack.equals(stack) && itemStack.getCapability(CapabilityEnergy.ENERGY).isPresent()) {
                        LazyOptional<IEnergyStorage> optional = itemStack.getCapability(CapabilityEnergy.ENERGY);
                        if (optional.isPresent()) {
                            IEnergyStorage energyStorage = optional.orElseThrow(IllegalStateException::new);
                            if (isCreative()) {
                                energyStorage.receiveEnergy(1000, false);
                            } else {
                                useEnergy(stack,energyStorage.receiveEnergy(getEnergyUsage(stack),false));
                            }
                        }
                    }
                }
            }
        }
    }

    @Nonnull
    @Override
    public Rarity getRarity(@Nonnull ItemStack stack) {
        return type.getRarity();
    }

    @Override
    public boolean isEnchantable(@Nonnull ItemStack stack) {
        return true;
    }

    private void fly(PlayerEntity player, double y) {
        Vector3d motion = player.getMotion();
        player.setMotion(motion.getX(), y, motion.getZ());
    }

    private void flyUser(PlayerEntity player, ItemStack stack, JetpackItem item) {
        if (isEngineOn(stack)) {
            boolean hoverMode = isHoverOn(stack);
            double hoverSpeed = SimplyJetpacksConfig.CLIENT.invertHoverSneakingBehavior.get() == SyncHandler.isHoldingDown(player) ? type.getSpeedVerticalHoverSlow() : type.getSpeedVerticalHover();
            boolean flyKeyDown = SyncHandler.isHoldingUp(player);
            boolean descendKeyDown = SyncHandler.isHoldingDown(player);
            double currentAccel = type.getAccelVertical() * (player.getMotion().getY() < 0.3D ? 2.5D : 1.0D);
            double currentSpeedVertical = type.getSpeedVertical() * (player.isInWater() ? 0.4D : 1.0D);
            double speedVerticalHover = type.getSpeedVerticalHover();
            double speedVerticalHoverSlow = type.getSpeedVerticalHoverSlow();

            if ((flyKeyDown || hoverMode && !player.isOnGround())) {
                if (!isCreative()) {
                    int amount = (int) (player.isSprinting() ? Math.round(getEnergyUsage(stack) * type.getSprintEnergyModifier()) : getEnergyUsage(stack));
                    useEnergy(stack, amount);
                }
                if (getEnergyStored(stack) > 0 || isCreative()) {
                    if (flyKeyDown) {
                        if (!hoverMode) {
                            fly(player, Math.min(player.getMotion().getY() + currentAccel, currentSpeedVertical));
                        } else {
                            if (descendKeyDown) {
                                fly(player, Math.min(player.getMotion().getY() + currentAccel, -speedVerticalHoverSlow));
                            } else {
                                fly(player, Math.min(player.getMotion().getY() + currentAccel, speedVerticalHover));
                            }
                        }
                    } else {
                        fly(player, Math.min(player.getMotion().getY() + currentAccel, -hoverSpeed));
                    }

                    double baseSpeedSideways = type.getSpeedSideways();
                    double baseSpeedForward = type.getSprintSpeedModifier();
                    float speedSideways = (float) (player.isSneaking() ? baseSpeedSideways * 0.5F : baseSpeedSideways);
                    float speedForward = (float) (player.isSprinting() ? speedSideways * baseSpeedForward : speedSideways);

                    if (SyncHandler.isHoldingForwards(player)) {
                        player.moveRelative(1, new Vector3d(0, 0, speedForward));
                    }
                    if (SyncHandler.isHoldingBackwards(player)) {
                        player.moveRelative(1, new Vector3d(0, 0, -speedSideways * 0.8F));
                    }
                    if (SyncHandler.isHoldingLeft(player)) {
                        player.moveRelative(1, new Vector3d(speedSideways, 0, 0));
                    }
                    if (SyncHandler.isHoldingRight(player)) {
                        player.moveRelative(1, new Vector3d(-speedSideways, 0, 0));
                    }
                    if (!player.world.isRemote()) {
                        player.fallDistance = 0.0F;
                        if (player instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity) player).connection.floatingTickCount = 0;
                        }
                    }
                }
            }
        }
        if (!player.world.isRemote && this.isEHoverOn(stack)) {
            if (item.getEnergyStored(stack) > 0 && (!this.isHoverOn(stack) || !this.isEngineOn(stack))) {
                if (player.getPositionVec().getY() < -5) {
                    this.doEHover(stack, player);
                } else {
                    if (!player.isCreative() && player.fallDistance - 1.2F >= player.getHealth()) {
                        for (int j = 0; j <= 16; j++) {
                            int x = Math.round((float) player.getPositionVec().getX() - 0.5F);
                            int y = Math.round((float) player.getPositionVec().getY()) - j;
                            int z = Math.round((float) player.getPositionVec().getZ() - 0.5F);
                            if (!player.world.isAirBlock(new BlockPos(x, y, z))) {
                                this.doEHover(stack, player);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}