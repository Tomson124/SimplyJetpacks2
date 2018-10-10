package tonius.simplyjetpacks.item;

import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.config.PackDefaults;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.NBTHelper;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.config.Configuration;

import javax.annotation.Nonnull;
import java.util.*;

public enum Packs implements IStringSerializable {
	CREATIVE_JETPACK("jetpack_Creative", 6, "jetpackCreative", EnumRarity.EPIC, ParticleType.RAINBOW_SMOKE, false),
	JETPACK_TEST("jetpack_Test", 1, "jetpackEIO1", EnumRarity.COMMON),
	//POTATO_JETPACK("jetpack_Potato", 1, "jetpackPotato", EnumRarity.COMMON, ParticleType.DEFAULT, false),

	//EnderIO
	JETPACK_EIO_1("jetpack_EIO1", 1, "jetpackEIO1", EnumRarity.COMMON),
	JETPACK_EIO_2("jetpack_EIO2", 2, "jetpackEIO2", EnumRarity.COMMON),
	JETPACK_EIO_3("jetpack_EIO3", 3, "jetpackEIO3", EnumRarity.UNCOMMON),
	JETPACK_EIO_4("jetpack_EIO4", 4, "jetpackEIO4", EnumRarity.RARE),
	JETPACK_EIO_1_ARMORED("jetpack_EIO1_Armored", 1, "jetpackEIO1", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_EIO_1.ordinal()),
	JETPACK_EIO_2_ARMORED("jetpack_EIO2_Armored", 2, "jetpackEIO2", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_EIO_2.ordinal()),
	JETPACK_EIO_3_ARMORED("jetpack_EIO3_Armored", 3, "jetpackEIO3", EnumRarity.UNCOMMON, true, MetaItemsMods.ARMOR_PLATING_EIO_3.ordinal()),
	JETPACK_EIO_4_ARMORED("jetpack_EIO4_Armored", 4, "jetpackEIO4", EnumRarity.RARE, true, MetaItemsMods.ARMOR_PLATING_EIO_4.ordinal()),
	JETPLATE_EIO_5("jetpack_EIO5", 5, "jetpackEIO5", EnumRarity.EPIC, true),

	//ThermalExpansion
	JETPACK_TE_1("jetpack_TE1", 1, "jetpackTE1", EnumRarity.COMMON),
	JETPACK_TE_2("jetpack_TE2", 2, "jetpackTE2", EnumRarity.COMMON),
	JETPACK_TE_3("jetpack_TE3", 3, "jetpackTE3", EnumRarity.UNCOMMON),
	JETPACK_TE_4("jetpack_TE4", 4, "jetpackTE4", EnumRarity.RARE),
	JETPACK_TE_1_ARMORED("jetpack_TE1_Armored", 1, "jetpackTE1", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_TE_1.ordinal()),
	JETPACK_TE_2_ARMORED("jetpack_TE2_Armored", 2, "jetpackTE2", EnumRarity.COMMON, true, MetaItemsMods.ARMOR_PLATING_TE_2.ordinal()),
	JETPACK_TE_3_ARMORED("jetpack_TE3_Armored", 3, "jetpackTE3", EnumRarity.UNCOMMON, true, MetaItemsMods.ARMOR_PLATING_TE_3.ordinal()),
	JETPACK_TE_4_ARMORED("jetpack_TE4_Armored", 4, "jetpackTE4", EnumRarity.RARE, true, MetaItemsMods.ARMOR_PLATING_TE_4.ordinal()),
	JETPLATE_TE_5("jetpack_TE5", 5, "jetpackTE5", EnumRarity.EPIC, true),
	JETPLATE_TE_5_ENDERIUM("jetpack_TE5_enderium", 5, "jetpackTE5enderium", EnumRarity.EPIC, true),
	
