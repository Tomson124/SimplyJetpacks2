package tonius.simplyjetpacks.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistered extends Item {
    
    public ItemRegistered(String registryName) {
        GameRegistry.registerItem(this, registryName);
    }
    
}
