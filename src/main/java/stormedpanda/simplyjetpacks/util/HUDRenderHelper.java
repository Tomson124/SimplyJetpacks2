package stormedpanda.simplyjetpacks.util;

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
    private static final FontRenderer fontRenderer = minecraft.fontRenderer;

    public static void drawStringAtPosition(MainWindow window, MatrixStack matrix, ITextComponent text, int lineOffset) {
        int windowScaleHeight = window.getScaledHeight();
        int windowScaleWidth = window.getScaledWidth();

        ConfigDefaults.HUDPosition position = SimplyJetpacksConfig.CLIENT.hudTextPosition.get();
        int color = SimplyJetpacksConfig.CLIENT.hudTextColor.get();
        int xOffset = SimplyJetpacksConfig.CLIENT.hudXOffset.get();
        int yOffset = SimplyJetpacksConfig.CLIENT.hudYOffset.get();
        long hudScale = SimplyJetpacksConfig.CLIENT.hudScale.get();
        boolean hudTextShadow = SimplyJetpacksConfig.CLIENT.hudTextShadow.get();

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
        fontRenderer.func_238407_a_(matrix, text, x, y, color);
    }
    public static void drawStringCenter(MatrixStack matrix, ITextComponent text, int x, int y, int color, boolean shadow) {
        float textWidth = fontRenderer.func_238414_a_(text);
        fontRenderer.func_238407_a_(matrix, text, x - (textWidth / 2), y, color);
    }
    public static void drawStringRight(MatrixStack matrix, ITextComponent text, int x, int y, int color, boolean shadow) {
        float textWidth = fontRenderer.func_238414_a_(text);
        fontRenderer.func_238407_a_(matrix, text, x - textWidth, y, color);
    }
}
