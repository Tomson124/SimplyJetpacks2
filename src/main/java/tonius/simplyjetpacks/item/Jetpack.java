package tonius.simplyjetpacks.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.config.ConfigWrapper;
import tonius.simplyjetpacks.config.PackDefaults;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.NBTHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

public enum Jetpack implements IStringSerializable {

	JETPACK_CREATIVE("jetpack_creative", 6, "jetpack_creative", EnumRarity.EPIC, ParticleType.RAINBOW, false),
	//JETPACK_POTATO("jetpack_potato", 1, "jetpack_potato", EnumRarity.COMMON, ParticleType.DEFAULT, false),

	// Vanilla
	JETPACK_VANILLA_1("jetpack_vanilla1", 1, "jetpack_vanilla1", EnumRarity.COMMON),
	JETPACK_VANILLA_2("jetpack_vanilla2", 2, "jetpack_vanilla2", EnumRarity.UNCOMMON),
	JETPACK_VANILLA_3("jetpack_vanilla3", 3, "jetpack_vanilla3", EnumRarity.RARE),
	JETPACK_VANILLA_1_ARMORED("jetpack_vanilla1_armored", 1, "jetpack_vanilla1", EnumRarity.COMMON, true),
	JETPACK_VANILLA_2_ARMORED("jetpack_vanilla2_armored", 2, "jetpack_vanilla2", EnumRarity.UNCOMMON, true),
	JETPACK_VANILLA_3_ARMORED("jetpack_vanilla3_armored", 3, "jetpack_vanilla3", EnumRarity.RARE, true),

	// EnderIO
	JETPACK_EIO_1("jetpack_eio1", 1, "jetpack_eio1", EnumRarity.COMMON),
	JETPACK_EIO_2("jetpack_eio2", 2, "jetpack_eio2", EnumRarity.COMMON),
	JETPACK_EIO_3("jetpack_eio3", 3, "jetpack_eio3", EnumRarity.UNCOMMON),
	JETPACK_EIO_4("jetpack_eio4", 4, "jetpack_eio4", EnumRarity.RARE),
	JETPACK_EIO_1_ARMORED("jetpack_eio1_armored", 1, "jetpack_eio1", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_EIO_1.ordinal()),
	JETPACK_EIO_2_ARMORED("jetpack_eio2_armored", 2, "jetpack_eio2", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_EIO_2.ordinal()),
	JETPACK_EIO_3_ARMORED("jetpack_eio3_armored", 3, "jetpack_eio3", EnumRarity.UNCOMMON, true, MetaItemsMods.ARMOR_PLATING_EIO_3.ordinal()),
	JETPACK_EIO_4_ARMORED("jetpack_eio4_armored", 4, "jetpack_eio4", EnumRarity.RARE, true, MetaItemsMods.ARMOR_PLATING_EIO_4.ordinal()),
	JETPLATE_EIO_5("jetpack_eio5", 5, "jetpack_eio5", EnumRarity.EPIC, true),

	// Thermal Expansion
	JETPACK_TE_1("jetpack_te1", 1, "jetpack_te1", EnumRarity.COMMON),
	JETPACK_TE_2("jetpack_te2", 2, "jetpack_te2", EnumRarity.COMMON),
	JETPACK_TE_3("jetpack_te3", 3, "jetpack_te3", EnumRarity.UNCOMMON),
	JETPACK_TE_4("jetpack_te4", 4, "jetpack_te4", EnumRarity.RARE),
	JETPACK_TE_1_ARMORED("jetpack_te1_armored", 1, "jetpack_te1", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_TE_1.ordinal()),
	JETPACK_TE_2_ARMORED("jetpack_te2_armored", 2, "jetpack_te2", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_TE_2.ordinal()),
	JETPACK_TE_3_ARMORED("jetpack_te3_armored", 3, "jetpack_te3", EnumRarity.UNCOMMON, true, MetaItemsMods.ARMOR_PLATING_TE_3.ordinal()),
	JETPACK_TE_4_ARMORED("jetpack_te4_armored", 4, "jetpack_te4", EnumRarity.RARE, true, MetaItemsMods.ARMOR_PLATING_TE_4.ordinal()),
	JETPLATE_TE_5("jetpack_te5", 5, "jetpack_te5", EnumRarity.EPIC, true),
	JETPLATE_TE_5_ENDERIUM("jetpack_te5_enderium", 5, "jetpack_te5_enderium", EnumRarity.EPIC, true),

