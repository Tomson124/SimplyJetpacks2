package tonius.simplyjetpacks.item.rewrite;

import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.item.IHUDInfoProvider;
import tonius.simplyjetpacks.setup.*;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;
import tonius.simplyjetpacks.util.StackUtil;
import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static tonius.simplyjetpacks.handler.LivingTickHandler.floatingTickCount;

public class ItemJetpack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem, IHUDInfoProvider {

	public static final String TAG_ENERGY = "Energy";
	public static final String TAG_ON = "PackOn";
	public static final String TAG_HOVERMODE_ON = "JetpackHoverModeOn";

	public String name;
	public boolean showTier = true;
	public boolean hasFuelIndicator = true;
	public boolean hasStateIndicators = true;
	public FuelType fuelType = FuelType.ENERGY;
	public boolean isFluxBased = false;

	private final int numItems;
	private boolean isArmored = true;

	public ItemJetpack(String name) {
		super(ArmorMaterial.IRON, 2, EntityEquipmentSlot.CHEST);
		this.name = name;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(ModCreativeTab.instance);
		this.setRegistryName(name);

		numItems = Jetpack.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List<ItemStack> List) {
		if (ModItems.integrateEIO) {
			for (Jetpack pack : Jetpack.ALL_PACKS) {
				ItemStack stack;
				if (pack.usesFuel) {
					List.add(new ItemStack(item, 1, pack.ordinal()));
				} else {
					stack = new ItemStack(item, 1, pack.ordinal());
					if (item instanceof ItemJetpack) {
						((ItemJetpack) item).addFuel(stack, ((ItemJetpack) item).getMaxEnergyStored(stack), false);
					}
					List.add(stack);
				}
			}
		} else {
			for (Jetpack pack : Jetpack.PACKS_SJ) {
				ItemStack stack;
				if (pack.usesFuel) {
					List.add(new ItemStack(item, 1, pack.ordinal()));
				} else {
					stack = new ItemStack(item, 1, pack.ordinal());
					if (item instanceof ItemJetpack) {
						((ItemJetpack) item).addFuel(stack, ((ItemJetpack) item).getMaxEnergyStored(stack), false);
					}
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
	}

	public void toggleState(boolean on, ItemStack stack, String type, String tag, EntityPlayer player, boolean showInChat) {
		NBTHelper.setBoolean(stack, tag, !on);

		if (player != null && showInChat) {
			String color = on ? TextFormatting.RED.toString() : TextFormatting.GREEN.toString();
			type = type != null && !type.equals("") ? "chat." + this.name + "." + type + ".on" : "chat." + this.name + ".on";
			String msg = SJStringHelper.localize(type) + " " + color + SJStringHelper.localize("chat." + (on ? "disabled" : "enabled"));
			player.addChatMessage(new TextComponentString(msg));
		}
	}

	public void setParticleType(ItemStack stack, ParticleType particle)
	{
		NBTHelper.setInt(stack, Jetpack.TAG_PARTICLE, particle.ordinal());
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		if (Jetpack.values()[i].getRarity() != null) {
			return Jetpack.values()[i].getRarity();
		}
		return super.getRarity(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
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
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		return Jetpack.values()[i].unlocalisedName;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
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
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		if (this.showTier) {
			list.add(SJStringHelper.getTierText(Jetpack.values()[i].getTier()));
		}
		list.add(SJStringHelper.getFuelText(this.fuelType, item.getFuelStored(stack), Jetpack.values()[i].getFuelCapacity(), !Jetpack.values()[i].usesFuel));
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void shiftInformation(ItemStack stack, List list) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);

		list.add(SJStringHelper.getStateText(this.isOn(stack)));
		list.add(SJStringHelper.getHoverModeText(this.isHoverModeOn(stack)));
		if (Jetpack.values()[i].getFuelUsage() > 0) {
			list.add(SJStringHelper.getFuelUsageText(this.fuelType, Jetpack.values()[i].getFuelUsage()));
		}
		list.add(SJStringHelper.getParticlesText(Jetpack.values()[i].getParticleType(stack)));
		SJStringHelper.addDescriptionLines(list, "jetpack", TextFormatting.GREEN.toString());
	}

	public boolean isOn(ItemStack stack) {
		return NBTHelper.getBooleanFallback(stack, TAG_ON, true);
	}

	// fuel
	public int getFuelStored(ItemStack stack) {
		return this.getEnergyStored(stack);
	}

	public int getMaxFuelStored(ItemStack stack) {
		return this.getMaxEnergyStored(stack);
	}

	protected int getFuelUsage(ItemStack stack) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		//if(ModEnchantments.fuelEffeciency == null) {
		return Jetpack.values()[i].getFuelUsage();
		//}

		//int fuelEfficiencyLevel = tonius.simplyjetpacks.util.math.MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		//return (int) Math.round(this.fuelUsage * (20 - fuelEfficiencyLevel) / 20.0D);
	}

	public int addFuel(ItemStack stack, int maxAdd, boolean simulate) {
		int energy = this.getEnergyStored(stack);
		int energyReceived = Math.min(this.getMaxEnergyStored(stack) - energy, maxAdd);
		if (!simulate) {
			energy += energyReceived;
			NBTHelper.setInt(stack, TAG_ENERGY, energy);
		}
		return energyReceived;
	}

	public int useFuel(ItemStack stack, int maxUse, boolean simulate) {
		int energy = this.getEnergyStored(stack);
		int energyExtracted = Math.min(energy, maxUse);
		if (!simulate) {
			energy -= energyExtracted;
			NBTHelper.setInt(stack, TAG_ENERGY, energy);
		}
		return energyExtracted;
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		int i = MathHelper.clamp_int(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, Jetpack.values()[i].getFuelPerTickIn()));
		if (!simulate) {
			energy += energyReceived;
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		int i = MathHelper.clamp_int(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyExtracted = Math.min(energy, Math.min(maxExtract, Jetpack.values()[i].getFuelPerTickOut()));
		if (!simulate) {
			energy -= energyExtracted;
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return NBTHelper.getInt(container, TAG_ENERGY);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		int i = MathHelper.clamp_int(container.getItemDamage(), 0, numItems - 1);
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
		return NBTHelper.getBoolean(stack, TAG_HOVERMODE_ON);
	}

	@SideOnly(Side.CLIENT)
	public String getHUDStatesInfo(ItemStack stack) {
		Boolean engine = this.isOn(stack);
		Boolean hover = this.isHoverModeOn(stack);
		return SJStringHelper.getHUDStateText(engine, hover, null);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		int i = MathHelper.clamp_int(armor.getItemDamage(), 0, numItems - 1);
		if (Jetpack.values()[i].getIsArmored() && !source.isUnblockable()) {
			if (this.isFluxBased && source.damageType.equals("flux")) {
				return new ArmorProperties(0, 0.5D, Integer.MAX_VALUE);
			}
			int energyPerDamage = this.getFuelPerDamage(armor);
			int maxAbsorbed = energyPerDamage > 0 ? 25 * (this.getFuelStored(armor) / energyPerDamage) : 0;
			return new ArmorProperties(0, 0.85D * (Jetpack.values()[i].getArmorReduction() / 20.0D), maxAbsorbed);
		}
		return new ArmorProperties(0, 1, 0);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		int i = MathHelper.clamp_int(armor.getItemDamage(), 0, numItems - 1);
		if (Jetpack.values()[i].getIsArmored()) {
			if (this.getFuelStored(armor) >= Jetpack.values()[i].getArmorFuelPerHit()) {
				return Jetpack.values()[i].getArmorReduction();
			}
		}
		return 0;
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
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
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		String flat = Config.enableArmor3DModels || Jetpack.values()[i].armorModel == PackModelType.FLAT ? "" : ".flat";
		return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + Jetpack.values()[i].getBaseName() + flat + ".png";
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
		int i = MathHelper.clamp_int(armor.getItemDamage(), 0, numItems - 1);
		if (Jetpack.values()[i].getIsArmored() && Jetpack.values()[i].usesFuel) {
			if (this.fuelType == FuelType.ENERGY && this.isFluxBased && source.damageType.equals("flux")) {
				this.addFuel(armor, damage * (source.getEntity() == null ? Jetpack.values()[i].getArmorFuelPerHit() / 2 : this.getFuelPerDamage(armor)), false);
			} else {
				this.useFuel(armor, damage * this.getFuelPerDamage(armor), false);
			}
		}
	}

	/*public void switchModeSecondary(ItemStack stack, EntityPlayer player, boolean showInChat) TODO: Add Jetplates
	{
		if(this.emergencyHoverMode)
		{
			this.switchEHover(stack, player, showInChat);
		}
	}*/

	// armor
	protected int getFuelPerDamage(ItemStack stack) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		if (ModEnchantments.fuelEffeciency == null) {
			return Jetpack.values()[i].getArmorFuelPerHit();
		}

		int fuelEfficiencyLevel = MathHelper.clamp_int(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		return (int) Math.round(Jetpack.values()[i].getArmorFuelPerHit() * (5 - fuelEfficiencyLevel) / 5.0D);
	}

	public void flyUser(EntityPlayer user, ItemStack stack, ItemJetpack item, boolean force) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
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
						user.moveRelative(0, speedForward, speedForward);
					}
					if (SyncHandler.isBackwardKeyDown(user)) {
						user.moveRelative(0, -speedSideways, speedSideways * 0.8F);
					}
					if (SyncHandler.isLeftKeyDown(user)) {
						user.moveRelative(speedSideways, 0, speedSideways);
					}
					if (SyncHandler.isRightKeyDown(user)) {
						user.moveRelative(-speedSideways, 0, speedSideways);
					}

					if (!user.worldObj.isRemote) {
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

		/*if(!user.worldObj.isRemote && this.emergencyHoverMode && this.isEHoverOn(stack)) TODO Add Jetplates
		{
			if(item.getEnergyStored(stack) > 0 && (!this.isHoverModeOn(stack) || !this.isOn(stack)))
			{
				if(user.posY < -5)
				{
					this.doEHover(stack, user);
				}
				else if(user instanceof EntityPlayer)
				{
					if(!((EntityPlayer) user).capabilities.isCreativeMode && user.fallDistance - 1.2F >= user.getHealth())
					{
						for(int i = 0; i <= 16; i++)
						{
							int x = Math.round((float) user.posX - 0.5F);
							int y = Math.round((float) user.posY) - i;
							int z = Math.round((float) user.posZ - 0.5F);
							if(!user.worldObj.isAirBlock(new BlockPos(x, y, z)))
							{
								this.doEHover(stack, user);
								break;
							}
						}
					}
				}
			}
		}*/
	}

	public void registerItemModel() {
		for (int i = 0; i < numItems; i++) {
			SimplyJetpacks.proxy.registerItemRenderer(this, i, Jetpack.getTypeFromMeta(i).getBaseName());
		}
	}
}
