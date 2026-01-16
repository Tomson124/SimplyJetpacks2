package tomson124.simplyjetpacks.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import tomson124.simplyjetpacks.SimplyJetpacks;

public class SJTags {

    public static final TagKey<Item> PARTICLES = mod("particles");
    public static final TagKey<Item> JETPACK = mod("jetpack");

    public static final TagKey<Item> CURIOS_HEAD = curios("head");
    public static final TagKey<Item> CURIOS_JETPACK = curios("jetpack");

    private static TagKey<Item> neoforge(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }
    private static TagKey<Item> mod(String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, path));
    }
    private static TagKey<Item> curios(String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("curios", path));
    }
}
