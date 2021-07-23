package stormedpanda.simplyjetpacks.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, SimplyJetpacks.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        // testing:
        builder(itemGenerated, "test_energy_item");
        builder(itemGenerated, "test_energy_item_two");

        // Simply Jetpacks:
        builder(itemGenerated, "particle_none");
        builder(itemGenerated, "particle_default");
        builder(itemGenerated, "particle_smoke");
        builder(itemGenerated, "particle_rainbow");

        builder(itemGenerated, "leather_strap");
        builder(itemGenerated, "pilot_goggles");

        builder(itemGenerated, "jetpack_potato");
        builder(itemGenerated, "jetpack_creative");
        builder(itemGenerated, "jetpack_creative_armored");

        builder(itemGenerated, "jetpack_vanilla1");
        builder(itemGenerated, "jetpack_vanilla1_armored");
        builder(itemGenerated, "jetpack_vanilla2");
        builder(itemGenerated, "jetpack_vanilla2_armored");
        builder(itemGenerated, "jetpack_vanilla3");
        builder(itemGenerated, "jetpack_vanilla3_armored");
        builder(itemGenerated, "jetpack_vanilla4");
        builder(itemGenerated, "jetpack_vanilla4_armored");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
