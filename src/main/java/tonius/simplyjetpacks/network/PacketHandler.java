package tonius.simplyjetpacks.network;

import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.network.message.*;

public abstract class PacketHandler {

	/*public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel("SimplyJetpacks");

	public static void init() {
		SimplyJetpacks.logger.info("Registering network messages");
		instance.registerMessage(MessageJetpackSync.class, MessageJetpackSync.class, 0, Side.CLIENT);
		instance.registerMessage(MessageKeyboardSync.class, MessageKeyboardSync.class, 2, Side.SERVER);
		instance.registerMessage(MessageKeyBind.class, MessageKeyBind.class, 4, Side.SERVER);
	}

	public static EntityPlayer getPlayer(MessageContext context) {
		return SimplyJetpacks.proxy.getPlayer(context);
	}*/
}
