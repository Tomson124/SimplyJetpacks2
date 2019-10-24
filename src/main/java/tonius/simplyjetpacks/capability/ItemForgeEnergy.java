package tonius.simplyjetpacks.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.reference.Keys;

import java.util.function.IntSupplier;

public final class ItemForgeEnergy extends EnergyConversionConfig {
	private final ItemStack stack;

	public ItemForgeEnergy(ItemStack stack, IntSupplier capacity) {
		super(capacity);
		this.stack = stack;
	}

	protected void writeEnergy() {
		CompoundNBT nbt = NBTHelper.getCompoundTag(stack);
		nbt.putInt(Keys.ENERGY, getEnergyStoredCache());
	}

	protected void updateEnergy() {
		CompoundNBT nbt = NBTHelper.getCompoundTag(stack);
		if (nbt.contains(Keys.ENERGY))
			setEnergy(nbt.getInt(Keys.ENERGY));
		updateMaxEnergy();
	}
}
