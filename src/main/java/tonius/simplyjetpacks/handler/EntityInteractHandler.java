package tonius.simplyjetpacks.handler;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tonius.simplyjetpacks.item.rewrite.ItemJetpack;

public class EntityInteractHandler
{
	@SubscribeEvent
	public void onEntityInteract(EntityInteract evt)
	{
		if(evt.getEntityPlayer().isSneaking() && (evt.getTarget() instanceof EntityZombie || evt.getTarget() instanceof EntitySkeleton))
		{
			EntityLiving target = (EntityLiving) evt.getTarget();
			ItemStack heldStack = evt.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
			if(heldStack != null && heldStack.getItem() instanceof ItemJetpack)
			{
				if(!target.world.isRemote)
				{
					target.entityDropItem(target.getItemStackFromSlot(EntityEquipmentSlot.CHEST), 0.0F);
				}
				target.setItemStackToSlot(EntityEquipmentSlot.CHEST, heldStack.copy());
				target.setDropChance(EntityEquipmentSlot.CHEST, 2.0F);
				target.enablePersistence();
				evt.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).shrink(1);
			}
		}
	}
}