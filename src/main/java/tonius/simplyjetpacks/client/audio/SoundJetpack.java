package tonius.simplyjetpacks.client.audio;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.TickableSound;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tonius.simplyjetpacks.SimplyJetpacks;

import tonius.simplyjetpacks.sound.SJSoundRegistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SoundJetpack extends TickableSound {
	private static final ResourceLocation SOUND = new ResourceLocation(SimplyJetpacks.RESOURCE_PREFIX + "jetpack");
	private static final ResourceLocation SOUND_OTHER = new ResourceLocation(SimplyJetpacks.RESOURCE_PREFIX + "jetpack_other");

	private static final Map<Integer, SoundJetpack> playingFor = Collections.synchronizedMap(new HashMap<Integer, SoundJetpack>());
	private static final Minecraft mc = Minecraft.getInstance();

	private final LivingEntity user;
	private int fadeOut = -1;

	public SoundJetpack(LivingEntity target) {
		//super(target == mc.thePlayer ? SJSoundEvents.JETPACK : SJSoundEvents.JETPACK_OTHER, target == mc.thePlayer ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL);
		super(SJSoundRegistry.JETPACK.getSoundEvent(), SoundCategory.PLAYERS);
		this.repeat = true;
		this.user = target;
		playingFor.put(target.getEntityId(), this);
	}

	public static boolean isPlayingFor(int entityId) {
		return playingFor.containsKey(entityId) && playingFor.get(entityId) != null && !playingFor.get(entityId).donePlaying;
	}

	public static void clearPlayingFor() {
		playingFor.clear();
	}

	@Override
	public void tick() {
		this.x = (float) this.user.posX;
		this.y = (float) this.user.posY;
		this.z = (float) this.user.posZ;

		if (this.fadeOut < 0 /*&& !SyncHandler.getJetpackStates().keySet().contains(this.user.getEntityId())*/) {
			this.fadeOut = 0;
			synchronized (playingFor) {
				playingFor.remove(this.user.getEntityId());
			}
		} else if (this.fadeOut >= 5) {
			this.donePlaying = true;
		} else if (this.fadeOut >= 0) {
			this.volume = 1.0F - this.fadeOut / 5F;
			this.fadeOut++;
		}
	}

	@Override
	public boolean canBeSilent() {
		return false;
	}
}