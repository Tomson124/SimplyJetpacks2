package com.simplyjetpacks.handlers;

import com.simplyjetpacks.SimplyJetpacks;
import com.simplyjetpacks.crafting.JetpackSpecialRecipe;
import com.simplyjetpacks.enchantment.EnchantmentFuelEfficiency;
import com.simplyjetpacks.item.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SimplyJetpacks.MODID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, SimplyJetpacks.MODID);
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SimplyJetpacks.MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SimplyJetpacks.MODID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, SimplyJetpacks.MODID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Particles:
    public static final RegistryObject<BasicParticleType> RAINBOW_PARTICLE = PARTICLE_TYPES.register("rainbow_particle", () -> new BasicParticleType(false));

    // Recipes:
    public static final RegistryObject<SpecialRecipeSerializer<JetpackSpecialRecipe>> JETPACK_SPECIAL_RECIPE = RECIPE_SERIALIZERS.register("jetpack_special_recipe",() -> new SpecialRecipeSerializer<>(JetpackSpecialRecipe::new));

    // Enchantments:
    public static final EnchantmentType JETPACK_ENCHANTMENT_TYPE = EnchantmentType.create("JETPACK", (item -> item instanceof JetpackItem));
    public static final RegistryObject<EnchantmentFuelEfficiency> FUEL_EFFICIENCY = ENCHANTMENTS.register("fuel_efficiency", EnchantmentFuelEfficiency::new);

    // Items:
    public static final RegistryObject<SJItem> LEATHER_STRAP = ITEMS.register("leather_strap", SJItem::new);
    public static final RegistryObject<SJItem> PARTICLE_BLEND = ITEMS.register("particle_blend", SJItem::new);
    public static final RegistryObject<SJItem> PARTICLE_NONE = ITEMS.register("particle_none", SJItem::new);
    public static final RegistryObject<SJItem> PARTICLE_FLAME = ITEMS.register("particle_flame", SJItem::new);
    public static final RegistryObject<SJItem> PARTICLE_SMOKE = ITEMS.register("particle_smoke", SJItem::new);
    public static final RegistryObject<SJItem> PARTICLE_RAINBOW = ITEMS.register("particle_rainbow", SJItem::new);
    public static final RegistryObject<SJItem> PARTICLE_SOUL = ITEMS.register("particle_soul", SJItem::new);
    public static final RegistryObject<SJItem> PARTICLE_SNOW = ITEMS.register("particle_snow", SJItem::new);

    public static final RegistryObject<PilotGogglesItem> PILOT_GOGGLES_GOLD = ITEMS.register("pilot_goggles_gold", () -> new PilotGogglesItem("gold"));
    public static final RegistryObject<PilotGogglesItem> PILOT_GOGGLES_IRON = ITEMS.register("pilot_goggles_iron", () -> new PilotGogglesItem("iron"));
    
    public static final RegistryObject<PotatoJetpackItem> JETPACK_POTATO = ITEMS.register("jetpack_potato", PotatoJetpackItem::new);
    public static final RegistryObject<JetpackItem> JETPACK_CREATIVE = ITEMS.register("jetpack_creative", () -> new JetpackItem(JetpackType.CREATIVE));
    public static final RegistryObject<JetpackItem> JETPACK_CREATIVE_ARMORED = ITEMS.register("jetpack_creative_armored", () -> new JetpackItem(JetpackType.CREATIVE_ARMORED));

    public static final RegistryObject<JetpackItem> JETPACK_VANILLA1 = ITEMS.register("jetpack_vanilla1", () -> new JetpackItem(JetpackType.VANILLA1));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA1_ARMORED = ITEMS.register("jetpack_vanilla1_armored", () -> new JetpackItem(JetpackType.VANILLA1_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA2 = ITEMS.register("jetpack_vanilla2", () -> new JetpackItem(JetpackType.VANILLA2));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA2_ARMORED = ITEMS.register("jetpack_vanilla2_armored", () -> new JetpackItem(JetpackType.VANILLA2_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA3 = ITEMS.register("jetpack_vanilla3", () -> new JetpackItem(JetpackType.VANILLA3));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA3_ARMORED = ITEMS.register("jetpack_vanilla3_armored", () -> new JetpackItem(JetpackType.VANILLA3_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA4 = ITEMS.register("jetpack_vanilla4", () -> new JetpackItem(JetpackType.VANILLA4));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA4_ARMORED = ITEMS.register("jetpack_vanilla4_armored", () -> new JetpackItem(JetpackType.VANILLA4_ARMORED));

    public static final RegistryObject<JetpackItem> JETPACK_MEK1 = ITEMS.register("jetpack_mek1", () -> new JetpackItem(JetpackType.MEK1));
    public static final RegistryObject<JetpackItem> JETPACK_MEK1_ARMORED = ITEMS.register("jetpack_mek1_armored", () -> new JetpackItem(JetpackType.MEK1_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_MEK2 = ITEMS.register("jetpack_mek2", () -> new JetpackItem(JetpackType.MEK2));
    public static final RegistryObject<JetpackItem> JETPACK_MEK2_ARMORED = ITEMS.register("jetpack_mek2_armored", () -> new JetpackItem(JetpackType.MEK2_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_MEK3 = ITEMS.register("jetpack_mek3", () -> new JetpackItem(JetpackType.MEK3));
    public static final RegistryObject<JetpackItem> JETPACK_MEK3_ARMORED = ITEMS.register("jetpack_mek3_armored", () -> new JetpackItem(JetpackType.MEK3_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_MEK4 = ITEMS.register("jetpack_mek4", () -> new JetpackItem(JetpackType.MEK4));
    public static final RegistryObject<JetpackItem> JETPACK_MEK4_ARMORED = ITEMS.register("jetpack_mek4_armored", () -> new JetpackItem(JetpackType.MEK4_ARMORED));

    public static final RegistryObject<JetpackItem> JETPACK_TE1 = ITEMS.register("jetpack_te1", () -> new JetpackItem(JetpackType.TE1));
    public static final RegistryObject<JetpackItem> JETPACK_TE1_ARMORED = ITEMS.register("jetpack_te1_armored", () -> new JetpackItem(JetpackType.TE1_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_TE2 = ITEMS.register("jetpack_te2", () -> new JetpackItem(JetpackType.TE2));
    public static final RegistryObject<JetpackItem> JETPACK_TE2_ARMORED = ITEMS.register("jetpack_te2_armored", () -> new JetpackItem(JetpackType.TE2_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_TE3 = ITEMS.register("jetpack_te3", () -> new JetpackItem(JetpackType.TE3));
    public static final RegistryObject<JetpackItem> JETPACK_TE3_ARMORED = ITEMS.register("jetpack_te3_armored", () -> new JetpackItem(JetpackType.TE3_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_TE4 = ITEMS.register("jetpack_te4", () -> new JetpackItem(JetpackType.TE4));
    public static final RegistryObject<JetpackItem> JETPACK_TE4_ARMORED = ITEMS.register("jetpack_te4_armored", () -> new JetpackItem(JetpackType.TE4_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_TE5 = ITEMS.register("jetpack_te5", () -> new JetpackItem(JetpackType.TE5));
    public static final RegistryObject<JetpackItem> JETPACK_TE5_ARMORED = ITEMS.register("jetpack_te5_enderium", () -> new JetpackItem(JetpackType.TE5_ARMORED));

    public static final RegistryObject<JetpackItem> JETPACK_IE1 = ITEMS.register("jetpack_ie1", () -> new JetpackItem(JetpackType.IE1));
    public static final RegistryObject<JetpackItem> JETPACK_IE1_ARMORED = ITEMS.register("jetpack_ie1_armored", () -> new JetpackItem(JetpackType.IE1_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_IE2 = ITEMS.register("jetpack_ie2", () -> new JetpackItem(JetpackType.IE2));
    public static final RegistryObject<JetpackItem> JETPACK_IE2_ARMORED = ITEMS.register("jetpack_ie2_armored", () -> new JetpackItem(JetpackType.IE2_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_IE3 = ITEMS.register("jetpack_ie3", () -> new JetpackItem(JetpackType.IE3));
    public static final RegistryObject<JetpackItem> JETPACK_IE3_ARMORED = ITEMS.register("jetpack_ie3_armored", () -> new JetpackItem(JetpackType.IE3_ARMORED));

    public static final RegistryObject<SJItem> ARMORPLATING_MEK1 = ITEMS.register("armorplating_mek1", SJItem::new);
    public static final RegistryObject<SJItem> ARMORPLATING_MEK2 = ITEMS.register("armorplating_mek2", SJItem::new);
    public static final RegistryObject<SJItem> ARMORPLATING_MEK3 = ITEMS.register("armorplating_mek3", SJItem::new);
    public static final RegistryObject<SJItem> ARMORPLATING_MEK4 = ITEMS.register("armorplating_mek4", SJItem::new);

    public static final RegistryObject<SJItem> ARMORPLATING_TE1 = ITEMS.register("armorplating_te1", SJItem::new);
    public static final RegistryObject<SJItem> ARMORPLATING_TE2 = ITEMS.register("armorplating_te2", SJItem::new);
    public static final RegistryObject<SJItem> ARMORPLATING_TE3 = ITEMS.register("armorplating_te3", SJItem::new);
    public static final RegistryObject<SJItem> ARMORPLATING_TE4 = ITEMS.register("armorplating_te4", SJItem::new);
    public static final RegistryObject<SJItem> ARMORPLATING_TE5 = ITEMS.register("armorplating_te5", SJItem::new);
    public static final RegistryObject<SJItem> ARMORPLATING_TE5_ENDERIUM = ITEMS.register("armorplating_te5_enderium", SJItem::new);

    public static final RegistryObject<SJItem> ARMORPLATING_IE1 = ITEMS.register("armorplating_ie1", SJItem::new);
    public static final RegistryObject<SJItem> ARMORPLATING_IE2 = ITEMS.register("armorplating_ie2", SJItem::new);
    public static final RegistryObject<SJItem> ARMORPLATING_IE3 = ITEMS.register("armorplating_ie3", SJItem::new);

    public static final RegistryObject<SJItem> THRUSTER_VANILLA1 = ITEMS.register("thruster_vanilla1", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_VANILLA2 = ITEMS.register("thruster_vanilla2", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_VANILLA3 = ITEMS.register("thruster_vanilla3", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_VANILLA4 = ITEMS.register("thruster_vanilla4", SJItem::new);
    
    public static final RegistryObject<SJItem> THRUSTER_MEK1 = ITEMS.register("thruster_mek1", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_MEK2 = ITEMS.register("thruster_mek2", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_MEK3 = ITEMS.register("thruster_mek3", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_MEK4 = ITEMS.register("thruster_mek4", SJItem::new);

    public static final RegistryObject<SJItem> THRUSTER_TE1 = ITEMS.register("thruster_te1", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_TE2 = ITEMS.register("thruster_te2", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_TE3 = ITEMS.register("thruster_te3", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_TE4 = ITEMS.register("thruster_te4", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_TE5 = ITEMS.register("thruster_te5", SJItem::new);

    public static final RegistryObject<SJItem> THRUSTER_IE1 = ITEMS.register("thruster_ie1", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_IE2 = ITEMS.register("thruster_ie2", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_IE3 = ITEMS.register("thruster_ie3", SJItem::new);
    
    public static final RegistryObject<SJItem> UNIT_CRYOTHEUM_EMPTY = ITEMS.register("unit_cryotheum_empty", SJItem::new);
    public static final RegistryObject<SJItem> UNIT_CRYOTHEUM = ITEMS.register("unit_cryotheum", SJItem::new);
    public static final RegistryObject<SJItem> UNIT_GLOWSTONE_EMPTY = ITEMS.register("unit_glowstone_empty", SJItem::new);
    public static final RegistryObject<SJItem> UNIT_GLOWSTONE = ITEMS.register("unit_glowstone", SJItem::new);

    public static final RegistryObject<SJItem> FLUX_CHESTPLATE = ITEMS.register("flux_chestplate", SJItem::new);
}
