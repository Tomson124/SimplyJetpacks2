package tomson124.simplyjetpacks.datagen;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
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
        super(output, lookupProvider, existingFileHelper, List.of(new SJAdvancementGenerator()));
    }

    private static final class SJAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
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
                .rewards(AdvancementRewards.Builder.experience(100))
                .addCriterion("install_mod", PlayerTrigger.TriggerInstance.located(EntityPredicate.Builder.entity()))
                .addCriterion("crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CRAFTING_TABLE))
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
                            lastRoot = generateJetpack(builder, (JetpackItem) currentJetpack.get(), saver);
                        } else {
                            lastRoot = generateJetpack(lastRoot, (JetpackItem) currentJetpack.get(), saver);
                        }
                        lastJetpackType = type;
                    }
                }
            }

            // Vanilla:
            AdvancementHolder vanilla1 = generateJetpack(builder, RegistryHandler.JETPACK_VANILLA1.get(), saver);
            AdvancementHolder vanilla2 = generateJetpack(vanilla1, RegistryHandler.JETPACK_VANILLA2.get(), saver);
            AdvancementHolder vanilla3 = generateJetpack(vanilla2, RegistryHandler.JETPACK_VANILLA3.get(), saver);
            AdvancementHolder vanilla4 = generateJetpack(vanilla3, RegistryHandler.JETPACK_VANILLA4.get(), saver);
            // Mekanism:
            AdvancementHolder mek1 = generateJetpack(builder, RegistryHandler.JETPACK_MEK1.get(), saver);
            AdvancementHolder mek2 = generateJetpack(mek1, RegistryHandler.JETPACK_MEK2.get(), saver);
            AdvancementHolder mek3 = generateJetpack(mek2, RegistryHandler.JETPACK_MEK3.get(), saver);
            AdvancementHolder mek4 = generateJetpack(mek3, RegistryHandler.JETPACK_MEK4.get(), saver);
            // Immersive Engineering:
            AdvancementHolder ie1 = generateJetpack(builder, RegistryHandler.JETPACK_IE1.get(), saver);
            AdvancementHolder ie2 = generateJetpack(ie1, RegistryHandler.JETPACK_IE2.get(), saver);
            AdvancementHolder ie3 = generateJetpack(ie2, RegistryHandler.JETPACK_IE3.get(), saver);
            // Thermal:
            AdvancementHolder te1 = generateJetpack(builder, RegistryHandler.JETPACK_TE1.get(), saver);
            AdvancementHolder te2 = generateJetpack(te1, RegistryHandler.JETPACK_TE2.get(), saver);
            AdvancementHolder te3 = generateJetpack(te2, RegistryHandler.JETPACK_TE3.get(), saver);
            AdvancementHolder te4 = generateJetpack(te3, RegistryHandler.JETPACK_TE4.get(), saver);
            //AdvancementHolder te5 = generateJetpack(te4, RegistryHandler.JETPACK_TE5.get(), saver);
            //AdvancementHolder te5_armored = generateJetpack(te5, RegistryHandler.JETPACK_TE5_ARMORED.get(), saver);
        }

        private AdvancementHolder generateJetpack(AdvancementHolder parent, JetpackItem displayItem, Consumer<AdvancementHolder> consumer) {
            ResourceLocation namespace = ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, BuiltInRegistries.ITEM.getKey(displayItem.asItem()).getPath());
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
