package stormedpanda.simplyjetpacks.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;
import stormedpanda.simplyjetpacks.item.JetpackItem;

public class EnchantmentFuelEfficiency extends Enchantment {

    public EnchantmentFuelEfficiency() {
        super(Rarity.RARE, RegistryHandler.JETPACK_ENCHANTMENT_TYPE, new EquipmentSlotType[]{ EquipmentSlotType.CHEST });
    }

    @Override
    public int getMinCost(int level) {
        return 8 + (level - 1) * 8;
    }

    @Override
    public int getMaxCost(int level) {
        return super.getMinCost(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof JetpackItem && super.canEnchant(stack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() instanceof JetpackItem && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}