	JETPACK_VANILLA_1("jetpack_Vanilla1", 1, "jetpackVanilla1", EnumRarity.COMMON),
	JETPACK_VANILLA_2("jetpack_Vanilla2", 2, "jetpackVanilla2", EnumRarity.UNCOMMON),
	JETPACK_VANILLA_3("jetpack_Vanilla3", 3, "jetpackVanilla3", EnumRarity.RARE),


	CREATIVE_FLUXPACK("fluxPack_creative", 6, "fluxPackCreative", EnumRarity.EPIC, false),

	//EnderIO
	FLUXPACK_EIO1("fluxPack_EIO1", 1, "fluxPackEIO1", EnumRarity.COMMON),
	FLUXPACK_EIO2("fluxPack_EIO2", 2, "fluxPackEIO2", EnumRarity.UNCOMMON),
	FLUXPACK_EIO3("fluxPack_EIO3", 3, "fluxPackEIO3", EnumRarity.RARE),
	FLUXPACK_EIO2_ARMORED("fluxPack_EIO2_Armored", 2, "fluxPackEIO2", EnumRarity.UNCOMMON, true, MetaItemsMods.ARMOR_PLATING_EIO_2.ordinal()),
	FLUXPACK_EIO3_ARMORED("fluxPack_EIO3_Armored", 3, "fluxPackEIO3", EnumRarity.RARE, true, MetaItemsMods.ARMOR_PLATING_EIO_4.ordinal()),

	//Thermal Expansioin
	FLUXPACK_TE1("fluxPack_TE1", 1, "fluxPackTE1", EnumRarity.COMMON),
	FLUXPACK_TE2("fluxPack_TE2", 2, "fluxPackTE2", EnumRarity.UNCOMMON),
	FLUXPACK_TE3("fluxPack_TE3", 3, "fluxPackTE3", EnumRarity.RARE),
	FLUXPACK_TE2_ARMORED("fluxPack_TE2_Armored", 2, "fluxPackTE2", EnumRarity.UNCOMMON, true, MetaItemsMods.ARMOR_PLATING_TE_2.ordinal()),
	FLUXPACK_TE3_ARMORED("fluxPack_TE3_Armored", 3, "fluxPackTE3", EnumRarity.RARE, true, MetaItemsMods.ARMOR_PLATING_TE_4.ordinal());

	protected final PackDefaults defaults;
	protected static final EnumSet<Packs> ALL_PACKS = EnumSet.allOf(Packs.class);
	protected static final EnumSet<Packs> PACKS_SJ = EnumSet.of(CREATIVE_JETPACK, JETPACK_TEST);
	public static final EnumSet<Packs> PACKS_EIO = EnumSet.range(JETPACK_EIO_1, JETPLATE_EIO_5);
	public static final EnumSet<Packs> PACKS_TE = EnumSet.range(JETPACK_TE_1, JETPLATE_TE_5);
	public static final EnumSet<Packs> PACKS_TE_ARMORED = EnumSet.range(JETPACK_TE_1_ARMORED, JETPACK_TE_4_ARMORED);
	public static final EnumSet<Packs> PACKS_RR = EnumSet.of(JETPLATE_TE_5_ENDERIUM);
	public static final EnumSet<Packs> PACKS_VANILLA = EnumSet.range(JETPACK_VANILLA_1, JETPACK_VANILLA_3);

	protected static final String TAG_PARTICLE = "JetpackParticleType";
	public ParticleType defaultParticleType = ParticleType.DEFAULT;
	public PackModelType armorModel = PackModelType.FLAT;

	public final @Nonnull String baseName;
	public final @Nonnull String unlocalisedName;
	public final int tier;
	public int fuelCapacity;
	public int fuelPerTickIn;
	public int fuelPerTickOut;
	public int armorFuelPerHit;
	public int armorReduction;
	public int fuelUsage;

	public boolean isArmored;
	public int platingMeta;

	public boolean usesFuel;
	public EnumRarity rarity;

	public double speedVertical;
	public double accelVertical;
	public double speedVerticalHover;
	public double speedVerticalHoverSlow;
	public double speedSideways;
	public double sprintSpeedModifier;
	public double sprintFuelModifier;
	public boolean emergencyHoverMode;

