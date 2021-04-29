package tonius.simplyjetpacks.handler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import tonius.simplyjetpacks.client.audio.SoundJetpack;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.AdvancementUtil;

import java.util.HashMap;
import java.util.Map;

public class SyncHandler {

    private static final Map<EntityPlayer, Boolean> flyKeyState = new HashMap<>();
    private static final Map<EntityPlayer, Boolean> descendKeyState = new HashMap<>();
    private static final Map<EntityPlayer, Boolean> forwardKeyState = new HashMap<>();
    private static final Map<EntityPlayer, Boolean> backwardKeyState = new HashMap<>();
    private static final Map<EntityPlayer, Boolean> leftKeyState = new HashMap<>();
    private static final Map<EntityPlayer, Boolean> rightKeyState = new HashMap<>();

    private static final Map<Integer, ParticleType> jetpackState = new HashMap<>();

    public static boolean isFlyKeyDown(EntityLivingBase user) {
        return !(user instanceof EntityPlayer) || flyKeyState.containsKey(user) && flyKeyState.get(user);
    }

    public static boolean isDescendKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && descendKeyState.containsKey(user) && descendKeyState.get(user);
    }

    public static boolean isForwardKeyDown(EntityLivingBase user) {
        return !(user instanceof EntityPlayer) || forwardKeyState.containsKey(user) && forwardKeyState.get(user);
    }

    public static boolean isBackwardKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && backwardKeyState.containsKey(user) && backwardKeyState.get(user);
    }

    public static boolean isLeftKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && leftKeyState.containsKey(user) && leftKeyState.get(user);
    }

    public static boolean isRightKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && rightKeyState.containsKey(user) && rightKeyState.get(user);
    }

    public static void processKeyUpdate(EntityPlayer player, boolean keyFly, boolean keyDescend, boolean keyForward, boolean keyBackward, boolean keyLeft, boolean keyRight) {
        flyKeyState.put(player, keyFly);
        descendKeyState.put(player, keyDescend);
        forwardKeyState.put(player, keyForward);
        backwardKeyState.put(player, keyBackward);
        leftKeyState.put(player, keyLeft);
        rightKeyState.put(player, keyRight);
    }

    public static void processJetpackUpdate(int entityId, ParticleType particleType) {
        if (particleType != null) {
            jetpackState.put(entityId, particleType);
        } else {
            jetpackState.remove(entityId);
        }
    }

    public static Map<Integer, ParticleType> getJetpackStates() {
        return jetpackState;
    }

    public static void clearAll() {
        flyKeyState.clear();
        forwardKeyState.clear();
        descendKeyState.clear();
        backwardKeyState.clear();
        leftKeyState.clear();
        rightKeyState.clear();
    }

    private static void removeFromAll(EntityPlayer player) {
        flyKeyState.remove(player);
        forwardKeyState.remove(player);
        descendKeyState.remove(player);
        backwardKeyState.remove(player);
        leftKeyState.remove(player);
        rightKeyState.remove(player);
    }

    // This is here because it does not want to be lonely.
    public static void checkAdvancements(EntityPlayer player) {
        AdvancementUtil.unlockAdvancement(player, "root");

        if (ModItems.integrateVanilla) {
            AdvancementUtil.unlockAdvancement(player, "vanilla/root_vanilla");
        }
        if (ModItems.integrateIE) {
            AdvancementUtil.unlockAdvancement(player, "ie/root_ie");
        }
        if (ModItems.integrateMek) {
            AdvancementUtil.unlockAdvancement(player, "mek/root_mek");
        }
        if (ModItems.integrateEIO) {
            AdvancementUtil.unlockAdvancement(player, "eio/root_eio");
        }
        if (ModItems.integrateTE) {
            AdvancementUtil.unlockAdvancement(player, "te/root_te");
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (Config.joinAdvancements) checkAdvancements(event.player);
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        removeFromAll(event.player);
    }

    @SubscribeEvent
    public void onDimChanged(PlayerEvent.PlayerChangedDimensionEvent event) {
        removeFromAll(event.player);
    }

    @SubscribeEvent
    public void onClientDisconnectedFromServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        SoundJetpack.clearPlayingFor();
    }
}