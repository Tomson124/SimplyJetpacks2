package tonius.simplyjetpacks.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPositions;
import tonius.simplyjetpacks.item.Fluxpack;
import tonius.simplyjetpacks.item.Jetpack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public static final List<Section> configSections = new ArrayList<>();
    private static final Section sectionItems = new Section(false, "items");
    private static final Section sectionIntegration = new Section(false, "integration");
    private static final Section sectionControls = new Section(true, "controls");
    private static final Section sectionAesthetics = new Section(true, "aesthetics");
    private static final Section sectionSounds = new Section(true, "sounds");
    private static final Section sectionGui = new Section(true, "gui");
    private static final Section sectionTuning = new Section(false, "tuning");

    public static Configuration configCommon;
    public static Configuration configClient;

    // Item
    public static boolean enableFuelEfficiencyEnchantment = Defaults.enableFuelEfficiencyEnchantment;
    public static boolean addRAItemsIfNotInstalled = Defaults.addRAItemsIfNotInstalled;
    // Integration
    public static boolean enableIntegrationVanilla = Defaults.enableIntegrationVanilla;
    public static boolean enableIntegrationEIO = Defaults.enableIntegrationEIO;
    public static boolean enableIntegrationTE = Defaults.enableIntegrationTE;
    public static boolean enableIntegrationTD = Defaults.enableIntegrationTD;
    public static boolean enableIntegrationRA = Defaults.enableIntegrationRA;
    public static boolean enableIntegrationMek = Defaults.enableIntegrationMek;
    public static boolean enableIntegrationIE = Defaults.enableIntegrationIE;
    public static boolean enableIntegrationRR = Defaults.enableIntegrationRR;
    public static float gelidEnderiumFuelUsageBonus = Defaults.gelidEnderiumFuelUsageBonus;
    // Controls
    public static boolean customControls = Defaults.customControls;
    public static String flyKey = Defaults.flyKey;
    public static String descendKey = Defaults.descendKey;
    public static boolean invertHoverSneakingBehavior = Defaults.invertHoverSneakingBehavior;
    public static boolean doubleTapSprintInAir = Defaults.doubleTapSprintInAir;
    // Aesthetics
    public static boolean enableArmor3DModels = Defaults.enableArmor3DModels;
    // Sounds
    public static boolean jetpackSounds = Defaults.jetpackSounds;
    // GUI
    public static boolean holdShiftForDetails = Defaults.holdShiftForDetails;
    public static HUDPositions HUDPosition = Defaults.HUDPosition;
    public static int HUDOffsetX = Defaults.HUDOffsetX;
    public static int HUDOffsetY = Defaults.HUDOffsetY;
    public static double HUDScale = Defaults.HUDScale;
    public static boolean showHUDWhileChatting = Defaults.showHUDWhileChatting;
    public static boolean enableFuelHUD = Defaults.enableFuelHUD;
    public static boolean minimalFuelHUD = Defaults.minimalFuelHUD;
    public static boolean showExactFuelInHUD = Defaults.showExactFuelInHUD;
    public static boolean enableStateHUD = Defaults.enableStateHUD;
    public static boolean enableStateMessages = Defaults.enableStateMessages;

    public static void preInit(FMLPreInitializationEvent event) {
        configCommon = new Configuration(new File(event.getModConfigurationDirectory(), SimplyJetpacks.MODID + "/common.cfg"), "2.2.16", true);
        configClient = new Configuration(new File(event.getModConfigurationDirectory(), SimplyJetpacks.MODID + "/client.cfg"), "2.2.16", true);
        syncConfig();
        SimplyJetpacks.proxy.updateCustomKeybinds(flyKey, descendKey);
    }

    private static String getKey(Section section) {
        return "config." + SimplyJetpacks.PREFIX + section.key + ".";
    }

    public static void processConfig() {
        enableFuelEfficiencyEnchantment = configCommon.get(sectionItems.key, getKey(sectionItems) + "enableFuelEfficiencyEnchantment", Defaults.enableFuelEfficiencyEnchantment, getKey(sectionItems) + "enableFuelEfficiency.tooltip").getBoolean(Defaults.enableFuelEfficiencyEnchantment);
        addRAItemsIfNotInstalled = configCommon.get(sectionItems.key, getKey(sectionItems) + "addRAItemsIfNotInstalled", Defaults.addRAItemsIfNotInstalled, getKey(sectionItems) + "addRAItemsIfNotInstalled.tooltip").setRequiresMcRestart(true).getBoolean(Defaults.addRAItemsIfNotInstalled);

        enableIntegrationVanilla = configCommon.get(sectionIntegration.key, getKey(sectionIntegration) + "enableIntegrationVanilla", Defaults.enableIntegrationVanilla, getKey(sectionIntegration) + "enableIntegrationVanilla.tooltip").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationVanilla);
        enableIntegrationEIO = configCommon.get(sectionIntegration.key, getKey(sectionIntegration) + "enableIntegrationEIO", Defaults.enableIntegrationEIO, getKey(sectionIntegration) + "enableIntegrationEIO.tooltip").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationEIO);
        enableIntegrationTE = configCommon.get(sectionIntegration.key, getKey(sectionIntegration) + "enableIntegrationTE", Defaults.enableIntegrationTE, getKey(sectionIntegration) + "enableIntegrationTE.tooltip").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationTE);
        enableIntegrationTD = configCommon.get(sectionIntegration.key, getKey(sectionIntegration) + "enableIntegrationTD", Defaults.enableIntegrationTD, getKey(sectionIntegration) + "enableIntegrationTD.tooltip").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationTD);
        enableIntegrationRA = configCommon.get(sectionIntegration.key, getKey(sectionIntegration) + "enableIntegrationRA", Defaults.enableIntegrationRA, getKey(sectionIntegration) + "enableIntegrationRA.tooltip").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationRA);
        enableIntegrationMek = configCommon.get(sectionIntegration.key, getKey(sectionIntegration) + "enableIntegrationMek", Defaults.enableIntegrationMek, getKey(sectionIntegration) + "enableIntegrationMek.tooltip").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationMek);
        enableIntegrationIE = configCommon.get(sectionIntegration.key, getKey(sectionIntegration) + "enableIntegrationIE", Defaults.enableIntegrationIE, getKey(sectionIntegration) + "enableIntegrationIE.tooltip").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationIE);
        enableIntegrationRR = configCommon.get(sectionIntegration.key, getKey(sectionIntegration) + "enableIntegrationRR", Defaults.enableIntegrationRR, getKey(sectionIntegration) + "enableIntegrationRR.tooltip").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationRR);
        gelidEnderiumFuelUsageBonus = configCommon.get(sectionIntegration.key, getKey(sectionIntegration) + "gelidEnderiumFuelUsageBonus", Defaults.gelidEnderiumFuelUsageBonus, getKey(sectionIntegration) + "gelidEnderiumFuelUsageBonus.tooltip").setMinValue(0).setMaxValue(100).setRequiresMcRestart(true).getInt(Defaults.gelidEnderiumFuelUsageBonus);

        customControls = configClient.get(sectionControls.key, getKey(sectionControls) + "customControls", Defaults.customControls, getKey(sectionControls) + "customControls.tooltip").getBoolean(Defaults.customControls);
        flyKey = configClient.get(sectionControls.key, getKey(sectionControls) + "flyKey", Defaults.flyKey, getKey(sectionControls) + "flyKey.tooltip").getString();
        descendKey = configClient.get(sectionControls.key, getKey(sectionControls) + "descendKey", Defaults.descendKey, getKey(sectionControls) + "descendKey.tooltip").getString();
        invertHoverSneakingBehavior = configClient.get(sectionControls.key, getKey(sectionControls) + "invertHoverSneakingBehavior", Defaults.invertHoverSneakingBehavior, getKey(sectionControls) + "invertHoverSneakingBehavior.tooltip").getBoolean(Defaults.invertHoverSneakingBehavior);
        doubleTapSprintInAir = configClient.get(sectionControls.key, getKey(sectionControls) + "doubleTapSprintInAir", Defaults.doubleTapSprintInAir, getKey(sectionControls) + "doubleTapSprintInAir.tooltip").getBoolean(Defaults.doubleTapSprintInAir);

        enableArmor3DModels = configClient.get(sectionAesthetics.key, getKey(sectionAesthetics) + "enableArmor3DModels", Defaults.enableArmor3DModels, getKey(sectionAesthetics) + "enableArmor3DModels.tooltip").getBoolean(Defaults.enableArmor3DModels);

        jetpackSounds = configClient.get(sectionSounds.key, getKey(sectionSounds) + "jetpackSounds", Defaults.jetpackSounds, getKey(sectionSounds) + "jetpackSounds.tooltip").getBoolean(Defaults.jetpackSounds);

        holdShiftForDetails = configClient.get(sectionGui.key, getKey(sectionGui) + "holdShiftForDetails", Defaults.holdShiftForDetails, getKey(sectionGui) + "holdShiftForDetails.tooltip").getBoolean(Defaults.holdShiftForDetails);
        HUDPosition = HUDPositions.valueOf(configClient.get(sectionGui.key, getKey(sectionGui) + "hudPosition", HUDPositions.TOP_LEFT.name(), getKey(sectionGui) + "hudPosition.tooltip", new String[]{"TOP_LEFT", "TOP_CENTER", "TOP_RIGHT", "LEFT", "RIGHT", "BOTTOM_LEFT", "BOTTOM_RIGHT"}).getString());
        HUDOffsetX = configClient.get(sectionGui.key, getKey(sectionGui) + "HUDOffsetX", Defaults.HUDOffsetX, getKey(sectionGui) + "HUDOffsetX.tooltip").getInt(Defaults.HUDOffsetX);
        HUDOffsetY = configClient.get(sectionGui.key, getKey(sectionGui) + "HUDOffsetY", Defaults.HUDOffsetY, getKey(sectionGui) + "HUDOffsetY.tooltip").getInt(Defaults.HUDOffsetY);
        HUDScale = Math.abs(configClient.get(sectionGui.key, getKey(sectionGui) + "HUDScale", Defaults.HUDScale, getKey(sectionGui) + "HUDScale.tooltip").setMinValue(0.001D).getDouble(Defaults.HUDScale));
        showHUDWhileChatting = configClient.get(sectionGui.key, getKey(sectionGui) + "showHUDWhileChatting", Defaults.showHUDWhileChatting, getKey(sectionGui) + "showHUDWhileChatting.tooltip").getBoolean(Defaults.showHUDWhileChatting);
        enableFuelHUD = configClient.get(sectionGui.key, getKey(sectionGui) + "enableFuelHUD", Defaults.enableFuelHUD, getKey(sectionGui) + "enableFuelHUD.tooltip").getBoolean(Defaults.enableFuelHUD);
        minimalFuelHUD = configClient.get(sectionGui.key, getKey(sectionGui) + "minimalFuelHUD", Defaults.minimalFuelHUD, getKey(sectionGui) + "minimalFuelHUD.tooltip").getBoolean(Defaults.minimalFuelHUD);
        showExactFuelInHUD = configClient.get(sectionGui.key, getKey(sectionGui) + "showExactFuelInHUD", Defaults.showExactFuelInHUD, getKey(sectionGui) + "showExactFuelInHUD.tooltip").getBoolean(Defaults.showExactFuelInHUD);
        enableStateHUD = configClient.get(sectionGui.key, getKey(sectionGui) + "enableStateHUD", Defaults.enableStateHUD, getKey(sectionGui) + "enableStateHUD.tooltip").getBoolean(Defaults.enableStateHUD);
        enableStateMessages = configClient.get(sectionGui.key, getKey(sectionGui) + "enableStateMessages", Defaults.enableStateMessages, getKey(sectionGui) + "enableStateMessages.tooltip").getBoolean(Defaults.enableStateMessages);

        Jetpack.loadAllConfigs(configCommon);
        Fluxpack.loadAllConfigs(configCommon);
    }

    private static void syncConfig() {
        SimplyJetpacks.logger.info("Loading Configuration Files...");
        try {
            processConfig();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (configCommon.hasChanged()) {
                configCommon.save();
            }
            if (configClient.hasChanged()) {
                configClient.save();
            }
        }
    }

    public static void onConfigChanged() {
        SimplyJetpacks.logger.info("Simply Jetpacks Config Changed!");
        syncConfig();
        SimplyJetpacks.proxy.updateCustomKeybinds(flyKey, descendKey);
    }
}