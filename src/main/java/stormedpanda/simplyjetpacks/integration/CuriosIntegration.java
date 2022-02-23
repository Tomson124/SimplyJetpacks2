package stormedpanda.simplyjetpacks.integration;

// TODO: fix this.
public class CuriosIntegration {

    /*public static ICapabilityProvider initGogglesCapabilities(ItemStack itemStack) {
        return getProvider(new ICurio() {

            @Override
            public void playRightClickEquipSound(LivingEntity livingEntity) {
                livingEntity.level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), ((ArmorItem)itemStack.getItem()).getMaterial().getEquipSound(), SoundCategory.PLAYERS, 1.0F, 1.0F);
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
            public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
                return true;
            }


            @Override
            public void render(String identifier, int index, PoseStack matrixStack, RenderType renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                HumanoidModel<LivingEntity> gogglesModel = new HumanoidModel<>(1.0F);
                ICurioRenderer.followHeadRotations(livingEntity, gogglesModel.head);
                //VertexConsumer vertexBuilder = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, gogglesModel.renderType(new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_" + ((PilotGogglesItem) itemStack.getItem()).getType() + "_layer_1.png")), false, itemStack.getItem().isFoil(itemStack));
                VertexConsumer vertexBuilder = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, gogglesModel.renderType(new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/pilot_goggles_" + ((PilotGogglesItem) itemStack.getItem()).getType() + ".png")), false, itemStack.getItem().isFoil(itemStack));
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

            @Override
            public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
                return true;
            }

            public void render(String identifier, int index, PoseStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                JetpackModelNew jetpackModel = new JetpackModelNew();
                ICurio.RenderHelper.followBodyRotations(livingEntity, jetpackModel);
                VertexConsumer vertexBuilder = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, jetpackModel.renderType(new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/jetpack_" + ((JetpackItem) itemStack.getItem()).getJetpackType().getName() + ".png")), false, itemStack.getItem().isFoil(itemStack));
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
    }*/
}
