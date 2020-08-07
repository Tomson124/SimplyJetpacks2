package stormedpanda.simplyjetpacks.sound;

import net.minecraft.client.audio.TickableSound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stormedpanda.simplyjetpacks.client.ClientJetpackHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class JetpackSound extends TickableSound {
	private static final Map<Integer, JetpackSound> PLAYING_FOR = Collections.synchronizedMap(new HashMap<>());
	private final PlayerEntity player;
	private int fadeOut = -1;

	public JetpackSound(PlayerEntity player) {
		super(ModSounds.JETPACK, SoundCategory.PLAYERS);
		this.player = player;
		this.repeat = true;
		PLAYING_FOR.put(player.getEntityId(), this);
	}
	
	public static boolean playing(int entityId) {
		return PLAYING_FOR.containsKey(entityId) && PLAYING_FOR.get(entityId) != null && !PLAYING_FOR.get(entityId).isDonePlaying();
	}

	@Override
	public void tick() {
		BlockPos pos = this.player.getPosition();
		this.x = (float) pos.getX();
		this.y = (float) pos.getY();// - 10;
		this.z = (float) pos.getZ();
		if (this.fadeOut < 0 && !ClientJetpackHandler.isFlying(this.player)) {
			this.fadeOut = 0;
			synchronized (PLAYING_FOR) {
				PLAYING_FOR.remove(this.player.getEntityId());
			}
		} else
			if (this.fadeOut >= 5) {
			this.func_239509_o_();
		} else
			if(this.fadeOut >= 0) {
			this.volume = 1.0F - this.fadeOut / 5F;
			this.fadeOut++;
		}
	}
}
