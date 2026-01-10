package tomson124.simplyjetpacks.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import tomson124.simplyjetpacks.SimplyJetpacks;

@EventBusSubscriber(value = Dist.CLIENT, modid = SimplyJetpacks.MODID)
public class JetpackModelLayers {

    public static final ModelLayerLocation JETPACK_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "jetpack_layer"), "main");
    public static JetpackModel<LivingEntity> JETPACK_MODEL = null;

    @SubscribeEvent
    public static void initLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(JETPACK_LAYER, JetpackModel::createLayer);
    }

    @SubscribeEvent
    public static void initModels(EntityRenderersEvent.AddLayers event) {
        JETPACK_MODEL = new JetpackModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(JetpackModelLayers.JETPACK_LAYER));
    }
}