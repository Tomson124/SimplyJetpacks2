package tonius.simplyjetpacks.item;

import cofh.core.init.CoreProps;
import cofh.core.item.IEnchantableItem;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thundr.redstonerepository.api.IArmorEnderium;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.capability.CapabilityProviderEnergy;
import tonius.simplyjetpacks.capability.EnergyConversionStorage;
import tonius.simplyjetpacks.capability.IEnergyContainerItem;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static tonius.simplyjetpacks.handler.LivingTickHandler.floatingTickCount;

@Optional.Interface(iface = "thundr.redstonerepository.api.IArmorEnderium", modid = "redstonerepository")
@Optional.Interface(iface = "cofh.core.item.IEnchantableItem", modid = "cofhcore")
public class ItemJetpack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem, IHUDInfoProvider, IArmorEnderium, IEnchantableItem {

	public static final String TAG_ENERGY = "Energy";
	public static final String TAG_ENGINE = "JetpackEngine";
	public static final String TAG_HOVER = "JetpackHover";
	public static final String TAG_E_HOVER = "JetpackEHover";
	public static final String TAG_CHARGER = "JetpackCharger";

	protected static final UUID ARMOR_MODIFIER = UUID.fromString("0819e549-a0f9-49d3-a199-53662799c67b");

	public String name;
	public boolean showTier = true;
	public boolean hasEnergyIndicator = true;
	public boolean hasStateIndicators = true;
	public boolean isFluxBased = false;
	private final int numItems;

