package tonius.simplyjetpacks.crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import tonius.simplyjetpacks.ServerProxy;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.ItemHelper;
import tonius.simplyjetpacks.util.NBTHelper;

import javax.annotation.Nonnull;
import java.util.List;

public class UpgradingRecipeShapeless {

	private static int j = 0;

	public UpgradingRecipeShapeless(Item result, Object... recipe) {
		//setRegistryName(new ResourceLocation(SimplyJetpacks.MODID, "upgradeRecipeShapeless" + j));
		j++;
	}

	/*@Override
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
				} else if (OreDictionary.containsMatch(false, ServerProxy.oresListParticles, inputStack)) {
					particleType = ParticleType.values()[inputStack.getItemDamage()];
				}

				if (stack.getItem() instanceof ItemJetpack || stack.getItem() instanceof ItemFluxpack) {
					outputStack = ItemHelper.copyTag(outputStack, inputStack);
				}
			}
		}

		if (inputStack.isEmpty()) {
			return ItemStack.EMPTY;
		}

		NBTHelper.setInt(outputStack, "Energy", Math.min(addedEnergy, outputItem.getMaxEnergyStored(outputStack)));

		if (outputItem instanceof ItemJetpack && particleType != null) {
			((ItemJetpack) outputItem).setParticleType(outputStack, particleType);
		}

		return outputStack;
	}

	@Override
	public boolean isDynamic() {

		return true;
	}*/
}
