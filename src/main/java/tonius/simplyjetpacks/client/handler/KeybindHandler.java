package tonius.simplyjetpacks.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.gui.JetpackGuiScreen;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.network.NetworkHandler;
import tonius.simplyjetpacks.network.message.MessageKeybind;
import tonius.simplyjetpacks.network.message.MessageKeyboardSync;
import tonius.simplyjetpacks.util.StackUtil;

public class KeybindHandler {

	public static final KeybindHandler instance = new KeybindHandler();
	static final Minecraft mc = Minecraft.getMinecraft();

	private static int flyKey;
	private static int descendKey;
	private static boolean lastFlyState = false;
	private static boolean lastDescendState = false;
	private static boolean lastForwardState = false;
	private static boolean lastBackwardState = false;
	private static boolean lastLeftState = false;
	private static boolean lastRightState = false;

	public static KeyBinding JETPACK_GUI_KEY;
	public static KeyBinding JETPACK_ENGINE_KEY;
	public static KeyBinding JETPACK_CHARGER_KEY;
	public static KeyBinding JETPACK_HOVER_KEY;
	public static KeyBinding JETPACK_EHOVER_KEY;

	private static final String category = "keybind.simplyjetpacks.category";

	public static void setup() {
		JETPACK_GUI_KEY = new KeyBinding("keybind.simplyjetpacks.gui", Keyboard.KEY_NONE, category);
		ClientRegistry.registerKeyBinding(JETPACK_GUI_KEY);
		JETPACK_ENGINE_KEY = new KeyBinding("keybind.simplyjetpacks.engine", Keyboard.KEY_G, category);
		ClientRegistry.registerKeyBinding(JETPACK_ENGINE_KEY);
		JETPACK_CHARGER_KEY = new KeyBinding("keybind.simplyjetpacks.charger", Keyboard.KEY_P, category);
		ClientRegistry.registerKeyBinding(JETPACK_CHARGER_KEY);
		JETPACK_HOVER_KEY = new KeyBinding("keybind.simplyjetpacks.hover", Keyboard.KEY_L, category);
		ClientRegistry.registerKeyBinding(JETPACK_HOVER_KEY);
		JETPACK_EHOVER_KEY = new KeyBinding("keybind.simplyjetpacks.emergency_hover", Keyboard.KEY_R, category);
		ClientRegistry.registerKeyBinding(JETPACK_EHOVER_KEY);
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		EntityPlayer player = FMLClientHandler.instance().getClient().player;
		ItemStack chestStack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		Item chestItem = StackUtil.getItem(chestStack);

		if (chestItem instanceof ItemJetpack) {
			ItemJetpack jetpack = (ItemJetpack) chestItem;
			if (JETPACK_GUI_KEY.isPressed()) {
				Minecraft.getMinecraft().displayGuiScreen(new JetpackGuiScreen());
			}
			if (JETPACK_ENGINE_KEY.isPressed()) {
				jetpack.toggleState(jetpack.isOn(chestStack), chestStack, "engine_mode", ItemJetpack.TAG_ON, player, Config.enableStateMessages);
				NetworkHandler.instance.sendToServer(new MessageKeybind(MessageKeybind.JetpackPacket.ENGINE));
			}
			if (JETPACK_CHARGER_KEY.isPressed()) {
				jetpack.toggleState(jetpack.isChargerOn(chestStack), chestStack, "charger_mode", ItemJetpack.TAG_CHARGER_ON, player, Config.enableStateMessages);
				NetworkHandler.instance.sendToServer(new MessageKeybind(MessageKeybind.JetpackPacket.CHARGER));
			}
			if (JETPACK_HOVER_KEY.isPressed()) {
				jetpack.toggleState(jetpack.isHoverModeOn(chestStack), chestStack, "hover_mode", ItemJetpack.TAG_HOVERMODE_ON, player, Config.enableStateMessages);
				NetworkHandler.instance.sendToServer(new MessageKeybind(MessageKeybind.JetpackPacket.HOVER));
			}
			if (JETPACK_EHOVER_KEY.isPressed()) {
				jetpack.toggleState(jetpack.isEHoverModeOn(chestStack), chestStack, "emergency_hover_mode", ItemJetpack.TAG_EHOVER_ON, player, Config.enableStateMessages);
				NetworkHandler.instance.sendToServer(new MessageKeybind(MessageKeybind.JetpackPacket.E_HOVER));
			}
		}
		if (chestItem instanceof ItemFluxpack) {
			ItemFluxpack fluxpack = (ItemFluxpack) chestItem;
			if (JETPACK_GUI_KEY.isPressed()) {
				Minecraft.getMinecraft().displayGuiScreen(new JetpackGuiScreen());
			}
			if (JETPACK_ENGINE_KEY.isPressed()) {
				fluxpack.toggleState(fluxpack.isOn(chestStack), chestStack, "engine_mode", ItemFluxpack.TAG_ON, player, Config.enableStateMessages);
				NetworkHandler.instance.sendToServer(new MessageKeybind(MessageKeybind.JetpackPacket.ENGINE));
			}
		}
	}

	public static void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
		flyKey = Keyboard.getKeyIndex(flyKeyName);
		descendKey = Keyboard.getKeyIndex(descendKeyName);
	}

	private static void tickStart() {
		if (mc.player != null) {
			boolean flyState;
			boolean descendState;
			if (Config.customControls) {
				flyState = mc.inGameHasFocus && Keyboard.isKeyDown(flyKey);
				descendState = mc.inGameHasFocus && Keyboard.isKeyDown(descendKey);
			} else {
				flyState = mc.gameSettings.keyBindJump.isKeyDown();
				descendState = mc.gameSettings.keyBindSneak.isKeyDown();
			}

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
				NetworkHandler.instance.sendToServer(new MessageKeyboardSync(flyState, descendState, forwardState, backwardState, leftState, rightState));
				SyncHandler.processKeyUpdate(mc.player, flyState, descendState, forwardState, backwardState, leftState, rightState);
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
