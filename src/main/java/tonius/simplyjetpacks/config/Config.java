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
    private static final Section sectionMisc = new Section(true, "misc");

    public static ConfigWrapper configCommon;
    public static ConfigWrapper configClient;

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
    // Misc
    public static boolean joinAdvancements = Defaults.joinAdvancements;

    public static void preInit(FMLPreInitializationEvent event) {
        configCommon = new ConfigWrapper(new File(event.getModConfigurationDirectory(), SimplyJetpacks.MODID + "/common.cfg"), SimplyJetpacks.VERSION, true);
        configClient = new ConfigWrapper(new File(event.getModConfigurationDirectory(), SimplyJetpacks.MODID + "/client.cfg"), SimplyJetpacks.VERSION, true);
        syncConfig();
        SimplyJetpacks.proxy.updateCustomKeybinds(flyKey, descendKey);
    }

    public static void processConfig() {
        enableFuelEfficiencyEnchantment = configCommon.getBooleanS(sectionItems.category, "enableFuelEfficiencyEnchantment", null, Defaults.enableFuelEfficiencyEnchantment, true, "Enable the Fuel Efficiency enchantment.");
        addRAItemsIfNotInstalled = configCommon.getBooleanS(sectionItems.category, "addRAItemsIfNotInstalled", null, Defaults.addRAItemsIfNotInstalled, true, "hen enabled, Simply Jetpacks will register some crafting components from Redstone Arsenal to make the Flux-Infused JetPlate craftable if Redstone Arsenal is not installed.");

        enableIntegrationVanilla = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationVanilla", null, Defaults.enableIntegrationVanilla, true, "When enabled, Simply Jetpacks will register its Vanilla Jetpacks.");
        enableIntegrationEIO = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationEIO", null, Defaults.enableIntegrationEIO, true, "When enabled, Simply Jetpacks will register its EnderIO Jetpacks and Fluxpacks.");
        enableIntegrationTE = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationTE", null, Defaults.enableIntegrationTE, true, "When enabled, Simply Jetpacks will register its Thermal Expansion Jetpacks and Fluxpacks.");
        enableIntegrationTD = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationTD", null, Defaults.enableIntegrationTD, true, "When enabled, Simply Jetpacks will register Thermal Dynamics items for thruster recipes.");
        enableIntegrationRA = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationRA", null, Defaults.enableIntegrationRA, true, "When enabled, Simply Jetpacks will register its Redstone Arsenal Tier5 Jetpack recipes.");
        enableIntegrationMek = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationMek", null, Defaults.enableIntegrationMek, true, "When enabled, Simply Jetpacks will register its Mekanism Jetpacks.");
        enableIntegrationIE = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationIE", null, Defaults.enableIntegrationIE, true, "When enabled, Simply Jetpacks will register its Immersive Engineering Jetpacks.");
        enableIntegrationRR = configCommon.getBooleanS(sectionIntegration.category, "enableIntegrationRR", null, Defaults.enableIntegrationRR, true, "When enabled, Simply Jetpacks will register its Redstone Repository Tier5 JetPlate recipes.");
        gelidEnderiumEnergyUsageBonus = configCommon.getIntS(sectionIntegration.category, "gelidEnderiumEnergyUsageBonus", null, Defaults.gelidEnderiumEnergyUsageBonus, 0, 100, true, "When set to a value between 0-100, changes the energy efficiency bonus of the Enderium Armored Jetplate (Example: 80 uses energy at 80% rate).");
        joinAdvancements = configCommon.getBooleanS(sectionMisc.category, "joinAdvancements", null, Defaults.joinAdvancements, false, "When enabled, you will get several advancements when joining a world for the first time.");

        customControls = configClient.getBooleanS(sectionControls.category, "customControls", null, Defaults.customControls, false, "When enabled, the key codes specified here will be used for the fly and descend keys. Otherwise, the vanilla jump and sneak keys will be used.");
        flyKey = configClient.getStringS(sectionControls.category, "flyKey", null, Defaults.flyKey, false, "The name of the Fly key when Custom Controls are enabled.");
        descendKey = configClient.getStringS(sectionControls.category, "descendKey", null, Defaults.descendKey, false, "The name of the Descend key when Custom Controls are enabled.");
        invertHoverSneakingBehavior = configClient.getBooleanS(sectionControls.category, "invertHoverSneakingBehavior", null, Defaults.invertHoverSneakingBehavior, false, "Invert Hover Mode sneaking behavior.");
        doubleTapSprintInAir = configClient.getBooleanS(sectionControls.category, "doubleTapSprintInAir", null, Defaults.doubleTapSprintInAir, false, "When enabled, sprinting by double-tapping the forward key will work while flying with a Jetpack. Can be used as an easier way to activate a Jetpack's boost while airborne (the sprint key also works).");

        enableArmor3DModels = configClient.getBooleanS(sectionAesthetics.category, "enableArmor3DModels", null, Defaults.enableArmor3DModels, false, "When enabled, worn Jetpacks and Fluxpacks will have a 3D armor model. Otherwise, flat textures will be used.");

        jetpackSounds = configClient.getBooleanS(sectionSounds.category, "jetpackSounds", null, Defaults.jetpackSounds, false, "When enabled, jetpacks will make sounds when used.");

        holdShiftForDetails = configClient.getBooleanS(sectionGui.category, "holdShiftForDetails", null, Defaults.holdShiftForDetails, false, "When enabled, item details are only shown in the tooltip when holding Shift.");
        HUDPosition = HUDPositions.valueOf(configClient.getStringListS(sectionGui.category, "hudPosition", null, Defaults.HUDPosition.name(), new String[]{"TOP_LEFT", "TOP_CENTER", "TOP_RIGHT", "LEFT", "RIGHT", "BOTTOM_LEFT", "BOTTOM_RIGHT"}, false, "Change the position of the HUD."));
        HUDOffsetX = configClient.getIntS(sectionGui.category, "HUDOffsetX", null, Defaults.HUDOffsetX, null, null, false, "The HUD display will be shifted horizontally by this value. This value may be negative.");
        HUDOffsetY = configClient.getIntS(sectionGui.category, "HUDOffsetY", null, Defaults.HUDOffsetY, null, null, false, "The HUD display will be shifted vertically by this value. This value may be negative.");
        HUDScale = configClient.getDoubleS(sectionGui.category, "HUDScale", null, Defaults.HUDScale, 0.001D, null, false, "How large the HUD will be rendered.");
        showHUDWhileChatting = configClient.getBooleanS(sectionGui.category, "showHUDWhileChatting", null, Defaults.showHUDWhileChatting, false, "When enabled, the HUD will display even when the chat window is opened.");
        enableEnergyHUD = configClient.getBooleanS(sectionGui.category, "enableEnergyHUD", null, Defaults.enableEnergyHUD, false, "When enabled, a HUD that displays the energy level of the currently worn Jetpack or Fluxpack will show.");
        minimalEnergyHUD = configClient.getBooleanS(sectionGui.category, "minimalEnergyHUD", null, Defaults.minimalEnergyHUD, false, "When enabled, only the energy amounts themselves will be rendered on the energy HUD.");
        showExactEnergyInHUD = configClient.getBooleanS(sectionGui.category, "showExactEnergyInHUD", null, Defaults.showExactEnergyInHUD, false, "When enabled, the energy HUD will display the exact amount of RF rather than just a percentage.");
        enableStateHUD = configClient.getBooleanS(sectionGui.category, "enableStateHUD", null, Defaults.enableStateHUD, false, "When enabled, the HUD that displays the states (engine/hover/charger) of the currently worn Jetpack or Fluxpack will show.");
        enableStateMessages = configClient.getBooleanS(sectionGui.category, "enableStateMessages", null, Defaults.enableStateMessages, false, "When enabled, switching Jetpacks or Fluxpacks on or off, or changing their modes will display a status message above the inventory bar.");
        HUDTextColor = configClient.getIntS(sectionGui.category, "HUDTextColor", null, Defaults.HUDTextColor, Integer.MIN_VALUE, Integer.MAX_VALUE, false, "Change the text color in the HUD. (Note: Color is in Integer form)");

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
