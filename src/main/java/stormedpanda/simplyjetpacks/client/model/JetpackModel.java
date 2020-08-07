package stormedpanda.simplyjetpacks.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.player.PlayerEntity;

public class JetpackModel extends BipedModel<PlayerEntity> {

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
		super(1f, 0f, 64, 64);
		textureWidth = 64;
		textureHeight = 64;

		middle = new ModelRenderer(this, 0, 54);//.setTextureSize(64, 64);
		middle.setRotationPoint(0F, 0F, 0F);
		middle.mirror = true;
		this.setRotation(middle, 0F, 0F, 0F);

		leftCanister = new ModelRenderer(this, 0, 32);//.setTextureSize(64, 64);
		leftCanister.setRotationPoint(0F, 0F, 0F);
		leftCanister.mirror = true;
		this.setRotation(leftCanister, 0F, 0F, 0F);

		rightCanister = new ModelRenderer(this, 17, 32);//.setTextureSize(64, 64);
		rightCanister.setRotationPoint(0F, 0F, 0F);
		rightCanister.mirror = true;
		this.setRotation(rightCanister, 0F, 0F, 0F);

		leftTip1 = new ModelRenderer(this, 0, 45);//.setTextureSize(64, 64);
		leftTip1.setRotationPoint(0F, 0F, 0F);
		leftTip1.mirror = true;
		this.setRotation(leftTip1, 0F, 0F, 0F);

		leftTip2 = new ModelRenderer(this, 0, 49);//.setTextureSize(64, 64);
		leftTip2.setRotationPoint(0F, 0F, 0F);
		leftTip2.mirror = true;
		this.setRotation(leftTip2, 0F, 0F, 0F);

		rightTip1 = new ModelRenderer(this, 17, 45);//.setTextureSize(64, 64);
		rightTip1.setRotationPoint(0F, 0F, 0F);
		rightTip1.mirror = true;
		this.setRotation(rightTip1, 0F, 0F, 0F);

		rightTip2 = new ModelRenderer(this, 17, 49);//.setTextureSize(64, 64);
		rightTip2.setRotationPoint(0F, 0F, 0F);
		rightTip2.mirror = true;
		this.setRotation(rightTip2, 0F, 0F, 0F);

		leftExhaust1 = new ModelRenderer(this, 35, 32);//.setTextureSize(64, 64);
		leftExhaust1.addBox(1F, 9F, 3.1F, 3, 1, 3);
		leftExhaust1.mirror = true;
		this.setRotation(leftExhaust1, 0F, 0F, 0F);

		leftExhaust2 = new ModelRenderer(this, 35, 37);//.setTextureSize(64, 64);
		leftExhaust2.setRotationPoint(0F, 0F, 0F);
		leftExhaust2.mirror = true;
		this.setRotation(leftExhaust2, 0F, 0F, 0F);

		rightExhaust1 = new ModelRenderer(this, 48, 32);//.setTextureSize(64, 64);
		rightExhaust1.setRotationPoint(0F, 0F, 0F);
		rightExhaust1.mirror = true;
		this.setRotation(rightExhaust1, 0F, 0F, 0F);

		rightExhaust2 = new ModelRenderer(this, 35, 45);//.setTextureSize(64, 64);
		rightExhaust2.setRotationPoint(0F, 0F, 0F);
		rightExhaust2.mirror = true;
		this.setRotation(rightExhaust2, 0F, 0F, 0F);

		setupCustomModel();
	}

	private void setupCustomModel() {
		bipedBody.addChild(middle);
		middle.addBox(-2F, 3F, 3.6F, 4, 5, 2);
		bipedBody.addChild(leftCanister);
		leftCanister.addBox(0.5F, 2F, 2.6F, 4, 7, 4);
		bipedBody.addChild(rightCanister);
		rightCanister.addBox(-4.5F, 2F, 2.6F, 4, 7, 4);
		bipedBody.addChild(leftTip1);
		leftTip1.addBox(1F, 1F, 3.1F, 3, 1, 3);
		bipedBody.addChild(leftTip2);
		leftTip2.addBox(1.5F, -1F, 3.6F, 2, 2, 2);
		bipedBody.addChild(rightTip1);
		rightTip1.addBox(-4F, 1F, 3.1F, 3, 1, 3);
		bipedBody.addChild(rightTip2);
		rightTip2.addBox(-3.5F, -1F, 3.6F, 2, 2, 2);
		bipedBody.addChild(leftExhaust1);
		leftExhaust1.setRotationPoint(0F, 0F, 0F);
		bipedBody.addChild(leftExhaust2);
		leftExhaust2.addBox(0.5F, 10F, 2.6F, 4, 3, 4);
		bipedBody.addChild(rightExhaust1);
		rightExhaust1.addBox(-4F, 9F, 3.1F, 3, 1, 3);
		bipedBody.addChild(rightExhaust2);
		rightExhaust2.addBox(-4.5F, 10F, 2.6F, 4, 3, 4);
	}

	@SuppressWarnings("rawtypes")
	public BipedModel<PlayerEntity> applyData(BipedModel defaultArmor) {
		this.isChild = defaultArmor.isChild;
		this.isSneak = defaultArmor.isSneak;
		this.isSitting = defaultArmor.isSitting;
		this.rightArmPose = defaultArmor.rightArmPose;
		this.leftArmPose = defaultArmor.leftArmPose;
/*		middle.showModel = true;
		leftCanister.showModel = true;
		rightCanister.showModel = true;
		leftTip1.showModel = true;
		leftTip2.showModel = true;
		rightTip1.showModel = true;
		rightTip2.showModel = true;
		leftExhaust1.showModel = true;
		leftExhaust2.showModel = true;
		rightExhaust1.showModel = true;
		rightExhaust2.showModel = true;*/
		return this;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}