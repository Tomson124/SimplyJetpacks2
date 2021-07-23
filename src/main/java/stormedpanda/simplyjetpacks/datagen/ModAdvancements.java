package stormedpanda.simplyjetpacks.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.init.RegistryHandler;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ModAdvancements implements Consumer<Consumer<Advancement>> {

    // showToast, announceChat, hidden

    public ModAdvancements() {
    }

    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement root = rootAdvancement(consumer, RegistryHandler.JETPACK_CREATIVE.get(), "root", null, "stone", FrameType.TASK, "crafting_table", InventoryChangeTrigger.Instance.hasItems(Blocks.CRAFTING_TABLE));
        Advancement creative = advancement(consumer, root, RegistryHandler.JETPACK_CREATIVE.get(), "jetpack_creative", "simplyjetpacks/", FrameType.CHALLENGE, "has_jetpack", null);
        Advancement creative_armored = advancement(consumer, creative, RegistryHandler.JETPACK_CREATIVE_ARMORED.get(), "jetpack_creative_armored", "simplyjetpacks/", FrameType.CHALLENGE, "has_jetpack", null);
    }

    private Advancement advancement(Consumer<Advancement> consumer, Advancement parent, IItemProvider icon, String key, String path, FrameType frame, String criterionId, @Nullable ICriterionInstance criterion) {
        Advancement.Builder advancement = Advancement.Builder.advancement();
        advancement.parent(parent).display(icon,
                new TranslationTextComponent("advancement.simplyjetpacks." + key + ".title"),
                new TranslationTextComponent("advancement.simplyjetpacks." + key + ".description"),
                null, frame, true, true, false
        );
        if (criterion == null) {
            advancement.addCriterion(criterionId, InventoryChangeTrigger.Instance.hasItems(icon));
        } else {
            advancement.addCriterion(criterionId, criterion);
        }
        return advancement.save(consumer, SimplyJetpacks.MODID + ":" + path + key);
    }

    private Advancement advancementOld(Consumer<Advancement> consumer, Advancement parent, IItemProvider icon, String key, String path, FrameType frame, String criterionId, @Nullable ICriterionInstance criterion) {
        return criterion == null ?
                Advancement.Builder.advancement()
                        .parent(parent)
                        .display(icon,
                                new TranslationTextComponent("advancement.simplyjetpacks." + key + ".title"),
                                new TranslationTextComponent("advancement.simplyjetpacks." + key + ".description"),
                                null,
                                frame, true, true, false)
                        .addCriterion(criterionId, InventoryChangeTrigger.Instance.hasItems(icon))
                        .save(consumer, path + key)
                :
                Advancement.Builder.advancement()
                        .parent(parent)
                        .display(icon,
                                new TranslationTextComponent("advancement.simplyjetpacks." + key + ".title"),
                                new TranslationTextComponent("advancement.simplyjetpacks." + key + ".description"),
                                null,
                                frame, true, true, false)
                        .addCriterion(criterionId, criterion)
                        .save(consumer, path + key);

    }

    private Advancement rootAdvancement(Consumer<Advancement> consumer, IItemProvider icon, String key, @Nullable String path, String background, FrameType frame, String criterionId, ICriterionInstance criterion) {
        return Advancement.Builder.advancement()
                .display(icon,
                        new TranslationTextComponent("advancement.simplyjetpacks." + key + ".title"),
                        new TranslationTextComponent("advancement.simplyjetpacks." + key + ".description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/" + background + ".png"),
                        frame, true, true, false)
                //.addCriterion("any", PositionTrigger.Instance.located(LocationPredicate.ANY))
                .addCriterion(criterionId, criterion)
                .save(consumer, path == null ? SimplyJetpacks.MODID + ":" + key : SimplyJetpacks.MODID + ":" + path + key);
    }
}
