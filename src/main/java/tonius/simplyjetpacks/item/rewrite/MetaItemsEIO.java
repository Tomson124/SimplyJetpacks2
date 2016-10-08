package tonius.simplyjetpacks.item.rewrite;

import net.minecraft.item.EnumRarity;

import javax.annotation.Nonnull;

public enum MetaItemsEIO {

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
	ARMOR_PLATING_EIO_4("armorPlating.eio.4", null, EnumRarity.COMMON);

	private String name;
	private String keyTooltip;
	private EnumRarity rarity;
	private boolean glow;

	private MetaItemsEIO(String name, String keyTooltip, EnumRarity rarity) {
		this.name = name;
		this.keyTooltip = keyTooltip;
		this.rarity = rarity;
	}

	private MetaItemsEIO(String name, String keyTooltip, EnumRarity rarity, boolean glow) {
		this(name, keyTooltip, rarity);
		this.glow = glow;
	}

	public static MetaItemsEIO getFromName(String s)
	{
		for(MetaItemsEIO meta : values())
		{
			if(meta.name.toLowerCase().equals(s.toLowerCase()))
			{
				return meta;
			}
		}

		return null;
	}

	public static @Nonnull MetaItemsEIO getTypeFromMeta(int meta) {
		return values()[meta >= 0 && meta < values().length + MetaItems.values().length ? meta : 0];
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
