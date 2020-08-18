package tonius.simplyjetpacks;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.handler.RegistryHandler;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.proxy.CommonProxy;
import tonius.simplyjetpacks.setup.CreativeTabSimplyJetpacks;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;

@Mod(modid = SimplyJetpacks.MODID, dependencies = SimplyJetpacks.DEPENDENCIES, guiFactory = "tonius.simplyjetpacks.config.ConfigGuiFactory")
public class SimplyJetpacks {

    public static final String MODID = "simplyjetpacks";
    public static final String MODNAME = "Simply Jetpacks 2";
    public static final String VERSION = "${version}";
    
    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";
    // TODO: Put dependencies back in.
    public static final String DEPENDENCIES = "";//"required-after:redstoneflux@[2.0.1,);" + "after:thermalexpansion;" + "after:redstonearsenal;" + "after:thermaldynamics;" + "after:enderio;" + "after:redstonerepository;";

    @Mod.Instance(MODID)
    public static SimplyJetpacks instance;

    @SidedProxy(clientSide = "tonius.simplyjetpacks.proxy.ClientProxy", serverSide = "tonius.simplyjetpacks.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger = LogManager.getLogger(MODID);

    public static final CreativeTabSimplyJetpacks tabSimplyJetpacks = new CreativeTabSimplyJetpacks();

    public SimplyJetpacks() {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger.info("Starting Simply Jetpacks 2...");
        MinecraftForge.EVENT_BUS.register(new RegistryHandler());
        Config.preInit(event);
        ModItems.preInit();
        ModEnchantments.init();
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.registerHandlers();
        proxy.initKeys();
        PacketHandler.init();
        ModItems.init();
        proxy.init();
    }

    @EventHandler
    public static void loadComplete(FMLLoadCompleteEvent event) {
        Config.configCommon.save();
        Config.configClient.save();
    }

    @EventHandler
    public static void serverStopping(FMLServerStoppingEvent event) {
        SyncHandler.clearAll();
    }
}
