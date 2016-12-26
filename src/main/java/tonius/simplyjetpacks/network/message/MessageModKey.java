package tonius.simplyjetpacks.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tonius.simplyjetpacks.item.IControllableArmor;
import tonius.simplyjetpacks.setup.ModKey;

@Deprecated
public class MessageModKey implements IMessage, IMessageHandler<MessageModKey, IMessage>
{
	public int keyId;
	public boolean showInChat;

	public MessageModKey()
	{
	}

	public MessageModKey(ModKey key, boolean showInChat)
	{
		this.keyId = key.ordinal();
		this.showInChat = showInChat;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.keyId = buf.readInt();
		this.showInChat = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.keyId);
		buf.writeBoolean(this.showInChat);
	}

	@Override
	public IMessage onMessage(MessageModKey msg, MessageContext ctx)
	{
		EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;

		if(entityPlayer != null)
		{
			ItemStack armor = entityPlayer.inventory.armorItemInSlot(2);
			if(armor != null && armor.getItem() instanceof IControllableArmor)
			{
				if(msg.keyId < 0 || msg.keyId >= ModKey.values().length)
				{
					return null;
				}
				ModKey key = ModKey.values()[msg.keyId];
				((IControllableArmor) armor.getItem()).onKeyPressed(armor, entityPlayer, key, msg.showInChat);
			}
		}
		return null;
	}
}
