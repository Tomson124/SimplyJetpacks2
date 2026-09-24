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
                .icon(() -> new ItemStack(RegistryHandler.JETPACK.get()))
                .backgroundTexture(ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/creative_inventory/tab_items.png"))
                .displayItems((params, output) -> {
                    for (DeferredHolder<Item, ? extends Item> i : RegistryHandler.ITEMS.getEntries()) {
                        Item item = i.get();
                        if (!(item instanceof JetpackItem)) {
                            output.accept(new ItemStack(item));
                        }
                    }

                    for (Jetpack jetpack : JetpackRegistry.getInstance().getJetpacks()) {
                        if (jetpack.disabled) {
                            continue;
                        }
                        output.accept(JetpackItem.createStack(jetpack, false));
                        if (!jetpack.creative) {
                            output.accept(JetpackItem.createStack(jetpack, true));
                        }
                    }
                }));
    }
}
