package tonius.simplyjetpacks.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.config.Configuration;
import tonius.simplyjetpacks.client.model.PackModelType;
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

	protected final PackDefaults defaults;
	protected static final EnumSet<Jetpack> PACKS_ALL = EnumSet.allOf(Jetpack.class);
	protected static final EnumSet<Jetpack> PACKS_SJ = EnumSet.of(JETPACK_CREATIVE);
	public static final EnumSet<Jetpack> PACKS_VANILLA = EnumSet.range(JETPACK_VANILLA_1, JETPACK_VANILLA_3_ARMORED);
	public static final EnumSet<Jetpack> PACKS_EIO = EnumSet.range(JETPACK_EIO_1, JETPLATE_EIO_5);
	public static final EnumSet<Jetpack> PACKS_TE = EnumSet.range(JETPACK_TE_1, JETPLATE_TE_5);
	public static final EnumSet<Jetpack> PACKS_TE_ARMORED = EnumSet.range(JETPACK_TE_1_ARMORED, JETPACK_TE_4_ARMORED);
	public static final EnumSet<Jetpack> PACKS_MEK = EnumSet.range(JETPACK_MEK_1, JETPACK_MEK_4_ARMORED);
	public static final EnumSet<Jetpack> PACKS_IE = EnumSet.range(JETPACK_IE_1, JETPACK_IE_3_ARMORED);
	public static final EnumSet<Jetpack> PACKS_RR = EnumSet.of(JETPLATE_TE_5_ENDERIUM);

	protected static final String TAG_PARTICLE = "JetpackParticleType";
	public ParticleType defaultParticleType;
	public PackModelType armorModel = PackModelType.FLAT;

	@Nonnull
	public final String baseName;
	@Nonnull
	public final String unlocalisedName;
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

	@Nonnull
	private final List<String> jetpacks = new ArrayList<>();

	Jetpack(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity, ParticleType defaultParticleType, boolean usesFuel) {
		this(baseName, tier, defaultConfigKey, rarity);
		this.defaultParticleType = defaultParticleType;
		this.usesFuel = usesFuel;
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
		this.usesFuel = true;
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

	public int getFuelCapacity() {
		return fuelCapacity;
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

	@Nonnull
	public ItemStack getStackJetpack() {
		return getStackJetpack(1);
	}

	@Nonnull
	public ItemStack getStackJetpack(int size) {
		return new ItemStack(ModItems.itemJetpack, size, ordinal());
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
		if (item.isOn(stack) && item.getFuelStored(stack) > 0 && (flyKeyDown || item.isHoverModeOn(stack) && !user.onGround && user.motionY < 0)) {
			return this.getParticleType(stack);
		}
		return null;
	}
	
	public static void loadAllConfigs(Configuration config) {
		for (Jetpack pack : PACKS_SJ) {
			pack.loadConfig(config);
		}
		if (ModItems.integrateVanilla){
			for (Jetpack pack : PACKS_VANILLA) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateEIO){
			for (Jetpack pack : PACKS_EIO) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateTE) {
			for (Jetpack pack : PACKS_TE) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateMek){
			for (Jetpack pack : PACKS_MEK) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateIE){
			for (Jetpack pack : PACKS_IE) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateRR){
			for (Jetpack pack : PACKS_RR) {
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
