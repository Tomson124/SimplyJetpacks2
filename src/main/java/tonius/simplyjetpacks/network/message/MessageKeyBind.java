package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tonius.simplyjetpacks.item.rewrite.ItemFluxpack;
import tonius.simplyjetpacks.item.rewrite.ItemJetpack;
import tonius.simplyjetpacks.network.PacketHandler;

public class MessageKeyBind implements IMessage, IMessageHandler<MessageKeyBind, IMessage> {

	public JetpackPacket packetType;

	public MessageKeyBind() {}

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
		EntityPlayerMP entityPlayerMP = ctx.getServerHandler().playerEntity;
		WorldServer worldServer = entityPlayerMP.getServerWorld();

		worldServer.addScheduledTask(new Runnable() {
			@Override
			public void run() {
				handleMessage(msg, ctx);
			}
		});

		return null;
	}

	public void handleMessage(MessageKeyBind msg, MessageContext ctx) {
		EntityPlayer player = PacketHandler.getPlayer(ctx);
		ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

		if(msg.packetType == JetpackPacket.ENGINE) {
			if(stack != null && stack.getItem() instanceof ItemJetpack) {
				ItemJetpack jetpack = (ItemJetpack)stack.getItem();
				((ItemJetpack)stack.getItem()).toggleState(jetpack.isOn(stack), stack, null, ItemJetpack.TAG_ON, player, false);
			}
		}
		if (msg.packetType == JetpackPacket.CHARGER){
			ItemFluxpack fluxpack = (ItemFluxpack) stack.getItem();
			((ItemFluxpack)stack.getItem()).toggleState(fluxpack.isOn(stack), stack, null, ItemFluxpack.TAG_ON, player, false);
		}
		if(msg.packetType == JetpackPacket.HOVER) {
			if(stack != null && stack.getItem() instanceof ItemJetpack) {
				ItemJetpack jetpack = (ItemJetpack)stack.getItem();
				((ItemJetpack)stack.getItem()).toggleState(jetpack.isHoverModeOn(stack), stack, null, ItemJetpack.TAG_HOVERMODE_ON, player, false);
			}
		}
		if (msg.packetType == JetpackPacket.E_HOVER) {
			if(stack != null && stack.getItem() instanceof ItemJetpack) {
				ItemJetpack jetpack = (ItemJetpack)stack.getItem();
				((ItemJetpack)stack.getItem()).toggleState(jetpack.isEHoverModeOn(stack), stack, null, ItemJetpack.TAG_EHOVER_ON, player, false);
			}
		}
	}

	public static enum JetpackPacket {
		ENGINE,
		HOVER,
		CHARGER,
		E_HOVER;
	}
}
