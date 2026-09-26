package tomson124.simplyjetpacks.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.gui.widget.ExtendedSlider;
import net.neoforged.neoforge.network.PacketDistributor;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.handlers.KeybindForgeBusHandler;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.network.payloads.*;
import tomson124.simplyjetpacks.util.JetpackUtil;
import tomson124.simplyjetpacks.util.SJTextUtil;

@OnlyIn(Dist.CLIENT)
public class JetpackScreen extends Screen {

    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final float LEAN_FACTOR = 2.0f;

    private final ResourceLocation JETPACK_TEXTURE = ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "textures/gui/jetpack_screen.png");
    private static final int WIDTH = 176;
    private static final int HEIGHT = 120;

    private final JetpackItem jetpackItem;
    private final ItemStack jetpackStack;

    private ImageButton engine, hover, ehover, charger;
    private ExtendedSlider slider;
    /** Last throttle sent to the server, so we only send on change rather than every frame. */
    private int lastSentThrottle;

    public JetpackScreen() {
        super(Component.translatable("screen.simplyjetpacks.jetpack_screen.title"));
        this.width = WIDTH;
        this.height = HEIGHT;
        this.jetpackStack = JetpackUtil.getFromBothSlots(minecraft.player);
        this.jetpackItem = jetpackStack.getItem() instanceof JetpackItem item ? item : null;
    }

    @Override
    protected void init() {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        addRenderableWidget(this.engine = new ImageButton(relX + 120, relY + 16, 20, 20, new WidgetSprites(JETPACK_TEXTURE, JETPACK_TEXTURE), button -> PacketDistributor.sendToServer(new PayloadToggleEngine())));

        if (jetpackItem != null) {
            var jetpack = JetpackUtil.getJetpack(jetpackStack);

            addRenderableWidget(this.hover = new ImageButton(relX + 120, relY + 38, 20, 20, new WidgetSprites(JETPACK_TEXTURE, JETPACK_TEXTURE), button -> PacketDistributor.sendToServer(new PayloadToggleHover())));
            this.hover.active = jetpack.hoverMode;

            addRenderableWidget(this.charger = new ImageButton(relX + 142, relY + 16, 20, 20, new WidgetSprites(JETPACK_TEXTURE, JETPACK_TEXTURE), button -> PacketDistributor.sendToServer(new PayloadToggleCharger())));
            this.charger.active = jetpack.chargerMode;

            addRenderableWidget(this.ehover = new ImageButton(relX + 142, relY + 38, 20, 20, new WidgetSprites(JETPACK_TEXTURE, JETPACK_TEXTURE), button -> PacketDistributor.sendToServer(new PayloadToggleEHover())));
            this.ehover.active = jetpack.emergencyHoverMode;
        }

        // Throttle is stored as a 0..1 multiplier but presented as a percentage.
        this.lastSentThrottle = (int) Math.round(JetpackUtil.getThrottle(jetpackStack) * 100D);
        addRenderableWidget(slider = new ExtendedSlider(relX + 10, relY + 98, 152, 16,
                Component.translatable("screen.simplyjetpacks.throttle"), Component.literal("%"),
                0, 100, lastSentThrottle, true));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        RenderSystem.setShaderTexture(0, JETPACK_TEXTURE);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        graphics.blit(JETPACK_TEXTURE, relX, relY, 0, 0, WIDTH, HEIGHT);

        assert minecraft.screen != null;
        float halfScreenHeight = minecraft.screen.height / 2f;
        float halfScreenWidth = minecraft.screen.width / 2f;
        float angleX = -((mouseX - halfScreenWidth) / halfScreenWidth * LEAN_FACTOR);
        float angleY = -(((mouseY + 25) - halfScreenHeight) / halfScreenHeight * LEAN_FACTOR);

        // 1.21 takes a scissor box (x1,y1,x2,y2) plus scale rather than a centre point.
        InventoryScreen.renderEntityInInventoryFollowsAngle(graphics,
                relX + 55, relY + 25, relX + 105, relY + 95,
                40, 0.0625F, angleX, angleY, minecraft.player);
        graphics.drawCenteredString(minecraft.font, jetpackStack.getHoverName(), relX + 88, relY + 5, 0xFFFFFF);
        RenderSystem.setShaderTexture(0, JETPACK_TEXTURE);

        if (slider.getValueInt() != lastSentThrottle) {
            lastSentThrottle = slider.getValueInt();
            PacketDistributor.sendToServer(new PayloadUpdateThrottle(lastSentThrottle));
        }

        int amount = getEnergyBarAmount(); // Texture height
        int barOffset = 78 - amount;
        int barX = 0;
        boolean useGradient = false;

        switch (JetpackUtil.getJetpack(jetpackStack).getModId()) {
            case ("mek"):
                barX = 28;
                break;
            case ("ie"):
                barX = 56;
                useGradient = true;
                break;
            case ("sj"):
                break;
        }

        if (jetpackItem != null && jetpackItem.isCreative(jetpackStack)) {
            graphics.blit(JETPACK_TEXTURE, relX + 10, relY + 16, 70, 178, 14, 78);
        } else {
            graphics.blit(JETPACK_TEXTURE, relX + 10, relY + 16, barX, 178, 14, 78);
            if (useGradient) {
                // Top left corner -> bottom right corner
                graphics.fillGradient(relX + 12, relY + 18 + barOffset, relX + 22, relY + 14 + 78, 0xffb51500, 0xff600b00);
            } else {
                //matrixStack, xPos, yPos, textureXPos, textureYPos, textureWidth, textureHeight
                //blit(matrixStack, relX + 10, relY + 14 + barOffset - 1, barX + 14, 178 + 1, 14, amount - 1);
                graphics.blit(JETPACK_TEXTURE, relX + 10, relY + 16 + 1 + barOffset, barX + 14, 178 + 1, 14, amount - 2);
            }
        }

        if (mouseX >= relX + 10 && mouseY >= relY + 16 && mouseX < relX + 10 + 14 && mouseY < relY + 16 + 78) {
            Component text;
            if (jetpackItem != null && jetpackItem.isCreative(jetpackStack)) {
                text = SJTextUtil.translate("tooltip", "infiniteEnergy", ChatFormatting.LIGHT_PURPLE);
            } else if (JetpackUtil.getEnergyStorage(jetpackStack).getEnergyStored() == 0) {
                text = SJTextUtil.translate("hud", "energyDepleted", ChatFormatting.RED);
            } else text = null;
            if (text != null) {
                graphics.renderTooltip(font, text, mouseX, mouseY);
            }
        }
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    private int getEnergyBarAmount() {
        Item item = jetpackStack.getItem();
        if (item instanceof JetpackItem jetpack) {
            if (jetpack.isCreative(jetpackStack)) {
                return 78;
            }
            int i = JetpackUtil.getEnergyStorage(jetpackStack).getEnergyStored();
            int j = JetpackUtil.getEnergyStorage(jetpackStack).getMaxEnergyStored();
            return (int) (j != 0 && i != 0 ? (long) i * 78 / j : 0);
        }
        return 0;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (KeybindForgeBusHandler.JETPACK_GUI_KEY.matches(keyCode, scanCode) || minecraft.options.keyInventory.matches(keyCode, scanCode)) {
            minecraft.setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}