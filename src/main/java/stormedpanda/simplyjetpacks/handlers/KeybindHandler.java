package stormedpanda.simplyjetpacks.handlers;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.network.NetworkHandler;
import stormedpanda.simplyjetpacks.network.packets.*;
import stormedpanda.simplyjetpacks.screen.JetpackScreen;
import stormedpanda.simplyjetpacks.util.JetpackUtil;

public class KeybindHandler {

    private static boolean lastFlyState = false;
    private static boolean lastDescendState = false;
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;

    /*public static KeyMapping JETPACK_GUI_KEY;
    public static KeyMapping JETPACK_ENGINE_KEY;
    public static KeyMapping JETPACK_HOVER_KEY;
    public static KeyMapping JETPACK_EHOVER_KEY;
    public static KeyMapping JETPACK_CHARGER_KEY;
    public static KeyMapping JETPACK_THROTTLE_INCREASE;
    public static KeyMapping JETPACK_THROTTLE_DECREASE;*/

    public static KeyMapping JETPACK_GUI_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_gui", GLFW.GLFW_KEY_K, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_ENGINE_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_engine", GLFW.GLFW_KEY_J, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_HOVER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_hover", GLFW.GLFW_KEY_H, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_EHOVER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_ehover", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_CHARGER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_charger", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_THROTTLE_INCREASE = new KeyMapping("keybind.simplyjetpacks.jetpack_throttle_increase", GLFW.GLFW_KEY_PERIOD, "keybind.simplyjetpacks.category");
    public static KeyMapping JETPACK_THROTTLE_DECREASE = new KeyMapping("keybind.simplyjetpacks.jetpack_throttle_decrease", GLFW.GLFW_KEY_COMMA, "keybind.simplyjetpacks.category");

    // TODO: check this
    /*public static void setup() {
        JETPACK_GUI_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_gui", GLFW.GLFW_KEY_K, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_GUI_KEY);
        JETPACK_ENGINE_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_engine", GLFW.GLFW_KEY_J, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_ENGINE_KEY);
        JETPACK_HOVER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_hover", GLFW.GLFW_KEY_H, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_HOVER_KEY);
        JETPACK_EHOVER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_ehover", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_EHOVER_KEY);
        JETPACK_CHARGER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_charger", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_CHARGER_KEY);
        JETPACK_THROTTLE_INCREASE = new KeyMapping("keybind.simplyjetpacks.jetpack_throttle_increase", GLFW.GLFW_KEY_PERIOD, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_THROTTLE_INCREASE);
        JETPACK_THROTTLE_DECREASE = new KeyMapping("keybind.simplyjetpacks.jetpack_throttle_decrease", GLFW.GLFW_KEY_COMMA, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_THROTTLE_DECREASE);
    }*/

    /*@SubscribeEvent
    public void onKeymapEvent(RegisterKeyMappingsEvent event) {
        SimplyJetpacks.LOGGER.info("REGISTERING KEY MAP EVENT HERE");
        JETPACK_GUI_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_gui", GLFW.GLFW_KEY_K, "keybind.simplyjetpacks.category");
        event.register(JETPACK_GUI_KEY);
        JETPACK_ENGINE_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_engine", GLFW.GLFW_KEY_J, "keybind.simplyjetpacks.category");
        event.register(JETPACK_ENGINE_KEY);
        JETPACK_HOVER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_hover", GLFW.GLFW_KEY_H, "keybind.simplyjetpacks.category");
        event.register(JETPACK_HOVER_KEY);
        JETPACK_EHOVER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_ehover", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
        event.register(JETPACK_EHOVER_KEY);
        JETPACK_CHARGER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_charger", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
        event.register(JETPACK_CHARGER_KEY);
        JETPACK_THROTTLE_INCREASE = new KeyMapping("keybind.simplyjetpacks.jetpack_throttle_increase", GLFW.GLFW_KEY_PERIOD, "keybind.simplyjetpacks.category");
        event.register(JETPACK_THROTTLE_INCREASE);
        JETPACK_THROTTLE_DECREASE = new KeyMapping("keybind.simplyjetpacks.jetpack_throttle_decrease", GLFW.GLFW_KEY_COMMA, "keybind.simplyjetpacks.category");
        event.register(JETPACK_THROTTLE_DECREASE);
    }*/

    @SubscribeEvent
    public void onKeyInput(InputEvent.InteractionKeyMappingTriggered event) {
        Player player = Minecraft.getInstance().player;
        if (player == null) {
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
            if (JETPACK_GUI_KEY.isDown()) {
                Minecraft.getInstance().setScreen(new JetpackScreen());
            }
            if (JETPACK_ENGINE_KEY.isDown()) {
                NetworkHandler.sendToServer(new PacketToggleEngine());
            }
            if (JETPACK_HOVER_KEY.isDown()) {
                NetworkHandler.sendToServer(new PacketToggleHover());
            }
            if (JETPACK_EHOVER_KEY.isDown()) {
                NetworkHandler.sendToServer(new PacketToggleEHover());
            }
            if (JETPACK_CHARGER_KEY.isDown()) {
                NetworkHandler.sendToServer(new PacketToggleCharger());
            }
            if (JETPACK_THROTTLE_INCREASE.isDown()) {
                NetworkHandler.sendToServer(new PacketUpdateThrottle(Math.max(0, Math.min(100,jetpack.getThrottle(chestStack) + 10))));
            }
            if (JETPACK_THROTTLE_DECREASE.isDown()) {
                NetworkHandler.sendToServer(new PacketUpdateThrottle(Math.max(0, Math.min(100, jetpack.getThrottle(chestStack) - 10))));
            }
        }
    }

    private static void tickEnd() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            boolean flyState = mc.player.input.jumping;
            boolean descendState = mc.player.input.shiftKeyDown;
            boolean forwardState = mc.player.input.up;
            boolean backwardState = mc.player.input.down;
            boolean leftState = mc.player.input.left;
            boolean rightState = mc.player.input.right;
            if (flyState != lastFlyState || descendState != lastDescendState || forwardState != lastForwardState || backwardState != lastBackwardState || leftState != lastLeftState || rightState != lastRightState) {
                lastFlyState = flyState;
                lastDescendState = descendState;
                lastForwardState = forwardState;
                lastBackwardState = backwardState;
                lastLeftState = leftState;
                lastRightState = rightState;
                NetworkHandler.sendToServer(new PacketUpdateInput(flyState, descendState, forwardState, backwardState, leftState, rightState));
                CommonJetpackHandler.update(mc.player, flyState, descendState, forwardState, backwardState, leftState, rightState);
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
