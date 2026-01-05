package tomson124.simplyjetpacks.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.level.Level;
import tomson124.simplyjetpacks.SimplyJetpacks;

public class SJDamageTypes {
    public final static ResourceKey<DamageType> DEATH_BY_POTATO_JETPACK = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "jetpack_potato"));
    public final static ResourceKey<DamageType> DEATH_BY_JETPACK_EXPLOSION = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "jetpack_explode"));

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(DEATH_BY_POTATO_JETPACK, new DamageType("simplyjetpacks.jetpack_potato", DamageScaling.NEVER, 0.1F, DamageEffects.HURT, DeathMessageType.DEFAULT));
        context.register(DEATH_BY_JETPACK_EXPLOSION, new DamageType("simplyjetpacks.jetpack_explode", DamageScaling.NEVER, 0.1F, DamageEffects.HURT, DeathMessageType.DEFAULT));
    }

    public static DamageSource provideDamage(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
    }
}
