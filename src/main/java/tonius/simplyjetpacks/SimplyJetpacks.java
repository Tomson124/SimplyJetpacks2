package tonius.simplyjetpacks;

import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.crafting.UpgradingRecipe;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.Packs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = SimplyJetpacks.MODID, name = SimplyJetpacks.MOD_NAME, version = SimplyJetpacks.VERSION, dependencies = SimplyJetpacks.DEPENDENCIES, guiFactory = SimplyJetpacks.GUI_FACTORY)
public class SimplyJetpacks {
	public static final String MODID = "simplyjetpacks";
	public static final String MOD_NAME = "Simply Jetpacks 2";
	public static final String VERSION = "@VERSION@";
	public static final String PREFIX = MODID + ".";
	public static final String RESOURCE_PREFIX = MODID + ":";
	public static final String DEPENDENCIES = "required-after:Forge@[12.18.2.2099,);" /*after:EnderIO@[1.7.10-2.1.3.243,) */;
	public static final String GUI_FACTORY = "tonius.simplyjetpacks.config.ConfigGuiFactory";

	@Instance(MODID)
	public static SimplyJetpacks instance;
	@SidedProxy(clientSide = "tonius.simplyjetpacks.client.ClientProxy", serverSide = "tonius.simplyjetpacks.CommonProxy")
	public static CommonProxy proxy;
	public static Logger logger = LogManager.getLogger("SimplyJetpacks");
	public static SyncHandler keyboard;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent evt) {
		logger.info("Starting Simply Jetpacks 2");

		//Packs.preInit();
		Config.preInit(evt);
		ModItems.preInit();
	}

	@EventHandler
	public static void init(FMLInitializationEvent evt) {
		RecipeSorter.register(SimplyJetpacks.MODID + ":upgrading", UpgradingRecipe.class, Category.SHAPED, "after:minecraft:shaped");
		proxy.registerHandlers();
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
