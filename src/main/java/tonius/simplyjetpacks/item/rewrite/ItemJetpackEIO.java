package tonius.simplyjetpacks.item.rewrite;

import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;
import tonius.simplyjetpacks.util.StackUtil;
import tonius.simplyjetpacks.util.StringHelper;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static tonius.simplyjetpacks.handler.LivingTickHandler.floatingTickCount;

public class ItemJetpackEIO extends ItemJetpack {

	public String name;
	public boolean showTier = true;
	public boolean hasFuelIndicator = true;
	public boolean hasStateIndicators = true;
	public FuelType fuelType = FuelType.ENERGY;
	public boolean isFluxBased = false;

	private final int numItems;
	private boolean isArmored = true;

	public ItemJetpackEIO(String name) {
		super(name);
		this.name = name;

		numItems = JetpackEIO.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List<ItemStack> List) {
			for (int j = 0; j < numItems; ++j) {
				ItemStack stack;
				if(JetpackEIO.values()[j].usesFuel)
				{
					List.add(new ItemStack(item, 1, j));
				}
				else {
					stack = new ItemStack(item, 1, j);
					if (item instanceof ItemJetpackEIO) {
						((ItemJetpackEIO) item).addFuel(stack, ((ItemJetpackEIO) item).getMaxEnergyStored(stack), false);
					}
					List.add(stack);
				}
			}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		flyUser(player, stack, this, false);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		if (JetpackEIO.values()[i].getRarity() != null) {
			return JetpackEIO.values()[i].getRarity();
		}
		return super.getRarity(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		if (!JetpackEIO.values()[i].usesFuel) {
			return false;
		}
		return this.hasFuelIndicator;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		return JetpackEIO.values()[i].unlocalisedName;
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void information(ItemStack stack, ItemJetpackEIO item, List list) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		if (this.showTier) {
			list.add(SJStringHelper.getTierText(JetpackEIO.values()[i].getTier()));
		}
		list.add(SJStringHelper.getFuelText(this.fuelType, item.getFuelStored(stack), JetpackEIO.values()[i].getFuelCapacity(), !JetpackEIO.values()[i].usesFuel));
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void shiftInformation(ItemStack stack, List list) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);

		list.add(SJStringHelper.getStateText(this.isOn(stack)));
		list.add(SJStringHelper.getHoverModeText(this.isHoverModeOn(stack)));
		if (JetpackEIO.values()[i].getFuelUsage() > 0) {
			list.add(SJStringHelper.getFuelUsageText(this.fuelType, JetpackEIO.values()[i].getFuelUsage()));
		}
		list.add(SJStringHelper.getParticlesText(JetpackEIO.values()[i].getParticleType(stack)));
		SJStringHelper.addDescriptionLines(list, "jetpack", StringHelper.BRIGHT_GREEN);
		String key = SimplyJetpacks.proxy.getPackGUIKey();
		if (key != null) {
			list.add(SJStringHelper.getPackGUIText(key));
		}
	}

	protected int getFuelUsage(ItemStack stack) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		//if(ModEnchantments.fuelEffeciency == null) {
		return JetpackEIO.values()[i].getFuelUsage();
		//}

