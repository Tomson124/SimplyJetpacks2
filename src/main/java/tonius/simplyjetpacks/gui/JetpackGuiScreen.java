package tonius.simplyjetpacks.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.handler.KeybindHandler;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.network.NetworkHandler;
import tonius.simplyjetpacks.network.message.MessageKeybind;
import tonius.simplyjetpacks.util.JetpackUtil;
import tonius.simplyjetpacks.util.SJStringUtil;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class JetpackGuiScreen extends GuiScreen {

    private static final int WIDTH = 176;
    private static final int HEIGHT = 100;

    private static final Minecraft minecraft = Minecraft.getMinecraft();

    private final ResourceLocation JETPACK_TEXTURE = new ResourceLocation(SimplyJetpacks.MODID, "textures/gui/jetpack_screen.png");

    private final ItemStack itemStack;
    private final Item item;

    private GuiButtonImage engine, hover, ehover, charger;

    public JetpackGuiScreen() {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.itemStack = JetpackUtil.getFromBothSlots(minecraft.player);
        this.item = JetpackUtil.getFromBothSlots(minecraft.player).getItem();
    }

    private static void drawStringCenter(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow) {
        fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string) / 2.0f, y, color, shadow);
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        addButton(this.engine = new GuiButtonImage(1,relX + 120, relY + 16, 20, 20, 176, 0, 20, JETPACK_TEXTURE));

        if (item instanceof ItemJetpack) {
            ItemJetpack jetpack = (ItemJetpack) item;
            if (jetpack.canHover(itemStack)) {
                addButton(this.hover = new GuiButtonImage(3, relX + 120, relY + 38, 20, 20, 216, 0, 20, JETPACK_TEXTURE));
                this.hover.enabled = true;
            } else {
                addButton(this.hover = new GuiButtonImage(0, relX + 120, relY + 38, 20, 20, 196, 40, 0, JETPACK_TEXTURE));
                this.hover.enabled = false;
            }
            if (jetpack.canCharge(itemStack)) {
                addButton(this.charger = new GuiButtonImage(2, relX + 142, relY + 16, 20, 20, 196, 0, 20, JETPACK_TEXTURE));
                this.charger.enabled = true;
            } else {
                addButton(this.charger = new GuiButtonImage(0, relX + 142, relY + 16, 20, 20, 196, 40, 0, JETPACK_TEXTURE));
                this.charger.enabled = false;
            }
            if (jetpack.canEHover(itemStack)) {
                addButton(this.ehover = new GuiButtonImage(4, relX + 142, relY + 38, 20, 20, 236, 0, 20, JETPACK_TEXTURE));
                this.ehover.enabled = true;
            } else {
                addButton(this.ehover = new GuiButtonImage(0, relX + 142, relY + 38, 20, 20, 236, 40, 0, JETPACK_TEXTURE));
                this.ehover.enabled = false;
            }
        }
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        FontRenderer fontRenderer = minecraft.fontRenderer;
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;
        float mousePosX = (float) mouseX;
        float mousePosY = (float) mouseY;

        minecraft.getTextureManager().bindTexture(JETPACK_TEXTURE);
        drawTexturedModalRect(relX, relY, 0, 0, WIDTH, HEIGHT);
        drawStringCenter(itemStack.getDisplayName(), fontRenderer, relX + 88, relY + 5, 0xFFFFFF, true);
        GuiInventory.drawEntityOnScreen(relX + 80, relY + 90, 40, (float) (relX + 51) - mousePosX, (float) (relY + 75 - 50) - mousePosY, minecraft.player);
        minecraft.getTextureManager().bindTexture(JETPACK_TEXTURE);

        int amount = getEnergyBarAmount(); // texture height
        int barOffset = 78 - amount;
        int barX = 0;
        boolean useGradient = false;

        String modId = null;
        String text = null;
        boolean isCreative = false;
        int energyMax = 0;
        int energyStored = 0;

        if (item instanceof ItemJetpack) {
            ItemJetpack jetpack = (ItemJetpack) item;
            modId = jetpack.getModId(itemStack);
            isCreative = jetpack.isCreative(itemStack);
            energyStored = jetpack.getEnergyStored(itemStack);
            energyMax = jetpack.getMaxEnergyStored(itemStack);
        }

        if (item instanceof ItemFluxpack) {
            ItemFluxpack fluxpack = (ItemFluxpack) item;
            modId = fluxpack.getModId(itemStack);
            isCreative = fluxpack.isCreative(itemStack);
            energyStored = fluxpack.getEnergyStored(itemStack);
            energyMax = fluxpack.getMaxEnergyStored(itemStack);
        }

        switch (modId) {
            case ("mek"):
                barX = 28;
                break;
            case ("ie"):
                barX = 56;
                useGradient = true;
                break;
            case ("eio"):
                barX = 84;
                break;
            default:
                break;
        }

        if (isCreative) {
            drawTexturedModalRect(relX + 10, relY + 16, 70, 178, 14, 78);
        } else {
            drawTexturedModalRect(relX + 10, relY + 16, barX, 178, 14, 78);
            if (useGradient) {
                drawGradientRect(relX + 12, relY + 18 + barOffset, relX + 22, relY + 14 + 78, 0xffb51500, 0xff600b00);
            } else {
                drawTexturedModalRect(relX + 10, relY + 16 + 1 + barOffset, barX + 14, 178 + 1, 14, amount - 2);
            }
        }

        if (mouseX >= relX + 10 && mouseY >= relY + 16 && mouseX < relX + 10 + 14 && mouseY < relY + 16 + 78) {
            if (energyStored == 0 && !isCreative) {
                text = SJStringUtil.getHUDEnergyText("jetpack", 0, 0);
            } else if (isCreative) {
                text = SJStringUtil.getEnergyText(energyStored, energyMax, isCreative);
            }
            if (text != null) {
                drawHoveringText(new ArrayList<>(Collections.singleton(text)), mouseX, mouseY, fontRenderer);
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private int getEnergyBarAmount() {
        if (item instanceof ItemJetpack) {
            ItemJetpack jetpack = (ItemJetpack) item;
            if (jetpack.isCreative(itemStack)) {
                return 78;
            }
            int i = jetpack.getEnergyStored(itemStack);
            int j = jetpack.getMaxEnergyStored(itemStack);
            return (int) (j != 0 && i != 0 ? (long) i * 78 / j : 0);
        } else if (item instanceof ItemFluxpack) {
            ItemFluxpack fluxpack = (ItemFluxpack) item;
            if (fluxpack.isCreative(itemStack)) {
                return 78;
            }
            int i = fluxpack.getEnergyStored(itemStack);
            int j = fluxpack.getMaxEnergyStored(itemStack);
            return (int) (j != 0 && i != 0 ? (long) i * 78 / j : 0);
        } else {
            return 0;
        }
    }

    @Override
    protected void actionPerformed(@Nonnull GuiButton button) {
        if (item instanceof ItemFluxpack) {
            if (button.id == 1) {
                NetworkHandler.instance.sendToServer(new MessageKeybind(MessageKeybind.JetpackPacket.ENGINE));
            }
        } else if (item instanceof ItemJetpack) {
            if (button.id == 1) {
                NetworkHandler.instance.sendToServer(new MessageKeybind(MessageKeybind.JetpackPacket.ENGINE));
            } else if (button.id == 2) {
                NetworkHandler.instance.sendToServer(new MessageKeybind(MessageKeybind.JetpackPacket.CHARGER));
            } else if (button.id == 3) {
                NetworkHandler.instance.sendToServer(new MessageKeybind(MessageKeybind.JetpackPacket.HOVER));
            } else if (button.id == 4) {
                NetworkHandler.instance.sendToServer(new MessageKeybind(MessageKeybind.JetpackPacket.E_HOVER));
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (KeybindHandler.JETPACK_GUI_KEY.getKeyCode() == keyCode || minecraft.gameSettings.keyBindInventory.getKeyCode() == keyCode) {
            minecraft.displayGuiScreen(null);
        } else {
            super.keyTyped(typedChar, keyCode);
        }
    }
}
