package tonius.simplyjetpacks.item;

import cofh.redstoneflux.api.IEnergyContainerItem;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.capability.CapabilityProviderEnergy;
import tonius.simplyjetpacks.capability.EnergyConversionStorage;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.EquipmentSlotHelper;
import tonius.simplyjetpacks.util.ItemHelper;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringUtil;

import javax.annotation.Nonnull;
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
		this.setCreativeTab(SimplyJetpacks.tabSimplyJetpacks);
		this.setRegistryName(name);

		numItems = Fluxpack.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(@Nonnull CreativeTabs creativeTabs, @Nonnull NonNullList<ItemStack> List) {
		if (isInCreativeTab(creativeTabs)) {
			for (Fluxpack pack : Fluxpack.SJ_FLUXPACKS) {
				ItemHelper.addFluxpacks(pack, List);
			}
			if (ModItems.integrateEIO) {
				for (Fluxpack pack : Fluxpack.EIO_FLUXPACKS) {
					ItemHelper.addFluxpacks(pack, List);
				}
			}
			if (ModItems.integrateTE) {
				for (Fluxpack pack : Fluxpack.TE_FLUXPACKS) {
					ItemHelper.addFluxpacks(pack, List);
				}
			}
		}
	}

	@Override
	public void onArmorTick(@Nonnull World world, @Nonnull EntityPlayer player, @Nonnull ItemStack stack) {
		if(this.isOn(stack)) {
			this.chargeInventory(player, stack, this);
		}
	}

	public void toggleState(boolean on, ItemStack stack, String type, String tag, EntityPlayer player, boolean showState) {
		NBTHelper.setBoolean(stack, tag, !on);
		if (player != null && showState) {
			ITextComponent state = on ? SJStringUtil.localizeNew("chat.", ".disabled") : SJStringUtil.localizeNew("chat.", ".enabled");
			if (on) {
				state.setStyle(new Style().setColor(TextFormatting.RED));
			} else {
				state.setStyle(new Style().setColor(TextFormatting.GREEN));
			}
			ITextComponent msg = SJStringUtil.localizeNew("chat.", ".fluxpack." + type, state);
			player.sendStatusMessage(msg, true);
		}
	}

	protected void chargeInventory(EntityLivingBase user, ItemStack stack, ItemFluxpack item) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (this.fuelType == FuelType.ENERGY) {
			for (int j = 0; j <= 5; j++) {
				ItemStack currentStack = user.getItemStackFromSlot(EquipmentSlotHelper.fromSlot(j));
				if (currentStack != stack && getIEnergyStorage(currentStack) != null && (currentStack.hasCapability(CapabilityEnergy.ENERGY, null) || currentStack.getItem() instanceof IEnergyContainerItem)) {
					if (Fluxpack.values()[i].usesFuel) {
						int energyToAdd = Math.min(item.useFuel(stack, Fluxpack.values()[i].getFuelPerTickOut(), true), getIEnergyStorage(currentStack).receiveEnergy(Fluxpack.values()[i].getFuelPerTickOut(), true));
						item.useFuel(stack, energyToAdd, false);
						getIEnergyStorage(currentStack).receiveEnergy(energyToAdd, false);
					} else {
						getIEnergyStorage(currentStack).receiveEnergy(Fluxpack.values()[i].getFuelPerTickOut(), false);
					}
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		if (Fluxpack.values()[i].getGlow()) {
			return true;
		}
		return super.hasEffect(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
		information(stack, this, tooltip);
		if (SJStringUtil.canShowDetails()) {
			shiftInformation(stack, tooltip);
		} else {
			tooltip.add(SJStringUtil.getShiftText());
		}
	}

	@SideOnly(Side.CLIENT)
	public void information(ItemStack stack, ItemFluxpack item, List<String> list) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (this.showTier) {
			list.add(SJStringUtil.getTierText(Fluxpack.values()[i].getTier()));
		}
		list.add(SJStringUtil.getFuelText(this.fuelType, item.getFuelStored(stack), Fluxpack.values()[i].getFuelCapacity(), !Fluxpack.values()[i].usesFuel));
	}

	@SideOnly(Side.CLIENT)
	public void shiftInformation(ItemStack stack, List<String> list) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		list.add(SJStringUtil.getStateText(this.isOn(stack)));
		list.add(SJStringUtil.getEnergySendText(Fluxpack.values()[i].getFuelPerTickOut()));
		if (Fluxpack.values()[i].getFuelPerTickIn() > 0) {
			list.add(SJStringUtil.getEnergyReceiveText(Fluxpack.values()[i].getFuelPerTickIn()));
		}
		SJStringUtil.addDescriptionLines(list, "fluxpack", TextFormatting.GREEN.toString());
	}

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
		return SJStringUtil.getHUDFuelText("fluxpack", percent, fuel);
	}

	@SideOnly(Side.CLIENT)
	public String getHUDStatesInfo(ItemStack stack) {
		return SJStringUtil.getHUDStateText(this.isOn(stack), null, null);
	}

	public boolean isOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_ON, true);
	}

	@Nonnull
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
	public double getDurabilityForDisplay(@Nonnull ItemStack stack) {
		double stored = this.getMaxFuelStored(stack) - this.getFuelStored(stack) + 1;
		double max = this.getMaxFuelStored(stack) + 1;
		return stored / max;
	}

	@Nonnull
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		return Fluxpack.values()[i].unlocalisedName;
	}

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
		if (!Fluxpack.values()[i].usesFuel) {
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
		return NBTHelper.getInt(container, TAG_ENERGY, 0);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		int i = MathHelper.clamp(armor.getItemDamage(), 0, numItems - 1);
		if (Fluxpack.values()[i].getIsArmored() && !source.isUnblockable()) {
			int energyPerDamage = this.getFuelPerDamage(armor);
			int maxAbsorbed = energyPerDamage > 0 ? 25 * (this.getFuelStored(armor) / energyPerDamage) : 0;
			if (this.getFuelStored(armor) < energyPerDamage) {
				return new ArmorProperties(0, 0.65D * (Fluxpack.values()[i].getArmorReduction() / 20.0D), Integer.MAX_VALUE);
			}
			if (this.isFluxBased && source.damageType.equals("flux")) {
				return new ArmorProperties(0, 0.5D, Integer.MAX_VALUE);
			}
			return new ArmorProperties(0, 0.85D * (Fluxpack.values()[i].getArmorReduction() / 20.0D), maxAbsorbed);
		}
		return new ArmorProperties(0, 1, 0);
	}

	@Nonnull
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EntityEquipmentSlot slot, ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(slot);
		if (!Fluxpack.values()[i].getIsArmored()) {
			multimap.clear();
			return multimap;
		}
		if (slot == EntityEquipmentSlot.CHEST) {
			multimap.clear();
			multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ItemJetpack.ARMOR_MODIFIER, "Armor Modifier", Fluxpack.values()[i].getArmorReduction(), 0));
		}
		return multimap;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
		return 0;
	}

	@Override
	public ModelBiped getArmorModel(@Nonnull EntityLivingBase entityLiving, ItemStack itemStack, @Nonnull EntityEquipmentSlot armorSlot, @Nonnull ModelBiped _default) {
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
	public String getArmorTexture(ItemStack stack, @Nonnull Entity entity, @Nonnull EntityEquipmentSlot slot, @Nonnull String type) {
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
	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, NBTTagCompound nbt) {
		return new CapabilityProviderEnergy<>(new EnergyConversionStorage(this, stack), CapabilityEnergy.ENERGY, null);
	}

	public void registerItemModel() {
		for (int i = 0; i < numItems; i++) {
			SimplyJetpacks.proxy.registerItemRenderer(this, i, Fluxpack.getTypeFromMeta(i).getBaseName());
		}
	}
}
