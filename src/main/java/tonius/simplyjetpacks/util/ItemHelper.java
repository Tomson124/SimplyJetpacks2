package tonius.simplyjetpacks.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.Fluxpack;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.item.Jetpack;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.ParticleType;

public class ItemHelper {

    public static OreDictionaryProxy oreProxy = new OreDictionaryProxy();
    public static ResourceLocation resourceLocation = new ResourceLocation(SimplyJetpacks.MODID);

    public static boolean oreNameExists(String oreName) {
        return oreProxy.oreNameExists(oreName);
    }

    public static ItemStack cloneStack(Item item, int stackSize) {
        if (item == null) {
            return null;
        }
        return new ItemStack(item, stackSize);
    }

    public static ItemStack cloneStack(Block item, int stackSize) {
        if (item == null) {
            return null;
        }
        return new ItemStack(item, stackSize);
    }

    public static ItemStack cloneStack(ItemStack stack, int stackSize) {
        if (stack == null) {
            return null;
        }
        ItemStack retStack = stack.copy();
        retStack.setCount(stackSize);
        return retStack;
    }

    public static ItemStack cloneStack(ItemStack stack) {
        if (stack == null) {
            return null;
        }
        return stack.copy();
    }

    public static ItemStack copyTag(ItemStack container, ItemStack other) {
        if (!other.isEmpty() && other.hasTagCompound()) {
            container.setTagCompound(other.getTagCompound().copy());
        }
        return container;
    }

    // CREATING OreRecipes
    public static IRecipe ShapedRecipe(Block result, Object... recipe) {
        return new ShapedOreRecipe(resourceLocation, result, recipe);
    }

    public static IRecipe ShapedRecipe(Item result, Object... recipe) {
        return new ShapedOreRecipe(resourceLocation, result, recipe);
    }

    public static IRecipe ShapedRecipe(String name, ItemStack result, Object... recipe) {
        return new ShapedOreRecipe(new ResourceLocation(SimplyJetpacks.MODID, name), result, recipe);
    }

    public static IRecipe ShapedRecipe(Block result, int s, Object... recipe) {
        return new ShapedOreRecipe(resourceLocation, new ItemStack(result, s), recipe);
    }

    public static IRecipe ShapedRecipe(Item result, int s, Object... recipe) {
        return new ShapedOreRecipe(resourceLocation, new ItemStack(result, s), recipe);
    }

    public static IRecipe ShapedRecipe(ItemStack result, int s, Object... recipe) {
        return new ShapedOreRecipe(resourceLocation, cloneStack(result, s), recipe);
    }

    public static IRecipe ShapelessRecipe(Block result, Object... recipe) {
        return new ShapelessOreRecipe(resourceLocation, result, recipe);
    }

    public static IRecipe ShapelessRecipe(Item result, Object... recipe) {
        return new ShapelessOreRecipe(resourceLocation, result, recipe);
    }

    public static IRecipe ShapelessRecipe(ItemStack result, Object... recipe) {
        return new ShapelessOreRecipe(resourceLocation, result, recipe);
    }

    public static IRecipe ShapelessRecipe(Block result, int s, Object... recipe) {
        return new ShapelessOreRecipe(resourceLocation, new ItemStack(result, s), recipe);
    }

    public static IRecipe ShapelessRecipe(Item result, int s, Object... recipe) {
        return new ShapelessOreRecipe(resourceLocation, new ItemStack(result, s), recipe);
    }

    public static IRecipe ShapelessRecipe(ItemStack result, int s, Object... recipe) {
        return new ShapelessOreRecipe(resourceLocation, cloneStack(result, s), recipe);
    }

    // RECIPE

    public static void addShapedRecipe(String name, ItemStack out, Object... recipe) {
        ForgeRegistries.RECIPES.register(ShapedRecipe(name, out, recipe));
    }

    public static void addShapedRecipe(String name, Item out, Object... recipe) {
        addShapedRecipe(name, new ItemStack(out), recipe);
    }

    public static void addShapedRecipe(String name, Block out, Object... recipe) {
        addShapedRecipe(name, new ItemStack(out), recipe);
    }

    public static void addShapelessRecipe(ItemStack out, Object... recipe) {
        ForgeRegistries.RECIPES.register(ShapelessRecipe(out, recipe));
    }

    public static void addShapelessRecipe(Item out, Object... recipe) {
        addShapelessRecipe(new ItemStack(out), recipe);
    }

