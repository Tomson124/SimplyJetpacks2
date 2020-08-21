package tonius.simplyjetpacks.config;

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
    private static final Section sectionTuning = new Section(false, "tuning");
    private static final Section sectionControls = new Section(true, "controls");
    private static final Section sectionAesthetics = new Section(true, "aesthetics");
    private static final Section sectionSounds = new Section(true, "sounds");
    private static final Section sectionGui = new Section(true, "gui");

    public static ConfigWrapper configCommon;
    public static ConfigWrapper configClient;

    public static final String version = SimplyJetpacks.VERSION;
    private static final String configVersion = version.substring(0, version.indexOf(".") + 4);

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
    public static float gelidEnderiumEnergyUsageBonus = Defaults.gelidEnderiumEnergyUsageBonus;
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
    public static int HUDTextColor = Defaults.HUDTextColor;
    public static int HUDOffsetX = Defaults.HUDOffsetX;
    public static int HUDOffsetY = Defaults.HUDOffsetY;
    public static double HUDScale = Defaults.HUDScale;
    public static boolean showHUDWhileChatting = Defaults.showHUDWhileChatting;
    public static boolean enableEnergyHUD = Defaults.enableEnergyHUD;
    public static boolean minimalEnergyHUD = Defaults.minimalEnergyHUD;
    public static boolean showExactEnergyInHUD = Defaults.showExactEnergyInHUD;
    public static boolean enableStateHUD = Defaults.enableStateHUD;
    public static boolean enableStateMessages = Defaults.enableStateMessages;

    public static void preInit(FMLPreInitializationEvent event) {
        configCommon = new ConfigWrapper(new File(event.getModConfigurationDirectory(), SimplyJetpacks.MODID + "/common.cfg"), configVersion, true);
        configClient = new ConfigWrapper(new File(event.getModConfigurationDirectory(), SimplyJetpacks.MODID + "/client.cfg"), configVersion, true);
        syncConfig();
        SimplyJetpacks.proxy.updateCustomKeybinds(flyKey, descendKey);
    }

    public static void processConfig() {
        enableFuelEfficiencyEnchantment = configCommon.getBooleanS(sectionItems.category, "enableFuelEfficiencyEnchantment", null, Defaults.enableFuelEfficiencyEnchantment, true);
        addRAItemsIfNotInstalled = configCommon.getBooleanS(sectionItems.category, "addRAItemsIfNotInstalled", null, Defaults.addRAItemsIfNotInstalled, true);
        
        enableIntegrationVanilla = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationVanilla", null, Defaults.enableIntegrationVanilla, true);
        enableIntegrationEIO = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationEIO", null, Defaults.enableIntegrationEIO, true);
        enableIntegrationTE = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationTE", null, Defaults.enableIntegrationTE, true);
        enableIntegrationTD = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationTD", null, Defaults.enableIntegrationTD, true);
        enableIntegrationRA = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationRA", null, Defaults.enableIntegrationRA, true);
        enableIntegrationMek = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationMek", null, Defaults.enableIntegrationMek, true);
        enableIntegrationIE = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationIE", null, Defaults.enableIntegrationIE, true);
        enableIntegrationRR = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationRR", null, Defaults.enableIntegrationRR, true);
        gelidEnderiumEnergyUsageBonus = configCommon.getIntS(sectionIntegration.category, "gelidEnderiumEnergyUsageBonus", null, Defaults.gelidEnderiumEnergyUsageBonus, 0, 100, true);

        customControls = configClient.getBooleanS(sectionControls.category, "customControls", null, Defaults.customControls, false);
        flyKey = configClient.getStringS(sectionControls.category, "flyKey", null, Defaults.flyKey, false);
        descendKey = configClient.getStringS(sectionControls.category, "descendKey", null, Defaults.descendKey, false);
        customControls = configClient.getBooleanS(sectionControls.category, "customControls", null, Defaults.customControls, false);
        invertHoverSneakingBehavior = configClient.getBooleanS(sectionControls.category, "invertHoverSneakingBehavior", null, Defaults.invertHoverSneakingBehavior, false);
        doubleTapSprintInAir = configClient.getBooleanS(sectionControls.category, "doubleTapSprintInAir", null, Defaults.doubleTapSprintInAir, false);

        enableArmor3DModels = configClient.getBooleanS(sectionAesthetics.category, "enableArmor3DModels", null, Defaults.enableArmor3DModels, false);

        jetpackSounds = configClient.getBooleanS(sectionSounds.category, "jetpackSounds", null, Defaults.jetpackSounds, false);

        holdShiftForDetails = configClient.getBooleanS(sectionGui.category, "holdShiftForDetails", null, Defaults.holdShiftForDetails, false);
        HUDPosition = HUDPositions.valueOf(configClient.getStringListS(sectionGui.category, "hudPosition", null, Defaults.HUDPosition.name(), new String[]{"TOP_LEFT", "TOP_CENTER", "TOP_RIGHT", "LEFT", "RIGHT", "BOTTOM_LEFT", "BOTTOM_RIGHT"}, false));
        HUDOffsetX = configClient.getIntS(sectionGui.category, "HUDOffsetX", null, Defaults.HUDOffsetX, null, null, false);
        HUDOffsetY = configClient.getIntS(sectionGui.category, "HUDOffsetY", null, Defaults.HUDOffsetY, null, null, false);
        HUDScale = configClient.getDoubleS(sectionGui.category, "HUDScale", null, Defaults.HUDScale, 0.001D, null, false);
        showHUDWhileChatting = configClient.getBooleanS(sectionGui.category, "showHUDWhileChatting", null, Defaults.showHUDWhileChatting, false);
        enableEnergyHUD = configClient.getBooleanS(sectionGui.category, "enableEnergyHUD", null, Defaults.enableEnergyHUD, false);
        minimalEnergyHUD = configClient.getBooleanS(sectionGui.category, "minimalEnergyHUD", null, Defaults.minimalEnergyHUD, false);
        showExactEnergyInHUD = configClient.getBooleanS(sectionGui.category, "showExactEnergyInHUD", null, Defaults.showExactEnergyInHUD, false);
        enableStateHUD = configClient.getBooleanS(sectionGui.category, "enableStateHUD", null, Defaults.enableStateHUD, false);
        enableStateMessages = configClient.getBooleanS(sectionGui.category, "enableStateMessages", null, Defaults.enableStateMessages, false);
        HUDTextColor = configClient.getIntS(sectionGui.category, "HUDTextColor", null, Defaults.HUDTextColor, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

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