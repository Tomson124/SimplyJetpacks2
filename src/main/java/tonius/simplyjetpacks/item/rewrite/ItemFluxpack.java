package tonius.simplyjetpacks.item.rewrite;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.NonNullList;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.capability.CapabilityProviderEnergy;
import tonius.simplyjetpacks.capability.EnergyConversionStorage;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.IHUDInfoProvider;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.EquipmentSlotHelper;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;
import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFluxpack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem, IHUDInfoProvider {

	public boolean hasFuelIndicator = true;
	public boolean hasStateIndicators = true;
	public FuelType fuelType = FuelType.ENERGY;
	public boolean isFluxBased = false;
	public boolean showTier = true;
	public static final String TAG_ENERGY = "Energy";
	public static final String TAG_ON = "PackOn";

	public String name;
	private final int numItems;

	public ItemFluxpack(String name) {
		super(ArmorMaterial.IRON, 2, EntityEquipmentSlot.CHEST);
		this.name = name;
		this.setUnlocalizedName(SimplyJetpacks.PREFIX + name);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(ModCreativeTab.instance);
		this.setRegistryName(name);

		numItems = Fluxpack.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> List) {
		if (ModItems.integrateEIO) {
			for (Fluxpack pack : Fluxpack.ALL_FLUXPACKS) {
				ItemStack stack;
				if (pack.usesFuel) {
					List.add(new ItemStack(this, 1, pack.ordinal()));
				} else {
					stack = new ItemStack(this, 1, pack.ordinal());
					if (this instanceof ItemFluxpack) {
						((ItemFluxpack) this).addFuel(stack, ((ItemFluxpack) this).getMaxEnergyStored(stack), false);
					}

					List.add(stack);
				}
			}
		}
		else {
			Fluxpack pack = Fluxpack.CREATIVE_FLUXPACK;

				ItemStack stack;
				if (pack.usesFuel) {
					List.add(new ItemStack(this, 1, pack.ordinal()));
				} else {
					stack = new ItemStack(this, 1, pack.ordinal());
					if (this instanceof ItemFluxpack) {
						((ItemFluxpack) this).addFuel(stack, ((ItemFluxpack) this).getMaxEnergyStored(stack), false);
					}
					List.add(stack);
				}
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if(this.isOn(stack)) {
			this.chargeInventory(player, stack, this);
		}
	}

	public void toggleState(boolean on, ItemStack stack, String type, String tag, EntityPlayer player, boolean showInChat) {
		NBTHelper.setBoolean(stack, tag, !on);

		if (player != null && showInChat) {
			String color = on ? TextFormatting.RED.toString() : TextFormatting.GREEN.toString();
			type = type != null && !type.equals("") ? "chat." + this.name + "." + type + ".on" : "chat." + this.name + ".on";
			String msg = SJStringHelper.localize(type) + " " + color + SJStringHelper.localize("chat." + (on ? "disabled" : "enabled"));
			player.sendMessage(new TextComponentString(msg));
		}
	}

	protected void chargeInventory(EntityLivingBase user, ItemStack stack, ItemFluxpack item)
	{
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);

		if(this.fuelType == FuelType.ENERGY)
		{
			for(int j = 0; j <= 5; j++)
			{
				ItemStack currentStack = user.getItemStackFromSlot(EquipmentSlotHelper.fromSlot(j));
				if (currentStack != null && currentStack != stack && getIEnergyStorage(currentStack) != null && (currentStack.hasCapability(CapabilityEnergy.ENERGY, null) || currentStack.getItem() instanceof IEnergyContainerItem)) {
					if (Fluxpack.values()[i].usesFuel) {
						int energyToAdd = Math.min(item.useFuel(stack, Fluxpack.values()[i].getFuelPerTickOut(), true), getIEnergyStorage(currentStack).receiveEnergy(Fluxpack.values()[i].getFuelPerTickOut(), true));
						item.useFuel(stack, energyToAdd, false);
						getIEnergyStorage(currentStack).receiveEnergy(energyToAdd, false);
					}
					else {
						getIEnergyStorage(currentStack).receiveEnergy(Fluxpack.values()[i].getFuelPerTickOut(), false);
					}
				}
			}
		}
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
	@SuppressWarnings("unchecked")
	public void information(ItemStack stack, ItemFluxpack item, List list) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (this.showTier) {
			list.add(SJStringHelper.getTierText(Fluxpack.values()[i].getTier()));
		}
		list.add(SJStringHelper.getFuelText(this.fuelType, item.getFuelStored(stack), Fluxpack.values()[i].getFuelCapacity(), !Fluxpack.values()[i].usesFuel));
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void shiftInformation(ItemStack stack, List list) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);

		list.add(SJStringHelper.getStateText(this.isOn(stack)));
		list.add(SJStringHelper.getEnergySendText(Fluxpack.values()[i].getFuelPerTickOut()));
		if (Fluxpack.values()[i].getFuelPerTickIn() > 0) {
			list.add(SJStringHelper.getEnergyReceiveText(Fluxpack.values()[i].getFuelPerTickIn()));
		}
		SJStringHelper.addDescriptionLines(list, "fluxpack", TextFormatting.GREEN.toString());
	}

	// HUD info
	@Override
	@SideOnly(Side.CLIENT)
	public void addHUDInfo(List<String> list, ItemStack stack, boolean showFuel, boolean showState) {
		if (showFuel && this.hasFuelIndicator) {
			list.add(this.getHUDFuelInfo(stack, this));
		}
		if (showState && this.hasStateIndicators) {
			list.add(this.getHUDStatesInfo(stack));
		}
	}

	@SideOnly(Side.CLIENT)
	public String getHUDFuelInfo(ItemStack stack, ItemFluxpack item) {
		int fuel = item.getFuelStored(stack);
		int maxFuel = item.getMaxFuelStored(stack);
		int percent = (int) Math.ceil((double) fuel / (double) maxFuel * 100D);
		return SJStringHelper.getHUDFuelText(this.name, percent, this.fuelType, fuel);
	}

	@SideOnly(Side.CLIENT)
	public String getHUDStatesInfo(ItemStack stack) {
		return SJStringHelper.getHUDStateText(this.isOn(stack), null, null);
	}

	public boolean isOn(ItemStack stack) {
		return NBTHelper.getBooleanFallback(stack, TAG_ON, true);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (Fluxpack.values()[i].getRarity() != null) {
			return Fluxpack.values()[i].getRarity();
		}
		return super.getRarity(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (!Fluxpack.values()[i].usesFuel) {
			return false;
		}
		return this.hasFuelIndicator;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		double stored = this.getMaxFuelStored(stack) - this.getFuelStored(stack) + 1;
		double max = this.getMaxFuelStored(stack) + 1;
		return stored / max;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		return Fluxpack.values()[i].unlocalisedName;
	}

	// fuel
	public int getFuelStored(ItemStack stack) {
		return this.getEnergyStored(stack);
	}

	public int getMaxFuelStored(ItemStack stack) {
		return this.getMaxEnergyStored(stack);
	}

	public int addFuel(ItemStack stack, int maxAdd, boolean simulate) {
		return this.receiveEnergy(stack, maxAdd, simulate);
	}

	public int useFuel(ItemStack stack, int maxUse, boolean simulate) {
		return this.extractEnergy(stack, maxUse, simulate);
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		int i = MathHelper.clamp(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, Fluxpack.values()[i].getFuelPerTickIn()));
		if (!simulate) {
			energy += energyReceived;
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		int i = MathHelper.clamp(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyExtracted = Math.min(energy, Math.min(maxExtract, Fluxpack.values()[i].getFuelPerTickOut()));
		if (!simulate) {
			energy -= energyExtracted;
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyExtracted;
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		int i = MathHelper.clamp(container.getItemDamage(), 0, numItems - 1);
		return Fluxpack.values()[i].getFuelCapacity();
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return NBTHelper.getInt(container, TAG_ENERGY);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		int i = MathHelper.clamp(armor.getItemDamage(), 0, numItems - 1);
		if (Fluxpack.values()[i].getIsArmored() && !source.isUnblockable()) {
			if (this.isFluxBased && source.damageType.equals("flux")) {
				return new ArmorProperties(0, 0.5D, Integer.MAX_VALUE);
			}
			int energyPerDamage = this.getFuelPerDamage(armor);
			int maxAbsorbed = energyPerDamage > 0 ? 25 * (this.getFuelStored(armor) / energyPerDamage) : 0;
			return new ArmorProperties(0, 0.85D * (Fluxpack.values()[i].getArmorReduction() / 20.0D), maxAbsorbed);
		}
		return new ArmorProperties(0, 1, 0);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		int i = MathHelper.clamp(armor.getItemDamage(), 0, numItems - 1);
		if (Fluxpack.values()[i].getIsArmored()) {
			if (this.getFuelStored(armor) >= Fluxpack.values()[i].getArmorFuelPerHit()) {
				return Fluxpack.values()[i].getArmorReduction();
			}
		}
		return 0;
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		if (Config.enableArmor3DModels) {
			ModelBiped model = RenderUtils.getArmorModel(Fluxpack.values()[i], entityLiving);
			if (model != null) {
				return model;
			}
		}
		return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		String flat = Config.enableArmor3DModels || Fluxpack.values()[i].armorModel == PackModelType.FLAT ? "" : ".flat";
		return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + Fluxpack.values()[i].getBaseName() + flat + ".png";
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
		int i = MathHelper.clamp(armor.getItemDamage(), 0, numItems - 1);
		if (Fluxpack.values()[i].getIsArmored() && Fluxpack.values()[i].usesFuel) {
			if (this.fuelType == FuelType.ENERGY && this.isFluxBased && source.damageType.equals("flux")) {
				this.addFuel(armor, damage * (source.getImmediateSource() == null ? Fluxpack.values()[i].getArmorFuelPerHit() / 2 : this.getFuelPerDamage(armor)), false);
			} else {
				this.useFuel(armor, damage * this.getFuelPerDamage(armor), false);
			}
		}
	}

	// armor
	protected int getFuelPerDamage(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (ModEnchantments.fuelEffeciency == null) {
			return Fluxpack.values()[i].getArmorFuelPerHit();
		}

		int fuelEfficiencyLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		return (int) Math.round(Fluxpack.values()[i].getArmorFuelPerHit() * (5 - fuelEfficiencyLevel) / 5.0D);
	}

	public IEnergyStorage getIEnergyStorage(ItemStack chargeItem) {

		if (chargeItem.hasCapability(CapabilityEnergy.ENERGY, null)) {
			return chargeItem.getCapability(CapabilityEnergy.ENERGY, null);
		}
		else if (chargeItem.getItem() instanceof IEnergyContainerItem) {
			return new EnergyConversionStorage((IEnergyContainerItem) chargeItem.getItem(), chargeItem);
		}

		return null;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new CapabilityProviderEnergy<>(new EnergyConversionStorage(this, stack), CapabilityEnergy.ENERGY, null);
	}

	public void registerItemModel() {
		for (int i = 0; i < numItems; i++) {
			SimplyJetpacks.proxy.registerItemRenderer(this, i, Fluxpack.getTypeFromMeta(i).getBaseName());
		}
	}
}
