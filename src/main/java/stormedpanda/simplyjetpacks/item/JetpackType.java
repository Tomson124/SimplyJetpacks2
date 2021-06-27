package stormedpanda.simplyjetpacks.item;

import stormedpanda.simplyjetpacks.config.NewJetpackConfig;

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
        this.energyCapacity = NewJetpackConfig.packs.get("jetpack_" + this.name).energyCapacity.get();
        this.energyUsage = NewJetpackConfig.packs.get("jetpack_" + this.name).energyUsage.get();
        this.energyPerTickIn = NewJetpackConfig.packs.get("jetpack_" + this.name).energyPerTickIn.get();
        this.energyPerTickOut = NewJetpackConfig.packs.get("jetpack_" + this.name).energyPerTickOut.get();
        this.armorReduction = NewJetpackConfig.packs.get("jetpack_" + this.name).armorReduction.get();
        this.armorEnergyPerHit = NewJetpackConfig.packs.get("jetpack_" + this.name).armorEnergyPerHit.get();
        this.enchantability = NewJetpackConfig.packs.get("jetpack_" + this.name).enchantability.get();

        this.speedVertical = NewJetpackConfig.packs.get("jetpack_" + this.name).speedVertical.get();
        this.accelVertical = NewJetpackConfig.packs.get("jetpack_" + this.name).accelVertical.get();
        this.speedVerticalHover = NewJetpackConfig.packs.get("jetpack_" + this.name).speedVerticalHover.get();
        this.speedVerticalHoverSlow = NewJetpackConfig.packs.get("jetpack_" + this.name).speedVerticalHoverSlow.get();
        this.speedSideways = NewJetpackConfig.packs.get("jetpack_" + this.name).speedSideways.get();
        this.sprintSpeedModifier = NewJetpackConfig.packs.get("jetpack_" + this.name).sprintSpeedModifier.get();
        this.sprintEnergyModifier = NewJetpackConfig.packs.get("jetpack_" + this.name).sprintEnergyModifier.get();
        this.emergencyHoverMode = NewJetpackConfig.packs.get("jetpack_" + this.name).emergencyHoverMode.get();
        this.chargerMode = NewJetpackConfig.packs.get("jetpack_" + this.name).chargerMode.get();
    }
}
