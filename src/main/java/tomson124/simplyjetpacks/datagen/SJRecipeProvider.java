package tomson124.simplyjetpacks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.handlers.RegistryHandler;
import tomson124.simplyjetpacks.item.Jetpack;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.item.JetpackRegistry;
import tomson124.simplyjetpacks.util.JetpackUtil;

import java.util.concurrent.CompletableFuture;

public class SJRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public SJRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        // Simply Jetpacks:
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegistryHandler.LEATHER_STRAP.get())
                .pattern("LIL")
                .pattern("LIL")
                .define('I', commonTag("ingots/iron"))
                .define('L', commonTag("leather"))
                .unlockedBy("has_leather", has(Items.LEATHER))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegistryHandler.PILOT_GOGGLES_GOLD.get())
                .pattern(" S ")
                .pattern("GIG")
                .define('S', RegistryHandler.LEATHER_STRAP.get())
                .define('G', commonTag("glass_panes"))
                .define('I', commonTag("ingots/gold"))
                .unlockedBy("has_leather_strap", has(RegistryHandler.LEATHER_STRAP.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegistryHandler.PILOT_GOGGLES_IRON.get())
                .pattern(" S ")
                .pattern("GIG")
                .define('S', RegistryHandler.LEATHER_STRAP.get())
                .define('G', commonTag("glass_panes"))
                .define('I', commonTag("ingots/iron"))
                .unlockedBy("has_leather_strap", has(RegistryHandler.LEATHER_STRAP.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegistryHandler.PARTICLE_BLEND.get(), 2)
                .requires(Items.COAL)
                .requires(Items.CLAY_BALL)
                .requires(Items.GUNPOWDER)
                .requires(Items.BONE_MEAL)
                .unlockedBy("has_gunpowder", has(Items.GUNPOWDER))
                .save(output);

        particleRecipe(output, RegistryHandler.PARTICLE_NONE.get(), Items.GLASS);
        particleRecipe(output, RegistryHandler.PARTICLE_FLAME.get(), Items.TORCH);
        particleRecipe(output, RegistryHandler.PARTICLE_SMOKE.get(), Items.COAL);
        particleRecipe(output, RegistryHandler.PARTICLE_SOUL.get(), Items.SOUL_TORCH);
        particleRecipe(output, RegistryHandler.PARTICLE_SNOW.get(), Items.SNOWBALL);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RegistryHandler.PARTICLE_RAINBOW.get())
                .pattern(" R ")
                .pattern(" P ")
                .pattern("G B")
                .define('R', commonTag("dyes/red"))
                .define('G', commonTag("dyes/green"))
                .define('B', commonTag("dyes/blue"))
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .unlockedBy("has_particle_blend", has(RegistryHandler.PARTICLE_BLEND.get()))
                .save(output);

        jetpackRecipes(output);
    }

    /**
     * Jetpacks all share one registered item, so each variant is generated from its {@link Jetpack} entry
     * with the identity baked into the result stack rather than declared as a distinct item recipe.
     */
    private void jetpackRecipes(RecipeOutput output) {
        for (Jetpack jetpack : JetpackRegistry.getInstance().getJetpacks()) {
            if (jetpack.disabled || jetpack.creative) {
                continue;
            }

            Ingredient material = jetpack.getCraftingMaterial();
            if (material == Ingredient.EMPTY) {
                continue;
            }

            if (!jetpack.armored) {
                JetpackRecipeBuilder.of(JetpackItem.createStack(jetpack, false))
                        .pattern("M M")
                        .pattern("MSM")
                        .pattern("R R")
                        .define('M', material)
                        .define('S', Ingredient.of(RegistryHandler.LEATHER_STRAP.get()))
                        .define('R', Ingredient.of(Items.REDSTONE))
                        .group("simplyjetpacks.jetpack")
                        .unlockedBy("has_leather_strap", has(RegistryHandler.LEATHER_STRAP.get()))
                        .save(output, modId("jetpack/" + jetpack.name));
                continue;
            }

            // Armoured variants are the un-armoured pack plus its plating, and can be taken apart again.
            Jetpack base = JetpackRegistry.getInstance()
                    .getJetpackById(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, stripArmored(jetpack.name)));
            Item plating = platingFor(jetpack.platingId);
            if (base == Jetpack.UNDEFINED || plating == null) {
                continue;
            }

            JetpackRecipeBuilder.of(JetpackItem.createStack(jetpack, false))
                    .requires(JetpackUtil.ingredientFor(base))
                    .requires(Ingredient.of(plating))
                    .group("simplyjetpacks.jetpack_armored")
                    .unlockedBy("has_leather_strap", has(RegistryHandler.LEATHER_STRAP.get()))
                    .save(output, modId("jetpack/" + jetpack.name));

            JetpackRecipeBuilder.of(JetpackItem.createStack(base, false))
                    .requires(JetpackUtil.ingredientFor(jetpack))
                    .group("simplyjetpacks.jetpack")
                    .unlockedBy("has_leather_strap", has(RegistryHandler.LEATHER_STRAP.get()))
                    .save(output, modId("jetpack/" + base.name + "_from_armored"));
        }
    }

    private void particleRecipe(RecipeOutput output, Item result, Item surround) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', surround)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .unlockedBy("has_particle_blend", has(RegistryHandler.PARTICLE_BLEND.get()))
                .save(output);
    }

    private static String stripArmored(String name) {
        return name.endsWith("_armored") ? name.substring(0, name.length() - "_armored".length()) : name;
    }

    private static Item platingFor(int id) {
        return switch (id) {
            case 0 -> Items.IRON_CHESTPLATE;
            case 1 -> Items.GOLDEN_CHESTPLATE;
            case 2 -> Items.DIAMOND_CHESTPLATE;
            case 3 -> Items.NETHERITE_CHESTPLATE;
            default -> null;
        };
    }

    private static ResourceLocation modId(String path) {
        return ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, path);
    }

    /** 1.21 moved the cross-mod tag namespace from {@code forge:} to {@code c:}. */
    private static TagKey<Item> commonTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }
}
