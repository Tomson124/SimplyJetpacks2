package tonius.simplyjetpacks.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.config.Configuration;
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
	FLUXPACK_EIO2_ARMORED("fluxpack_eio2_armored", "fluxpack_eio2", 2, EnumRarity.UNCOMMON, true, true, MetaItemsMods.ARMOR_PLATING_EIO_2.ordinal()),
	FLUXPACK_EIO3_ARMORED("fluxpack_eio3_armored", "fluxpack_eio3", 3, EnumRarity.RARE, true, true, MetaItemsMods.ARMOR_PLATING_EIO_4.ordinal()),

	// Thermal Expansion
	FLUXPACK_TE1("fluxpack_te1", "fluxpack_te1", 1, EnumRarity.COMMON),
	FLUXPACK_TE2("fluxpack_te2", "fluxpack_te2", 2, EnumRarity.UNCOMMON),
	FLUXPACK_TE3("fluxpack_te3", "fluxpack_te3", 3, EnumRarity.RARE),
	FLUXPACK_TE2_ARMORED("fluxpack_te2_armored", "fluxpack_te2", 2, EnumRarity.UNCOMMON, true, true, MetaItemsMods.ARMOR_PLATING_TE_2.ordinal()),
	FLUXPACK_TE3_ARMORED("fluxpack_te3_armored", "fluxpack_te3", 3, EnumRarity.RARE, true, true, MetaItemsMods.ARMOR_PLATING_TE_4.ordinal());

	public final @Nonnull String baseName;
	public final @Nonnull String unlocalisedName;
	public final int tier;
	public int fuelCapacity;
	public int fuelPerTickIn;
	public int fuelPerTickOut;
	public int armorFuelPerHit;
	public int armorReduction;
	public int fuelUsage;
	public int platingMeta;

	public boolean usesFuel;
	public EnumRarity rarity;
	public PackModelType armorModel = PackModelType.FLAT;

	protected final PackDefaults defaults;

	public boolean isArmored;

	protected static final EnumSet<Fluxpack> SJ_FLUXPACKS = EnumSet.of(FLUXPACK_CREATIVE);
	public static final EnumSet<Fluxpack> EIO_FLUXPACKS = EnumSet.range(FLUXPACK_EIO1, FLUXPACK_EIO3_ARMORED);
	public static final EnumSet<Fluxpack> TE_FLUXPACKS = EnumSet.range(FLUXPACK_TE1, FLUXPACK_TE3_ARMORED);
	public static final EnumSet<Fluxpack> TE_FLUXPACKS_ARMORED = EnumSet.of(FLUXPACK_TE2_ARMORED, FLUXPACK_TE3_ARMORED);

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
		this.setArmorModel(PackModelType.FLUX_PACK);
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
		return getStackFluxpack(1);
	}

	@Nonnull
	public ItemStack getStackFluxpack(int size) {
		return new ItemStack(ModItems.itemFluxPack, size, ordinal());
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

	public static void loadAllConfigs(Configuration config) {
		for (Fluxpack pack : SJ_FLUXPACKS) {
			pack.loadConfig(config);
		}
		if (ModItems.integrateEIO) {
			for (Fluxpack pack : EIO_FLUXPACKS) {
				pack.loadConfig(config);
			}
		}
		if (ModItems.integrateTE) {
			for (Fluxpack pack : TE_FLUXPACKS) {
				pack.loadConfig(config);
			}
		}
	}

	public static void writeAllConfigsToNBT(NBTTagCompound tag) {
		for (Fluxpack pack : SJ_FLUXPACKS) {
			NBTTagCompound packTag = new NBTTagCompound();
			pack.writeConfigToNBT(packTag);
			tag.setTag(pack.defaults.section.id, packTag);
		}
		if (ModItems.integrateEIO) {
			for (Fluxpack pack : EIO_FLUXPACKS) {
				NBTTagCompound packTag = new NBTTagCompound();
				pack.writeConfigToNBT(packTag);
				tag.setTag(pack.defaults.section.id, packTag);
			}
		}
		if (ModItems.integrateTE) {
			for (Fluxpack pack : TE_FLUXPACKS) {
				NBTTagCompound packTag = new NBTTagCompound();
				pack.writeConfigToNBT(packTag);
				tag.setTag(pack.defaults.section.id, packTag);
			}
		}
	}

	public static void readAllConfigsFromNBT(NBTTagCompound tag) {
		for (Fluxpack pack : SJ_FLUXPACKS) {
			NBTTagCompound packTag = tag.getCompoundTag(pack.defaults.section.id);
			pack.readConfigFromNBT(packTag);
		}
		if (ModItems.integrateEIO) {
			for (Fluxpack pack : EIO_FLUXPACKS) {
				NBTTagCompound packTag = tag.getCompoundTag(pack.defaults.section.id);
				pack.readConfigFromNBT(packTag);
			}
		}
		if (ModItems.integrateTE) {
			for (Fluxpack pack : TE_FLUXPACKS) {
				NBTTagCompound packTag = tag.getCompoundTag(pack.defaults.section.id);
				pack.readConfigFromNBT(packTag);
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
		/*if(this.defaults.enchantability != null) {
			this.enchantability = config.get(this.defaults.section.name, "Enchantability", this.defaults.enchantability, "The enchantability of this pack. If set to 0, no enchantments can be applied.").setMinValue(0).getInt(this.defaults.enchantability);
		}*/
	}

	protected void writeConfigToNBT(NBTTagCompound tag) {
		if (this.defaults.fuelCapacity != null) {
			tag.setInteger("FuelCapacity", this.fuelCapacity);
		}
		if (this.defaults.fuelUsage != null) {
			tag.setInteger("FuelUsage", this.fuelUsage);
		}
		if (this.defaults.fuelPerTickIn != null) {
			tag.setInteger("FuelPerTickIn", this.fuelPerTickIn);
		}
		if (this.defaults.fuelPerTickOut != null) {
			tag.setInteger("FuelPerTickOut", this.fuelPerTickOut);
		}
		if (this.defaults.armorReduction != null) {
			tag.setInteger("ArmorReduction", this.armorReduction);
		}
	}

	protected void readConfigFromNBT(NBTTagCompound tag) {
		if (this.defaults.fuelCapacity != null) {
			this.fuelCapacity = tag.getInteger("FuelCapacity");
		}
		if (this.defaults.fuelUsage != null) {
			this.fuelUsage = tag.getInteger("FuelUsage");
		}
		if (this.defaults.fuelPerTickIn != null) {
			this.fuelPerTickIn = tag.getInteger("FuelPerTickIn");
		}
		if (this.defaults.fuelPerTickOut != null) {
			this.fuelPerTickOut = tag.getInteger("FuelPerTickOut");
		}
		if (this.defaults.armorReduction != null) {
			this.armorReduction = tag.getInteger("ArmorReduction");
		}
	}
}
