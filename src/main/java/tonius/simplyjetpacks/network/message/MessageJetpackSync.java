package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.ParticleType;

public class MessageJetpackSync extends MessageAbstract<MessageJetpackSync>
{
	public int entityId;
	public int particleId;

	public MessageJetpackSync()
	{}

	public MessageJetpackSync(int entityId, int particleId)
	{
		this.entityId = entityId;
		this.particleId = particleId;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.entityId = buf.readInt();
		this.particleId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.entityId);
		buf.writeInt(this.particleId);
	}

	@Override
	public IMessage onMessage(MessageJetpackSync msg, MessageContext ctx)
	{
		Entity entity = FMLClientHandler.instance().getClient().theWorld.getEntityByID(msg.entityId);
		if(entity != null && entity instanceof EntityLivingBase && entity != FMLClientHandler.instance().getClient().thePlayer)
		{
			if(msg.particleId >= 0)
			{
				ParticleType particle = ParticleType.values()[msg.particleId];
				SyncHandler.processJetpackUpdate(msg.entityId, particle);
			}
			else
			{
				SyncHandler.processJetpackUpdate(msg.entityId, null);
			}
		}
		return null;
	}

	@Override
	public void onClientReceived(Minecraft client, MessageJetpackSync msg, EntityPlayer player, MessageContext messageContext) {

		Entity entity = FMLClientHandler.instance().getClient().theWorld.getEntityByID(msg.entityId);
		if(entity != null && entity instanceof EntityLivingBase && entity != FMLClientHandler.instance().getClient().thePlayer)
		{
			if(msg.particleId >= 0)
			{
				ParticleType particle = ParticleType.values()[msg.particleId];
				SyncHandler.processJetpackUpdate(msg.entityId, particle);
			}
			else
			{
				SyncHandler.processJetpackUpdate(msg.entityId, null);
			}
		}
	}

	@Override
	public void onServerReceived(MinecraftServer server, MessageJetpackSync message, EntityPlayer player, MessageContext messageContext) {

	}
}
