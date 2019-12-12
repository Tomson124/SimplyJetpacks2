package tonius.simplyjetpacks.item;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.nbt.CompoundNBT;
import tonius.simplyjetpacks.client.handler.IModelRegister;

import java.util.List;

public class ItemPack extends ArmorItem implements IModelRegister, IHUDInfoProvider {

	public static final String TAG_ON = "PackOn";

	public ItemPack(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void registerModels() {

	}

	@Override
	public void addHUDInfo(List<String> list, ItemStack stack, boolean showFuel, boolean showState) {

	}

	/*public String name; //BaseName
	public static final String TAG_ENERGY = "Energy";
	public static final String TAG_ON = "PackOn";

	public FuelType fuelType = FuelType.ENERGY;
	public boolean hasFuelIndicator = true;
	public boolean hasStateIndicators = true;
	public boolean showTier = true;
	public boolean isFluxBased = false;

	protected static final UUID ARMOR_MODIFIER = UUID.fromString("0819e549-a0f9-49d3-a199-53662799c67b");

	public ItemPack(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.name = name;
		this.setUnlocalizedName(SimplyJetpacks.PREFIX + name);
		this.setCreativeTab(SimplyJetpacks.creativeTab);
		this.setRegistryName(new ResourceLocation(SimplyJetpacks.MODID, name));
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		if (Objects.requireNonNull(Packs.getTypeFromName(name)).getRarity() != null) {
			return Objects.requireNonNull(Packs.getTypeFromName(name)).getRarity();
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
		int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, Objects.requireNonNull(Packs.getTypeFromName(name)).getFuelPerTickIn()));
		if (!Objects.requireNonNull(Packs.getTypeFromName(name)).usesFuel) {
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
		int energyExtracted = Math.min(energy, Math.min(maxExtract, Objects.requireNonNull(Packs.getTypeFromName(name)).getFuelPerTickOut() == 0 ? Objects.requireNonNull(Packs.getTypeFromName(name)).getFuelUsage() : Objects.requireNonNull(Packs.getTypeFromName(name)).getFuelPerTickOut()));
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
			return Objects.requireNonNull(Packs.getTypeFromName(name)).getFuelCapacity() + Objects.requireNonNull(Packs.getTypeFromName(name)).getFuelCapacity() * StackUtil.getEnchantmentLevel(id, container) / 2;
		}
		return Objects.requireNonNull(Packs.getTypeFromName(name)).getFuelCapacity();
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		double stored = this.getMaxEnergyStored(stack) - this.getEnergyStored(stack) + 1;
		double max = this.getMaxEnergyStored(stack) + 1;
		return stored / max;
	}

	protected int getFuelPerDamage(ItemStack stack) {
		if (ModEnchantments.fuelEffeciency == null) {
			return Objects.requireNonNull(Packs.getTypeFromName(name)).getArmorFuelPerHit();
		}
		int fuelEfficiencyLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		return (int) Math.round(Objects.requireNonNull(Packs.getTypeFromName(name)).getArmorFuelPerHit() * (5 - fuelEfficiencyLevel) / 5.0D);
	}

	public IEnergyStorage getIEnergyStorage(ItemStack chargeItem) {

		if (chargeItem.hasCapability(CapabilityEnergy.ENERGY, null)) {
			return chargeItem.getCapability(CapabilityEnergy.ENERGY, null);
		} else if (chargeItem.getItem() instanceof IEnergyContainerItem) {
			return new EnergyConversionStorage((IEnergyContainerItem) chargeItem.getItem(), chargeItem);
		}

		return null;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new CapabilityProviderEnergy<>(new EnergyConversionStorage(this, stack), CapabilityEnergy.ENERGY, null);
	}

	protected void chargeInventory(EntityLivingBase user, ItemStack stack, ItemPack item) {
		if (this.fuelType == FuelType.ENERGY) {
			for (int j = 0; j <= 5; j++) {
				ItemStack currentStack = user.getItemStackFromSlot(EquipmentSlotHelper.fromSlot(j));
				if (currentStack != null && currentStack != stack && getIEnergyStorage(currentStack) != null && (currentStack.hasCapability(CapabilityEnergy.ENERGY, null) || currentStack.getItem() instanceof IEnergyContainerItem)) {
					if (Packs.getTypeFromName(name).usesFuel) {
						int energyToAdd = Math.min(item.extractEnergy(stack, Packs.getTypeFromName(name).getFuelPerTickOut(), true), getIEnergyStorage(currentStack).receiveEnergy(Packs.getTypeFromName(name).getFuelPerTickOut(), true));
						item.extractEnergy(stack, energyToAdd, false);
						getIEnergyStorage(currentStack).receiveEnergy(energyToAdd, false);
					}
					else {
						getIEnergyStorage(currentStack).receiveEnergy(Packs.getTypeFromName(name).getFuelPerTickOut(), false);
					}
				}
			}
		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
		if (Objects.requireNonNull(Packs.getTypeFromName(name)).getIsArmored() && !source.isUnblockable()) {
			int energyPerDamage = this.getFuelPerDamage(armor);
			int maxAbsorbed = energyPerDamage > 0 ? 25 * (this.getEnergyStored(armor) / energyPerDamage) : 0;
			if (this.getEnergyStored(armor) < energyPerDamage) {
				return new ArmorProperties(0, 0.65D * (Objects.requireNonNull(Packs.getTypeFromName(name)).getArmorReduction() / 20.0D), Integer.MAX_VALUE);
			}
			if (this.isFluxBased && source.damageType.equals("flux")) {
				return new ArmorProperties(0, 0.5D, Integer.MAX_VALUE);
			}
			return new ArmorProperties(0, 0.85D * (Objects.requireNonNull(Packs.getTypeFromName(name)).getArmorReduction() / 20.0D), maxAbsorbed);
		}
		return new ArmorProperties(0, 0, 0);
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(slot);
		if (!Packs.getTypeFromName(name).getIsArmored()) {
			multimap.clear();
			return multimap;
		}
		if (slot == EntityEquipmentSlot.CHEST) {
			multimap.clear();
			multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIER, "Armor modifier", (double) Packs.getTypeFromName(name).getArmorReduction(), 0));
		}
		return multimap;
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
		return 0; //TODO: Look into it
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if (Config.enableArmor3DModels) {
			ModelBiped model = RenderUtils.getArmorModel(Packs.getTypeFromName(name), entityLiving);
			if (model != null) {
				return model;
			}
		}
		return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		String flat = Config.enableArmor3DModels || Packs.getTypeFromName(name).armorModel == PackModelType.FLAT ? "" : ".flat";
		return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + name + flat + ".png";
	}

	@Override
	public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {
		if (Objects.requireNonNull(Packs.getTypeFromName(name)).getIsArmored()) {
			if (Objects.requireNonNull(Packs.getTypeFromName(name)).usesFuel) {
				if (this.fuelType == FuelType.ENERGY && this.isFluxBased && source.damageType.equals("flux")) {
					this.receiveEnergy(stack, damage * (source.getImmediateSource() == null ? Objects.requireNonNull(Packs.getTypeFromName(name)).getArmorFuelPerHit() / 2 : this.getFuelPerDamage(stack)), false);
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
		return SJStringHelper.getHUDFuelText(percent, this.fuelType, fuel);
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
			list.add(SJStringHelper.getTierText(Objects.requireNonNull(Packs.getTypeFromName(name)).getTier()));
		}
		list.add(SJStringHelper.getFuelText(this.fuelType, item.getEnergyStored(stack), item.getMaxEnergyStored(stack), !Objects.requireNonNull(Packs.getTypeFromName(name)).usesFuel));
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		if (!Objects.requireNonNull(Packs.getTypeFromName(name)).usesFuel) {
			return false;
		}
		return this.hasFuelIndicator;
	}

	public boolean isOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_ON, true);
	}

	@Override
	public void registerModels() {
		SimplyJetpacks.proxy.registerItemRenderer(this, getRegistryName());
	}*/
}
