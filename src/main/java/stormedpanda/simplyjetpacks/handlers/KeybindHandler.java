package stormedpanda.simplyjetpacks.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.network.NetworkHandler;
import stormedpanda.simplyjetpacks.network.packets.*;
import stormedpanda.simplyjetpacks.screen.JetpackScreen;

public class KeybindHandler {

    private static boolean lastFlyState = false;
    private static boolean lastDescendState = false;
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;

    public static KeyBinding JETPACK_GUI_KEY;
    public static KeyBinding JETPACK_ENGINE_KEY;
    public static KeyBinding JETPACK_HOVER_KEY;
    public static KeyBinding JETPACK_EHOVER_KEY;
    public static KeyBinding JETPACK_CHARGER_KEY;

    public static void setup() {
        JETPACK_GUI_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_gui", GLFW.GLFW_KEY_K, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_GUI_KEY);
        JETPACK_ENGINE_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_engine", GLFW.GLFW_KEY_J, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_ENGINE_KEY);
        JETPACK_HOVER_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_hover", GLFW.GLFW_KEY_H, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_HOVER_KEY);
        JETPACK_EHOVER_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_ehover", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_EHOVER_KEY);
        JETPACK_CHARGER_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_charger", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_CHARGER_KEY);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player == null) return;
        ItemStack chestStack = player.getItemBySlot(EquipmentSlotType.CHEST);
        Item chestItem = null;
        JetpackItem jetpack;
        if (!chestStack.isEmpty()) { chestItem = chestStack.getItem(); }
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
