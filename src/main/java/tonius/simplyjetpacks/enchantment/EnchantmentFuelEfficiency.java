package tonius.simplyjetpacks.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemJetpack;
import tonius.simplyjetpacks.setup.ModEnchantments;

import javax.annotation.Nonnull;

public class EnchantmentFuelEfficiency extends Enchantment {

    public EnchantmentFuelEfficiency() {
        super(Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_CHEST, new EntityEquipmentSlot[] { EntityEquipmentSlot.CHEST });
        this.setName("fuel_efficiency");
        this.setRegistryName(new ResourceLocation(SimplyJetpacks.RESOURCE_PREFIX + "fuel_efficiency"));
        ModEnchantments.ENCHANTMENTS.add(this);
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
        return super.canApply(stack) && stack.getItem() instanceof ItemJetpack;
    }
}
