package stormedpanda.simplyjetpacks.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.stream.Collectors;

@JeiPlugin
public class SJPluginJEI implements IModPlugin {

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(SimplyJetpacks.MODID, "simplyjetpacks");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration){
        //RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        registration.addRecipes(JetpackParticleRecipeMaker.createJetpackParticleRecipes(), VanillaRecipeCategoryUid.CRAFTING);
    }

    private static Collection<?> getRecipes(RecipeManager manager, IRecipeSerializer<?> serializer){
        return manager.getRecipes().parallelStream().filter(r -> r.getSerializer().equals(serializer)).collect(Collectors.toList());
    }

    private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type){
        return manager.getRecipes().parallelStream().filter(r -> r.getType().equals(type)).collect(Collectors.toList());
    }
}
