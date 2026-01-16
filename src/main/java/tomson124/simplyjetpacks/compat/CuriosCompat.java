package tomson124.simplyjetpacks.compat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.compat.curios.JetpackCurios;
import tomson124.simplyjetpacks.handlers.RegistryHandler;
import tomson124.simplyjetpacks.integration.PilotGogglesRenderer;
import tomson124.simplyjetpacks.item.JetpackItem;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.function.Predicate;

public class CuriosCompat {

    // TODO: See if this can be dynamic.
    public static void initRenderers() {
        CuriosRendererRegistry.register(RegistryHandler.PILOT_GOGGLES_IRON.get(), () -> new PilotGogglesRenderer(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_iron.png")));
        CuriosRendererRegistry.register(RegistryHandler.PILOT_GOGGLES_GOLD.get(), () -> new PilotGogglesRenderer(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_gold.png")));

        /*CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("creative")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("creative_armored")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_VANILLA1.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla1")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_VANILLA1_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla1_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_VANILLA2.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla2")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_VANILLA2_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla2_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_VANILLA3.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla3")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_VANILLA3_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla3_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_VANILLA4.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla4")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_VANILLA4_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla4_armored")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_MEK1.get(), () -> new JetpackRenderer(getJetpackTexture("mek1")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_MEK1_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("mek1_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_MEK2.get(), () -> new JetpackRenderer(getJetpackTexture("mek2")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_MEK2_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("mek2_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_MEK3.get(), () -> new JetpackRenderer(getJetpackTexture("mek3")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_MEK3_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("mek3_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_MEK4.get(), () -> new JetpackRenderer(getJetpackTexture("mek4")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_MEK4_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("mek4_armored")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_TE1.get(), () -> new JetpackRenderer(getJetpackTexture("te1")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_TE1_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("te1_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_TE2.get(), () -> new JetpackRenderer(getJetpackTexture("te2")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_TE2_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("te2_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_TE3.get(), () -> new JetpackRenderer(getJetpackTexture("te3")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_TE3_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("te3_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_TE4.get(), () -> new JetpackRenderer(getJetpackTexture("te4")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_TE4_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("te4_armored")));
        //CuriosRendererRegistry.register(RegistryHandler.JETPACK_TE5.get(), () -> new JetpackRenderer(getJetpackTexture("te5")));
        //CuriosRendererRegistry.register(RegistryHandler.JETPACK_TE5_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("te5_enderium")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_IE1.get(), () -> new JetpackRenderer(getJetpackTexture("ie1")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_IE1_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("ie1_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_IE2.get(), () -> new JetpackRenderer(getJetpackTexture("ie2")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_IE2_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("ie2_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_IE3.get(), () -> new JetpackRenderer(getJetpackTexture("ie3")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_IE3_ARMORED.get(), () -> new JetpackRenderer(getJetpackTexture("ie3_armored")));*/
    }

    public static ItemStack findMatchingItem(Item item, LivingEntity entity) {
        return CuriosApi.getCuriosInventory(entity)
                .map(e -> e.findFirstCurio(item))
                .flatMap(e -> e.map(SlotResult::stack))
                .orElse(ItemStack.EMPTY);
    }

    public static ItemStack findMatchingItem(Predicate<ItemStack> predicate, LivingEntity entity) {
        return CuriosApi.getCuriosInventory(entity)
                .map(e -> e.findFirstCurio(predicate))
                .flatMap(e -> e.map(SlotResult::stack))
                .orElse(ItemStack.EMPTY);
    }

    public static ItemStack getJetpackCurio(LivingEntity entity) {
        return CuriosApi.getCuriosInventory(entity)
                .map(e -> e.findFirstCurio(itemstack -> itemstack.getItem() instanceof JetpackItem))
                .flatMap(e -> e.map(SlotResult::stack))
                .orElse(ItemStack.EMPTY);
    }

    private static ResourceLocation getJetpackTexture(String name) {
        return ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "textures/models/armor/jetpack_" + name + ".png");
    }

    /*public static ICapabilityProvider initGogglesCapabilities(ItemStack itemStack) {
        return getProvider(new ICurio() {

            @Override
            public void playRightClickEquipSound(LivingEntity livingEntity) {
                livingEntity.getCommandSenderWorld().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(),
                        ((ArmorItem) itemStack.getItem()).getMaterial().getEquipSound(), SoundSource.PLAYERS, 1.0F, 1.0F
                );
            }
        });
    }

    public static ICapabilityProvider initJetpackCapabilities(ItemStack itemStack) {
        return getProvider(new ICurio() {

            @Override
            public void playRightClickEquipSound(LivingEntity livingEntity) {
                livingEntity.getCommandSenderWorld().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(),
                        ((ArmorItem) itemStack.getItem()).getMaterial().getEquipSound(), SoundSource.PLAYERS, 1.0F, 1.0F
                );
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof Player) {
                    itemStack.onArmorTick(livingEntity.getCommandSenderWorld(), (Player) livingEntity);
                }
            }
        });
    }*/

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(CuriosCapability.ITEM, (stack, context) -> new JetpackCurios(stack), RegistryHandler.JETPACK.get());
    }
}
