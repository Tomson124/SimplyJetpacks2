package stormedpanda.simplyjetpacks;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;

import javax.annotation.Nonnull;

public class CreativeTabSimplyJetpacks extends CreativeModeTab {

    public CreativeTabSimplyJetpacks() {
        super(SimplyJetpacks.MODID + ".main");
    }

    @Nonnull
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(RegistryHandler.JETPACK_CREATIVE.get());
    }
}
