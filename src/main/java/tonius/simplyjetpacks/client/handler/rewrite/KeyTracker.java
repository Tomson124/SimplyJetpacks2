package tonius.simplyjetpacks.client.handler.rewrite;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.rewrite.ItemJetpack;
import tonius.simplyjetpacks.util.StackUtil;

public class KeyTracker {

	public static final KeyTracker instance = new KeyTracker();

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
			}
			if(hoverKey.isPressed()) {
				ItemJetpack jetpack = (ItemJetpack)chestItem;

				jetpack.toggleState(jetpack.isHoverModeOn(chestStack), chestStack, "hoverMode", jetpack.TAG_HOVERMODE_ON, player, true);
			}
		}
	}

	/*private void handleCharger() {
		if(!JumpUpgrade.isEquipped(Minecraft.getMinecraft().thePlayer)) {
			return;
		}
		if(stepAssistKey.isPressed()) {
			toggleDarkSteelController(Type.STEP_ASSIST, "darksteel.upgrade.stepAssist");
		}
	}

	private void handleEHover() {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if(!GogglesOfRevealingUpgrade.isUpgradeEquipped(player)){
			return;
		}
		if(gogglesKey.isPressed()) {
			boolean isActive = !DarkSteelItems.itemDarkSteelHelmet.isGogglesUgradeActive();
			sendEnabledChatMessage("darksteel.upgrade.goggles", isActive);
			DarkSteelItems.itemDarkSteelHelmet.setGogglesUgradeActive(isActive);
		}
	}*/
}
