package stormedpanda.simplyjetpacks.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.config.ConfigDefaults;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;

public class HUDRenderHelper {

    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final FontRenderer fontRenderer = minecraft.font;

    public static void drawStringAtPosition(MainWindow window, MatrixStack matrix, ITextComponent text, int lineOffset) {
        int windowScaleHeight = window.getGuiScaledHeight();
        int windowScaleWidth = window.getGuiScaledHeight();

        ConfigDefaults.HUDPosition position = SimplyJetpacksConfig.hudTextPosition.get();
        int color = SimplyJetpacksConfig.hudTextColor.get();
        int xOffset = SimplyJetpacksConfig.hudXOffset.get();
        int yOffset = SimplyJetpacksConfig.hudYOffset.get();
        long hudScale = SimplyJetpacksConfig.hudScale.get();
        boolean hudTextShadow = SimplyJetpacksConfig.hudTextShadow.get();

        int screenHeight = (int) (windowScaleHeight / hudScale);
        int screenWidth = (int) (windowScaleWidth / hudScale);

        switch(position) {
            case TOP_LEFT:
                yOffset += lineOffset * 9;
                drawStringLeft(matrix, text, 2 + xOffset, 2 + yOffset, color, hudTextShadow);
                break;
            case TOP_CENTER:
                yOffset += lineOffset * 9;
                drawStringCenter(matrix, text, screenWidth / 2 + xOffset, 2 + yOffset, color, hudTextShadow);
                break;
            case TOP_RIGHT:
                yOffset += lineOffset * 9;
                drawStringRight(matrix, text, screenWidth - 2 + xOffset, 2 + yOffset, color, hudTextShadow);
                break;
            case LEFT:
                yOffset += lineOffset * 9;
                drawStringLeft(matrix, text, 2 + xOffset, screenHeight / 2 + yOffset, color, hudTextShadow);
                break;
            case RIGHT:
                yOffset += lineOffset * 9;
                drawStringRight(matrix, text, screenWidth - 2 + xOffset, screenHeight / 2 + yOffset, color, hudTextShadow);
                break;
            case BOTTOM_LEFT:
                yOffset -= lineOffset * 9;
                drawStringLeft(matrix, text, 2 + xOffset, screenHeight - 9 + yOffset, color, hudTextShadow);
                break;
            case BOTTOM_RIGHT:
                yOffset -= lineOffset * 9;
                drawStringRight(matrix, text, screenWidth - 2 + xOffset, screenHeight - 9 + yOffset, color, hudTextShadow);
                break;
            default:
                SimplyJetpacks.LOGGER.info("Invalid HUD Position passed to renderer.");
        }
    }
    public static void drawStringLeft(MatrixStack matrix, ITextComponent text, int x, int y, int color, boolean shadow) {
        if (shadow) {
            fontRenderer.drawShadow(matrix, text, x, y, color);
        } else {
            fontRenderer.draw(matrix, text, x, y, color);
        }
    }
    public static void drawStringCenter(MatrixStack matrix, ITextComponent text, int x, int y, int color, boolean shadow) {
        float textWidth = fontRenderer.width(text);
        if (shadow) {
            fontRenderer.drawShadow(matrix, text, x - (textWidth / 2), y, color);
        } else {
            fontRenderer.draw(matrix, text, x - (textWidth / 2), y, color);
        }
    }
    public static void drawStringRight(MatrixStack matrix, ITextComponent text, int x, int y, int color, boolean shadow) {
        float textWidth = fontRenderer.width(text);
        if (shadow) {
            fontRenderer.drawShadow(matrix, text, x - textWidth, y, color);
        } else {
            fontRenderer.draw(matrix, text, x - textWidth, y, color);
        }
    }
}
