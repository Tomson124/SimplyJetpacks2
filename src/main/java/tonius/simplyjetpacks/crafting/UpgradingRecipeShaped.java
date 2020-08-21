package tonius.simplyjetpacks.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.capability.IEnergyContainerItem;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.proxy.CommonProxy;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.NBTHelper;

import javax.annotation.Nonnull;

public class UpgradingRecipeShaped extends ShapedOreRecipe {
	private final IEnergyContainerItem resultItem;
	private final int resultMeta;

	private static int j = 0;

	public UpgradingRecipeShaped(ItemStack result, Object... recipe) {
		super(null, result, recipe);
		setRegistryName(SimplyJetpacks.MODID, "upgradeRecipeShaped" + j);
		j++;
		this.resultItem = (IEnergyContainerItem) result.getItem();
		this.resultMeta = result.getItemDamage();
		result.getEnchantmentTagList();
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
		int addedEnergy = 0;
		ParticleType particleType = null;
		NBTTagCompound tags = null;

		ItemStack slotStack;
		for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
			slotStack = inventoryCrafting.getStackInSlot(i);
			slotStack.getItem();
			if (slotStack.getItem() instanceof ItemJetpack || slotStack.getItem() instanceof ItemFluxpack) {
				tags = NBTHelper.getTagCompound(slotStack).copy();
			}
			if (slotStack.getItem() instanceof IEnergyContainerItem) {
				addedEnergy += ((IEnergyContainerItem) slotStack.getItem()).getEnergyStored(slotStack);
			} else if (OreDictionary.containsMatch(false, CommonProxy.oresListParticles, slotStack)) {
				particleType = ParticleType.values()[slotStack.getItemDamage()];
			}
		}
		ItemStack result = new ItemStack((Item) this.resultItem, 1, this.resultMeta);
		if (tags != null) {
			result.setTagCompound(tags);
		}
		NBTHelper.setInt(result, "Energy", Math.min(addedEnergy, this.resultItem.getMaxEnergyStored(result)));
		if (this.resultItem instanceof ItemJetpack && particleType != null) {
			((ItemJetpack) this.resultItem).setParticleType(result, particleType);
		}
		return result;
	}
}