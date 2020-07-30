package tomson124.simplyjetpacks.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import tomson124.simplyjetpacks.item.ItemJetpack;
import tomson124.simplyjetpacks.setup.ModEnchantments;
import tomson124.simplyjetpacks.SimplyJetpacks;

public class EnchantmentFuelEfficiency extends Enchantment
{
	public EnchantmentFuelEfficiency()
	{
		super(Rarity.UNCOMMON, ModEnchantments.enchantType, EntityEquipmentSlot.values());
	}

	@Override
	public int getMinEnchantability(int level)
	{
		return 8 + (level - 1) * 8;
	}

	@Override
	public int getMaxEnchantability(int level)
	{
		return super.getMinEnchantability(level) + 50;
	}

	@Override
	public int getMaxLevel()
	{
		return 4;
	}

	@Override
	public String getName()
	{
		return SimplyJetpacks.PREFIX + "enchantment.fuelEfficiency";
	}

	@Override
	public boolean canApply(ItemStack stack)
	{
		ModEnchantments.canEnchantItem(stack.getItem());
		return stack.getItem() instanceof ItemJetpack;
	}

}