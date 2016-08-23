package tonius.simplyjetpacks.item.rewrite;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import tonius.simplyjetpacks.setup.ModItems;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public enum Jetpack implements IStringSerializable {
	CREATIVE_JETPACK("jetpackCreative", 5, 90000, 500, 500),
	POTATO_JETPACK("jetpackPotato", 1, 500, 50, 50);

	public final @Nonnull
	String baseName;
	public final @Nonnull String unlocalisedName;
	public final @Nonnull String iconKey;
	public final int tier;
	public final int fuelCapacity;
	public final int fuelPerTickIn;
	public final int fuelPerTickOut;
	private final @Nonnull List<String> jetpacks = new ArrayList<String>();

	private Jetpack(@Nonnull String baseName, int tier, int fuelCapacity, int fuelPerTickIn, int fuelPerTickOut) {
		this.baseName = baseName;
		this.tier = tier;
		this.fuelCapacity = fuelCapacity;
		this.fuelPerTickIn = fuelPerTickIn;
		this.fuelPerTickOut = fuelPerTickOut;
		this.unlocalisedName = "simplyjetpacks." + baseName;
		this.iconKey = "simplyjetpacks:" + baseName;
		this.jetpacks.add(baseName);
	}

	public @Nonnull String getBaseName() {
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

	public @Nonnull ItemStack getStackJetpack() {
		return getStackJetpack(1);
	}

	public @Nonnull ItemStack getStackJetpack(int size) {
		return new ItemStack(ModItems.itemJetpack, size, ordinal());
	}

	public @Nonnull List<String> getJetpacks() {
		return jetpacks;
	}

	@Override
	public String getName() {
		return baseName.toLowerCase(Locale.ENGLISH);
	}

	public static @Nonnull Jetpack getTypeFromMeta(int meta) {
		return values()[meta >= 0 && meta < values().length ? meta : 0];
	}

	public static int getMetaFromType(Jetpack value) {
		return value.ordinal();
	}

}
