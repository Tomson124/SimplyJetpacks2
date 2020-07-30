package tomson124.simplyjetpacks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.oredict.OreDictionary;
import tomson124.simplyjetpacks.crafting.PlatingReturnHandler;
import tomson124.simplyjetpacks.handler.EntityInteractHandler;
import tomson124.simplyjetpacks.handler.LivingTickHandler;
import tomson124.simplyjetpacks.handler.SyncHandler;
import tomson124.simplyjetpacks.setup.ParticleType;
import tomson124.simplyjetpacks.sound.SJSoundRegistry;

public class CommonProxy
{
	public static NonNullList<ItemStack> oresListParticles = null;

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

	public void init() {
		SimplyJetpacks.logger.info("Registering Sounds...");
		SJSoundRegistry.init();

		oresListParticles = OreDictionary.getOres("particleCustomizer");
	}

	public EntityPlayer getPlayer(MessageContext context) {
		return context.getServerHandler().player;
	}

	public void initKeys() {
	}
}
