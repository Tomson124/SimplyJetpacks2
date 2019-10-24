package tonius.simplyjetpacks.item;

import net.minecraft.item.Rarity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum ItemsSJ2 {
	ARMOR_PLATE_FLUX("armor_plate_flux", null, Rarity.RARE),
	ARMOR_PLATING_EIO_1("armor_Plating_EIO1", null, Rarity.COMMON),
	ARMOR_PLATING_EIO_2("armor_Plating_EIO2", null, Rarity.COMMON),
	ARMOR_PLATING_EIO_3("armor_Plating_EIO3", null, Rarity.COMMON),

	ARMOR_PLATING_EIO_4("armor_Plating_EIO4", null, Rarity.COMMON),

	ARMOR_PLATING_TE_1("armor_Plating_TE1", null, Rarity.COMMON),
	ARMOR_PLATING_TE_2("armor_Plating_TE2", null, Rarity.COMMON),
	ARMOR_PLATING_TE_3("armor_Plating_TE3", null, Rarity.COMMON),
	ARMOR_PLATING_TE_4("armor_Plating_TE4", null, Rarity.COMMON),

	INGOT_DARK_SOULARIUM("ingot_dark_soularium", null, Rarity.UNCOMMON, true)//EnderIO
	,
	LEATHER_STRAP("leather_Strap", null, Rarity.COMMON),
	PARTICLE_DEFAULT("particle_Default", "particleCustomizers", Rarity.COMMON),
	PARTICLE_NONE("particle_None", "particleCustomizers", Rarity.COMMON),

	PARTICLE_RAINBOWSMOKE("particle_Rainbow_Smoke", "particleCustomizers", Rarity.COMMON),
	PARTICLE_SMOKE("particle_Smoke", "particleCustomizers", Rarity.COMMON),
	PLATE_FLUX("plate_flux", null, Rarity.UNCOMMON)//ThermalExpansion
	,
	REINFORCED_GLIDERWINGS("reinforced_gliderwing", null, Rarity.UNCOMMON),
	THRUSTER_EIO_1("thruster_EIO1", null, Rarity.COMMON),

	THRUSTER_EIO_2("thruster_EIO2", null, Rarity.COMMON),
	THRUSTER_EIO_3("thruster_EIO3", null, Rarity.UNCOMMON),
	THRUSTER_EIO_4("thruster_EIO4", null, Rarity.RARE),
	THRUSTER_EIO_5("thruster_EIO5", null, Rarity.EPIC),
	THRUSTER_TE_1("thruster_TE1", null, Rarity.COMMON),
	THRUSTER_TE_2("thruster_TE2", null, Rarity.COMMON),

	THRUSTER_TE_3("thruster_TE3", null, Rarity.UNCOMMON),
	THRUSTER_TE_4("thruster_TE4", null, Rarity.RARE),
	THRUSTER_TE_5("thruster_TE5", null, Rarity.EPIC),
	UNIT_CRYOTHEUM("unit_cryotheum", null, Rarity.UNCOMMON),

	UNIT_CRYOTHEUM_EMTPY("unit_cryotheum_empty", null, Rarity.COMMON),
	UNIT_FLIGHT_CONTROL("unit_flight_control", null, Rarity.UNCOMMON),
	UNIT_FLIGHT_CONTROL_EMPTY("unit_flight_control_empty", null, Rarity.COMMON),
	UNIT_GLOWSTONE("unit_glowstone", null, Rarity.UNCOMMON),
	UNIT_GLOWSTONE_EMTPY("unit_glowstone_empty", null, Rarity.COMMON);

	private String name;
	private String keyTooltip;
	private Rarity rarity;
	private boolean glow;

	private ItemsSJ2(String name, String keyTooltip, Rarity rarity) {
		this.name = name;
		this.keyTooltip = keyTooltip;
		this.rarity = rarity;
	}

	private ItemsSJ2(String name, String keyTooltip, Rarity rarity, boolean glow) {
		this(name, keyTooltip, rarity);
		this.glow = glow;
	}

	public static ItemsSJ2 getFromName(String s) {
		for (ItemsSJ2 meta : values()) {
			if (meta.name.toLowerCase().equals(s.toLowerCase())) {
				return meta;
			}
		}

		return null;
	}

	public static Packs getTypeFromName(String name) {
		for (Packs pack : Packs.values()) {
			if (pack.baseName.equalsIgnoreCase(name)) {
				return pack;
			}
		}
		return null;
	}

	public @Nonnull String getName() {
		return name;
	}

	public @Nullable String getKeyTooltip() {
		return keyTooltip;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public boolean getGlow() {
		return glow;
	}
}