    public static void addShapelessRecipe(Block out, Object... recipe) {
        addShapelessRecipe(new ItemStack(out), recipe);
    }

    public static void addShapedOreRecipe(String name, ItemStack out, Object... recipe) {
        ForgeRegistries.RECIPES.register(ShapedRecipe(name, out, recipe));
    }

    public static void addShapedOreRecipe(Item out, Object... recipe) {
        ForgeRegistries.RECIPES.register(ShapedRecipe(out, recipe));
    }

    public static void addShapedOreRecipe(Block out, Object... recipe) {
        ForgeRegistries.RECIPES.register(ShapedRecipe(out, recipe));
    }

    public static void addShapelessOreRecipe(String name, ItemStack out, Object... recipe) {
        ForgeRegistries.RECIPES.register(ShapedRecipe(name, out, recipe));
    }

    public static void addShapelessOreRecipe(Item out, Object... recipe) {
        ForgeRegistries.RECIPES.register(ShapedRecipe(out, recipe));
    }

    public static void addShapelessOreRecipe(Block out, Object... recipe) {
        ForgeRegistries.RECIPES.register(ShapedRecipe(out, recipe));
    }

    // END RECIPE

    // SURROUND

    public static boolean addSurroundRecipe(ItemStack out, ItemStack one, ItemStack eight) {
        if (out == null | one == null | eight == null) {
            return false;
        }
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(resourceLocation, cloneStack(out), "XXX", "XIX", "XXX", 'X', cloneStack(eight, 1), 'I', cloneStack(one, 1)));
        return true;
    }

    public static boolean addSurroundRecipe(ItemStack out, String one, ItemStack eight) {
        if (out == null | eight == null || !oreNameExists(one)) {
            return false;
        }
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(resourceLocation, out, "XXX", "XIX", "XXX", 'X', eight, 'I', one));
        return true;
    }

    public static boolean addSurroundRecipe(ItemStack out, ItemStack one, String eight) {
        if (out == null | one == null || !oreNameExists(eight)) {
            return false;
        }
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(resourceLocation, out, "XXX", "XIX", "XXX", 'X', eight, 'I', one));
        return true;
    }

    public static boolean addSurroundRecipe(ItemStack out, String one, String eight) {
        if (out == null || !oreNameExists(one) || !oreNameExists(eight)) {
            return false;
        }
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(resourceLocation, out, "XXX", "XIX", "XXX", 'X', eight, 'I', one));
        return true;
    }

    // END SURROUND

    public static void addJetpacks(Jetpack pack, NonNullList<ItemStack> List, boolean full) {
        ItemJetpack jetpackItem = ModItems.itemJetpack;
        ItemStack jetpackStack = new ItemStack(jetpackItem, 1, pack.ordinal());
        if (pack.usesEnergy) {
            NBTHelper.setInt(jetpackStack, Constants.TAG_ENERGY, 0);
        } else {
            jetpackItem.addEnergy(jetpackStack, jetpackItem.getMaxEnergyStored(jetpackStack), false);
        }
        if (jetpackItem.isCreative(jetpackStack)) {
            jetpackItem.setParticleType(jetpackStack, ParticleType.RAINBOW);
        } else {
            jetpackItem.setParticleType(jetpackStack, ParticleType.FLAME);
        }
        List.add(jetpackStack);

        if (full) {
            ItemStack jetpackStackFull = JetpackUtil.setDefaultEnergyTag(jetpackStack, pack.getEnergyCapacity());
            //NBTHelper.setInt(jetpackStackFull, Constants.TAG_ENERGY, pack.getEnergyCapacity());
            List.add((jetpackStackFull));
        }
    }

    public static void addFluxpacks(Fluxpack pack, NonNullList<ItemStack> List, boolean full) {
        ItemStack stack;
        ItemFluxpack fluxpackItem = ModItems.itemFluxpack;
        ItemStack fluxpackStack = new ItemStack(fluxpackItem, 1, pack.ordinal());
        if (pack.usesEnergy) {
            List.add(fluxpackStack);
            NBTHelper.setInt(fluxpackStack, Constants.TAG_ENERGY, 0);
        } else {
            stack = new ItemStack(fluxpackItem, 1, pack.ordinal());
            fluxpackItem.addEnergy(stack, fluxpackItem.getMaxEnergyStored(stack), false);
            List.add(stack);
        }
    }
}