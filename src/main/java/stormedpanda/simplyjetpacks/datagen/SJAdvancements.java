package stormedpanda.simplyjetpacks.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.PositionTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.handlers.RegistryHandler;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class SJAdvancements implements Consumer<Consumer<Advancement>> {

    // showToast, announceChat, hidden

    public SJAdvancements() {
    }

    @Override
    public void accept(Consumer<Advancement> consumer) {
        // Simply Jetpacks:
        Advancement root = rootAdvancement(consumer, RegistryHandler.JETPACK_CREATIVE.get(), "crafting_table", InventoryChangeTrigger.Instance.hasItems(Blocks.CRAFTING_TABLE));
        // Vanilla:
        Advancement vanilla1 = jetpackAdvancement(consumer, root, RegistryHandler.JETPACK_VANILLA1.get());
        Advancement vanilla2 = jetpackAdvancement(consumer, vanilla1, RegistryHandler.JETPACK_VANILLA2.get());
        Advancement vanilla3 = jetpackAdvancement(consumer, vanilla2, RegistryHandler.JETPACK_VANILLA3.get());
        Advancement vanilla4 = jetpackAdvancement(consumer, vanilla3, RegistryHandler.JETPACK_VANILLA4.get());
        // Mekanism:
        Advancement mek1 = jetpackAdvancement(consumer, root, RegistryHandler.JETPACK_MEK1.get());
        Advancement mek2 = jetpackAdvancement(consumer, mek1, RegistryHandler.JETPACK_MEK2.get());
        Advancement mek3 = jetpackAdvancement(consumer, mek2, RegistryHandler.JETPACK_MEK3.get());
        Advancement mek4 = jetpackAdvancement(consumer, mek3, RegistryHandler.JETPACK_MEK4.get());
        // Immersive Engineering:
        Advancement ie1 = jetpackAdvancement(consumer, root, RegistryHandler.JETPACK_IE1.get());
        Advancement ie2 = jetpackAdvancement(consumer, ie1, RegistryHandler.JETPACK_IE2.get());
        Advancement ie3 = jetpackAdvancement(consumer, ie2, RegistryHandler.JETPACK_IE3.get());
        // Thermal:
        Advancement te1 = jetpackAdvancement(consumer, root, RegistryHandler.JETPACK_TE1.get());
        Advancement te2 = jetpackAdvancement(consumer, te1, RegistryHandler.JETPACK_TE2.get());
        Advancement te3 = jetpackAdvancement(consumer, te2, RegistryHandler.JETPACK_TE3.get());
        Advancement te4 = jetpackAdvancement(consumer, te3, RegistryHandler.JETPACK_TE4.get());
        Advancement te5 = jetpackAdvancement(consumer, te4, RegistryHandler.JETPACK_TE5.get());
        Advancement te5_armored = jetpackAdvancement(consumer, te5, RegistryHandler.JETPACK_TE5_ARMORED.get());
    }

    private Advancement jetpackAdvancement(Consumer<Advancement> consumer, Advancement parent, IItemProvider item) {
        String name = item.asItem().getRegistryName().getPath();
        return Advancement.Builder.advancement().parent(parent)
                .display(item,
                        new TranslationTextComponent("advancement.simplyjetpacks." + name + ".title"),
                        new TranslationTextComponent("advancement.simplyjetpacks." + name + ".description"),
                        null, FrameType.TASK, true, true, false
                )
                .addCriterion("has_jetpack", InventoryChangeTrigger.Instance.hasItems(item))
                .save(consumer, new ResourceLocation(SimplyJetpacks.MODID, ("simplyjetpacks" == null ? "" : "simplyjetpacks" + "/") + name).toString());
    }

    private Advancement advancement(Consumer<Advancement> consumer, Advancement parent, IItemProvider item, String path, FrameType frame, String criterionId, @Nullable ICriterionInstance criterion) {
        String name = item.asItem().getRegistryName().getPath();
        return Advancement.Builder.advancement().parent(parent)
                .display(item,
                        new TranslationTextComponent("advancement.simplyjetpacks." + name + ".title"),
                        new TranslationTextComponent("advancement.simplyjetpacks." + name + ".description"),
                        null, frame, true, true, false
                )
                .addCriterion(criterionId, criterion == null ? InventoryChangeTrigger.Instance.hasItems(item) : criterion)
                .save(consumer, new ResourceLocation(SimplyJetpacks.MODID, (path == null ? "" : path + "/") + name).toString());
    }

    private Advancement rootAdvancement(Consumer<Advancement> consumer, IItemProvider item, String criterionId, ICriterionInstance criterion) {
        return Advancement.Builder.advancement()
                .display(item,
                        new TranslationTextComponent("advancement.simplyjetpacks." + "root" + ".title"),
                        new TranslationTextComponent("advancement.simplyjetpacks." + "root" + ".description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/" + "stone" + ".png"),
                        FrameType.TASK, true, false, false)
                .addCriterion("any", PositionTrigger.Instance.located(LocationPredicate.ANY))
                //.addCriterion(criterionId, criterion)
                .save(consumer, new ResourceLocation(SimplyJetpacks.MODID, "root").toString());
    }
}
