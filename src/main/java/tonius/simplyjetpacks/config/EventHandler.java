package tonius.simplyjetpacks.config;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tonius.simplyjetpacks.SimplyJetpacks;

@Mod.EventBusSubscriber(modid = SimplyJetpacks.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(SimplyJetpacks.MODID)) {
            Config.onConfigChanged();
        }
    }
}
