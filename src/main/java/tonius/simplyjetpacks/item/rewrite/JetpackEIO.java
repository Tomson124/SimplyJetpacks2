package tonius.simplyjetpacks.item.rewrite;

import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.config.PackDefaults;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.NBTHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.config.Configuration;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

public enum JetpackEIO implements IStringSerializable {
	JETPACK_EIO_1("jetpackEIO1", 1, "jetpackEIO1", EnumRarity.COMMON),
	JETPACK_EIO_2("jetpackEIO2", 1, "jetpackEIO2", EnumRarity.COMMON),
	JETPACK_EIO_3("jetpackEIO3", 1, "jetpackEIO3", EnumRarity.UNCOMMON),
	JETPACK_EIO_4("jetpackEIO4", 1, "jetpackEIO4", EnumRarity.RARE);

	protected final PackDefaults defaults;
	protected static final EnumSet<Jetpack> ALL_PACKS = EnumSet.allOf(Jetpack.class);

	protected static final String TAG_PARTICLE = "JetpackParticleType";
	public ParticleType defaultParticleType = ParticleType.DEFAULT;
	public PackModelType armorModel = PackModelType.FLAT;

	public final
	@Nonnull
	String baseName;
	public final
	@Nonnull
	String unlocalisedName;
	public final int tier;
	public int fuelCapacity;
	public int fuelPerTickIn;
	public int fuelPerTickOut;
	public int armorFuelPerHit;
	public int armorReduction;
	public int fuelUsage;

	public boolean usesFuel;
	public EnumRarity rarity;

	public double speedVertical;
	public double accelVertical;
	public double speedVerticalHover;
	public double speedVerticalHoverSlow;
	public double speedSideways;
	public double sprintSpeedModifier;
	public double sprintFuelModifier;
	public boolean emergencyHoverMode;

	private final
	@Nonnull
	List<String> jetpacks = new ArrayList<String>();

	private JetpackEIO(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity, ParticleType defaultParticleType, boolean usesFuel) {
		this(baseName, tier, defaultConfigKey, rarity);
		this.defaultParticleType = defaultParticleType;
		this.usesFuel = usesFuel;
	}

	private JetpackEIO(@Nonnull String baseName, int tier, String defaultConfigKey, EnumRarity rarity) {
		this.baseName = baseName;
		this.tier = tier;
		this.defaults = PackDefaults.get(defaultConfigKey);
		this.defaultParticleType = ParticleType.DEFAULT;
		this.unlocalisedName = "item.simplyjetpacks." + baseName;
		this.jetpacks.add(baseName);
		this.usesFuel = true;
		this.rarity = rarity;
		this.setArmorModel(PackModelType.JETPACK);
	}

