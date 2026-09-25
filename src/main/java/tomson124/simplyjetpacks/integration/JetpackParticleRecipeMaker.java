package tomson124.simplyjetpacks.integration;

import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.datagen.SJTags;
import tomson124.simplyjetpacks.item.Jetpack;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.item.JetpackRegistry;

import java.util.ArrayList;
import java.util.List;

public final class JetpackParticleRecipeMaker {

    private JetpackParticleRecipeMaker() {
    }

    public static List<RecipeHolder<CraftingRecipe>> createJetpackParticleRecipes() {
        List<RecipeHolder<CraftingRecipe>> recipes = new ArrayList<>();
        String group = "simplyjetpacks.particle_customization";

        List<ItemStack> particles = BuiltInRegistries.ITEM.getTag(SJTags.PARTICLES)
                .map(holders -> holders.stream().map(holder -> new ItemStack(holder.value())).toList())
                .orElse(List.of());

        if (particles.isEmpty()) {
            return recipes;
        }

        for (Jetpack jetpack : JetpackRegistry.getInstance().getJetpacks()) {
            if (jetpack.disabled) {
                continue;
            }
            ItemStack jetpackStack = JetpackItem.createStack(jetpack, false);
            for (ItemStack particleStack : particles) {
                NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY,
                        Ingredient.of(particleStack), Ingredient.of(jetpackStack));
                ItemStack output = JetpackItem.setParticleId(jetpackStack.copy(), particleStack);
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID,
                        "particle/" + jetpack.name + "/"
                                + BuiltInRegistries.ITEM.getKey(particleStack.getItem()).getPath());
                recipes.add(new RecipeHolder<>(id, new ShapelessRecipe(group, CraftingBookCategory.MISC, output, inputs)));
            }
        }
        return recipes;
    }
}
