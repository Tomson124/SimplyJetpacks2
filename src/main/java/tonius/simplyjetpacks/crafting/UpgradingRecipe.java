package tonius.simplyjetpacks.crafting;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tonius.simplyjetpacks.CommonProxy;
import tonius.simplyjetpacks.Log;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.item.meta.JetPlate;
import tonius.simplyjetpacks.item.meta.PackBase;
import tonius.simplyjetpacks.item.rewrite.ItemJetpack;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.NBTHelper;

public class UpgradingRecipe extends ShapedOreRecipe
{
	private final IEnergyContainerItem resultItem;
	private final int resultMeta;

	public UpgradingRecipe(ItemStack result, Object... recipe)
	{
		super(result, recipe);
		this.resultItem = (IEnergyContainerItem) result.getItem();
		this.resultMeta = result.getItemDamage();
		result.getEnchantmentTagList();
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting)
	{
		int addedEnergy = 0;
		ParticleType particleType = null;
		NBTTagCompound tags = null;
		boolean enderiumUpgrade = false;

		ItemStack slotStack;
		for(int i = 0; i < inventoryCrafting.getSizeInventory(); i++)
		{
			slotStack = inventoryCrafting.getStackInSlot(i);
			Log.info("Particle: " + OreDictionary.containsMatch(true, CommonProxy.oresListParticles, slotStack));
			if(slotStack != null && slotStack.getItem() != null)
			{
				if(slotStack.getItem() instanceof ItemJetpack)
				{
					tags = (NBTTagCompound) NBTHelper.getDataMap(slotStack).copy();
				}
				if(slotStack.getItem() instanceof IEnergyContainerItem)
				{
					addedEnergy += ((IEnergyContainerItem) slotStack.getItem()).getEnergyStored(slotStack);
				}
				else if(OreDictionary.containsMatch(false, CommonProxy.oresListParticles, slotStack))
				{
					particleType = ParticleType.values()[slotStack.getItemDamage() - 1];
				}
				else if(ModItems.enderiumUpgrade != null && slotStack.getItem() == ModItems.enderiumUpgrade.getItem() && slotStack.getItemDamage() == ModItems.enderiumUpgrade.getItemDamage())
				{
					enderiumUpgrade = true;
				}
			}
		}

		ItemStack result = new ItemStack((Item) this.resultItem, 1, this.resultMeta);

		if(tags != null)
		{
			result.setTagCompound(tags);
		}

		NBTHelper.setInt(result, "Energy", Math.min(addedEnergy, this.resultItem.getMaxEnergyStored(result)));

		if(this.resultItem instanceof ItemJetpack && particleType != null)
		{
			((ItemJetpack) this.resultItem).setParticleType(result, particleType);
		}

		if(enderiumUpgrade && this.resultItem instanceof ItemPack)
		{
			PackBase pack = ((ItemPack) this.resultItem).getPack(result);
			if(pack instanceof JetPlate)
			{
				((JetPlate) pack).setEnderiumUpgrade(result, true);
			}
		}

		return result;
	}
}