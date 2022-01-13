package stormedpanda.simplyjetpacks.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.widget.Slider;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.handlers.KeybindHandler;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.network.NetworkHandler;
import stormedpanda.simplyjetpacks.network.packets.*;
import stormedpanda.simplyjetpacks.util.JetpackUtil;
import stormedpanda.simplyjetpacks.util.SJTextUtil;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class JetpackScreen extends Screen {

    private static final Minecraft minecraft = Minecraft.getInstance();

    private final ResourceLocation JETPACK_TEXTURE = new ResourceLocation(SimplyJetpacks.MODID, "textures/gui/jetpack_screen_large.png");
    private static final int WIDTH = 176;
    private static final int HEIGHT = 120;

    private final JetpackItem jetpackItem;
    private final ItemStack jetpackStack;

    private ImageButton engine, hover, ehover, charger;
    private Slider slider;

    public JetpackScreen() {
        super(new TranslationTextComponent("screen.simplyjetpacks.jetpack_screen.title"));
        this.width = WIDTH;
        this.height = HEIGHT;
        this.jetpackItem = (JetpackItem) JetpackUtil.getFromBothSlots(minecraft.player).getItem();
        this.jetpackStack = JetpackUtil.getFromBothSlots(minecraft.player);
    }

    @Override
    protected void init() {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        addButton(this.engine = new ImageButton(relX + 120, relY + 16, 20, 20, 176, 0, 20, JETPACK_TEXTURE, button -> NetworkHandler.sendToServer(new PacketToggleEngine())));
        //addButton(this.hover = new ImageButton(relX + 120, relY + 38, 20, 20, 216, 0, 20, JETPACK_TEXTURE, button -> NetworkHandler.sendToServer(new PacketToggleHover())));

        Item item = jetpackStack.getItem();
        if (item instanceof JetpackItem) {
            JetpackItem jetpack = (JetpackItem) item;
            if (jetpack.getJetpackType().getHoverMode()) {
                addButton(this.hover = new ImageButton(relX + 120, relY + 38, 20, 20, 216, 0, 20, JETPACK_TEXTURE, button -> NetworkHandler.sendToServer(new PacketToggleHover())));
                this.hover.active = true;
            } else {
                addButton(this.hover = new ImageButton(relX + 120, relY + 38, 20, 20, 196, 40, 0, JETPACK_TEXTURE, button -> NetworkHandler.sendToServer(new PacketToggleHover())));
                this.hover.active = false;
            }
            if (jetpack.getJetpackType().getChargerMode()) {
                addButton(this.charger = new ImageButton(relX + 142, relY + 16, 20, 20, 196, 0, 20, JETPACK_TEXTURE, button -> NetworkHandler.sendToServer(new PacketToggleCharger())));
                this.charger.active = true;
            } else {
                addButton(this.charger = new ImageButton(relX + 142, relY + 16, 20, 20, 196, 40, 0, JETPACK_TEXTURE, button -> NetworkHandler.sendToServer(new PacketToggleCharger())));
                this.charger.active = false;
            }
            if (jetpack.getJetpackType().getEmergencyHoverMode()) {
                addButton(this.ehover = new ImageButton(relX + 142, relY + 38, 20, 20, 236, 0, 20, JETPACK_TEXTURE, button -> NetworkHandler.sendToServer(new PacketToggleEHover())));
                this.ehover.active = true;
            } else {
                addButton(this.ehover = new ImageButton(relX + 142, relY + 38, 20, 20, 236, 40, 0, JETPACK_TEXTURE, button -> NetworkHandler.sendToServer(new PacketToggleEHover())));
                this.ehover.active = false;
            }
        }
        addButton(slider = new Slider(relX + 10, relY + 98, 152, 16, new TranslationTextComponent("screen.simplyjetpacks.throttle"), new StringTextComponent("%"),
                0, 101, jetpackItem.getThrottle(jetpackStack), false, true, s -> {}));
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bind(JETPACK_TEXTURE);
        blit(matrixStack, relX, relY, 0, 0, WIDTH, HEIGHT);
        InventoryScreen.renderEntityInInventory(relX + 80, relY + 90, 40, (float)(relX + 51) - mouseX, (float)(relY + 75 - 50) - mouseY, minecraft.player);
        drawCenteredString(matrixStack, minecraft.font, new TranslationTextComponent(jetpackStack.getDescriptionId()), relX + 88, relY + 5, 0xFFFFFF);
        minecraft.getTextureManager().bind(JETPACK_TEXTURE);

        if (slider.getValueInt() != jetpackItem.getThrottle(jetpackStack)) {
            NetworkHandler.sendToServer(new PacketUpdateThrottle(slider.getValueInt()));
        }

        int amount = getEnergyBarAmount(); // Texture height
        int barOffset = 78 - amount;
        int barX = 0;
        boolean useGradient = false;

        switch (jetpackItem.getModId()) {
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

        if (jetpackItem.isCreative()) {
            blit(matrixStack, relX + 10, relY + 16, 70, 178, 14, 78);
        } else {
            blit(matrixStack, relX + 10, relY + 16, barX, 178, 14, 78);
            if (useGradient) {
                // Top left corner -> bottom right corner
                fillGradient(matrixStack, relX + 12, relY + 18 + barOffset, relX + 22, relY + 14 + 78, 0xffb51500, 0xff600b00);
            } else {
                //matrixStack, xPos, yPos, textureXPos, textureYPos, textureWidth, textureHeight
                //blit(matrixStack, relX + 10, relY + 14 + barOffset - 1, barX + 14, 178 + 1, 14, amount - 1);
                blit(matrixStack, relX + 10, relY + 16 + 1 + barOffset, barX + 14, 178 + 1, 14, amount - 2);
            }
        }
        // This does not update like a screen container :(
        if (mouseX >= relX + 10 && mouseY >= relY + 16 && mouseX < relX + 10 + 14 && mouseY < relY + 16 + 78) {
            ITextComponent text;
            //text = SJTextUtil.energyWithMax(jetpackItem.getEnergy(jetpackStack), jetpackItem.getCapacity(jetpackStack));
            if (jetpackItem.isCreative()) {
                text = SJTextUtil.translate("tooltip", "infiniteEnergy", TextFormatting.LIGHT_PURPLE);
            } else if (jetpackItem.getEnergy(jetpackStack) == 0) {
                text = SJTextUtil.translate("hud", "energyDepleted", TextFormatting.RED);
            } else text = null;
            if (text != null) {
                renderTooltip(matrixStack, text, mouseX, mouseY);
            }
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private int getEnergyBarAmount() {
        Item item = jetpackStack.getItem();
        if (item instanceof JetpackItem) {
            JetpackItem jetpack = (JetpackItem) item;
            if (jetpack.isCreative()) {
                return 78;
            }
            int i = jetpack.getEnergy(jetpackStack);
            int j = jetpack.getCapacity(jetpackStack);
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
        if (KeybindHandler.JETPACK_GUI_KEY.matches(keyCode, scanCode) || minecraft.options.keyInventory.matches(keyCode, scanCode)) {
            minecraft.setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}