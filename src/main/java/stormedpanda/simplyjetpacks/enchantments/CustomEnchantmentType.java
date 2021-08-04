package stormedpanda.simplyjetpacks.enchantments;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import stormedpanda.simplyjetpacks.items.JetpackItem;

import java.util.function.Predicate;

public class CustomEnchantmentType {

    public static final EnchantmentCategory JETPACK = addEnchantment("jetpack", JetpackItem.class::isInstance);

    private static EnchantmentCategory addEnchantment(String name, Predicate<Item> condition) {
        return EnchantmentCategory.create(name, condition);
    }
}