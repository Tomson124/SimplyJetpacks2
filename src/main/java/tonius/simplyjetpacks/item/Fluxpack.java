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
	public int enchantability;

	Fluxpack(@Nonnull String baseName, String configKey, int tier, EnumRarity rarity, boolean usesEnergy) {
		this(baseName, configKey, tier, rarity);
		this.usesEnergy = usesEnergy;
	}

	Fluxpack(@Nonnull String baseName, String configKey, int tier, EnumRarity rarity, boolean usesEnergy, boolean isArmored) {
		this(baseName, configKey, tier, rarity, usesEnergy);
		this.isArmored = isArmored;
	}

	Fluxpack(@Nonnull String baseName, String configKey, int tier, EnumRarity rarity, boolean usesEnergy, boolean isArmored, int platingMeta) {
		this(baseName, configKey, tier, rarity, usesEnergy);
		this.isArmored = isArmored;
		this.platingMeta = platingMeta;
	}

	Fluxpack(@Nonnull String baseName, String configKey, int tier, EnumRarity rarity) {
		this.baseName = baseName;
		this.defaults = PackDefaults.get(configKey);
		this.tier = tier;
		this.unlocalisedName = "item.simplyjetpacks." + baseName;
		this.usesEnergy = true;
		this.rarity = rarity;
		this.setArmorModel(PackModelType.FLUXPACK);
	}

	@Nonnull
	public String getBaseName() {
		return baseName;
	}

	public int getEnergyCapacity() {
		return energyCapacity;
	}

	public int getTier() {
		return tier;
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
		return new ItemStack(ModItems.itemFluxpack, 1, ordinal());
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
		if (this.defaults.energyCapacity != null) {
			this.energyCapacity = config.get(this.defaults.section.key, I18n.format(getKey() + "energyCapacity"), this.defaults.energyCapacity, I18n.format(getKey() + "energyCapacity.tooltip")).setMinValue(1).getInt(this.defaults.energyCapacity);
		}
		if (this.defaults.energyUsage != null) {
			this.energyUsage = config.get(this.defaults.section.key, I18n.format(getKey() + "energyUsage"), this.defaults.energyUsage, I18n.format(getKey() + "energyUsage.tooltip")).setMinValue(0).getInt(this.defaults.energyUsage);
		}
		if (this.defaults.energyPerTickIn != null) {
			this.energyPerTickIn = config.get(this.defaults.section.key, I18n.format(getKey() + "energyPerTickIn"), this.defaults.energyPerTickIn, I18n.format(getKey() + "energyPerTickIn.tooltip")).setMinValue(0).getInt(this.defaults.energyPerTickIn);
		}
		if (this.defaults.energyPerTickOut != null) {
			this.energyPerTickOut = config.get(this.defaults.section.key, I18n.format(getKey() + "energyPerTickOut"), this.defaults.energyPerTickOut, I18n.format(getKey() + "energyPerTickOut.tooltip")).setMinValue(0).getInt(this.defaults.energyPerTickOut);
		}
		if (this.defaults.armorReduction != null) {
			this.armorReduction = config.get(this.defaults.section.key, I18n.format(getKey() + "armorReduction"), this.defaults.armorReduction, I18n.format(getKey() + "armorReduction.tooltip")).setMinValue(0).setMaxValue(20).getInt(this.defaults.armorReduction);
		}
		if (this.defaults.armorEnergyPerHit != null) {
			this.armorEnergyPerHit = config.get(this.defaults.section.key, I18n.format(getKey() + "armorFuelPerHit"), this.defaults.armorEnergyPerHit, I18n.format(getKey() + "armorEnergyPerHit.tooltip")).setMinValue(0).getInt(this.defaults.armorEnergyPerHit);
		}
		if(this.defaults.enchantability != null) {
			this.enchantability = config.get(this.defaults.section.key, I18n.format(getKey() + "enchantability"), this.defaults.enchantability, I18n.format(getKey() + "enchantability.tooltip")).setMinValue(0).getInt(this.defaults.enchantability);
		}
	}
}
