package stormedpanda.simplyjetpacks.client.hud;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.items.JetpackItem;
import stormedpanda.simplyjetpacks.util.HUDRenderHelper;

import java.util.ArrayList;
import java.util.List;

public class HUDHandler {

    public final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent()
    public void renderOverlay(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (SimplyJetpacksConfig.CLIENT.enableJetpackHud.get() && !minecraft.options.hideGui && !minecraft.options.renderDebug) {
            if (minecraft.player != null) {
                ItemStack chestplate = minecraft.player.getItemBySlot(EquipmentSlot.CHEST);
                Item item = chestplate.getItem();

                if (!chestplate.isEmpty() && item instanceof JetpackItem) {
                    JetpackItem jetpack = (JetpackItem) item;

                    IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();

                    List<MutableComponent> renderStrings = new ArrayList<>();
                    provider.addHUDInfo(chestplate, renderStrings);
                    if (renderStrings.isEmpty()) {
                        return;
                    }
                    int count = 0;
                    PoseStack matrix = event.getMatrixStack();
                    matrix.pushPose();
                    matrix.scale(SimplyJetpacksConfig.CLIENT.hudScale.get(), SimplyJetpacksConfig.CLIENT.hudScale.get(), 1.0F);
                    Window window = event.getWindow();
                    for (MutableComponent text : renderStrings) {
                        HUDRenderHelper.drawStringAtPosition(window, matrix, text, count);
                        count++;
                    }
                    matrix.popPose();
                }
            }
        }
    }
}
