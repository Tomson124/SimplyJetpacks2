package stormedpanda.simplyjetpacks.items;

import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.client.particle.JetpackParticleType;
import stormedpanda.simplyjetpacks.config.JetpackConfigDefaults;
import stormedpanda.simplyjetpacks.integration.IntegrationList;
import stormedpanda.simplyjetpacks.lists.ArmorMaterialList;
import stormedpanda.simplyjetpacks.util.NBTHelper;

import java.util.EnumSet;

public enum JetpackType {
    CREATIVE("jetpack_creative", 6, "jetpackCreative", JetpackParticleType.RAINBOW),
    CREATIVE_ARMORED("jetpack_creative_armored", 6, "jetpackCreative", JetpackParticleType.RAINBOW, true),

    VANILLA1("jetpack_vanilla1", 1, "jetpack1"),
    VANILLA1_ARMORED("jetpack_vanilla1_armored", 1, "jetpack1", true, 0),
    VANILLA2("jetpack_vanilla2", 2, "jetpack2"),
    VANILLA2_ARMORED("jetpack_vanilla2_armored", 2, "jetpack2", true, 1),
    VANILLA3("jetpack_vanilla3", 3, "jetpack3"),
    VANILLA3_ARMORED("jetpack_vanilla3_armored", 3, "jetpack3", true, 2),
    VANILLA4("jetpack_vanilla4", 4, "jetpack4"),
    VANILLA4_ARMORED("jetpack_vanilla4_armored", 4, "jetpack4", true, 3),

    IE1("jetpack_ie1", 1, "jetpack1"),
    IE1_ARMORED("jetpack_ie1_armored", 1, "jetpack1", true, 4),
    IE2("jetpack_ie2", 2, "jetpack2"),
    IE2_ARMORED("jetpack_ie2_armored", 2, "jetpack2", true, 5),
    IE3("jetpack_ie3", 3, "jetpack3"),
    IE3_ARMORED("jetpack_ie3_armored", 3, "jetpack3", true, 6),

    MEK1("jetpack_mek1", 1, "jetpack1"),
    MEK1_ARMORED("jetpack_mek1_armored", 1, "jetpack1", true, 7),
    MEK2("jetpack_mek2", 2, "jetpack2"),
    MEK2_ARMORED("jetpack_mek2_armored", 2, "jetpack2", true, 8),
    MEK3("jetpack_mek3", 3, "jetpack3"),
    MEK3_ARMORED("jetpack_mek3_armored", 3, "jetpack3", true, 9),
    MEK4("jetpack_mek4", 4, "jetpack4"),
    MEK4_ARMORED("jetpack_mek4_armored", 4, "jetpack4", true, 10);

    protected static final EnumSet<JetpackType> JETPACK_ALL = EnumSet.allOf(JetpackType.class);
    public static final EnumSet<JetpackType> JETPACK_SJ = EnumSet.range(CREATIVE, CREATIVE_ARMORED);
    public static final EnumSet<JetpackType> JETPACK_VANILLA = EnumSet.range(VANILLA1, VANILLA4_ARMORED);
    public static final EnumSet<JetpackType> JETPACK_IE = EnumSet.range(IE1, IE3_ARMORED);
    public static final EnumSet<JetpackType> JETPACK_MEK = EnumSet.range(MEK1, MEK4_ARMORED);

    private final String name;
    private final int tier;
    private final ResourceLocation armorTexture;
    private boolean isArmored;
    private int platingID;
    private final Item.Properties properties;

    // Configurations:
    public final JetpackConfigDefaults defaults;
    //public int energyCapacity;
    //public int energyPerTickIn;
    //public int energyPerTickOut;
    private int capacity;
    private int maxReceive;
    private int maxExtract;
    private int enchantability;
    public int armorEnergyPerHit;
    public int armorReduction;
    public int energyUsage;
    public double speedVertical;
    public double accelVertical;
    public double speedVerticalHover;
    public double speedVerticalHoverSlow;
    public double speedSideways;
    public double sprintSpeedModifier;
    public double sprintEnergyModifier;
    public boolean emergencyHoverMode;
    public boolean chargerMode;

    public JetpackParticleType particleType;

    JetpackType(String name, int tier, String defaultConfigKey, JetpackParticleType particleType, boolean isArmored) {
        this(name, tier, defaultConfigKey);
        this.particleType = particleType;
        this.isArmored = isArmored;
    }

    JetpackType(String name, int tier, String defaultConfigKey, JetpackParticleType particleType) {
        this(name, tier, defaultConfigKey);
        this.particleType = particleType;
    }

    JetpackType(String name, int tier, String defaultConfigKey, boolean isArmored) {
        this(name, tier, defaultConfigKey);
        this.isArmored = isArmored;
    }

    JetpackType(String name, int tier, String defaultConfigKey, boolean isArmored, int platingID ) {
        this(name, tier, defaultConfigKey);
        this.isArmored = isArmored;
        this.platingID = platingID;
    }

