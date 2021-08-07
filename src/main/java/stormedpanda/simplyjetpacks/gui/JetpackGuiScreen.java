package stormedpanda.simplyjetpacks.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.handlers.KeybindHandler;
import stormedpanda.simplyjetpacks.items.JetpackItem;
import stormedpanda.simplyjetpacks.network.NetworkHandler;
import stormedpanda.simplyjetpacks.network.packets.PacketToggleCharger;
import stormedpanda.simplyjetpacks.network.packets.PacketToggleEHover;
import stormedpanda.simplyjetpacks.network.packets.PacketToggleEngine;
import stormedpanda.simplyjetpacks.network.packets.PacketToggleHover;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class JetpackGuiScreen extends Screen {

    private static final int WIDTH = 176;
    private static final int HEIGHT = 100;

    private static final Minecraft minecraft = Minecraft.getInstance();

    private final ResourceLocation GUI_BASE = new ResourceLocation(SimplyJetpacks.MODID, "textures/gui/gui_base.png");
    private final ResourceLocation ENERGY_BAR = new ResourceLocation(SimplyJetpacks.MODID, "textures/gui/energy_bar.png");

    private static final Component energyStorageTooltip = new TranslatableComponent("tooltips.simplyjetpacks.jetpack_gui.energyStorage");

    public JetpackGuiScreen() {
        super(new TranslatableComponent("screen.simplyjetpacks.jetpack_gui.title"));
        this.width = WIDTH;
        this.height = HEIGHT;
    }

    @Override
    protected void init() {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        this.addRenderableWidget(new ImageButton(relX + 120, relY + 16, 20, 20, 176, 0, 20, GUI_BASE, button -> NetworkHandler.sendToServer(new PacketToggleEngine())));
        this.addRenderableWidget(new ImageButton(relX + 120, relY + 38, 20, 20, 216, 0, 20, GUI_BASE, button -> NetworkHandler.sendToServer(new PacketToggleHover())));

        ItemStack stack = minecraft.player.getItemBySlot(EquipmentSlot.CHEST);
        Item item = stack.getItem();
        if (item instanceof JetpackItem jetpack) {
            if (jetpack.getType().canCharge()) {
                this.addRenderableWidget(new ImageButton(relX + 142, relY + 16, 20, 20, 196, 0, 20, GUI_BASE, button -> NetworkHandler.sendToServer(new PacketToggleCharger())));
            } else {
                this.addRenderableWidget(new ImageButton(relX + 142, relY + 16, 20, 20, 196, 40, 0, GUI_BASE, button -> buttonClicked("CHARGER NOT AVAILABLE")));
            }
            if (jetpack.getType().canEHover()) {
                this.addRenderableWidget(new ImageButton(relX + 142, relY + 38, 20, 20, 236, 0, 20, GUI_BASE, button -> NetworkHandler.sendToServer(new PacketToggleEHover())));
            } else {
                this.addRenderableWidget(new ImageButton(relX + 142, relY + 38, 20, 20, 236, 40, 0, GUI_BASE, button -> buttonClicked("E HOVER NOT AVAILABLE")));
            }
        }
    }

    private void buttonClicked(String name) {
        SimplyJetpacks.LOGGER.info(name);
    }

    @Override
    public void render(@Nonnull PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        Font fontRenderer = minecraft.font;
        RenderSystem.setShaderTexture(0, GUI_BASE);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;
        float mousePosX = (float) mouseX;
        float mousePosY = (float) mouseY;
        this.blit(stack, relX, relY, 0, 0, WIDTH, HEIGHT);
        drawCenteredString(stack, fontRenderer, new TranslatableComponent(minecraft.player.getItemBySlot(EquipmentSlot.CHEST).getDescriptionId()), relX + 88, relY + 5, 0xFFFFFF);
        InventoryScreen.renderEntityInInventory(relX + 80, relY + 90, 40, (float)(relX + 51) - mousePosX, (float)(relY + 75 - 50) - mousePosY, minecraft.player);
        RenderSystem.setShaderTexture(0, ENERGY_BAR);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        blit(stack, relX + 10, relY + 16, 0, 0, 14, 78, 128, 128);
        int amount = getEnergyBarAmount();
        int barOffset = 78-amount;
        blit(stack, relX + 10, relY + 16 + barOffset, 14, 0, 14, amount, 128, 128);
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    private int getEnergyBarAmount() {
        ItemStack stack = minecraft.player.getItemBySlot(EquipmentSlot.CHEST);
        Item item = stack.getItem();
        if (item instanceof JetpackItem jetpack) {
            if (jetpack.isCreative()) {
                return 78;
            }
            int i = jetpack.getEnergyStored(stack);
            int j = jetpack.getCapacity();
            return (int) (j != 0 && i != 0 ? (long) i * 78 / j : 0);
        } else {
            return 0;
        }
    }

    @Override
    public boolean isPauseScreen() { return false; }
    @Override
    public boolean shouldCloseOnEsc() { return true; }
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (KeybindHandler.JETPACK_GUI_KEY.matches(keyCode, scanCode)) {
            minecraft.setScreen(null);
            return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }
}