package stormedpanda.simplyjetpacks.integration;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.registries.ForgeRegistries;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.datagen.SJTags;
import stormedpanda.simplyjetpacks.item.JetpackItem;

import java.util.ArrayList;
import java.util.List;

public final class JetpackParticleRecipeMaker {

    public static List<CraftingRecipe> createJetpackParticleRecipes() {
        List<CraftingRecipe> recipes = new ArrayList<>();
        String group = "simplyjetpacks.particle_customization";
        // TODO: test these
        List<Item> jetpackList = ForgeRegistries.ITEMS.tags().getTag(SJTags.JETPACK).stream().toList();
        List<Item> particleList = ForgeRegistries.ITEMS.tags().getTag(SJTags.PARTICLES).stream().toList();
//        List<Item> jetpackList = SJTags.JETPACK.getValues();
//        List<Item> particleList = SJTags.PARTICLES.getValues();
        ItemStack jetpackStack;
        ItemStack particleStack;
        for (Item jetpack : jetpackList) {
            jetpackStack = new ItemStack(jetpack);
            for (Item particle : particleList) {
                particleStack = new ItemStack(particle);
                NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY, Ingredient.of(particleStack), Ingredient.of(jetpackStack));
                ResourceLocation id = new ResourceLocation(SimplyJetpacks.MODID, particleStack.getItem() + "_" + jetpackStack.getItem());
                ItemStack output = JetpackItem.setParticleId(jetpackStack.copy(), particleStack);
                ShapelessRecipe recipe = new ShapelessRecipe(id, group, CraftingBookCategory.MISC, output, inputs);
                recipes.add(recipe);
            }
        }
        return recipes;
    }
}
