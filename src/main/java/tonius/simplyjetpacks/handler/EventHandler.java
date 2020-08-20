package tonius.simplyjetpacks.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.ItemJetpack;

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
            EntityPlayer player = (EntityPlayer) event.getEntity();
            ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            Item item = stack.getItem();
            if (item instanceof ItemJetpack) {
                ItemJetpack jetpack = (ItemJetpack) item;
                SimplyJetpacks.logger.info("Player Died with Jetpack on!");
                //player.getCombatTracker().getDeathMessage();
                // TODO: Custom death messages for jetpacks. (ie. running out of energy, or not having hovermode on)
            }
        }
    }
}
