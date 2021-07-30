package stormedpanda.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import stormedpanda.simplyjetpacks.datagen.SJTags;
import stormedpanda.simplyjetpacks.init.RegistryHandler;

import java.util.ArrayList;
import java.util.List;

public final class JetpackParticleRecipeMaker {

    private JetpackParticleRecipeMaker() {
    }

    public static List<IRecipe<?>> createJetpackParticleRecipes() {
        List<IRecipe<?>> recipes = new ArrayList<>();
        String group = "simplyjetpacks.jetpacks.particles";
        // TODO: get all jetpacks.
        ItemStack baseStack = new ItemStack(RegistryHandler.JETPACK_CREATIVE.get());
        NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY, Ingredient.of(SJTags.PARTICLES), Ingredient.of(SJTags.JETPACK));
        ItemStack output = baseStack;
        ResourceLocation id = new ResourceLocation("minecraft:particle_" + output.getItem());
        ShapelessRecipe recipe = new ShapelessRecipe(id, group, output, inputs);
        recipes.add(recipe);
        return recipes;
    }
}
