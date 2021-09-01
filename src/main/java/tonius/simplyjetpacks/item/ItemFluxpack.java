package tonius.simplyjetpacks.item;

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
import tonius.simplyjetpacks.capability.IEnergyContainerItem;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemFluxpack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem, IHUDInfoProvider {

	public boolean hasEnergyIndicator = true;
	public boolean hasStateIndicators = true;
	public boolean isFluxBased = false;
	public boolean showTier = true;

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
			for (Fluxpack pack : Fluxpack.FLUXPACKS_SJ) {
				ItemHelper.addFluxpacks(pack, List);
			}
			if (ModItems.integrateEIO) {
				for (Fluxpack pack : Fluxpack.FLUXPACKS_EIO) {
					ItemHelper.addFluxpacks(pack, List);
				}
			}
			if (ModItems.integrateTE) {
				for (Fluxpack pack : Fluxpack.FLUXPACKS_TE) {
					ItemHelper.addFluxpacks(pack, List);
				}
			}
		}
	}

	@Override
	public void onArmorTick(@Nonnull World world, @Nonnull EntityPlayer player, @Nonnull ItemStack stack) {
		if (this.isOn(stack)) {
			this.chargeInventory(player, stack, this);
		}
	}

	@Override
	public int getItemEnchantability(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		return Fluxpack.values()[i].enchantability;
	}

	public void toggleState(boolean on, ItemStack stack, String type, String tag, EntityPlayer player, boolean showState) {
		NBTHelper.setBoolean(stack, tag, !on);
		if (player != null && showState) {
			ITextComponent state = on ? SJStringUtil.localizeNew("chat.", ".disabled") : SJStringUtil.localizeNew("chat.", ".enabled");
			state.setStyle(on ? new Style().setColor(TextFormatting.RED) : new Style().setColor(TextFormatting.GREEN));
			ITextComponent msg = SJStringUtil.localizeNew("chat.", ".fluxpack." + type, state);
			player.sendStatusMessage(msg, true);
		}
	}

	protected void chargeInventory(EntityLivingBase user, ItemStack stack, ItemFluxpack item) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		for (int j = 0; j <= 5; j++) {
			ItemStack currentStack = user.getItemStackFromSlot(JetpackUtil.fromSlot(j));
			if (currentStack != stack && getIEnergyStorage(currentStack) != null && (currentStack.hasCapability(CapabilityEnergy.ENERGY, null) || currentStack.getItem() instanceof IEnergyContainerItem)) {
				if (Fluxpack.values()[i].usesEnergy) {
					int energyToAdd = Math.min(item.useEnergy(stack, Fluxpack.values()[i].getEnergyPerTickOut(), true), getIEnergyStorage(currentStack).receiveEnergy(Fluxpack.values()[i].getEnergyPerTickOut(), true));
					item.useEnergy(stack, energyToAdd, false);
					getIEnergyStorage(currentStack).receiveEnergy(energyToAdd, false);
				} else {
					getIEnergyStorage(currentStack).receiveEnergy(Fluxpack.values()[i].getEnergyPerTickOut(), false);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		return Fluxpack.values()[i].getGlow() || super.hasEffect(itemStack);
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
		list.add(SJStringUtil.getEnergyText(item.getEnergyStored(stack), Fluxpack.values()[i].getEnergyCapacity(), !Fluxpack.values()[i].usesEnergy));
	}

	@SideOnly(Side.CLIENT)
	public void shiftInformation(ItemStack stack, List<String> list) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		list.add(SJStringUtil.getStateText(this.isOn(stack)));
		list.add(SJStringUtil.getEnergySendText(Fluxpack.values()[i].getEnergyPerTickOut()));
		if (Fluxpack.values()[i].getEnergyPerTickIn() > 0) {
			list.add(SJStringUtil.getEnergyReceiveText(Fluxpack.values()[i].getEnergyPerTickIn()));
		}
		SJStringUtil.addDescriptionLines(list, "fluxpack", TextFormatting.GREEN.toString());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addHUDInfo(List<String> list, ItemStack stack, boolean showEnergy, boolean showState) {
		if (showEnergy && this.hasEnergyIndicator) {
			list.add(this.getHUDEnergyInfo(stack, this));
		}
		if (showState && this.hasStateIndicators) {
			list.add(this.getHUDStatesInfo(stack));
		}
	}

	@SideOnly(Side.CLIENT)
	public String getHUDEnergyInfo(ItemStack stack, ItemFluxpack item) {
		int energy = item.getEnergyStored(stack);
		int maxEnergy = item.getMaxEnergyStored(stack);
		int percent = (int) Math.ceil((double) energy / (double) maxEnergy * 100D);
		return SJStringUtil.getHUDEnergyText("fluxpack", percent, energy);
	}

	@SideOnly(Side.CLIENT)
	public String getHUDStatesInfo(ItemStack stack) {
		return SJStringUtil.getHUDStateText(this.isOn(stack), null, null, null);
	}

	public boolean isOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, Constants.TAG_ENGINE_FLUX, true);
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
		if (!Fluxpack.values()[i].usesEnergy) {
			return false;
		}
		return this.hasEnergyIndicator;
	}

	@Override
	public double getDurabilityForDisplay(@Nonnull ItemStack stack) {
		double stored = this.getMaxEnergyStored(stack) - this.getEnergyStored(stack) + 1;
		double max = this.getMaxEnergyStored(stack) + 1;
		return stored / max;
	}

	@Nonnull
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		return Fluxpack.values()[i].unlocalisedName;
	}

	public int addEnergy(ItemStack stack, int maxAdd, boolean simulate) {
		return this.receiveEnergy(stack, maxAdd, simulate);
	}

	public int useEnergy(ItemStack stack, int maxUse, boolean simulate) {
		return this.extractEnergy(stack, maxUse, simulate);
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		int i = MathHelper.clamp(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, Fluxpack.values()[i].getEnergyPerTickIn()));
		if (!Fluxpack.values()[i].usesEnergy) {
			energyReceived = this.getMaxEnergyStored(container);
		}
		if (!simulate) {
			energy += energyReceived;
			NBTHelper.setInt(container, Constants.TAG_ENERGY, energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		int i = MathHelper.clamp(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyExtracted = Math.min(energy, Math.min(maxExtract, Fluxpack.values()[i].getEnergyPerTickOut()));
		if (!simulate) {
			energy -= energyExtracted;
			NBTHelper.setInt(container, Constants.TAG_ENERGY, energy);
		}
		return energyExtracted;
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		int i = MathHelper.clamp(container.getItemDamage(), 0, numItems - 1);
		return Fluxpack.values()[i].getEnergyCapacity();
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return NBTHelper.getInt(container, Constants.TAG_ENERGY, 0);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		int i = MathHelper.clamp(armor.getItemDamage(), 0, numItems - 1);
		if (Fluxpack.values()[i].getIsArmored() && !source.isUnblockable()) {
			int energyPerDamage = this.getEnergyPerDamage(armor);
			int maxAbsorbed = energyPerDamage > 0 ? 25 * (this.getEnergyStored(armor) / energyPerDamage) : 0;
			if (this.getEnergyStored(armor) < energyPerDamage) {
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
		if (Fluxpack.values()[i].getIsArmored() && Fluxpack.values()[i].usesEnergy) {
			if (this.isFluxBased && source.damageType.equals("flux")) {
				this.addEnergy(armor, damage * (source.getImmediateSource() == null ? Fluxpack.values()[i].getArmorEnergyPerHit() / 2 : this.getEnergyPerDamage(armor)), false);
			} else {
				this.useEnergy(armor, damage * this.getEnergyPerDamage(armor), false);
			}
		}
	}

	protected int getEnergyPerDamage(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		int fuelEfficiencyLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.FUEL_EFFICIENCY, stack), 0, 4);
		return (int) Math.round(Fluxpack.values()[i].getArmorEnergyPerHit() * (5 - fuelEfficiencyLevel) / 5.0D);
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
			SimplyJetpacks.PROXY.registerItemRenderer(this, i, Fluxpack.getTypeFromMeta(i).getBaseName());
		}
	}
}
