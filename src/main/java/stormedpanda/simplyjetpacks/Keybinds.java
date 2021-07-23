package stormedpanda.simplyjetpacks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.screen.JetpackScreen;

public class Keybinds {

    public static KeyBinding JETPACK_GUI_KEY;

    public static void setup() {
        JETPACK_GUI_KEY = new KeyBinding("keybind.simplyjetpacks.jetpack_screen", GLFW.GLFW_KEY_K, "keybind.simplyjetpacks.category");
        ClientRegistry.registerKeyBinding(JETPACK_GUI_KEY);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player == null) return;
        ItemStack chestStack = player.getItemBySlot(EquipmentSlotType.CHEST);
        Item chestItem = null;
        JetpackItem jetpack;
        if (!chestStack.isEmpty()) { chestItem = chestStack.getItem(); }
        if (chestItem instanceof JetpackItem) {
            jetpack = (JetpackItem) chestItem;
            if (JETPACK_GUI_KEY.isDown()) {
                Minecraft.getInstance().setScreen(new JetpackScreen());
                //NetworkHandler.sendToServer(new PacketDisplayScreen());
            }
        }
    }
}
