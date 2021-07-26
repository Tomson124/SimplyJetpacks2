package stormedpanda.simplyjetpacks.item;

import net.minecraft.util.ResourceLocation;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.config.JetpackDataHolder;
import stormedpanda.simplyjetpacks.particle.JetpackParticleType;

import java.util.EnumSet;

public enum JetpackType {

    POTATO("potato"),
    CREATIVE("creative"),
    CREATIVE_ARMORED("creative_armored", "creative", true),

    VANILLA1("vanilla1"),
    VANILLA1_ARMORED("vanilla1_armored", "vanilla1", true),
    VANILLA2("vanilla2"),
    VANILLA2_ARMORED("vanilla2_armored", "vanilla2", true),
    VANILLA3("vanilla3"),
    VANILLA3_ARMORED("vanilla3_armored", "vanilla3", true),
    VANILLA4("vanilla4"),
    VANILLA4_ARMORED("vanilla4_armored", "vanilla4", true),

    MEK1("mek1"),
    MEK1_ARMORED("mek1_armored", "mek1", true),
    MEK2("mek2"),
    MEK2_ARMORED("mek2_armored", "mek2", true),
    MEK3("mek3"),
    MEK3_ARMORED("mek3_armored", "mek3", true),
    MEK4("mek4"),
    MEK4_ARMORED("mek4_armored", "mek4", true),

    TE1("te1"),
    TE1_ARMORED("te1_armored", "te1", true),
    TE2("te2"),
    TE2_ARMORED("te2_armored", "te2", true),
    TE3("te3"),
    TE3_ARMORED("te3_armored", "te3", true),
    TE4("te4"),
    TE4_ARMORED("te4_armored", "te4", true),
    TE5("te5", "te5", true),
    TE5_ARMORED("te5_enderium", "te5", true),

    IE1("ie1"),
    IE1_ARMORED("ie1_armored", "ie1", true),
    IE2("ie2"),
    IE2_ARMORED("ie2_armored", "ie2", true),
    IE3("ie3"),
    IE3_ARMORED("ie3_armored", "ie3", true),
    ;

    protected static final EnumSet<JetpackType> JETPACK_ALL = EnumSet.allOf(JetpackType.class);

    private final String name;
    private final String configKey;
    private final boolean armored;
    private final int platingId;
    private final ResourceLocation armorTexture;

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

    JetpackType(String name, String configKey, boolean armored) {
        this.name = name;
        this.configKey = configKey;
        this.armored = armored;
        this.platingId = 0;
        this.armorTexture = new ResourceLocation(("simplyjetpacks:textures/models/armor/jetpack_" + name + ".png"));
    }

    JetpackType(String name) {
        this.name = name;
        this.configKey = name;
        this.armored = false;
        this.platingId = 0;
        this.armorTexture = new ResourceLocation(("simplyjetpacks:textures/models/armor/jetpack_" + name + ".png"));
    }

    public String getName() {
        return name;
    }

    public String getConfigKey() {
        return configKey;
    }

    public boolean isArmored() {
        return armored;
    }

    public int getPlatingId() {
        return platingId;
    }

    public String getArmorTexture() {
        return armorTexture.toString();
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

    public static void loadAllConfigs() {
        SimplyJetpacks.LOGGER.debug("SJ: loading configs to the jetpack type");
        if (true) {
            for (JetpackType jetpackType : JETPACK_ALL) {
                jetpackType.loadConfig();
            }
        }
    }

    public int getDefaultParticles() {
        if (name.contains("creative")) {
            return JetpackParticleType.RAINBOW.ordinal();
        }
        return JetpackParticleType.DEFAULT.ordinal();
    }

    public void loadConfig() {
        this.energyCapacity = JetpackDataHolder.DEFAULTS.get(this.configKey)._energyCapacity.get();
        this.energyUsage = JetpackDataHolder.DEFAULTS.get(this.configKey)._energyUsage.get();
        this.energyPerTickIn = JetpackDataHolder.DEFAULTS.get(this.configKey)._energyPerTickIn.get();
        this.energyPerTickOut = JetpackDataHolder.DEFAULTS.get(this.configKey)._energyPerTickOut.get();
        this.armorReduction = JetpackDataHolder.DEFAULTS.get(this.configKey)._armorReduction.get();
        this.armorEnergyPerHit = JetpackDataHolder.DEFAULTS.get(this.configKey)._armorEnergyPerHit.get();
        this.enchantability = JetpackDataHolder.DEFAULTS.get(this.configKey)._enchantability.get();
        this.speedVertical = JetpackDataHolder.DEFAULTS.get(this.configKey)._speedVertical.get();
        this.accelVertical = JetpackDataHolder.DEFAULTS.get(this.configKey)._accelVertical.get();
        this.speedVerticalHover = JetpackDataHolder.DEFAULTS.get(this.configKey)._speedVerticalHover.get();
        this.speedVerticalHoverSlow = JetpackDataHolder.DEFAULTS.get(this.configKey)._speedVerticalHoverSlow.get();
        this.speedSideways = JetpackDataHolder.DEFAULTS.get(this.configKey)._speedSideways.get();
        this.sprintSpeedModifier = JetpackDataHolder.DEFAULTS.get(this.configKey)._sprintSpeedModifier.get();
        this.sprintEnergyModifier = JetpackDataHolder.DEFAULTS.get(this.configKey)._sprintEnergyModifier.get();
        this.emergencyHoverMode = JetpackDataHolder.DEFAULTS.get(this.configKey)._emergencyHoverMode.get();
        this.chargerMode = JetpackDataHolder.DEFAULTS.get(this.configKey)._chargerMode.get();
    }
}
