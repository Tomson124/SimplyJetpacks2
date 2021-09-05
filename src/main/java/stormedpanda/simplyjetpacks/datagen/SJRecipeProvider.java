package stormedpanda.simplyjetpacks.datagen;

import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class SJRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public SJRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        CustomRecipeBuilder.special(RegistryHandler.JETPACK_SPECIAL_RECIPE.get()).save(consumer, savePath("jetpack_special_recipe"));

        ShapedRecipeBuilder.shaped(RegistryHandler.LEATHER_STRAP.get())
                .pattern("LIL")
                .pattern("LIL")
                .define('I', forgeTag("ingots/iron"))
                .define('L', forgeTag("leather"))
                .unlockedBy("has_item", InventoryChangeTrigger.Instance.hasItems(Items.LEATHER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RegistryHandler.JETPACK_POTATO.get())
                .pattern("S S")
                .pattern("GPG")
                .pattern("R R")
                .define('S', forgeTag("string"))
                .define('G', forgeTag("nuggets/gold"))
                .define('P', forgeTag("crops/potato"))
                .define('R', forgeTag("dusts/redstone"))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RegistryHandler.PILOT_GOGGLES_GOLD.get())
                .pattern(" S ")
                .pattern("GIG")
                .define('S', RegistryHandler.LEATHER_STRAP.get())
                .define('G', forgeTag("glass_panes"))
                .define('I', forgeTag("ingots/gold"))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RegistryHandler.PILOT_GOGGLES_IRON.get())
                .pattern(" S ")
                .pattern("GIG")
                .define('S', RegistryHandler.LEATHER_STRAP.get())
                .define('G', forgeTag("glass_panes"))
                .define('I', forgeTag("ingots/iron"))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RegistryHandler.PARTICLE_BLEND.get(), 2)
                .requires(Items.COAL)
                .requires(Items.CLAY_BALL)
                .requires(Items.GUNPOWDER)
                .requires(Items.BONE_MEAL)
                .save(consumer);

        ShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_NONE.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.GLASS)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        ShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_FLAME.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.TORCH)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        ShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_SMOKE.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.COAL)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        ShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_RAINBOW.get())
                .pattern(" R ")
                .pattern(" P ")
                .pattern("G B")
                .define('R', forgeTag("dyes/red"))
                .define('G', forgeTag("dyes/green"))
                .define('B', forgeTag("dyes/blue"))
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        ShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_SOUL.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.SOUL_TORCH)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        ShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_SNOW.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.SNOWBALL)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(SimplyJetpacks.MODID, path);
    }

    private static Tags.IOptionalNamedTag<Item> forgeTag(String name) {
        return ItemTags.createOptional(new ResourceLocation("forge", name));
    }

    private static Ingredient fromJson(String name) {
        JsonObject object = new JsonObject();
        object.addProperty("item", name);
        return Ingredient.fromJson(object);
    }

    private static String savePath(String path) {
        return new ResourceLocation(SimplyJetpacks.MODID, path).toString();
    }
}
