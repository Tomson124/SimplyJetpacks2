package stormedpanda.simplyjetpacks.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.item.PotatoJetpackItem;
import stormedpanda.simplyjetpacks.particle.JetpackParticleType;
import stormedpanda.simplyjetpacks.sound.JetpackSound;
import stormedpanda.simplyjetpacks.util.JetpackUtil;
import stormedpanda.simplyjetpacks.util.Pos3D;

import java.time.LocalDate;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class ClientJetpackHandler {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (event.phase == TickEvent.Phase.END) {
            if (minecraft.player != null && minecraft.level != null) {
                if (!minecraft.isPaused() && !minecraft.player.isSpectator()) {
                    ItemStack chest = JetpackUtil.getFromBothSlots(minecraft.player);
                    Item item = chest.getItem();
                    if ((!chest.isEmpty() && item instanceof JetpackItem && isFlying(minecraft.player)) ||
                            (item instanceof PotatoJetpackItem && ((PotatoJetpackItem)item).isFired(chest))) {
                        // Show particles:
                        // TODO: Fix this
                        if (SimplyJetpacksConfig.enableJetpackParticles.get() && (minecraft.options.particles().get() != ParticleStatus.MINIMAL)) {
                            JetpackParticleType particleType;
                            if (minecraft.player.isInWaterRainOrBubble()) {
                                particleType = JetpackParticleType.BUBBLES;
                            } else if (checkValentines()) {
                                particleType = JetpackParticleType.HEARTS;
                            } else {
                                particleType = JetpackParticleType.values()[JetpackItem.getParticleId(chest)];
                            }
                            if (particleType.getParticleData() != null) {
                                showJetpackParticles(minecraft, particleType);
                            }
                        }
                        // Play sounds:
                        if (SimplyJetpacksConfig.enableJetpackSounds.get() && !JetpackSound.playing(minecraft.player.getId())) {
                            minecraft.getSoundManager().play(new JetpackSound(minecraft.player));
                        }
                    }
                }
            }
        }
    }

    private void showJetpackParticles(Minecraft minecraft, JetpackParticleType particleType) {
        ParticleOptions particle = particleType.getParticleData();
        Random rand = new Random();
        float random = (rand.nextFloat() - 0.5F) * 0.1F;
        double[] sneakBonus = minecraft.player.isCrouching() ? new double[]{-0.30, -0.10} : new double[]{0, 0};
        Pos3D playerPos = new Pos3D(minecraft.player).translate(0, 1.5, 0);
        Pos3D vLeft = new Pos3D(-0.18, -0.90 + sneakBonus[1], -0.30 + sneakBonus[0]).rotate(minecraft.player.yBodyRot, 0);
        Pos3D vRight = new Pos3D(0.18, -0.90 + sneakBonus[1], -0.30 + sneakBonus[0]).rotate(minecraft.player.yBodyRot, 0);
        Pos3D vCenter = new Pos3D((rand.nextFloat() - 0.5F) * 0.25F, -0.90 + sneakBonus[1], -0.30 + sneakBonus[0]).rotate(minecraft.player.yBodyRot, 0);
        Pos3D v = playerPos.translate(vLeft).translate(new Pos3D(minecraft.player.getDeltaMovement()));
        minecraft.particleEngine.createParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
        v = playerPos.translate(vRight).translate(new Pos3D(minecraft.player.getDeltaMovement()));
        minecraft.particleEngine.createParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
        if (particleType != JetpackParticleType.HEARTS) {
            v = playerPos.translate(vCenter).translate(new Pos3D(minecraft.player.getDeltaMovement()));
            minecraft.particleEngine.createParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
        }
        //minecraft.level.addParticle(particle, v.x, v.y, v.z, random, -0.2D, random); // alternative method
    }

    public static boolean isFlying(Player player) {
        ItemStack stack = JetpackUtil.getFromBothSlots(player);
        if (!stack.isEmpty()) {
            Item item = stack.getItem();
            if (item instanceof JetpackItem) {
                JetpackItem jetpack = (JetpackItem) item;
                if (jetpack.isEngineOn(stack) && (jetpack.getEnergy(stack) > 0 || jetpack.isCreative())) {
                    if (jetpack.isHoverOn(stack)) {
                        return !player.onGround();
                    } else {
                        return CommonJetpackHandler.isHoldingUp(player);
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkValentines() {
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();
        return day == 14 && month == 2;
    }
}
