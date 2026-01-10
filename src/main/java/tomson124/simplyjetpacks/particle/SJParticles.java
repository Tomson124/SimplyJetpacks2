package tomson124.simplyjetpacks.particle;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.handlers.RegistryHandler;

@EventBusSubscriber( value = Dist.CLIENT, modid = SimplyJetpacks.MODID)
public class SJParticles {

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(RegistryHandler.RAINBOW_PARTICLE.get(), ParticleRainbow.Factory::new);
    }
}