package tonius.simplyjetpacks.item.rewrite;

import javax.annotation.Nonnull;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.setup.ModItems;

public enum MetaItemsVanilla {
	
	THRUSTER_VANILLA_1("thrusterVanilla1", null, EnumRarity.COMMON),
	THRUSTER_VANILLA_2("thrusterVanilla2", null, EnumRarity.UNCOMMON),
	THRUSTER_VANILLA_3("thrusterVanilla3", null, EnumRarity.RARE);

	private String name;
	private String keyTooltip;
	private EnumRarity rarity;

	private MetaItemsVanilla(String name, String keyTooltip, EnumRarity rarity) {
		this.name = name;
		this.keyTooltip = keyTooltip;
		this.rarity = rarity;
	}

	public static MetaItemsVanilla getFromName(String s)
	{
		for(MetaItemsVanilla meta : values())
		{
			if(meta.name.toLowerCase().equals(s.toLowerCase()))
			{
				return meta;
			}
		}

		return null;
	}

	public static @Nonnull MetaItemsVanilla getTypeFromMeta(int meta) {
		return values()[meta >= 0 && meta < values().length + MetaItemsVanilla.values().length ? meta : 0];
	}

	public
	@Nonnull
	ItemStack getStackMetaItemVanilla() {
		return getStackMetaItemVanilla(1);
	}

	public
	@Nonnull
	ItemStack getStackMetaItemVanilla(int size) {
		return new ItemStack(ModItems.metaItemVanilla, size, ordinal());
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
