package tomson124.simplyjetpacks.handlers;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;
import tomson124.simplyjetpacks.config.SimplyJetpacksConfig;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.network.NetworkHandler;
import tomson124.simplyjetpacks.network.packets.*;
import tomson124.simplyjetpacks.screen.JetpackScreen;
import tomson124.simplyjetpacks.util.JetpackUtil;

public class KeybindForgeBusHandler {

    private static boolean lastFlyState = false;
    private static boolean lastInvertHover = false;
    private static boolean lastDescendState = false;
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;

    public static KeyMapping JETPACK_GUI_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_gui", GLFW.GLFW_KEY_K, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_ENGINE_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_engine", GLFW.GLFW_KEY_J, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_HOVER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_hover", GLFW.GLFW_KEY_H, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_EHOVER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_ehover", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_CHARGER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_charger", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_THROTTLE_INCREASE = new KeyMapping("keybind.simplyjetpacks.jetpack_throttle_increase", GLFW.GLFW_KEY_PERIOD, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_THROTTLE_DECREASE = new KeyMapping("keybind.simplyjetpacks.jetpack_throttle_decrease", GLFW.GLFW_KEY_COMMA, "keybind.simplyjetpacks.category");

    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;
        if (player == null || event.getAction() != InputConstants.PRESS || Minecraft.getInstance().screen != null) {
            return;
        }
        ItemStack chestStack = JetpackUtil.getFromBothSlots(player);
        Item chestItem = null;
        JetpackItem jetpack;
        if (!chestStack.isEmpty()) {
            chestItem = chestStack.getItem();
        }
        if (chestItem instanceof JetpackItem) {
            jetpack = (JetpackItem) chestItem;
            if (JETPACK_GUI_KEY.getKey().getValue() == event.getKey()) {
                Minecraft.getInstance().setScreen(new JetpackScreen());
            }
            if (JETPACK_ENGINE_KEY.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketToggleEngine());
            }
            if (JETPACK_HOVER_KEY.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketToggleHover());
            }
            if (JETPACK_EHOVER_KEY.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketToggleEHover());
            }
            if (JETPACK_CHARGER_KEY.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketToggleCharger());
            }
            if (JETPACK_THROTTLE_INCREASE.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketUpdateThrottle(Math.max(0, Math.min(100,jetpack.getThrottle(chestStack) + 10))));
            }
            if (JETPACK_THROTTLE_DECREASE.getKey().getValue() == event.getKey()) {
                NetworkHandler.sendToServer(new PacketUpdateThrottle(Math.max(0, Math.min(100, jetpack.getThrottle(chestStack) - 10))));
            }
        }
    }

    private static void tickEnd() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            boolean flyState = mc.player.input.jumping;
            boolean invertHover = SimplyJetpacksConfig.invertHoverSneakingBehavior.get();
            boolean descendState = mc.player.input.shiftKeyDown;
            boolean forwardState = mc.player.input.up;
            boolean backwardState = mc.player.input.down;
            boolean leftState = mc.player.input.left;
            boolean rightState = mc.player.input.right;
            if (flyState != lastFlyState || invertHover != lastInvertHover || descendState != lastDescendState || forwardState != lastForwardState || backwardState != lastBackwardState || leftState != lastLeftState || rightState != lastRightState) {
                lastFlyState = flyState;
                lastInvertHover = invertHover;
                lastDescendState = descendState;
                lastForwardState = forwardState;
                lastBackwardState = backwardState;
                lastLeftState = leftState;
                lastRightState = rightState;
                NetworkHandler.sendToServer(new PacketUpdateInput(invertHover, flyState, descendState, forwardState, backwardState, leftState, rightState));
                CommonJetpackHandler.update(mc.player, invertHover, flyState, descendState, forwardState, backwardState, leftState, rightState);
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent evt) {
        if (evt.phase == TickEvent.Phase.END) {
            tickEnd();
        }
    }
}
