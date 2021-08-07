package stormedpanda.simplyjetpacks.client.particle;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;

public enum JetpackParticleType {

    NONE(null),
    DEFAULT(ParticleTypes.FLAME),
    SMOKE(ParticleTypes.SMOKE),
    RAINBOW(SJParticles.RAINBOW),
    BUBBLES(ParticleTypes.BUBBLE),
    HEARTS(ParticleTypes.HEART);

    public ParticleOptions particleData;

    JetpackParticleType(ParticleOptions particleData) {
        this.particleData = particleData;
    }

    public ParticleOptions getParticleData() {
        return particleData;
    }
}
