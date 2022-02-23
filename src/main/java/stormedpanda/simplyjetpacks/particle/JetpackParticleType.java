package stormedpanda.simplyjetpacks.particle;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;

public enum JetpackParticleType {

    NONE(null),
    FLAME(ParticleTypes.FLAME),
    SMOKE(ParticleTypes.SMOKE),
    RAINBOW(RegistryHandler.RAINBOW_PARTICLE.get()),
    BUBBLES(ParticleTypes.BUBBLE),
    HEARTS(ParticleTypes.HEART),
    SOUL(ParticleTypes.SOUL_FIRE_FLAME),
    SNOW(ParticleTypes.ITEM_SNOWBALL),
    ;

    public final ParticleOptions particleData;

    JetpackParticleType(ParticleOptions particleData) {
        this.particleData = particleData;
    }

    public ParticleOptions getParticleData() {
        return particleData;
    }
}