	private final
	@Nonnull
	List<String> jetpacks = new ArrayList<String>();

	private Packs(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity, ParticleType defaultParticleType, boolean usesFuel) {
		this(baseName, tier, defaultConfigKey, rarity);
		this.defaultParticleType = defaultParticleType;
		this.usesFuel = usesFuel;
	}

	private Packs(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity, boolean isArmored) {
		this(baseName, tier, defaultConfigKey, rarity);
		this.isArmored = isArmored;
	}

	private Packs(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity, boolean isArmored, int platingMeta) {
		this(baseName, tier, defaultConfigKey, rarity);
		this.isArmored = isArmored;
		this.platingMeta = platingMeta;
	}

	private Packs(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity) {
		this.baseName = baseName;
		this.tier = tier;
		this.defaults = PackDefaults.get(defaultConfigKey);
		this.defaultParticleType = ParticleType.DEFAULT;
		this.unlocalisedName = "item.simplyjetpacks." + baseName;
		this.jetpacks.add(baseName);
		this.usesFuel = true;
		this.isArmored = false;
		this.rarity = rarity;
		this.setArmorModel(PackModelType.JETPACK);
	}

	public
	@Nonnull
	String getBaseName() {
		return baseName;
	}

	public int getFuelCapacity() {
		return fuelCapacity;
	}

	public int getTier() {
		return tier;
	}

	public int getFuelPerTickIn() {
		return fuelPerTickIn;
	}

	public int getFuelPerTickOut() {
		return fuelPerTickOut;
	}

	public int getArmorFuelPerHit() {
		return armorFuelPerHit;
	}

	public int getArmorReduction() {
		return armorReduction;
	}

	public int getFuelUsage() {
		return fuelUsage;
	}

