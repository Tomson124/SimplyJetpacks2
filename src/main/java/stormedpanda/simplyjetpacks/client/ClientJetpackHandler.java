package stormedpanda.simplyjetpacks.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.ParticleStatus;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import stormedpanda.simplyjetpacks.client.particle.JetpackParticleType;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.handlers.SyncHandler;
import stormedpanda.simplyjetpacks.items.JetpackItem;
import stormedpanda.simplyjetpacks.sound.JetpackSound;
import stormedpanda.simplyjetpacks.util.Pos3D;

import java.util.Random;

public class ClientJetpackHandler {
    Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (minecraft.player != null && minecraft.world != null) {
                if (!minecraft.isGamePaused()) {
                    ItemStack chest = minecraft.player.getItemStackFromSlot(EquipmentSlotType.CHEST);
                    Item item = chest.getItem();
                    if (!chest.isEmpty() && item instanceof JetpackItem) {
                        if (isFlying(minecraft.player)) {
                            JetpackParticleType particleType;
                            if (minecraft.player.isInWaterOrBubbleColumn()) {
                                particleType = JetpackParticleType.BUBBLES;
                            } else {
                                particleType = ((JetpackItem) item).getType().getParticleType(chest);
                            }
                            showJetpackParticles(minecraft.world, minecraft.player, particleType);
                            if (SimplyJetpacksConfig.CLIENT.enableJetpackSounds.get()) {
                                if (!JetpackSound.playing(minecraft.player.getEntityId())) {
                                    minecraft.getSoundHandler().play(new JetpackSound(minecraft.player));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void showJetpackParticles(World world, PlayerEntity player, JetpackParticleType particleType) {
        IParticleData particle = particleType.getParticleData();
        if (minecraft.gameSettings.particles != ParticleStatus.MINIMAL && particle != null) {
            Random rand = new Random();
            Pos3D playerPos = new Pos3D(player).translate(0, 1.5, 0);
            float random = (rand.nextFloat() - 0.5F) * 0.1F;
            Pos3D vLeft = new Pos3D(-0.28, -0.95, -0.35).rotatePitch(0).rotateYaw(player.renderYawOffset);
            Pos3D vRight = new Pos3D(0.28, -0.95, -0.35).rotatePitch(0).rotateYaw(player.renderYawOffset);
            Pos3D vCenter = new Pos3D((rand.nextFloat() - 0.5F) * 0.25F, -0.95, -0.38).rotatePitch(0).rotateYaw(player.renderYawOffset);
            Pos3D v = playerPos.translate(vLeft).translate(new Pos3D(player.getMotion()));
            world.addParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
            v = playerPos.translate(vRight).translate(new Pos3D(player.getMotion()));
            world.addParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
            v = playerPos.translate(vCenter).translate(new Pos3D(player.getMotion()));
            world.addParticle(particle, v.x, v.y, v.z, random, -0.2D, random);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean isFlying(PlayerEntity player) {
        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        if (!stack.isEmpty()) {
            Item item = stack.getItem();
            if (item instanceof JetpackItem) {
                JetpackItem jetpack = (JetpackItem) item;
                if (jetpack.isEngineOn(stack) && (jetpack.getEnergyStored(stack) > 0 || player.isCreative() || jetpack.isCreative())) {
                    if (jetpack.isHoverOn(stack)) {
                        return !player.isOnGround();
                    } else {
                        return SyncHandler.isHoldingUp(player);
                    }
                }
            }
        }
        return false;
    }
}
