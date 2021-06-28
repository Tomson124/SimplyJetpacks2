package stormedpanda.simplyjetpacks.item;

import stormedpanda.simplyjetpacks.config.JetpackDataHolder;

import java.util.EnumSet;

public enum JetpackType {

    CREATIVE("creative"),
    CREATIVE_ARMORED("creative_armored");

    protected static final EnumSet<JetpackType> JETPACK_ALL = EnumSet.allOf(JetpackType.class);

    private final String name;
    
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
    }

    public String getName() {
        return name;
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

    public static void LoadAllConfigs() {
        if (true) {
            for (JetpackType jetpackType : JETPACK_ALL) {
                jetpackType.LoadConfig();
            }
        }
    }

    public void LoadConfig() {
        this.energyCapacity = JetpackDataHolder.DEFAULTS.get(this.name).energyCapacity;
        this.energyUsage = JetpackDataHolder.DEFAULTS.get(this.name).energyUsage;
        this.energyPerTickIn = JetpackDataHolder.DEFAULTS.get(this.name).energyPerTickIn;
        this.energyPerTickOut = JetpackDataHolder.DEFAULTS.get(this.name).energyPerTickOut;
        this.armorReduction = JetpackDataHolder.DEFAULTS.get(this.name).armorReduction;
        this.armorEnergyPerHit = JetpackDataHolder.DEFAULTS.get(this.name).armorEnergyPerHit;
        this.enchantability = JetpackDataHolder.DEFAULTS.get(this.name).enchantability;
        this.speedVertical = JetpackDataHolder.DEFAULTS.get(this.name).speedVertical;
        this.accelVertical = JetpackDataHolder.DEFAULTS.get(this.name).accelVertical;
        this.speedVerticalHover = JetpackDataHolder.DEFAULTS.get(this.name).speedVerticalHover;
        this.speedVerticalHoverSlow = JetpackDataHolder.DEFAULTS.get(this.name).speedVerticalHoverSlow;
        this.speedSideways = JetpackDataHolder.DEFAULTS.get(this.name).speedSideways;
        this.sprintSpeedModifier = JetpackDataHolder.DEFAULTS.get(this.name).sprintSpeedModifier;
        this.sprintEnergyModifier = JetpackDataHolder.DEFAULTS.get(this.name).sprintEnergyModifier;
        this.emergencyHoverMode = JetpackDataHolder.DEFAULTS.get(this.name).emergencyHoverMode;
        this.chargerMode = JetpackDataHolder.DEFAULTS.get(this.name).chargerMode;
    }
}
