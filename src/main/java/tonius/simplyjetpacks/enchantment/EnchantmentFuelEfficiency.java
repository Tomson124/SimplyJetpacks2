package tonius.simplyjetpacks.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.setup.ModEnchantments;

import javax.annotation.Nonnull;

public class EnchantmentFuelEfficiency extends Enchantment {

    public EnchantmentFuelEfficiency() {
        super(Rarity.UNCOMMON, ModEnchantments.enchantType, EntityEquipmentSlot.values());
    }

    @Override
    public int getMinEnchantability(int level) {
        return 8 + (level - 1) * 8;
    }

    @Override
    public int getMaxEnchantability(int level) {
        return super.getMinEnchantability(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Nonnull
    @Override
    public String getName() {
        return "enchantment." + SimplyJetpacks.MODID + ".fuel_efficiency";
    }

    @Override
    public boolean canApply(ItemStack stack) {
        ModEnchantments.canEnchantItem(stack.getItem());
        return stack.getItem() instanceof ItemJetpack;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}