package tonius.simplyjetpacks.item.rewrite;

import tonius.simplyjetpacks.setup.ModItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.EnumSet;

public enum MetaItemsMods {

	//EnderIO
	INGOT_DARK_SOULARIUM("ingot_Dark_Soularium", null, EnumRarity.UNCOMMON, true),
	REINFORCED_GLIDERWINGS("reinforced_Gliderwing", null, EnumRarity.UNCOMMON),
	UNIT_FLIGHT_CONTROL_EMPTY("unit_Flight_Control_Empty", null, EnumRarity.COMMON),
	UNIT_FLIGHT_CONTROL("unit_Flight_Control", null, EnumRarity.UNCOMMON),
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
	THRUSTER_TE_1("thruster_TE1", null, EnumRarity.COMMON),
	THRUSTER_TE_2("thruster_TE2", null, EnumRarity.COMMON),
	THRUSTER_TE_3("thruster_TE3", null, EnumRarity.UNCOMMON),
	THRUSTER_TE_4("thruster_TE4", null, EnumRarity.RARE),
	THRUSTER_TE_5("thruster_TE5", null, EnumRarity.EPIC);

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
