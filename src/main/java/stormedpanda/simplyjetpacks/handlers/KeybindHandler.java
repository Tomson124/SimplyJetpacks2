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
import stormedpanda.simplyjetpacks.gui.JetpackGuiScreen;
import stormedpanda.simplyjetpacks.items.JetpackItem;
import stormedpanda.simplyjetpacks.network.NetworkHandler;
import stormedpanda.simplyjetpacks.network.packets.*;

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
        JETPACK_GUI_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_gui", GLFW.GLFW_KEY_K, "keybind.categories.simplyjetpacks");
        ClientRegistry.registerKeyBinding(JETPACK_GUI_KEY);
        JETPACK_ENGINE_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_engine", GLFW.GLFW_KEY_J, "keybind.categories.simplyjetpacks");
        ClientRegistry.registerKeyBinding(JETPACK_ENGINE_KEY);
        JETPACK_HOVER_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_hover", GLFW.GLFW_KEY_H, "keybind.categories.simplyjetpacks");
        ClientRegistry.registerKeyBinding(JETPACK_HOVER_KEY);
        JETPACK_EHOVER_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_ehover", GLFW.GLFW_KEY_UNKNOWN, "keybind.categories.simplyjetpacks");
        ClientRegistry.registerKeyBinding(JETPACK_EHOVER_KEY);
        JETPACK_CHARGER_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_charger", GLFW.GLFW_KEY_UNKNOWN, "keybind.categories.simplyjetpacks");
        ClientRegistry.registerKeyBinding(JETPACK_CHARGER_KEY);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player == null) return;
        ItemStack chestStack = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        Item chestItem = null;
        JetpackItem jetpack;
        if(!chestStack.isEmpty()) { chestItem = chestStack.getItem(); }
        if (chestItem instanceof JetpackItem) {
            jetpack = (JetpackItem) chestItem;
            if(JETPACK_GUI_KEY.isPressed()) {
                Minecraft.getInstance().displayGuiScreen(new JetpackGuiScreen());
            }
            if (JETPACK_ENGINE_KEY.isPressed()) {
                NetworkHandler.sendToServer(new PacketToggleEngine());
            }
            if (JETPACK_HOVER_KEY.isPressed()) {
                NetworkHandler.sendToServer(new PacketToggleHover());
            }
            if (JETPACK_EHOVER_KEY.isPressed()) {
                NetworkHandler.sendToServer(new PacketToggleEHover());
            }
            if (JETPACK_CHARGER_KEY.isPressed()) {
                NetworkHandler.sendToServer(new PacketToggleCharger());
            }
        }
    }

    private static void tickStart() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            boolean flyState = mc.gameSettings.keyBindJump.isKeyDown();
            boolean descendState = mc.gameSettings.keyBindSneak.isKeyDown();
            boolean forwardState = mc.gameSettings.keyBindForward.isKeyDown();
            boolean backwardState = mc.gameSettings.keyBindBack.isKeyDown();
            boolean leftState = mc.gameSettings.keyBindLeft.isKeyDown();
            boolean rightState = mc.gameSettings.keyBindRight.isKeyDown();
            if (flyState != lastFlyState || descendState != lastDescendState || forwardState != lastForwardState || backwardState != lastBackwardState || leftState != lastLeftState || rightState != lastRightState) {
                lastFlyState = flyState;
                lastDescendState = descendState;
                lastForwardState = forwardState;
                lastBackwardState = backwardState;
                lastLeftState = leftState;
                lastRightState = rightState;
                NetworkHandler.sendToServer(new PacketUpdateInput(flyState, descendState, forwardState, backwardState, leftState, rightState));
                SyncHandler.update(mc.player, flyState, descendState, forwardState, backwardState, leftState, rightState);
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent evt) {
        if (evt.phase == TickEvent.Phase.START) {
            tickStart();
        }
    }
}
