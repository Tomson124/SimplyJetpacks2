package stormedpanda.simplyjetpacks.datagen;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class ModTags {

    public static final ITag.INamedTag<Item> PARTICLES = mod("particles");
    public static final ITag.INamedTag<Item> JETPACK = mod("jetpack");

    public static final ITag.INamedTag<Item> HEAD = curios("head");
    public static final ITag.INamedTag<Item> BACK = curios("back");

    private static ITag.INamedTag<Item> forge(String path) {
        return ItemTags.createOptional(new ResourceLocation("forge", path));
    }
    private static ITag.INamedTag<Item> mod(String path) {
        return ItemTags.createOptional(new ResourceLocation(SimplyJetpacks.MODID, path));
    }
    private static ITag.INamedTag<Item> curios(String path) {
        return ItemTags.createOptional(new ResourceLocation("curios", path));
    }
}
