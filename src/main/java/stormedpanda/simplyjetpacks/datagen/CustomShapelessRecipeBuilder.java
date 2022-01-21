package stormedpanda.simplyjetpacks.datagen;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CustomShapelessRecipeBuilder extends ShapelessRecipeBuilder {

    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private String group;
    private final List<ICondition> conditions = new ArrayList<>();

    public CustomShapelessRecipeBuilder(IItemProvider result, int count) {
        super(result, count);
        this.result = result.asItem();
        this.count = count;
    }

    public CustomShapelessRecipeBuilder addCondition(ICondition condition) {
        conditions.add(condition);
        return this;
    }

    private boolean hasCriteria() {
        return !advancement.getCriteria().isEmpty();
    }

    public static CustomShapelessRecipeBuilder shapeless(IItemProvider item) {
        return new CustomShapelessRecipeBuilder(item, 1);
    }

    public static CustomShapelessRecipeBuilder shapeless(IItemProvider item, int count) {
        return new CustomShapelessRecipeBuilder(item, count);
    }

    public CustomShapelessRecipeBuilder requires(ITag<Item> item) {
        return this.requires(Ingredient.of(item));
    }

    public CustomShapelessRecipeBuilder requires(IItemProvider item) {
        return this.requires(item, 1);
    }

    public CustomShapelessRecipeBuilder requires(IItemProvider item, int count) {
        for (int i = 0; i < count; ++i) {
            this.requires(Ingredient.of(item));
        }
        return this;
    }

    public CustomShapelessRecipeBuilder requires(Ingredient item) {
        return this.requires(item, 1);
    }

    public CustomShapelessRecipeBuilder requires(Ingredient item, int count) {
        for (int i = 0; i < count; ++i) {
            this.ingredients.add(item);
        }
        return this;
    }

    public CustomShapelessRecipeBuilder unlockedBy(String name, ICriterionInstance criteria) {
        this.advancement.addCriterion(name, criteria);
        return this;
    }

    public CustomShapelessRecipeBuilder group(String group) {
        this.group = group;
        return this;
    }

    public void save(Consumer<IFinishedRecipe> consumer) {
        this.save(consumer, Registry.ITEM.getKey(this.result));
    }

    public void save(Consumer<IFinishedRecipe> consumer, String id) {
        ResourceLocation resourceLocation = Registry.ITEM.getKey(this.result);
        if ((new ResourceLocation(id)).equals(resourceLocation)) {
            throw new IllegalStateException("Shapeless Recipe " + id + " should remove its 'save' argument");
        } else {
            this.save(consumer, new ResourceLocation(id));
        }
    }

    public void save(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
        this.ensureValid(id);
        if (hasCriteria()) {
            this.advancement
                    .parent(new ResourceLocation(SimplyJetpacks.MODID, "root"))
                    .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                    .rewards(AdvancementRewards.Builder.recipe(id))
                    .requirements(IRequirementsStrategy.OR);
        }
        consumer.accept(new CustomShapelessRecipeBuilder.Result(id, this.result, this.count, this.group == null ? "" : this.group, this.ingredients, this.advancement));
    }

    private void ensureValid(ResourceLocation id) {
        /*if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }*/
    }

    public class Result implements IFinishedRecipe {

        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final List<Ingredient> ingredients;
        private final Advancement.Builder advancement;

        public Result(ResourceLocation id, Item result, int count, @Nullable String group, List<Ingredient> ingredients, Advancement.Builder advancement) {
            this.id = id;
            this.result = result;
            this.count = count;
            this.group = group;
            this.ingredients = ingredients;
            this.advancement = advancement;
        }

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            /*if (!this.group.isEmpty()) {
                jsonObject.addProperty("group", this.group);
            }*/

            JsonArray ingredientsArray = new JsonArray();
            for (Ingredient ingredient : this.ingredients) {
                ingredientsArray.add(ingredient.toJson());
            }
            jsonObject.add("ingredients", ingredientsArray);

            JsonObject resultObject = new JsonObject();
            resultObject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                resultObject.addProperty("count", this.count);
            }

            jsonObject.add("result", resultObject);
        }

        @Override
        public JsonObject serializeRecipe() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", Registry.RECIPE_SERIALIZER.getKey(this.getType()).toString());

            JsonArray conditionsArray = new JsonArray();
            if (!CustomShapelessRecipeBuilder.this.conditions.isEmpty()) {
                for (ICondition condition : conditions) {
                    conditionsArray.add(CraftingHelper.serialize(condition));
                }
                jsonObject.add("conditions", conditionsArray);
            }

            this.serializeRecipeData(jsonObject);
            return jsonObject;
        }

        @Nonnull
        @Override
        public IRecipeSerializer<?> getType() {
            return IRecipeSerializer.SHAPELESS_RECIPE;
        }

        @Nonnull
        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return hasCriteria() ? this.advancement.serializeToJson() : null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return new ResourceLocation(SimplyJetpacks.MODID, (group != null ? group : "") + id.getPath());
        }
    }
}
