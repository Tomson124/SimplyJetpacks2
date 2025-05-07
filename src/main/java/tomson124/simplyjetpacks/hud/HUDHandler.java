package tomson124.simplyjetpacks.hud;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import tomson124.simplyjetpacks.config.SimplyJetpacksConfig;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.util.JetpackUtil;

import java.util.ArrayList;
import java.util.List;

public final class HUDHandler {

   private static final IGuiOverlay HUD_OVERLAY = (gui, gfx, partialTick, width, height) -> {
        var minecraft = Minecraft.getInstance();
        if (SimplyJetpacksConfig.enableJetpackHud.get() && !minecraft.options.hideGui && !minecraft.options.renderDebug) {
            if (minecraft.player != null) {
                ItemStack chestplate = JetpackUtil.getFromBothSlots(minecraft.player);
                Item item = chestplate.getItem();

                if (!chestplate.isEmpty() && item instanceof JetpackItem) {

                    IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();

                    List<Component> renderStrings = new ArrayList<>();
                    provider.addHUDInfo(chestplate, renderStrings);
                    if (renderStrings.isEmpty()) {
                        return;
                    }
                    int count = 0;
                    PoseStack matrix = gfx.pose();
                    matrix.pushPose();
                    matrix.scale(SimplyJetpacksConfig.hudScale.get(), SimplyJetpacksConfig.hudScale.get(), 1.0F);
                    Window window = minecraft.getWindow();
                    for (Component text : renderStrings) {
                        HUDRenderHelper.drawStringAtPosition(gfx, window, text, count);
                        count++;
                    }
                    matrix.popPose();
                }
            }
        }
    };

    @SubscribeEvent
    public void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "hud", HUD_OVERLAY);
    }
}