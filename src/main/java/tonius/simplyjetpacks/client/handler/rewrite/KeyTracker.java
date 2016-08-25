package tonius.simplyjetpacks.client.handler.rewrite;

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
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.item.rewrite.ItemJetpack;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageKeyBind;
import tonius.simplyjetpacks.network.message.MessageKeyboardSync;
import tonius.simplyjetpacks.util.StackUtil;

public class KeyTracker {

	public static final KeyTracker instance = new KeyTracker();

	static final Minecraft mc = Minecraft.getMinecraft();
	private static int flyKey;
	private static int descendKey;
	private static boolean lastFlyState = false;
	private static boolean lastDescendState = false;
	private static boolean lastForwardState = false;
	private static boolean lastBackwardState = false;
	private static boolean lastLeftState = false;
	private static boolean lastRightState = false;

	private final KeyBinding engineKey;

	private final KeyBinding hoverKey;

	private final KeyBinding chargerKey;

	private final KeyBinding emergencyHoverKey;

	public KeyTracker() {
		engineKey = new KeyBinding(SimplyJetpacks.PREFIX + "keybind.engine", Keyboard.KEY_G, SimplyJetpacks.PREFIX + "category.simplyjetpacks");
		ClientRegistry.registerKeyBinding(engineKey);

		hoverKey = new KeyBinding(SimplyJetpacks.PREFIX + "keybind.hover", Keyboard.KEY_L, SimplyJetpacks.PREFIX + "category.simplyjetpacks");
		ClientRegistry.registerKeyBinding(hoverKey);

		chargerKey = new KeyBinding(SimplyJetpacks.PREFIX + "keybind.charger", Keyboard.KEY_P, SimplyJetpacks.PREFIX + "category.simplyjetpacks");
		ClientRegistry.registerKeyBinding(chargerKey);

		emergencyHoverKey = new KeyBinding(SimplyJetpacks.PREFIX + "keybind.emergencyhover", Keyboard.KEY_R, SimplyJetpacks.PREFIX + "category.simplyjetpacks");
		ClientRegistry.registerKeyBinding(emergencyHoverKey);
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
		ItemStack chestStack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		Item chestItem = StackUtil.getItem(chestStack);

		if(chestItem instanceof ItemJetpack) {
			if(engineKey.isPressed()) {
				ItemJetpack jetpack = (ItemJetpack)chestItem;

				jetpack.toggleState(jetpack.isOn(chestStack), chestStack, null, jetpack.TAG_ON, player, true);
				PacketHandler.instance.sendToServer(new MessageKeyBind(MessageKeyBind.JetpackPacket.ENGINE));
			}
			if(hoverKey.isPressed()) {
				ItemJetpack jetpack = (ItemJetpack)chestItem;

				jetpack.toggleState(jetpack.isHoverModeOn(chestStack), chestStack, "hoverMode", jetpack.TAG_HOVERMODE_ON, player, true);
				PacketHandler.instance.sendToServer(new MessageKeyBind(MessageKeyBind.JetpackPacket.HOVER));
			}
		}
	}

	public static void updateCustomKeybinds(String flyKeyName, String descendKeyName)
	{
		flyKey = Keyboard.getKeyIndex(flyKeyName);
		descendKey = Keyboard.getKeyIndex(descendKeyName);
	}

	private static void tickStart()
	{
		if(mc.thePlayer != null)
		{
			boolean flyState;
			boolean descendState;
			if(Config.customControls)
			{
				flyState = mc.inGameHasFocus && Keyboard.isKeyDown(flyKey);
				descendState = mc.inGameHasFocus && Keyboard.isKeyDown(descendKey);
			}
			else
			{
				flyState = mc.gameSettings.keyBindJump.isKeyDown();
				descendState = mc.gameSettings.keyBindSneak.isKeyDown();
			}

			boolean forwardState = mc.gameSettings.keyBindForward.isKeyDown();
			boolean backwardState = mc.gameSettings.keyBindBack.isKeyDown();
			boolean leftState = mc.gameSettings.keyBindLeft.isKeyDown();
			boolean rightState = mc.gameSettings.keyBindRight.isKeyDown();

			if(flyState != lastFlyState || descendState != lastDescendState || forwardState != lastForwardState || backwardState != lastBackwardState || leftState != lastLeftState || rightState != lastRightState)
			{
				lastFlyState = flyState;
				lastDescendState = descendState;

				lastForwardState = forwardState;
				lastBackwardState = backwardState;
				lastLeftState = leftState;
				lastRightState = rightState;
				PacketHandler.instance.sendToServer(new MessageKeyboardSync(flyState, descendState, forwardState, backwardState, leftState, rightState));
				SyncHandler.processKeyUpdate(mc.thePlayer, flyState, descendState, forwardState, backwardState, leftState, rightState);
			}
		}
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent evt)
	{
		if(evt.phase == TickEvent.Phase.START)
		{
			tickStart();
		}
	}
}
