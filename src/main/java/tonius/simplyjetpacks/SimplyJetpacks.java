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
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.network.PacketHandler;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;

@Mod(modid = SimplyJetpacks.MODID, dependencies = SimplyJetpacks.DEPENDENCIES, guiFactory = SimplyJetpacks.GUI_FACTORY)
public class SimplyJetpacks {

    //@Instance(MODID)
    public static SimplyJetpacks INSTANCE;

    public static final String MODID = "simplyjetpacks";
    public static final String MODNAME = "Simply Jetpacks 2";
    public static final String VERSION = "${version}";
    
    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";
    public static final String DEPENDENCIES = "required-after:redstoneflux@[2.0.1,);" + "after:thermalexpansion;" + "after:redstonearsenal;" + "after:thermaldynamics;" + "after:enderio;" + "after:redstonerepository;";
    public static final String GUI_FACTORY = "tonius.simplyjetpacks.config.ConfigGuiFactory";

    @SidedProxy(clientSide = "tonius.simplyjetpacks.client.ClientProxy", serverSide = "tonius.simplyjetpacks.CommonProxy")
    public static CommonProxy proxy;
    public static Logger LOGGER = LogManager.getLogger("SimplyJetpacks");
    public static SyncHandler keyboard;

    public static final ModCreativeTab creativeTab = new ModCreativeTab();

    public SimplyJetpacks() {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public static void preInit(FMLPreInitializationEvent evt) {
        LOGGER.info("Starting Simply Jetpacks 2");
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
    public static void loadComplete(FMLLoadCompleteEvent evt) {
        Config.config.save();
        Config.configClient.save();
    }

    @EventHandler
    public static void serverStopping(FMLServerStoppingEvent evt) {
        SyncHandler.clearAll();
    }
}
