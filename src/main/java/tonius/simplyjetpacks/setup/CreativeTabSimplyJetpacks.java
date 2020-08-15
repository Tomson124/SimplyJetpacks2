package tonius.simplyjetpacks.setup;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.SimplyJetpacks;

import javax.annotation.Nonnull;

public class CreativeTabSimplyJetpacks extends CreativeTabs {

    public CreativeTabSimplyJetpacks() {
        super(SimplyJetpacks.MODID + ".main");
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.itemJetpack);
    }
}
