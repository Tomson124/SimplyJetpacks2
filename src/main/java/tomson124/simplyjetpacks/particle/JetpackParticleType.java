package tomson124.simplyjetpacks.particle;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import tomson124.simplyjetpacks.handlers.RegistryHandler;

import java.util.function.Supplier;

public enum JetpackParticleType {

    NONE(() -> null),
    FLAME(() -> ParticleTypes.FLAME),
    SMOKE(() -> ParticleTypes.SMOKE),
    // Resolved lazily: enum init can run before the particle registry is populated.
    RAINBOW(() -> RegistryHandler.RAINBOW_PARTICLE.get()),
    BUBBLES(() -> ParticleTypes.BUBBLE),
    HEARTS(() -> ParticleTypes.HEART),
    SOUL(() -> ParticleTypes.SOUL_FIRE_FLAME),
    SNOW(() -> ParticleTypes.ITEM_SNOWBALL),
    ;

    private final Supplier<ParticleOptions> particleData;

    JetpackParticleType(Supplier<ParticleOptions> particleData) {
        this.particleData = particleData;
    }

    public ParticleOptions getParticleData() {
        return particleData.get();
    }

    public static JetpackParticleType byId(int id) {
        var values = values();
        return id >= 0 && id < values.length ? values[id] : FLAME;
    }
}
