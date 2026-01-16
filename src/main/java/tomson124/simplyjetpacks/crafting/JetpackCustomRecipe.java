package tomson124.simplyjetpacks.crafting;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import tomson124.simplyjetpacks.datagen.SJTags;
import tomson124.simplyjetpacks.handlers.RegistryHandler;
import tomson124.simplyjetpacks.item.JetpackItem;

public class JetpackCustomRecipe extends CustomRecipe {

    public JetpackCustomRecipe(ResourceLocation recipeId) {
        super(recipeId, CraftingBookCategory.MISC);
    }

    @Override
    public boolean matches(CraftingContainer inventory, Level world) {
        ItemStack jetpack = ItemStack.EMPTY;
        ItemStack particle = ItemStack.EMPTY;
        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack currentStack = inventory.getItem(i);
            if (!currentStack.isEmpty()) {
                Item item = currentStack.getItem();
                if (item instanceof JetpackItem) {
                    if (!jetpack.isEmpty()) {
                        return false;
                    }
                    jetpack = currentStack.copy();
                }
                if (BuiltInRegistries.ITEM.tags().getTag(SJTags.PARTICLES).contains(item)) {
                    if (!particle.isEmpty()) {
                        return false;
                    }
                    particle = currentStack.copy();
                }
            }
        }
        return !jetpack.isEmpty() && !particle.isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingContainer inventory, RegistryAccess registryAccess) {
        ItemStack jetpack = ItemStack.EMPTY;
        ItemStack particle = ItemStack.EMPTY;
        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack currentStack = inventory.getItem(i);
            if (!currentStack.isEmpty()) {
                Item item = currentStack.getItem();
                if (item instanceof JetpackItem) {
                    if (!jetpack.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                    jetpack = currentStack.copy();
                }
                if (ForgeRegistries.ITEMS.tags().getTag(SJTags.PARTICLES).contains(item)) {
                    if (!particle.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                    particle = currentStack.copy();
                }
            }
        }
        return !jetpack.isEmpty() && !particle.isEmpty() ? JetpackItem.setParticleId(jetpack, particle) : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return x * y >= 4;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegistryHandler.JETPACK_CUSTOM_RECIPE.get();
    }
}
