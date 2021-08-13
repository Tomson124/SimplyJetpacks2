package stormedpanda.simplyjetpacks.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.lists.ArmorMaterialList;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;

public class PilotGogglesItem extends ArmorItem implements ICurioItem {

    private BipedModel<LivingEntity> model;
    private final String type;

    public PilotGogglesItem(String type) {
        super(ArmorMaterialList.PILOT_GOGGLES, EquipmentSlotType.HEAD, new Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
        this.type = type;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_" + this.type + "_layer_1.png").toString();
    }

    /* ICurioItem start */
    @Override
    public boolean canRender(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        return true;
    }

    @Override
    public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, ItemStack stack) {
        if (this.model == null) {
            this.model = new BipedModel<>(1.0F);
        }
        BipedModel<LivingEntity> gogglesModel = this.model;
        ICurio.RenderHelper.followHeadRotations(livingEntity, gogglesModel.head);
        IVertexBuilder vertexBuilder = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, gogglesModel.renderType(new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_" + this.type + "_layer_1.png")), false, stack.getItem().isFoil(stack));
        gogglesModel.head.render(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    /* ICurioItem end */
}
