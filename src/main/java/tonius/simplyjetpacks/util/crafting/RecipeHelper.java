package tonius.simplyjetpacks.util.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import tonius.simplyjetpacks.RegistryHandler;
import tonius.simplyjetpacks.SimplyJetpacks;
import java.util.List;
import  tonius.simplyjetpacks.util.reference.Reference;

import javax.annotation.Nonnull;

public final class RecipeHelper {

	private static int j = 0;
	public static final List<IRecipe> RECIPE_LIST = RegistryHandler.RECIPES_TO_REGISTER;

	/*public static IRecipe addShapedOreRecipe(Item result, Object... recipe) {
		return new ShapedOreRecipe(null, result, recipe).setRegistryName(result.getRegistryName());
	}*/

	/*
	 * This adds the recipe to the list of crafting recipes.  Since who cares about names, it adds it as recipesX, where X is the current recipe you are adding.
	 */
	public static void addRecipe(int j, IRecipe rec) {
		addRecipe("recipes" + j, rec);
	}

	/*
	 * This adds the recipe to the list of crafting recipes.  Cares about names.
	 */
	public static void addRecipe(String name, IRecipe rec) {
		Item i = rec.getRecipeOutput().getItem();
		RECIPE_LIST.add(rec);
		RecipeHandler.lastRecipe = rec;
	}



