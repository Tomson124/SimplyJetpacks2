package tonius.simplyjetpacks.client.audio;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SoundRegistry {

    public static final RegistryNamespaced<ResourceLocation, SoundEvent> REGISTRY = net.minecraftforge.fml.common.registry.GameData.getSoundEventRegistry();
    private final ResourceLocation soundName;
    private static int soundEventId = 443;

    public SoundRegistry(ResourceLocation soundNameIn)
    {
        this.soundName = soundNameIn;
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getSoundName()
    {
        return this.soundName;
    }

    public static void registerSounds() {
        registerSound("rocket");
    }

    private static void registerSound(String soundNameIn) {
        ResourceLocation resourcelocation = new ResourceLocation(soundNameIn);
        REGISTRY.register(soundEventId++, resourcelocation, new SoundEvent(resourcelocation));
    }
}
