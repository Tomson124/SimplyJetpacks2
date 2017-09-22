package tonius.simplyjetpacks.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.rewrite.ItemJetpack;
import tonius.simplyjetpacks.setup.ModEnchantments;

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
		return stack.getItem() instanceof ItemJetpack;
	}
}