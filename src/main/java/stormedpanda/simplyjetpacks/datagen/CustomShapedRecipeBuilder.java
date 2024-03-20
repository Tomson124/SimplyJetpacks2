package stormedpanda.simplyjetpacks.datagen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CustomShapedRecipeBuilder extends ShapedRecipeBuilder {

    private final Item result;
    private final int count;
    private final List<String> rows = Lists.newArrayList();
    private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private String group;
    private final List<ICondition> conditions = new ArrayList<>();

    public CustomShapedRecipeBuilder(ItemLike result, int count) {
        super(RecipeCategory.MISC, result, count); //TODO: Change Recipe Category
        this.result = result.asItem();
        this.count = count;
    }

    public CustomShapedRecipeBuilder addCondition(ICondition condition) {
        conditions.add(condition);
        return this;
    }

    private boolean hasCriteria() {
        return !advancement.getCriteria().isEmpty();
    }

    public static CustomShapedRecipeBuilder shaped(ItemLike item) {
        return shaped(item, 1);
    }

    public static CustomShapedRecipeBuilder shaped(ItemLike item, int count) {
        return new CustomShapedRecipeBuilder(item, count);
    }

    public CustomShapedRecipeBuilder define(Character character, TagKey<Item> item) {
        return this.define(character, Ingredient.of(item));
    }

    public CustomShapedRecipeBuilder define(Character character, ItemLike item) {
        return this.define(character, Ingredient.of(item));
    }

    public CustomShapedRecipeBuilder define(Character character, Ingredient item) {
        if (this.key.containsKey(character)) {
            throw new IllegalArgumentException("Symbol '" + character + "' is already defined!");
        } else if (character == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put(character, item);
            return this;
        }
    }

    public CustomShapedRecipeBuilder pattern(String pattern) {
        if (!this.rows.isEmpty() && pattern.length() != this.rows.get(0).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.rows.add(pattern);
            return this;
        }
    }

    public CustomShapedRecipeBuilder unlockedBy(String name, Criterion criteria) {
        this.advancement.addCriterion(name, criteria);
        return this;
    }

    public CustomShapedRecipeBuilder group(String group) {
        this.group = group;
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer) {
        this.save(consumer, BuiltInRegistries.ITEM.getKey(this.result));
    }

    public void save(Consumer<FinishedRecipe> consumer, String id) {
        ResourceLocation resourceLocation = BuiltInRegistries.ITEM.getKey(this.result);
        if ((new ResourceLocation(id)).equals(resourceLocation)) {
            throw new IllegalStateException("Shaped Recipe " + id + " should remove its 'save' argument");
        } else {
            this.save(consumer, new ResourceLocation(id));
        }
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        this.ensureValid(id);
        if (hasCriteria()) {
            this.advancement
                    .parent(new ResourceLocation(SimplyJetpacks.MODID, "root"))
                    .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                    .rewards(AdvancementRewards.Builder.recipe(id))
                    .requirements(RequirementsStrategy.OR);
        }
        consumer.accept(new CustomShapedRecipeBuilder.Result(id, this.result, this.count, this.group == null ? "" : this.group, this.rows, this.key, this.advancement));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.rows.isEmpty()) {
            throw new IllegalStateException("No pattern is defined for shaped recipe " + id + "!");
        } else {
            Set<Character> set = Sets.newHashSet(this.key.keySet());
            set.remove(' ');
            for (String s : this.rows) {
                for(int i = 0; i < s.length(); ++i) {
                    char c0 = s.charAt(i);
                    if (!this.key.containsKey(c0) && c0 != ' ') {
                        throw new IllegalStateException("Pattern in recipe " + id + " uses undefined symbol '" + c0 + "'");
                    }
                    set.remove(c0);
                }
            }
            if (!set.isEmpty()) {
                throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + id);
            } else if (this.rows.size() == 1 && this.rows.get(0).length() == 1) {
                throw new IllegalStateException("Shaped recipe " + id + " only takes in a single item - should it be a shapeless recipe instead?");
            } /*else if (this.advancement.getCriteria().isEmpty()) {
                throw new IllegalStateException("No way of obtaining recipe " + id);
            }*/
        }
    }

    public class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final List<String> pattern;
        private final Map<Character, Ingredient> key;
        private final Advancement.Builder advancement;

        public Result(ResourceLocation id, Item result, int count, @Nullable String group, List<String> pattern, Map<Character, Ingredient> key, Advancement.Builder advancement) {
            this.id = id;
            this.result = result;
            this.count = count;
            this.group = group;
            this.pattern = pattern;
            this.key = key;
            this.advancement = advancement;
        }

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
/*            if (!this.group.isEmpty()) {
                jsonObject.addProperty("group", this.group);
            }*/

            JsonArray patternArray = new JsonArray();
            for (String row : this.pattern) {
                patternArray.add(row);
            }
            jsonObject.add("pattern", patternArray);
            JsonObject jsonobject = new JsonObject();

            for (Map.Entry<Character, Ingredient> entry : this.key.entrySet()) {
                jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
            }

            jsonObject.add("key", jsonobject);
            JsonObject ingredientObject = new JsonObject();
            ingredientObject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                ingredientObject.addProperty("count", this.count);
            }

            jsonObject.add("result", ingredientObject);
        }

        @Override
        public JsonObject serializeRecipe() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", BuiltInRegistries.RECIPE_SERIALIZER.getKey(this.getType()).toString());

            JsonArray conditionsArray = new JsonArray();
            if (!CustomShapedRecipeBuilder.this.conditions.isEmpty()) {
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
        public RecipeSerializer<?> getType() {
            return RecipeSerializer.SHAPED_RECIPE;
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
