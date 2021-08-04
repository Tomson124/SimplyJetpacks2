package stormedpanda.simplyjetpacks;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.fmlserverevents.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stormedpanda.simplyjetpacks.client.ClientJetpackHandler;
import stormedpanda.simplyjetpacks.client.hud.HUDHandler;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.crafting.EnergyTransferHandler;
import stormedpanda.simplyjetpacks.crafting.ModIntegrationCondition;
import stormedpanda.simplyjetpacks.crafting.PlatingReturnHandler;
import stormedpanda.simplyjetpacks.handlers.KeybindHandler;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;
import stormedpanda.simplyjetpacks.handlers.SyncHandler;
import stormedpanda.simplyjetpacks.integration.IntegrationList;
import stormedpanda.simplyjetpacks.items.JetpackType;
import stormedpanda.simplyjetpacks.network.NetworkHandler;
import stormedpanda.simplyjetpacks.sound.ModSounds;

import java.util.stream.Collectors;

@Mod(SimplyJetpacks.MODID)
public class SimplyJetpacks {

    public static SimplyJetpacks INSTANCE;

    public static final String MODID = "simplyjetpacks";
    public static final String MODNAME = "Simply Jetpacks 2";
    public static final String VERSION = "${version}";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final CreativeTabSimplyJetpacks tabSimplyJetpacks = new CreativeTabSimplyJetpacks();

    public SimplyJetpacks() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::CommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new SyncHandler());
        MinecraftForge.EVENT_BUS.register(new PlatingReturnHandler());
        MinecraftForge.EVENT_BUS.register(new EnergyTransferHandler());
        MinecraftForge.EVENT_BUS.register(new ModSounds());
        MinecraftForge.EVENT_BUS.register(SimplyJetpacksConfig.class);

        // TODO: Get all configs in one folder?
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SimplyJetpacksConfig.CLIENT_SPEC, "simplyjetpacks-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SimplyJetpacksConfig.COMMON_SPEC, "simplyjetpacks-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SimplyJetpacksConfig.SERVER_SPEC, "simplyjetpacks-server.toml");

        CraftingHelper.register(ModIntegrationCondition.Serializer.INSTANCE);

        IntegrationList.init();
        JetpackType.loadAllConfigs();
        RegistryHandler.init();
    }

    private void CommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common Setup Method registered.");
        NetworkHandler.registerMessages();
    }

    private void ClientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Client Setup Method registered.");
        MinecraftForge.EVENT_BUS.register(new KeybindHandler());
        MinecraftForge.EVENT_BUS.register(new ClientJetpackHandler());
        MinecraftForge.EVENT_BUS.register(new HUDHandler());
        KeybindHandler.setup();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo(MODID, "helloworld", () -> { LOGGER.info("Hello from Simply Jetpacks 2"); return "Hello!";});
    }

    private void processIMC(final InterModProcessEvent event) {
        LOGGER.info("Got IMC {}", event.getIMCStream().map(m -> m.getMessageSupplier().get()).collect(Collectors.toList()));
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("Server starting...");
    }

    @SubscribeEvent
    public void onServerStopping(FMLServerStoppingEvent event) {
        LOGGER.info("Server stopping...");
        SyncHandler.clear();
    }

    @SubscribeEvent
    public void registerRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
        LOGGER.info("Recipe Serializers Registered.");
        CraftingHelper.register(ModIntegrationCondition.Serializer.INSTANCE);
    }
}
