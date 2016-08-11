package tonius.simplyjetpacks;

import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

import org.apache.logging.log4j.Logger;

import tonius.simplyjetpacks.client.audio.SJSoundEvent;
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

@Mod(modid = SimplyJetpacks.MODID, version = SimplyJetpacks.VERSION, dependencies = SimplyJetpacks.DEPENDENCIES, guiFactory = SimplyJetpacks.GUI_FACTORY)
public class SimplyJetpacks {
    
    public static final String MODID = "simplyjetpacks";
    public static final String VERSION = "@VERSION@";
    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";
    public static final String DEPENDENCIES = "required-after:Forge@[12.17.0.1976,)"
            //";after:ThermalExpansion;after:RedstoneArsenal;after:RArm;after:EnderIO@[1.7.10-2.1.3.243,);after:BuildCraft|Core@[6.4.15,7.0.0),[7.0.4,)"
            ;
    public static final String GUI_FACTORY = "tonius.simplyjetpacks.config.ConfigGuiFactory";
    
    @Instance(MODID)
    public static SimplyJetpacks instance;
    @SidedProxy(clientSide = "tonius.simplyjetpacks.client.ClientProxy", serverSide = "tonius.simplyjetpacks.CommonProxy")
    public static CommonProxy proxy;
    public static Logger logger;
    public static SyncHandler keyboard;
    
    @EventHandler
    public static void preInit(FMLPreInitializationEvent evt) {
        logger = evt.getModLog();
        logger.info("Starting Simply Jetpacks");
        
        //checkCoFHLib(); TODO: Clean up! See bottom
        
        Packs.preInit();
        Config.preInit(evt);
        ModItems.preInit();
        SJSoundEvent.registerSounds();
    }
    
    @EventHandler
    public static void init(FMLInitializationEvent evt) {
        RecipeSorter.register(SimplyJetpacks.MODID + ":upgrading", UpgradingRecipe.class, Category.SHAPED, "after:minecraft:shaped");
        proxy.registerHandlers();
        PacketHandler.init();
        ModItems.init();
        ModEnchantments.init();
    }
    
    @EventHandler
    public static void serverStopping(FMLServerStoppingEvent evt) {
        SyncHandler.clearAll();
    }
    
    /*private static void checkCoFHLib() {
        try {
            Class.forName("cofh.lib.util.helpers.FireworksHelper$Explosion");
            logger.info("Successfully found CoFHLib");
            return;
            
        } catch (ClassNotFoundException e) {
            logger.error("Could not find CoFHLib!");
            proxy.throwCoFHLibException();
        }
    }*/
    
}