		//int fuelEfficiencyLevel = tonius.simplyjetpacks.util.math.MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		//return (int) Math.round(this.fuelUsage * (20 - fuelEfficiencyLevel) / 20.0D);
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		int i = MathHelper.clamp_int(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, JetpackEIO.values()[i].getFuelPerTickIn()));
		if (!simulate) {
			energy += energyReceived;
			NBTHelper.setInt(container, ItemJetpack.TAG_ENERGY, energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		int i = MathHelper.clamp_int(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyExtracted = Math.min(energy, Math.min(maxExtract, JetpackEIO.values()[i].getFuelPerTickOut()));
		if (!simulate) {
			energy -= energyExtracted;
			NBTHelper.setInt(container, ItemJetpack.TAG_ENERGY, energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return NBTHelper.getInt(container, ItemJetpack.TAG_ENERGY);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		int i = MathHelper.clamp_int(container.getItemDamage(), 0, numItems - 1);
		return JetpackEIO.values()[i].getFuelCapacity();
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
	public String getHUDFuelInfo(ItemStack stack, ItemJetpackEIO item) {
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
		if (/*pack.isArmored && */ !source.isUnblockable()) {
			if (this.isFluxBased && source.damageType.equals("flux")) {
				return new ArmorProperties(0, 0.5D, Integer.MAX_VALUE);
			}
			int energyPerDamage = this.getFuelPerDamage(armor);
			int maxAbsorbed = energyPerDamage > 0 ? 25 * (this.getFuelStored(armor) / energyPerDamage) : 0;
			return new ArmorProperties(0, 0.85D * (JetpackEIO.values()[i].getArmorReduction() / 20.0D), maxAbsorbed);
		}
		return new ArmorProperties(0, 1, 0);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		int i = MathHelper.clamp_int(armor.getItemDamage(), 0, numItems - 1);
		if (this.isArmored) {
			if (this.getFuelStored(armor) >= JetpackEIO.values()[i].getArmorFuelPerHit()) {
				return JetpackEIO.values()[i].getArmorReduction();
			}
		}
		return 0;
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		if (Config.enableArmor3DModels) {
			ModelBiped model = RenderUtils.getArmorModel(JetpackEIO.values()[i], entityLiving);
			if (model != null) {
				return model;
			}
		}
		return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		String flat = Config.enableArmor3DModels || JetpackEIO.values()[i].armorModel == PackModelType.FLAT ? "" : ".flat";
		return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + JetpackEIO.values()[i].getBaseName() + flat + ".png";
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
		int i = MathHelper.clamp_int(armor.getItemDamage(), 0, numItems - 1);
		if (/*pack.isArmored && */ JetpackEIO.values()[i].usesFuel) {
			if (this.fuelType == FuelType.ENERGY && this.isFluxBased && source.damageType.equals("flux")) {
				this.addFuel(armor, damage * (source.getEntity() == null ? JetpackEIO.values()[i].getArmorFuelPerHit() / 2 : this.getFuelPerDamage(armor)), false);
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
			return JetpackEIO.values()[i].getArmorFuelPerHit();
		}

		int fuelEfficiencyLevel = MathHelper.clamp_int(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		return (int) Math.round(JetpackEIO.values()[i].getArmorFuelPerHit() * (5 - fuelEfficiencyLevel) / 5.0D);
	}

	public void flyUser(EntityPlayer user, ItemStack stack, ItemJetpackEIO item, boolean force) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		Item chestItem = StackUtil.getItem(stack);
		ItemJetpackEIO jetpack = (ItemJetpackEIO) chestItem;
		if (jetpack.isOn(stack)) {
			boolean hoverMode = jetpack.isHoverModeOn(stack);
			double hoverSpeed = Config.invertHoverSneakingBehavior == SyncHandler.isDescendKeyDown(user) ? JetpackEIO.values()[i].speedVerticalHoverSlow : JetpackEIO.values()[i].speedVerticalHover;
			boolean flyKeyDown = force || SyncHandler.isFlyKeyDown(user);
			boolean descendKeyDown = SyncHandler.isDescendKeyDown(user);
			double currentAccel = JetpackEIO.values()[i].accelVertical * (user.motionY < 0.3D ? 2.5D : 1.0D);
			double currentSpeedVertical = JetpackEIO.values()[i].speedVertical * (user.isInWater() ? 0.4D : 1.0D);

			if (flyKeyDown || hoverMode && !user.onGround) {
				if (JetpackEIO.values()[i].usesFuel) {
					item.useFuel(stack, (int) (user.isSprinting() ? Math.round(this.getFuelUsage(stack) * JetpackEIO.values()[i].sprintFuelModifier) : this.getFuelUsage(stack)), false);
				}

				if (item.getFuelStored(stack) > 0) {
					if (flyKeyDown) {
						if (!hoverMode) {
							user.motionY = Math.min(user.motionY + currentAccel, currentSpeedVertical);
						} else {
							if (descendKeyDown) {
								user.motionY = Math.min(user.motionY + currentAccel, -JetpackEIO.values()[i].speedVerticalHoverSlow);
							} else {
								user.motionY = Math.min(user.motionY + currentAccel, JetpackEIO.values()[i].speedVerticalHover);
							}
						}
					} else {
						user.motionY = Math.min(user.motionY + currentAccel, -hoverSpeed);
					}

					float speedSideways = (float) (user.isSneaking() ? JetpackEIO.values()[i].speedSideways * 0.5F : JetpackEIO.values()[i].speedSideways);
					float speedForward = (float) (user.isSprinting() ? speedSideways * JetpackEIO.values()[i].sprintSpeedModifier : speedSideways);
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
			SimplyJetpacks.proxy.registerItemRenderer(this, i, JetpackEIO.getTypeFromMeta(i).getBaseName());
		}
	}
}
