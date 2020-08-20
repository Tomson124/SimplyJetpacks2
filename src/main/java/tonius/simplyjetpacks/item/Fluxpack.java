package tonius.simplyjetpacks.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.config.Configuration;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.config.PackDefaults;
import tonius.simplyjetpacks.setup.ModItems;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Locale;

public enum Fluxpack implements IStringSerializable {

	FLUXPACK_CREATIVE("fluxpack_creative", "fluxpack_creative", 6, EnumRarity.EPIC, false, true),

	// EnderIO
	FLUXPACK_EIO1("fluxpack_eio1", "fluxpack_eio1", 1, EnumRarity.COMMON),
	FLUXPACK_EIO2("fluxpack_eio2", "fluxpack_eio2", 2, EnumRarity.UNCOMMON),
	FLUXPACK_EIO3("fluxpack_eio3", "fluxpack_eio3", 3, EnumRarity.RARE),
	FLUXPACK_EIO1_ARMORED("fluxpack_eio1_armored", "fluxpack_eio1", 1, EnumRarity.COMMON, true, true, MetaItemsMods.ARMOR_PLATING_EIO_1.ordinal()),
	FLUXPACK_EIO2_ARMORED("fluxpack_eio2_armored", "fluxpack_eio2", 2, EnumRarity.UNCOMMON, true, true, MetaItemsMods.ARMOR_PLATING_EIO_2.ordinal()),
	FLUXPACK_EIO3_ARMORED("fluxpack_eio3_armored", "fluxpack_eio3", 3, EnumRarity.RARE, true, true, MetaItemsMods.ARMOR_PLATING_EIO_3.ordinal()),

	// Thermal Expansion
	FLUXPACK_TE1("fluxpack_te1", "fluxpack_te1", 1, EnumRarity.COMMON),
	FLUXPACK_TE2("fluxpack_te2", "fluxpack_te2", 2, EnumRarity.COMMON),
	FLUXPACK_TE3("fluxpack_te3", "fluxpack_te3", 3, EnumRarity.UNCOMMON),
	FLUXPACK_TE4("fluxpack_te4", "fluxpack_te4", 4, EnumRarity.RARE),
	FLUXPACK_TE1_ARMORED("fluxpack_te1_armored", "fluxpack_te1", 1, EnumRarity.COMMON, true, true, MetaItemsMods.ARMOR_PLATING_TE_1.ordinal()),
	FLUXPACK_TE2_ARMORED("fluxpack_te2_armored", "fluxpack_te2", 2, EnumRarity.COMMON, true, true, MetaItemsMods.ARMOR_PLATING_TE_2.ordinal()),
	FLUXPACK_TE3_ARMORED("fluxpack_te3_armored", "fluxpack_te3", 3, EnumRarity.UNCOMMON, true, true, MetaItemsMods.ARMOR_PLATING_TE_3.ordinal()),
	FLUXPACK_TE4_ARMORED("fluxpack_te4_armored", "fluxpack_te4", 4, EnumRarity.RARE, true, true, MetaItemsMods.ARMOR_PLATING_TE_4.ordinal());

	protected final PackDefaults defaults;
	protected static final EnumSet<Fluxpack> FLUXPACKS_ALL = EnumSet.allOf(Fluxpack.class);
	protected static final EnumSet<Fluxpack> FLUXPACKS_SJ = EnumSet.of(FLUXPACK_CREATIVE);
	public static final EnumSet<Fluxpack> FLUXPACKS_EIO = EnumSet.range(FLUXPACK_EIO1, FLUXPACK_EIO3_ARMORED);
	public static final EnumSet<Fluxpack> FLUXPACKS_EIO_ARMORED = EnumSet.range(FLUXPACK_EIO1_ARMORED, FLUXPACK_EIO3_ARMORED);
	public static final EnumSet<Fluxpack> FLUXPACKS_TE = EnumSet.range(FLUXPACK_TE1, FLUXPACK_TE4_ARMORED);
	public static final EnumSet<Fluxpack> FLUXPACKS_TE_ARMORED = EnumSet.range(FLUXPACK_TE1_ARMORED, FLUXPACK_TE4_ARMORED);

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
	public int platingMeta;
	public boolean usesFuel;
	public boolean isArmored;
	public EnumRarity rarity;
	public PackModelType armorModel = PackModelType.FLAT;
	public int enchantability;

	Fluxpack(@Nonnull String baseName, String defaultConfigKey, int tier, EnumRarity rarity, boolean usesFuel) {
		this(baseName, defaultConfigKey, tier, rarity);
		this.usesFuel = usesFuel;
	}

