package tonius.simplyjetpacks.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import tonius.simplyjetpacks.client.particle.EntityColoredFlameFX;
import tonius.simplyjetpacks.client.particle.EntityColoredSmokeFX;
import tonius.simplyjetpacks.setup.ParticleType;

public class ParticleUtils {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void spawnParticle(ParticleType particle, World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        switch (particle) {
            case NONE:
                return;
            case FLAME:
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.FLAME.getParticleID(), posX, posY, posZ, velX, velY, velZ);
            case SMOKE:
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.SMOKE_NORMAL.getParticleID(), posX, posY, posZ, velX, velY - 0.1D, velZ);
                return;
            case RAINBOW:
                mc.effectRenderer.addEffect(EntityColoredSmokeFX.getRainbowSmoke(world, posX, posY, posZ, velX, velY - 0.1D, velZ));
                mc.effectRenderer.addEffect(EntityColoredSmokeFX.getRainbowSmoke(world, posX, posY - 0.2D, posZ, velX, velY - 0.1D, velZ));
                return;
            case BUBBLE:
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.WATER_BUBBLE.getParticleID(), posX, posY, posZ, velX, velY, velZ);
                return;
            case HEART:
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.HEART.getParticleID(), posX, posY, posZ, velX, velY, velZ);
                return;
            case SOUL:
                mc.effectRenderer.addEffect(EntityColoredFlameFX.getSoulFlame(world, posX, posY - 0.2D, posZ, velX, velY, velZ));
                return;
            case SNOW:
                mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.SNOWBALL.getParticleID(), posX, posY, posZ, velX, velY, velZ);
        }
    }
}