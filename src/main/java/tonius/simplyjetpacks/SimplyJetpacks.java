package tonius.simplyjetpacks;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tonius.simplyjetpacks.client.ClientProxy;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.setup.IProxy;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.reference.Reference;

@Mod(Reference.MODID)
public class SimplyJetpacks {

	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
	public static Logger logger = LogManager.getLogger("SimplyJetpacks");

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {

		@SubscribeEvent
		public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
			ModItems.registerItems(event);
		}
	}

	public SimplyJetpacks() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("mytutorial-client.toml"));
		Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("mytutorial-common.toml"));
	}

	private void setup(final FMLCommonSetupEvent event) {
		ModItems.init();
	}

	/*@EventHandler
	public static void preInit(FMLPreInitializationEvent evt) {
		logger.info("Starting Simply Jetpacks 2");

		//MinecraftForge.EVENT_BUS.register(new RegistryHandler());

		Config.preInit(evt);
	}

	@EventHandler
	public static void init(FMLInitializationEvent evt) {
		proxy.registerHandlers();
		proxy.initKeys();
		PacketHandler.init();
		ModItems.init();
		ModEnchantments.init();

		proxy.init();
	}

	@EventHandler
	public static void loadComplete(FMLLoadCompleteEvent evt){
		Config.config.save();
		Config.configClient.save();
	}

	@EventHandler
	public static void serverStopping(FMLServerStoppingEvent evt) {
		//SyncHandler.clearAll();
	}*/
}
