package stormedpanda.simplyjetpacks.particle;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import stormedpanda.simplyjetpacks.init.RegistryHandler;

public enum JetpackParticleType {

    NONE(null),
    DEFAULT(ParticleTypes.FLAME),
    SMOKE(ParticleTypes.SMOKE),
    RAINBOW(RegistryHandler.RAINBOW_PARTICLE.get()),
    BUBBLES(ParticleTypes.BUBBLE),
    HEARTS(ParticleTypes.HEART),
    WARPED(ParticleTypes.WARPED_SPORE),
    CRIMSON(ParticleTypes.CRIMSON_SPORE),
    PORTAL(ParticleTypes.PORTAL)
    ;

    public final IParticleData particleData;

    JetpackParticleType(IParticleData particleData) {
        this.particleData = particleData;
    }

    public IParticleData getParticleData() {
        return particleData;
    }
}