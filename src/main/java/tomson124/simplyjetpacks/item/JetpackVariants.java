package tomson124.simplyjetpacks.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.init.SJDataComponentTypes;

import java.util.HashMap;
import java.util.Map;

public final class JetpackVariants {

    public static final ResourceLocation PROPERTY_ID =
            ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "variant");

    private static final Map<String, Integer> INDEX_BY_NAME = new HashMap<>();

    static {
        for (JetpackType type : JetpackType.values()) {
            INDEX_BY_NAME.put(type.getName(), type.ordinal());
        }
    }

    private JetpackVariants() {
    }

    public static int indexOf(String name) {
        return INDEX_BY_NAME.getOrDefault(name, -1);
    }

    public static int indexOf(ItemStack stack) {
        ResourceLocation id = stack.get(SJDataComponentTypes.JETPACK_ID);
        return id == null ? -1 : indexOf(id.getPath());
    }
}
