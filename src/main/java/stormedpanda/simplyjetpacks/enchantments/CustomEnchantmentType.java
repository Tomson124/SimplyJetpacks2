package stormedpanda.simplyjetpacks.enchantments;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import stormedpanda.simplyjetpacks.items.JetpackItem;

import java.util.function.Predicate;

public class CustomEnchantmentType {

    public static final EnchantmentType JETPACK = addEnchantment("jetpack", JetpackItem.class::isInstance);

    private static EnchantmentType addEnchantment(String name, Predicate<Item> condition) {
        return EnchantmentType.create(name, condition);
    }
}