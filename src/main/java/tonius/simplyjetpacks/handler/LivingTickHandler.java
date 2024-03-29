package tonius.simplyjetpacks.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.Jetpack;
import tonius.simplyjetpacks.network.NetworkHandler;
import tonius.simplyjetpacks.network.message.MessageJetpackSync;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.JetpackUtil;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LivingTickHandler {

    private static final Map<Integer, ParticleType> lastJetpackState = new ConcurrentHashMap<>();
    public static Field floatingTickCount = null;
    private final int numItems = Jetpack.values().length;

    public LivingTickHandler() {
        try {
            floatingTickCount = ReflectionHelper.findField(NetHandlerPlayServer.class, "floatingTickCount", "field_147365_f");
        } catch (Exception e) {
            SimplyJetpacks.LOGGER.error("Unable to find field 'floatingTickCount'");
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onLivingTick(LivingUpdateEvent event) {
        if (!event.getEntityLiving().world.isRemote) {
            ParticleType jetpackState = null;
            if (event.getEntityLiving() instanceof EntityPlayer) {
                ItemStack armor = JetpackUtil.getFromBothSlots((EntityPlayer) event.getEntityLiving());
                Jetpack jetpack = null;
                if (armor.getItem() instanceof ItemJetpack) {
                    int i = MathHelper.clamp(armor.getItemDamage(), 0, numItems - 1);
                    jetpack = Jetpack.getTypeFromMeta(i);
                    jetpackState = jetpack.getDisplayParticleType(armor, (ItemJetpack) armor.getItem(), event.getEntityLiving());
                }
                if (jetpackState != lastJetpackState.get(event.getEntityLiving().getEntityId())) {
                    if (jetpackState == null) {
                        lastJetpackState.remove(event.getEntityLiving().getEntityId());
                    } else {
                        lastJetpackState.put(event.getEntityLiving().getEntityId(), jetpackState);
                    }
                    NetworkHandler.instance.sendToAllAround(new MessageJetpackSync(event.getEntityLiving().getEntityId(), jetpackState != null ? jetpackState.ordinal() : -1), new TargetPoint(event.getEntityLiving().dimension, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, 256));
                } else if (jetpack != null && event.getEntityLiving().world.getTotalWorldTime() % 160L == 0) {
                    NetworkHandler.instance.sendToAllAround(new MessageJetpackSync(event.getEntityLiving().getEntityId(), jetpackState != null ? jetpackState.ordinal() : -1), new TargetPoint(event.getEntityLiving().dimension, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, 256));
                }
                if (event.getEntityLiving().world.getTotalWorldTime() % 200L == 0) {
                    lastJetpackState.keySet().removeIf(entityId -> event.getEntityLiving().world.getEntityByID(entityId) == null);
                }
            }
        }
    }
}