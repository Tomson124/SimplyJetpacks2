package tonius.simplyjetpacks.client.handler.deprecated;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.item.IControllableArmor;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.network.message.MessageModKey;
import tonius.simplyjetpacks.setup.ModKey;

@Deprecated
public class SJKeyBinding extends KeyBinding
{
	private ModKey keyType;

	public SJKeyBinding(String name, int keyId, ModKey keyType)
	{
		super(SimplyJetpacks.PREFIX + "keybind." + name, keyId, "Simply Jetpacks");
		this.keyType = keyType;
		ClientRegistry.registerKeyBinding(this);
		KeyHandler.keyBindings.add(this);
	}

	public void handleKeyPress()
	{
		ItemStack itemStack = KeyHandler.mc.thePlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		if(itemStack != null && itemStack.getItem() instanceof IControllableArmor)
		{
			PacketHandler.instance.sendToServer(new MessageModKey(this.keyType, this.keyType.alwaysShowInChat || Config.enableStateChatMessages));
		}
	}
}