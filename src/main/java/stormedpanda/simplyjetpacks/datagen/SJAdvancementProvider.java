package stormedpanda.simplyjetpacks.datagen;

import com.google.common.collect.Sets;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

public class SJAdvancementProvider extends AdvancementProvider {

    private static final Logger LOGGER = LogManager.getLogger();
//    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;

    public SJAdvancementProvider(DataGenerator generatorIn) {
        super(generatorIn);
        generator = generatorIn;
    }


    @Override
    public void run(CachedOutput cache) {
        Path path = this.generator.getOutputFolder();
        Set<ResourceLocation> set = Sets.newHashSet();
        Consumer<Advancement> consumer = (advancement) -> {
            if (!set.add(advancement.getId())) {
                throw new IllegalStateException("Duplicate advancement " + advancement.getId());
            } else {
                Path path1 = createPath(path, advancement);
                try {
                    DataProvider.saveStable(cache, advancement.deconstruct().serializeToJson(), path1);
                } catch (IOException ioexception) {
                    LOGGER.error("Couldn't save advancement {}", path1, ioexception);
                }
            }
        };
        new SJAdvancements().accept(consumer);
    }

    private static Path createPath(Path path, Advancement advancement) {
        return path.resolve("data/" + SimplyJetpacks.MODID + "/advancements/" + advancement.getId().getPath() + ".json");
    }
}
