package tomson124.simplyjetpacks.item;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import tomson124.simplyjetpacks.handlers.CommonJetpackHandler;
import tomson124.simplyjetpacks.hud.IHUDInfoProvider;
import tomson124.simplyjetpacks.init.SJDataComponentTypes;
import tomson124.simplyjetpacks.model.JetpackModelLayers;
import tomson124.simplyjetpacks.util.JetpackUtil;
import tomson124.simplyjetpacks.util.KeyboardUtil;
import tomson124.simplyjetpacks.util.SJTextUtil;

import java.util.List;
import java.util.function.Consumer;

import static tomson124.simplyjetpacks.util.JetpackUtil.getThrottle;

public class JetpackItem extends ArmorItem implements IHUDInfoProvider {

    public JetpackItem() {
        this(JetpackArmorMaterial.JETPACK);
    }

    protected JetpackItem(Holder<ArmorMaterial> material) {
        super(material, Type.CHESTPLATE, new Item.Properties().stacksTo(1).setNoRepair());
    }

    public static ItemStack createStack(Jetpack jetpack, boolean charged) {
        ItemStack stack = new ItemStack(tomson124.simplyjetpacks.handlers.RegistryHandler.JETPACK.get());
        stack.set(SJDataComponentTypes.JETPACK_ID, jetpack.getId());
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, jetpack.createAttributeModifiers());
        stack.set(DataComponents.RARITY, jetpack.rarity);
        stack.set(SJDataComponentTypes.JETPACK_ENERGY, charged || jetpack.creative ? jetpack.capacity : 0);
        return stack;
    }

    @OnlyIn(Dist.CLIENT)
    private static class Rendering implements IClientItemExtensions {
        private static final JetpackItem.Rendering INSTANCE = new JetpackItem.Rendering();

        private Rendering() {
        }

        @Override
        public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
            return JetpackModelLayers.JETPACK_MODEL;
        }
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(Rendering.INSTANCE);
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return JetpackUtil.getJetpack(stack).getArmorTexture();
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        var jetpack = JetpackUtil.getJetpack(stack);
        return jetpack == Jetpack.UNDEFINED ? super.getName(stack) : jetpack.getDisplayName();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity holder, int vanillaIndex, boolean selected) {
        if (holder instanceof Player player && !player.isSpectator()
                && !player.getAbilities().flying && JetpackUtil.checkTickForEquippedSlot(vanillaIndex, stack, player)) {
            flyUser(player, stack, this, false);
            if (JetpackUtil.getJetpack(stack).chargerMode && this.isChargerOn(stack)) {
                chargeInventory(player, stack);
            }
        }
        super.inventoryTick(stack, level, holder, vanillaIndex, selected);
    }

    public boolean isCreative(ItemStack stack) {
        return JetpackUtil.getJetpack(stack).creative;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return super.isFoil(stack) || isCreative(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return JetpackUtil.getJetpack(stack).enchantablilty > 0;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return JetpackUtil.getJetpack(stack).enchantablilty;
    }

    // -- Toggles ------------------------------------------------------------------------------------

    public boolean isEngineOn(ItemStack stack) {
        return stack.getOrDefault(SJDataComponentTypes.JETPACK_ENGINE, false);
    }

    public void toggleEngine(ItemStack stack, Player player) {
        boolean next = !isEngineOn(stack);
        stack.set(SJDataComponentTypes.JETPACK_ENGINE, next);
        player.displayClientMessage(SJTextUtil.getStateToggle("engineMode", next), true);
    }

    public boolean isHoverOn(ItemStack stack) {
        return stack.getOrDefault(SJDataComponentTypes.JETPACK_HOVER, false);
    }

    public void toggleHover(ItemStack stack, Player player) {
        if (JetpackUtil.getJetpack(stack).hoverMode) {
            boolean next = !isHoverOn(stack);
            stack.set(SJDataComponentTypes.JETPACK_HOVER, next);
            player.displayClientMessage(SJTextUtil.getStateToggle("hoverMode", next), true);
        }
    }

    public boolean isEHoverOn(ItemStack stack) {
        return stack.getOrDefault(SJDataComponentTypes.JETPACK_E_HOVER, false);
    }

    public void toggleEHover(ItemStack stack, Player player) {
        if (JetpackUtil.getJetpack(stack).emergencyHoverMode) {
            boolean next = !isEHoverOn(stack);
            stack.set(SJDataComponentTypes.JETPACK_E_HOVER, next);
            player.displayClientMessage(SJTextUtil.getStateToggle("emergencyHoverMode", next), true);
        }
    }

    private void doEHover(ItemStack stack, Player player) {
        if (JetpackUtil.getJetpack(stack).hoverMode) {
            stack.set(SJDataComponentTypes.JETPACK_ENGINE, true);
            stack.set(SJDataComponentTypes.JETPACK_HOVER, true);
            player.displayClientMessage(SJTextUtil.getEmergencyText(), true);
        }
    }

    public boolean isChargerOn(ItemStack stack) {
        return stack.getOrDefault(SJDataComponentTypes.JETPACK_CHARGER, false);
    }

    public void toggleCharger(ItemStack stack, Player player) {
        if (JetpackUtil.getJetpack(stack).chargerMode) {
            boolean next = !isChargerOn(stack);
            stack.set(SJDataComponentTypes.JETPACK_CHARGER, next);
            player.displayClientMessage(SJTextUtil.getStateToggle("chargerMode", next), true);
        }
    }

    // -- Particles ----------------------------------------------------------------------------------

    public static ItemStack setParticleId(ItemStack stack, ItemStack particle) {
        String key = particle.getDescriptionId().split("item.simplyjetpacks.particle_")[1].toUpperCase();
        setParticleId(stack, tomson124.simplyjetpacks.particle.JetpackParticleType.valueOf(key).ordinal());
        return stack;
    }

    public static void setParticleId(ItemStack stack, int id) {
        stack.set(SJDataComponentTypes.JETPACK_PARTICLE, id);
    }

    public static int getParticleId(ItemStack stack) {
        var id = stack.get(SJDataComponentTypes.JETPACK_PARTICLE);
        return id != null ? id : JetpackUtil.getJetpack(stack).defaultParticle;
    }

    // -- Energy -------------------------------------------------------------------------------------

    public int getCapacity(ItemStack stack) {
        return JetpackUtil.getJetpack(stack).capacity;
    }

    public static float getChargeRatio(ItemStack stack) {
        var energy = JetpackUtil.getEnergyStorage(stack);
        if (energy != null && energy.getMaxEnergyStored() > 0) {
            return (float) energy.getEnergyStored() / energy.getMaxEnergyStored();
        }
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        SJTextUtil.addBaseInfo(stack, tooltip);
        if (KeyboardUtil.isHoldingShift()) {
            SJTextUtil.addShiftInfo(stack, tooltip);
        } else {
            tooltip.add(SJTextUtil.getShiftText());
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return !JetpackUtil.getJetpack(stack).creative && JetpackUtil.getEnergyStorage(stack).getEnergyStored() > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13.0F * getChargeRatio(stack));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0x03fc49;
    }

    public int getEnergyUsage(ItemStack stack) {
        int baseUsage = JetpackUtil.getJetpack(stack).usage;
        int level = JetpackUtil.getFuelEfficiencyLevel(stack);
        return level != 0 ? (int) Math.round(baseUsage * (5 - level) / 5.0D) : baseUsage;
    }

    public void chargeInventory(Player player, ItemStack stack) {
        var energy = JetpackUtil.getEnergyStorage(stack);
        var jetpack = JetpackUtil.getJetpack(stack);
        if (!player.getCommandSenderWorld().isClientSide) {
            if (energy.getEnergyStored() > 0 || jetpack.creative) {
                for (ItemStack itemStack : player.getHandSlots()) {
                    charge(stack, itemStack);
                }
                for (ItemStack itemStack : player.getArmorSlots()) {
                    charge(stack, itemStack);
                }
            }
        }
    }

    /** Moves energy out of the jetpack and into another held/worn item. */
    private void charge(ItemStack stack, ItemStack target) {
        if (target.isEmpty() || target == stack) {
            return;
        }
        var targetEnergy = JetpackUtil.getEnergyStorage(target);
        if (targetEnergy == null || !targetEnergy.canReceive()) {
            return;
        }
        var jetpack = JetpackUtil.getJetpack(stack);
        int rate = getEnergyUsage(stack);
        if (jetpack.creative) {
            targetEnergy.receiveEnergy(rate, false);
            return;
        }
        var source = JetpackUtil.getEnergyStorage(stack);
        int available = source.extractEnergy(rate, true);
        int accepted = targetEnergy.receiveEnergy(available, false);
        if (accepted > 0) {
            source.extractEnergy(accepted, false);
        }
    }

    private void fly(Player player, double y) {
        Vec3 motion = player.getDeltaMovement();
        player.setDeltaMovement(motion.get(Direction.Axis.X), y, motion.get(Direction.Axis.Z));
    }

    public void flyUser(Player player, ItemStack stack, JetpackItem item, Boolean force) {
        var energy = JetpackUtil.getEnergyStorage(stack);
        var jetpack = JetpackUtil.getJetpack(stack);
        if (isEngineOn(stack)) {
            boolean hoverMode = isHoverOn(stack);
            double hoverSpeed = CommonJetpackHandler.isInverted(player) == CommonJetpackHandler.isHoldingDown(player) ? jetpack.speedHoverSlow : jetpack.speedHover;
            boolean flyKeyDown = force || CommonJetpackHandler.isHoldingUp(player);
            boolean descendKeyDown = CommonJetpackHandler.isHoldingDown(player);
            double currentAccel = jetpack.accelVert * (player.getDeltaMovement().get(Direction.Axis.Y) < 0.3D ? 2.5D : 1.0D);
            double currentSpeedVertical = jetpack.speedVert * (player.isInWater() ? 0.4D : 1.0D);
            double speedVerticalHover = jetpack.speedHover;
            double speedVerticalHoverSlow = jetpack.speedHoverSlow;

            if ((flyKeyDown || hoverMode && !player.onGround())) {
                if (!jetpack.creative) {
                    int amount = (int) (player.isSprinting() ? Math.round(getEnergyUsage(stack) * jetpack.sprintEnergyModifier) : getEnergyUsage(stack));
                    energy.extractEnergy(amount, false);
                }
                if (energy.getEnergyStored() > 0 || jetpack.creative) {
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

                    double baseSpeedSideways = jetpack.speedSide;
                    double sprintSpeedModifier = jetpack.sprintSpeedModifier;
                    double speedSideways = (player.isCrouching() ? baseSpeedSideways * 0.5D : baseSpeedSideways) * getThrottle(stack);
                    double speedForward = (player.isSprinting() ? speedSideways * sprintSpeedModifier : speedSideways);

                    if (CommonJetpackHandler.isHoldingForwards(player)) {
                        player.moveRelative(1, new Vec3(0, 0, speedForward));
                    }
                    if (CommonJetpackHandler.isHoldingBackwards(player)) {
                        player.moveRelative(1, new Vec3(0, 0, -speedSideways * 0.8D));
                    }
                    if (CommonJetpackHandler.isHoldingLeft(player)) {
                        player.moveRelative(1, new Vec3(speedSideways, 0, 0));
                    }
                    if (CommonJetpackHandler.isHoldingRight(player)) {
                        player.moveRelative(1, new Vec3(-speedSideways, 0, 0));
                    }
                    if (!player.getCommandSenderWorld().isClientSide()) {
                        player.fallDistance = 0.0F;
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.connection.aboveGroundTickCount = 0;
                        }
                    }
                }
            }
        }
        if (!player.getCommandSenderWorld().isClientSide && this.isEHoverOn(stack)) {
            if ((energy.getEnergyStored() > 0 || jetpack.creative) && (!this.isHoverOn(stack) || !this.isEngineOn(stack))) {
                if (player.position().get(Direction.Axis.Y) < player.level().getMinBuildHeight() + 5) {
                    this.doEHover(stack, player);
                } else if (!player.isCreative() && player.fallDistance - 1.2F >= player.getHealth()
                        && !player.onGround() && !player.isSwimming()) {
                    this.doEHover(stack, player);
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addHUDInfo(ItemStack stack, List<Component> list) {
        SJTextUtil.addHUDInfoText(stack, list);
    }
}
