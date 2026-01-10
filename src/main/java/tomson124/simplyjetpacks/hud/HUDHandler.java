package tomson124.simplyjetpacks.hud;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.config.SimplyJetpacksConfig;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.util.JetpackUtil;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT, modid = SimplyJetpacks.MODID)
public final class HUDHandler {

    @SubscribeEvent
    public void onRegisterGuiOverlays(RenderGuiEvent.Pre event) {
        var minecraft = Minecraft.getInstance();
        if (SimplyJetpacksConfig.enableJetpackHud.get() && !minecraft.options.hideGui) {
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

                    var gfx = event.getGuiGraphics();
                    var matrix = gfx.pose();

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
    }
}