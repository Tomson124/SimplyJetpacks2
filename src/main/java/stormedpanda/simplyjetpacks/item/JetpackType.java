package stormedpanda.simplyjetpacks.item;

import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.config.JetpackDataHolder;

import java.util.EnumSet;

public enum JetpackType {

    CREATIVE("creative"),
    CREATIVE_ARMORED("creative_armored");

    protected static final EnumSet<JetpackType> JETPACK_ALL = EnumSet.allOf(JetpackType.class);

    private final String name;
    private int platingId;

    private int energyCapacity;
    private int energyUsage;
    private int energyPerTickIn;
    private int energyPerTickOut;
    private int armorReduction;
    private int armorEnergyPerHit;
    private int enchantability;

    private double speedVertical;
    private double accelVertical;
    private double speedVerticalHover;
    private double speedVerticalHoverSlow;
    private double speedSideways;
    private double sprintSpeedModifier;
    private double sprintEnergyModifier;
    private boolean emergencyHoverMode;
    private boolean chargerMode;

    JetpackType(String name) {
        this.name = name;
        this.platingId = 0;
    }

    public String getName() {
        return name;
    }

    public int getPlatingId() {
        return platingId;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    public int getEnergyUsage() {
        return energyUsage;
    }

    public int getEnergyPerTickIn() {
        return energyPerTickIn;
    }

    public int getEnergyPerTickOut() {
        return energyPerTickOut;
    }

    public int getArmorReduction() {
        return armorReduction;
    }

    public int getArmorEnergyPerHit() {
        return armorEnergyPerHit;
    }

    public int getEnchantability() {
        return enchantability;
    }

    public double getSpeedVertical() {
        return speedVertical;
    }

    public double getAccelVertical() {
        return accelVertical;
    }

    public double getSpeedVerticalHover() {
        return speedVerticalHover;
    }

    public double getSpeedVerticalHoverSlow() {
        return speedVerticalHoverSlow;
    }

    public double getSpeedSideways() {
        return speedSideways;
    }

    public double getSprintSpeedModifier() {
        return sprintSpeedModifier;
    }

    public double getSprintEnergyModifier() {
        return sprintEnergyModifier;
    }

    public boolean getEmergencyHoverMode() {
        return emergencyHoverMode;
    }

    public boolean getChargerMode() {
        return chargerMode;
    }

    public boolean isArmored() {
        return this.name.contains("_armored");
    }

    public static void LoadAllConfigs() {
        SimplyJetpacks.LOGGER.debug("SJ: loading configs to the jetpack type");
        if (true) {
            for (JetpackType jetpackType : JETPACK_ALL) {
                jetpackType.LoadConfig();
            }
        }
    }

    public void LoadConfig() {
        this.energyCapacity = JetpackDataHolder.DEFAULTS.get(this.name)._energyCapacity.get();
        this.energyUsage = JetpackDataHolder.DEFAULTS.get(this.name)._energyUsage.get();
        this.energyPerTickIn = JetpackDataHolder.DEFAULTS.get(this.name)._energyPerTickIn.get();
        this.energyPerTickOut = JetpackDataHolder.DEFAULTS.get(this.name)._energyPerTickOut.get();
        this.armorReduction = JetpackDataHolder.DEFAULTS.get(this.name)._armorReduction.get();
        this.armorEnergyPerHit = JetpackDataHolder.DEFAULTS.get(this.name)._armorEnergyPerHit.get();
        this.enchantability = JetpackDataHolder.DEFAULTS.get(this.name)._enchantability.get();
        this.speedVertical = JetpackDataHolder.DEFAULTS.get(this.name)._speedVertical.get();
        this.accelVertical = JetpackDataHolder.DEFAULTS.get(this.name)._accelVertical.get();
        this.speedVerticalHover = JetpackDataHolder.DEFAULTS.get(this.name)._speedVerticalHover.get();
        this.speedVerticalHoverSlow = JetpackDataHolder.DEFAULTS.get(this.name)._speedVerticalHoverSlow.get();
        this.speedSideways = JetpackDataHolder.DEFAULTS.get(this.name)._speedSideways.get();
        this.sprintSpeedModifier = JetpackDataHolder.DEFAULTS.get(this.name)._sprintSpeedModifier.get();
        this.sprintEnergyModifier = JetpackDataHolder.DEFAULTS.get(this.name)._sprintEnergyModifier.get();
        this.emergencyHoverMode = JetpackDataHolder.DEFAULTS.get(this.name)._emergencyHoverMode.get();
        this.chargerMode = JetpackDataHolder.DEFAULTS.get(this.name)._chargerMode.get();
    }
}
