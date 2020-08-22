package stormedpanda.simplyjetpacks;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;

import javax.annotation.Nonnull;

public class CreativeTabSimplyJetpacks extends ItemGroup {

    public CreativeTabSimplyJetpacks() {
        super(SimplyJetpacks.MODID + ".main");
    }

    @Nonnull
    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(RegistryHandler.JETPACK_CREATIVE.get());
    }
}
