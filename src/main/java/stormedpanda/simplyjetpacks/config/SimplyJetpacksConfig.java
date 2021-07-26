package stormedpanda.simplyjetpacks.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.item.JetpackType;

@Mod.EventBusSubscriber(modid = SimplyJetpacks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SimplyJetpacksConfig {

    public static final Builder CLIENT_BUILDER = new Builder();
    public static final Builder COMMON_BUILDER = new Builder();
    public static final Builder SERVER_BUILDER = new Builder();

    public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec SERVER_CONFIG;

    public static void register() {
        setupClientConfig();
        setupCommonConfig();
        setupServerConfig();

        CLIENT_CONFIG = CLIENT_BUILDER.build();
        COMMON_CONFIG = COMMON_BUILDER.build();
        SERVER_CONFIG = SERVER_BUILDER.build();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
    }

    private static void setupClientConfig() {
        CLIENT_BUILDER.comment("Simply Jetpacks 2 - Client Configurations").push("simplyjetpacks-client");

        CLIENT_BUILDER.comment("Controls Configurations").push("controls");

        invertHoverSneakingBehavior = CLIENT_BUILDER
                .comment("This sets whether you must hold sneak to hover.")
                .translation("config.simplyjetpacks.invertHoverSneakingBehavior")
                .define("invertHoverSneakingBehavior", ConfigDefaults.invertHoverSneakingBehavior);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("Audio Configurations").push("audio");

        enableJetpackSounds = CLIENT_BUILDER
                .comment("This sets whether jetpack sounds will play.")
                .translation("config.simplyjetpacks.enableJetpackSounds")
                .define("enableJetpackSounds", ConfigDefaults.enableJetpackSounds);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("Audio Configurations").push("visual");

        enableJetpackParticles = CLIENT_BUILDER
                .comment("This sets whether jetpack particles will be displayed.")
                .translation("config.simplyjetpacks.enableJetpackParticles")
                .define("enableJetpackParticles", ConfigDefaults.enableJetpackParticles);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("GUI Configurations").push("gui");

        showExactEnergy = CLIENT_BUILDER
                .comment("Show exact Energy of Jetpack in HUD")
                .translation("config.simplyjetpacks.showExactEnergy")
                .define("showExactEnergy", ConfigDefaults.showExactEnergy);

        enableStateMessages = CLIENT_BUILDER
                .comment("This sets whether or not jetpack state messages will show.")
                .translation("config.simplyjetpacks.enableStateMessages")
                .define("enableStateMessages", ConfigDefaults.enableStateMessages);

        enableJetpackHud = CLIENT_BUILDER
                .comment("This sets whether or not the jetpack HUD will be visible.")
                .translation("config.simplyjetpacks.enableJetpackHud")
                .define("enableJetpackHud", ConfigDefaults.enableJetpackHud);

        hudTextColor = CLIENT_BUILDER
                .comment("This sets the color of the Jetpack HUD.")
                .translation("config.simplyjetpacks.hudTextColor")
                .defineInRange("hudTextColor", ConfigDefaults.hudTextColor, Integer.MIN_VALUE, Integer.MAX_VALUE);

        hudTextPosition = CLIENT_BUILDER
                .comment("HUD Position")
                .translation("config.simplyjetpacks.hudTextPosition")
                .defineEnum("hudTextPosition", ConfigDefaults.hudTextPosition);

        hudXOffset = CLIENT_BUILDER
                .comment("HUD Position X Offset")
                .translation("config.simplyjetpacks.hudXOffset")
                .defineInRange("hudXOffset", ConfigDefaults.hudXOffset, Integer.MIN_VALUE, Integer.MAX_VALUE);

        hudYOffset = CLIENT_BUILDER
                .comment("HUD Position Y Offset")
                .translation("config.simplyjetpacks.hudYOffset")
                .defineInRange("hudYOffset", ConfigDefaults.hudYOffset, Integer.MIN_VALUE, Integer.MAX_VALUE);

        hudScale = CLIENT_BUILDER
                .comment("HUD Scale")
                .translation("config.simplyjetpacks.hudScale")
                .defineInRange("hudScale", ConfigDefaults.hudScale, 1, 100);

        hudTextShadow = CLIENT_BUILDER
                .comment("HUD Text Shadow")
                .translation("config.simplyjetpacks.hudTextShadow")
                .define("hudTextShadow", ConfigDefaults.hudTextShadow);

        CLIENT_BUILDER.pop();
        CLIENT_BUILDER.pop();
    }

    private static void setupCommonConfig() {
        COMMON_BUILDER.comment("Simply Jetpacks 2 - Common Configurations").push("simplyjetpacks-common");

        COMMON_BUILDER.comment("Integration Configurations").push("integration");

        enableIntegrationVanilla = COMMON_BUILDER
                .comment("Enable Vanilla Jetpacks Integration.")
                .translation("config.simplyjetpacks.enableIntegrationVanilla")
                .worldRestart()
                .define("enableIntegrationVanilla", ConfigDefaults.enableIntegrationVanilla);

        enableIntegrationImmersiveEngineering = COMMON_BUILDER
                .comment("Enable Immersive Engineering Jetpacks Integration.")
                .translation("config.simplyjetpacks.enableIntegrationImmersiveEngineering")
                .worldRestart()
                .define("enableIntegrationImmersiveEngineering", ConfigDefaults.enableIntegrationImmersiveEngineering);

        enableIntegrationMekanism = COMMON_BUILDER
                .comment("Enable Mekanism Jetpacks Integration.")
                .translation("config.simplyjetpacks.enableIntegrationMekanism")
                .worldRestart()
                .define("enableIntegrationMekanism", ConfigDefaults.enableIntegrationMekanism);

        enableIntegrationEnderIO = COMMON_BUILDER
                .comment("Enable EnderIO Jetpacks Integration.")
                .translation("config.simplyjetpacks.enableIntegrationEnderIO")
                .worldRestart()
                .define("enableIntegrationEnderIO", ConfigDefaults.enableIntegrationEnderIO);

        enableIntegrationThermalExpansion = COMMON_BUILDER
                .comment("Enable Thermal Expansion Jetpacks Integration.")
                .translation("config.simplyjetpacks.enableIntegrationThermalExpansion")
                .worldRestart()
                .define("enableIntegrationThermalExpansion", ConfigDefaults.enableIntegrationThermalExpansion);

        enableIntegrationThermalDynamics = COMMON_BUILDER
                .comment("Enable Thermal Dynamics Jetpacks Integration.")
                .translation("config.simplyjetpacks.enableIntegrationThermalDynamics")
                .worldRestart()
                .define("enableIntegrationThermalDynamics", ConfigDefaults.enableIntegrationThermalDynamics);

        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Jetpack Tuning Configurations").push("tuning");
        JetpackConfig.createJetpackConfig(COMMON_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Misc Configurations").push("misc");

        enableJoinAdvancements = COMMON_BUILDER
                .comment("Enable Join Advancements")
                .translation("config.simplyjetpacks.enableJoinAdvancements")
                .define("enableJoinAdvancements", ConfigDefaults.enableJoinAdvancements);

        COMMON_BUILDER.pop();
        COMMON_BUILDER.pop();
    }

    private static void setupServerConfig() {
        SERVER_BUILDER.comment("Simply Jetpacks 2 - Server Configurations").push("simplyjetpacks-server");
        //JetpackConfig.createJetpackConfig(SERVER_BUILDER);
        SERVER_BUILDER.pop();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
        SimplyJetpacks.LOGGER.info("SJ: CONFIG LOADED: {}", configEvent.getConfig().getFileName());
        JetpackType.loadAllConfigs();
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading configEvent) {
        SimplyJetpacks.LOGGER.info("SJ: CONFIG RE-LOADED: {}", configEvent.getConfig().getFileName());
        JetpackType.loadAllConfigs();
    }

    // Client
    public static ForgeConfigSpec.BooleanValue invertHoverSneakingBehavior;
    public static ForgeConfigSpec.BooleanValue enableJetpackSounds;
    public static ForgeConfigSpec.BooleanValue enableJetpackParticles;
    public static ForgeConfigSpec.BooleanValue showExactEnergy;
    public static ForgeConfigSpec.BooleanValue enableStateMessages;
    public static ForgeConfigSpec.BooleanValue enableJetpackHud;
    public static ForgeConfigSpec.BooleanValue hudTextShadow;
    public static ForgeConfigSpec.IntValue hudTextColor;
    public static ForgeConfigSpec.IntValue hudXOffset;
    public static ForgeConfigSpec.IntValue hudYOffset;
    public static ForgeConfigSpec.LongValue hudScale;
    public static ForgeConfigSpec.EnumValue<ConfigDefaults.HUDPosition> hudTextPosition;
    // Common
    public static ForgeConfigSpec.BooleanValue enableIntegrationVanilla;
    public static ForgeConfigSpec.BooleanValue enableIntegrationImmersiveEngineering;
    public static ForgeConfigSpec.BooleanValue enableIntegrationMekanism;
    public static ForgeConfigSpec.BooleanValue enableIntegrationEnderIO;
    public static ForgeConfigSpec.BooleanValue enableIntegrationThermalExpansion;
    public static ForgeConfigSpec.BooleanValue enableIntegrationThermalDynamics;
    public static ForgeConfigSpec.BooleanValue enableJoinAdvancements;
    // Server
}
