package tomson124.simplyjetpacks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tomson124.simplyjetpacks.config.SimplyJetpacksConfig;
import tomson124.simplyjetpacks.crafting.JetpackCraftingEvents;
import tomson124.simplyjetpacks.handlers.CommonJetpackHandler;
import tomson124.simplyjetpacks.handlers.RegistryHandler;
import tomson124.simplyjetpacks.init.SJDataComponentTypes;
import tomson124.simplyjetpacks.item.SJItemGroup;
import tomson124.simplyjetpacks.network.NetworkHandler;

@Mod(SimplyJetpacks.MODID)
public class SimplyJetpacks {

    public static final String MODID = "simplyjetpacks";
    public static final String MODNAME = "Simply Jetpacks 2";
    public static final String VERSION = "${version}";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final Logger LOGGER = LogManager.getLogger();

    public SimplyJetpacks(IEventBus eventBus, ModContainer container) {
        eventBus.register(this);
        CREATIVE_TAB.register(SimplyJetpacks.MODID + ".main", SJItemGroup::new);

        /*var bus = FMLJavaModLoadingContext.get().getModEventBus();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            bus.register(new HUDHandler());
        });*/

        /*// TODO: fix this.
        if (ModList.get().isLoaded("curios")) {
            NeoForge.EVENT_BUS.addGenericListener(ItemStack.class, this::attachCapabilities);
        }*/

        SJDataComponentTypes.REGISTRY.register(eventBus);

        eventBus.register(new JetpackCraftingEvents());
        eventBus.register(new CommonJetpackHandler());
//        NeoForge.EVENT_BUS.register(new SJSounds());

        SimplyJetpacksConfig.register(container);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);
        RegistryHandler.init(eventBus);
        eventBus.register(new NetworkHandler());
    }

    @SubscribeEvent
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common Setup Method registered.");
    }

    @SubscribeEvent
    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Client Setup Method registered.");

        //NeoForge.EVENT_BUS.register(new KeybindForgeBusHandler());
        //NeoForge.EVENT_BUS.register(new ClientJetpackHandler());
        //NeoForge.EVENT_BUS.register(new HUDHandler());

        /*if (ModList.get().isLoaded("curios")) {
            CuriosIntegration.initRenderers();
        }*/
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server starting...");
    }

    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        LOGGER.info("Server stopping...");
        CommonJetpackHandler.clear();
    }

    @SubscribeEvent
    public void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent loggedInEvent) {
        SimplyJetpacks.LOGGER.info("{} logging in. Syncing server jetpack configs with client.", loggedInEvent.getEntity().getName().getString());
        //SimplyJetpacksConfig.sendServerConfigFiles(loggedInEvent.getEntity());
        SimplyJetpacks.LOGGER.info("Finished syncing server jetpack configs.");
    }

    /*private void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (!ModList.get().isLoaded("curios")) {
            return;
        }
        ItemStack stack = event.getObject();
        if (stack.getItem() instanceof JetpackItem) {
             event.addCapability(CuriosCapability.ID_ITEM, CuriosIntegration.initJetpackCapabilities(stack));
        }
        if (stack.getItem() instanceof PilotGogglesItem) {
             event.addCapability(CuriosCapability.ID_ITEM, CuriosIntegration.initGogglesCapabilities(stack));
        }
    }*/
}
