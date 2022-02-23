package stormedpanda.simplyjetpacks.integration;

import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.item.PilotGogglesItem;
import stormedpanda.simplyjetpacks.model.JetpackModel;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CuriosIntegration {

    public static ICapabilityProvider initGogglesCapabilities(ItemStack itemStack) {
        return getProvider(new ICurio() {

            @Override
            public void playRightClickEquipSound(LivingEntity livingEntity) {
                livingEntity.level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), ((ArmorItem)itemStack.getItem()).getMaterial().getEquipSound(), SoundCategory.PLAYERS, 1.0F, 1.0F);
            }

            @Override
            public boolean canRightClickEquip() {
                return true;
            }

            @Override
            public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
                return true;
            }

            @Override
            public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                BipedModel<LivingEntity> gogglesModel = new BipedModel<>(1.0F);
                ICurio.RenderHelper.followHeadRotations(livingEntity, gogglesModel.head);
                //IVertexBuilder vertexBuilder = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, gogglesModel.renderType(new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_" + ((PilotGogglesItem) itemStack.getItem()).getType() + "_layer_1.png")), false, itemStack.getItem().isFoil(itemStack));
                IVertexBuilder vertexBuilder = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, gogglesModel.renderType(new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_" + ((PilotGogglesItem) itemStack.getItem()).getType() + ".png")), false, itemStack.getItem().isFoil(itemStack));
                gogglesModel.head.render(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        });
    }

    public static ICapabilityProvider initJetpackCapabilities(ItemStack itemStack) {
        return getProvider(new ICurio() {

            @Override
            public void playRightClickEquipSound(LivingEntity livingEntity) {
                livingEntity.level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), ((ArmorItem)itemStack.getItem()).getMaterial().getEquipSound(), SoundCategory.PLAYERS, 1.0F, 1.0F);
            }

            @Override
            public boolean canRightClickEquip() {
                return true;
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if (livingEntity instanceof PlayerEntity) {
                    itemStack.onArmorTick(livingEntity.level, (Player) livingEntity);
                }
            }

            @Override
            public boolean canSync(String identifier, int index, LivingEntity livingEntity) {
                return true;
            }

            @Override
            public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
                return true;
            }

            @Override
            public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                JetpackModel jetpackModel = new JetpackModel();
                ICurio.RenderHelper.followBodyRotations(livingEntity, jetpackModel);
                IVertexBuilder vertexBuilder = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, jetpackModel.renderType(new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/jetpack_" + ((JetpackItem) itemStack.getItem()).getJetpackType().getName() + ".png")), false, itemStack.getItem().isFoil(itemStack));
                jetpackModel.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
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
