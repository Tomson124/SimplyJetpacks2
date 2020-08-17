package tonius.simplyjetpacks.config;

import tonius.simplyjetpacks.integration.ModType;

import java.util.HashMap;
import java.util.Map;

public class PackDefaults {

    private static final Map<String, PackDefaults> DEFAULTS = new HashMap<>();
    public final Section section;

    // Base
    public Integer fuelCapacity;
    public Integer fuelUsage;
    public Integer fuelPerTickIn;
    public Integer fuelPerTickOut;
    public Integer armorReduction;
    public Integer armorFuelPerHit;
    public Integer enchantability;

    // Jetpack
    public Double speedVertical;
    public Double accelVertical;
    public Double speedVerticalHover;
    public Double speedVerticalHoverSlow;
    public Double speedSideways;
    public Double sprintSpeedModifier;
    public Double sprintFuelModifier;
    public Boolean emergencyHoverMode;

    public PackDefaults(String key, String sectionTitle) {
        this.section = new Section(false, "Tuning - " + sectionTitle, "tuning." + key);
        DEFAULTS.put(key, this);
    }

    public static PackDefaults get(String key) {
        return DEFAULTS.get(key);
    }

    // The Great Mighty List of Defaults
    static {
        PackDefaults d = new PackDefaults("jetpack_potato", "Potato Jetpack");
        d.fuelCapacity = 1200;
        d.fuelUsage = 45;
        d.speedVertical = 0.9D;
        d.accelVertical = 0.5D;

        d = new PackDefaults("jetpack_creative", "Creative Jetpack");
        d.fuelCapacity = 200000;
        d.fuelPerTickOut = 50000;
        d.fuelPerTickIn = 0;
        d.armorReduction = 12;
        d.enchantability = 20;
        d.speedVertical = 0.9D;
        d.accelVertical = 0.15D;
        d.speedVerticalHover = 0.45D;
        d.speedVerticalHoverSlow = 0.0D;
        d.speedSideways = 0.21D;
        d.sprintSpeedModifier = 2.5D;
        d.emergencyHoverMode = true;

        d = new PackDefaults("fluxpack_creative", "Creative Fluxpack");
        d.fuelCapacity = 200000;
        d.fuelPerTickOut = 50000;
        d.fuelPerTickIn = 0;
        d.armorReduction = 8;
        d.enchantability = 10;

        if (ModType.ENDER_IO.loaded) {
            d = new PackDefaults("jetpack_eio1", "Conductive Iron Jetpack (eio 1)");
            d.fuelCapacity = 80000;
            d.fuelUsage = 32;
            d.fuelPerTickIn = 400;
            d.armorReduction = 5;
            d.armorFuelPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintFuelModifier = 1.0D;
            d.emergencyHoverMode = false;

            d = new PackDefaults("jetpack_eio2", "Electrical Steel Jetpack (eio 2)");
            d.fuelCapacity = 400000;
            d.fuelUsage = 50;
            d.fuelPerTickIn = 2000;
            d.armorReduction = 6;
            d.armorFuelPerHit = 100;
            d.enchantability = 8;
            d.speedVertical = 0.3D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.08D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintFuelModifier = 1.0D;
            d.emergencyHoverMode = false;

            d = new PackDefaults("jetpack_eio3", "Energetic Jetpack (eio 3)");
            d.fuelCapacity = 4000000;
            d.fuelUsage = 200;
            d.fuelPerTickIn = 20000;
            d.armorReduction = 7;
            d.armorFuelPerHit = 120;
            d.enchantability = 13;
            d.speedVertical = 0.48D;
            d.accelVertical = 0.13D;
            d.speedVerticalHover = 0.34D;
            d.speedVerticalHoverSlow = 0.03D;
            d.speedSideways = 0.14D;
            d.sprintSpeedModifier = 1.3D;
            d.sprintFuelModifier = 2.5D;
            d.emergencyHoverMode = true;

            d = new PackDefaults("jetpack_eio4", "Vibrant Jetpack (eio 4)");
            d.fuelCapacity = 20000000;
            d.fuelUsage = 450;
            d.fuelPerTickIn = 50000;
            d.armorReduction = 8;
            d.armorFuelPerHit = 160;
            d.enchantability = 17;
            d.speedVertical = 0.8D;
            d.accelVertical = 0.14D;
            d.speedVerticalHover = 0.4D;
            d.speedVerticalHoverSlow = 0.005D;
            d.speedSideways = 0.19D;
            d.sprintSpeedModifier = 1.8D;
            d.sprintFuelModifier = 4.0D;
            d.emergencyHoverMode = true;

            d = new PackDefaults("jetpack_eio5", "Dark Soularium JetPlate (eio 5)");
            d.fuelCapacity = 60000000;
            d.fuelUsage = 850;
            d.fuelPerTickIn = 200000;
            d.fuelPerTickOut = 32000;
            d.armorReduction = 12;
            d.armorFuelPerHit = 240;
            d.enchantability = 20;
            d.speedVertical = 0.9D;
            d.accelVertical = 0.15D;
            d.speedVerticalHover = 0.45D;
            d.speedVerticalHoverSlow = 0.0D;
            d.speedSideways = 0.21D;
            d.sprintSpeedModifier = 2.4D;
            d.sprintFuelModifier = 6.0D;
            d.emergencyHoverMode = true;

            d = new PackDefaults("fluxpack_eio1", "Basic Capacitor Pack (eio 1)");
            d.fuelCapacity = 800000;
            d.fuelPerTickIn = 800;
            d.fuelPerTickOut = 800;
            d.armorReduction = 4;
            d.armorFuelPerHit = 60;
            d.enchantability = 4;

            d = new PackDefaults("fluxpack_eio2", "Double-Layer Capacitor Pack (eio 2)");
            d.fuelCapacity = 4000000;
            d.fuelPerTickIn = 4000;
            d.fuelPerTickOut = 4000;
            d.armorReduction = 6;
            d.armorFuelPerHit = 100;
            d.enchantability = 6;

            d = new PackDefaults("fluxpack_eio3", "Octadic Capacitor Pack (eio 3)");
            d.fuelCapacity = 20000000;
            d.fuelPerTickIn = 20000;
            d.fuelPerTickOut = 20000;
            d.armorReduction = 7;
            d.armorFuelPerHit = 140;
            d.enchantability = 8;
        }

        if (ModType.THERMAL_EXPANSION.loaded) {
            d = new PackDefaults("jetpack_te1", "Leadstone Jetpack (te 1)");
            d.fuelCapacity = 800000;
            d.fuelUsage = 32;
            d.fuelPerTickIn = 1500;
            d.armorReduction = 5;
            d.armorFuelPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintFuelModifier = 1.0D;
            d.emergencyHoverMode = false;

            d = new PackDefaults("jetpack_te2", "Hardened Jetpack (te 2)");
            d.fuelCapacity = 3000000;
            d.fuelUsage = 50;
            d.fuelPerTickIn = 8000;
            d.armorReduction = 6;
            d.armorFuelPerHit = 80;
            d.enchantability = 8;
            d.speedVertical = 0.3D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.08D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintFuelModifier = 1.0D;
            d.emergencyHoverMode = false;

            d = new PackDefaults("jetpack_te3", "Reinforced Jetpack (te 3)");
            d.fuelCapacity = 6000000;
            d.fuelUsage = 200;
            d.fuelPerTickIn = 15000;
            d.armorReduction = 7;
            d.armorFuelPerHit = 120;
            d.enchantability = 13;
            d.speedVertical = 0.48D;
            d.accelVertical = 0.13D;
            d.speedVerticalHover = 0.34D;
            d.speedVerticalHoverSlow = 0.03D;
            d.speedSideways = 0.14D;
            d.sprintSpeedModifier = 1.3D;
            d.sprintFuelModifier = 2.5D;
            d.emergencyHoverMode = true;

            d = new PackDefaults("jetpack_te4", "Resonant Jetpack (te 4)");
            d.fuelCapacity = 25000000;
            d.fuelUsage = 450;
            d.fuelPerTickIn = 20000;
            d.armorReduction = 8;
            d.armorFuelPerHit = 160;
            d.enchantability = 17;
            d.speedVertical = 0.8D;
            d.accelVertical = 0.14D;
            d.speedVerticalHover = 0.4D;
            d.speedVerticalHoverSlow = 0.005D;
            d.speedSideways = 0.19D;
            d.sprintSpeedModifier = 1.8D;
            d.sprintFuelModifier = 4.0D;
            d.emergencyHoverMode = true;

            d = new PackDefaults("jetpack_te5", "Flux-Infused JetPlate (te 5)");
            d.fuelCapacity = 50000000;
            d.fuelUsage = 850;
            d.fuelPerTickIn = 30000;
            d.fuelPerTickOut = 30000;
            d.armorReduction = 12;
            d.armorFuelPerHit = 240;
            d.enchantability = 20;
            d.speedVertical = 0.9D;
            d.accelVertical = 0.15D;
            d.speedVerticalHover = 0.45D;
            d.speedVerticalHoverSlow = 0.0D;
            d.speedSideways = 0.21D;
            d.sprintSpeedModifier = 2.4D;
            d.sprintFuelModifier = 6.0D;
            d.emergencyHoverMode = true;

            if (ModType.REDSTONE_REPOSITORY.loaded) {
                d = new PackDefaults("jetpack_te5_enderium", "Flux-Infused JetPlate (Enderium Armored) (te/rr 5)");
                d.fuelCapacity = 50000000;
                d.fuelUsage = 850;
                d.fuelPerTickIn = 30000;
                d.fuelPerTickOut = 30000;
                d.armorReduction = 12;
                d.armorFuelPerHit = 4500;
                d.enchantability = 20;
                d.speedVertical = 0.9D;
                d.accelVertical = 0.15D;
                d.speedVerticalHover = 0.45D;
                d.speedVerticalHoverSlow = 0.0D;
                d.speedSideways = 0.21D;
                d.sprintSpeedModifier = 2.4D;
                d.sprintFuelModifier = 6.0D;
                d.emergencyHoverMode = true;
            }

            d = new PackDefaults("fluxpack_te1", "Leadstone Fluxpack (te 1)");
            d.fuelCapacity = 1500000;
            d.fuelPerTickIn = 800;
            d.fuelPerTickOut = 800;
            d.armorReduction = 4;
            d.armorFuelPerHit = 60;
            d.enchantability = 4;

            d = new PackDefaults("fluxpack_te2", "Hardened Fluxpack (te 2)");
            d.fuelCapacity = 12000000;
            d.fuelPerTickIn = 6000;
            d.fuelPerTickOut = 6000;
            d.armorReduction = 6;
            d.armorFuelPerHit = 100;
            d.enchantability = 6;

            d = new PackDefaults("fluxpack_te3", "Reinforced Fluxpack (te 3)");
            d.fuelCapacity = 40000000;
            d.fuelPerTickIn = 20000;
            d.fuelPerTickOut = 20000;
            d.armorReduction = 7;
            d.armorFuelPerHit = 140;
            d.enchantability = 8;

            d = new PackDefaults("fluxpack_te4", "Resonant Fluxpack (te 4)");
            d.fuelCapacity = 80000000;
            d.fuelPerTickIn = 32000;
            d.fuelPerTickOut = 32000;
            d.armorReduction = 7;
            d.armorFuelPerHit = 160;
            d.enchantability = 10;
        }

        if (ModType.MEKANISM.loaded) {
            d = new PackDefaults("jetpack_mek1", "Basic Jetpack (mek 1)");
            d.fuelCapacity = 80000;
            d.fuelUsage = 32;
            d.fuelPerTickIn = 400;
            d.armorReduction = 5;
            d.armorFuelPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintFuelModifier = 1.0D;
            d.emergencyHoverMode = false;

            d = new PackDefaults("jetpack_mek2", "Advanced Jetpack (mek 2)");
            d.fuelCapacity = 400000;
            d.fuelUsage = 50;
            d.fuelPerTickIn = 2000;
            d.armorReduction = 6;
            d.armorFuelPerHit = 100;
            d.enchantability = 8;
            d.speedVertical = 0.3D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.08D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintFuelModifier = 1.0D;
            d.emergencyHoverMode = false;

            d = new PackDefaults("jetpack_mek3", "Elite Jetpack (mek 3)");
            d.fuelCapacity = 4000000;
            d.fuelUsage = 200;
            d.fuelPerTickIn = 20000;
            d.armorReduction = 7;
            d.armorFuelPerHit = 120;
            d.enchantability = 13;
            d.speedVertical = 0.48D;
            d.accelVertical = 0.13D;
            d.speedVerticalHover = 0.34D;
            d.speedVerticalHoverSlow = 0.03D;
            d.speedSideways = 0.14D;
            d.sprintSpeedModifier = 1.3D;
            d.sprintFuelModifier = 2.5D;
            d.emergencyHoverMode = true;

            d = new PackDefaults("jetpack_mek4", "Ultimate Jetpack (mek 4)");
            d.fuelCapacity = 20000000;
            d.fuelUsage = 450;
            d.fuelPerTickIn = 50000;
            d.armorReduction = 8;
            d.armorFuelPerHit = 160;
            d.enchantability = 17;
            d.speedVertical = 0.8D;
            d.accelVertical = 0.14D;
            d.speedVerticalHover = 0.4D;
            d.speedVerticalHoverSlow = 0.005D;
            d.speedSideways = 0.19D;
            d.sprintSpeedModifier = 1.8D;
            d.sprintFuelModifier = 4.0D;
            d.emergencyHoverMode = true;
        }

        if (ModType.IMMERSIVE_ENGINEERING.loaded) {
            d = new PackDefaults("jetpack_ie1", "Low Voltage Jetpack (ie 1)");
            d.fuelCapacity = 80000;
            d.fuelUsage = 32;
            d.fuelPerTickIn = 400;
            d.armorReduction = 5;
            d.armorFuelPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintFuelModifier = 1.0D;
            d.emergencyHoverMode = false;

            d = new PackDefaults("jetpack_ie2", "Medium Voltage Jetpack (ie 2)");
            d.fuelCapacity = 400000;
            d.fuelUsage = 50;
            d.fuelPerTickIn = 2000;
            d.armorReduction = 6;
            d.armorFuelPerHit = 100;
            d.enchantability = 8;
            d.speedVertical = 0.3D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.08D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintFuelModifier = 1.0D;
            d.emergencyHoverMode = false;

            d = new PackDefaults("jetpack_ie3", "High Voltage Jetpack (ie 3)");
            d.fuelCapacity = 4000000;
            d.fuelUsage = 200;
            d.fuelPerTickIn = 20000;
            d.armorReduction = 7;
            d.armorFuelPerHit = 120;
            d.enchantability = 13;
            d.speedVertical = 0.48D;
            d.accelVertical = 0.13D;
            d.speedVerticalHover = 0.34D;
            d.speedVerticalHoverSlow = 0.03D;
            d.speedSideways = 0.14D;
            d.sprintSpeedModifier = 1.3D;
            d.sprintFuelModifier = 2.5D;
            d.emergencyHoverMode = true;
        }

        if (Config.enableIntegrationVanilla) {
            d = new PackDefaults("jetpack_vanilla1", "Iron Jetpack (vanilla 1)");
            d.fuelCapacity = 80000;
            d.fuelUsage = 32;
            d.fuelPerTickIn = 400;
            d.armorReduction = 5;
            d.armorFuelPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintFuelModifier = 1.0D;
            d.emergencyHoverMode = false;

            d = new PackDefaults("jetpack_vanilla2", "Gold Jetpack (vanilla 2)");
            d.fuelCapacity = 400000;
            d.fuelUsage = 50;
            d.fuelPerTickIn = 2000;
            d.armorReduction = 6;
            d.armorFuelPerHit = 80;
            d.enchantability = 8;
            d.speedVertical = 0.4D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.2D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.1D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintFuelModifier = 1.0D;
            d.emergencyHoverMode = false;

            d = new PackDefaults("jetpack_vanilla3", "Diamond Jetpack (vanilla 3)");
            d.fuelCapacity = 20000000;
            d.fuelUsage = 450;
            d.fuelPerTickIn = 50000;
            d.armorReduction = 8;
            d.armorFuelPerHit = 160;
            d.enchantability = 17;
            d.speedVertical = 0.8D;
            d.accelVertical = 0.14D;
            d.speedVerticalHover = 0.4D;
            d.speedVerticalHoverSlow = 0.005D;
            d.speedSideways = 0.19D;
            d.sprintSpeedModifier = 1.8D;
            d.sprintFuelModifier = 4.0D;
            d.emergencyHoverMode = true;
        }
    }
}