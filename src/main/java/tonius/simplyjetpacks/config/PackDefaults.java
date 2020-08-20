package tonius.simplyjetpacks.config;

import tonius.simplyjetpacks.integration.ModType;

import java.util.HashMap;
import java.util.Map;

public class PackDefaults {

    private static final Map<String, PackDefaults> DEFAULTS = new HashMap<>();
    public final Section section;
    // Base
    public Integer energyCapacity;
    public Integer energyUsage;
    public Integer energyPerTickIn;
    public Integer energyPerTickOut;
    public Integer armorReduction;
    public Integer armorEnergyPerHit;
    public Integer enchantability;
    // Jetpack
    public Double speedVertical;
    public Double accelVertical;
    public Double speedVerticalHover;
    public Double speedVerticalHoverSlow;
    public Double speedSideways;
    public Double sprintSpeedModifier;
    public Double sprintEnergyModifier;
    public Boolean emergencyHoverMode;
    public Boolean chargerMode;

    public PackDefaults(String modid, String key) {
        this.section = new Section(false, "tuning." + modid + "." + key, "tuning." + key, "item.simplyjetpacks." + key + ".name");
        DEFAULTS.put(key, this);
    }

    public static PackDefaults get(String key) {
        return DEFAULTS.get(key);
    }

    // The Great Mighty List of Defaults
    static {
        PackDefaults d = new PackDefaults("simplyjetpacks", "jetpack_potato");
        d.energyCapacity = 1200;
        d.energyUsage = 45;
        d.speedVertical = 0.9D;
        d.accelVertical = 0.5D;

        d = new PackDefaults("simplyjetpacks", "jetpack_creative");
        d.energyCapacity = 200000;
        d.energyPerTickOut = 50000;
        d.energyPerTickIn = 0;
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
        d.energyCapacity = 200000;
        d.energyPerTickOut = 50000;
        d.energyPerTickIn = 0;
        d.armorReduction = 8;
        d.enchantability = 10;

        if (ModType.ENDER_IO.loaded) {
            d = new PackDefaults("enderio", "jetpack_eio1");
            d.energyCapacity = 80000;
            d.energyUsage = 32;
            d.energyPerTickIn = 400;
            d.armorReduction = 5;
            d.armorEnergyPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new PackDefaults("enderio", "jetpack_eio2");
            d.energyCapacity = 400000;
            d.energyUsage = 50;
            d.energyPerTickIn = 2000;
            d.armorReduction = 6;
            d.armorEnergyPerHit = 100;
            d.enchantability = 8;
            d.speedVertical = 0.3D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.08D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new PackDefaults("enderio", "jetpack_eio3");
            d.energyCapacity = 4000000;
            d.energyUsage = 200;
            d.energyPerTickIn = 20000;
            d.armorReduction = 7;
            d.armorEnergyPerHit = 120;
            d.enchantability = 13;
            d.speedVertical = 0.48D;
            d.accelVertical = 0.13D;
            d.speedVerticalHover = 0.34D;
            d.speedVerticalHoverSlow = 0.03D;
            d.speedSideways = 0.14D;
            d.sprintSpeedModifier = 1.3D;
            d.sprintEnergyModifier = 2.5D;
            d.emergencyHoverMode = true;
            d.chargerMode = false;

            d = new PackDefaults("enderio", "jetpack_eio4");
            d.energyCapacity = 20000000;
            d.energyUsage = 450;
            d.energyPerTickIn = 50000;
            d.armorReduction = 8;
            d.armorEnergyPerHit = 160;
            d.enchantability = 17;
            d.speedVertical = 0.8D;
            d.accelVertical = 0.14D;
            d.speedVerticalHover = 0.4D;
            d.speedVerticalHoverSlow = 0.005D;
            d.speedSideways = 0.19D;
            d.sprintSpeedModifier = 1.8D;
            d.sprintEnergyModifier = 4.0D;
            d.emergencyHoverMode = true;
            d.chargerMode = false;

            d = new PackDefaults("enderio", "jetpack_eio5");
            d.energyCapacity = 60000000;
            d.energyUsage = 850;
            d.energyPerTickIn = 200000;
            d.energyPerTickOut = 32000;
            d.armorReduction = 12;
            d.armorEnergyPerHit = 240;
            d.enchantability = 20;
            d.speedVertical = 0.9D;
            d.accelVertical = 0.15D;
            d.speedVerticalHover = 0.45D;
            d.speedVerticalHoverSlow = 0.0D;
            d.speedSideways = 0.21D;
            d.sprintSpeedModifier = 2.4D;
            d.sprintEnergyModifier = 6.0D;
            d.emergencyHoverMode = true;
            d.chargerMode = true;

            d = new PackDefaults("enderio", "fluxpack_eio1");
            d.energyCapacity = 800000;
            d.energyPerTickIn = 800;
            d.energyPerTickOut = 800;
            d.armorReduction = 4;
            d.armorEnergyPerHit = 60;
            d.enchantability = 4;

            d = new PackDefaults("enderio", "fluxpack_eio2");
            d.energyCapacity = 4000000;
            d.energyPerTickIn = 4000;
            d.energyPerTickOut = 4000;
            d.armorReduction = 6;
            d.armorEnergyPerHit = 100;
            d.enchantability = 6;

            d = new PackDefaults("enderio", "fluxpack_eio3");
            d.energyCapacity = 20000000;
            d.energyPerTickIn = 20000;
            d.energyPerTickOut = 20000;
            d.armorReduction = 7;
            d.armorEnergyPerHit = 140;
            d.enchantability = 8;
        }

        if (ModType.THERMAL_EXPANSION.loaded) {
            d = new PackDefaults("te", "jetpack_te1");
            d.energyCapacity = 800000;
            d.energyUsage = 32;
            d.energyPerTickIn = 1500;
            d.armorReduction = 5;
            d.armorEnergyPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new PackDefaults("te", "jetpack_te2");
            d.energyCapacity = 3000000;
            d.energyUsage = 50;
            d.energyPerTickIn = 8000;
            d.armorReduction = 6;
            d.armorEnergyPerHit = 80;
            d.enchantability = 8;
            d.speedVertical = 0.3D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.08D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new PackDefaults("te", "jetpack_te3");
            d.energyCapacity = 6000000;
            d.energyUsage = 200;
            d.energyPerTickIn = 15000;
            d.armorReduction = 7;
            d.armorEnergyPerHit = 120;
            d.enchantability = 13;
            d.speedVertical = 0.48D;
            d.accelVertical = 0.13D;
            d.speedVerticalHover = 0.34D;
            d.speedVerticalHoverSlow = 0.03D;
            d.speedSideways = 0.14D;
            d.sprintSpeedModifier = 1.3D;
            d.sprintEnergyModifier = 2.5D;
            d.emergencyHoverMode = true;
            d.chargerMode = false;

            d = new PackDefaults("te", "jetpack_te4");
            d.energyCapacity = 25000000;
            d.energyUsage = 450;
            d.energyPerTickIn = 20000;
            d.armorReduction = 8;
            d.armorEnergyPerHit = 160;
            d.enchantability = 17;
            d.speedVertical = 0.8D;
            d.accelVertical = 0.14D;
            d.speedVerticalHover = 0.4D;
            d.speedVerticalHoverSlow = 0.005D;
            d.speedSideways = 0.19D;
            d.sprintSpeedModifier = 1.8D;
            d.sprintEnergyModifier = 4.0D;
            d.emergencyHoverMode = true;
            d.chargerMode = false;

            d = new PackDefaults("te", "jetpack_te5");
            d.energyCapacity = 50000000;
            d.energyUsage = 850;
            d.energyPerTickIn = 30000;
            d.energyPerTickOut = 30000;
            d.armorReduction = 12;
            d.armorEnergyPerHit = 240;
            d.enchantability = 20;
            d.speedVertical = 0.9D;
            d.accelVertical = 0.15D;
            d.speedVerticalHover = 0.45D;
            d.speedVerticalHoverSlow = 0.0D;
            d.speedSideways = 0.21D;
            d.sprintSpeedModifier = 2.4D;
            d.sprintEnergyModifier = 6.0D;
            d.emergencyHoverMode = true;
            d.chargerMode = true;

            if (ModType.REDSTONE_REPOSITORY.loaded) {
                d = new PackDefaults("te", "jetpack_te5_enderium");
                d.energyCapacity = 50000000;
                d.energyUsage = 850;
                d.energyPerTickIn = 30000;
                d.energyPerTickOut = 30000;
                d.armorReduction = 12;
                d.armorEnergyPerHit = 4500;
                d.enchantability = 20;
                d.speedVertical = 0.9D;
                d.accelVertical = 0.15D;
                d.speedVerticalHover = 0.45D;
                d.speedVerticalHoverSlow = 0.0D;
                d.speedSideways = 0.21D;
                d.sprintSpeedModifier = 2.4D;
                d.sprintEnergyModifier = 6.0D;
                d.emergencyHoverMode = true;
                d.chargerMode = true;
            }

            d = new PackDefaults("te", "fluxpack_te1");
            d.energyCapacity = 1500000;
            d.energyPerTickIn = 800;
            d.energyPerTickOut = 800;
            d.armorReduction = 4;
            d.armorEnergyPerHit = 60;
            d.enchantability = 4;

            d = new PackDefaults("te", "fluxpack_te2");
            d.energyCapacity = 12000000;
            d.energyPerTickIn = 6000;
            d.energyPerTickOut = 6000;
            d.armorReduction = 6;
            d.armorEnergyPerHit = 100;
            d.enchantability = 6;

            d = new PackDefaults("te", "fluxpack_te3");
            d.energyCapacity = 40000000;
            d.energyPerTickIn = 20000;
            d.energyPerTickOut = 20000;
            d.armorReduction = 7;
            d.armorEnergyPerHit = 140;
            d.enchantability = 8;

            d = new PackDefaults("te", "fluxpack_te4");
            d.energyCapacity = 80000000;
            d.energyPerTickIn = 32000;
            d.energyPerTickOut = 32000;
            d.armorReduction = 7;
            d.armorEnergyPerHit = 160;
            d.enchantability = 10;
        }

        if (ModType.MEKANISM.loaded) {
            d = new PackDefaults("mek", "jetpack_mek1");
            d.energyCapacity = 80000;
            d.energyUsage = 32;
            d.energyPerTickIn = 400;
            d.armorReduction = 5;
            d.armorEnergyPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new PackDefaults("mek", "jetpack_mek2");
            d.energyCapacity = 400000;
            d.energyUsage = 50;
            d.energyPerTickIn = 2000;
            d.armorReduction = 6;
            d.armorEnergyPerHit = 100;
            d.enchantability = 8;
            d.speedVertical = 0.3D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.08D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new PackDefaults("mek", "jetpack_mek3");
            d.energyCapacity = 4000000;
            d.energyUsage = 200;
            d.energyPerTickIn = 20000;
            d.armorReduction = 7;
            d.armorEnergyPerHit = 120;
            d.enchantability = 13;
            d.speedVertical = 0.48D;
            d.accelVertical = 0.13D;
            d.speedVerticalHover = 0.34D;
            d.speedVerticalHoverSlow = 0.03D;
            d.speedSideways = 0.14D;
            d.sprintSpeedModifier = 1.3D;
            d.sprintEnergyModifier = 2.5D;
            d.emergencyHoverMode = true;
            d.chargerMode = false;

            d = new PackDefaults("mek", "jetpack_mek4");
            d.energyCapacity = 20000000;
            d.energyUsage = 450;
            d.energyPerTickIn = 50000;
            d.armorReduction = 8;
            d.armorEnergyPerHit = 160;
            d.enchantability = 17;
            d.speedVertical = 0.8D;
            d.accelVertical = 0.14D;
            d.speedVerticalHover = 0.4D;
            d.speedVerticalHoverSlow = 0.005D;
            d.speedSideways = 0.19D;
            d.sprintSpeedModifier = 1.8D;
            d.sprintEnergyModifier = 4.0D;
            d.emergencyHoverMode = true;
            d.chargerMode = false;
        }

        if (ModType.IMMERSIVE_ENGINEERING.loaded) {
            d = new PackDefaults("ie", "jetpack_ie1");
            d.energyCapacity = 80000;
            d.energyUsage = 32;
            d.energyPerTickIn = 400;
            d.armorReduction = 5;
            d.armorEnergyPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new PackDefaults("ie", "jetpack_ie2");
            d.energyCapacity = 400000;
            d.energyUsage = 50;
            d.energyPerTickIn = 2000;
            d.armorReduction = 6;
            d.armorEnergyPerHit = 100;
            d.enchantability = 8;
            d.speedVertical = 0.3D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.08D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new PackDefaults("ie", "jetpack_ie3");
            d.energyCapacity = 4000000;
            d.energyUsage = 200;
            d.energyPerTickIn = 20000;
            d.armorReduction = 7;
            d.armorEnergyPerHit = 120;
            d.enchantability = 13;
            d.speedVertical = 0.48D;
            d.accelVertical = 0.13D;
            d.speedVerticalHover = 0.34D;
            d.speedVerticalHoverSlow = 0.03D;
            d.speedSideways = 0.14D;
            d.sprintSpeedModifier = 1.3D;
            d.sprintEnergyModifier = 2.5D;
            d.emergencyHoverMode = true;
            d.chargerMode = false;
        }

        if (Config.enableIntegrationVanilla) {
            d = new PackDefaults("vanilla", "jetpack_vanilla1");
            d.energyCapacity = 80000;
            d.energyUsage = 32;
            d.energyPerTickIn = 400;
            d.armorReduction = 5;
            d.armorEnergyPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new PackDefaults("vanilla", "jetpack_vanilla2");
            d.energyCapacity = 400000;
            d.energyUsage = 50;
            d.energyPerTickIn = 2000;
            d.armorReduction = 6;
            d.armorEnergyPerHit = 80;
            d.enchantability = 8;
            d.speedVertical = 0.4D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.2D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.1D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new PackDefaults("vanilla", "jetpack_vanilla3");
            d.energyCapacity = 20000000;
            d.energyUsage = 450;
            d.energyPerTickIn = 50000;
            d.armorReduction = 8;
            d.armorEnergyPerHit = 160;
            d.enchantability = 17;
            d.speedVertical = 0.8D;
            d.accelVertical = 0.14D;
            d.speedVerticalHover = 0.4D;
            d.speedVerticalHoverSlow = 0.005D;
            d.speedSideways = 0.19D;
            d.sprintSpeedModifier = 1.8D;
            d.sprintEnergyModifier = 4.0D;
            d.emergencyHoverMode = true;
            d.chargerMode = false;
        }
    }
}