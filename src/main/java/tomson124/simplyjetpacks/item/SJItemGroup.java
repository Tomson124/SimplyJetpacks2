package tomson124.simplyjetpacks.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.handlers.RegistryHandler;

public class SJItemGroup extends CreativeModeTab {

    public SJItemGroup() {
        super(CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + SimplyJetpacks.MODID + ".main"))
                .icon(()-> new ItemStack(RegistryHandler.JETPACK_CREATIVE.get()))
                // I have *zero* clue why this is required for this mod _exclusively_ but I don't care enough to figure out, why.
                .backgroundTexture(ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/creative_inventory/tab_items.png"))
                .displayItems((params, output) -> {
                    for(DeferredHolder<Item, ? extends Item> i : RegistryHandler.ITEMS.getEntries()) {
                        Item item = i.get();
                        output.accept(new ItemStack(item));
                        if (item instanceof JetpackItem jetpackItem) {
                            output.accept(jetpackItem.asChargedCopy());
                        }
                    }
                }));
    }
}
