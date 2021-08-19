package stormedpanda.simplyjetpacks.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class JetpackModel extends BipedModel<LivingEntity> {

    private final ModelRenderer middle;
    private final ModelRenderer leftCanister;
    private final ModelRenderer rightCanister;
    private final ModelRenderer leftTip1;
    private final ModelRenderer leftTip2;
    private final ModelRenderer rightTip1;
    private final ModelRenderer rightTip2;
    private final ModelRenderer leftExhaust1;
    private final ModelRenderer leftExhaust2;
    private final ModelRenderer rightExhaust1;
    private final ModelRenderer rightExhaust2;

    public JetpackModel() {
        super(1F, 0F, 64, 64);
        texWidth = 64;
        texHeight = 64;

        middle = new ModelRenderer(this, 0, 54);
        middle.setPos(0F, 0F, 0F);
        middle.mirror = true;
        this.setRotation(middle, 0F, 0F, 0F);

        leftCanister = new ModelRenderer(this, 0, 32);
        leftCanister.setPos(0F, 0F, 0F);
        leftCanister.mirror = true;
        this.setRotation(leftCanister, 0F, 0F, 0F);

        rightCanister = new ModelRenderer(this, 17, 32);
        rightCanister.setPos(0F, 0F, 0F);
        rightCanister.mirror = true;
        this.setRotation(rightCanister, 0F, 0F, 0F);

        leftTip1 = new ModelRenderer(this, 0, 45);
        leftTip1.setPos(0F, 0F, 0F);
        leftTip1.mirror = true;
        this.setRotation(leftTip1, 0F, 0F, 0F);

        leftTip2 = new ModelRenderer(this, 0, 49);
        leftTip2.setPos(0F, 0F, 0F);
        leftTip2.mirror = true;
        this.setRotation(leftTip2, 0F, 0F, 0F);

        rightTip1 = new ModelRenderer(this, 17, 45);
        rightTip1.setPos(0F, 0F, 0F);
        rightTip1.mirror = true;
        this.setRotation(rightTip1, 0F, 0F, 0F);

        rightTip2 = new ModelRenderer(this, 17, 49);
        rightTip2.setPos(0F, 0F, 0F);
        rightTip2.mirror = true;
        this.setRotation(rightTip2, 0F, 0F, 0F);

        leftExhaust1 = new ModelRenderer(this, 35, 32);
        leftExhaust1.addBox(1F, 9F, 3.1F, 3, 1, 3);
        leftExhaust1.mirror = true;
        this.setRotation(leftExhaust1, 0F, 0F, 0F);

        leftExhaust2 = new ModelRenderer(this, 35, 37);
        leftExhaust2.setPos(0F, 0F, 0F);
        leftExhaust2.mirror = true;
        this.setRotation(leftExhaust2, 0F, 0F, 0F);

        rightExhaust1 = new ModelRenderer(this, 48, 32);
        rightExhaust1.setPos(0F, 0F, 0F);
        rightExhaust1.mirror = true;
        this.setRotation(rightExhaust1, 0F, 0F, 0F);

        rightExhaust2 = new ModelRenderer(this, 35, 45);
        rightExhaust2.setPos(0F, 0F, 0F);
        rightExhaust2.mirror = true;
        this.setRotation(rightExhaust2, 0F, 0F, 0F);

        setupCustomModel();
    }

    private void setupCustomModel() {
        body.addChild(middle);
        middle.addBox(-2F, 3F, 3.6F, 4, 5, 2);
        body.addChild(leftCanister);
        leftCanister.addBox(0.5F, 2F, 2.6F, 4, 7, 4);
        body.addChild(rightCanister);
        rightCanister.addBox(-4.5F, 2F, 2.6F, 4, 7, 4);
        body.addChild(leftTip1);
        leftTip1.addBox(1F, 1F, 3.1F, 3, 1, 3);
        body.addChild(leftTip2);
        leftTip2.addBox(1.5F, -1F, 3.6F, 2, 2, 2);
        body.addChild(rightTip1);
        rightTip1.addBox(-4F, 1F, 3.1F, 3, 1, 3);
        body.addChild(rightTip2);
        rightTip2.addBox(-3.5F, -1F, 3.6F, 2, 2, 2);
        body.addChild(leftExhaust1);
        leftExhaust1.addBox(1F, 9F, 3.1F, 3, 1, 3);
        body.addChild(leftExhaust2);
        leftExhaust2.addBox(0.5F, 10F, 2.6F, 4, 3, 4);
        body.addChild(rightExhaust1);
        rightExhaust1.addBox(-4F, 9F, 3.1F, 3, 1, 3);
        body.addChild(rightExhaust2);
        rightExhaust2.addBox(-4.5F, 10F, 2.6F, 4, 3, 4);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }
}