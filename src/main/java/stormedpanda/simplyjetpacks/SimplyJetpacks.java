package stormedpanda.simplyjetpacks;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
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
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stormedpanda.simplyjetpacks.config.NewConfig;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.init.RegistryHandler;
import stormedpanda.simplyjetpacks.item.JetpackType;
import stormedpanda.simplyjetpacks.item.SimplyJetpacksItemGroup;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.stream.Collectors;

@Mod(SimplyJetpacks.MODID)
public class SimplyJetpacks {

    public static SimplyJetpacks INSTANCE;

    public static final String MODID = "simplyjetpacks";
    public static final String MODNAME = "Simply Jetpacks 2";
    public static final String VERSION = "${version}";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final SimplyJetpacksItemGroup tabSimplyJetpacks = new SimplyJetpacksItemGroup();

    public SimplyJetpacks() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::CommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(SimplyJetpacksConfig.class);

/*        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SimplyJetpacksConfig.CLIENT_SPEC, "simplyjetpacks-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SimplyJetpacksConfig.COMMON_SPEC, "simplyjetpacks-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SimplyJetpacksConfig.SERVER_SPEC, "simplyjetpacks-server.toml");*/

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, NewConfig.server_config);//, "simplyjetpacks-server-2.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, NewConfig.client_config);//, "simplyjetpacks-client-2.toml");

        NewConfig.LoadConfig(NewConfig.server_config, FMLPaths.CONFIGDIR.get().resolve("simplyjetpacks-server.toml").toString());
        NewConfig.LoadConfig(NewConfig.client_config, FMLPaths.CONFIGDIR.get().resolve("simplyjetpacks-client.toml").toString());

        JetpackType.LoadAllConfigs();
        RegistryHandler.init();
    }

    private void CommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common Setup Method registered.");
    }

    private void ClientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Client Setup Method registered.");
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo(MODID, "curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
        InterModComms.sendTo(MODID, "curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder().build());
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
    }

    @SubscribeEvent
    public void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        LOGGER.info("Recipe Serializers Registered.");
    }
}
