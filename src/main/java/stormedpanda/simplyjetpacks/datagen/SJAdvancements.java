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
        Advancement root = rootAdvancement(consumer, RegistryHandler.JETPACK_CREATIVE.get(), "root", null, "stone", FrameType.TASK, "crafting_table", InventoryChangeTrigger.Instance.hasItems(Blocks.CRAFTING_TABLE));
        Advancement creative = jetpackAdvancement(consumer, root, RegistryHandler.JETPACK_CREATIVE.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement creative_armored = jetpackAdvancement(consumer, creative, RegistryHandler.JETPACK_CREATIVE_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        // Vanilla:
        Advancement vanilla1 = jetpackAdvancement(consumer, root, RegistryHandler.JETPACK_VANILLA1.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement vanilla1_armored = jetpackAdvancement(consumer, vanilla1, RegistryHandler.JETPACK_VANILLA1_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement vanilla2 = jetpackAdvancement(consumer, vanilla1, RegistryHandler.JETPACK_VANILLA2.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement vanilla2_armored = jetpackAdvancement(consumer, vanilla2, RegistryHandler.JETPACK_VANILLA2_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement vanilla3 = jetpackAdvancement(consumer, vanilla2, RegistryHandler.JETPACK_VANILLA3.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement vanilla3_armored = jetpackAdvancement(consumer, vanilla3, RegistryHandler.JETPACK_VANILLA3_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement vanilla4 = jetpackAdvancement(consumer, vanilla3, RegistryHandler.JETPACK_VANILLA4.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement vanilla4_armored = jetpackAdvancement(consumer, vanilla4, RegistryHandler.JETPACK_VANILLA4_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
    }

    private Advancement jetpackAdvancement(Consumer<Advancement> consumer, Advancement parent, IItemProvider item, String path, FrameType frame) {
        String name = item.asItem().getRegistryName().getPath();
        return Advancement.Builder.advancement().parent(parent)
                .display(item,
                        new TranslationTextComponent("advancement.simplyjetpacks." + name + ".title"),
                        new TranslationTextComponent("advancement.simplyjetpacks." + name + ".description"),
                        null, frame, true, true, false
                )
                .addCriterion("has_jetpack", InventoryChangeTrigger.Instance.hasItems(item))
                .save(consumer, new ResourceLocation(SimplyJetpacks.MODID, (path == null ? "" : path + "/") + name).toString());
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

    private Advancement rootAdvancement(Consumer<Advancement> consumer, IItemProvider item, String key, @Nullable String path, String background, FrameType frame, String criterionId, ICriterionInstance criterion) {
        return Advancement.Builder.advancement()
                .display(item,
                        new TranslationTextComponent("advancement.simplyjetpacks." + key + ".title"),
                        new TranslationTextComponent("advancement.simplyjetpacks." + key + ".description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/" + background + ".png"),
                        frame, true, false, false)
                .addCriterion("any", PositionTrigger.Instance.located(LocationPredicate.ANY))
                //.addCriterion(criterionId, criterion)
                .save(consumer, new ResourceLocation(SimplyJetpacks.MODID, (path == null ? "" : path + "/") + key).toString());
    }
}
