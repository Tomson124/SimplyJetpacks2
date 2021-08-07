package stormedpanda.simplyjetpacks.util;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.config.ConfigDefaults;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;

public class HUDRenderHelper {

    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final Font font = minecraft.font;

    public static void drawStringAtPosition(Window window, PoseStack matrix, Component text, int lineOffset) {
        int windowScaleHeight = window.getHeight();
        int windowScaleWidth = window.getWidth();

        ConfigDefaults.HUDPosition position = SimplyJetpacksConfig.CLIENT.hudTextPosition.get();
        int color = SimplyJetpacksConfig.CLIENT.hudTextColor.get();
        int xOffset = SimplyJetpacksConfig.CLIENT.hudXOffset.get();
        int yOffset = SimplyJetpacksConfig.CLIENT.hudYOffset.get();
        long hudScale = SimplyJetpacksConfig.CLIENT.hudScale.get();
        boolean hudTextShadow = SimplyJetpacksConfig.CLIENT.hudTextShadow.get();

        int screenHeight = (int) (windowScaleHeight / hudScale);
        int screenWidth = (int) (windowScaleWidth / hudScale);

        switch (position) {
            case TOP_LEFT -> {
                yOffset += lineOffset * 9;
                drawStringLeft(matrix, text, 2 + xOffset, 2 + yOffset, color, hudTextShadow);
            }
            case TOP_CENTER -> {
                yOffset += lineOffset * 9;
                drawStringCenter(matrix, text, screenWidth / 2 + xOffset, 2 + yOffset, color, hudTextShadow);
            }
            case TOP_RIGHT -> {
                yOffset += lineOffset * 9;
                drawStringRight(matrix, text, screenWidth - 2 + xOffset, 2 + yOffset, color, hudTextShadow);
            }
            case LEFT -> {
                yOffset += lineOffset * 9;
                drawStringLeft(matrix, text, 2 + xOffset, screenHeight / 2 + yOffset, color, hudTextShadow);
            }
            case RIGHT -> {
                yOffset += lineOffset * 9;
                drawStringRight(matrix, text, screenWidth - 2 + xOffset, screenHeight / 2 + yOffset, color, hudTextShadow);
            }
            case BOTTOM_LEFT -> {
                yOffset -= lineOffset * 9;
                drawStringLeft(matrix, text, 2 + xOffset, screenHeight - 9 + yOffset, color, hudTextShadow);
            }
            case BOTTOM_RIGHT -> {
                yOffset -= lineOffset * 9;
                drawStringRight(matrix, text, screenWidth - 2 + xOffset, screenHeight - 9 + yOffset, color, hudTextShadow);
            }
            default -> SimplyJetpacks.LOGGER.info("Invalid HUD Position passed to renderer.");
        }
    }

    public static void drawStringLeft(PoseStack matrix, Component text, int x, int y, int color, boolean shadow) {
        if (shadow) {
            font.drawShadow(matrix, text, x, y, color);
        } else {
            font.draw(matrix, text, x, y, color);
        }
    }

    public static void drawStringCenter(PoseStack matrix, Component text, int x, int y, int color, boolean shadow) {
        float textWidth = font.width(text);
        if (shadow) {
            font.drawShadow(matrix, text, x - (textWidth / 2), y, color);
        } else {
            font.draw(matrix, text, x - (textWidth / 2), y, color);
        }
    }

    public static void drawStringRight(PoseStack matrix, Component text, int x, int y, int color, boolean shadow) {
        float textWidth = font.width(text);
        if (shadow) {
            font.drawShadow(matrix, text, x - textWidth, y, color);
        } else {
            font.draw(matrix, text, x - textWidth, y, color);
        }
    }
}
