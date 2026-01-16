package tomson124.simplyjetpacks.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.datagen.SJDamageTypes;
import tomson124.simplyjetpacks.handlers.CommonJetpackHandler;
import tomson124.simplyjetpacks.handlers.RegistryHandler;
import tomson124.simplyjetpacks.util.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import static tomson124.simplyjetpacks.datagen.SJDamageTypes.DEATH_BY_JETPACK_EXPLOSION;
import static tomson124.simplyjetpacks.datagen.SJDamageTypes.DEATH_BY_POTATO_JETPACK;

public class PotatoJetpackItem extends JetpackItem {

    public PotatoJetpackItem() {
        super(JetpackType.POTATO, JetpackArmorMaterial.POTATO);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(SJTextUtil.translate("tooltip", "jetpack_potato"));
        SJTextUtil.addBaseInfo(stack, tooltip);
        if (KeyboardUtil.isHoldingShift()) {
            tooltip.add(SJTextUtil.translate("tooltip", "jetpack_potato.warning", ChatFormatting.RED));
        } else {
            tooltip.add(SJTextUtil.getShiftText());
        }
    }

    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (!player.isSpectator() && stack == JetpackUtil.getFromBothSlots(player)) {
            this.flyUser(player, stack, this, true);
            if (getJetpackType().getChargerMode() && this.isChargerOn(stack)) {
                super.chargeInventory(player, stack);
            }
        }
    }

    @Override
    public void flyUser(Player player, ItemStack stack, JetpackItem item, Boolean force) {
        if (super.isEngineOn(stack)) {
            if (this.isFired(stack)) {
                super.flyUser(player, stack, item, true);
                player.yHeadRot += 37.5F;
                var energyItem = JetpackUtil.getEnergyStorage(new ItemStack(item));
                if (energyItem.getEnergyStored() <= 0) {
                    Random random = new Random();
                    player.getInventory().removeItem(stack);
                    if (!player.getCommandSenderWorld().isClientSide()) {
                        player.getCommandSenderWorld().explode(player, player.getX(), player.getY(), player.getZ(), 4.0F, Level.ExplosionInteraction.NONE);
                    }
                    for (int i = 0; i <= random.nextInt(3) + 4; i++) {
                        SimplyJetpacks.LOGGER.info("SJ2: CREATING FIREWORKS!");
                        // TODO: create some fireworks.
                        //ItemStack firework = FireworksHelper.getRandomFireworks(0, 1, new Random().nextInt(6) + 1, 1);
                        //player.level.createFireworks(new ProjectileImpactEvent.FireworkRocket(player.level, player.getX() + new Random().nextDouble() * 6.0D - 3.0D, player.getY(), player.getZ() + new Random().nextDouble() * 6.0D - 3.0D, firework));
                    }

                    player.drop(new ItemStack(Items.BAKED_POTATO), false);
                    player.hurt(SJDamageTypes.provideDamage(player.getCommandSenderWorld(), random.nextBoolean() ? DEATH_BY_POTATO_JETPACK : DEATH_BY_JETPACK_EXPLOSION), 100.0f);
                }
            } else {
                if (force || CommonJetpackHandler.isHoldingUp(player)) {
                    if (this.isTimerSet(stack)) {
                        this.decrementTimer(stack, player);
                    } else {
                        this.setTimer(stack, 50);
                    }
                }
            }
        }
    }

    public boolean isFired(ItemStack itemStack) {
        return NBTUtil.getBoolean(itemStack, Constants.TAG_FIRED);
    }

    private void setFired(ItemStack itemStack) {
        NBTUtil.setBoolean(itemStack, Constants.TAG_FIRED, true);
    }

    private boolean isTimerSet(ItemStack itemStack) {
        return NBTUtil.getBoolean(itemStack, Constants.TAG_ROCKET_TIMER_SET);
    }

    private void setTimer(ItemStack itemStack, int timer) {
        NBTUtil.setInt(itemStack, Constants.TAG_ROCKET_TIMER, timer);
        NBTUtil.setBoolean(itemStack, Constants.TAG_ROCKET_TIMER_SET, true);
    }

    private void decrementTimer(ItemStack itemStack, Player player) {
        int timer = NBTUtil.getInt(itemStack, Constants.TAG_ROCKET_TIMER);
        timer = timer > 0 ? timer - 1 : 0;
        NBTUtil.setInt(itemStack, Constants.TAG_ROCKET_TIMER, timer);
        if (timer == 0) {
            this.setFired(itemStack);
            // TODO: test this
//            player.level.playSound(player, player, SJSounds.ROCKET, SoundSource.PLAYERS, 1F, 1F);
            player.getCommandSenderWorld().playSound(player, player, RegistryHandler.ROCKET_SOUND.get(), SoundSource.PLAYERS, 1F, 1F);
        }
    }
}
