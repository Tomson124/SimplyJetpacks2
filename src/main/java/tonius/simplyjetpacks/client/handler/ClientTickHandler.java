package tonius.simplyjetpacks.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.audio.SoundJetpack;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.Jetpack;
import tonius.simplyjetpacks.setup.ParticleType;

import java.lang.reflect.Field;
import java.util.Iterator;

public class ClientTickHandler {
	private static final Minecraft mc = Minecraft.getMinecraft();
	private static ParticleType lastJetpackState = null;
	private static boolean wearingJetpack = false;
	private static boolean sprintKeyCheck = false;

	private static Field sprintToggleTimer = null;

	private static final int numItems = Jetpack.values().length;

	public ClientTickHandler() {
		try {
			sprintToggleTimer = ReflectionHelper.findField(EntityPlayerSP.class, "sprintToggleTimer", "field_71156_d");
		} catch (Exception e) {
			SimplyJetpacks.logger.error("Unable to find field \"sprintToggleTimer\"");
			e.printStackTrace();
		}
	}

	private static void tickStart() {
		if (mc.player == null) {
			return;
		}

		ParticleType jetpackState = null;
		ItemStack armor = mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		if (armor != null && armor.getItem() instanceof ItemJetpack) {
			String name = ((ItemJetpack) armor.getItem()).name;
			Jetpack jetpack = Jetpack.getTypeFromName(name);
			if (jetpack != null) {
				jetpackState = ((ItemJetpack) armor.getItem()).getDisplayParticleType(armor, (ItemJetpack) armor.getItem(), mc.player);
			}
			wearingJetpack = true;
		} else {
			wearingJetpack = false;
		}

		if (jetpackState != lastJetpackState) {
			lastJetpackState = jetpackState;
			SyncHandler.processJetpackUpdate(mc.player.getEntityId(), jetpackState);
		}
	}

	private static void tickEnd() {
		if (mc.player == null || mc.world == null) {
			return;
		}

		if (!mc.isGamePaused()) {
			Iterator<Integer> itr = SyncHandler.getJetpackStates().keySet().iterator();
			int currentEntity;
			while (itr.hasNext()) {
				currentEntity = itr.next();
				Entity entity = mc.world.getEntityByID(currentEntity);
				if (entity == null || !(entity instanceof EntityLivingBase) || entity.dimension != mc.player.dimension) {
					itr.remove();
				} else {
					ParticleType particle = SyncHandler.getJetpackStates().get(currentEntity);
					if (particle != null) {
						if (entity.isInWater() && particle != ParticleType.NONE) {
							particle = ParticleType.BUBBLE;
						}
						SimplyJetpacks.proxy.showJetpackParticles(mc.world, (EntityLivingBase) entity, particle);
						if (Config.jetpackSounds && !SoundJetpack.isPlayingFor(entity.getEntityId())) {
							Minecraft.getMinecraft().getSoundHandler().playSound(new SoundJetpack((EntityLivingBase) entity));
						}
					} else {
						itr.remove();
					}
				}
			}
		}

		if (sprintKeyCheck && mc.player.movementInput.moveForward < 1.0F) {
			sprintKeyCheck = false;
		}

		if (!Config.doubleTapSprintInAir || !wearingJetpack || mc.player.onGround || mc.player.isSprinting() || mc.player.isHandActive() || mc.player.isPotionActive(MobEffects.POISON)) {
			return;
		}

		if (!sprintKeyCheck && sprintToggleTimer != null && mc.player.movementInput.moveForward >= 1.0F && !mc.player.collidedHorizontally && (mc.player.getFoodStats().getFoodLevel() > 6.0F || mc.player.capabilities.allowFlying)) {
			try {
				if (sprintToggleTimer.getInt(mc.player) <= 0 && !mc.gameSettings.keyBindSprint.isKeyDown()) {
					sprintToggleTimer.setInt(mc.player, 7);
					sprintKeyCheck = true;
				} else {
					mc.player.setSprinting(true);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}


	@SubscribeEvent
	public void onClientTick(ClientTickEvent evt) {
		if (evt.phase == Phase.START) {
			tickStart();
		} else {
			tickEnd();
		}
	}
}