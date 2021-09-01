package tonius.simplyjetpacks.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import tonius.simplyjetpacks.client.particle.EntityColoredSmokeFX;
import tonius.simplyjetpacks.setup.ParticleType;

public class ParticleUtils {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void spawnParticle(ParticleType particle, World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        switch (particle) {
            case NONE:
                return;
            case FLAME:
                //mc.effectRenderer.addEffect(new EntityCustomFlameFX(world, posX, posY, posZ, velX, velY, velZ));
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.FLAME.getParticleID(), posX, posY, posZ, velX, velY, velZ);
            case SMOKE:
                //mc.effectRenderer.addEffect(new EntityCustomSmokeFX(world, posX, posY, posZ, velX, velY - 0.1D, velZ));
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleID(), posX, posY, posZ, velX, velY - 0.1D, velZ);
                return;
            case RAINBOW:
                mc.effectRenderer.addEffect(EntityColoredSmokeFX.getRainbowSmoke(world, posX, posY, posZ, velX, velY - 0.1D, velZ));
                mc.effectRenderer.addEffect(EntityColoredSmokeFX.getRainbowSmoke(world, posX, posY - 0.2D, posZ, velX, velY - 0.1D, velZ));
                return;
            case BUBBLE:
                //mc.effectRenderer.addEffect(new EntityCustomBubbleFX(world, posX, posY, posZ, velX, velY, velZ));
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.WATER_BUBBLE.getParticleID(), posX, posY, posZ, velX, velY, velZ);
                return;
            case HEART:
                // TODO: Valentine's Day check
                //mc.effectRenderer.addEffect(new EntityCustomHeartFX(world, posX, posY, posZ, velX, velY, velZ));
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.HEART.getParticleID(), posX, posY, posZ, velX, velY, velZ);
                return;
            case SOUL:
                // TODO: create soul flame particle
                //mc.effectRenderer.addEffect(new EntityCustomFlameFX(world, posX, posY, posZ, velX, velY, velZ));
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.FLAME.getParticleID(), posX, posY, posZ, velX, velY, velZ);
                return;
            case SNOW:
                // TODO: create snow particle
                //mc.effectRenderer.addEffect(new EntityCustomSnowFX(world, posX, posY, posZ, velX, velY, velZ));
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.SNOWBALL.getParticleID(), posX, posY, posZ, velX, velY, velZ);
        }
    }
}