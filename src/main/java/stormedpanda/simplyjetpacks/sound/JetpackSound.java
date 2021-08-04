package stormedpanda.simplyjetpacks.sound;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stormedpanda.simplyjetpacks.client.ClientJetpackHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class JetpackSound extends Sound {
    private static final Map<Integer, JetpackSound> PLAYING_FOR = Collections.synchronizedMap(new HashMap<>());
    private final Player player;
    private int fadeOut = -1;

    public JetpackSound(Player player) {
        super(ModSounds.JETPACK, SoundCategory.PLAYERS);
        this.player = player;
        this.repeat = true;
        PLAYING_FOR.put(player.getId(), this);
    }

    public static boolean playing(int entityId) {
        return PLAYING_FOR.containsKey(entityId) && PLAYING_FOR.get(entityId) != null && !PLAYING_FOR.get(entityId).isDonePlaying();
    }

    @Override
    public void tick() {
        if (this.player.isSpectator()) {
            this.finishPlaying();
        }
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
            this.finishPlaying();
        } else
        if(this.fadeOut >= 0) {
            this.volume = 1.0F - this.fadeOut / 5F;
            this.fadeOut++;
        }
    }
}
