package tomson124.simplyjetpacks.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import tomson124.simplyjetpacks.SimplyJetpacks;

public class SJSounds {

    public static final SoundEvent JETPACK = SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "jetpack"));
    public static final SoundEvent JETPACK_OTHER = SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "jetpack_other"));
    public static final SoundEvent ROCKET = SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "rocket"));

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
