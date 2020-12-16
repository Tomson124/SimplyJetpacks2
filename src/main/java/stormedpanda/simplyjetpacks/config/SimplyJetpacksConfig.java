package stormedpanda.simplyjetpacks.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.integration.IntegrationList;

@Mod.EventBusSubscriber(modid = SimplyJetpacks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SimplyJetpacksConfig {

    public static class Client {

        public final BooleanValue invertHoverSneakingBehavior;
        public final BooleanValue enableJetpackSounds;
        public final BooleanValue showExactEnergy;
        public final BooleanValue enableStateMessages;
        public final BooleanValue enableJetpackHud;
        public final IntValue hudTextColor;
        public final EnumValue<ConfigDefaults.HUDPosition> hudTextPosition;
        public final IntValue hudXOffset;
        public final IntValue hudYOffset;
        public final ForgeConfigSpec.LongValue hudScale;
        public final BooleanValue hudTextShadow;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.comment("Simply Jetpacks 2 - Client Configurations").push("simplyjetpacks-client");

            builder.comment("Controls Configurations").push("controls");

            invertHoverSneakingBehavior = builder
                    .comment("This sets whether you must hold sneak to hover.")
                    .translation("config.simplyjetpacks.invertHoverSneakingBehavior")
                    .define("invertHoverSneakingBehavior", ConfigDefaults.invertHoverSneakingBehavior);

            builder.pop();

            builder.comment("Audio Configurations").push("audio");

            enableJetpackSounds = builder
                    .comment("This sets whether jetpack sounds will play.")
                    .translation("config.simplyjetpacks.enableJetpackSounds")
                    .define("enableJetpackSounds", ConfigDefaults.enableJetpackSounds);

            builder.pop();

            builder.comment("GUI Configurations").push("gui");

            showExactEnergy = builder
                    .comment("Show exact Energy of Jetpack in HUD")
                    .translation("config.simplyjetpacks.showExactEnergy")
                    .define("showExactEnergy", ConfigDefaults.showExactEnergy);

            enableStateMessages = builder
                    .comment("This sets whether or not jetpack state messages will show.")
                    .translation("config.simplyjetpacks.enableStateMessages")
                    .define("enableStateMessages", ConfigDefaults.enableStateMessages);

            enableJetpackHud = builder
                    .comment("This sets whether or not the jetpack HUD will be visible.")
                    .translation("config.simplyjetpacks.enableJetpackHud")
                    .define("enableJetpackHud", ConfigDefaults.enableJetpackHud);

            hudTextColor = builder
                    .comment("This sets the color of the Jetpack HUD.")
                    .translation("config.simplyjetpacks.hudTextColor")
                    .defineInRange("hudTextColor", ConfigDefaults.hudTextColor, Integer.MIN_VALUE, Integer.MAX_VALUE);

            hudTextPosition = builder
                    .comment("HUD Position")
                    .translation("config.simplyjetpacks.hudTextPosition")
                    .defineEnum("hudTextPosition", ConfigDefaults.hudTextPosition);

            hudXOffset = builder
                    .comment("HUD Position X Offset")
                    .translation("config.simplyjetpacks.hudXOffset")
                    .defineInRange("hudXOffset", ConfigDefaults.hudXOffset, Integer.MIN_VALUE, Integer.MAX_VALUE);

            hudYOffset = builder
                    .comment("HUD Position Y Offset")
                    .translation("config.simplyjetpacks.hudYOffset")
                    .defineInRange("hudYOffset", ConfigDefaults.hudYOffset, Integer.MIN_VALUE, Integer.MAX_VALUE);

            hudScale = builder
                    .comment("HUD Scale")
                    .translation("config.simplyjetpacks.hudScale")
                    .defineInRange("hudScale", ConfigDefaults.hudScale, 1, 100);

            hudTextShadow = builder
                    .comment("HUD Text Shadow")
                    .translation("config.simplyjetpacks.hudTextShadow")
                    .define("hudTextShadow", ConfigDefaults.hudTextShadow);

            builder.pop();
        }
    }

    public static class Common {

        public final BooleanValue enableIntegrationVanilla;
        public final BooleanValue enableIntegrationImmersiveEngineering;
        public final BooleanValue enableIntegrationMekanism;
        public final BooleanValue enableIntegrationEnderIO;
        public final BooleanValue enableIntegrationThermalExpansion;
        public final BooleanValue enableIntegrationThermalDynamics;
        public final BooleanValue enableJoinAdvancements;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Simply Jetpacks 2 - Common Configurations").push("simplyjetpacks-common");

            builder.comment("Integration Configurations").push("integration");
            enableIntegrationVanilla = builder
                    .comment("Enable Vanilla Jetpacks Integration.")
                    .translation("config.simplyjetpacks.enableIntegrationVanilla")
                    .worldRestart()
                    .define("enableIntegrationVanilla", ConfigDefaults.enableIntegrationVanilla);

            enableIntegrationImmersiveEngineering = builder
                    .comment("Enable Immersive Engineering Jetpacks Integration.")
                    .translation("config.simplyjetpacks.enableIntegrationImmersiveEngineering")
                    .worldRestart()
                    .define("enableIntegrationImmersiveEngineering", ConfigDefaults.enableIntegrationImmersiveEngineering);

            enableIntegrationMekanism = builder
                    .comment("Enable Mekanism Jetpacks Integration.")
                    .translation("config.simplyjetpacks.enableIntegrationMekanism")
                    .worldRestart()
                    .define("enableIntegrationMekanism", ConfigDefaults.enableIntegrationMekanism);

            enableIntegrationEnderIO = builder
                    .comment("Enable EnderIO Jetpacks Integration.")
                    .translation("config.simplyjetpacks.enableIntegrationEnderIO")
                    .worldRestart()
                    .define("enableIntegrationEnderIO", ConfigDefaults.enableIntegrationEnderIO);

            enableIntegrationThermalExpansion = builder
                    .comment("Enable Thermal Expansion Jetpacks Integration.")
                    .translation("config.simplyjetpacks.enableIntegrationThermalExpansion")
                    .worldRestart()
                    .define("enableIntegrationThermalExpansion", ConfigDefaults.enableIntegrationThermalExpansion);

            enableIntegrationThermalDynamics = builder
                    .comment("Enable Thermal Dynamics Jetpacks Integration.")
                    .translation("config.simplyjetpacks.enableIntegrationThermalDynamics")
                    .worldRestart()
                    .define("enableIntegrationThermalDynamics", ConfigDefaults.enableIntegrationThermalDynamics);

            builder.pop();

            builder.comment("Jetpack Tuning Configurations").push("tuning");

            builder.pop();

            builder.comment("Misc Configurations").push("misc");

            enableJoinAdvancements = builder
                    .comment("Enable Join Advancements")
                    .translation("config.simplyjetpacks.enableJoinAdvancements")
                    .define("enableJoinAdvancements", ConfigDefaults.enableJoinAdvancements);

            builder.pop();
        }
    }

    public static class Server {
        public Server(ForgeConfigSpec.Builder builder) {
            builder.comment("Simply Jetpacks 2 - Server Configurations").push("simplyjetpacks-server");
            builder.pop();
        }
    }

    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;
    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static final ForgeConfigSpec SERVER_SPEC;
    public static final Server SERVER;
    static {
        final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading event) {
        IntegrationList.init();
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading event) {
        IntegrationList.init();
    }
}