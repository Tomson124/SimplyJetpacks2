package tonius.simplyjetpacks.client.audio;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SJSoundEvents
{
	public static final SoundEvent ROCKET;
	public static final SoundEvent JETPACK;
	public static final SoundEvent JETPACK_OTHER;

	private static SoundEvent getRegisteredSoundEvent(String id)
	{
		SoundEvent soundevent = (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(id));

		if(soundevent == null)
		{
			throw new IllegalStateException("Invalid Sound requested: " + id);
		}
		else
		{
			return soundevent;
		}
	}

	static
	{
		ROCKET = getRegisteredSoundEvent("rocket");
		JETPACK = getRegisteredSoundEvent("jetpack");
		JETPACK_OTHER = getRegisteredSoundEvent("jetpack_other");
	}
}