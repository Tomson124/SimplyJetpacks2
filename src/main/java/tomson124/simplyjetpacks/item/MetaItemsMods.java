package tomson124.simplyjetpacks.item;

import tomson124.simplyjetpacks.setup.ModItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.EnumSet;

public enum MetaItemsMods {

	//the 'vanilla' mod (here for modular item registration)
	THRUSTER_VANILLA_1("thruster_Vanilla1", null, EnumRarity.COMMON),
	THRUSTER_VANILLA_2("thruster_Vanilla2", null, EnumRarity.UNCOMMON),
	THRUSTER_VANILLA_3("thruster_Vanilla3", null, EnumRarity.RARE),

	//EnderIO
	INGOT_DARK_SOULARIUM("ingot_dark_soularium", null, EnumRarity.UNCOMMON, true),
	REINFORCED_GLIDERWINGS("reinforced_gliderwing", null, EnumRarity.UNCOMMON),
	UNIT_FLIGHT_CONTROL_EMPTY("unit_flight_control_empty", null, EnumRarity.COMMON),
	UNIT_FLIGHT_CONTROL("unit_flight_control", null, EnumRarity.UNCOMMON),
	THRUSTER_EIO_1("thruster_EIO1", null, EnumRarity.COMMON),
	THRUSTER_EIO_2("thruster_EIO2", null, EnumRarity.COMMON),
	THRUSTER_EIO_3("thruster_EIO3", null, EnumRarity.UNCOMMON),
	THRUSTER_EIO_4("thruster_EIO4", null, EnumRarity.RARE),
	THRUSTER_EIO_5("thruster_EIO5", null, EnumRarity.EPIC),
	ARMOR_PLATING_EIO_1("armor_Plating_EIO1", null, EnumRarity.COMMON),
	ARMOR_PLATING_EIO_2("armor_Plating_EIO2", null, EnumRarity.COMMON),
	ARMOR_PLATING_EIO_3("armor_Plating_EIO3", null, EnumRarity.COMMON),
	ARMOR_PLATING_EIO_4("armor_Plating_EIO4", null, EnumRarity.COMMON),

	//ThermalExpansion
	PLATE_FLUX("plate_flux", null, EnumRarity.UNCOMMON),
	ARMOR_PLATE_FLUX("armor_plate_flux", null, EnumRarity.RARE),
	UNIT_GLOWSTONE_EMTPY("unit_glowstone_empty", null, EnumRarity.COMMON),
	UNIT_GLOWSTONE("unit_glowstone", null, EnumRarity.UNCOMMON),
	UNIT_CRYOTHEUM_EMTPY("unit_cryotheum_empty", null, EnumRarity.COMMON),
	UNIT_CRYOTHEUM("unit_cryotheum", null, EnumRarity.UNCOMMON),

	ARMOR_PLATING_TE_1("armor_Plating_TE1", null, EnumRarity.COMMON),
	ARMOR_PLATING_TE_2("armor_Plating_TE2", null, EnumRarity.COMMON),
	ARMOR_PLATING_TE_3("armor_Plating_TE3", null, EnumRarity.COMMON),
	ARMOR_PLATING_TE_4("armor_Plating_TE4", null, EnumRarity.COMMON),

	THRUSTER_TE_1("thruster_TE1", null, EnumRarity.COMMON),
	THRUSTER_TE_2("thruster_TE2", null, EnumRarity.COMMON),
	THRUSTER_TE_3("thruster_TE3", null, EnumRarity.UNCOMMON),
	THRUSTER_TE_4("thruster_TE4", null, EnumRarity.RARE),
	THRUSTER_TE_5("thruster_TE5", null, EnumRarity.EPIC);

	private String name;
	private String keyTooltip;
	private EnumRarity rarity;
	private boolean glow;

	public static final EnumSet<MetaItemsMods> ITEMS_VANILLA = EnumSet.range(THRUSTER_VANILLA_1, THRUSTER_VANILLA_3);
	public static final EnumSet<MetaItemsMods> ITEMS_EIO = EnumSet.range(INGOT_DARK_SOULARIUM, ARMOR_PLATING_EIO_4);
	public static final EnumSet<MetaItemsMods> ITEMS_TE = EnumSet.range(PLATE_FLUX, THRUSTER_TE_5);
	public static final EnumSet<MetaItemsMods> ITEMS_TE_RA = EnumSet.range(UNIT_GLOWSTONE_EMTPY, THRUSTER_TE_5);

	private MetaItemsMods(String name, String keyTooltip, EnumRarity rarity) {
		this.name = name;
		this.keyTooltip = keyTooltip;
		this.rarity = rarity;
	}

	private MetaItemsMods(String name, String keyTooltip, EnumRarity rarity, boolean glow) {
		this(name, keyTooltip, rarity);
		this.glow = glow;
	}

	public static MetaItemsMods getFromName(String s)
	{
		for(MetaItemsMods meta : values())
		{
			if(meta.name.toLowerCase().equals(s.toLowerCase()))
			{
				return meta;
			}
		}

		return null;
	}

	public static @Nonnull
	MetaItemsMods getTypeFromMeta(int meta) {
		return values()[meta >= 0 && meta < values().length + MetaItemsMods.values().length ? meta : 0];
	}

	public
	@Nonnull
	ItemStack getStackMetaItem() {
		return getStackMetaItem(1);
	}

	public
	@Nonnull
	ItemStack getStackMetaItem(int size) {
		return new ItemStack(ModItems.metaItemMods, size, ordinal());
	}

	public String getName()
	{
		return name;
	}

	public String getKeyTooltip() {
		return keyTooltip;
	}

	public EnumRarity getRarity() {
		return rarity;
	}

	public boolean getGlow() {
		return glow;
	}
}
