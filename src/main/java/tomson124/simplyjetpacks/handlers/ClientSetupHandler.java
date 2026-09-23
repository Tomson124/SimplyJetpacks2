package tomson124.simplyjetpacks.handlers;

import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.compat.CuriosCompat;
import tomson124.simplyjetpacks.config.SimplyJetpacksConfig;
import tomson124.simplyjetpacks.item.JetpackVariants;

@EventBusSubscriber(value = Dist.CLIENT, modid = SimplyJetpacks.MODID)
public final class ClientSetupHandler {

    private ClientSetupHandler() {
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Every jetpack shares one registered item, so the item model picks its texture from this
        // numeric property rather than from the item id.
        event.enqueueWork(() -> {
            ItemProperties.register(
                    RegistryHandler.JETPACK.get(),
                    JetpackVariants.PROPERTY_ID,
                    (stack, level, entity, seed) -> JetpackVariants.indexOf(stack));

            if (SimplyJetpacksConfig.isCuriosInstalled()) {
                CuriosCompat.initRenderers();
            }
        });
    }
}
