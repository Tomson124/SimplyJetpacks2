package tomson124.simplyjetpacks.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import tomson124.simplyjetpacks.datagen.SJTags;
import tomson124.simplyjetpacks.handlers.RegistryHandler;
import tomson124.simplyjetpacks.item.JetpackItem;

/**
 * Combines a jetpack with a particle item to re-colour its exhaust, preserving the jetpack otherwise.
 */
public class JetpackCustomRecipe extends CustomRecipe {

    public JetpackCustomRecipe(CraftingBookCategory category) {
        super(category);
    }

    /** Returns the single jetpack/particle pair in the grid, or null if the input is not a valid pairing. */
    private static ItemStack[] findPair(CraftingInput input) {
        ItemStack jetpack = ItemStack.EMPTY;
        ItemStack particle = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); ++i) {
            ItemStack currentStack = input.getItem(i);
            if (currentStack.isEmpty()) {
                continue;
            }
            if (currentStack.getItem() instanceof JetpackItem) {
                if (!jetpack.isEmpty()) {
                    return null;
                }
                jetpack = currentStack;
            } else if (currentStack.is(SJTags.PARTICLES)) {
                if (!particle.isEmpty()) {
                    return null;
                }
                particle = currentStack;
            } else {
                // Any other item in the grid means this isn't our recipe.
                return null;
            }
        }
        return !jetpack.isEmpty() && !particle.isEmpty() ? new ItemStack[]{jetpack, particle} : null;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return findPair(input) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack[] pair = findPair(input);
        if (pair == null) {
            return ItemStack.EMPTY;
        }
        return JetpackItem.setParticleId(pair[0].copy(), pair[1]);
    }

    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return x * y >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegistryHandler.JETPACK_CUSTOM_RECIPE.get();
    }
}
