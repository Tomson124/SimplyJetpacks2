package stormedpanda.simplyjetpacks.datagen;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.init.RegistryHandler;

import javax.annotation.Nullable;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagProvider, SimplyJetpacks.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        // Simply Jetpacks:
        tag(ModTags.PARTICLES)
                .add(RegistryHandler.PARTICLE_DEFAULT.get())
                .add(RegistryHandler.PARTICLE_NONE.get())
                .add(RegistryHandler.PARTICLE_SMOKE.get())
                .add(RegistryHandler.PARTICLE_RAINBOW.get());

        // Curios:
        tag(ModTags.HEAD).add(RegistryHandler.PILOT_GOGGLES.get());
        tag(ModTags.BACK)
                .add(RegistryHandler.JETPACK_CREATIVE.get())
                .add(RegistryHandler.JETPACK_CREATIVE_ARMORED.get());
    }
}
