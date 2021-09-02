package tonius.simplyjetpacks.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.network.message.MessageJetpackSync;
import tonius.simplyjetpacks.network.message.MessageKeybind;
import tonius.simplyjetpacks.network.message.MessageKeyboardSync;

public abstract class NetworkHandler {

    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(SimplyJetpacks.MODID);
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void init() {
        SimplyJetpacks.LOGGER.info("Registering Network Messages...");
        instance.registerMessage(MessageJetpackSync.class, MessageJetpackSync.class, nextID(), Side.CLIENT);
        instance.registerMessage(MessageKeyboardSync.class, MessageKeyboardSync.class, nextID(), Side.SERVER);
        instance.registerMessage(MessageKeybind.class, MessageKeybind.class, nextID(), Side.SERVER);
    }

    public static EntityPlayer getPlayer(MessageContext context) {
        return SimplyJetpacks.PROXY.getPlayer(context);
    }
}