	// Mekanism
	JETPACK_MEK_1("jetpack_mek1", 1, "jetpack_mek1", EnumRarity.COMMON),
	JETPACK_MEK_2("jetpack_mek2", 2, "jetpack_mek2", EnumRarity.COMMON),
	JETPACK_MEK_3("jetpack_mek3", 3, "jetpack_mek3", EnumRarity.UNCOMMON),
	JETPACK_MEK_4("jetpack_mek4", 4, "jetpack_mek4", EnumRarity.RARE),
	JETPACK_MEK_1_ARMORED("jetpack_mek1_armored", 1, "jetpack_mek1", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_MEK_1.ordinal()),
	JETPACK_MEK_2_ARMORED("jetpack_mek2_armored", 2, "jetpack_mek2", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_MEK_2.ordinal()),
	JETPACK_MEK_3_ARMORED("jetpack_mek3_armored", 3, "jetpack_mek3", EnumRarity.UNCOMMON, true, MetaItemsMods.ARMOR_PLATING_MEK_3.ordinal()),
	JETPACK_MEK_4_ARMORED("jetpack_mek4_armored", 4, "jetpack_mek4", EnumRarity.RARE, true, MetaItemsMods.ARMOR_PLATING_MEK_4.ordinal()),

	// Immersive Engineering
	JETPACK_IE_1("jetpack_ie1", 1, "jetpack_ie1", EnumRarity.COMMON),
	JETPACK_IE_2("jetpack_ie2", 2, "jetpack_ie2", EnumRarity.COMMON),
	JETPACK_IE_3("jetpack_ie3", 3, "jetpack_ie3", EnumRarity.UNCOMMON),
	JETPACK_IE_1_ARMORED("jetpack_ie1_armored", 1, "jetpack_ie1", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_IE_1.ordinal()),
	JETPACK_IE_2_ARMORED("jetpack_ie2_armored", 2, "jetpack_ie2", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_IE_2.ordinal()),
	JETPACK_IE_3_ARMORED("jetpack_ie3_armored", 3, "jetpack_ie3", EnumRarity.UNCOMMON, true, MetaItemsMods.ARMOR_PLATING_IE_3.ordinal());

	protected static final String TAG_PARTICLE = "JetpackParticle";
	public ParticleType defaultParticleType;

	protected final PackDefaults defaults;
	protected static final EnumSet<Jetpack> JETPACKS_ALL = EnumSet.allOf(Jetpack.class);
	protected static final EnumSet<Jetpack> JETPACKS_SJ = EnumSet.of(JETPACK_CREATIVE);
	public static final EnumSet<Jetpack> JETPACKS_VANILLA = EnumSet.range(JETPACK_VANILLA_1, JETPACK_VANILLA_3_ARMORED);
	public static final EnumSet<Jetpack> JETPACKS_EIO = EnumSet.range(JETPACK_EIO_1, JETPLATE_EIO_5);
	public static final EnumSet<Jetpack> JETPACKS_EIO_ARMORED = EnumSet.range(JETPACK_EIO_1_ARMORED, JETPACK_EIO_4_ARMORED);
	public static final EnumSet<Jetpack> JETPACKS_TE = EnumSet.range(JETPACK_TE_1, JETPLATE_TE_5);
	public static final EnumSet<Jetpack> JETPACKS_TE_ARMORED = EnumSet.range(JETPACK_TE_1_ARMORED, JETPACK_TE_4_ARMORED);
	public static final EnumSet<Jetpack> JETPACKS_MEK = EnumSet.range(JETPACK_MEK_1, JETPACK_MEK_4_ARMORED);
	public static final EnumSet<Jetpack> JETPACKS_IE = EnumSet.range(JETPACK_IE_1, JETPACK_IE_3_ARMORED);
	public static final EnumSet<Jetpack> JETPACKS_RR = EnumSet.of(JETPLATE_TE_5_ENDERIUM);

	@Nonnull
	public final String baseName;
	@Nonnull
	public final String unlocalisedName;
	public final int tier;
	public int energyCapacity;
	public int energyPerTickIn;
	public int energyPerTickOut;
	public int armorEnergyPerHit;
	public int armorReduction;
	public int energyUsage;
	public int platingMeta;
	public boolean usesEnergy;
	public boolean isArmored;
	public EnumRarity rarity;
	public PackModelType armorModel = PackModelType.FLAT;

	public double speedVertical;
	public double accelVertical;
	public double speedVerticalHover;
	public double speedVerticalHoverSlow;
	public double speedSideways;
	public double sprintSpeedModifier;
	public double sprintEnergyModifier;
	public boolean emergencyHoverMode;
	public boolean chargerMode;
	public int enchantability;

	@Nonnull
	private final List<String> jetpacks = new ArrayList<>();

	Jetpack(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity, ParticleType defaultParticleType, boolean usesEnergy) {
		this(baseName, tier, defaultConfigKey, rarity);
		this.defaultParticleType = defaultParticleType;
		this.usesEnergy = usesEnergy;
	}

