package stormedpanda.simplyjetpacks.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.init.RegistryHandler;

import javax.annotation.Nonnull;

public class SJItemGroup extends ItemGroup {

    public SJItemGroup() {
        super(SimplyJetpacks.MODID + ".main");
    }

    @Nonnull
    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(RegistryHandler.JETPACK_CREATIVE.get());
    }
}
