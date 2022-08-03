package stormedpanda.simplyjetpacks;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.crafting.JetpackCraftingEvents;
import stormedpanda.simplyjetpacks.handlers.ClientJetpackHandler;
import stormedpanda.simplyjetpacks.handlers.CommonJetpackHandler;
import stormedpanda.simplyjetpacks.handlers.KeybindHandler;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;
import stormedpanda.simplyjetpacks.hud.HUDHandler;
import stormedpanda.simplyjetpacks.integration.CuriosIntegration;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.item.JetpackType;
import stormedpanda.simplyjetpacks.item.PilotGogglesItem;
import stormedpanda.simplyjetpacks.item.SJItemGroup;
import stormedpanda.simplyjetpacks.model.JetpackModelLayers;
import stormedpanda.simplyjetpacks.network.NetworkHandler;
import stormedpanda.simplyjetpacks.sound.SJSounds;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.stream.Collectors;

@Mod(SimplyJetpacks.MODID)
public class SimplyJetpacks {

    public static final String MODID = "simplyjetpacks";
    public static final String MODNAME = "Simply Jetpacks 2";
    public static final String VERSION = "${version}";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final SJItemGroup tabSimplyJetpacks = (SJItemGroup) new SJItemGroup().setEnchantmentCategories(RegistryHandler.JETPACK_ENCHANTMENT_TYPE);;

    public static final ResourceLocation JETPACK_SLOT = new ResourceLocation(MODID, "gui/empty_jetpack_slot");

    public SimplyJetpacks() {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onTextureStitch));

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // TODO: fix this.
        if (ModList.get().isLoaded("curios")) {
            MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class, this::attachCapabilities);
        }

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new JetpackCraftingEvents());
        MinecraftForge.EVENT_BUS.register(new CommonJetpackHandler());
        MinecraftForge.EVENT_BUS.register(new SJSounds());

        SimplyJetpacksConfig.register();
        JetpackType.loadAllConfigs();
        RegistryHandler.init();

        // TODO: see if this works.
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> JetpackModelLayers::init);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common Setup Method registered.");
        NetworkHandler.registerMessages();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Client Setup Method registered.");
        MinecraftForge.EVENT_BUS.register(new KeybindHandler());
        MinecraftForge.EVENT_BUS.register(new ClientJetpackHandler());
        MinecraftForge.EVENT_BUS.register(new HUDHandler());
        KeybindHandler.setup();

        if (ModList.get().isLoaded("curios")) {
            CuriosIntegration.initRenderers();
        }
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        if (ModList.get().isLoaded("curios")) {
            InterModComms.sendTo(MODID, CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
            InterModComms.sendTo(MODID, CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("jetpack").size(1).icon(JETPACK_SLOT).build());
        }
    }

    private void processIMC(final InterModProcessEvent event) {
        LOGGER.info("Got IMC {}", event.getIMCStream().map(m -> m.getMessageSupplier().get()).collect(Collectors.toList()));
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

    private void onTextureStitch(TextureStitchEvent.Pre event) {
        if (ModList.get().isLoaded("curios")) {
            if (event.getAtlas().location().equals(InventoryMenu.BLOCK_ATLAS)) {
                event.addSprite(JETPACK_SLOT);
            }
        }
    }

    private void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
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
    }
}