	Jetpack(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity, boolean isArmored) {
		this(baseName, tier, defaultConfigKey, rarity);
		this.isArmored = isArmored;
	}

	Jetpack(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity, boolean isArmored, int platingMeta) {
		this(baseName, tier, defaultConfigKey, rarity);
		this.isArmored = isArmored;
		this.platingMeta = platingMeta;
	}

	Jetpack(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity) {
		this.baseName = baseName;
		this.tier = tier;
		this.defaults = PackDefaults.get(defaultConfigKey);
		this.defaultParticleType = ParticleType.DEFAULT;
		this.unlocalisedName = "item.simplyjetpacks." + baseName;
		this.jetpacks.add(baseName);
		this.usesEnergy = true;
		this.isArmored = false;
		this.rarity = rarity;
		this.setArmorModel(PackModelType.JETPACK);
	}

	@Nonnull
	public String getBaseName() {
		return baseName;
	}

	public int getTier() {
		return tier;
	}

	public int getEnergyCapacity() {
		return energyCapacity;
	}

	public int getEnergyPerTickIn() {
		return energyPerTickIn;
	}

	public int getEnergyPerTickOut() {
		return energyPerTickOut;
	}

	public int getArmorEnergyPerHit() {
		return armorEnergyPerHit;
	}

	public int getArmorReduction() {
		return armorReduction;
	}

	public int getEnergyUsage() {
		return energyUsage;
	}

	@Nonnull
	public ItemStack getStackJetpack() {
		return new ItemStack(ModItems.itemJetpack, 1, ordinal());
	}

	// TODO: Investigate this.
	@Nonnull
	public List<String> getJetpacks() {
		return jetpacks;
	}

	@Override
	public String getName() {
		return baseName.toLowerCase(Locale.ENGLISH);
	}

	public EnumRarity getRarity() {
		return rarity;
	}

	public boolean getIsArmored() {
		return isArmored;
	}

	public int getPlatingMeta() {
		return platingMeta;
	}

	@Nonnull
	public static Jetpack getTypeFromMeta(int meta) {
		return values()[meta >= 0 && meta < values().length ? meta : 0];
	}

	public static int getMetaFromType(Jetpack value) {
		return value.ordinal();
	}

	public ParticleType getParticleType(ItemStack stack) {
		if (stack.getTagCompound() != null && NBTHelper.keyExists(stack, TAG_PARTICLE)) {
			int particle = NBTHelper.getInt(stack, TAG_PARTICLE, this.defaultParticleType.ordinal());
			ParticleType particleType = ParticleType.values()[particle];
			if (particleType != null) {
				return particleType;
			}
		}
		NBTHelper.setInt(stack, TAG_PARTICLE, this.defaultParticleType.ordinal());
		return this.defaultParticleType;
	}

	public Jetpack setArmorModel(PackModelType armorModel) {
		this.armorModel = armorModel;
		return this;
	}

	public boolean getGlow() {
		return this.baseName.contains("creative");
	}

	public ParticleType getDisplayParticleType(ItemStack stack, ItemJetpack item, EntityLivingBase user) {
		boolean flyKeyDown = SyncHandler.isFlyKeyDown(user);
		if (item.isOn(stack) && item.getEnergyStored(stack) > 0 && (flyKeyDown || item.isHoverModeOn(stack) && !user.onGround && user.motionY < 0)) {
			return this.getParticleType(stack);
		}
		return null;
	}

	public static void loadAllConfigs(ConfigWrapper config) {
		for (Jetpack pack : JETPACKS_SJ) {
			pack.loadConfig(config);
		}
		if (ModItems.integrateVanilla){
			for (Jetpack pack : JETPACKS_VANILLA) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateEIO){
			for (Jetpack pack : JETPACKS_EIO) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateTE) {
			for (Jetpack pack : JETPACKS_TE) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateMek){
			for (Jetpack pack : JETPACKS_MEK) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateIE){
			for (Jetpack pack : JETPACKS_IE) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateRR){
			for (Jetpack pack : JETPACKS_RR) {
				pack.loadConfig(config);
			}
		}
	}

