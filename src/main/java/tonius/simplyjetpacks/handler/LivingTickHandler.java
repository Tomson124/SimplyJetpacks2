package tonius.simplyjetpacks.handler;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import tonius.simplyjetpacks.item.ItemPack.ItemJetpack;
import tonius.simplyjetpacks.item.meta.Jetpack;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageJetpackSync;
import tonius.simplyjetpacks.setup.ParticleType;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LivingTickHandler
{
	private static final Map<Integer, ParticleType> lastJetpackState = new ConcurrentHashMap<Integer, ParticleType>();

	@SubscribeEvent
	public void onLivingTick(LivingUpdateEvent evt)
	{
		if(!evt.getEntityLiving().worldObj.isRemote)
		{
			ParticleType jetpackState = null;
			ItemStack armor = evt.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			Jetpack jetpack = null;
			if(armor != null && armor.getItem() instanceof ItemJetpack)
			{
				jetpack = ((ItemJetpack) armor.getItem()).getPack(armor);
				if(jetpack != null)
				{
					jetpackState = jetpack.getDisplayParticleType(armor, (ItemJetpack) armor.getItem(), evt.getEntityLiving());
				}
			}

			if(jetpackState != lastJetpackState.get(evt.getEntityLiving().getEntityId()))
			{
				if(jetpackState == null)
				{
					lastJetpackState.remove(evt.getEntityLiving().getEntityId());
				}
				else
				{
					lastJetpackState.put(evt.getEntityLiving().getEntityId(), jetpackState);
				}
				PacketHandler.instance.sendToAllAround(new MessageJetpackSync(evt.getEntityLiving().getEntityId(), jetpackState != null ? jetpackState.ordinal() : -1), new TargetPoint(evt.getEntityLiving().dimension, evt.getEntityLiving().posX, evt.getEntityLiving().posY, evt.getEntityLiving().posZ, 256));
			}
			else if(jetpack != null && evt.getEntityLiving().worldObj.getTotalWorldTime() % 160L == 0)
			{
				PacketHandler.instance.sendToAllAround(new MessageJetpackSync(evt.getEntityLiving().getEntityId(), jetpackState != null ? jetpackState.ordinal() : -1), new TargetPoint(evt.getEntityLiving().dimension, evt.getEntityLiving().posX, evt.getEntityLiving().posY, evt.getEntityLiving().posZ, 256));
			}

			if(evt.getEntityLiving().worldObj.getTotalWorldTime() % 200L == 0)
			{
				Iterator<Integer> itr = lastJetpackState.keySet().iterator();
				while(itr.hasNext())
				{
					int entityId = itr.next();
					if(evt.getEntityLiving().worldObj.getEntityByID(entityId) == null)
					{
						itr.remove();
					}
				}
			}
		}
	}

    /*@SubscribeEvent
	public void mobUseJetpack(LivingUpdateEvent evt) {
        if (!evt.getEntityLiving().worldObj.isRemote && evt.getEntityLiving() instanceof EntityMob) {
            ItemStack armor = evt.getEntityLiving().getEquipmentInSlot(3);
            if (armor != null && armor.getItem() instanceof ItemJetpack) {
                ItemJetpack jetpackItem = (ItemJetpack) armor.getItem();
                Jetpack jetpack = jetpackItem.getPack(armor);
                if (jetpack != null) {
                    if (jetpack instanceof JetpackPotato || MathHelper.RANDOM.nextInt(3) == 0) {
                        jetpack.setMobMode(armor);
                        jetpack.flyUser(evt.getEntityLiving(), armor, jetpackItem, false);
                    }
                }
                if (evt.getEntityLiving().posY > evt.getEntityLiving().worldObj.getActualHeight() + 10) {
                    evt.getEntityLiving().attackEntityFrom(DamageSource.generic, 80);
                }
            }
        }
    } TODO: Investigate and update*/
}