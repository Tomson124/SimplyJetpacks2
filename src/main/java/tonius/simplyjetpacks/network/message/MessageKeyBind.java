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
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.network.PacketHandler;

public class MessageKeyBind implements IMessage, IMessageHandler<MessageKeyBind, IMessage> {

	public JetpackPacket packetType;

	public MessageKeyBind() {

	}

	public MessageKeyBind(JetpackPacket type) {
		packetType = type;
	}

	@Override
	public void toBytes(ByteBuf dataStream) {
		dataStream.writeInt(packetType.ordinal());
	}

	@Override
	public void fromBytes(ByteBuf dataStream) {
		packetType = JetpackPacket.values()[dataStream.readInt()];
	}

	@Override
	public IMessage onMessage(MessageKeyBind msg, MessageContext ctx) {
		EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
		WorldServer worldServer = entityPlayerMP.getServerWorld();
		worldServer.addScheduledTask(() -> handleMessage(msg, ctx));
		return null;
	}

	public void handleMessage(MessageKeyBind msg, MessageContext ctx) {
		EntityPlayer player = PacketHandler.getPlayer(ctx);
		ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

		if (stack.getItem() instanceof ItemJetpack) {
			ItemJetpack jetpack = (ItemJetpack)stack.getItem();
			switch (msg.packetType) {
				case ENGINE:
					jetpack.toggleState(jetpack.isOn(stack), stack, null, ItemJetpack.TAG_ON, player, false);
				case CHARGER:
					jetpack.toggleState(jetpack.isChargerOn(stack), stack, null, ItemJetpack.TAG_CHARGER_ON, player, false);
				case HOVER:
					jetpack.toggleState(jetpack.isHoverModeOn(stack), stack, null, ItemJetpack.TAG_HOVERMODE_ON, player, false);
				case E_HOVER:
					jetpack.toggleState(jetpack.isEHoverModeOn(stack), stack, null, ItemJetpack.TAG_EHOVER_ON, player, false);
			}
		}
		else if (stack.getItem() instanceof ItemFluxpack) {
			ItemFluxpack fluxpack = (ItemFluxpack) stack.getItem();
			switch (msg.packetType) {
				case ENGINE:
					fluxpack.toggleState(fluxpack.isOn(stack), stack, null, ItemFluxpack.TAG_ON, player, false);
			}
		}
	}

	public enum JetpackPacket {
		ENGINE,
		HOVER,
		CHARGER,
		E_HOVER
	}
}
