package stormedpanda.simplyjetpacks.client.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
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
        if (SimplyJetpacksConfig.CLIENT.enableJetpackHud.get() && !minecraft.gameSettings.hideGUI && !minecraft.gameSettings.showDebugInfo) {
            if (minecraft.player != null) {
                ItemStack chestplate = minecraft.player.getItemStackFromSlot(EquipmentSlotType.CHEST);
                Item item = chestplate.getItem();

                if (!chestplate.isEmpty() && item instanceof JetpackItem) {
                    JetpackItem jetpack = (JetpackItem) item;

                    IHUDInfoProvider provider = (IHUDInfoProvider) chestplate.getItem();

                    List<ITextComponent> renderStrings = new ArrayList<>();
                    provider.addHUDInfo(chestplate, renderStrings);
                    if (renderStrings.isEmpty()) {
                        return;
                    }
                    int count = 0;
                    MatrixStack matrix = event.getMatrixStack();
                    matrix.push();
                    matrix.scale(SimplyJetpacksConfig.CLIENT.hudScale.get(), SimplyJetpacksConfig.CLIENT.hudScale.get(), 1.0F);
                    MainWindow window = event.getWindow();
                    for (ITextComponent text : renderStrings) {
                        HUDRenderHelper.drawStringAtPosition(window, matrix, text, count);
                        count++;
                    }
                    matrix.pop();
                }
            }
        }
    }
}
