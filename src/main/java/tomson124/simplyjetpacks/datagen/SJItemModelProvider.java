package tomson124.simplyjetpacks.datagen;

import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.item.JetpackType;
import tomson124.simplyjetpacks.item.JetpackVariants;

public class SJItemModelProvider extends ItemModelProvider {

    public SJItemModelProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn.getPackOutput(), SimplyJetpacks.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        // Simply Jetpacks:
        builder(itemGenerated, "particle_blend");
        builder(itemGenerated, "particle_none");
        builder(itemGenerated, "particle_flame");
        builder(itemGenerated, "particle_smoke");
        builder(itemGenerated, "particle_rainbow");
        builder(itemGenerated, "particle_soul");
        builder(itemGenerated, "particle_snow");

        builder(itemGenerated, "leather_strap");
        builder(itemGenerated, "pilot_goggles_gold");
        builder(itemGenerated, "pilot_goggles_iron");

        jetpackModel(itemGenerated);
    }

    private void jetpackModel(ModelFile itemGenerated) {
        // Each variant's own model, named after its texture.
        for (JetpackType type : JetpackType.values()) {
            builder(itemGenerated, "jetpack_" + type.getName());
        }

        ItemModelBuilder jetpack = getBuilder("jetpack")
                .parent(itemGenerated)
                .texture("layer0", "item/jetpack_vanilla1");

        // Overrides are matched with >=, so they must be emitted in ascending index order.
        JetpackType[] types = JetpackType.values();
        for (JetpackType type : types) {
            jetpack.override()
                    .predicate(JetpackVariants.PROPERTY_ID, JetpackVariants.indexOf(type.getName()))
                    .model(getExistingFile(modLoc("item/jetpack_" + type.getName())))
                    .end();
        }
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
