package tonius.simplyjetpacks.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;

public final class NBTHelper {
	private NBTHelper() {

	}

	public static CompoundNBT getCompoundTag(ItemStack stack) {
		CompoundNBT nbt = stack.getTag();
		if (nbt == null) {
			nbt = new CompoundNBT();
			stack.setTag(nbt);
		}
		return nbt;
	}

	public static boolean keyExists(ItemStack stack, String key) {

		if (stack.isEmpty()) {
			return false;
		}
		return getCompoundTag(stack).contains(key);
	}

	public static void setBoolean(ItemStack stack, String key, boolean value) {

		getCompoundTag(stack).putBoolean(key, value);
	}

	public static boolean getBoolean(ItemStack stack, String key, boolean defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getCompoundTag(stack).getBoolean(key);
	}

	public static int getInt(ItemStack stack, String key, int defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getCompoundTag(stack).getInt(key);
	}

	public static void setInt(ItemStack stack, String key, int value) {
		getCompoundTag(stack).putInt(key, value);
	}

	public static long getLong(ItemStack stack, String key, long defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getCompoundTag(stack).getLong(key);
	}

	public static void setLong(ItemStack stack, String key, Long value) {

		getCompoundTag(stack).putLong(key, value);
	}

	public static byte getByte(ItemStack stack, String key, byte defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getCompoundTag(stack).getByte(key);
	}

	public static void setByte(ItemStack stack, String key, byte value) {

		getCompoundTag(stack).putByte(key, value);
	}

	public static byte[] getByteArray(ItemStack stack, String key, byte[] defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getCompoundTag(stack).getByteArray(key);
	}

	public static void setByteArray(ItemStack stack, String key, byte[] value) {

		getCompoundTag(stack).putByteArray(key, value);
	}

	public static double getDouble(ItemStack stack, String key, double defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getCompoundTag(stack).getDouble(key);
	}

	public static void setDouble(ItemStack stack, String key, double value) {

		getCompoundTag(stack).putDouble(key, value);
	}

	public static float getFloat(ItemStack stack, String key, float defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getCompoundTag(stack).getFloat(key);
	}

	public static void setFloat(ItemStack stack, String key, float value) {

		getCompoundTag(stack).putFloat(key, value);
	}

	public static int[] getIntArray(ItemStack stack, String key, int[] defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getCompoundTag(stack).getIntArray(key);
	}

	public static void setIntArray(ItemStack stack, String key, int[] value) {

		getCompoundTag(stack).putIntArray(key, value);
	}

	public static short getShort(ItemStack stack, String key, short defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getCompoundTag(stack).getShort(key);
	}

	public static void setShort(ItemStack stack, String key, short value) {

		getCompoundTag(stack).putShort(key, value);
	}

	public static String getString(ItemStack stack, String key, String defaultValue) {

		if (!keyExists(stack, key)) {
			return defaultValue;
		}
		return getCompoundTag(stack).getString(key);
	}

	public static void setString(ItemStack stack, String key, String value) {

		getCompoundTag(stack).putString(key, value);
	}

	public static INBT getTag(ItemStack stack, String key) {

		if (!keyExists(stack, key)) {
			return null;
		}
		return getCompoundTag(stack).getCompound(key);
	}

	public static void setTag(ItemStack stack, String key, INBT value) {

		getCompoundTag(stack).put(key, value);
	}

	public static CompoundNBT getCompoundTag(ItemStack stack, String key) {

		if (!keyExists(stack, key)) {
			return null;
		}
		return getCompoundTag(stack).getCompound(key);
	}

	public static void removeTag(ItemStack stack, String key) {

		getCompoundTag(stack).remove(key);
	}
}
