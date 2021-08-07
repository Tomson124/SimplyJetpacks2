package stormedpanda.simplyjetpacks.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class JetpackModelLayers {

    public static final ModelLayerLocation JETPACK_LAYER = new ModelLayerLocation(new ResourceLocation(SimplyJetpacks.MODID, "jetpack_layer"), "main");
    public static JetpackModel<LivingEntity> JETPACK_MODEL = null;

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(JetpackModelLayers::initLayers);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(JetpackModelLayers::initModels);
    }

    public static void initLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(JETPACK_LAYER, JetpackModel::createLayer);
    }

    public static void initModels(EntityRenderersEvent.AddLayers event) {
        JETPACK_MODEL = new JetpackModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(JetpackModelLayers.JETPACK_LAYER));
    }
}
