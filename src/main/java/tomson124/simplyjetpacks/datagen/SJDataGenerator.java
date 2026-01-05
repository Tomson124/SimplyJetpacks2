package tomson124.simplyjetpacks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import tomson124.simplyjetpacks.SimplyJetpacks;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = SimplyJetpacks.MODID)
public final class SJDataGenerator {

    private SJDataGenerator() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Required for 1.20 data-gen
        BlockTagsProvider dummyProvider = new BlockTagsProvider(gen.getPackOutput(), event.getLookupProvider(), SimplyJetpacks.MODID, existingFileHelper) {
            @Override
            protected void addTags(HolderLookup.Provider provider) {}
        };

        // TODO: test the boolean
        gen.addProvider(true, new SJItemModelProvider(gen, existingFileHelper));
        gen.addProvider(true, dummyProvider);
        gen.addProvider(true, new SJItemTagsProvider(gen.getPackOutput(), event.getLookupProvider(), dummyProvider.contentsGetter(), event.getExistingFileHelper()));
        gen.addProvider(true, new SJRecipeProvider(gen));
        gen.addProvider(true, new SJAdvancementProvider(output, lookupProvider, existingFileHelper));

        DatapackBuiltinEntriesProvider modRegistryProvider = new SJRegistryProvider(gen.getPackOutput(), event.getLookupProvider());
        gen.addProvider(true, modRegistryProvider);
    }
}