package stormedpanda.simplyjetpacks.handlers;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

@Mod.EventBusSubscriber(modid = SimplyJetpacks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeybindModBusHandler {

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        SimplyJetpacks.LOGGER.info("REGISTERING KEY MAP EVENT HERE");
        KeybindForgeBusHandler.JETPACK_GUI_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_gui", GLFW.GLFW_KEY_K, "keybind.simplyjetpacks.category");
        event.register(KeybindForgeBusHandler.JETPACK_GUI_KEY);
        KeybindForgeBusHandler.JETPACK_ENGINE_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_engine", GLFW.GLFW_KEY_J, "keybind.simplyjetpacks.category");
        event.register(KeybindForgeBusHandler.JETPACK_ENGINE_KEY);
        KeybindForgeBusHandler.JETPACK_HOVER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_hover", GLFW.GLFW_KEY_H, "keybind.simplyjetpacks.category");
        event.register(KeybindForgeBusHandler.JETPACK_HOVER_KEY);
        KeybindForgeBusHandler.JETPACK_EHOVER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_ehover", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
        event.register(KeybindForgeBusHandler.JETPACK_EHOVER_KEY);
        KeybindForgeBusHandler.JETPACK_CHARGER_KEY = new KeyMapping("keybind.simplyjetpacks.jetpack_charger", GLFW.GLFW_KEY_UNKNOWN, "keybind.simplyjetpacks.category");
        event.register(KeybindForgeBusHandler.JETPACK_CHARGER_KEY);
        KeybindForgeBusHandler.JETPACK_THROTTLE_INCREASE = new KeyMapping("keybind.simplyjetpacks.jetpack_throttle_increase", GLFW.GLFW_KEY_PERIOD, "keybind.simplyjetpacks.category");
        event.register(KeybindForgeBusHandler.JETPACK_THROTTLE_INCREASE);
        KeybindForgeBusHandler.JETPACK_THROTTLE_DECREASE = new KeyMapping("keybind.simplyjetpacks.jetpack_throttle_decrease", GLFW.GLFW_KEY_COMMA, "keybind.simplyjetpacks.category");
        event.register(KeybindForgeBusHandler.JETPACK_THROTTLE_DECREASE);
    }
}
