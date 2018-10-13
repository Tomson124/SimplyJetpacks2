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
import tonius.simplyjetpacks.setup.ModItems;

public class ItemHelper {
	public static OreDictionaryProxy oreProxy = new OreDictionaryProxy();
	public static ResourceLocation resourceLocation = new ResourceLocation(SimplyJetpacks.MODID);

	private ItemHelper() {
	}

	public static boolean oreNameExists(String oreName) {
		return oreProxy.oreNameExists(oreName);
	}

	public static ItemStack cloneStack(Item item, int stackSize) {
		if (item == null) {
			return null;
		}
		ItemStack stack = new ItemStack(item, stackSize);

		return stack;
	}

	public static ItemStack cloneStack(Block item, int stackSize) {
		if (item == null) {
			return null;
		}
		ItemStack stack = new ItemStack(item, stackSize);

		return stack;
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
		ItemStack retStack = stack.copy();

		return retStack;
	}

	public static ItemStack copyTag(ItemStack container, ItemStack other) {

		if (!other.isEmpty() && other.hasTagCompound()) {
			container.setTagCompound(other.getTagCompound().copy());
		}

		return container;
	}

	/* CREATING OreRecipes */
	public static final IRecipe ShapedRecipe(Block result, Object... recipe) {
		return new ShapedOreRecipe(resourceLocation, result, recipe);
	}

	public static final IRecipe ShapedRecipe(Item result, Object... recipe) {
		return new ShapedOreRecipe(resourceLocation, result, recipe);
	}

	public static final IRecipe ShapedRecipe(String name, ItemStack result, Object... recipe) {
		return new ShapedOreRecipe(new ResourceLocation(SimplyJetpacks.MODID, name), result, recipe);
	}

	public static final IRecipe ShapedRecipe(Block result, int s, Object... recipe) {
		return new ShapedOreRecipe(resourceLocation, new ItemStack(result, s), recipe);
	}

	public static final IRecipe ShapedRecipe(Item result, int s, Object... recipe) {
		return new ShapedOreRecipe(resourceLocation, new ItemStack(result, s), recipe);
	}

	public static final IRecipe ShapedRecipe(ItemStack result, int s, Object... recipe) {
		return new ShapedOreRecipe(resourceLocation, cloneStack(result, s), recipe);
	}

	public static final IRecipe ShapelessRecipe(Block result, Object... recipe) {
		return new ShapelessOreRecipe(resourceLocation, result, recipe);
	}

	public static final IRecipe ShapelessRecipe(Item result, Object... recipe) {
		return new ShapelessOreRecipe(resourceLocation, result, recipe);
	}

	public static final IRecipe ShapelessRecipe(ItemStack result, Object... recipe) {
		return new ShapelessOreRecipe(resourceLocation, result, recipe);
	}

	public static final IRecipe ShapelessRecipe(Block result, int s, Object... recipe) {
		return new ShapelessOreRecipe(resourceLocation, new ItemStack(result, s), recipe);
	}

	public static final IRecipe ShapelessRecipe(Item result, int s, Object... recipe) {
		return new ShapelessOreRecipe(resourceLocation, new ItemStack(result, s), recipe);
	}

	public static final IRecipe ShapelessRecipe(ItemStack result, int s, Object... recipe) {
		return new ShapelessOreRecipe(resourceLocation, cloneStack(result, s), recipe);
	}

	//RECIPE

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

	//END RECIPE

	//SURROUND

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

	//END SURROUND

	public static void addFluxpacks(Fluxpack pack, NonNullList<ItemStack> List) {
		ItemStack stack;
		Item fluxpackItem = ModItems.itemFluxPack;
		ItemStack fluxpackStack = new ItemStack(fluxpackItem, 1, pack.ordinal());
		if (pack.usesFuel) {
			List.add(fluxpackStack);
			NBTHelper.setInt(fluxpackStack, ItemFluxpack.TAG_ENERGY, 0);
		} else {
			stack = new ItemStack(fluxpackItem, 1, pack.ordinal());
			if (fluxpackItem != null) {
				((ItemFluxpack) fluxpackItem).receiveEnergy(stack, ((ItemFluxpack) fluxpackItem).getMaxEnergyStored(stack), false);
			}

			List.add(stack);
		}
	}
}