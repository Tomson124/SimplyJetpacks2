package tonius.simplyjetpacks.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.handler.KeybindHandler;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.network.NetworkHandler;
import tonius.simplyjetpacks.network.message.MessageKeybind;

import javax.annotation.Nonnull;
import java.io.IOException;

public class JetpackGuiScreen extends GuiScreen {

    private static final int WIDTH = 176;
    private static final int HEIGHT = 100;

    private static final Minecraft minecraft = Minecraft.getMinecraft();

    private final ResourceLocation GUI_BASE = new ResourceLocation(SimplyJetpacks.MODID, "textures/gui/jetpack_screen.png");
    private final ResourceLocation ENERGY_BAR = new ResourceLocation(SimplyJetpacks.MODID, "textures/gui/energy_bar.png");

    public JetpackGuiScreen() {
        this.width = WIDTH;
        this.height = HEIGHT;
    }

    private static void drawStringCenter(String string, FontRenderer fontRenderer, int x, int y, int color, boolean shadow) {
        fontRenderer.drawString(string, x - fontRenderer.getStringWidth(string) / 2.0f, y, color, shadow);
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        this.addButton(new GuiButtonImage(1, relX + 120, relY + 16, 20, 20, 176, 0, 20, GUI_BASE));

        ItemStack stack = minecraft.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        Item item = stack.getItem();
        if (item instanceof ItemJetpack) {
            ItemJetpack jetpack = (ItemJetpack) item;
            this.addButton(new GuiButtonImage(3, relX + 120, relY + 38, 20, 20, 216, 0, 20, GUI_BASE));
            if (jetpack.canCharge(stack)) {
                this.addButton(new GuiButtonImage(2, relX + 142, relY + 16, 20, 20, 196, 0, 20, GUI_BASE));
            } else {
                this.addButton(new GuiButtonImage(0, relX + 142, relY + 16, 20, 20, 196, 40, 0, GUI_BASE));
            }
            if (jetpack.canEHover(stack)) {
                this.addButton(new GuiButtonImage(4, relX + 142, relY + 38, 20, 20, 236, 0, 20, GUI_BASE));
            } else {
                this.addButton(new GuiButtonImage(0, relX + 142, relY + 38, 20, 20, 236, 40, 0, GUI_BASE));
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
        minecraft.getTextureManager().bindTexture(GUI_BASE);
        this.drawTexturedModalRect(relX, relY, 0, 0, WIDTH, HEIGHT);

        //drawStringCenter(I18n.format(minecraft.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getUnlocalizedName() + ".name"), fontRenderer, relX + 88, relY + 5, 0xFFFFFF, true);
        drawStringCenter(minecraft.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getDisplayName(), fontRenderer, relX + 88, relY + 5, 0xFFFFFF, true);
        GuiInventory.drawEntityOnScreen(relX + 80, relY + 90, 40, (float) (relX + 51) - mousePosX, (float) (relY + 75 - 50) - mousePosY, minecraft.player);

        minecraft.getTextureManager().bindTexture(ENERGY_BAR);
        drawModalRectWithCustomSizedTexture(relX + 10, relY + 16, 0, 0, 14, 78, 128, 128);
        int amount = getEnergyBarAmount();
        int barOffset = 78-amount;
        drawModalRectWithCustomSizedTexture(relX + 10, relY + 16 + barOffset, 14, 0, 14, amount, 128, 128);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private int getEnergyBarAmount() {
        ItemStack stack = minecraft.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        Item item = stack.getItem();
        if (item instanceof ItemJetpack) {
            ItemJetpack jetpack = (ItemJetpack) item;
/*            if (jetpack.isCreative) {
                return 78;
            }*/
            int i = jetpack.getEnergyStored(stack);
            int j = jetpack.getMaxEnergyStored(stack);
            return (int) (j != 0 && i != 0 ? (long) i * 78 / j : 0);
        } else if (item instanceof ItemFluxpack) {
            ItemFluxpack fluxpack = (ItemFluxpack) item;
/*            if (fluxpack.isCreative) {
                return 78;
            }*/
            int i = fluxpack.getEnergyStored(stack);
            int j = fluxpack.getMaxEnergyStored(stack);
            return (int) (j != 0 && i != 0 ? (long) i * 78 / j : 0);
        } else {
            return 0;
        }
    }

    @Override
    protected void actionPerformed(@Nonnull GuiButton button) throws IOException {
        ItemStack stack = minecraft.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        Item item = stack.getItem();
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
        if (KeybindHandler.JETPACK_GUI_KEY.getKeyCode() == keyCode) {
            minecraft.displayGuiScreen(null);
        } else {
            super.keyTyped(typedChar, keyCode);
        }
    }
}