	Fluxpack(@Nonnull String baseName, String defaultConfigKey, int tier, EnumRarity rarity, boolean usesFuel, boolean isArmored) {
		this(baseName, defaultConfigKey, tier, rarity, usesFuel);
		this.isArmored = isArmored;
	}

	Fluxpack(@Nonnull String baseName, String defaultConfigKey, int tier, EnumRarity rarity, boolean usesFuel, boolean isArmored, int platingMeta) {
		this(baseName, defaultConfigKey, tier, rarity, usesFuel);
		this.isArmored = isArmored;
		this.platingMeta = platingMeta;
	}

	Fluxpack(@Nonnull String baseName, String defaultConfigKey, int tier, EnumRarity rarity) {
		this.baseName = baseName;
		this.defaults = PackDefaults.get(defaultConfigKey);
		this.tier = tier;
		this.unlocalisedName = "item.simplyjetpacks." + baseName;
		this.usesFuel = true;
		this.rarity = rarity;
		this.setArmorModel(PackModelType.FLUXPACK);
	}

	@Nonnull
	public String getBaseName() {
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
	public ItemStack getStackFluxpack() {
		return new ItemStack(ModItems.itemFluxPack, 1, ordinal());
	}

	@Nonnull
	public static Fluxpack getTypeFromMeta(int meta) {
		return values()[meta >= 0 && meta < values().length ? meta : 0];
	}

	public static int getMetaFromType(Fluxpack value) {
		return value.ordinal();
	}

	public Fluxpack setArmorModel(PackModelType armorModel) {
		this.armorModel = armorModel;
		return this;
	}

	public boolean getGlow() {
		return this.baseName.contains("creative");
	}

	public static void loadAllConfigs(Configuration config) {
		for (Fluxpack pack : FLUXPACKS_SJ) {
			pack.loadConfig(config);
		}
		if (ModItems.integrateEIO) {
			for (Fluxpack pack : FLUXPACKS_EIO) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateTE) {
			for (Fluxpack pack : FLUXPACKS_TE) {
				pack.loadConfig(config);
			}
		}
	}

	private static String getKey() {
		return "config." + SimplyJetpacks.PREFIX + "tuning.";
	}

	protected void loadConfig(Configuration config) {
		if (this.defaults.fuelCapacity != null) {
			this.fuelCapacity = config.get(this.defaults.section.key, I18n.format(getKey() + "fuelCapacity"), this.defaults.fuelCapacity, I18n.format(getKey() + "fuelCapacity.tooltip")).setMinValue(1).getInt(this.defaults.fuelCapacity);
		}
		if (this.defaults.fuelUsage != null) {
			this.fuelUsage = config.get(this.defaults.section.key, I18n.format(getKey() + "fuelUsage"), this.defaults.fuelUsage, I18n.format(getKey() + "fuelUsage.tooltip")).setMinValue(0).getInt(this.defaults.fuelUsage);
		}
		if (this.defaults.fuelPerTickIn != null) {
			this.fuelPerTickIn = config.get(this.defaults.section.key, I18n.format(getKey() + "fuelPerTickIn"), this.defaults.fuelPerTickIn, I18n.format(getKey() + "fuelPerTickIn.tooltip")).setMinValue(0).getInt(this.defaults.fuelPerTickIn);
		}
		if (this.defaults.fuelPerTickOut != null) {
			this.fuelPerTickOut = config.get(this.defaults.section.key, I18n.format(getKey() + "fuelPerTickOut"), this.defaults.fuelPerTickOut, I18n.format(getKey() + "fuelPerTickOut.tooltip")).setMinValue(0).getInt(this.defaults.fuelPerTickOut);
		}
		if (this.defaults.armorReduction != null) {
			this.armorReduction = config.get(this.defaults.section.key, I18n.format(getKey() + "armorReduction"), this.defaults.armorReduction, I18n.format(getKey() + "armorReduction.tooltip")).setMinValue(0).setMaxValue(20).getInt(this.defaults.armorReduction);
		}
		if (this.defaults.armorFuelPerHit != null) {
			this.armorFuelPerHit = config.get(this.defaults.section.key, I18n.format(getKey() + "armorFuelPerHit"), this.defaults.armorFuelPerHit, I18n.format(getKey() + "armorFuelPerHit.tooltip")).setMinValue(0).getInt(this.defaults.armorFuelPerHit);
		}
		if(this.defaults.enchantability != null) {
			this.enchantability = config.get(this.defaults.section.key, I18n.format(getKey() + "enchantability"), this.defaults.enchantability, I18n.format(getKey() + "enchantability.tooltip")).setMinValue(0).getInt(this.defaults.enchantability);
		}
	}
}
