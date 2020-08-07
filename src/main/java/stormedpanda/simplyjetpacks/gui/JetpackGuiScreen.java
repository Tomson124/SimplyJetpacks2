package stormedpanda.simplyjetpacks.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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

    private final ResourceLocation GUI_BASE = new ResourceLocation(SimplyJetpacks.MODID, "textures/gui/jetpack_gui_pack.png");
    private final ResourceLocation ENERGY_BAR = new ResourceLocation(SimplyJetpacks.MODID, "textures/gui/energy_bar.png");

    private static final ITextComponent energyStorageTooltip = new TranslationTextComponent("tooltips.simplyjetpacks.jetpack_gui.energyStorage");

    public JetpackGuiScreen() {
        super(new TranslationTextComponent("screen.simplyjetpacks.jetpack_gui.title"));
        this.width = WIDTH;
        this.height = HEIGHT;
    }

    @Override
    protected void init() {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        this.addButton(new ImageButton(relX + 120, relY + 16, 20, 20, 176, 0, 20, GUI_BASE, button -> NetworkHandler.sendToServer(new PacketToggleEngine())));
        this.addButton(new ImageButton(relX + 120, relY + 38, 20, 20, 216, 0, 20, GUI_BASE, button -> NetworkHandler.sendToServer(new PacketToggleHover())));

        ItemStack stack = minecraft.player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        Item item = stack.getItem();
        if (item instanceof JetpackItem) {
            JetpackItem jetpack = (JetpackItem) item;
            if (jetpack.getType().canCharge()) {
                this.addButton(new ImageButton(relX + 142, relY + 16, 20, 20, 196, 0, 20, GUI_BASE, button -> NetworkHandler.sendToServer(new PacketToggleCharger())));
            } else {
                this.addButton(new ImageButton(relX + 142, relY + 16, 20, 20, 196, 40, 0, GUI_BASE, button -> buttonClicked("CHARGER NOT AVAILABLE")));
            }
            if (jetpack.getType().canEHover()) {
                this.addButton(new ImageButton(relX + 142, relY + 38, 20, 20, 236, 0, 20, GUI_BASE, button -> NetworkHandler.sendToServer(new PacketToggleEHover())));
            } else {
                this.addButton(new ImageButton(relX + 142, relY + 38, 20, 20, 236, 40, 0, GUI_BASE, button -> buttonClicked("E HOVER NOT AVAILABLE")));
            }
        }
    }

    private void buttonClicked(String name) {
        SimplyJetpacks.LOGGER.info(name);
    }

    @Override
    public void render(@Nonnull MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        FontRenderer fontRenderer = minecraft.fontRenderer;
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;
        float mousePosX = (float) mouseX;
        float mousePosY = (float) mouseY;
        minecraft.getTextureManager().bindTexture(GUI_BASE);
        this.blit(stack, relX, relY, 0, 0, WIDTH, HEIGHT);
        drawCenteredString(stack, fontRenderer, new TranslationTextComponent(minecraft.player.getItemStackFromSlot(EquipmentSlotType.CHEST).getTranslationKey()), relX + 88, relY + 5, 0xFFFFFF);
        InventoryScreen.drawEntityOnScreen(relX + 80, relY + 90, 40, (float)(relX + 51) - mousePosX, (float)(relY + 75 - 50) - mousePosY, minecraft.player);
        minecraft.getTextureManager().bindTexture(ENERGY_BAR);
        blit(stack, relX + 10, relY + 16, 0, 0, 14, 78, 128, 128);
        int amount = getEnergyBarAmount();
        int barOffset = 78-amount;
        blit(stack, relX + 10, relY + 16 + barOffset, 14, 0, 14, amount, 128, 128);
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    private int getEnergyBarAmount() {
        ItemStack stack = minecraft.player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        Item item = stack.getItem();
        if (item instanceof JetpackItem) {
            JetpackItem jetpack = (JetpackItem) item;
            if (jetpack.isCreative()) { return 78; }
            int i = jetpack.getEnergyStored(stack);
            int j = jetpack.getCapacity();
            return (int) (j != 0 && i != 0 ? (long) i * 78 / j : 0);
        } else { return 0; }
    }

    @Override
    public boolean isPauseScreen() { return false; }
    @Override
    public boolean shouldCloseOnEsc() { return true; }
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (KeybindHandler.JETPACK_GUI_KEY.matchesKey(keyCode, scanCode)) {
            minecraft.displayGuiScreen(null);
            return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }
}