package stormedpanda.simplyjetpacks.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.ParticleStatus;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.particle.JetpackParticleType;
import stormedpanda.simplyjetpacks.sound.JetpackSound;
import stormedpanda.simplyjetpacks.util.Pos3D;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class ClientJetpackHandler {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (event.phase == TickEvent.Phase.END) {
            if (minecraft.player != null && minecraft.level != null) {
                if (!minecraft.isPaused() && !minecraft.player.isSpectator()) {
                    ItemStack chest = minecraft.player.getItemBySlot(EquipmentSlotType.CHEST);
                    Item item = chest.getItem();
                    if (!chest.isEmpty() && item instanceof JetpackItem && isFlying(minecraft.player)) {
                        if (SimplyJetpacksConfig.enableJetpackParticles.get() && (minecraft.options.particles != ParticleStatus.MINIMAL)) {
                            JetpackParticleType particleType;
                            if (minecraft.player.isInWaterRainOrBubble()) {
                                particleType = JetpackParticleType.BUBBLES;
                            } else {
                                particleType = JetpackParticleType.values()[JetpackItem.getParticleId(chest)];
                            }
                            if (particleType.getParticleData() != null) {
                                showJetpackParticles(minecraft, particleType);
                            }
                        }
                        // Play sounds:
                        if (SimplyJetpacksConfig.enableJetpackSounds.get() && !JetpackSound.playing(minecraft.player.getId())) {
                            //minecraft.getSoundManager().play(new JetpackSound(minecraft.player));
                        }
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void showJetpackParticles(Minecraft minecraft, JetpackParticleType particleType) {
        IParticleData particle = particleType.getParticleData();
/*        Pos3D vLeft = new Pos3D(-0.28, -0.95, -0.35).yRot(0).zRot(player.z);
        Pos3D vRight = new Pos3D(0.28, -0.95, -0.35).rotate(player.yBodyRot, 0);// rotatePitch(0).rotateYaw(player.renderYawOffset);
        Pos3D vCenter = new Pos3D((rand.nextFloat() - 0.5F) * 0.25F, -0.95, -0.38).rotatePitch(0).rotateYaw(player.renderYawOffset);
        Pos3D v = playerPos.translate(vLeft).translate(new Pos3D(player.getMotion()));
        world.addParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
        v = playerPos.translate(vRight).translate(new Pos3D(player.getMotion()));
        world.addParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
        v = playerPos.translate(vCenter).translate(new Pos3D(player.getMotion()));
        world.addParticle(particle, v.x, v.y, v.z, random, -0.2D, random);*/
        Random rand = new Random();
        float random = (rand.nextFloat() - 0.5F) * 0.1F;
        double[] sneakBonus = minecraft.player.isCrouching() ? new double[]{-0.30, -0.10} : new double[]{0, 0};
        Pos3D playerPos = new Pos3D(minecraft.player).translate(0, 1.5, 0);
        Pos3D vLeft = new Pos3D(-0.18, -0.90 + sneakBonus[1], -0.30 + sneakBonus[0]).rotate(minecraft.player.yBodyRot, 0);
        Pos3D vRight = new Pos3D(0.18, -0.90 + sneakBonus[1], -0.30 + sneakBonus[0]).rotate(minecraft.player.yBodyRot, 0);
        Pos3D v = playerPos.translate(vLeft).translate(new Pos3D(minecraft.player.getDeltaMovement()));
        //minecraft.particleEngine.createParticle(ParticleTypes.FLAME, v.x, v.y, v.z, random, -0.2D, random);
        //minecraft.particleEngine.createParticle(ParticleTypes.SMOKE, v.x, v.y, v.z, random, -0.2D, random);
        v = playerPos.translate(vRight).translate(new Pos3D(minecraft.player.getDeltaMovement()));
        //minecraft.particleEngine.createParticle(ParticleTypes.FLAME, v.x, v.y, v.z, random, -0.2D, random);
        //minecraft.particleEngine.createParticle(ParticleTypes.SMOKE, v.x, v.y, v.z, random, -0.2D, random);
        minecraft.particleEngine.createParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
        //minecraft.level.addParticle(JetpackParticleType.HEARTS.getParticleData(), v.x, v.y, v.z, random, -0.2D, random);
        // TODO: add centered particles?
    }

    //@OnlyIn(Dist.CLIENT)
    public static boolean isFlying(PlayerEntity player) {
        ItemStack stack = player.getItemBySlot(EquipmentSlotType.CHEST);
        if (!stack.isEmpty()) {
            Item item = stack.getItem();
            if (item instanceof JetpackItem) {
                JetpackItem jetpack = (JetpackItem) item;
                return true;
                /*if (jetpack.isEngineOn(stack) && (jetpack.getEnergyStored(stack) > 0 || player.isCreative() || jetpack.isCreative())) {
                    if (jetpack.isHoverOn(stack)) {
                        return !player.isOnGround();
                    } else {
                        return SyncHandler.isHoldingUp(player);
                    }
                }*/
            }
        }
        //return false;
        return true;
    }
}
