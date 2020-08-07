package stormedpanda.simplyjetpacks.client.particle;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;

public enum JetpackParticleType {

    NONE(null),
    DEFAULT(ParticleTypes.FLAME),
    SMOKE(ParticleTypes.SMOKE),
    RAINBOW(SJParticles.RAINBOW),
    BUBBLES(ParticleTypes.BUBBLE),
    HEARTS(ParticleTypes.HEART);

    public IParticleData particleData;

    JetpackParticleType(IParticleData particleData) {
        this.particleData = particleData;
    }

    public IParticleData getParticleData() {
        return particleData;
    }
}
