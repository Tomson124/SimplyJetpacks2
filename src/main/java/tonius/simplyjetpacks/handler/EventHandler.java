package tonius.simplyjetpacks.handler;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.util.JetpackUtil;

@Mod.EventBusSubscriber(modid = SimplyJetpacks.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(SimplyJetpacks.MODID)) {
            Config.onConfigChanged();
        }
    }

    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            ItemStack stack = JetpackUtil.getFromBothSlots((EntityPlayer) event.getEntity());
            Item item = stack.getItem();
            if (item instanceof ItemJetpack) {
                ItemJetpack jetpack = (ItemJetpack) item;
                //player.getCombatTracker().getDeathMessage();
                // TODO: Custom death messages for jetpacks. (ie. running out of energy, or not having hovermode on)
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getEntityPlayer().isSneaking() && (event.getTarget() instanceof EntityZombie || event.getTarget() instanceof EntitySkeleton)) {
            EntityLiving target = (EntityLiving) event.getTarget();
            ItemStack heldStack = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
            if (heldStack.getItem() instanceof ItemJetpack) {
                if (!target.world.isRemote) {
                    target.entityDropItem(target.getItemStackFromSlot(EntityEquipmentSlot.CHEST), 0.0F);
                }
                target.setItemStackToSlot(EntityEquipmentSlot.CHEST, heldStack.copy());
                target.setDropChance(EntityEquipmentSlot.CHEST, 2.0F);
                target.enablePersistence();
                event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).shrink(1);
            }
        }
    }
}