    JetpackType(String name, int tier, String defaultConfigKey) {
        this.name = name;
        this.tier = tier;
        this.armorTexture = new ResourceLocation(("simplyjetpacks:textures/models/armor/" + name + ".png"));
        this.isArmored = false;
        this.properties = new Item.Properties().group(SimplyJetpacks.tabSimplyJetpacks).maxStackSize(1);
        this.defaults = JetpackConfigDefaults.get(defaultConfigKey);
        this.particleType = JetpackParticleType.DEFAULT;
    }

    public String getName() {
        return name;
    }

    public int getTier() {
        return tier;
    }

    public Rarity getRarity() {
        switch (tier) {
            case 2:
                return Rarity.UNCOMMON;
            case 3:
                return Rarity.RARE;
            case 4:
                return Rarity.EPIC;
            default:
                return Rarity.COMMON;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxReceive() {
        return maxReceive;
    }

    public int getMaxExtract() {
        return maxExtract;
    }

    public int getEnergyUsage() {
        return energyUsage;
    }

    public double getSpeedVertical() {
        return speedVertical;
    }

    public double getAccelVertical() {
        return accelVertical;
    }

    public double getSpeedVerticalHoverSlow() {
        return speedVerticalHoverSlow;
    }

    public double getSpeedVerticalHover() {
        return speedVerticalHover;
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

    public boolean canEHover() {
        return emergencyHoverMode;
    }
    public boolean canCharge() {
        return chargerMode;
    }

    public String getArmorTexture() {
        return armorTexture.toString();
    }

    public IArmorMaterial getArmorMaterial() {
        //ArmorMaterialList.setArmorReduction(ArmorMaterialList.JETPACK_ARMORED, getArmorReduction());
        //return isArmored ? ArmorMaterialList.JETPACK_ARMORED : ArmorMaterialList.JETPACK;
        ArmorMaterialList armorMaterial = isArmored ? ArmorMaterialList.JETPACK_ARMORED : ArmorMaterialList.JETPACK;
        ArmorMaterialList.setStats(armorMaterial, isArmored, getEnchantability(), getArmorReduction());
        return armorMaterial;
    }

    public boolean getIsArmored() {
        return isArmored;
    }

    public int getArmorReduction() {
        return armorReduction;
    }

    public int getEnchantability() {
        return enchantability;
    }

    public Item.Properties getProperties() {
        return properties;
    }

    public int getPlatingID() {
        return platingID;
    }

    public JetpackParticleType getParticleType(ItemStack stack) {
        if (stack.getTag() != null && NBTHelper.hasKey(stack, JetpackItem.TAG_PARTICLE)) {
            int particle = NBTHelper.getInt(stack, JetpackItem.TAG_PARTICLE);//, particleType.ordinal());
            JetpackParticleType particleType = JetpackParticleType.values()[particle];
            if (particleType != null) {
                return particleType;
            }
        }
        NBTHelper.setInt(stack, JetpackItem.TAG_PARTICLE, particleType.ordinal());
        return this.particleType;
    }

    public static void loadAllConfigs() {
/*        for (JetpackType jetpackType : JETPACK_ALL) {
            jetpackType.loadJetpackConfigurations();
        }*/
        for (JetpackType jetpackType : JETPACK_SJ) {
            jetpackType.loadJetpackConfigurations();
        }
        if (IntegrationList.integrateVanilla) {
            for (JetpackType jetpackType : JETPACK_VANILLA) {
                jetpackType.loadJetpackConfigurations();
            }
        }
        if (IntegrationList.integrateImmersiveEngineering) {
            for (JetpackType jetpackType : JETPACK_IE) {
                jetpackType.loadJetpackConfigurations();
            }
        }
        if (IntegrationList.integrateMekanism) {
            for (JetpackType jetpackType : JETPACK_MEK) {
                jetpackType.loadJetpackConfigurations();
            }
        }
    }

    protected void loadJetpackConfigurations() {
        this.capacity = this.defaults.energyCapacity;
        this.maxReceive = this.defaults.energyPerTickIn;
        this.maxExtract = this.defaults.energyPerTickOut;
        this.enchantability = this.defaults.enchantability;
        this.armorEnergyPerHit = this.defaults.armorEnergyPerHit;
        this.armorReduction = this.defaults.armorReduction;
        this.energyUsage = this.defaults.energyUsage;
        this.speedVertical = this.defaults.speedVertical;
        this.accelVertical = this.defaults.accelVertical;
        this.speedVerticalHover = this.defaults.speedVerticalHover;
        this.speedVerticalHoverSlow = this.defaults.speedVerticalHoverSlow;
        this.speedSideways = this.defaults.speedSideways;
        this.sprintSpeedModifier = this.defaults.sprintSpeedModifier;
        this.sprintEnergyModifier = this.defaults.sprintEnergyModifier;
        this.emergencyHoverMode = this.defaults.emergencyHoverMode;
        this.chargerMode = this.defaults.chargerMode;
    }
}
