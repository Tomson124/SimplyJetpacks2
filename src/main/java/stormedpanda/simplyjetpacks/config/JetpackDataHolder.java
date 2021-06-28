package stormedpanda.simplyjetpacks.config;

import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

import java.util.HashMap;
import java.util.Map;

public class JetpackDataHolder {

    public static final Map<String, JetpackDataHolder> DEFAULTS = new HashMap<>();

    public String name;
    public String mod;

    // actual values
    public int energyCapacity;
    public int energyUsage;
    public int energyPerTickIn;
    public int energyPerTickOut;
    public int armorReduction;
    public int armorEnergyPerHit;
    public int enchantability;
    public double speedVertical;
    public double accelVertical;
    public double speedVerticalHover;
    public double speedVerticalHoverSlow;
    public double speedSideways;
    public double sprintSpeedModifier;
    public double sprintEnergyModifier;
    public boolean emergencyHoverMode;
    public boolean chargerMode;

    // config values
    public IntValue _energyCapacity;
    public IntValue _energyUsage;
    public IntValue _energyPerTickIn;
    public IntValue _energyPerTickOut;
    public IntValue _armorReduction;
    public IntValue _armorEnergyPerHit;
    public IntValue _enchantability;
    public DoubleValue _speedVertical;
    public DoubleValue _accelVertical;
    public DoubleValue _speedVerticalHover;
    public DoubleValue _speedVerticalHoverSlow;
    public DoubleValue _speedSideways;
    public DoubleValue _sprintSpeedModifier;
    public DoubleValue _sprintEnergyModifier;
    public BooleanValue _emergencyHoverMode;
    public BooleanValue _chargerMode;

    public JetpackDataHolder(String name, String mod) {
        this.name = name;
        this.mod = mod;
        DEFAULTS.put(name, this);
    }

    static {
        JetpackDataHolder d = new JetpackDataHolder("potato", "simplyjetpacks");
        d.energyCapacity = 1200;
        d.energyUsage = 45;
        d.speedVertical = 0.9D;
        d.accelVertical = 0.5D;

        d = new JetpackDataHolder("creative", "simplyjetpacks");
        d.energyCapacity = 0;
        d.energyPerTickOut = 0;
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

        d = new JetpackDataHolder("creative_armored", "simplyjetpacks");
        d.energyCapacity = 500;
        d.energyPerTickOut = 0;
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

        d = new JetpackDataHolder("jetpack1", "simplyjetpacks");
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

        d = new JetpackDataHolder("jetpack2", "simplyjetpacks");
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

        d = new JetpackDataHolder("jetpack3", "simplyjetpacks");
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

        d = new JetpackDataHolder("jetpack4", "simplyjetpacks");
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
        d.chargerMode = true;

        d = new JetpackDataHolder("jetpack5", "simplyjetpacks");
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

        d = new JetpackDataHolder("test", "anothermod");
        d.energyCapacity = 1200;
        d.energyUsage = 45;
        d.speedVertical = 0.9D;
        d.accelVertical = 0.5D;

    }
}
