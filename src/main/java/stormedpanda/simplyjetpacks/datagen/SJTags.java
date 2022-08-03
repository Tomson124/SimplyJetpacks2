package stormedpanda.simplyjetpacks.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class SJTags {

    public static final TagKey<Item> PARTICLES = mod("particles");
    public static final TagKey<Item> JETPACK = mod("jetpack");

    public static final TagKey<Item> CURIOS_HEAD = curios("head");
    public static final TagKey<Item> CURIOS_JETPACK = curios("jetpack");

    private static TagKey<Item> forge(String path) {
        return ItemTags.create(new ResourceLocation("forge", path));
    }
    private static TagKey<Item> mod(String path) {
        return ItemTags.create(new ResourceLocation(SimplyJetpacks.MODID, path));
    }
    private static TagKey<Item> curios(String path) {
        return ItemTags.create(new ResourceLocation("curios", path));
    }
}
