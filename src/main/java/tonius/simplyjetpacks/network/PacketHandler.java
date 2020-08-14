package tonius.simplyjetpacks.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.network.message.MessageJetpackSync;
import tonius.simplyjetpacks.network.message.MessageKeyBind;
import tonius.simplyjetpacks.network.message.MessageKeyboardSync;

public abstract class PacketHandler {

	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel("SimplyJetpacks");

	public static void init() {
		SimplyJetpacks.LOGGER.info("Registering network messages");
		instance.registerMessage(MessageJetpackSync.class, MessageJetpackSync.class, 0, Side.CLIENT);
		instance.registerMessage(MessageKeyboardSync.class, MessageKeyboardSync.class, 2, Side.SERVER);
		instance.registerMessage(MessageKeyBind.class, MessageKeyBind.class, 4, Side.SERVER);
	}

	public static EntityPlayer getPlayer(MessageContext context) {
		return SimplyJetpacks.proxy.getPlayer(context);
	}
}
