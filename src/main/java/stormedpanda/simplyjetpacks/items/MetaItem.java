package stormedpanda.simplyjetpacks.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.integration.IntegrationList;

import javax.annotation.Nonnull;

public class MetaItem extends Item {

    public String fromMod;

    public MetaItem(String fromMod) {
        super(new Properties().group(SimplyJetpacks.tabSimplyJetpacks));
        this.fromMod = fromMod;
    }

    @Override
    public void fillItemGroup(@Nonnull ItemGroup group, @Nonnull NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
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
        }
    }
}
