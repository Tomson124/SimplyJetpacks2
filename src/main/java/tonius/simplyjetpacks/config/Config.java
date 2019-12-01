package tonius.simplyjetpacks.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.util.RenderUtils.HUDPositions;
import tonius.simplyjetpacks.item.Packs;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Config {
	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

	public static ForgeConfigSpec COMMON_CONFIG;
	public static ForgeConfigSpec CLIENT_CONFIG;

	public static final List<Section> configSections = new ArrayList<Section>();
	private static final String CATEGORY_JETPACK = "jetpack";
	private static final String SUBCATEGORY_CREATIVE_JETPACK = "creativeJetpack";

	public static ForgeConfigSpec.IntValue CREATIVE_JETPACK_MAX_ENERGY;

	private static final Section sectionIntegration = new Section(false, "Integration Settings", "integration");
	private static final Section sectionControls = new Section(true, "Controls Settings", "controls");
	private static final Section sectionAesthetics = new Section(true, "Aesthetics Settings", "aesthetics");
	private static final Section sectionSounds = new Section(true, "Sound Settings", "sounds");
	private static final Section sectionGui = new Section(true, "GUI Settings", "gui");

	// item
	public static int enchantFuelEfficiencyID = Defaults.enchantFuelEfficiencyID;
	public static boolean flammableFluidsExplode = Defaults.flammableFluidsExplode;
	public static boolean addRAItemsIfNotInstalled = Defaults.addRAItemsIfNotInstalled;

	/*integration
	public static boolean enableIntegrationEIO = Defaults.enableIntegrationEIO;
	public static boolean enableIntegrationTE = Defaults.enableIntegrationTE;
	public static boolean enableIntegrationTD = Defaults.enableIntegrationTD;
	public static boolean enableIntegrationRA = Defaults.enableIntegrationRA;
	public static boolean enableIntegrationRR = Defaults.enableIntegrationRR;
	public static int gelidEnderiumFuelUsageBonus = Defaults.gelidEnderiumFuelUsageBonus;
	*/

	// controls
	public static boolean customControls = Defaults.customControls;
	public static String flyKey = Defaults.flyKey;
	public static String descendKey = Defaults.descendKey;
	public static boolean invertHoverSneakingBehavior = Defaults.invertHoverSneakingBehavior;
	public static boolean doubleTapSprintInAir = Defaults.doubleTapSprintInAir;

	// aesthetics
	public static boolean enableArmor3DModels = Defaults.enableArmor3DModels;

	// sounds
	public static boolean jetpackSounds = Defaults.jetpackSounds;

	// gui
	public static boolean holdShiftForDetails = Defaults.holdShiftForDetails;
	public static int HUDPosition = Defaults.HUDPosition;
	public static int HUDOffsetX = Defaults.HUDOffsetX;
	public static int HUDOffsetY = Defaults.HUDOffsetY;
	public static double HUDScale = Defaults.HUDScale;
	public static boolean showHUDWhileChatting = Defaults.showHUDWhileChatting;
	public static boolean enableFuelHUD = Defaults.enableFuelHUD;
	public static boolean minimalFuelHUD = Defaults.minimalFuelHUD;
	public static boolean showExactFuelInHUD = Defaults.showExactFuelInHUD;
	public static boolean enableStateHUD = Defaults.enableStateHUD;
	public static boolean enableStateMessages = Defaults.enableStateMessages;

	static {

		COMMON_BUILDER.comment("Item settings").push(CATEGORY_JETPACK);
		COMMON_BUILDER.pop();

		setupJetpackConfig();
		COMMON_BUILDER.pop();

		COMMON_CONFIG = COMMON_BUILDER.build();
		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}

	private static void setupJetpackConfig() {
		COMMON_BUILDER.comment("Creative Jetpack settings").push(SUBCATEGORY_CREATIVE_JETPACK);

		CREATIVE_JETPACK_MAX_ENERGY = COMMON_BUILDER.comment("The maximum amount of energy that this pack can hold.")
				.defineInRange("maxPower", 200000, 0, Integer.MAX_VALUE);

		COMMON_BUILDER.pop();
	}

	public static void loadConfig(ForgeConfigSpec spec, Path path) {

		final CommentedFileConfig configData = CommentedFileConfig.builder(path)
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();

		configData.load();
		spec.setConfig(configData);
	}




	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {

	}

	@SubscribeEvent
	public static void onReload(final ModConfig.ConfigReloading configEvent) {
	}

	/*public static void preInit(FMLPreInitializationEvent evt) {
		config = new Configuration(new File(evt.getModConfigurationDirectory(), SimplyJetpacks.MODID + ".cfg"));
		configClient = new Configuration(new File(evt.getModConfigurationDirectory(), SimplyJetpacks.MODID + "-client.cfg"));
		config.load();
		configClient.load();
		processConfig();
		SimplyJetpacks.proxy.updateCustomKeybinds(flyKey, descendKey);
	}

	private static void processConfig() {
		enchantFuelEfficiencyID = config.get(sectionItem.name, "Fuel Efficiency enchant ID", Defaults.enchantFuelEfficiencyID, "The ID of the Fuel Efficiency enchantment. Set to 0 to disable.").setMinValue(0).setMaxValue(255).setRequiresMcRestart(true).getInt();
		flammableFluidsExplode = config.get(sectionItem.name, "Jetpacks explode in flammable fluid blocks", Defaults.flammableFluidsExplode, "When enabled, jetpacks will explode and kill their users when they are being used to fly through flammable fluid blocks.").getBoolean(Defaults.flammableFluidsExplode);
		addRAItemsIfNotInstalled = config.get(sectionItem.name, "Add Redstone Arsenal items if not installed", Defaults.addRAItemsIfNotInstalled, "When enabled, Simply Jetpacks will register some crafting components from Redstone Arsenal to make the Flux-Infused JetPlate craftable if Redstone Arsenal is not installed.").setRequiresMcRestart(true).getBoolean(Defaults.addRAItemsIfNotInstalled);

		enableIntegrationEIO = config.get(sectionIntegration.name, "Ender IO integration", Defaults.enableIntegrationEIO, "When enabled, Simply Jetpacks will register its Ender IO-based jetpacks and flux packs.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationEIO);
		enableIntegrationTE = config.get(sectionIntegration.name, "ThermalExpansion integration", Defaults.enableIntegrationTE, "When enabled, Simply Jetpacks will register its Thermal Expansion-based jetpacks and flux packs.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationTE);
		enableIntegrationTD = config.get(sectionIntegration.name, "ThermalDynamics integration", Defaults.enableIntegrationTD, "When enabled, Simply Jetpacks will register ThermalDynamic items for thruster recipes.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationTD);
		enableIntegrationRA = config.get(sectionIntegration.name, "RedstoneArsenal integration", Defaults.enableIntegrationRA, "When enabled, Simply Jetpacks will register its RedstoneArsenal tier5 jetpack recipes.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationRA);
		enableIntegrationRR = config.get(sectionIntegration.name, "Redstone Repository integration", Defaults.enableIntegrationRA, "When enabled, Simply Jetpacks will register its RedstoneRepository tier5 jetplate recipes.").setRequiresMcRestart(true).getBoolean(Defaults.enableIntegrationRR);
		gelidEnderiumFuelUsageBonus = config.get(sectionIntegration.name, "RedstoneRepository Fuel Efficiency Bonus", Defaults.gelidEnderiumFuelUsageBonus, "When set to a value between 0-100, changes the fuel efficiency bonus of the Enderium Armored Jetplate (Ex: 80 uses fuel at 80% rate").setMinValue(0).setMaxValue(100).setRequiresMcRestart(true).getInt(Defaults.gelidEnderiumFuelUsageBonus);

		customControls = configClient.get(sectionControls.name, "Custom controls", Defaults.customControls, "When enabled, the key codes specified here will be used for the fly and descend keys. Otherwise, the vanilla jump and sneak keys will be used.").getBoolean(Defaults.customControls);
		flyKey = configClient.get(sectionControls.name, "Custom Fly key", Defaults.flyKey, "The name of the Fly key when custom controls are enabled.").getString();
		descendKey = configClient.get(sectionControls.name, "Custom Descend key", Defaults.descendKey, "The name of the Descend key when custom controls are enabled.").getString();
		invertHoverSneakingBehavior = configClient.get(sectionControls.name, "Invert Hover Mode sneaking behavior", Defaults.invertHoverSneakingBehavior, "Invert Hover Mode sneaking behavior").getBoolean(Defaults.invertHoverSneakingBehavior);
		doubleTapSprintInAir = configClient.get(sectionControls.name, "Allow double-tap sprinting while flying", Defaults.doubleTapSprintInAir, "When enabled, sprinting by double-tapping the forward key will work while flying with a jetpack. Can be used as an easier way to activate a jetpack's boost while airborne (the sprint key also works).").getBoolean(Defaults.doubleTapSprintInAir);

		enableArmor3DModels = configClient.get(sectionAesthetics.name, "Enable Armor 3D Models", Defaults.enableArmor3DModels, "When enabled, worn jetpacks and flux packs will have a 3D armor model. Otherwise, flat textures will be used.").getBoolean(Defaults.enableArmor3DModels);

		jetpackSounds = configClient.get(sectionSounds.name, "Packs Sounds", Defaults.jetpackSounds, "When enabled, jetpacks will make sounds when used.").getBoolean(Defaults.jetpackSounds);

		holdShiftForDetails = configClient.get(sectionGui.name, "Hold Shift for Details", Defaults.holdShiftForDetails, "When enabled, item details are only shown in the tooltip when holding Shift.").getBoolean(Defaults.holdShiftForDetails);
		HUDPosition = configClient.get(sectionGui.name, "HUD Base Position", Defaults.HUDPosition, "The base position of the HUD on the screen. 0 = top left, 1 = top center, 2 = top right, 3 = left, 4 = right, 5 = bottom left, 6 = bottom right").setMinValue(0).setMaxValue(HUDPositions.values().length - 1).getInt(Defaults.HUDPosition);
		HUDOffsetX = configClient.get(sectionGui.name, "HUD Offset - X", Defaults.HUDOffsetX, "The HUD display will be shifted horizontally by this value. This value may be negative.").getInt(Defaults.HUDOffsetX);
		HUDOffsetY = configClient.get(sectionGui.name, "HUD Offset - Y", Defaults.HUDOffsetY, "The HUD display will be shifted vertically by this value. This value may be negative.").getInt(Defaults.HUDOffsetY);
		HUDScale = Math.abs(configClient.get(sectionGui.name, "HUD Scale", Defaults.HUDScale, "How large the HUD will be rendered. Default is 1.0, can be bigger or smaller").setMinValue(0.001D).getDouble(Defaults.HUDScale));
		showHUDWhileChatting = configClient.get(sectionGui.name, "Show HUD while chatting", Defaults.showHUDWhileChatting, "When enabled, the HUD will display even when the chat window is opened.").getBoolean(Defaults.showHUDWhileChatting);
		enableFuelHUD = configClient.get(sectionGui.name, "Enable Fuel HUD", Defaults.enableFuelHUD, "When enabled, a HUD that displays the fuel level of the currently worn jetpack or flux pack will show.").getBoolean(Defaults.enableFuelHUD);
		minimalFuelHUD = configClient.get(sectionGui.name, "Minimal Fuel HUD", Defaults.minimalFuelHUD, "When enabled, only the fuel amounts themselves will be rendered on the fuel HUD.").getBoolean(Defaults.minimalFuelHUD);
		showExactFuelInHUD = configClient.get(sectionGui.name, "Exact fuel amounts in HUD", Defaults.showExactFuelInHUD, "When enabled, the fuel HUD will display the exact amount of RF or mB other than just a percentage.").getBoolean(Defaults.showExactFuelInHUD);
		enableStateHUD = configClient.get(sectionGui.name, "Enable State HUD", Defaults.enableStateHUD, "When enabled, a HUD that displays the states (engine/mode/etc.) of the currently worn jetpack or flux pack will show.").getBoolean(Defaults.enableStateHUD);
		enableStateMessages = configClient.get(sectionGui.name, "Enable State Messages", Defaults.enableStateMessages, "When enabled, switching jetpacks or flux packs on or off, or change their modes will display a status message above the inventory bar.").getBoolean(Defaults.enableStateMessages);

		//PackBase.loadAllConfigs(config);
		Packs.loadAllConfigs(config);
	}*/
}