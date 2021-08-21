package stormedpanda.simplyjetpacks.particle;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import stormedpanda.simplyjetpacks.init.RegistryHandler;

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

    public final IParticleData particleData;

    JetpackParticleType(IParticleData particleData) {
        this.particleData = particleData;
    }

    public IParticleData getParticleData() {
        return particleData;
    }
}