	public
	@Nonnull
	String getBaseName() {
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

	public int getArmorFuelPerHit() {
		return armorFuelPerHit;
	}

	public int getArmorReduction() {
		return armorReduction;
	}

	public int getFuelUsage() {
		return fuelUsage;
	}

	public
	@Nonnull
	ItemStack getStackJetpack() {
		return getStackJetpack(1);
	}

	public
	@Nonnull
	ItemStack getStackJetpack(int size) {
		return new ItemStack(ModItems.itemJetpack, size, ordinal());
	}

	public
	@Nonnull
	List<String> getJetpacks() {
		return jetpacks;
	}

	@Override
	public String getName() {
		return baseName.toLowerCase(Locale.ENGLISH);
	}

	public EnumRarity getRarity() {
		return rarity;
	}

	public static @Nonnull JetpackEIO getTypeFromMeta(int meta) {
		return values()[meta >= 0 && meta < values().length ? meta : 0];
	}

	public static int getMetaFromType(Jetpack value) {
		return value.ordinal();
	}

	protected ParticleType getParticleType(ItemStack stack) {
		if (stack.getTagCompound() != null && stack.getTagCompound().hasKey(TAG_PARTICLE)) {
			int particle = NBTHelper.getInt(stack, TAG_PARTICLE);
			ParticleType particleType = ParticleType.values()[particle];
			if (particleType != null) {
				return particleType;
			}
		}
		NBTHelper.setInt(stack, TAG_PARTICLE, this.defaultParticleType.ordinal());
		return this.defaultParticleType;
	}

	public JetpackEIO setArmorModel(PackModelType armorModel) {
		this.armorModel = armorModel;
		return this;
	}

	public ParticleType getDisplayParticleType(ItemStack stack, ItemJetpack item, EntityLivingBase user) {
		boolean flyKeyDown = SyncHandler.isFlyKeyDown(user);
		if (item.isOn(stack) && item.getFuelStored(stack) > 0 && (flyKeyDown || item.isHoverModeOn(stack) && !user.onGround && user.motionY < 0)) {
			return this.getParticleType(stack);
		}
		return null;
	}

	public static void loadAllConfigs(Configuration config) {
		for (Jetpack pack : ALL_PACKS) {
			pack.loadConfig(config);
		}
	}

	public static void writeAllConfigsToNBT(NBTTagCompound tag) {
		if (ModItems.integrateEIO) {
			for (Jetpack pack : ALL_PACKS) {
				NBTTagCompound packTag = new NBTTagCompound();
				pack.writeConfigToNBT(packTag);
				tag.setTag(pack.defaults.section.id, packTag);
			}
		}
		else {
			for (Jetpack pack : ALL_PACKS) {
				NBTTagCompound packTag = new NBTTagCompound();
				pack.writeConfigToNBT(packTag);
				tag.setTag(pack.defaults.section.id, packTag);
			}
		}
	}

	public static void readAllConfigsFromNBT(NBTTagCompound tag) {
		for (Jetpack pack : ALL_PACKS) {
			NBTTagCompound packTag = tag.getCompoundTag(pack.defaults.section.id);
			pack.readConfigFromNBT(packTag);
		}
	}

	protected void loadConfig(Configuration config) {
		if (this.defaults.fuelCapacity != null) {
			this.fuelCapacity = config.get(this.defaults.section.name, "Fuel Capacity", this.defaults.fuelCapacity, "The maximum amount of fuel that this pack can hold.").setMinValue(1).getInt(this.defaults.fuelCapacity);
		}
		if (this.defaults.fuelUsage != null) {
			this.fuelUsage = config.get(this.defaults.section.name, "Fuel Usage", this.defaults.fuelUsage, "The amount of fuel that this pack uses every tick when used.").setMinValue(0).getInt(this.defaults.fuelUsage);
		}
		if (this.defaults.fuelPerTickIn != null) {
			this.fuelPerTickIn = config.get(this.defaults.section.name, "Fuel Per Tick In", this.defaults.fuelPerTickIn, "The amount of fuel that can be inserted into this pack per tick from external sources.").setMinValue(0).getInt(this.defaults.fuelPerTickIn);
		}
		if (this.defaults.fuelPerTickOut != null) {
			this.fuelPerTickOut = config.get(this.defaults.section.name, "Fuel Per Tick Out", this.defaults.fuelPerTickOut, "The amount of fuel that can be extracted from this pack per tick by external sources. Also determines how quickly Flux Packs can charge other items.").setMinValue(0).getInt(this.defaults.fuelPerTickOut);
		}
		if (this.defaults.armorReduction != null) {
			this.armorReduction = config.get(this.defaults.section.name, "Armor Reduction", this.defaults.armorReduction, "How well this pack can protect the user from damage, if armored. The higher the value, the stronger the armor will be.").setMinValue(0).setMaxValue(20).getInt(this.defaults.armorReduction);
		}
		if (this.defaults.armorFuelPerHit != null) {
			this.armorFuelPerHit = config.get(this.defaults.section.name, "Armor Fuel Per Hit", this.defaults.armorFuelPerHit, "How much fuel is lost from this pack when the user is hit, if armored.").setMinValue(0).getInt(this.defaults.armorFuelPerHit);
		}
		if (this.defaults.speedVertical != null) {
			this.speedVertical = config.get(this.defaults.section.name, "Vertical Speed", this.defaults.speedVertical, "The maximum vertical speed of this jetpack when flying.").setMinValue(0.0D).getDouble(this.defaults.speedVertical);
		}
		if (this.defaults.accelVertical != null) {
			this.accelVertical = config.get(this.defaults.section.name, "Vertical Acceleration", this.defaults.accelVertical, "The vertical acceleration of this jetpack when flying; every tick, this amount of vertical speed will be added until maximum speed is reached.").setMinValue(0.0D).getDouble(this.defaults.accelVertical);
		}
		if (this.defaults.speedVerticalHover != null) {
			this.speedVerticalHover = config.get(this.defaults.section.name, "Vertical Speed (Hover Mode)", this.defaults.speedVerticalHover, "The maximum vertical speed of this jetpack when flying in hover mode.").setMinValue(0.0D).getDouble(this.defaults.speedVerticalHover);
		}
		if (this.defaults.speedVerticalHoverSlow != null) {
			this.speedVerticalHoverSlow = config.get(this.defaults.section.name, "Vertical Speed (Hover Mode / Slow Descent)", this.defaults.speedVerticalHoverSlow, "The maximum vertical speed of this jetpack when slowly descending in hover mode.").setMinValue(0.0D).getDouble(this.defaults.speedVerticalHoverSlow);
		}
		if (this.defaults.speedSideways != null) {
			this.speedSideways = config.get(this.defaults.section.name, "Sideways Speed", this.defaults.speedSideways, "The speed of this jetpack when flying sideways. This is mostly noticeable in hover mode.").setMinValue(0.0D).getDouble(this.defaults.speedSideways);
		}
		if (this.defaults.sprintSpeedModifier != null) {
			this.sprintSpeedModifier = config.get(this.defaults.section.name, "Sprint Speed Multiplier", this.defaults.sprintSpeedModifier, "How much faster this jetpack will fly forward when sprinting. Setting this to 1.0 will make sprinting have no effect apart from the added speed from vanilla.").setMinValue(0.0D).getDouble(this.defaults.sprintSpeedModifier);
		}
		if (this.defaults.sprintFuelModifier != null) {
			this.sprintFuelModifier = config.get(this.defaults.section.name, "Sprint Fuel Usage Multiplier", this.defaults.sprintFuelModifier, "How much more energy this jetpack will use when sprinting. Setting this to 1.0 will make sprinting have no effect on energy usage.").setMinValue(0.0D).getDouble(this.defaults.sprintFuelModifier);
		}
		if (this.defaults.emergencyHoverMode != null) {
			this.emergencyHoverMode = config.get(this.defaults.section.name, "Emergency Hover Mode", this.defaults.emergencyHoverMode, "When enabled, this jetpack will activate hover mode automatically when the wearer is about to die from a fall.").getBoolean(this.defaults.emergencyHoverMode);
		}
	}

	protected void writeConfigToNBT(NBTTagCompound tag) {
		if (this.defaults.fuelCapacity != null) {
			tag.setInteger("FuelCapacity", this.fuelCapacity);
		}
		if (this.defaults.fuelUsage != null) {
			tag.setInteger("FuelUsage", this.fuelUsage);
		}
		if (this.defaults.fuelPerTickIn != null) {
			tag.setInteger("FuelPerTickIn", this.fuelPerTickIn);
		}
		if (this.defaults.fuelPerTickOut != null) {
			tag.setInteger("FuelPerTickOut", this.fuelPerTickOut);
		}
		if (this.defaults.armorReduction != null) {
			tag.setInteger("ArmorReduction", this.armorReduction);
		}
		if (this.defaults.speedVertical != null) {
			tag.setDouble("SpeedVertical", this.speedVertical);
		}
		if (this.defaults.accelVertical != null) {
			tag.setDouble("AccelVertical", this.accelVertical);
		}
		if (this.defaults.speedVerticalHover != null) {
			tag.setDouble("SpeedVerticalHover", this.speedVerticalHover);
		}
		if (this.defaults.speedVerticalHoverSlow != null) {
			tag.setDouble("SpeedVerticalHoverSlow", this.speedVerticalHoverSlow);
		}
		if (this.defaults.speedSideways != null) {
			tag.setDouble("SpeedSideways", this.speedSideways);
		}
		if (this.defaults.sprintSpeedModifier != null) {
			tag.setDouble("SprintSpeedModifier", this.sprintSpeedModifier);
		}
		if (this.defaults.sprintFuelModifier != null) {
			tag.setDouble("SprintFuelModifier", this.sprintFuelModifier);
		}
		if (this.defaults.emergencyHoverMode != null) {
			tag.setBoolean("EmergencyHoverMode", this.emergencyHoverMode);
		}
	}

	protected void readConfigFromNBT(NBTTagCompound tag) {
		if (this.defaults.fuelCapacity != null) {
			this.fuelCapacity = tag.getInteger("FuelCapacity");
		}
		if (this.defaults.fuelUsage != null) {
			this.fuelUsage = tag.getInteger("FuelUsage");
		}
		if (this.defaults.fuelPerTickIn != null) {
			this.fuelPerTickIn = tag.getInteger("FuelPerTickIn");
		}
		if (this.defaults.fuelPerTickOut != null) {
			this.fuelPerTickOut = tag.getInteger("FuelPerTickOut");
		}
		if (this.defaults.armorReduction != null) {
			this.armorReduction = tag.getInteger("ArmorReduction");
		}
		if (this.defaults.speedVertical != null) {
			this.speedVertical = tag.getDouble("SpeedVertical");
		}
		if (this.defaults.accelVertical != null) {
			this.accelVertical = tag.getDouble("AccelVertical");
		}
		if (this.defaults.speedVerticalHover != null) {
			this.speedVerticalHover = tag.getDouble("SpeedVerticalHover");
		}
		if (this.defaults.speedVerticalHoverSlow != null) {
			this.speedVerticalHoverSlow = tag.getDouble("SpeedVerticalHoverSlow");
		}
		if (this.defaults.speedSideways != null) {
			this.speedSideways = tag.getDouble("SpeedSideways");
		}
		if (this.defaults.sprintSpeedModifier != null) {
			this.sprintSpeedModifier = tag.getDouble("SprintSpeedModifier");
		}
		if (this.defaults.sprintFuelModifier != null) {
			this.sprintFuelModifier = tag.getDouble("SprintFuelModifier");
		}
		if (this.defaults.emergencyHoverMode != null) {
			this.emergencyHoverMode = tag.getBoolean("EmergencyHoverMode");
		}
	}
}
