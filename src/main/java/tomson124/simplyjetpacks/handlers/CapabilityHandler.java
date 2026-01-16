package tomson124.simplyjetpacks.handlers;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.energy.ComponentEnergyStorage;
import tomson124.simplyjetpacks.compat.CuriosCompat;
import tomson124.simplyjetpacks.config.SimplyJetpacksConfig;
import tomson124.simplyjetpacks.init.SJDataComponentTypes;
import tomson124.simplyjetpacks.util.JetpackUtil;

public final class CapabilityHandler {
    @SubscribeEvent
    public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(Capabilities.EnergyStorage.ITEM, (stack, context) -> {
            var jetpack = JetpackUtil.getJetpack(stack);
            return new ComponentEnergyStorage(stack, SJDataComponentTypes.JETPACK_ENERGY.get(), jetpack.capacity);
        }, RegistryHandler.JETPACK.get());

        if (SimplyJetpacksConfig.isCuriosEnabled()) {
            CuriosCompat.registerCapabilities(event);
        }
    }
}
