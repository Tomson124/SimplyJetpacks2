package stormedpanda.simplyjetpacks.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CurioArmorModel extends HumanoidModel {

    public CurioArmorModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createCurioModel (CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.getChild("body");

        body.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 59).addBox(-4.5F, 10.0F, -2.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.2F)),
                PartPose.offset(0.5F, 1F, 0.0F)
        );

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
