package tonius.simplyjetpacks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tonius.simplyjetpacks.crafting.PlatingReturnHandler;
import tonius.simplyjetpacks.handler.EntityInteractHandler;
import tonius.simplyjetpacks.handler.LivingTickHandler;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.ParticleType;

public class CommonProxy
{
	public void registerHandlers()
	{
		SimplyJetpacks.logger.info("Registering handlers");
		//NetworkRegistry.INSTANCE.registerGuiHandler(SimplyJetpacks.instance, new GuiHandler()); TODO: Readd GUIs
		FMLCommonHandler.instance().bus().register(new SyncHandler());
		FMLCommonHandler.instance().bus().register(new PlatingReturnHandler());
		MinecraftForge.EVENT_BUS.register(new EntityInteractHandler());
		MinecraftForge.EVENT_BUS.register(new LivingTickHandler());
	}

	public void showJetpackParticles(World world, EntityLivingBase wearer, ParticleType particle)
	{}

	public void updateCustomKeybinds(String flyKeyName, String descendKeyName)
	{}

	public String getPackGUIKey()
	{
		return null;
	}

	public void registerItemRenderer(Item item, int meta, String id)
	{}

	public EntityPlayer getPlayer(MessageContext context) {
		return context.getServerHandler().playerEntity;
	}
}
