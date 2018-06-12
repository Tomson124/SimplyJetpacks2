package tonius.simplyjetpacks.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.IHUDInfoProvider;

public class HUDTickHandler {
	private static final Minecraft mc = Minecraft.getMinecraft();

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onOverlayRender(RenderGameOverlayEvent.Text event) {
		if (mc.player != null) {
			if ((mc.currentScreen == null || Config.showHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo) {
				ItemStack chestplate = mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
				if (chestplate != null && chestplate.getItem() instanceof IHUDInfoProvider) {
					IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();
					provider.addHUDInfo(event, chestplate, Config.enableFuelHUD, Config.enableStateHUD);
				}
			}
		}
	}
}