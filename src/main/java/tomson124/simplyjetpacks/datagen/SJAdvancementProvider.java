package tomson124.simplyjetpacks.datagen;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.handlers.RegistryHandler;
import tomson124.simplyjetpacks.item.Jetpack;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.item.JetpackRegistry;
import tomson124.simplyjetpacks.item.JetpackType;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class SJAdvancementProvider extends AdvancementProvider {

    public SJAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new SJAdvancementGenerator()));
    }

    private static final class SJAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            AdvancementHolder builder = Advancement.Builder.advancement()
                .parent(AdvancementSubProvider.createPlaceholder("minecraft:story/root"))
                .display(
                    new ItemStack(RegistryHandler.JETPACK.get()),
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
                // Every declared criterion must appear in the requirements; either one grants the root.
                .requirements(AdvancementRequirements.anyOf(List.of("install_mod", "crafting_table")))
                .save(saver, ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "root"), existingFileHelper);

            AdvancementHolder lastRoot = null;
            Jetpack lastJetpack = null;
            for (JetpackType type : JetpackType.values()) {
                if (type.isNoAdvancements() || type.isArmored()) {
                    continue;
                }
                Jetpack jetpack = JetpackRegistry.getInstance()
                        .getJetpackById(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, type.getName()));
                if (jetpack == Jetpack.UNDEFINED || jetpack.disabled) {
                    continue;
                }
                AdvancementHolder parent = (lastJetpack == null || lastJetpack.getTier() > jetpack.getTier())
                        ? builder
                        : lastRoot;
                lastRoot = generateJetpack(parent, jetpack, saver);
                lastJetpack = jetpack;
            }

            /*// Vanilla:
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
            //AdvancementHolder te5_armored = generateJetpack(te5, RegistryHandler.JETPACK_TE5_ARMORED.get(), saver);*/
        }

        private AdvancementHolder generateJetpack(AdvancementHolder parent, Jetpack jetpack, Consumer<AdvancementHolder> consumer) {
            ResourceLocation namespace = jetpack.getId();
            String advancementPath = "advancement." + namespace.getNamespace() + "." + "jetpack_" + namespace.getPath();
            return Advancement.Builder.advancement().parent(parent)
                .display(
                    JetpackItem.createStack(jetpack, false),
                    Component.translatable(advancementPath + ".title"),
                    Component.translatable(advancementPath + ".description"),
                    null,
                    AdvancementType.TASK,
                    true,
                    true,
                    false
                )
                .requirements(AdvancementRequirements.allOf(List.of("has_jetpack")))
                // Matches any jetpack: the trigger has no component predicate, and every variant shares an item.
                .addCriterion("has_jetpack", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(RegistryHandler.JETPACK.get())))
                .save(consumer, namespace.getNamespace() + ":" + "jetpack" + "/" + namespace.getPath());
        }
    }
}
