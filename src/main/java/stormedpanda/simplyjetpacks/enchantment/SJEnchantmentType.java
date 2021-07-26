package stormedpanda.simplyjetpacks.enchantment;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import stormedpanda.simplyjetpacks.item.JetpackItem;

import java.util.function.Predicate;

public class SJEnchantmentType {

    public static final EnchantmentType JETPACK = addEnchantment("jetpack", JetpackItem.class::isInstance);

    private static EnchantmentType addEnchantment(String name, Predicate<Item> condition) {
        return EnchantmentType.create(name, condition);
    }
}