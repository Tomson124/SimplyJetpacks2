package tonius.simplyjetpacks.item;

import cofh.core.item.IEnchantableItem;
import cofh.redstoneflux.api.IEnergyContainerItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thundr.redstonerepository.api.IArmorEnderium;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;
import tonius.simplyjetpacks.util.StackUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class ItemPack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem, IHUDInfoProvider, IArmorEnderium, IEnchantableItem {

	public String name; //BaseName
	public static final String TAG_ENERGY = "Energy";
	public static final String TAG_ON = "PackOn";

	public FuelType fuelType = FuelType.ENERGY;
	public boolean hasFuelIndicator = true;
	public boolean hasStateIndicators = true;
	public boolean showTier = true;
	public boolean isFluxBased = false;

	public ItemPack(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.name = name;
		this.setUnlocalizedName(SimplyJetpacks.PREFIX + name);
		this.setCreativeTab(SimplyJetpacks.creativeTab);
		this.setRegistryName(name);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		if (Objects.requireNonNull(Jetpack.getTypeFromName(name)).getRarity() != null) {
			return Objects.requireNonNull(Jetpack.getTypeFromName(name)).getRarity();
		}
		return super.getRarity(stack);
	}

	@Override
	@Optional.Method(modid = "cofhcore")
	public boolean canEnchant(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantment.getEnchantmentByLocation("cofhcore:holding");
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		int energy = this.getEnergyStored(container);
		int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, Objects.requireNonNull(Jetpack.getTypeFromName(name)).getFuelPerTickIn()));
		if (!Objects.requireNonNull(Jetpack.getTypeFromName(name)).usesFuel) {
			energyReceived = this.getMaxEnergyStored(container);
		}
		if (!simulate) {
			energy += energyReceived;
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		int energy = this.getEnergyStored(container);
		int energyExtracted = Math.min(energy, Math.min(maxExtract, Objects.requireNonNull(Jetpack.getTypeFromName(name)).getFuelPerTickOut() == 0 ? Objects.requireNonNull(Jetpack.getTypeFromName(name)).getFuelUsage() : Objects.requireNonNull(Jetpack.getTypeFromName(name)).getFuelPerTickOut()));
		if (!simulate) {
			energy -= energyExtracted;
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return NBTHelper.getInt(container, TAG_ENERGY, 0);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		int id = StackUtil.getEnchantmentIdByName("holding", container);
		if(id != -1){
			return Objects.requireNonNull(Jetpack.getTypeFromName(name)).getFuelCapacity() + Objects.requireNonNull(Jetpack.getTypeFromName(name)).getFuelCapacity() * StackUtil.getEnchantmentLevel(id, container) / 2;
		}
		return Objects.requireNonNull(Jetpack.getTypeFromName(name)).getFuelCapacity();
	}

	protected int getFuelPerDamage(ItemStack stack) {
		if (ModEnchantments.fuelEffeciency == null) {
			return Objects.requireNonNull(Jetpack.getTypeFromName(name)).getArmorFuelPerHit();
		}
		int fuelEfficiencyLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		return (int) Math.round(Objects.requireNonNull(Jetpack.getTypeFromName(name)).getArmorFuelPerHit() * (5 - fuelEfficiencyLevel) / 5.0D);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
		if (Objects.requireNonNull(Jetpack.getTypeFromName(name)).getIsArmored() && !source.isUnblockable()) {
			int energyPerDamage = this.getFuelPerDamage(armor);
			int maxAbsorbed = energyPerDamage > 0 ? 25 * (this.getEnergyStored(armor) / energyPerDamage) : 0;
			if (this.getEnergyStored(armor) < energyPerDamage) {
				return new ArmorProperties(0, 0.65D * (Objects.requireNonNull(Jetpack.getTypeFromName(name)).getArmorReduction() / 20.0D), Integer.MAX_VALUE);
			}
			if (this.isFluxBased && source.damageType.equals("flux")) {
				return new ArmorProperties(0, 0.5D, Integer.MAX_VALUE);
			}
			return new ArmorProperties(0, 0.85D * (Objects.requireNonNull(Jetpack.getTypeFromName(name)).getArmorReduction() / 20.0D), maxAbsorbed);
		}
		return new ArmorProperties(0, 0, 0);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
		return 0; //TODO: Look into it
	}

	@Override
	public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {
		if (Objects.requireNonNull(Jetpack.getTypeFromName(name)).getIsArmored()) {
			if(Objects.requireNonNull(Jetpack.getTypeFromName(name)).usesFuel) {
				if (this.fuelType == FuelType.ENERGY && this.isFluxBased && source.damageType.equals("flux")) {
					this.receiveEnergy(stack, damage * (source.getImmediateSource() == null ? Objects.requireNonNull(Jetpack.getTypeFromName(name)).getArmorFuelPerHit() / 2 : this.getFuelPerDamage(stack)), false);
				} else {
					this.extractEnergy(stack, damage * this.getFuelPerDamage(stack), false);
				}
			}
		}
	}

	@Optional.Method(modid = "redstonerepository")
	public boolean isEnderiumArmor(ItemStack stack){
		return name.equals("jetpack_TE5_enderium");
	}

	@Override
	public void addHUDInfo(List<String> list, ItemStack stack, boolean showFuel, boolean showState) {
		if (showFuel && this.hasFuelIndicator) {
			list.add(this.getHUDFuelInfo(stack, this));
		}
		if (showState && this.hasStateIndicators) {
			list.add(this.getHUDStatesInfo(stack));
		}
	}

	@SideOnly(Side.CLIENT)
	public String getHUDFuelInfo(ItemStack stack, ItemPack item) {
		int fuel = item.getEnergyStored(stack);
		int maxFuel = item.getMaxEnergyStored(stack);
		int percent = (int) Math.ceil((double) fuel / (double) maxFuel * 100D);
		return SJStringHelper.getHUDFuelText(this.name, percent, this.fuelType, fuel);
	}

	@SideOnly(Side.CLIENT)
	public String getHUDStatesInfo(ItemStack stack) {
		Boolean engine = this.isOn(stack);
		return SJStringHelper.getHUDStateText(engine, null, null);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		information(stack, this, tooltip);
		if (SJStringHelper.canShowDetails()) {
			shiftInformation(stack, tooltip);
		} else {
			tooltip.add(SJStringHelper.getShiftText());
		}
	}

	@SideOnly(Side.CLIENT)
	public void shiftInformation(ItemStack stack, List list) {}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void information(ItemStack stack, ItemPack item, List list) {
		if (this.showTier) {
			list.add(SJStringHelper.getTierText(Objects.requireNonNull(Jetpack.getTypeFromName(name)).getTier()));
		}
		list.add(SJStringHelper.getFuelText(this.fuelType, item.getEnergyStored(stack), item.getMaxEnergyStored(stack), !Objects.requireNonNull(Jetpack.getTypeFromName(name)).usesFuel));
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		if (!Objects.requireNonNull(Jetpack.getTypeFromName(name)).usesFuel) {
			return false;
		}
		return this.hasFuelIndicator;
	}

	public boolean isOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_ON, true);
	}
}
