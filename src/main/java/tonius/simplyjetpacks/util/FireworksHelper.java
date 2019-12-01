package tonius.simplyjetpacks.util;

import net.minecraft.item.Items;
import tonius.simplyjetpacks.util.math.MathHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class FireworksHelper {
	public FireworksHelper() {
	}

	public static ItemStack getFireworksStack(int var0, FireworksHelper.Explosion... var1) {
		CompoundNBT var2 = new CompoundNBT();
		CompoundNBT var3 = new CompoundNBT();
		ListNBT var4 = new ListNBT();
		if (var1 != null) {
			FireworksHelper.Explosion[] var5 = var1;
			int var6 = var1.length;

			for (int var7 = 0; var7 < var6; ++var7) {
				FireworksHelper.Explosion var8 = var5[var7];
				if (var8 != null) {
					var4.add(var8.getTagCompound());
				}
			}
		}

		var3.putByte("Flight", (byte) MathHelper.clampI(var0, 0, 3));
		var3.put("Explosions", var4);
		var2.put("Fireworks", var3);
		ItemStack var9 = new ItemStack(Items.FIREWORK_ROCKET);
		var9.setTag(var2);
		return var9;
	}

	public static ItemStack getRandomFireworks(int var0, int var1, int var2, int var3) {
		var1 = MathHelper.clampI(var1, 0, 2147483647);
		FireworksHelper.Explosion[] var4 = new FireworksHelper.Explosion[var1];

		for (int var5 = 0; var5 < var1; ++var5) {
			var4[var5] = FireworksHelper.Explosion.getRandom(var2, var3);
		}

		return getFireworksStack(var0, var4);
	}

	public static final class Explosion {
		private boolean twinkle = false;
		private boolean trail = false;
		private List<Integer> primaryColors = new ArrayList();
		private List<Integer> fadeColors = new ArrayList();
		private FireworksHelper.Explosion.Type type;

		public Explosion() {
			this.type = FireworksHelper.Explosion.Type.BALL;
		}

		public static FireworksHelper.Explosion getRandom(int var0, int var1) {
			var0 = MathHelper.clampI(var0, 1, 2147483647);
			var1 = MathHelper.clampI(var1, 1, 2147483647);
			FireworksHelper.Explosion var2 = new FireworksHelper.Explosion();
			int var3;
			switch (var3 = MathHelper.RANDOM.nextInt(4)) {
				case 0:
				case 2:
					var2.setTwinkle(true);
					if (var3 == 0) {
						break;
					}
				case 1:
					var2.setTrail(true);
			}

			var2.setType(MathHelper.RANDOM.nextInt(5));

			int var4;
			Color var5;
			for (var4 = 0; var4 < var0; ++var4) {
				var5 = new Color(Color.HSBtoRGB(MathHelper.RANDOM.nextFloat() * 360.0F, MathHelper.RANDOM.nextFloat() * 0.15F + 0.8F, 0.85F));
				var2.addPrimaryColor(var5.getRed(), var5.getGreen(), var5.getBlue());
			}

			for (var4 = 0; var4 < var1; ++var4) {
				var5 = new Color(Color.HSBtoRGB(MathHelper.RANDOM.nextFloat() * 360.0F, MathHelper.RANDOM.nextFloat() * 0.15F + 0.8F, 0.85F));
				var2.addFadeColor(var5.getRed(), var5.getGreen(), var5.getBlue());
			}

			return var2;
		}

		public FireworksHelper.Explosion setTwinkle(boolean var1) {
			this.twinkle = var1;
			return this;
		}

		public FireworksHelper.Explosion setTrail(boolean var1) {
			this.trail = var1;
			return this;
		}

		public FireworksHelper.Explosion setType(FireworksHelper.Explosion.Type var1) {
			this.type = var1;
			return this;
		}

		public FireworksHelper.Explosion setType(int var1) {
			this.setType(FireworksHelper.Explosion.Type.values()[MathHelper.clampI(var1, 0, FireworksHelper.Explosion.Type.values().length - 1)]);
			return this;
		}

		public FireworksHelper.Explosion addPrimaryColor(int var1, int var2, int var3) {
			this.primaryColors.add(Integer.valueOf((var1 << 16) + (var2 << 8) + var3));
			return this;
		}

		public FireworksHelper.Explosion addFadeColor(int var1, int var2, int var3) {
			this.fadeColors.add(Integer.valueOf((var1 << 16) + (var2 << 8) + var3));
			return this;
		}

		public CompoundNBT getTagCompound() {
			CompoundNBT var1 = new CompoundNBT();
			var1.putBoolean("Flicker", this.twinkle);
			var1.putBoolean("Trail", this.trail);
			var1.putByte("Type", (byte) this.type.ordinal());
			int[] var2 = new int[this.primaryColors.size()];

			int var3;
			for (var3 = 0; var3 < this.primaryColors.size(); ++var3) {
				var2[var3] = ((Integer) this.primaryColors.get(var3)).intValue();
			}

			var1.putIntArray("Colors", var2);
			var2 = new int[this.fadeColors.size()];

			for (var3 = 0; var3 < this.fadeColors.size(); ++var3) {
				var2[var3] = ((Integer) this.fadeColors.get(var3)).intValue();
			}

			var1.putIntArray("FadeColors", var2);
			return var1;
		}

		public ItemStack getFireworkStarStack() {
			CompoundNBT var1 = new CompoundNBT();
			CompoundNBT var2 = this.getTagCompound();
			var1.put("Explosion", var2);
			ItemStack var3 = new ItemStack(Items.FIREWORK_STAR);
			var3.setTag(var1);
			return var3;
		}

		public static enum Type {
			BALL,
			LARGE_BALL,
			STAR,
			CREEPER,
			BURST;

			private Type() {
			}
		}
	}
}
