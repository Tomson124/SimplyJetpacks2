package tomson124.simplyjetpacks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.handlers.RegistryHandler;

import java.util.concurrent.CompletableFuture;

public class SJRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public SJRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        //SpecialRecipeBuilder.special(SimpleCraftingRecipeSerializer(SimpleCraftingRecipeSerializer<?>) RegistryHandler.JETPACK_CUSTOM_RECIPE.get()).save(output, savePath("jetpack_custom_recipe"));

        // Simply Jetpacks:
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegistryHandler.LEATHER_STRAP.get())
                .pattern("LIL")
                .pattern("LIL")
                .define('I', forgeTag("ingots/iron"))
                .define('L', forgeTag("leather"))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegistryHandler.PILOT_GOGGLES_GOLD.get())
                .pattern(" S ")
                .pattern("GIG")
                .define('S', RegistryHandler.LEATHER_STRAP.get())
                .define('G', forgeTag("glass_panes"))
                .define('I', forgeTag("ingots/gold"))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegistryHandler.PILOT_GOGGLES_IRON.get())
                .pattern(" S ")
                .pattern("GIG")
                .define('S', RegistryHandler.LEATHER_STRAP.get())
                .define('G', forgeTag("glass_panes"))
                .define('I', forgeTag("ingots/iron"))
                .save(output);

        CustomShapelessRecipeBuilder.shapeless(RegistryHandler.PARTICLE_BLEND.get(), 2)
                .requires(Items.COAL)
                .requires(Items.CLAY_BALL)
                .requires(Items.GUNPOWDER)
                .requires(Items.BONE_MEAL)
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RegistryHandler.PARTICLE_NONE.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.GLASS)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RegistryHandler.PARTICLE_FLAME.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.TORCH)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RegistryHandler.PARTICLE_SMOKE.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.COAL)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RegistryHandler.PARTICLE_RAINBOW.get())
                .pattern(" R ")
                .pattern(" P ")
                .pattern("G B")
                .define('R', forgeTag("dyes/red"))
                .define('G', forgeTag("dyes/green"))
                .define('B', forgeTag("dyes/blue"))
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RegistryHandler.PARTICLE_SOUL.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.SOUL_TORCH)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, RegistryHandler.PARTICLE_SNOW.get())
                .pattern(" T ")
                .pattern("TPT")
                .pattern(" T ")
                .define('T', Items.SNOWBALL)
                .define('P', RegistryHandler.PARTICLE_BLEND.get())
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, RegistryHandler.JETPACK_POTATO.get())
                .pattern("S S")
                .pattern("GPG")
                .pattern("R R")
                .define('S', forgeTag("string"))
                .define('G', forgeTag("nuggets/gold"))
                .define('P', forgeTag("crops/potato"))
                .define('R', forgeTag("dusts/redstone"))
                .save(output);

        // Testing:
        /*CustomShapedRecipeBuilder.shaped(RegistryHandler.JETPACK_CREATIVE_ARMORED.get())
                .pattern("S S")
                .pattern("GPG")
                .pattern("R R")
                .define('S', forgeTag("string"))
                .define('G', forgeTag("nuggets/gold"))
                .define('P', forgeTag("crops/potato"))
                .define('R', forgeTag("dusts/redstone"))
                .save(output);

        CustomShapelessRecipeBuilder.shapeless(RegistryHandler.JETPACK_CREATIVE.get(), 2)
                .requires(Items.COAL)
                .requires(Items.CLAY_BALL)
                .requires(Items.GUNPOWDER)
                .requires(Items.BONE_MEAL)
                .save(output);*/

        // Vanilla:
        armorRecipeCombo(output, RegistryHandler.JETPACK_VANILLA1_ARMORED.get(), RegistryHandler.JETPACK_VANILLA1.get(), Items.IRON_CHESTPLATE, "vanilla");
        armorRecipeCombo(output, RegistryHandler.JETPACK_VANILLA2_ARMORED.get(), RegistryHandler.JETPACK_VANILLA2.get(), Items.GOLDEN_CHESTPLATE, "vanilla");
        armorRecipeCombo(output, RegistryHandler.JETPACK_VANILLA3_ARMORED.get(), RegistryHandler.JETPACK_VANILLA3.get(), Items.DIAMOND_CHESTPLATE, "vanilla");
        armorRecipeCombo(output, RegistryHandler.JETPACK_VANILLA4_ARMORED.get(), RegistryHandler.JETPACK_VANILLA4.get(), Items.NETHERITE_CHESTPLATE, "vanilla");
        // Thermal:

        // Mekanism:

        // Immersive Engineering:
    }

    private static ResourceLocation modId(String path) {
        return ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, path);
    }

    private static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
    }
    /*
    private static Ingredient fromJson(String name) {
        JsonObject object = new JsonObject();
        object.addProperty("item", name);
        return Ingredient.fromJson(object);
    }*/

    private static String savePath(String path) {
        return ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, path).toString();
    }

    public void armorRecipeCombo(RecipeOutput output, ItemLike armored, ItemLike base, ItemLike plating, String modid) {
        // armoring
        // TODO: test this (Registry.ITEM.getKey(item.asItem()))
//        CustomShapelessRecipeBuilder.shapeless(armored).requires(base).requires(plating).save(output, savePath(modid + "/" + armored.asItem().getRegistryName().getPath()));
        CustomShapelessRecipeBuilder.shapeless(armored).requires(base).requires(plating).save(output, savePath(modid + "/" + BuiltInRegistries.ITEM.getKey(armored.asItem()).getPath()));
        // de-armoring
        CustomShapelessRecipeBuilder.shapeless(base).requires(armored).save(output, savePath(modid + "/" + BuiltInRegistries.ITEM.getKey(base.asItem()).getPath() + "_from_armored"));
    }

}