	public ItemJetpack(String name) {
		//super(EnumHelper.addArmorMaterial("JETPACK_SJ", "jetpack", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0), 2, EntityEquipmentSlot.CHEST);
		super(ArmorMaterial.IRON, 2, EntityEquipmentSlot.CHEST);
		this.name = name;
		this.setUnlocalizedName(SimplyJetpacks.PREFIX + name);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(SimplyJetpacks.tabSimplyJetpacks);
		this.setRegistryName(name);
		numItems = Jetpack.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(@Nonnull CreativeTabs creativeTabs, @Nonnull NonNullList<ItemStack> List) {
		if (isInCreativeTab(creativeTabs)) {
			for (Jetpack pack : Jetpack.JETPACKS_SJ) {
				ItemHelper.addJetpacks(pack, List);
			}
			if (ModItems.integrateVanilla) {
				for (Jetpack pack : Jetpack.JETPACKS_VANILLA) {
					ItemStack stack;
					stack = new ItemStack(this, 1, pack.ordinal());
					List.add(stack);
				}
			}
			if (ModItems.integrateEIO) {
				for (Jetpack pack : Jetpack.JETPACKS_EIO) {
					ItemHelper.addJetpacks(pack, List);
				}
			}
			if (ModItems.integrateTE) {
				for (Jetpack pack : Jetpack.JETPACKS_TE) {
					ItemHelper.addJetpacks(pack, List);
				}
			}
			if (ModItems.integrateMek) {
				for (Jetpack pack : Jetpack.JETPACKS_MEK) {
					ItemHelper.addJetpacks(pack, List);
				}
			}
			if (ModItems.integrateIE) {
				for (Jetpack pack : Jetpack.JETPACKS_IE) {
					ItemHelper.addJetpacks(pack, List);
				}
			}
			if (ModItems.integrateRR) {
				for (Jetpack pack : Jetpack.JETPACKS_RR) {
					ItemHelper.addJetpacks(pack, List);
				}
			}
		}
	}

	@Override
	public void onArmorTick(@Nonnull World world, @Nonnull EntityPlayer player, @Nonnull ItemStack stack) {
		flyUser(player, stack, this, false);
		if (this.canCharge(stack) && this.isChargerOn(stack)) {
			chargeInventory(player, stack, this);
		}
	}

	public void toggleState(boolean on, ItemStack stack, String type, String tag, EntityPlayer player, boolean showState) {
		NBTHelper.setBoolean(stack, tag, !on);
		if (player != null && showState) {
			ITextComponent state = on ? SJStringUtil.localizeNew("chat.", ".disabled") : SJStringUtil.localizeNew("chat.", ".enabled");
			state.setStyle(on ? new Style().setColor(TextFormatting.RED) : new Style().setColor(TextFormatting.GREEN));
			ITextComponent msg = SJStringUtil.localizeNew("chat.", ".jetpack." + type, state);
			player.sendStatusMessage(msg, true);
		}
	}

	public boolean isJetplate(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		return Jetpack.values()[i].getTier() == 5;
	}

    public boolean canCharge(ItemStack stack) {
        int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
        return Jetpack.values()[i].chargerMode;
    }

	public boolean canEHover(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		return Jetpack.values()[i].emergencyHoverMode;
	}

	public boolean isChargerOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_CHARGER, true);
	}

	public void setParticleType(ItemStack stack, ParticleType particle) {
		NBTHelper.setInt(stack, Jetpack.TAG_PARTICLE, particle.ordinal());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		return Jetpack.values()[i].getGlow() || super.hasEffect(itemStack);
	}

	@Nonnull
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (Jetpack.values()[i].getRarity() != null) {
			return Jetpack.values()[i].getRarity();
		}
		return super.getRarity(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (!Jetpack.values()[i].usesEnergy) {
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

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		Jetpack jetpack = Jetpack.values()[i];
		return jetpack == Jetpack.JETPLATE_TE_5_ENDERIUM ? CoreProps.RGB_DURABILITY_ENDER : super.getRGBDurabilityForDisplay(stack);
	}

	@Nonnull
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		return Jetpack.values()[i].unlocalisedName;
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
	public void information(ItemStack stack, ItemJetpack item, @Nonnull List<String> list) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (this.showTier) {
			list.add(SJStringUtil.getTierText(Jetpack.values()[i].getTier()));
		}
		list.add(SJStringUtil.getEnergyText(item.getEnergyStored(stack), item.getMaxEnergyStored(stack), !Jetpack.values()[i].usesEnergy));
	}

	@SideOnly(Side.CLIENT)
	public void shiftInformation(ItemStack stack, @Nonnull List<String> list) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		list.add(SJStringUtil.getStateText(this.isOn(stack)));
		list.add(SJStringUtil.getHoverModeText(this.isHoverModeOn(stack)));
		if (Jetpack.values()[i].getEnergyUsage() > 0) {
			if (Jetpack.values()[i].getBaseName().contains("enderium")) {
				list.add(SJStringUtil.getEnderiumBonusText());
			}
			list.add(SJStringUtil.getEnergyUsageText(this.getEnergyUsage(stack)));
		}
		list.add(SJStringUtil.getParticlesText(Jetpack.values()[i].getParticleType(stack)));
		SJStringUtil.addDescriptionLines(list, "jetpack", TextFormatting.GREEN.toString());
	}

	@Override
	public int getItemEnchantability(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		return Jetpack.values()[i].enchantability;
	}

	public boolean isOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_ENGINE, true);
	}

	protected int getEnergyUsage(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		int baseUsage = Jetpack.values()[i].getEnergyUsage();
		if (Jetpack.values()[i].getBaseName().contains("enderium")) {
			float bonus = (int) (Config.gelidEnderiumEnergyUsageBonus / 10);
			baseUsage = Math.round(baseUsage * bonus);
		}
		int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.FUEL_EFFICIENCY, stack);
		return level != 0 ? (int) Math.round(baseUsage * (5 - level) / 5.0D) : baseUsage;
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
		int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, Jetpack.values()[i].getEnergyPerTickIn()));
		if (!Jetpack.values()[i].usesEnergy) {
			energyReceived = this.getMaxEnergyStored(container);
		}
		if (!simulate) {
			energy += energyReceived;
			//energy = MathHelper.clamp(0, Integer.MAX_VALUE, energy);
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		int i = MathHelper.clamp(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyExtracted = Math.min(energy, Math.min(maxExtract, Jetpack.values()[i].getEnergyPerTickOut() == 0 ? Jetpack.values()[i].getEnergyUsage() : Jetpack.values()[i].getEnergyPerTickOut()));
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
		int i = MathHelper.clamp(container.getItemDamage(), 0, numItems - 1);
		int id = StackUtil.getEnchantmentIdByName("cofhcore:holding", container);
		if (id != -1){
			return Jetpack.values()[i].getEnergyCapacity() + Jetpack.values()[i].getEnergyCapacity() * EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(id), container) / 2;
		}
		return Jetpack.values()[i].getEnergyCapacity();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addHUDInfo(List<String> list, ItemStack stack, boolean addEnergy, boolean showState) {
		if (addEnergy && this.hasEnergyIndicator) {
			list.add(this.getHUDEnergyInfo(stack, this));
		}
		if (showState && this.hasStateIndicators) {
			list.add(this.getHUDStatesInfo(stack));
		}
	}

	@SideOnly(Side.CLIENT)
	public String getHUDEnergyInfo(ItemStack stack, ItemJetpack item) {
		int energy = item.getEnergyStored(stack);
		int maxEnergy = item.getMaxEnergyStored(stack);
		int percent = (int) Math.ceil((double) energy / (double) maxEnergy * 100D);
		return SJStringUtil.getHUDEnergyText("jetpack", percent, energy);
	}

	public boolean isHoverModeOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_HOVER, false);
	}

	public boolean isEHoverModeOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_E_HOVER, true);
	}

	public void doEHover(ItemStack armor, EntityLivingBase user) {
		NBTHelper.setBoolean(armor, TAG_ENGINE, true);
		NBTHelper.setBoolean(armor, TAG_HOVER, true);
		if (user instanceof EntityPlayer) {
			ITextComponent msg = SJStringUtil.localizeNew("chat.", ".jetpack.emergency_hover_mode.msg");
			msg.setStyle(new Style().setColor(TextFormatting.RED));
			((EntityPlayer) user).sendStatusMessage(msg, true);
		}
	}

	@SideOnly(Side.CLIENT)
	public String getHUDStatesInfo(ItemStack stack) {
		Boolean engine = this.isOn(stack);
		Boolean hover = this.isHoverModeOn(stack);
		Boolean charger = this.isChargerOn(stack);
		Boolean eHover = this.isEHoverModeOn(stack);
		if (this.canCharge(stack)) {
			return SJStringUtil.getHUDStateText(engine, hover, charger, null);
		} else {
			return SJStringUtil.getHUDStateText(engine, hover, null, null);
		}
/*		if (this.canCharge(stack)) {
			if (this.canEHover(stack)) {
				return SJStringUtil.getHUDStateText(engine, hover, charger, eHover);
			} else {
				return SJStringUtil.getHUDStateText(engine, hover, charger, null);
			}
		} else if (this.canEHover(stack)) {
			return SJStringUtil.getHUDStateText(engine, hover, null, eHover);
		} else {
			return SJStringUtil.getHUDStateText(engine, hover, null, null);
		}*/
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		int i = MathHelper.clamp(armor.getItemDamage(), 0, numItems - 1);
		if (Jetpack.values()[i].getIsArmored() && !source.isUnblockable()) {
			int energyPerDamage = this.getEnergyPerDamage(armor);
			int maxAbsorbed = energyPerDamage > 0 ? 25 * (this.getEnergyStored(armor) / energyPerDamage) : 0;
			if (this.getEnergyStored(armor) < energyPerDamage) {
				return new ArmorProperties(0, 0.65D * (Jetpack.values()[i].getArmorReduction() / 20.0D), Integer.MAX_VALUE);
			}
			if (this.isFluxBased && source.damageType.equals("flux")) {
				return new ArmorProperties(0, 0.5D, Integer.MAX_VALUE);
			}
			return new ArmorProperties(0, 0.85D * (Jetpack.values()[i].getArmorReduction() / 20.0D), maxAbsorbed);
		}
		return new ArmorProperties(0, 0, 0);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
		return 0;
	}

	@Nonnull
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EntityEquipmentSlot slot, ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(slot);
		if (!Jetpack.values()[i].getIsArmored()) {
			multimap.clear();
			return multimap;
		}
		if (slot == EntityEquipmentSlot.CHEST) {
			multimap.clear();
			multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIER, "Armor Modifier", Jetpack.values()[i].getArmorReduction(), 0));
		}
		return multimap;
	}

	@Override
	public ModelBiped getArmorModel(@Nonnull EntityLivingBase entityLiving, ItemStack itemStack, @Nonnull EntityEquipmentSlot armorSlot, @Nonnull ModelBiped _default) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		if (Config.enableArmor3DModels) {
			ModelBiped model = RenderUtils.getArmorModel(Jetpack.values()[i], entityLiving);
			if (model != null) {
				return model;
			}
		}
		return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}

	@Override
	public String getArmorTexture(ItemStack stack, @Nonnull Entity entity, @Nonnull EntityEquipmentSlot slot, @Nonnull String type) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		String flat = Config.enableArmor3DModels || Jetpack.values()[i].armorModel == PackModelType.FLAT ? "" : ".flat";
		return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + Jetpack.values()[i].getBaseName() + flat + ".png";
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
		int i = MathHelper.clamp(armor.getItemDamage(), 0, numItems - 1);
		if (Jetpack.values()[i].getIsArmored()) {
			if (Jetpack.values()[i].usesEnergy) {
				if (this.isFluxBased && source.damageType.equals("flux")) {
					this.addEnergy(armor, damage * (source.getImmediateSource() == null ? Jetpack.values()[i].getArmorEnergyPerHit() / 2 : this.getEnergyPerDamage(armor)), false);
				} else {
					this.useEnergy(armor, damage * this.getEnergyPerDamage(armor), false);
				}
			}
		}
	}

	protected int getEnergyPerDamage(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		int fuelEfficiencyLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.FUEL_EFFICIENCY, stack), 0, 4);
		return (int) Math.round(Jetpack.values()[i].getArmorEnergyPerHit() * (5 - fuelEfficiencyLevel) / 5.0D);
	}

	public void flyUser(EntityPlayer user, ItemStack stack, ItemJetpack item, boolean force) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		Item chestItem = StackUtil.getItem(stack);
		ItemJetpack jetpack = (ItemJetpack) chestItem;
		if (jetpack.isOn(stack)) {
			boolean hoverMode = jetpack.isHoverModeOn(stack);
			double hoverSpeed = Config.invertHoverSneakingBehavior == SyncHandler.isDescendKeyDown(user) ? Jetpack.values()[i].speedVerticalHoverSlow : Jetpack.values()[i].speedVerticalHover;
			boolean flyKeyDown = force || SyncHandler.isFlyKeyDown(user);
			boolean descendKeyDown = SyncHandler.isDescendKeyDown(user);
			double currentAccel = Jetpack.values()[i].accelVertical * (user.motionY < 0.3D ? 2.5D : 1.0D);
			double currentSpeedVertical = Jetpack.values()[i].speedVertical * (user.isInWater() ? 0.4D : 1.0D);
			if (flyKeyDown || hoverMode && !user.onGround) {
				if (Jetpack.values()[i].usesEnergy) {
					item.useEnergy(stack, (int) (user.isSprinting() ? Math.round(this.getEnergyUsage(stack) * Jetpack.values()[i].sprintEnergyModifier) : this.getEnergyUsage(stack)), false);
				}
				if (item.getEnergyStored(stack) > 0) {
					if (flyKeyDown) {
						if (!hoverMode) {
							user.motionY = Math.min(user.motionY + currentAccel, currentSpeedVertical);
						} else {
							if (descendKeyDown) {
								user.motionY = Math.min(user.motionY + currentAccel, -Jetpack.values()[i].speedVerticalHoverSlow);
							} else {
								user.motionY = Math.min(user.motionY + currentAccel, Jetpack.values()[i].speedVerticalHover);
							}
						}
					} else {
						user.motionY = Math.min(user.motionY + currentAccel, -hoverSpeed);
					}
					float speedSideways = (float) (user.isSneaking() ? Jetpack.values()[i].speedSideways * 0.5F : Jetpack.values()[i].speedSideways);
					float speedForward = (float) (user.isSprinting() ? speedSideways * Jetpack.values()[i].sprintSpeedModifier : speedSideways);
					if (SyncHandler.isForwardKeyDown(user)) {
						user.moveRelative(0, 0, speedForward, speedForward);
					}
					if (SyncHandler.isBackwardKeyDown(user)) {
						user.moveRelative(0, 0, -speedSideways, speedSideways * 0.8F);
					}
					if (SyncHandler.isLeftKeyDown(user)) {
						user.moveRelative(speedSideways, 0, 0, speedSideways);
					}
					if (SyncHandler.isRightKeyDown(user)) {
						user.moveRelative(-speedSideways, 0, 0, speedSideways);
					}
					if (!user.world.isRemote) {
						user.fallDistance = 0.0F;
						if (user instanceof EntityPlayerMP) {
							try {
								floatingTickCount.setInt(((EntityPlayerMP) user).connection, 0);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		if (!user.world.isRemote && Jetpack.values()[i].emergencyHoverMode && this.isEHoverModeOn(stack)) {
			if (item.getEnergyStored(stack) > 0 && (!this.isHoverModeOn(stack) || !this.isOn(stack))) {
				if (user.posY < -5) {
					this.doEHover(stack, user);
				} else {
					if (!user.capabilities.isCreativeMode && user.fallDistance - 1.2F >= user.getHealth()) {
						for (int j = 0; j <= 16; j++) {
							int x = Math.round((float) user.posX - 0.5F);
							int y = Math.round((float) user.posY) - j;
							int z = Math.round((float) user.posZ - 0.5F);
							if (!user.world.isAirBlock(new BlockPos(x, y, z))) {
								this.doEHover(stack, user);
								break;
							}
						}
					}
				}
			}
		}
	}

	protected void chargeInventory(EntityLivingBase user, ItemStack stack, ItemJetpack item) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		for (int j = 0; j <= 5; j++) {
			ItemStack currentStack = user.getItemStackFromSlot(EquipmentSlotHelper.fromSlot(j));
			if (currentStack != stack && getIEnergyStorage(currentStack) != null && (currentStack.hasCapability(CapabilityEnergy.ENERGY, null) || currentStack.getItem() instanceof IEnergyContainerItem && (!ModItems.integrateRR || !(stack.getItem() instanceof IArmorEnderium)))) {
				if (Jetpack.values()[i].usesEnergy) {
					int energyToAdd = Math.min(item.useEnergy(stack, Jetpack.values()[i].getEnergyPerTickOut(), true), getIEnergyStorage(currentStack).receiveEnergy(Jetpack.values()[i].getEnergyPerTickOut(), true));
					item.useEnergy(stack, energyToAdd, false);
					getIEnergyStorage(currentStack).receiveEnergy(energyToAdd, false);
				} else {
					getIEnergyStorage(currentStack).receiveEnergy(Jetpack.values()[i].getEnergyPerTickOut(), false);
				}
			}
		}
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
	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, NBTTagCompound nbt) {
		return new CapabilityProviderEnergy<>(new EnergyConversionStorage(this, stack), CapabilityEnergy.ENERGY, null);
	}

	public void registerItemModel() {
		for (int i = 0; i < numItems; i++) {
			SimplyJetpacks.proxy.registerItemRenderer(this, i, Jetpack.getTypeFromMeta(i).getBaseName());
		}
	}

    @Optional.Method(modid = "redstonerepository")
	public boolean isEnderiumArmor(ItemStack stack){
	    return MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1) == 19;
    }

    @Optional.Method(modid = "cofhcore")
	public boolean canEnchant(ItemStack stack, Enchantment enchantment){
		return enchantment == Enchantment.getEnchantmentByLocation("cofhcore:holding");
	}
}
