package stormedpanda.simplyjetpacks.items;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.integration.IntegrationList;

import javax.annotation.Nonnull;

public class MetaItem extends Item {

    public String fromMod;

    public MetaItem(String fromMod) {
        super(new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks));
        this.fromMod = fromMod;
    }

    @Override
    public void fillItemCategory(@Nonnull CreativeModeTab group, @Nonnull NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            if (IntegrationList.integrateVanilla) {
                if (fromMod.equals("vanilla")) {
                    items.add(new ItemStack(this));
                }
            }
            if (IntegrationList.integrateImmersiveEngineering) {
                if (fromMod.equals("ie")) {
                    items.add(new ItemStack(this));
                }
            }
            if (IntegrationList.integrateMekanism) {
                if (fromMod.equals("mek")) {
                    items.add(new ItemStack(this));
                }
            }
            if (IntegrationList.integrateThermalExpansion) {
                if (fromMod.equals("te")) {
                    items.add(new ItemStack(this));
                }
            }
        }
    }
}
