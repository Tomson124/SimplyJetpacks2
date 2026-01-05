package tomson124.simplyjetpacks.datagen;

import net.minecraft.advancements.*;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.handlers.RegistryHandler;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.item.JetpackType;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class SJAdvancementProvider extends AdvancementProvider {

    public SJAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        // Add an instance of our generator to the list parameter. This can be done as many times as you want.
        // Having multiple generators is purely for organization, all functionality can be achieved with a single generator.
        super(output, lookupProvider, existingFileHelper, List.of(new MyAdvancementGenerator()));
    }

    private static final class MyAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            // Create an advancement builder using the static #advancement() method.
            // Using #advancement() automatically enables telemetry events. If you do not want this,
            // #recipeAdvancement() can be used instead, there are no other functional differences.
            AdvancementHolder builder = Advancement.Builder.advancement()
                .parent(AdvancementSubProvider.createPlaceholder("minecraft:story/root"))
                .display(
                    new ItemStack(RegistryHandler.JETPACK_CREATIVE.get()),
                    // The advancement title and description. Don't forget to add translations for these!
                    Component.translatable("advancements.simplyjetpacks.root.title"),
                    Component.translatable("advancements.simplyjetpacks.root.description"),
                    ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/advancements/backgrounds/stone.png"),
                    AdvancementType.TASK,
                    true,
                    false,
                    false
                )
                .rewards(
                    // Alternatively, use addExperience() to add to an existing builder.
                    AdvancementRewards.Builder.experience(100)
                        // Alternatively, use loot() to create a new builder.
                        .addLootTable(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath("minecraft", "chests/igloo")))
                        // Alternatively, use recipe() to create a new builder.
                        .addRecipe(ResourceLocation.fromNamespaceAndPath("minecraft", "iron_ingot"))
                        // Alternatively, use function() to create a new builder.
                        .runs(ResourceLocation.fromNamespaceAndPath("examplemod", "example_function"))
                )
                .addCriterion("install_mod", PlayerTrigger.TriggerInstance.located(EntityPredicate.Builder.entity()))
                .requirements(AdvancementRequirements.allOf(List.of("install_mod")))
                .save(saver, ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "root"), existingFileHelper);

            AdvancementHolder lastRoot = null;
            JetpackType lastJetpackType = null;
            for (JetpackType type : JetpackType.values()) {
                if (!type.isNoAdvancements() && !type.isArmored()) {
                    DeferredHolder<Item, ? extends Item> currentJetpack = null;
                    for (DeferredHolder<Item, ? extends Item> item : RegistryHandler.ITEMS.getEntries()) {
                        if (item.get() instanceof JetpackItem jetpackItem && jetpackItem.getJetpackType() == type) {
                            currentJetpack = item;
                            break;
                        }
                    }
                    if (currentJetpack != null) {
                        if (lastJetpackType == null || lastJetpackType.getTier() > type.getTier()) {
                            lastRoot = generateJetpack(builder, (JetpackItem) currentJetpack.get(), currentJetpack.getId(), saver);
                        } else {
                            lastRoot = generateJetpack(lastRoot, (JetpackItem) currentJetpack.get(), currentJetpack.getId(), saver);
                        }
                        lastJetpackType = type;
                    }
                }
            }
        }

        private AdvancementHolder generateJetpack(AdvancementHolder parent, JetpackItem displayItem, ResourceLocation namespace, Consumer<AdvancementHolder> consumer) {
            String advancementPath = "advancement." + namespace.getNamespace() + "." + namespace.getPath();
            return Advancement.Builder.advancement().parent(parent)
                .display(
                    new ItemStack(displayItem),
                    Component.translatable(advancementPath + ".title"),
                    Component.translatable(advancementPath + ".description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    false
                )
                .requirements(AdvancementRequirements.allOf(List.of("has_jetpack")))
                .addCriterion("has_jetpack", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(displayItem)))
                .save(consumer, namespace.getNamespace() + ":" + namespace.getNamespace() + "/" + namespace.getPath());
        }
    }
}
