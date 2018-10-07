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
import java.util.Objects;
import java.util.UUID;

import static tonius.simplyjetpacks.handler.LivingTickHandler.floatingTickCount;

@Optional.Interface(iface = "thundr.redstonerepository.api.IArmorEnderium", modid = "redstonerepository")
@Optional.Interface(iface = "cofh.core.item.IEnchantableItem", modid = "cofhcore")
public class ItemJetpack extends ItemPack {

	public static final String TAG_HOVERMODE_ON = "JetpackHoverModeOn";
	public static final String TAG_EHOVER_ON = "JetpackEHoverOn";
	public static final String TAG_CHARGER_ON = "JetpackChargerOn";

	protected static final UUID ARMOR_MODIFIER = UUID.fromString("0819e549-a0f9-49d3-a199-53662799c67b");

	public ItemJetpack(String name) {
		super(EnumHelper.addArmorMaterial("JETPACK_SJ", "jetpack", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0), 2, EntityEquipmentSlot.CHEST, name);
		//this.setHasSubtypes(true);
	}

	/*@Override
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
	}*/

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
		return Objects.requireNonNull(Jetpack.getTypeFromName(name)).getTier() == 5;
	}

	public boolean isChargerOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_CHARGER_ON, true);
	}

	public void setParticleType(ItemStack stack, ParticleType particle) {
		NBTHelper.setInt(stack, Jetpack.TAG_PARTICLE, particle.ordinal());
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		double stored = this.getMaxEnergyStored(stack) - this.getEnergyStored(stack) + 1;
		double max = this.getMaxEnergyStored(stack) + 1;
		return stored / max;
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void shiftInformation(ItemStack stack, List list) {
		list.add(SJStringHelper.getStateText(this.isOn(stack)));
		list.add(SJStringHelper.getHoverModeText(this.isHoverModeOn(stack)));
		if (Jetpack.getTypeFromName(name).getFuelUsage() > 0) {
			if(Jetpack.getTypeFromName(name).getBaseName().contains("enderium")){
				list.add(TextFormatting.BLUE + "Supercooled! Fuel Usage Rate " + Config.gelidEnderiumFuelUsageBonus + "%");
			}
			list.add(SJStringHelper.getFuelUsageText(this.fuelType, Jetpack.getTypeFromName(name).getFuelUsage()));
		}
		list.add(SJStringHelper.getParticlesText(Jetpack.getTypeFromName(name).getParticleType(stack)));
		SJStringHelper.addDescriptionLines(list, "jetpack", TextFormatting.GREEN.toString());
	}

	protected int getFuelUsage(ItemStack stack) {
		int usage = Jetpack.getTypeFromName(name).getFuelUsage();

		//if(ModEnchantments.fuelEffeciency == null) {
		if(Jetpack.getTypeFromName(name).getBaseName().contains("enderium")){
			return (int)Math.round(usage*.8);
		}
		else {
			return usage;
		}
		//}

		//int fuelEfficiencyLevel = tonius.simplyjetpacks.util.math.MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		//return (int) Math.round(this.fuelUsage * (20 - fuelEfficiencyLevel) / 20.0D);
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

	@Override
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
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(slot);
		if (!Jetpack.getTypeFromName(name).getIsArmored()) {
			multimap.clear();
			return multimap;
		}
		if (slot == EntityEquipmentSlot.CHEST) {
			multimap.clear();
			multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(ARMOR_MODIFIER, "Armor modifier", (double) Jetpack.getTypeFromName(name).getArmorReduction(), 0));
		}
		return multimap;
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if (Config.enableArmor3DModels) {
			ModelBiped model = RenderUtils.getArmorModel(Jetpack.getTypeFromName(name), entityLiving);
			if (model != null) {
				return model;
			}
		}
		return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		String flat = Config.enableArmor3DModels || Jetpack.getTypeFromName(name).armorModel == PackModelType.FLAT ? "" : ".flat";
		return SimplyJetpacks.RESOURCE_PREFIX + "textures/armor/" + Jetpack.getTypeFromName(name).getBaseName() + flat + ".png";
	}

	public void flyUser(EntityPlayer user, ItemStack stack, ItemJetpack item, boolean force) {

		Item chestItem = StackUtil.getItem(stack);
		ItemJetpack jetpack = (ItemJetpack) chestItem;
		if (jetpack.isOn(stack)) {
			boolean hoverMode = jetpack.isHoverModeOn(stack);
			double hoverSpeed = Config.invertHoverSneakingBehavior == SyncHandler.isDescendKeyDown(user) ? Jetpack.getTypeFromName(name).speedVerticalHoverSlow : Jetpack.getTypeFromName(name).speedVerticalHover;
			boolean flyKeyDown = force || SyncHandler.isFlyKeyDown(user);
			boolean descendKeyDown = SyncHandler.isDescendKeyDown(user);
			double currentAccel = Jetpack.getTypeFromName(name).accelVertical * (user.motionY < 0.3D ? 2.5D : 1.0D);
			double currentSpeedVertical = Jetpack.getTypeFromName(name).speedVertical * (user.isInWater() ? 0.4D : 1.0D);

			if (flyKeyDown || hoverMode && !user.onGround) {
				if (Jetpack.getTypeFromName(name).usesFuel) {
					item.extractEnergy(stack, (int) (user.isSprinting() ? Math.round(this.getFuelUsage(stack) * Jetpack.getTypeFromName(name).sprintFuelModifier) : this.getFuelUsage(stack)), false);
				}

				if (item.getEnergyStored(stack) > 0) {
					if (flyKeyDown) {
						if (!hoverMode) {
							user.motionY = Math.min(user.motionY + currentAccel, currentSpeedVertical);
						} else {
							if (descendKeyDown) {
								user.motionY = Math.min(user.motionY + currentAccel, -Jetpack.getTypeFromName(name).speedVerticalHoverSlow);
							} else {
								user.motionY = Math.min(user.motionY + currentAccel, Jetpack.getTypeFromName(name).speedVerticalHover);
							}
						}
					} else {
						user.motionY = Math.min(user.motionY + currentAccel, -hoverSpeed);
					}

					float speedSideways = (float) (user.isSneaking() ? Jetpack.getTypeFromName(name).speedSideways * 0.5F : Jetpack.getTypeFromName(name).speedSideways);
					float speedForward = (float) (user.isSprinting() ? speedSideways * Jetpack.getTypeFromName(name).sprintSpeedModifier : speedSideways);
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
		if (!user.world.isRemote && Jetpack.getTypeFromName(name).emergencyHoverMode && this.isEHoverModeOn(stack)) {
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

	public ParticleType getDisplayParticleType(ItemStack stack, ItemPack item, EntityLivingBase user) {
		boolean flyKeyDown = SyncHandler.isFlyKeyDown(user);
		if (item.isOn(stack) && item.getEnergyStored(stack) > 0 && (flyKeyDown || this.isHoverModeOn(stack) && !user.onGround && user.motionY < 0)) {
			return Objects.requireNonNull(Jetpack.getTypeFromName(name)).getParticleType(stack);
		}
		return null;
	}

	protected void chargeInventory(EntityLivingBase user, ItemStack stack, ItemJetpack item) {
		if (this.fuelType == FuelType.ENERGY) {
			for (int j = 0; j <= 5; j++) {
				ItemStack currentStack = user.getItemStackFromSlot(EquipmentSlotHelper.fromSlot(j));
				if (currentStack.isEmpty() && currentStack != stack && getIEnergyStorage(currentStack) != null && !(stack.getItem() instanceof IArmorEnderium)) {
					if (Jetpack.getTypeFromName(name).usesFuel) {
						int energyToAdd = Math.min(item.extractEnergy(stack, Jetpack.getTypeFromName(name).getFuelPerTickOut(), true), getIEnergyStorage(currentStack).receiveEnergy(Jetpack.getTypeFromName(name).getFuelPerTickOut(), true));
						item.extractEnergy(stack, energyToAdd, false);
						getIEnergyStorage(currentStack).receiveEnergy(energyToAdd, false);
					} else {
						getIEnergyStorage(currentStack).receiveEnergy(Jetpack.getTypeFromName(name).getFuelPerTickOut(), false);
					}
				}
			}
		}
	}
}
