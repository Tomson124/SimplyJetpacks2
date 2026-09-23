package tomson124.simplyjetpacks.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.network.payloads.*;

@EventBusSubscriber(modid = SimplyJetpacks.MODID)
public class NetworkHandler {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(PayloadToggleCharger.TYPE, PayloadToggleCharger.STREAM_CODEC, PayloadToggleCharger::handleServer);
        registrar.playToServer(PayloadToggleEHover.TYPE, PayloadToggleEHover.STREAM_CODEC, PayloadToggleEHover::handleServer);
        registrar.playToServer(PayloadToggleEngine.TYPE, PayloadToggleEngine.STREAM_CODEC, PayloadToggleEngine::handleServer);
        registrar.playToServer(PayloadToggleHover.TYPE, PayloadToggleHover.STREAM_CODEC, PayloadToggleHover::handleServer);
        registrar.playToServer(PayloadUpdateInput.TYPE, PayloadUpdateInput.STREAM_CODEC, PayloadUpdateInput::handleServer);
        registrar.playToServer(PayloadUpdateThrottle.TYPE, PayloadUpdateThrottle.STREAM_CODEC, PayloadUpdateThrottle::handleServer);

        // Server -> client: the authoritative jetpack stat table.
        registrar.playToClient(PayloadJetpackConfigSync.TYPE, PayloadJetpackConfigSync.STREAM_CODEC, PayloadJetpackConfigSync::handleClient);
    }
}
