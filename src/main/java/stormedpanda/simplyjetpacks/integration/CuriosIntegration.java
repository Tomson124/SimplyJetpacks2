package stormedpanda.simplyjetpacks.integration;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CuriosIntegration {

    private static ResourceLocation getJetpackTexture(String name) {
        return new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/jetpack_" + name + ".png");
    }

    // TODO: See if this can be dynamic.
    public static void initRenderers() {
        CuriosRendererRegistry.register(RegistryHandler.PILOT_GOGGLES_IRON.get(), () -> new PilotGogglesRenderer(new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_iron.png")));
        CuriosRendererRegistry.register(RegistryHandler.PILOT_GOGGLES_GOLD.get(), () -> new PilotGogglesRenderer(new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_gold.png")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("potato")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("creative")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("creative_armored")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla1")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla1_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla2")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla2_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla3")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla3_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla4")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("vanilla4_armored")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("mek1")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("mek1_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("mek2")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("mek2_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("mek3")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("mek3_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("mek4")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("mek4_armored")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("te1")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("te1_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("te2")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("te2_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("te3")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("te3_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("te4")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("te4_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("te5")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("te5_enderium")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("ie1")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("ie1_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("ie2")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("ie2_armored")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("ie3")));
        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("ie3_armored")));

        CuriosRendererRegistry.register(RegistryHandler.JETPACK_CREATIVE.get(), () -> new JetpackRenderer(getJetpackTexture("")));
    }

    public static ICapabilityProvider initGogglesCapabilities(ItemStack itemStack) {
        return getProvider(new ICurio() {

            @Override
            public void playRightClickEquipSound(LivingEntity livingEntity) {
                livingEntity.level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), ((ArmorItem)itemStack.getItem()).getMaterial().getEquipSound(), SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            @Override
            public ItemStack getStack() {
                return itemStack;
            }

            @Override
            public boolean canRightClickEquip() {
                return true;
            }
        });
    }

    public static ICapabilityProvider initJetpackCapabilities(ItemStack itemStack) {
        return getProvider(new ICurio() {

            @Override
            public void playRightClickEquipSound(LivingEntity livingEntity) {
                livingEntity.level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), ((ArmorItem)itemStack.getItem()).getMaterial().getEquipSound(), SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            @Override
            public ItemStack getStack() {
                return itemStack;
            }

            @Override
            public boolean canRightClickEquip() {
                return true;
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof Player) {
                    itemStack.onArmorTick(livingEntity.level, (Player) livingEntity);
                }
            }

            @Override
            public boolean canSync(String identifier, int index, LivingEntity livingEntity) {
                return true;
            }
        });
    }

    private static ICapabilityProvider getProvider(ICurio curio) {
        return new ICapabilityProvider() {
            private final LazyOptional<ICurio> curioOptional = LazyOptional.of(() -> curio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                return CuriosCapability.ITEM.orEmpty(cap, curioOptional);
            }
        };
    }

}
