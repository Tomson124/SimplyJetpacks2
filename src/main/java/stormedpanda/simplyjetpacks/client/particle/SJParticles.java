package stormedpanda.simplyjetpacks.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

@Mod.EventBusSubscriber(modid = SimplyJetpacks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SJParticles {

    public static final BasicParticleType RAINBOW = new BasicParticleType(false);

    @SubscribeEvent
    public static void registerParticleTypes(RegistryEvent.Register<ParticleType<?>> evt) {
        RAINBOW.setRegistryName(SimplyJetpacks.MODID, "rainbow");
        evt.getRegistry().registerAll(RAINBOW);
    }

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager manager = Minecraft.getInstance().particles;
        manager.registerFactory(SJParticles.RAINBOW, ParticleRainbow.Factory::new);
    }
}