package tonius.simplyjetpacks.crafting;

import com.google.common.collect.Lists;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.capability.IEnergyContainerItem;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.proxy.CommonProxy;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.ItemHelper;
import tonius.simplyjetpacks.util.NBTHelper;

import javax.annotation.Nonnull;
import java.util.List;

public class UpgradingRecipeShapeless extends ShapelessOreRecipe {

    private static int j = 0;

    public UpgradingRecipeShapeless(ItemStack result, Object... recipe) {
        super(null, result, recipe);
        setRegistryName(new ResourceLocation(SimplyJetpacks.MODID, "upgradeRecipeShapeless" + j));
        j++;
    }

    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world) {
        int ingredientCount = 0;
        List<ItemStack> items = Lists.newArrayList();

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                ++ingredientCount;
                items.add(itemstack);
            }
        }
        if (ingredientCount != this.input.size()) {
            return false;
        }
        return RecipeMatcher.findMatches(items, this.input) != null;
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        ItemStack inputStack = ItemStack.EMPTY;
        ItemStack outputStack = output.copy();
        IEnergyContainerItem outputItem = (IEnergyContainerItem) outputStack.getItem();

        int addedEnergy = 0;
        ParticleType particleType = null;

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);

            if (!stack.isEmpty()) {
                inputStack = stack;
                if (inputStack.getItem() instanceof IEnergyContainerItem) {
                    addedEnergy += ((IEnergyContainerItem) inputStack.getItem()).getEnergyStored(inputStack);
                } else if (OreDictionary.containsMatch(false, CommonProxy.oresListParticles, inputStack)) {
                    particleType = ParticleType.values()[inputStack.getItemDamage()];
                }

                if (stack.getItem() instanceof ItemJetpack || stack.getItem() instanceof ItemFluxpack) {
                    ItemHelper.copyTag(outputStack, inputStack);
                }
            }
        }

        if (inputStack.isEmpty()) return ItemStack.EMPTY;
        NBTHelper.setInt(outputStack, "Energy", Math.min(addedEnergy, outputItem.getMaxEnergyStored(outputStack)));
        if (outputItem instanceof ItemJetpack && particleType != null) {
            ((ItemJetpack) outputItem).setParticleType(outputStack, particleType);
        }
        return outputStack;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }
}
