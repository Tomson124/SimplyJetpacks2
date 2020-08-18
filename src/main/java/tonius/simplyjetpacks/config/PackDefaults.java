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
    public Boolean chargerMode;

    public PackDefaults(String modid, String key) {
        this.section = new Section(false, "tuning." + modid + "." + key, "tuning." + key, key);
        DEFAULTS.put(key, this);
    }

    public static PackDefaults get(String key) {
        return DEFAULTS.get(key);
    }

    // The Great Mighty List of Defaults
    static {
        PackDefaults d = new PackDefaults("simplyjetpacks", "jetpack_potato");
        d.fuelCapacity = 1200;
        d.fuelUsage = 45;
        d.speedVertical = 0.9D;
        d.accelVertical = 0.5D;

        d = new PackDefaults("simplyjetpacks", "jetpack_creative");
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
        d.chargerMode = true;

        d = new PackDefaults("simplyjetpacks", "fluxpack_creative");
        d.fuelCapacity = 200000;
        d.fuelPerTickOut = 50000;
        d.fuelPerTickIn = 0;
        d.armorReduction = 8;
        d.enchantability = 10;

        if (ModType.ENDER_IO.loaded) {
            d = new PackDefaults("enderio", "jetpack_eio1");
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
            d.chargerMode = false;

            d = new PackDefaults("enderio", "jetpack_eio2");
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
            d.chargerMode = false;

            d = new PackDefaults("enderio", "jetpack_eio3");
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
            d.chargerMode = false;

            d = new PackDefaults("enderio", "jetpack_eio4");
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
            d.chargerMode = false;

            d = new PackDefaults("enderio", "jetpack_eio5");
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
            d.chargerMode = true;

            d = new PackDefaults("enderio", "fluxpack_eio1");
            d.fuelCapacity = 800000;
            d.fuelPerTickIn = 800;
            d.fuelPerTickOut = 800;
            d.armorReduction = 4;
            d.armorFuelPerHit = 60;
            d.enchantability = 4;

            d = new PackDefaults("enderio", "fluxpack_eio2");
            d.fuelCapacity = 4000000;
            d.fuelPerTickIn = 4000;
            d.fuelPerTickOut = 4000;
            d.armorReduction = 6;
            d.armorFuelPerHit = 100;
            d.enchantability = 6;

            d = new PackDefaults("enderio", "fluxpack_eio3");
            d.fuelCapacity = 20000000;
            d.fuelPerTickIn = 20000;
            d.fuelPerTickOut = 20000;
            d.armorReduction = 7;
            d.armorFuelPerHit = 140;
            d.enchantability = 8;
        }

        if (ModType.THERMAL_EXPANSION.loaded) {
            d = new PackDefaults("te", "jetpack_te1");
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
            d.chargerMode = false;

            d = new PackDefaults("te", "jetpack_te2");
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
            d.chargerMode = false;

            d = new PackDefaults("te", "jetpack_te3");
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
            d.chargerMode = false;

            d = new PackDefaults("te", "jetpack_te4");
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
            d.chargerMode = false;

            d = new PackDefaults("te", "jetpack_te5");
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
            d.chargerMode = true;

            if (ModType.REDSTONE_REPOSITORY.loaded) {
                d = new PackDefaults("te", "jetpack_te5_enderium");
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
                d.chargerMode = true;
            }

            d = new PackDefaults("te", "fluxpack_te1");
            d.fuelCapacity = 1500000;
            d.fuelPerTickIn = 800;
            d.fuelPerTickOut = 800;
            d.armorReduction = 4;
            d.armorFuelPerHit = 60;
            d.enchantability = 4;

            d = new PackDefaults("te", "fluxpack_te2");
            d.fuelCapacity = 12000000;
            d.fuelPerTickIn = 6000;
            d.fuelPerTickOut = 6000;
            d.armorReduction = 6;
            d.armorFuelPerHit = 100;
            d.enchantability = 6;

            d = new PackDefaults("te", "fluxpack_te3");
            d.fuelCapacity = 40000000;
            d.fuelPerTickIn = 20000;
            d.fuelPerTickOut = 20000;
            d.armorReduction = 7;
            d.armorFuelPerHit = 140;
            d.enchantability = 8;

            d = new PackDefaults("te", "fluxpack_te4");
            d.fuelCapacity = 80000000;
            d.fuelPerTickIn = 32000;
            d.fuelPerTickOut = 32000;
            d.armorReduction = 7;
            d.armorFuelPerHit = 160;
            d.enchantability = 10;
        }

        if (ModType.MEKANISM.loaded) {
            d = new PackDefaults("mek", "jetpack_mek1");
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
            d.chargerMode = false;

            d = new PackDefaults("mek", "jetpack_mek2");
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
            d.chargerMode = false;

            d = new PackDefaults("mek", "jetpack_mek3");
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
            d.chargerMode = false;

            d = new PackDefaults("mek", "jetpack_mek4");
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
            d.chargerMode = false;
        }

        if (ModType.IMMERSIVE_ENGINEERING.loaded) {
            d = new PackDefaults("ie", "jetpack_ie1");
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
            d.chargerMode = false;

            d = new PackDefaults("ie", "jetpack_ie2");
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
            d.chargerMode = false;

            d = new PackDefaults("ie", "jetpack_ie3");
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
            d.chargerMode = false;
        }

        if (Config.enableIntegrationVanilla) {
            d = new PackDefaults("vanilla", "jetpack_vanilla1");
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
            d.chargerMode = false;

            d = new PackDefaults("vanilla", "jetpack_vanilla2");
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
            d.chargerMode = false;

            d = new PackDefaults("vanilla", "jetpack_vanilla3");
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
            d.chargerMode = false;
        }
    }
}