package tonius.simplyjetpacks.item;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.*;
import tonius.simplyjetpacks.util.*;

import java.util.List;
import java.util.Objects;

public class ItemJetpack extends ItemPack {

	public static final String TAG_HOVERMODE_ON = "JetpackHoverModeOn";
	public static final String TAG_EHOVER_ON = "JetpackEHoverOn";
	public static final String TAG_CHARGER_ON = "JetpackChargerOn";

	public ItemJetpack(Item.Properties properties) {
		super(ArmorMaterial.LEATHER, EquipmentSlotType.CHEST, properties);
	}
/*
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
	}

	@Override
	public void onArmorTick(World world, PlayerEntity player, ItemStack stack) {
		//flyUser(player, stack, this, false);
		if (this.isJetplate(stack) && this.isChargerOn(stack)) {
			//chargeInventory(player, stack, this);
		}
	}

 */

	/*public void toggleState(boolean on, ItemStack stack, String type, String tag, PlayerEntity player, boolean showState) {
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
		return Objects.requireNonNull(Packs.getTypeFromName(name)).getTier() == 5;
	}

	public boolean isChargerOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_CHARGER_ON, true);
	}

	public void setParticleType(ItemStack stack, ParticleType particle) {
		NBTHelper.setInt(stack, Packs.TAG_PARTICLE, particle.ordinal());
	}

	@OnlyIn(Dist.CLIENT)
	@SuppressWarnings("unchecked")
	public void shiftInformation(ItemStack stack, List list) {
		list.add(SJStringHelper.getStateText(this.isOn(stack)));
		list.add(SJStringHelper.getHoverModeText(this.isHoverModeOn(stack)));
		if (Packs.getTypeFromName(name).getFuelUsage() > 0) {
			if(Packs.getTypeFromName(name).getBaseName().contains("enderium")){
				list.add(TextFormatting.BLUE + "Supercooled! Fuel Usage Rate " + Config.gelidEnderiumFuelUsageBonus + "%");
			}
			list.add(SJStringHelper.getFuelUsageText(this.fuelType, Packs.getTypeFromName(name).getFuelUsage()));
		}
		list.add(SJStringHelper.getParticlesText(Packs.getTypeFromName(name).getParticleType(stack)));
		SJStringHelper.addDescriptionLines(list, "jetpack", TextFormatting.GREEN.toString());
	}


	protected int getFuelUsage(ItemStack stack) {
		int usage = Packs.getTypeFromName(name).getFuelUsage();

		//if(ModEnchantments.fuelEffeciency == null) {
		if(Packs.getTypeFromName(name).getBaseName().contains("enderium")){
			return (int)Math.round(usage*.8);
		}
		else {
			return usage;
		}
		//}

		//int fuelEfficiencyLevel = tonius.simplyjetpacks.util.math.MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.fuelEffeciency, stack), 0, 4);
		//return (int) Math.round(this.fuelUsage * (20 - fuelEfficiencyLevel) / 20.0D);
	}

	*/
	/*
	public boolean isHoverModeOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_HOVERMODE_ON, false);
	}

	public boolean isEHoverModeOn(ItemStack stack) {
		return NBTHelper.getBoolean(stack, TAG_EHOVER_ON, true);
	}

	public void doEHover(ItemStack armor, LivingEntity user) {
		NBTHelper.setBoolean(armor, TAG_ON, true);
		NBTHelper.setBoolean(armor, TAG_HOVERMODE_ON, true);

		if (user instanceof PlayerEntity) {
			ITextComponent msg = SJStringHelper.localizeNew("chat.itemJetpack.emergencyHoverMode.msg");
			msg.setStyle(new Style().setColor(TextFormatting.RED));
			((PlayerEntity) user).sendStatusMessage(msg, true);
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
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

 */

	/*public void flyUser(EntityPlayer user, ItemStack stack, ItemJetpack item, boolean force) {

		Item chestItem = StackUtil.getItem(stack);
		ItemJetpack jetpack = (ItemJetpack) chestItem;
		if (jetpack.isOn(stack)) {
			boolean hoverMode = jetpack.isHoverModeOn(stack);
			double hoverSpeed = Config.invertHoverSneakingBehavior == SyncHandler.isDescendKeyDown(user) ? Packs.getTypeFromName(name).speedVerticalHoverSlow : Packs.getTypeFromName(name).speedVerticalHover;
			boolean flyKeyDown = force || SyncHandler.isFlyKeyDown(user);
			boolean descendKeyDown = SyncHandler.isDescendKeyDown(user);
			double currentAccel = Packs.getTypeFromName(name).accelVertical * (user.motionY < 0.3D ? 2.5D : 1.0D);
			double currentSpeedVertical = Packs.getTypeFromName(name).speedVertical * (user.isInWater() ? 0.4D : 1.0D);

			if (flyKeyDown || hoverMode && !user.onGround) {
				if (Packs.getTypeFromName(name).usesFuel) {
					item.extractEnergy(stack, (int) (user.isSprinting() ? Math.round(this.getFuelUsage(stack) * Packs.getTypeFromName(name).sprintFuelModifier) : this.getFuelUsage(stack)), false);
				}

				if (item.getEnergyStored(stack) > 0) {
					if (flyKeyDown) {
						if (!hoverMode) {
							user.motionY = Math.min(user.motionY + currentAccel, currentSpeedVertical);
						} else {
							if (descendKeyDown) {
								user.motionY = Math.min(user.motionY + currentAccel, -Packs.getTypeFromName(name).speedVerticalHoverSlow);
							} else {
								user.motionY = Math.min(user.motionY + currentAccel, Packs.getTypeFromName(name).speedVerticalHover);
							}
						}
					} else {
						user.motionY = Math.min(user.motionY + currentAccel, -hoverSpeed);
					}

					float speedSideways = (float) (user.isSneaking() ? Packs.getTypeFromName(name).speedSideways * 0.5F : Packs.getTypeFromName(name).speedSideways);
					float speedForward = (float) (user.isSprinting() ? speedSideways * Packs.getTypeFromName(name).sprintSpeedModifier : speedSideways);
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
                        }
					}
				}
			}
		}

		//Emergency Hover
		if (!user.world.isRemote && Packs.getTypeFromName(name).emergencyHoverMode && this.isEHoverModeOn(stack)) {
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
	}*/

	/*public ParticleType getDisplayParticleType(ItemStack stack, ItemPack item, EntityLivingBase user) {
		boolean flyKeyDown = SyncHandler.isFlyKeyDown(user);
		if (item.isOn(stack) && item.getEnergyStored(stack) > 0 && (flyKeyDown || this.isHoverModeOn(stack) && !user.onGround && user.motionY < 0)) {
			return Objects.requireNonNull(Packs.getTypeFromName(name)).getParticleType(stack);
		}
		return null;
	}*/
}
