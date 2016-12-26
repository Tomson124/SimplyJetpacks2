package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.setup.ParticleType;

public class MessageJetpackSync implements IMessage, IMessageHandler<MessageJetpackSync, IMessage>
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

				/*Entity entity = FMLClientHandler.instance().getClient().theWorld.getEntityByID(msg.entityId);
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
				}*/
		Minecraft.getMinecraft().addScheduledTask(new Runnable() {
			@Override
			public void run() {
				handleMessage(msg, ctx);
			}
		});

		return null;
	}

	public void handleMessage(MessageJetpackSync msg, MessageContext ctx) {

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
}