	/*
	 * This adds a shaped recipe to the list of crafting recipes, using the forge format.

	public static void addOldShaped(Item output, Object... input) {
		ShapedPrimer primer = CraftingHelper.parseShaped(input);
		addRecipe(j++, new ShapedRecipe(new ResourceLocation(Reference.MODID, "recipes" + j).toString(), primer.width,
				primer.height, primer.input, new ItemStack(output)));
	}

	/*
	 * This adds a shaped recipe to the list of crafting recipes, using the forge format.

	public static void addOldShaped(ItemStack output, Object... input) {
		ShapedPrimer primer = CraftingHelper.parseShaped(input);
		addRecipe(j++, new ShapedRecipe(new ResourceLocation(Reference.MODID, "recipes" + j).toString(), primer.width,
				primer.height, primer.input, output));
	}

	/*
	 * This adds a shaped recipe to the list of crafting recipes, using the forge format, with a custom group.

	public static void addOldShaped(String group, ItemStack output, Object... input) {
		ShapedPrimer primer = CraftingHelper.parseShaped(input);
		addRecipe(j++, new ShapedRecipe(new ResourceLocation(Reference.Reference.MODID, group).toString(), primer.width, primer.height,
				primer.input, output));
	}

	/*
	* This adds a shaped recipe to the list of crafting recipes, using the forge format, with a custom group.

	public static void addOldShaped(String name, String group, ItemStack output, Object... input) {
		ShapedPrimer primer = CraftingHelper.parseShaped(input);
		addRecipe(j++, new ShapedRecipe(new ResourceLocation(Reference.MODID, group).toString(), primer.width, primer.height,
				primer.input, output).setRegistryName(Reference.MODID, name));
	}

	/*
	 * This adds a shapeless recipe to the list of crafting recipes, using the forge format.

	public static void addOldShapeless(ItemStack output, Object... input) {
		addRecipe(j++, new ShapelessRecipe(new ResourceLocation(Reference.MODID, "recipes" + j).toString(), output,
				createInput(input)));
	}

	/*
	 * This adds a shapeless recipe to the list of crafting recipes, using the forge format, with a custom group.

	public static void addOldShapeless(String group, ItemStack output, Object... input) {
		addRecipe(j++, new ShapelessRecipe(new ResourceLocation(Reference.MODID, group).toString(), output, createInput(input)));
	}

	public static void addOldShapeless(String name, String group, ItemStack output, Object... input) {
		addRecipe(j++, new ShapelessRecipe(new ResourceLocation(Reference.MODID, group).toString(), output, createInput(input))
				.setRegistryName(Reference.MODID, name));
	}

	/*
	 * Adds a shapeless recipe with X output using an array of inputs. Use Strings for OreDictionary support. This array is not ordered.

	public static void addShapeless(ItemStack output, Object... inputs) {
		addRecipe(j++, new ShapelessRecipe(Reference.MODID + ":" + j, output, createInput(inputs)));
	}

	public static void addShapeless(Item output, Object... inputs) {
		addShapeless(new ItemStack(output), inputs);
	}

	public static void addShapeless(Block output, Object... inputs) {
		addShapeless(new ItemStack(output), inputs);
	}

	/*
	 * Adds a shapeless recipe with X output using an array of inputs. Use Strings for OreDictionary support. This array is not ordered.  This has a custom group.

	public static void addShapeless(String group, ItemStack output, Object... inputs) {
		addRecipe(j++, new ShapelessRecipe(output.getItem().getRegistryName(), group, output, createInput(inputs)));
	}

	public static void addShapeless(String group, Item output, Object... inputs) {
		addShapeless(group, new ItemStack(output), inputs);
	}

	public static void addShapeless(String group, Block output, Object... inputs) {
		addShapeless(group, new ItemStack(output), inputs);
	}

	/*
	 * Adds a shapeless recipe with X output on a crafting grid that is W x H, using an array of inputs.  Use null for nothing, use Strings for OreDictionary support, this array must have a length of width * height.
	 * This array is ordered, and items must follow from left to right, top to bottom of the crafting grid.

	public static void addShaped(ItemStack output, int width, int height, Object... input) {
		addRecipe(j++, genShaped(output, width, height, input));
	}

	public static void addShaped(Item output, int width, int height, Object... input) {
		addShaped(new ItemStack(output), width, height, input);
	}

	public static void addShaped(Block output, int width, int height, Object... input) {
		addShaped(new ItemStack(output), width, height, input);
	}

	/*
	 * Adds a shapeless recipe with X output on a crafting grid that is W x H, using an array of inputs.  Use null for nothing, use Strings for OreDictionary support, this array must have a length of width * height.
	 * This array is ordered, and items must follow from left to right, top to bottom of the crafting grid. This has a custom group.

	public static void addShaped(String group, ItemStack output, int width, int height, Object... input) {
		addRecipe(j++, genShaped(Reference.MODID + ":" + group, output, width, height, input));
	}

	public static void addShaped(String group, Item output, int width, int height, Object... input) {
		addShaped(group, new ItemStack(output), width, height, input);
	}

	public static void addShaped(String group, Block output, int width, int height, Object... input) {
		addShaped(group, new ItemStack(output), width, height, input);
	}

	public static ShapedRecipe genShaped(ItemStack output, int l, int w, Object[] input) {
		if (input[0] instanceof Object[]) {
			input = (Object[]) input[0];
		}
		if (l * w != input.length) {
			throw new UnsupportedOperationException(
					"Attempted to add invalid shaped recipe.  Complain to the author of " + Reference.MOD_NAME);
		}
		NonNullList<Ingredient> inputL = NonNullList.create();
		for (int i = 0; i < input.length; i++) {
			Object k = input[i];
			if (k instanceof String) {
				inputL.add(i, new OreIngredient((String) k));
			} else if (k instanceof ItemStack && !((ItemStack) k).isEmpty()) {
				inputL.add(i, Ingredient.fromStacks((ItemStack) k));
			} else if (k instanceof Item) {
				inputL.add(i, Ingredient.fromStacks(new ItemStack((Item) k)));
			} else if (k instanceof Block) {
				inputL.add(i, Ingredient.fromStacks(new ItemStack((Block) k)));
			} else {
				inputL.add(i, Ingredient.EMPTY);
			}
		}

		return new ShapedRecipe(Reference.MODID + ":" + j, l, w, inputL, output);
	}

	public static ShapedRecipe genShaped(String group, ItemStack output, int l, int w, Object[] input) {
		if (input[0] instanceof List) {
			input = ((List<?>) input[0]).toArray();
		} else if (input[0] instanceof Object[]) {
			input = (Object[]) input[0];
		}
		if (l * w != input.length) {
			throw new UnsupportedOperationException(
					"Attempted to add invalid shaped recipe.  Complain to the author of " + MODNAME);
		}
		NonNullList<Ingredient> inputL = NonNullList.create();
		for (int i = 0; i < input.length; i++) {
			Object k = input[i];
			if (k instanceof String) {
				inputL.add(i, new OreIngredient((String) k));
			} else if (k instanceof ItemStack && !((ItemStack) k).isEmpty()) {
				inputL.add(i, Ingredient.fromStacks((ItemStack) k));
			} else if (k instanceof Item) {
				inputL.add(i, Ingredient.fromStacks(new ItemStack((Item) k)));
			} else if (k instanceof Block) {
				inputL.add(i, Ingredient.fromStacks(new ItemStack((Block) k)));
			} else {
				inputL.add(i, Ingredient.EMPTY);
			}
		}

		return new ShapedRecipe(group, l, w, inputL, output);
	}

	public static NonNullList<Ingredient> createInput(Object[] input) {
		if (input[0] instanceof List) {
			input = ((List<?>) input[0]).toArray();
		} else if (input[0] instanceof Object[]) {
			input = (Object[]) input[0];
		}
		NonNullList<Ingredient> inputL = NonNullList.create();
		for (int i = 0; i < input.length; i++) {
			Object k = input[i];
			if (k instanceof String) {
				inputL.add(i, new OreIngredient((String) k));
			} else if (k instanceof ItemStack) {
				inputL.add(i, Ingredient.fromStacks((ItemStack) k));
			} else if (k instanceof Item) {
				inputL.add(i, Ingredient.fromStacks(new ItemStack((Item) k)));
			} else if (k instanceof Block) {
				inputL.add(i, Ingredient.fromStacks(new ItemStack((Block) k)));
			} else {
				throw new UnsupportedOperationException(
						"Attempted to add invalid shapeless recipe.  Complain to the author of " + MODNAME);
			}
		}
		return inputL;
	} */
}