	public //TODO: INVESTIGATE!!
	@Nonnull
	List<String> getJetpacks() {
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

	public static Packs getTypeFromName(String name) {
		for (Packs pack : Packs.values()) {
			if (pack.baseName.equalsIgnoreCase(name)) {
				return pack;
			}
		}
		return null;
	}

	public static
	@Nonnull
	Packs getTypeFromMeta(int meta) {
		return values()[meta >= 0 && meta < values().length ? meta : 0];
	}

	public static int getMetaFromType(Packs value) {
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

	public Packs setArmorModel(PackModelType armorModel) {
		this.armorModel = armorModel;
		return this;
	}
	
	public static void loadAllConfigs(Configuration config) {
		for (Packs pack : PACKS_SJ) {
			pack.loadConfig(config);
		}
		if (ModItems.integrateEIO){
			for (Packs pack : PACKS_EIO) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateTE) {
			for (Packs pack : PACKS_TE) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateVanilla){
			for (Packs pack : PACKS_VANILLA) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateRR){
			for (Packs pack : PACKS_RR) {
				pack.loadConfig(config);
			}
		}
	}

	protected void loadConfig(Configuration config) {

		if (this.defaults.fuelCapacity != null) {
			this.fuelCapacity = config.get(this.defaults.section.name, "Fuel Capacity", this.defaults.fuelCapacity, "The maximum amount of fuel that this pack can hold.").setMinValue(1).getInt(this.defaults.fuelCapacity);
		}
		if (this.defaults.fuelUsage != null) {
			this.fuelUsage = config.get(this.defaults.section.name, "Fuel Usage", this.defaults.fuelUsage, "The amount of fuel that this pack uses every tick when used.").setMinValue(0).getInt(this.defaults.fuelUsage);
		}
		if (this.defaults.fuelPerTickIn != null) {
			this.fuelPerTickIn = config.get(this.defaults.section.name, "Fuel Per Tick In", this.defaults.fuelPerTickIn, "The amount of fuel that can be inserted into this pack per tick from external sources.").setMinValue(0).getInt(this.defaults.fuelPerTickIn);
		}
		if (this.defaults.fuelPerTickOut != null) {
			this.fuelPerTickOut = config.get(this.defaults.section.name, "Fuel Per Tick Out", this.defaults.fuelPerTickOut, "The amount of fuel that can be extracted from this pack per tick by external sources. Also determines how quickly Flux Packs can charge other items.").setMinValue(0).getInt(this.defaults.fuelPerTickOut);
		}
		if (this.defaults.armorReduction != null) {
			this.armorReduction = config.get(this.defaults.section.name, "Armor Reduction", this.defaults.armorReduction, "How well this pack can protect the user from damage, if armored. The higher the value, the stronger the armor will be.").setMinValue(0).setMaxValue(20).getInt(this.defaults.armorReduction);
		}
		if (this.defaults.armorFuelPerHit != null) {
			this.armorFuelPerHit = config.get(this.defaults.section.name, "Armor Fuel Per Hit", this.defaults.armorFuelPerHit, "How much fuel is lost from this pack when the user is hit, if armored.").setMinValue(0).getInt(this.defaults.armorFuelPerHit);
		}
		if (this.defaults.speedVertical != null) {
			this.speedVertical = config.get(this.defaults.section.name, "Vertical Speed", this.defaults.speedVertical, "The maximum vertical speed of this jetpack when flying.").setMinValue(0.0D).getDouble(this.defaults.speedVertical);
		}
		if (this.defaults.accelVertical != null) {
			this.accelVertical = config.get(this.defaults.section.name, "Vertical Acceleration", this.defaults.accelVertical, "The vertical acceleration of this jetpack when flying; every tick, this amount of vertical speed will be added until maximum speed is reached.").setMinValue(0.0D).getDouble(this.defaults.accelVertical);
		}
		if (this.defaults.speedVerticalHover != null) {
			this.speedVerticalHover = config.get(this.defaults.section.name, "Vertical Speed (Hover Mode)", this.defaults.speedVerticalHover, "The maximum vertical speed of this jetpack when flying in hover mode.").setMinValue(0.0D).getDouble(this.defaults.speedVerticalHover);
		}
		if (this.defaults.speedVerticalHoverSlow != null) {
			this.speedVerticalHoverSlow = config.get(this.defaults.section.name, "Vertical Speed (Hover Mode / Slow Descent)", this.defaults.speedVerticalHoverSlow, "The maximum vertical speed of this jetpack when slowly descending in hover mode.").setMinValue(0.0D).getDouble(this.defaults.speedVerticalHoverSlow);
		}
		if (this.defaults.speedSideways != null) {
			this.speedSideways = config.get(this.defaults.section.name, "Sideways Speed", this.defaults.speedSideways, "The speed of this jetpack when flying sideways. This is mostly noticeable in hover mode.").setMinValue(0.0D).getDouble(this.defaults.speedSideways);
		}
		if (this.defaults.sprintSpeedModifier != null) {
			this.sprintSpeedModifier = config.get(this.defaults.section.name, "Sprint Speed Multiplier", this.defaults.sprintSpeedModifier, "How much faster this jetpack will fly forward when sprinting. Setting this to 1.0 will make sprinting have no effect apart from the added speed from vanilla.").setMinValue(0.0D).getDouble(this.defaults.sprintSpeedModifier);
		}
		if (this.defaults.sprintFuelModifier != null) {
			this.sprintFuelModifier = config.get(this.defaults.section.name, "Sprint Fuel Usage Multiplier", this.defaults.sprintFuelModifier, "How much more energy this jetpack will use when sprinting. Setting this to 1.0 will make sprinting have no effect on energy usage.").setMinValue(0.0D).getDouble(this.defaults.sprintFuelModifier);
		}
		if (this.defaults.emergencyHoverMode != null) {
			this.emergencyHoverMode = config.get(this.defaults.section.name, "Emergency Hover Mode", this.defaults.emergencyHoverMode, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(this.defaults.emergencyHoverMode);
		}
	}
}
