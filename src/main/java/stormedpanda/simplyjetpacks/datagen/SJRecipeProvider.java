package stormedpanda.simplyjetpacks.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;

import java.util.function.Consumer;

public class SJRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public SJRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        SpecialRecipeBuilder.special((SimpleRecipeSerializer<?>) RegistryHandler.JETPACK_CUSTOM_RECIPE.get()).save(consumer, savePath("jetpack_custom_recipe"));

        // Simply Jetpacks:
        CustomShapedRecipeBuilder.shaped(RegistryHandler.LEATHER_STRAP.get())
                .pattern("LIL")
                .pattern("LIL")
                .define('I', forgeTag("ingots/iron"))
                .define('L', forgeTag("leather"))
                .save(consumer);

        CustomShapedRecipeBuilder.shaped(RegistryHandler.PILOT_GOGGLES_GOLD.get())
                .pattern(" S ")
                .pattern("GIG")
                .define('S', RegistryHandler.LEATHER_STRAP.get())
                .define('G', forgeTag("glass_panes"))
                .define('I', forgeTag("ingots/gold"))
                .save(consumer);

        CustomShapedRecipeBuilder.shaped(RegistryHandler.PILOT_GOGGLES_IRON.get())
                .pattern(" S ")
                .pattern("GIG")
                .define('S', RegistryHandler.LEATHER_STRAP.get())
                .define('G', forgeTag("glass_panes"))
                .define('I', forgeTag("ingots/iron"))
                .save(consumer);

        CustomShapelessRecipeBuilder.shapeless(RegistryHandler.PARTICLE_BLEND.get(), 2)
                .requires(Items.COAL)
                .requires(Items.CLAY_BALL)
                .requires(Items.GUNPOWDER)
                .requires(Items.BONE_MEAL)
                .save(consumer);

        CustomShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_NONE.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.GLASS)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        CustomShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_FLAME.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.TORCH)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        CustomShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_SMOKE.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.COAL)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        CustomShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_RAINBOW.get())
                .pattern(" R ")
                .pattern(" P ")
                .pattern("G B")
                .define('R', forgeTag("dyes/red"))
                .define('G', forgeTag("dyes/green"))
                .define('B', forgeTag("dyes/blue"))
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        CustomShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_SOUL.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.SOUL_TORCH)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        CustomShapedRecipeBuilder.shaped(RegistryHandler.PARTICLE_SNOW.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.SNOWBALL)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(consumer);

        CustomShapedRecipeBuilder.shaped(RegistryHandler.JETPACK_POTATO.get())
                .pattern("S S")
                .pattern("GPG")
                .pattern("R R")
                .define('S', forgeTag("string"))
                .define('G', forgeTag("nuggets/gold"))
                .define('P', forgeTag("crops/potato"))
                .define('R', forgeTag("dusts/redstone"))
                .save(consumer);

        // Testing:
        /*CustomShapedRecipeBuilder.shaped(RegistryHandler.JETPACK_CREATIVE_ARMORED.get())
                .pattern("S S")
                .pattern("GPG")
                .pattern("R R")
                .define('S', forgeTag("string"))
                .define('G', forgeTag("nuggets/gold"))
                .define('P', forgeTag("crops/potato"))
                .define('R', forgeTag("dusts/redstone"))
                .save(consumer);

        CustomShapelessRecipeBuilder.shapeless(RegistryHandler.JETPACK_CREATIVE.get(), 2)
                .requires(Items.COAL)
                .requires(Items.CLAY_BALL)
                .requires(Items.GUNPOWDER)
                .requires(Items.BONE_MEAL)
                .save(consumer);*/

        // Vanilla:
        armorRecipeCombo(consumer, RegistryHandler.JETPACK_VANILLA1_ARMORED.get(), RegistryHandler.JETPACK_VANILLA1.get(), Items.IRON_CHESTPLATE, "vanilla");
        armorRecipeCombo(consumer, RegistryHandler.JETPACK_VANILLA2_ARMORED.get(), RegistryHandler.JETPACK_VANILLA2.get(), Items.GOLDEN_CHESTPLATE, "vanilla");
        armorRecipeCombo(consumer, RegistryHandler.JETPACK_VANILLA3_ARMORED.get(), RegistryHandler.JETPACK_VANILLA3.get(), Items.DIAMOND_CHESTPLATE, "vanilla");
        armorRecipeCombo(consumer, RegistryHandler.JETPACK_VANILLA4_ARMORED.get(), RegistryHandler.JETPACK_VANILLA4.get(), Items.NETHERITE_CHESTPLATE, "vanilla");
        // Thermal:

        // Mekanism:

        // Immersive Engineering:
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(SimplyJetpacks.MODID, path);
    }

    private static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(new ResourceLocation("forge", name));
    }

    private static Ingredient fromJson(String name) {
        JsonObject object = new JsonObject();
        object.addProperty("item", name);
        return Ingredient.fromJson(object);
    }

    private static String savePath(String path) {
        return new ResourceLocation(SimplyJetpacks.MODID, path).toString();
    }

    public void armorRecipeCombo(Consumer<FinishedRecipe> consumer, ItemLike armored, ItemLike base, ItemLike plating, String modid) {
        // armoring
        CustomShapelessRecipeBuilder.shapeless(armored).requires(base).requires(plating).save(consumer, savePath(modid + "/" + armored.asItem().getRegistryName().getPath()));
        // de-armoring
        CustomShapelessRecipeBuilder.shapeless(base).requires(armored).save(consumer, savePath(modid + "/" + base.asItem().getRegistryName().getPath() + "_from_armored"));
    }
}
