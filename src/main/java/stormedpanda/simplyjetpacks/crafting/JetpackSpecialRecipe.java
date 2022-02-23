package stormedpanda.simplyjetpacks.crafting;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import stormedpanda.simplyjetpacks.datagen.SJTags;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;
import stormedpanda.simplyjetpacks.item.JetpackItem;

public class JetpackSpecialRecipe extends SpecialRecipe {

    public JetpackSpecialRecipe(ResourceLocation recipeId) {
        super(recipeId);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
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
                if (item.getItem().getTags().contains(SJTags.PARTICLES.getName())) {
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
    public ItemStack assemble(CraftingInventory inventory) {
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
                if (item.getItem().getTags().contains(SJTags.PARTICLES.getName())) {
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
        return RegistryHandler.JETPACK_SPECIAL_RECIPE.get();
    }
}
