package stormedpanda.simplyjetpacks.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class NewJetpackConfig {

    public static HashMap<String, TestPack> packs = new HashMap<>();

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {

        server.comment("jetpack config");

        packs.put("jetpack_creative", new TestPack("creative"));
        packs.put("jetpack_creative_armored", new TestPack("creative_armored"));

        for (Map.Entry<String, TestPack> entry : packs.entrySet()) {
            String name = entry.getKey();
            TestPack pack = entry.getValue();

            pack.energyCapacity = server
                    .comment("The maximum amount of energy that this pack can hold.")
                    .translation("config.simplyjetpacks.tuning.energyCapacity.tooltip")
                    .defineInRange("jetpacks." + name + ".energyCapacity", JetpackConfigDefaults.get(name).energyCapacity, 0, Integer.MAX_VALUE);

            pack.energyUsage = server
                    .comment("The amount of energy that this Jetpack/Fluxpack uses every tick, when being used.")
                    .translation("config.simplyjetpacks.tuning.energyUsage.tooltip")
                    .defineInRange("jetpacks." + name + ".energyUsage", JetpackConfigDefaults.get(name).energyUsage, 0, Integer.MAX_VALUE);

            pack.energyPerTickIn = server
                    .comment("The amount of energy that can be inserted into this Jetpack/Fluxpack per tick from external sources.")
                    .translation("config.simplyjetpacks.tuning.energyPerTickIn.tooltip")
                    .defineInRange("jetpacks." + name + ".energyPerTickIn", JetpackConfigDefaults.get(name).energyPerTickIn, 0, Integer.MAX_VALUE);

            pack.energyPerTickOut = server
                    .comment("The amount of energy that can be extracted from this Jetpack/Fluxpack per tick by external sources. Also determines how quickly Jetpacks/Fluxpacks can charge other items.")
                    .translation("config.simplyjetpacks.tuning.energyPerTickOut.tooltip")
                    .defineInRange("jetpacks." + name + ".energyPerTickOut", JetpackConfigDefaults.get(name).energyPerTickOut, 0, Integer.MAX_VALUE);

            pack.armorReduction = server
                    .comment("How well this Jetpack/Fluxpack can protect the user from damage, if armored. The higher the value, the stronger the armor will be.")
                    .translation("config.simplyjetpacks.tuning.armorReduction.tooltip")
                    .defineInRange("jetpacks." + name + ".armorReduction", JetpackConfigDefaults.get(name).armorReduction, 0, Integer.MAX_VALUE);

            pack.armorEnergyPerHit = server
                    .comment("How much energy is lost from this Jetpack/Fluxpack when the user is hit, if armored.")
                    .translation("config.simplyjetpacks.tuning.armorEnergyPerHit.tooltip")
                    .defineInRange("jetpacks." + name + ".armorEnergyPerHit", JetpackConfigDefaults.get(name).armorEnergyPerHit, 0, Integer.MAX_VALUE);

            pack.enchantability = server
                    .comment("The enchantability of this Jetpack/Fluxpack. If set to 0, no enchantments can be applied.")
                    .translation("config.simplyjetpacks.tuning.enchantability.tooltip")
                    .defineInRange("jetpacks." + name + ".enchantability", JetpackConfigDefaults.get(name).enchantability, 0, Integer.MAX_VALUE);

            pack.speedVertical = server
                    .comment("The maximum vertical speed of this Jetpack when flying.")
                    .translation("config.simplyjetpacks.tuning.speedVertical.tooltip")
                    .defineInRange("jetpacks." + name + ".speedVertical", JetpackConfigDefaults.get(name).speedVertical, 0, Double.MAX_VALUE);

            pack.accelVertical = server
                    .comment("The vertical acceleration of this Jetpack when flying, every tick, this amount of vertical speed will be added until maximum speed is reached.")
                    .translation("config.simplyjetpacks.tuning.accelVertical.tooltip")
                    .defineInRange("jetpacks." + name + ".accelVertical", JetpackConfigDefaults.get(name).accelVertical, 0, Double.MAX_VALUE);

            pack.speedVerticalHover = server
                    .comment("The maximum vertical speed of this Jetpack when flying in Hover Mode.")
                    .translation("config.simplyjetpacks.tuning.speedVerticalHover.tooltip")
                    .defineInRange("jetpacks." + name + ".speedVerticalHover", JetpackConfigDefaults.get(name).speedVerticalHover, 0, Double.MAX_VALUE);

            pack.speedVerticalHoverSlow = server
                    .comment("The maximum vertical speed of this Jetpack when slowly descending in Hover Mode.")
                    .translation("config.simplyjetpacks.tuning.speedVerticalHoverSlow.tooltip")
                    .defineInRange("jetpacks." + name + ".speedVerticalHoverSlow", JetpackConfigDefaults.get(name).speedVerticalHoverSlow, 0, Double.MAX_VALUE);

            pack.speedSideways = server
                    .comment("The speed of this Jetpack when flying sideways. This is mostly noticeable in Hover Mode.")
                    .translation("config.simplyjetpacks.tuning.speedSideways.tooltip")
                    .defineInRange("jetpacks." + name + ".speedSideways", JetpackConfigDefaults.get(name).speedSideways, 0, Double.MAX_VALUE);

            pack.sprintSpeedModifier = server
                    .comment("How much faster this Jetpack will fly forward when sprinting. Setting this to 1.0 will make sprinting have no effect apart from the added speed from vanilla.")
                    .translation("config.simplyjetpacks.tuning.sprintSpeedModifier.tooltip")
                    .defineInRange("jetpacks." + name + ".sprintSpeedModifier", JetpackConfigDefaults.get(name).sprintSpeedModifier, 0, Double.MAX_VALUE);

            pack.sprintEnergyModifier = server
                    .comment("How much more energy this Jetpack will use when sprinting. Setting this to 1.0 will make sprinting have no effect on energy usage.")
                    .translation("config.simplyjetpacks.tuning.sprintEnergyModifier.tooltip")
                    .defineInRange("jetpacks." + name + ".sprintEnergyModifier", JetpackConfigDefaults.get(name).sprintEnergyModifier, 0, Double.MAX_VALUE);

            pack.emergencyHoverMode = server
                    .comment("When enabled, this Jetpack will be able to activate Hover Mode automatically when the wearer is about to die from a fall.")
                    .translation("config.simplyjetpacks.tuning.emergencyHoverMode.tooltip")
                    .define("jetpacks." + name + ".emergencyHoverMode", JetpackConfigDefaults.get(name).emergencyHoverMode);

            pack.chargerMode = server
                    .comment("When enabled, this Jetpack will be able to activate Charger Mode.")
                    .translation("config.simplyjetpacks.tuning.chargerMode.tooltip")
                    .define("jetpacks." + name + ".chargerMode", JetpackConfigDefaults.get(name).chargerMode);
        }
    }
}
