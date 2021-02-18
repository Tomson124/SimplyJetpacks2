package stormedpanda.simplyjetpacks;

import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
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
import stormedpanda.simplyjetpacks.integration.CurioJetpack;
import stormedpanda.simplyjetpacks.integration.IntegrationList;
import stormedpanda.simplyjetpacks.items.JetpackItem;
import stormedpanda.simplyjetpacks.items.JetpackType;
import stormedpanda.simplyjetpacks.network.NetworkHandler;
import stormedpanda.simplyjetpacks.sound.ModSounds;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.type.capability.ICurio;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.Direction;

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
        MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class, this::addCaps);

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
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("jetpack").size(1).build());
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
    public void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        LOGGER.info("Recipe Serializers Registered.");
        CraftingHelper.register(ModIntegrationCondition.Serializer.INSTANCE);
    }

    private void addCaps(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        if (stack.getItem() instanceof JetpackItem) {
            CurioJetpack jetpackItem = new CurioJetpack(stack);
            event.addCapability(CuriosCapability.ID_ITEM, new ICapabilityProvider() {
                final LazyOptional<ICurio> curio = LazyOptional.of(() -> jetpackItem);

                @Nonnull
                @Override
                public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                    return CuriosCapability.ITEM.orEmpty(cap, curio);
                }
            });
        }
    }

}
