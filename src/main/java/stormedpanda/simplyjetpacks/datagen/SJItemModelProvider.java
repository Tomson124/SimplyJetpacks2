package stormedpanda.simplyjetpacks.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class SJItemModelProvider extends ItemModelProvider {

    public SJItemModelProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, SimplyJetpacks.MODID, existingFileHelper);
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

        builder(itemGenerated, "jetpack_mek1");
        builder(itemGenerated, "jetpack_mek1_armored");
        builder(itemGenerated, "jetpack_mek2");
        builder(itemGenerated, "jetpack_mek2_armored");
        builder(itemGenerated, "jetpack_mek3");
        builder(itemGenerated, "jetpack_mek3_armored");
        builder(itemGenerated, "jetpack_mek4");
        builder(itemGenerated, "jetpack_mek4_armored");

        builder(itemGenerated, "jetpack_te1");
        builder(itemGenerated, "jetpack_te1_armored");
        builder(itemGenerated, "jetpack_te2");
        builder(itemGenerated, "jetpack_te2_armored");
        builder(itemGenerated, "jetpack_te3");
        builder(itemGenerated, "jetpack_te3_armored");
        builder(itemGenerated, "jetpack_te4");
        builder(itemGenerated, "jetpack_te4_armored");
        builder(itemGenerated, "jetpack_te5");
        builder(itemGenerated, "jetpack_te5_enderium");

        builder(itemGenerated, "jetpack_ie1");
        builder(itemGenerated, "jetpack_ie1_armored");
        builder(itemGenerated, "jetpack_ie2");
        builder(itemGenerated, "jetpack_ie2_armored");
        builder(itemGenerated, "jetpack_ie3");
        builder(itemGenerated, "jetpack_ie3_armored");
        
        builder(itemGenerated, "armorplating_mek1");
        builder(itemGenerated, "armorplating_mek2");
        builder(itemGenerated, "armorplating_mek3");
        builder(itemGenerated, "armorplating_mek4");

        builder(itemGenerated, "armorplating_te1");
        builder(itemGenerated, "armorplating_te2");
        builder(itemGenerated, "armorplating_te3");
        builder(itemGenerated, "armorplating_te4");
        builder(itemGenerated, "armorplating_te5");
        builder(itemGenerated, "armorplating_te5_enderium");

        builder(itemGenerated, "armorplating_ie1");
        builder(itemGenerated, "armorplating_ie2");
        builder(itemGenerated, "armorplating_ie3");

        builder(itemGenerated, "thruster_vanilla1");
        builder(itemGenerated, "thruster_vanilla2");
        builder(itemGenerated, "thruster_vanilla3");
        builder(itemGenerated, "thruster_vanilla4");
        
        builder(itemGenerated, "thruster_mek1");
        builder(itemGenerated, "thruster_mek2");
        builder(itemGenerated, "thruster_mek3");
        builder(itemGenerated, "thruster_mek4");

        builder(itemGenerated, "thruster_te1");
        builder(itemGenerated, "thruster_te2");
        builder(itemGenerated, "thruster_te3");
        builder(itemGenerated, "thruster_te4");
        builder(itemGenerated, "thruster_te5");

        builder(itemGenerated, "thruster_ie1");
        builder(itemGenerated, "thruster_ie2");
        builder(itemGenerated, "thruster_ie3");

        builder(itemGenerated, "unit_cryotheum_empty");
        builder(itemGenerated, "unit_cryotheum");
        builder(itemGenerated, "unit_glowstone_empty");
        builder(itemGenerated, "unit_glowstone");

        builder(itemGenerated, "flux_chestplate");

        // Patchouli:
        builder(itemGenerated, "guidebook");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
