package stormedpanda.simplyjetpacks.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class SJSounds {

    public static final SoundEvent JETPACK = SoundEvent.createVariableRangeEvent(new ResourceLocation(SimplyJetpacks.MODID, "jetpack"));
    public static final SoundEvent JETPACK_OTHER = SoundEvent.createVariableRangeEvent(new ResourceLocation(SimplyJetpacks.MODID, "jetpack_other"));
    public static final SoundEvent ROCKET = SoundEvent.createVariableRangeEvent(new ResourceLocation(SimplyJetpacks.MODID, "rocket"));

    /*// TODO: check this (cringe)
    @SubscribeEvent
//    public void onRegisterSounds(FMLClientSetupEvent<SoundEvent> event) {
    public void onRegisterSounds(RegisterEvent.<SoundEvent> event) {
//        IForgeRegistry<SoundEvent> registry = event.get();
        IForgeRegistry<SoundEvent> registry = RegistryManager.ACTIVE.getRegistry(ForgeRegistries.Keys.SOUND_EVENTS);
        registry.register("jetpack", JETPACK);
        registry.register("jetpack_other", JETPACK_OTHER);
        registry.register("rocket", ROCKET);
    }*/
}
