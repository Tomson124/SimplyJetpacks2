package tonius.simplyjetpacks;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;

@Mod(modid = SimplyJetpacks.MODID, name = SimplyJetpacks.MOD_NAME, version = SimplyJetpacks.VERSION, dependencies = SimplyJetpacks.DEPENDENCIES, guiFactory = SimplyJetpacks.GUI_FACTORY, updateJSON = SimplyJetpacks.UPDATE_JSON)
public class SimplyJetpacks {
	public static final String MODID = "simplyjetpacks";
	public static final String MOD_NAME = "Simply Jetpacks 2";
	public static final String VERSION = "@VERSION@";
	public static final String PREFIX = MODID + ".";
	public static final String RESOURCE_PREFIX = MODID + ":";
	public static final String DEPENDENCIES = "required-after:thermalexpansion@[5.3.6,5.4.0);" + "after:redstonearsenal;";
	public static final String GUI_FACTORY = "tonius.simplyjetpacks.config.ConfigGuiFactory";
	public static final String UPDATE_JSON = "https://raw.githubusercontent.com/CyberdyneCC/SimplyJetpacks-2/TomsonDev/update/update.json";

	@Instance(MODID)
	public static SimplyJetpacks instance;
	@SidedProxy(clientSide = "tonius.simplyjetpacks.client.ClientProxy", serverSide = "tonius.simplyjetpacks.CommonProxy")
	public static CommonProxy proxy;
	public static Logger logger = LogManager.getLogger("SimplyJetpacks");
	public static SyncHandler keyboard;

	public static final ModCreativeTab creativeTab = new ModCreativeTab();

	public SimplyJetpacks() {
		FluidRegistry.enableUniversalBucket();
	}

	@EventHandler
	public static void preInit(FMLPreInitializationEvent evt) {
		logger.info("Starting Simply Jetpacks 2");

		MinecraftForge.EVENT_BUS.register(new RegistryHandler());

		Config.preInit(evt);
		ModItems.preInit();
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
	public static void serverStopping(FMLServerStoppingEvent evt) {
		SyncHandler.clearAll();
	}
}
