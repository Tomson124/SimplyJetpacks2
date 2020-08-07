package stormedpanda.simplyjetpacks.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class ModSounds {
    public static final SoundEvent JETPACK = new SoundEvent(new ResourceLocation(SimplyJetpacks.MODID, "jetpack"));
    public static final SoundEvent JETPACK_OTHER = new SoundEvent(new ResourceLocation(SimplyJetpacks.MODID, "jetpack_other"));
    public static final SoundEvent ROCKET = new SoundEvent(new ResourceLocation(SimplyJetpacks.MODID, "rocket"));

    @SubscribeEvent
    public void onRegisterSounds(RegistryEvent.Register<SoundEvent> event) {
        IForgeRegistry<SoundEvent> registry = event.getRegistry();
        registry.register(JETPACK.setRegistryName("jetpack"));
        registry.register(JETPACK_OTHER.setRegistryName("jetpack_other"));
        registry.register(ROCKET.setRegistryName("rocket"));
    }
}