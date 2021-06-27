package stormedpanda.simplyjetpacks.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import stormedpanda.simplyjetpacks.item.JetpackItem;

import javax.annotation.Nonnull;

public class EnchantmentFuelEfficiency extends Enchantment {

    public EnchantmentFuelEfficiency() {
        super(Rarity.RARE, CustomEnchantmentType.JETPACK, new EquipmentSlotType[]{ EquipmentSlotType.CHEST });
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

    @Nonnull
    @Override
    public ITextComponent getFullname(int id) {
        //return "enchantment.simplyjetpacks.fuelEfficiency";
        return super.getFullname(id);
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