	protected void loadConfig(ConfigWrapper config) {
		if (this.defaults.energyCapacity != null) {
			this.energyCapacity = config.getIntS(this.defaults.section.category, "energyCapacity", "tuning", this.defaults.energyCapacity, 1, null, false, "The maximum amount of energy that this pack can hold.");
		}
		if (this.defaults.energyUsage != null) {
			this.energyUsage = config.getIntS(this.defaults.section.category, "energyUsage", "tuning", this.defaults.energyUsage, 0, null, false, "The amount of energy that this Jetpack/Fluxpack uses every tick, when being used.");
		}
		if (this.defaults.energyPerTickIn != null) {
			this.energyPerTickIn = config.getIntS(this.defaults.section.category, "energyPerTickIn", "tuning", this.defaults.energyPerTickIn, 0, null, false, "The amount of energy that can be inserted into this Jetpack/Fluxpack per tick from external sources.");
		}
		if (this.defaults.energyPerTickOut != null) {
			this.energyPerTickOut = config.getIntS(this.defaults.section.category, "energyPerTickOut", "tuning", this.defaults.energyPerTickOut, 0, null, false, "The amount of energy that can be extracted from this Jetpack/Fluxpack per tick by external sources. Also determines how quickly Jetpacks/Fluxpacks can charge other items.");
		}
		if (this.defaults.armorReduction != null) {
			this.armorReduction = config.getIntS(this.defaults.section.category, "armorReduction", "tuning", this.defaults.armorReduction, 0, 20, false, "How well this Jetpack/Fluxpack can protect the user from damage, if armored. The higher the value, the stronger the armor will be.");
		}
		if (this.defaults.armorEnergyPerHit != null) {
			this.armorEnergyPerHit = config.getIntS(this.defaults.section.category, "armorEnergyPerHit", "tuning", this.defaults.armorEnergyPerHit, 0, null, false, "How much energy is lost from this Jetpack/Fluxpack when the user is hit, if armored.");
		}
		if (this.defaults.speedVertical != null) {
			this.speedVertical = config.getDoubleS(this.defaults.section.category, "speedVertical", "tuning", this.defaults.speedVertical, 0D, null, false, "The maximum vertical speed of this Jetpack when flying.");
		}
		if (this.defaults.accelVertical != null) {
			this.accelVertical = config.getDoubleS(this.defaults.section.category, "accelVertical", "tuning", this.defaults.accelVertical, 0D, null, false, "The vertical acceleration of this Jetpack when flying, every tick, this amount of vertical speed will be added until maximum speed is reached.");
		}
		if (this.defaults.speedVerticalHover != null) {
			this.speedVerticalHover = config.getDoubleS(this.defaults.section.category, "speedVerticalHover", "tuning", this.defaults.speedVerticalHover, 0D, null, false, "The maximum vertical speed of this Jetpack when flying in Hover Mode.");
		}
		if (this.defaults.speedVerticalHoverSlow != null) {
			this.speedVerticalHoverSlow = config.getDoubleS(this.defaults.section.category, "speedVerticalHoverSlow", "tuning", this.defaults.speedVerticalHoverSlow, 0D, null, false, "The maximum vertical speed of this Jetpack when slowly descending in Hover Mode.");
		}
		if (this.defaults.speedSideways != null) {
			this.speedSideways = config.getDoubleS(this.defaults.section.category, "speedSideways", "tuning", this.defaults.speedSideways, 0D, null, false, "The speed of this Jetpack when flying sideways. This is mostly noticeable in Hover Mode.");
		}
		if (this.defaults.sprintSpeedModifier != null) {
			this.sprintSpeedModifier = config.getDoubleS(this.defaults.section.category, "sprintSpeedModifier", "tuning", this.defaults.sprintSpeedModifier, 0D, null, false, "How much faster this Jetpack will fly forward when sprinting. Setting this to 1.0 will make sprinting have no effect apart from the added speed from vanilla.");
		}
		if (this.defaults.sprintEnergyModifier != null) {
			this.sprintEnergyModifier = config.getDoubleS(this.defaults.section.category, "sprintEnergyModifier", "tuning", this.defaults.sprintEnergyModifier, 0D, null, false, "How much more energy this Jetpack will use when sprinting. Setting this to 1.0 will make sprinting have no effect on energy usage.");
		}
		if (this.defaults.emergencyHoverMode != null) {
			this.emergencyHoverMode = config.getBooleanS(this.defaults.section.category, "emergencyHoverMode", "tuning", this.defaults.emergencyHoverMode, false, "When enabled, this Jetpack will be able to activate Hover Mode automatically when the wearer is about to die from a fall.");
		}
		if (this.defaults.chargerMode != null) {
			this.chargerMode = config.getBooleanS(this.defaults.section.category, "chargerMode", "tuning", this.defaults.chargerMode, false, "When enabled, this Jetpack will be able to activate Charger Mode.");
		}
		if (this.defaults.enchantability != null) {
			this.enchantability = config.getIntS(this.defaults.section.category, "enchantability", "tuning", this.defaults.enchantability, 0, null, false, "The enchantability of this Jetpack/Fluxpack. If set to 0, no enchantments can be applied.");
		}
	}
}
