package stormedpanda.simplyjetpacks.item;

import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.config.JetpackDataHolder;

import java.util.EnumSet;

public enum JetpackType {

    POTATO("potato"),

    CREATIVE("creative"),
    CREATIVE_ARMORED("creative_armored"),

    VANILLA1("vanilla1"),
    VANILLA1_ARMORED("vanilla1_armored"),
    VANILLA2("vanilla2"),
    VANILLA2_ARMORED("vanilla2_armored"),
    VANILLA3("vanilla3"),
    VANILLA3_ARMORED("vanilla3_armored"),
    VANILLA4("vanilla4"),
    VANILLA4_ARMORED("vanilla4_armored"),

    MEK1("mek1"),
    MEK1_ARMORED("mek1_armored"),
    MEK2("mek2"),
    MEK2_ARMORED("mek2_armored"),
    MEK3("mek3"),
    MEK3_ARMORED("mek3_armored"),
    MEK4("mek4"),
    MEK4_ARMORED("mek4_armored"),

    TE1("te1"),
    TE1_ARMORED("te1_armored"),
    TE2("te2"),
    TE2_ARMORED("te2_armored"),
    TE3("te3"),
    TE3_ARMORED("te3_armored"),
    TE4("te4"),
    TE4_ARMORED("te4_armored"),

    IE1("ie1"),
    IE1_ARMORED("ie1_armored"),
    IE2("ie2"),
    IE2_ARMORED("ie2_armored"),
    IE3("ie3"),
    IE3_ARMORED("ie3_armored"),
    ;

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
