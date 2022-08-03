package stormedpanda.simplyjetpacks.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class SJTags {

    // TODO: Fix tags
    public static final Tag.Named<Item> PARTICLES = mod("particles");
    public static final Tag.Named<Item> JETPACK = mod("jetpack");

    public static final Tag.Named<Item> CURIOS_HEAD = curios("head");
    public static final Tag.Named<Item> CURIOS_JETPACK = curios("jetpack");

    private static Tag.Named<Item> forge(String path) {
        return ItemTags.createOptional(new ResourceLocation("forge", path));
    }
    private static Tag.Named<Item> mod(String path) {
        return ItemTags.createOptional(new ResourceLocation(SimplyJetpacks.MODID, path));
    }
    private static Tag.Named<Item> curios(String path) {
        return ItemTags.createOptional(new ResourceLocation("curios", path));
    }
}
