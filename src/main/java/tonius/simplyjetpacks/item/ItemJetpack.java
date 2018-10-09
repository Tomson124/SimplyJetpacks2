package tonius.simplyjetpacks.item;

import cofh.core.item.IEnchantableItem;
import cofh.redstoneflux.api.IEnergyContainerItem;
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
import net.minecraft.init.SoundEvents;
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
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thundr.redstonerepository.api.IArmorEnderium;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.capability.CapabilityProviderEnergy;
import tonius.simplyjetpacks.capability.EnergyConversionStorage;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.*;
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
	public static final String TAG_ON = "PackOn";
	public static final String TAG_HOVERMODE_ON = "JetpackHoverModeOn";
	public static final String TAG_EHOVER_ON = "JetpackEHoverOn";
	public static final String TAG_CHARGER_ON = "JetpackChargerOn";

	protected static final UUID ARMOR_MODIFIER = UUID.fromString("0819e549-a0f9-49d3-a199-53662799c67b");

	public String name;
	public boolean showTier = true;
	public boolean hasFuelIndicator = true;
	public boolean hasStateIndicators = true;
	public FuelType fuelType = FuelType.ENERGY;
	public boolean isFluxBased = false;

	private final int numItems;

	public ItemJetpack(String name) {
		super(EnumHelper.addArmorMaterial("JETPACK_SJ", "jetpack", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0), 2, EntityEquipmentSlot.CHEST);
		this.name = name;
		this.setUnlocalizedName(SimplyJetpacks.PREFIX + name);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(SimplyJetpacks.creativeTab);
		this.setRegistryName(name);

		numItems = Jetpack.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> List) {
		if (isInCreativeTab(creativeTabs)) {
			for (Jetpack pack : Jetpack.PACKS_SJ) {
				ItemHelper.addJetpacks(pack, List);
			}
			if (ModItems.integrateEIO) {
				for (Jetpack pack : Jetpack.PACKS_EIO) {
					ItemHelper.addJetpacks(pack, List);
				}
			}
			if (ModItems.integrateTE) {
				for (Jetpack pack : Jetpack.PACKS_TE) {
					ItemHelper.addJetpacks(pack, List);
				}
			}
			if (ModItems.integrateRR) {
				for (Jetpack pack : Jetpack.PACKS_RR) {
					ItemHelper.addJetpacks(pack, List);
				}
			}
			if (ModItems.integrateVanilla) {
				for (Jetpack pack : Jetpack.PACKS_VANILLA) {
					ItemStack stack;
					stack = new ItemStack(this, 1, pack.ordinal());
					List.add(stack);
				}
			}
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		flyUser(player, stack, this, false);
		if (this.isJetplate(stack) && this.isChargerOn(stack)) {
			chargeInventory(player, stack, this);
		}
	}

	public void toggleState(boolean on, ItemStack stack, String type, String tag, EntityPlayer player, boolean showState) {
		NBTHelper.setBoolean(stack, tag, !on);
		if (player != null && showState) {
			type = type != null && !type.equals("") ? "chat." + this.name + "." + type : "chat." + this.name + ".on";
			ITextComponent state = SJStringHelper.localizeNew(on ? "chat.disabled" : "chat.enabled");
			if (on) {
				state.setStyle(new Style().setColor(TextFormatting.RED));
			}
			else {
				state.setStyle(new Style().setColor(TextFormatting.GREEN));
			}
			ITextComponent msg = SJStringHelper.localizeNew(type, state);
			player.sendStatusMessage(msg, true);
		}
	}

	public boolean isJetplate(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		return Jetpack.values()[i].getTier() == 5;
	}

	public boolean isChargerOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_CHARGER_ON, true);
	}

	public void setParticleType(ItemStack stack, ParticleType particle) {
		NBTHelper.setInt(stack, Jetpack.TAG_PARTICLE, particle.ordinal());
	}

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
		if (!Jetpack.values()[i].usesFuel) {
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
		return Jetpack.values()[i].unlocalisedName;
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
	public void information(ItemStack stack, ItemJetpack item, List list) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (this.showTier) {
			list.add(SJStringHelper.getTierText(Jetpack.values()[i].getTier()));
		}

		list.add(SJStringHelper.getFuelText(this.fuelType, item.getFuelStored(stack), item.getMaxFuelStored(stack), !Jetpack.values()[i].usesFuel));
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void shiftInformation(ItemStack stack, List list) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);

		list.add(SJStringHelper.getStateText(this.isOn(stack)));
		list.add(SJStringHelper.getHoverModeText(this.isHoverModeOn(stack)));
		if (Jetpack.values()[i].getFuelUsage() > 0) {
			if(Jetpack.values()[i].getBaseName().contains("enderium")){
				list.add(TextFormatting.BLUE + "Supercooled! Fuel Usage Rate " + Config.gelidEnderiumFuelUsageBonus + "%");
			}
			list.add(SJStringHelper.getFuelUsageText(this.fuelType, Jetpack.values()[i].getFuelUsage()));
		}
		list.add(SJStringHelper.getParticlesText(Jetpack.values()[i].getParticleType(stack)));
		SJStringHelper.addDescriptionLines(list, "jetpack", TextFormatting.GREEN.toString());
	}

	public boolean isOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_ON, true);
	}

	// fuel
	public int getFuelStored(ItemStack stack) {
		return this.getEnergyStored(stack);
	}

	public int getMaxFuelStored(ItemStack stack) {
		return this.getMaxEnergyStored(stack);
	}

	protected int getFuelUsage(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		int usage = Jetpack.values()[i].getFuelUsage();

		//if(ModEnchantments.fuelEffeciency == null) {
		if(Jetpack.values()[i].getBaseName().contains("enderium")){
			return (int)Math.round(usage*.8);
		}
		else {
			return usage;
		}
		//}

		//int fuelEfficiencyLevel = tonius.simplyjetpacks.util.math.MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		//return (int) Math.round(this.fuelUsage * (20 - fuelEfficiencyLevel) / 20.0D);
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
		int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, Jetpack.values()[i].getFuelPerTickIn()));
		if (!Jetpack.values()[i].usesFuel) {
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
		int energyExtracted = Math.min(energy, Math.min(maxExtract, Jetpack.values()[i].getFuelPerTickOut() == 0 ? Jetpack.values()[i].getFuelUsage() : Jetpack.values()[i].getFuelPerTickOut()));
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
		if(id != -1){
			return Jetpack.values()[i].getFuelCapacity() + Jetpack.values()[i].getFuelCapacity() * EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(id), container) / 2;
		}
		return Jetpack.values()[i].getFuelCapacity();
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
	public String getHUDFuelInfo(ItemStack stack, ItemJetpack item) {
		int fuel = item.getFuelStored(stack);
		int maxFuel = item.getMaxFuelStored(stack);
		int percent = (int) Math.ceil((double) fuel / (double) maxFuel * 100D);
		return SJStringHelper.getHUDFuelText(this.name, percent, this.fuelType, fuel);
	}

	public boolean isHoverModeOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_HOVERMODE_ON, false);
	}

	public boolean isEHoverModeOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_EHOVER_ON, true);
	}

	public void doEHover(ItemStack armor, EntityLivingBase user) {
		NBTHelper.setBoolean(armor, TAG_ON, true);
		NBTHelper.setBoolean(armor, TAG_HOVERMODE_ON, true);

		if (user instanceof EntityPlayer) {
			ITextComponent msg = SJStringHelper.localizeNew("chat.itemJetpack.emergencyHoverMode.msg");
			msg.setStyle(new Style().setColor(TextFormatting.RED));
			((EntityPlayer) user).sendStatusMessage(msg, true);
		}
	}

	@SideOnly(Side.CLIENT)
	public String getHUDStatesInfo(ItemStack stack) {
		Boolean engine = this.isOn(stack);
		Boolean hover = this.isHoverModeOn(stack);
		Boolean charger = this.isChargerOn(stack);
		if (isJetplate(stack)) {
			return SJStringHelper.getHUDStateText(engine, hover, charger);
		} else {
			return SJStringHelper.getHUDStateText(engine, hover, null);
		}
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		int i = MathHelper.clamp(armor.getItemDamage(), 0, numItems - 1);
		if (Jetpack.values()[i].getIsArmored() && !source.isUnblockable()) {
			int energyPerDamage = this.getFuelPerDamage(armor);
			int maxAbsorbed = energyPerDamage > 0 ? 25 * (this.getFuelStored(armor) / energyPerDamage) : 0;
			if (this.getFuelStored(armor) < energyPerDamage) {
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

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(slot);
		if (!Jetpack.values()[i].getIsArmored()) {
			multimap.clear();
			return multimap;
		}
		if (slot == EntityEquipmentSlot.CHEST) {
			multimap.clear();
			multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIER, "Armor modifier", (double) Jetpack.values()[i].getArmorReduction(), 0));
		}
		return multimap;
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
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
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		String flat = Config.enableArmor3DModels || Jetpack.values()[i].armorModel == PackModelType.FLAT ? "" : ".flat";
		return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + Jetpack.values()[i].getBaseName() + flat + ".png";
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
		int i = MathHelper.clamp(armor.getItemDamage(), 0, numItems - 1);
		if (Jetpack.values()[i].getIsArmored()) {
			if(Jetpack.values()[i].usesFuel) {
				if (this.fuelType == FuelType.ENERGY && this.isFluxBased && source.damageType.equals("flux")) {
					this.addFuel(armor, damage * (source.getImmediateSource() == null ? Jetpack.values()[i].getArmorFuelPerHit() / 2 : this.getFuelPerDamage(armor)), false);
				} else {
					this.useFuel(armor, damage * this.getFuelPerDamage(armor), false);
				}
			}
		}
	}

	// armor
	protected int getFuelPerDamage(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, numItems - 1);
		if (ModEnchantments.fuelEffeciency == null) {
			return Jetpack.values()[i].getArmorFuelPerHit();
		}

		int fuelEfficiencyLevel = MathHelper.clamp(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		return (int) Math.round(Jetpack.values()[i].getArmorFuelPerHit() * (5 - fuelEfficiencyLevel) / 5.0D);
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
				if (Jetpack.values()[i].usesFuel) {
					item.useFuel(stack, (int) (user.isSprinting() ? Math.round(this.getFuelUsage(stack) * Jetpack.values()[i].sprintFuelModifier) : this.getFuelUsage(stack)), false);
				}

				if (item.getFuelStored(stack) > 0) {
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

                        /*
						TODO: Reimplement explosions
                        if (Config.flammableFluidsExplode) {
                            if (!(user instanceof EntityPlayer) || !((EntityPlayer) user).capabilities.isCreativeMode) {
                                int x = Math.round((float) user.posX - 0.5F);
                                int y = Math.round((float) user.posY);
                                int z = Math.round((float) user.posZ - 0.5F);
                                Block fluidBlock = user.worldObj.getBlock(x, y, z);
                                if (fluidBlock instanceof IFluidBlock && fluidBlock.isFlammable(user.worldObj, x, y, z, ForgeDirection.UNKNOWN)) {
                                    user.worldObj.playSoundAtEntity(user, "mob.ghast.fireball", 2.0F, 1.0F);
                                    user.worldObj.createExplosion(user, user.posX, user.posY, user.posZ, 3.5F, false);
                                    user.attackEntityFrom(new EntityDamageSource("jetpackexplode", user), 100.0F);
                                }
                            }
                        }*/
					}
				}
			}
		}

		//Emergency Hover
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

		if (this.fuelType == FuelType.ENERGY) {
			for (int j = 0; j <= 5; j++) {
				ItemStack currentStack = user.getItemStackFromSlot(EquipmentSlotHelper.fromSlot(j));
				if (currentStack.isEmpty() && currentStack != stack && getIEnergyStorage(currentStack) != null && !(stack.getItem() instanceof IArmorEnderium)) {
					if (Jetpack.values()[i].usesFuel) {
						int energyToAdd = Math.min(item.useFuel(stack, Jetpack.values()[i].getFuelPerTickOut(), true), getIEnergyStorage(currentStack).receiveEnergy(Jetpack.values()[i].getFuelPerTickOut(), true));
						item.useFuel(stack, energyToAdd, false);
						getIEnergyStorage(currentStack).receiveEnergy(energyToAdd, false);
					} else {
						getIEnergyStorage(currentStack).receiveEnergy(Jetpack.values()[i].getFuelPerTickOut(), false);
					}
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
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
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
