package stormedpanda.simplyjetpacks.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class JetpackModel<T extends LivingEntity> extends HumanoidModel<T> {

    private final ModelPart middle;
    private final ModelPart leftCanister;
    private final ModelPart rightCanister;
    private final ModelPart leftTip1;
    private final ModelPart leftTip2;
    private final ModelPart rightTip1;
    private final ModelPart rightTip2;
    private final ModelPart leftExhaust1;
    private final ModelPart leftExhaust2;
    private final ModelPart rightExhaust1;
    private final ModelPart rightExhaust2;

    public JetpackModel(ModelPart model) {
        super(model);
        this.middle = model.getChild("middle");
        this.leftCanister = model.getChild("leftCanister");
        this.rightCanister = model.getChild("rightCanister");
        this.leftTip1 = model.getChild("leftTip1");
        this.leftTip2 = model.getChild("leftTip2");
        this.rightTip1 = model.getChild("rightTip1");
        this.rightTip2 = model.getChild("rightTip2");
        this.leftExhaust1 = model.getChild("leftExhaust1");
        this.leftExhaust2 = model.getChild("leftExhaust2");
        this.rightExhaust1 = model.getChild("rightExhaust1");
        this.rightExhaust2 = model.getChild("rightExhaust2");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(1.0F), 0.0F);
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("middle", CubeListBuilder.create().mirror()
                .texOffs(0, 54)
                .addBox(-2F, 3F, 3.6F, 4, 5, 2), PartPose.ZERO);

        root.addOrReplaceChild("leftCanister", CubeListBuilder.create().mirror()
                .texOffs(0, 32)
                .addBox(0.5F, 2F, 2.6F, 4, 7, 4), PartPose.ZERO);

        root.addOrReplaceChild("rightCanister", CubeListBuilder.create().mirror()
                .texOffs(17, 32)
                .addBox(-4.5F, 2F, 2.6F, 4, 7, 4), PartPose.ZERO);

        root.addOrReplaceChild("leftTip1", CubeListBuilder.create().mirror()
                .texOffs(0, 45)
                .addBox(1F, 1F, 3.1F, 3, 1, 3), PartPose.ZERO);

        root.addOrReplaceChild("leftTip2", CubeListBuilder.create().mirror()
                .texOffs(0, 49)
                .addBox(1.5F, -1F, 3.6F, 2, 2, 2), PartPose.ZERO);

        root.addOrReplaceChild("rightTip1", CubeListBuilder.create().mirror()
                .texOffs(17, 45)
                .addBox(-4F, 1F, 3.1F, 3, 1, 3), PartPose.ZERO);

        root.addOrReplaceChild("rightTip2", CubeListBuilder.create().mirror()
                .texOffs(17, 49)
                .addBox(-3.5F, -1F, 3.6F, 2, 2, 2), PartPose.ZERO);

        root.addOrReplaceChild("leftExhaust1", CubeListBuilder.create().mirror()
                .texOffs(35, 32)
                .addBox(1F, 9F, 3.1F, 3, 1, 3), PartPose.ZERO);

        root.addOrReplaceChild("leftExhaust2", CubeListBuilder.create().mirror()
                .texOffs(35, 37)
                .addBox(0.5F, 10F, 2.6F, 4, 3, 4), PartPose.ZERO);

        root.addOrReplaceChild("rightExhaust1", CubeListBuilder.create().mirror()
                .texOffs(48, 32)
                .addBox(-4F, 9F, 3.1F, 3, 1, 3), PartPose.ZERO);

        root.addOrReplaceChild("rightExhaust2", CubeListBuilder.create().mirror()
                .texOffs(35, 45)
                .addBox(-4.5F, 10F, 2.6F, 4, 3, 4), PartPose.ZERO);

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        this.middle.copyFrom(this.body);
        this.leftCanister.copyFrom(this.body);
        this.rightCanister.copyFrom(this.body);
        this.leftTip1.copyFrom(this.body);
        this.leftTip2.copyFrom(this.body);
        this.rightTip1.copyFrom(this.body);
        this.rightTip2.copyFrom(this.body);
        this.leftExhaust1.copyFrom(this.body);
        this.leftExhaust2.copyFrom(this.body);
        this.rightExhaust1.copyFrom(this.body);
        this.rightExhaust2.copyFrom(this.body);

        return ImmutableList.of(
                this.middle,
                this.leftCanister,
                this.rightCanister,
                this.leftTip1,
                this.leftTip2,
                this.rightTip1,
                this.rightTip2,
                this.leftExhaust1,
                this.leftExhaust2,
                this.rightExhaust1,
                this.rightExhaust2
        );
    }

    @Override
    public void renderToBuffer(PoseStack poseStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        // Could be used to alter texture of some of the elements?
        //ResourceLocation TEXTURE = new ResourceLocation(SimplyJetpacks.MODID, "texture_path");
        //VertexConsumer vertexConsumer = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.entityTranslucent(TEXTURE));
        //super.renderToBuffer(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.middle.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leftCanister.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.rightCanister.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leftTip1.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leftTip2.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.rightTip1.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.rightTip2.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leftExhaust1.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leftExhaust2.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.rightExhaust1.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.rightExhaust2.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.body.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leftArm.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.rightArm.render(poseStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
