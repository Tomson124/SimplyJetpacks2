package tonius.simplyjetpacks.item.rewrite;

import tonius.simplyjetpacks.setup.ModItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.EnumSet;

public enum MetaItemsMods {

	//EnderIO
	INGOT_DARK_SOULARIUM("ingotDarkSoularium", null, EnumRarity.UNCOMMON, true),
	REINFORCED_GLIDERWINGS("reinforcedGliderWing", null, EnumRarity.UNCOMMON),
	UNIT_FLIGHT_CONTROL_EMPTY("unitFlightControl.empty", null, EnumRarity.COMMON),
	UNIT_FLIGHT_CONTROL("unitFlightControl", null, EnumRarity.UNCOMMON),
	THRUSTER_EIO_1("thrusterEIO1", null, EnumRarity.COMMON),
	THRUSTER_EIO_2("thrusterEIO2", null, EnumRarity.COMMON),
	THRUSTER_EIO_3("thrusterEIO3", null, EnumRarity.UNCOMMON),
	THRUSTER_EIO_4("thrusterEIO4", null, EnumRarity.RARE),
	THRUSTER_EIO_5("thrusterEIO5", null, EnumRarity.EPIC),
	ARMOR_PLATING_EIO_1("armorPlating.eio.1", null, EnumRarity.COMMON),
	ARMOR_PLATING_EIO_2("armorPlating.eio.2", null, EnumRarity.COMMON),
	ARMOR_PLATING_EIO_3("armorPlating.eio.3", null, EnumRarity.COMMON),
	ARMOR_PLATING_EIO_4("armorPlating.eio.4", null, EnumRarity.COMMON),

	//ThermalExpansion
	THRUSTER_TE_1("thrusterTE1", null, EnumRarity.COMMON),
	THRUSTER_TE_2("thrusterTE2", null, EnumRarity.COMMON),
	THRUSTER_TE_3("thrusterTE3", null, EnumRarity.UNCOMMON),
	THRUSTER_TE_4("thrusterTE4", null, EnumRarity.RARE),
	THRUSTER_TE_5("thrusterTE5", null, EnumRarity.EPIC);

	private String name;
	private String keyTooltip;
	private EnumRarity rarity;
	private boolean glow;

	public static final EnumSet<MetaItemsMods> ITEMS_EIO = EnumSet.range(INGOT_DARK_SOULARIUM, ARMOR_PLATING_EIO_4);
	public static final EnumSet<MetaItemsMods> ITEMS_TE = EnumSet.range(THRUSTER_EIO_1, THRUSTER_EIO_5);

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
	ItemStack getStackMetaItemEIO() {
		return getStackMetaItemEIO(1);
	}

	public
	@Nonnull
	ItemStack getStackMetaItemEIO(int size) {
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
