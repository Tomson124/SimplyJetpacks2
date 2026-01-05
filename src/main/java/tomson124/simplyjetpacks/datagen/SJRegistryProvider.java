package tomson124.simplyjetpacks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import tomson124.simplyjetpacks.SimplyJetpacks;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class SJRegistryProvider  extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, SJDamageTypes::bootstrap);

    public SJRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Collections.singleton(SimplyJetpacks.MODID));
    }
}
