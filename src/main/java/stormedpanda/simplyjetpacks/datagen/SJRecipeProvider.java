package stormedpanda.simplyjetpacks.datagen;

import net.minecraft.data.CustomRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.init.RegistryHandler;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class SJRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public SJRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        CustomRecipeBuilder.special(RegistryHandler.JETPACK_SPECIAL_RECIPE.get()).save(consumer, new ResourceLocation(SimplyJetpacks.MODID, "jetpack_special_recipe").toString());
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(SimplyJetpacks.MODID, path);
    }

    private static Tags.IOptionalNamedTag<Item> forgeTag(String name) {
        return ItemTags.createOptional(new ResourceLocation("forge", name));
    }
}
