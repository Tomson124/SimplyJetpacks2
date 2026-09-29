package tomson124.simplyjetpacks.datagen;

import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.core.NonNullList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Emits crafting recipes whose result is a specific jetpack -- i.e. one item stack carrying a
 * {@code JETPACK_ID} component. Vanilla's builders only take an {@code ItemLike} result, which cannot
 * express "which" jetpack now that they all share one registered item.
 */
public final class JetpackRecipeBuilder {

    private final ItemStack result;
    private final Map<Character, Ingredient> key = new HashMap<>();
    private final List<String> pattern = new ArrayList<>();
    private final List<Ingredient> shapelessIngredients = new ArrayList<>();
    private Criterion<?> unlockedBy;
    private String criterionName = "has_ingredient";
    private String group = "";

    private JetpackRecipeBuilder(ItemStack result) {
        this.result = result;
    }

    public static JetpackRecipeBuilder of(ItemStack result) {
        return new JetpackRecipeBuilder(result);
    }

    public JetpackRecipeBuilder pattern(String row) {
        this.pattern.add(row);
        return this;
    }

    public JetpackRecipeBuilder define(char symbol, Ingredient ingredient) {
        this.key.put(symbol, ingredient);
        return this;
    }

    public JetpackRecipeBuilder requires(Ingredient ingredient) {
        this.shapelessIngredients.add(ingredient);
        return this;
    }

    public JetpackRecipeBuilder group(String group) {
        this.group = group;
        return this;
    }

    public JetpackRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criterionName = name;
        this.unlockedBy = criterion;
        return this;
    }

    public void save(RecipeOutput output, ResourceLocation id) {
        if (unlockedBy == null) {
            throw new IllegalStateException("No unlock criterion for jetpack recipe " + id);
        }

        var advancement = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .addCriterion(criterionName, unlockedBy)
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);

        var recipe = pattern.isEmpty()
                ? new ShapelessRecipe(group, CraftingBookCategory.EQUIPMENT, result,
                        NonNullList.copyOf(shapelessIngredients))
                : new ShapedRecipe(group, CraftingBookCategory.EQUIPMENT,
                        ShapedRecipePattern.of(key, pattern), result);

        output.accept(id, recipe, advancement.build(id.withPrefix("recipes/")));
    }
}
