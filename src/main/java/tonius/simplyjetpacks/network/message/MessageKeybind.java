package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.network.NetworkHandler;
import tonius.simplyjetpacks.util.Constants;

public class MessageKeybind implements IMessage, IMessageHandler<MessageKeybind, IMessage> {

	public JetpackPacket packetType;

	public MessageKeybind() {
	}

	public MessageKeybind(JetpackPacket type) {
		packetType = type;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(packetType.ordinal());
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		packetType = JetpackPacket.values()[buf.readInt()];
	}

	@Override
	public IMessage onMessage(MessageKeybind msg, MessageContext ctx) {
		EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
		WorldServer worldServer = entityPlayerMP.getServerWorld();
		worldServer.addScheduledTask(() -> handleMessage(msg, ctx));
		return null;
	}

	public void handleMessage(MessageKeybind msg, MessageContext ctx) {
		EntityPlayer player = NetworkHandler.getPlayer(ctx);
		ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

		if (stack.getItem() instanceof ItemJetpack) {
			ItemJetpack jetpack = (ItemJetpack) stack.getItem();
			if (msg.packetType == JetpackPacket.ENGINE) {
				jetpack.toggleState(jetpack.isOn(stack), stack, "engine_mode", Constants.TAG_ENGINE, player, Config.enableStateMessages);
			} else if (msg.packetType == JetpackPacket.CHARGER) {
				jetpack.toggleState(jetpack.isChargerOn(stack), stack, "charger_mode", Constants.TAG_CHARGER, player, Config.enableStateMessages);
			} else if (msg.packetType == JetpackPacket.HOVER) {
				jetpack.toggleState(jetpack.isHoverModeOn(stack), stack, "hover_mode", Constants.TAG_HOVER, player, Config.enableStateMessages);
			} else if (msg.packetType == JetpackPacket.E_HOVER) {
				jetpack.toggleState(jetpack.isEHoverModeOn(stack), stack, "emergency_hover_mode", Constants.TAG_E_HOVER, player, Config.enableStateMessages);
			}
		}
		else if (stack.getItem() instanceof ItemFluxpack) {
			ItemFluxpack fluxpack = (ItemFluxpack) stack.getItem();
			if (msg.packetType == JetpackPacket.ENGINE) {
				fluxpack.toggleState(fluxpack.isOn(stack), stack, "engine_mode", Constants.TAG_ENGINE_FLUX, player, Config.enableStateMessages);
			}
		}
	}

	public enum JetpackPacket {
		ENGINE,
		CHARGER,
		HOVER,
		E_HOVER
	}
}
