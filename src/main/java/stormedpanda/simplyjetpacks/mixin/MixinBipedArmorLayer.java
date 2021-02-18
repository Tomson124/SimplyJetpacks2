package stormedpanda.simplyjetpacks.mixin;

import javax.annotation.Nullable;

import com.mojang.blaze3d.matrix.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import stormedpanda.simplyjetpacks.util.JetpackUtil;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@Mixin(BipedArmorLayer.class)
public abstract class MixinBipedArmorLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>> extends LayerRenderer<T, M> {

	@Shadow
	@Final
	private A modelArmor;

	public MixinBipedArmorLayer(IEntityRenderer<T, M> entityRendererIn) {
		super(entityRendererIn);
	}

	@Shadow(remap = false)
	protected abstract A getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlotType slot, A model);

	@Shadow
	protected abstract void setModelSlotVisible(A modelIn, EquipmentSlotType slotIn);

	@Shadow(remap = false)
	protected abstract void func_241738_a_(MatrixStack p_241738_1_, IRenderTypeBuffer p_241738_2_, int p_241738_3_, boolean p_241738_5_, A p_241738_6_, float p_241738_8_, float p_241738_9_, float p_241738_10_, ResourceLocation armorResource);

	@Shadow(remap = false)
	public abstract ResourceLocation getArmorResource(Entity entity, ItemStack stack, EquipmentSlotType slot, @Nullable String type);

	@Inject(method = "render", at = @At(value = "RETURN"))
	private void renderJetPack(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
	                           float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		this.renderJetPackCurio(matrixStackIn, bufferIn, entitylivingbaseIn, packedLightIn, this.modelArmor);
	}

	private void renderJetPackCurio(MatrixStack matrices, IRenderTypeBuffer p_241739_2_, T livingEntity, int p_241739_5_, A model) {
		EquipmentSlotType slotType = EquipmentSlotType.CHEST;
		ItemStack itemstack = JetpackUtil.getJetpackFromBothSlots(livingEntity);
		if (itemstack.getItem() instanceof ArmorItem) {
			ArmorItem armoritem = (ArmorItem) itemstack.getItem();
			if (armoritem.getEquipmentSlot() == slotType) {
				model = this.getArmorModelHook(livingEntity, itemstack, slotType, model);
				this.getEntityModel().setModelAttributes(model);
				this.setModelSlotVisible(model, slotType);
				boolean glint = itemstack.hasEffect();
				if (armoritem instanceof IDyeableArmorItem) {
					int i = ((IDyeableArmorItem) armoritem).getColor(itemstack);
					float r = (i >> 16 & 255) / 255.0F;
					float g = (i >> 8 & 255) / 255.0F;
					float b = (i & 255) / 255.0F;
					this.func_241738_a_(matrices, p_241739_2_, p_241739_5_, glint, model, r, g, b, this.getArmorResource(livingEntity, itemstack, slotType, null));
					this.func_241738_a_(matrices, p_241739_2_, p_241739_5_, glint, model, 1.0F, 1.0F, 1.0F, this.getArmorResource(livingEntity, itemstack, slotType, "overlay"));
				} else {
					this.func_241738_a_(matrices, p_241739_2_, p_241739_5_, glint, model, 1.0F, 1.0F, 1.0F, this.getArmorResource(livingEntity, itemstack, slotType, null));
				}
			}
		}
	}

}
