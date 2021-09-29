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
        // Mekanism:
        Advancement mek1 = jetpackAdvancement(consumer, root, RegistryHandler.JETPACK_MEK1.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement mek1_armored = jetpackAdvancement(consumer, mek1, RegistryHandler.JETPACK_MEK1_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement mek2 = jetpackAdvancement(consumer, mek1, RegistryHandler.JETPACK_MEK2.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement mek2_armored = jetpackAdvancement(consumer, mek2, RegistryHandler.JETPACK_MEK2_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement mek3 = jetpackAdvancement(consumer, mek2, RegistryHandler.JETPACK_MEK3.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement mek3_armored = jetpackAdvancement(consumer, mek3, RegistryHandler.JETPACK_MEK3_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement mek4 = jetpackAdvancement(consumer, mek3, RegistryHandler.JETPACK_MEK4.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement mek4_armored = jetpackAdvancement(consumer, mek4, RegistryHandler.JETPACK_MEK4_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        // Immersive Engineering:
        Advancement ie1 = jetpackAdvancement(consumer, root, RegistryHandler.JETPACK_IE1.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement ie1_armored = jetpackAdvancement(consumer, ie1, RegistryHandler.JETPACK_IE1_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement ie2 = jetpackAdvancement(consumer, ie1, RegistryHandler.JETPACK_IE2.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement ie2_armored = jetpackAdvancement(consumer, ie2, RegistryHandler.JETPACK_IE2_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement ie3 = jetpackAdvancement(consumer, ie2, RegistryHandler.JETPACK_IE3.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement ie3_armored = jetpackAdvancement(consumer, ie3, RegistryHandler.JETPACK_IE3_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        // Thermal:
        Advancement te1 = jetpackAdvancement(consumer, root, RegistryHandler.JETPACK_TE1.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement te1_armored = jetpackAdvancement(consumer, te1, RegistryHandler.JETPACK_TE1_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement te2 = jetpackAdvancement(consumer, te1, RegistryHandler.JETPACK_TE2.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement te2_armored = jetpackAdvancement(consumer, te2, RegistryHandler.JETPACK_TE2_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement te3 = jetpackAdvancement(consumer, te2, RegistryHandler.JETPACK_TE3.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement te3_armored = jetpackAdvancement(consumer, te3, RegistryHandler.JETPACK_TE3_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement te4 = jetpackAdvancement(consumer, te3, RegistryHandler.JETPACK_TE4.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement te4_armored = jetpackAdvancement(consumer, te4, RegistryHandler.JETPACK_TE4_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement te5 = jetpackAdvancement(consumer, te3, RegistryHandler.JETPACK_TE5.get(), "simplyjetpacks", FrameType.CHALLENGE);
        Advancement te5_armored = jetpackAdvancement(consumer, te4, RegistryHandler.JETPACK_TE5_ARMORED.get(), "simplyjetpacks", FrameType.CHALLENGE);
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
