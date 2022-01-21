package stormedpanda.simplyjetpacks.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

import javax.annotation.Nonnull;

@JeiPlugin
public class SJPluginJEI implements IModPlugin {

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(SimplyJetpacks.MODID, "simplyjetpacks");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration){
        registration.addRecipes(JetpackParticleRecipeMaker.createJetpackParticleRecipes(), VanillaRecipeCategoryUid.CRAFTING);
    }
}
