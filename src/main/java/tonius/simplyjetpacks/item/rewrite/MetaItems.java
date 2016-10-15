package tonius.simplyjetpacks.item.rewrite;

import tonius.simplyjetpacks.setup.ModItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

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

	public static final EnumSet<MetaItems> PARTICLE_CUSTOMIZERS = EnumSet.range(PARTICLE_DEFAULT, PARTICLE_RAINBOWSMOKE);

	private MetaItems(String name, String keyTooltip, EnumRarity rarity) {
		this.name = name;
		this.keyTooltip = keyTooltip;
		this.rarity = rarity;
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

	public
	@Nonnull
	ItemStack getStackMetaItem() {
		return getStackMetaItem(1);
	}

	public
	@Nonnull
	ItemStack getStackMetaItem(int size) {
		return new ItemStack(ModItems.metaItem, size, ordinal());
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
