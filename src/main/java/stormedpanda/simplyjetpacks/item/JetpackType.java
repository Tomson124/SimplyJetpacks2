package stormedpanda.simplyjetpacks.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import stormedpanda.simplyjetpacks.config.JetpackDataHolder;
import stormedpanda.simplyjetpacks.particle.JetpackParticleType;

import java.util.EnumSet;

public enum JetpackType {

    POTATO("potato", 1),
    CREATIVE("creative", 5),
    CREATIVE_ARMORED("creative_armored", 5, "creative", true),

    VANILLA1("vanilla1", 1),
    VANILLA1_ARMORED("vanilla1_armored", 1, "vanilla1", true, 0),
    VANILLA2("vanilla2", 2),
    VANILLA2_ARMORED("vanilla2_armored", 2, "vanilla2", true, 1),
    VANILLA3("vanilla3", 3),
    VANILLA3_ARMORED("vanilla3_armored", 3, "vanilla3", true, 2),
    VANILLA4("vanilla4", 4),
    VANILLA4_ARMORED("vanilla4_armored", 4, "vanilla4", true, 3),

    IE1("ie1", 1),
    IE1_ARMORED("ie1_armored", 1, "ie1", true, 4),
    IE2("ie2", 2),
    IE2_ARMORED("ie2_armored", 2, "ie2", true, 5),
    IE3("ie3", 3),
    IE3_ARMORED("ie3_armored", 3, "ie3", true, 6),

    MEK1("mek1", 1),
    MEK1_ARMORED("mek1_armored", 1, "mek1", true, 7),
    MEK2("mek2", 2),
    MEK2_ARMORED("mek2_armored", 2, "mek2", true, 8),
    MEK3("mek3", 3),
    MEK3_ARMORED("mek3_armored", 3, "mek3", true, 9),
    MEK4("mek4", 4),
    MEK4_ARMORED("mek4_armored", 4, "mek4", true, 10),

    TE1("te1", 1),
    TE1_ARMORED("te1_armored", 1, "te1", true, 11),
    TE2("te2", 2),
    TE2_ARMORED("te2_armored", 2, "te2", true, 12),
    TE3("te3", 3),
    TE3_ARMORED("te3_armored", 3, "te3", true, 13),
    TE4("te4", 4),
    TE4_ARMORED("te4_armored", 4, "te4", true, 14),
    TE5("te5", 5, "te5", true),
    TE5_ARMORED("te5_enderium", 5, "te5", true),
    ;

    private static final EnumSet<JetpackType> JETPACK_ALL = EnumSet.allOf(JetpackType.class);

    private final String name;
    private final String configKey;
    private final boolean armored;
    private final int platingId;
    private final ResourceLocation armorTexture;
    private final int tier;

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
    private boolean hoverMode;
    private boolean emergencyHoverMode;
    private boolean chargerMode;

    JetpackType(String name, int tier) {
        this(name, tier, name, false, 0);
    }

    JetpackType(String name, int tier, String configKey, boolean armored) {
        this(name, tier, configKey, armored, 0);
    }

    JetpackType(String name, int tier, String configKey, boolean armored, int platingId) {
        this.name = name;
        this.tier = tier;
        this.configKey = configKey;
        this.armored = armored;
        this.platingId = platingId;
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

    public int getTier() {
        return tier;
    }

    public Rarity getRarity() {
        return switch (tier) {
            case 3 -> Rarity.UNCOMMON;
            case 4 -> Rarity.RARE;
            case 5 -> Rarity.EPIC;
            default -> Rarity.COMMON;
        };
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

    public boolean getHoverMode() {
        return hoverMode;
    }

    public boolean getEmergencyHoverMode() {
        return emergencyHoverMode;
    }

    public boolean getChargerMode() {
        return chargerMode;
    }

    public static void loadAllConfigs() {
        for (JetpackType jetpackType : JETPACK_ALL) {
            jetpackType.loadConfig();
        }
    }

    public static int getDefaultParticles(ItemStack stack) {
        JetpackItem item = (JetpackItem) stack.getItem();
        if (item.isCreative() || item.getJetpackType().getName().equals("potato")) {
            return JetpackParticleType.RAINBOW.ordinal();
        }
        return JetpackParticleType.FLAME.ordinal();
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
        this.hoverMode = JetpackDataHolder.DEFAULTS.get(this.configKey)._hoverMode.get();
        this.emergencyHoverMode = JetpackDataHolder.DEFAULTS.get(this.configKey)._emergencyHoverMode.get();
        this.chargerMode = JetpackDataHolder.DEFAULTS.get(this.configKey)._chargerMode.get();
    }
}
