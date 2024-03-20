package stormedpanda.simplyjetpacks.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;

import javax.annotation.Nonnull;

public class SJItemGroup extends CreativeModeTab {

    public SJItemGroup() {
        super(new Builder(Row.TOP, ))
        super(SimplyJetpacks.MODID + ".main");
    }

    @Nonnull
    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(RegistryHandler.JETPACK_CREATIVE.get());
    }
}
