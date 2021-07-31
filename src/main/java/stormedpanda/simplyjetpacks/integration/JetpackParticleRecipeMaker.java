package stormedpanda.simplyjetpacks.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.datagen.SJTags;
import stormedpanda.simplyjetpacks.item.JetpackItem;

import java.util.ArrayList;
import java.util.List;

public final class JetpackParticleRecipeMaker {

    public static List<IRecipe<?>> createJetpackParticleRecipes() {
        List<IRecipe<?>> recipes = new ArrayList<>();
        String group = "simplyjetpacks.particle_customization";
        List<Item> jetpackList = SJTags.JETPACK.getValues();
        List<Item> particleList = SJTags.PARTICLES.getValues();
        ItemStack jetpackStack;
        ItemStack particleStack;
        for (Item jetpack : jetpackList) {
            jetpackStack = new ItemStack(jetpack);
            for (Item particle : particleList) {
                particleStack = new ItemStack(particle);
                NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY, Ingredient.of(particleStack), Ingredient.of(jetpackStack));
                ResourceLocation id = new ResourceLocation(SimplyJetpacks.MODID, particleStack.getItem() + "_" + jetpackStack.getItem());
                ItemStack output = JetpackItem.setParticleId(jetpackStack.copy(), particleStack);
                ShapelessRecipe recipe = new ShapelessRecipe(id, group, output, inputs);
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    public static List<IRecipe<?>> createJetpackParticleRecipesBackup() {
        List<IRecipe<?>> recipes = new ArrayList<>();
        String group = "simplyjetpacks.particle_customization";
        List<Item> jetpackList = SJTags.JETPACK.getValues();
        for (Item jetpack : jetpackList) {
            ItemStack stack = new ItemStack(jetpack);
            NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY, Ingredient.of(SJTags.PARTICLES), Ingredient.of(stack));
            ResourceLocation id = new ResourceLocation(SimplyJetpacks.MODID, "particle_" + stack.getItem());
            ShapelessRecipe recipe = new ShapelessRecipe(id, group, stack, inputs);
            recipes.add(recipe);
        }
        return recipes;
    }
}
