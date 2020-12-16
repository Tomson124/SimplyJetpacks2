package tonius.simplyjetpacks.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.gui.JetpackGuiScreen;
import tonius.simplyjetpacks.item.IHUDInfoProvider;

import java.util.ArrayList;
import java.util.List;

public class HUDTickHandler {
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent()
    public void onOverlayRender(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
        if (mc.player != null) {
            if ((mc.currentScreen == null || mc.currentScreen instanceof JetpackGuiScreen || Config.showHUDWhileChatting && mc.currentScreen instanceof GuiChat) && !mc.gameSettings.hideGUI && !mc.gameSettings.showDebugInfo) {
                ItemStack chestplate = mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                if (chestplate.getItem() instanceof IHUDInfoProvider) {
                    IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();
                    List<String> info = new ArrayList<>();
                    provider.addHUDInfo(info, chestplate, Config.enableEnergyHUD, Config.enableStateHUD);
                    if (info.isEmpty()) return;
                    GL11.glPushMatrix();
                    mc.entityRenderer.setupOverlayRendering();
                    GL11.glScaled(Config.HUDScale, Config.HUDScale, 1.0D);
                    int i = 0;
                    for (String s : info) {
                        RenderUtils.drawStringAtHUDPosition(s, Config.HUDPosition, mc.fontRenderer, Config.HUDOffsetX, Config.HUDOffsetY, Config.HUDScale, Config.HUDTextColor, true, i);
                        i++;
                    }
                    GL11.glPopMatrix();
                }
            }
        }
    }
}