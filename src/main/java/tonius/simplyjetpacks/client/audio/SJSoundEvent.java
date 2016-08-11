package tonius.simplyjetpacks.client.audio;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tonius.simplyjetpacks.SimplyJetpacks;

public class SJSoundEvent {

    public static void registerSounds() {
        register("rocket");
        register("jetpack");
        register("jetpack_other");
    }

    protected static void register(String resourcePath) {

        ResourceLocation location = new ResourceLocation(SimplyJetpacks.MODID, resourcePath);
        SoundEvent event = new SoundEvent(location);
        GameRegistry.register(event, location);
    }
}
