package stormedpanda.simplyjetpacks.config;

import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class TestPack {

    public String name;

    // Base
    public IntValue energyCapacity;
    public IntValue energyUsage;
    public IntValue energyPerTickIn;
    public IntValue energyPerTickOut;
    public IntValue armorReduction;
    public IntValue armorEnergyPerHit;
    public IntValue enchantability;

    // Jetpack
    public DoubleValue speedVertical;
    public DoubleValue accelVertical;
    public DoubleValue speedVerticalHover;
    public DoubleValue speedVerticalHoverSlow;
    public DoubleValue speedSideways;
    public DoubleValue sprintSpeedModifier;
    public DoubleValue sprintEnergyModifier;
    public BooleanValue emergencyHoverMode;
    public BooleanValue chargerMode;

    public TestPack(String name) {
        this.name = name;
    }
}
