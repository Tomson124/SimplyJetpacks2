package tonius.simplyjetpacks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import tonius.simplyjetpacks.setup.IProxy;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.sound.SJSoundRegistry;

public class ServerProxy implements IProxy {
	public static NonNullList<ItemStack> oresListParticles = null;

	public void registerHandlers() {
		SimplyJetpacks.logger.info("Registering handlers");
		//NetworkRegistry.INSTANCE.registerGuiHandler(SimplyJetpacks.instance, new GuiHandler()); TODO: Readd GUIs
		//FMLCommonHandler.instance().bus().register(new SyncHandler());
		//MinecraftForge.EVENT_BUS.register(new PlatingReturnHandler());
		//MinecraftForge.EVENT_BUS.register(new EntityInteractHandler());
		//MinecraftForge.EVENT_BUS.register(new LivingTickHandler());
	}

	//public void showJetpackParticles(World world, EntityLivingBase wearer, ParticleType particle) {
	//}

	public void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
	}

	public String getPackGUIKey() {
		return null;
	}

	public void registerItemRenderer(Item item, ResourceLocation location) {
	}

	@Override
	public World getClientWorld() {
		throw new IllegalStateException("Only run this on the client!");
	}

	@Override
	public PlayerEntity getClientPlayer() {
		throw new IllegalStateException("Only run this on the client!");
	}

	public void init() {
		SimplyJetpacks.logger.info("Registering Sounds...");
		SJSoundRegistry.init();

		//oresListParticles = OreDictionary.getOres("particleCustomizer");
	}

	/*public EntityPlayer getPlayer(MessageContext context) {
		return context.getServerHandler().player;
	}*/
}
