package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.rewrite.Fluxpack;
import tonius.simplyjetpacks.item.rewrite.Jetpack;

public class MessageConfigSync implements IMessage, IMessageHandler<MessageConfigSync, IMessage> {
	public NBTTagCompound recv;

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound toSend = new NBTTagCompound();
		//PackBase.writeAllConfigsToNBT(toSend);
		Jetpack.writeAllConfigsToNBT(toSend);
		Fluxpack.writeAllConfigsToNBT(toSend);
		ByteBufUtils.writeTag(buf, toSend);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.recv = ByteBufUtils.readTag(buf);
	}

	@Override
	public IMessage onMessage(MessageConfigSync msg, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(new Runnable() {
			@Override
			public void run() {
				handleMessage(msg, ctx);
			}
		});

		return null;
	}

	public void handleMessage(MessageConfigSync msg, MessageContext ctx) {
		Jetpack.readAllConfigsFromNBT(msg.recv);
		Fluxpack.readAllConfigsFromNBT(msg.recv);
		SimplyJetpacks.logger.info("Received server configuration");
	}
}
