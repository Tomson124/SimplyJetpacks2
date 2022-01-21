package com.simplyjetpacks.crafting;

import com.simplyjetpacks.datagen.SJTags;
import com.simplyjetpacks.handlers.RegistryHandler;
import com.simplyjetpacks.item.JetpackItem;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

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
    public IRecipeSerializer<?> getSerializer() {
        return RegistryHandler.JETPACK_SPECIAL_RECIPE.get();
    }
}
