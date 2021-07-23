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

        tag(ModTags.JETPACK)
                //.add(RegistryHandler.JETPACK_POTATO.get())
                .add(RegistryHandler.JETPACK_CREATIVE.get())
                .add(RegistryHandler.JETPACK_CREATIVE_ARMORED.get())
                
                .add(RegistryHandler.JETPACK_VANILLA1.get())
                .add(RegistryHandler.JETPACK_VANILLA1_ARMORED.get())
                .add(RegistryHandler.JETPACK_VANILLA2.get())
                .add(RegistryHandler.JETPACK_VANILLA2_ARMORED.get())
                .add(RegistryHandler.JETPACK_VANILLA3.get())
                .add(RegistryHandler.JETPACK_VANILLA3_ARMORED.get())
                .add(RegistryHandler.JETPACK_VANILLA4.get())
                .add(RegistryHandler.JETPACK_VANILLA4_ARMORED.get())

                .add(RegistryHandler.JETPACK_MEK1.get())
                .add(RegistryHandler.JETPACK_MEK1_ARMORED.get())
                .add(RegistryHandler.JETPACK_MEK2.get())
                .add(RegistryHandler.JETPACK_MEK2_ARMORED.get())
                .add(RegistryHandler.JETPACK_MEK3.get())
                .add(RegistryHandler.JETPACK_MEK3_ARMORED.get())
                .add(RegistryHandler.JETPACK_MEK4.get())
                .add(RegistryHandler.JETPACK_MEK4_ARMORED.get())

                .add(RegistryHandler.JETPACK_TE1.get())
                .add(RegistryHandler.JETPACK_TE1_ARMORED.get())
                .add(RegistryHandler.JETPACK_TE2.get())
                .add(RegistryHandler.JETPACK_TE2_ARMORED.get())
                .add(RegistryHandler.JETPACK_TE3.get())
                .add(RegistryHandler.JETPACK_TE3_ARMORED.get())
                .add(RegistryHandler.JETPACK_TE4.get())
                .add(RegistryHandler.JETPACK_TE4_ARMORED.get())

                .add(RegistryHandler.JETPACK_IE1.get())
                .add(RegistryHandler.JETPACK_IE1_ARMORED.get())
                .add(RegistryHandler.JETPACK_IE2.get())
                .add(RegistryHandler.JETPACK_IE2_ARMORED.get())
                .add(RegistryHandler.JETPACK_IE3.get())
                .add(RegistryHandler.JETPACK_IE3_ARMORED.get())
                ;

        // Curios:
        tag(ModTags.HEAD).add(RegistryHandler.PILOT_GOGGLES.get());
        tag(ModTags.BACK)
                //.add(RegistryHandler.JETPACK_POTATO.get())
                .add(RegistryHandler.JETPACK_CREATIVE.get())
                .add(RegistryHandler.JETPACK_CREATIVE_ARMORED.get())

                .add(RegistryHandler.JETPACK_VANILLA1.get())
                .add(RegistryHandler.JETPACK_VANILLA1_ARMORED.get())
                .add(RegistryHandler.JETPACK_VANILLA2.get())
                .add(RegistryHandler.JETPACK_VANILLA2_ARMORED.get())
                .add(RegistryHandler.JETPACK_VANILLA3.get())
                .add(RegistryHandler.JETPACK_VANILLA3_ARMORED.get())
                .add(RegistryHandler.JETPACK_VANILLA4.get())
                .add(RegistryHandler.JETPACK_VANILLA4_ARMORED.get())

                .add(RegistryHandler.JETPACK_MEK1.get())
                .add(RegistryHandler.JETPACK_MEK1_ARMORED.get())
                .add(RegistryHandler.JETPACK_MEK2.get())
                .add(RegistryHandler.JETPACK_MEK2_ARMORED.get())
                .add(RegistryHandler.JETPACK_MEK3.get())
                .add(RegistryHandler.JETPACK_MEK3_ARMORED.get())
                .add(RegistryHandler.JETPACK_MEK4.get())
                .add(RegistryHandler.JETPACK_MEK4_ARMORED.get())

                .add(RegistryHandler.JETPACK_TE1.get())
                .add(RegistryHandler.JETPACK_TE1_ARMORED.get())
                .add(RegistryHandler.JETPACK_TE2.get())
                .add(RegistryHandler.JETPACK_TE2_ARMORED.get())
                .add(RegistryHandler.JETPACK_TE3.get())
                .add(RegistryHandler.JETPACK_TE3_ARMORED.get())
                .add(RegistryHandler.JETPACK_TE4.get())
                .add(RegistryHandler.JETPACK_TE4_ARMORED.get())

                .add(RegistryHandler.JETPACK_IE1.get())
                .add(RegistryHandler.JETPACK_IE1_ARMORED.get())
                .add(RegistryHandler.JETPACK_IE2.get())
                .add(RegistryHandler.JETPACK_IE2_ARMORED.get())
                .add(RegistryHandler.JETPACK_IE3.get())
                .add(RegistryHandler.JETPACK_IE3_ARMORED.get())
        ;
    }
}
