package tonius.simplyjetpacks.crafting;

import cofh.redstoneflux.api.IEnergyContainerItem;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.CommonProxy;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemFluxpack;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.NBTHelper;

public class UpgradingRecipe extends ShapedOreRecipe {
	private final IEnergyContainerItem resultItem;
	private final ItemStack resultStack;

	private static int j = 0;

	public UpgradingRecipe(Item result, Object... recipe) {
		super(null, result, recipe);
		setRegistryName(SimplyJetpacks.MODID, "upgradeRecipe" + j);
		j++;
		this.resultItem = (IEnergyContainerItem) result;
		this.resultStack = new ItemStack(result);
		resultStack.getEnchantmentTagList();
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
		int addedEnergy = 0;
		ParticleType particleType = null;
		NBTTagCompound tags = null;

		ItemStack slotStack;
		for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
			slotStack = inventoryCrafting.getStackInSlot(i);
			if (slotStack != null && slotStack.getItem() != null) {
				if (slotStack.getItem() instanceof ItemJetpack || slotStack.getItem() instanceof ItemFluxpack) {
					tags = NBTHelper.getTagCompound(slotStack).copy();
				}
				if (slotStack.getItem() instanceof IEnergyContainerItem) {
					addedEnergy += ((IEnergyContainerItem) slotStack.getItem()).getEnergyStored(slotStack);
				} else if (OreDictionary.containsMatch(false, CommonProxy.oresListParticles, slotStack)) {
					particleType = ParticleType.values()[slotStack.getItemDamage()];
				}
			}
		}

		if (tags != null) {
			resultStack.setTagCompound(tags);
		}
		NBTHelper.setInt(resultStack, "Energy", Math.min(addedEnergy, this.resultItem.getMaxEnergyStored(resultStack)));

		if (this.resultItem instanceof ItemJetpack && particleType != null) {
			((ItemJetpack) this.resultItem).setParticleType(resultStack, particleType);
		}

		return resultStack;
	}
}