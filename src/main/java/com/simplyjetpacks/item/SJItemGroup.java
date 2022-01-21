package com.simplyjetpacks.item;

import com.simplyjetpacks.SimplyJetpacks;
import com.simplyjetpacks.handlers.RegistryHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
