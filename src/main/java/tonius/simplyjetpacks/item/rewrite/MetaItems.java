package tonius.simplyjetpacks.item.rewrite;

import net.minecraft.item.EnumRarity;

import javax.annotation.Nonnull;
import java.util.EnumSet;

public enum MetaItems {

	LEATHER_STRAP("leatherStrap", null, EnumRarity.COMMON),

	PARTICLE_DEFAULT("particleDefault", "particleCustomizers", EnumRarity.COMMON),
	PARTICLE_NONE("particleNone", "particleCustomizers", EnumRarity.COMMON),
	PARTICLE_SMOKE("particleSmoke", "particleCustomizers", EnumRarity.COMMON),
	PARTICLE_RAINBOWSMOKE("particleRainbowSmoke", "particleCustomizers", EnumRarity.COMMON);

	private String name;
	private String keyTooltip;
	private EnumRarity rarity;
	private boolean oreDict;

	public static final EnumSet<MetaItems> PARTICLE_CUSTOMIZERS = EnumSet.range(PARTICLE_DEFAULT, PARTICLE_RAINBOWSMOKE);

	private MetaItems(String name, String keyTooltip, EnumRarity rarity) {
		this.name = name;
		this.keyTooltip = keyTooltip;
		this.rarity = rarity;
		this.oreDict = false;
	}

	private MetaItems(String name, String keyTooltip, EnumRarity rarity, boolean oreDict) {
		this(name, keyTooltip, rarity);
		this.oreDict = oreDict;
	}

	public static MetaItems getFromName(String s)
	{
		for(MetaItems meta : values())
		{
			if(meta.name.toLowerCase().equals(s.toLowerCase()))
			{
				return meta;
			}
		}

		return null;
	}

	public static @Nonnull MetaItems getTypeFromMeta(int meta) {
		return values()[meta >= 0 && meta < values().length ? meta : 0];
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
}
