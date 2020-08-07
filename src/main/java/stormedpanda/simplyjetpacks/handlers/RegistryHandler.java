package stormedpanda.simplyjetpacks.handlers;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.enchantments.EnchantmentFuelEfficiency;
import stormedpanda.simplyjetpacks.items.JetpackItem;
import stormedpanda.simplyjetpacks.items.JetpackType;
import stormedpanda.simplyjetpacks.items.MetaItem;
import stormedpanda.simplyjetpacks.items.PilotGogglesItem;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SimplyJetpacks.MODID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, SimplyJetpacks.MODID);
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SimplyJetpacks.MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SimplyJetpacks.MODID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    // Enchantments:
    public static final RegistryObject<EnchantmentFuelEfficiency> FUEL_EFFICIENCY = ENCHANTMENTS.register("fuel_efficiency", EnchantmentFuelEfficiency::new);

    // Recipe Serializers:

    // Simply Jetpacks:
    public static final RegistryObject<Item> PILOT_GOGGLES = ITEMS.register("pilot_goggles", PilotGogglesItem::new);
    public static final RegistryObject<Item> LEATHER_STRAP = ITEMS.register("leather_strap", () ->
            new Item(new Item.Properties().group(SimplyJetpacks.tabSimplyJetpacks)));
    public static final RegistryObject<Item> JETPACK_POTATO = ITEMS.register("jetpack_potato", () ->
            new Item(new Item.Properties().group(SimplyJetpacks.tabSimplyJetpacks)));
    public static final RegistryObject<Item> PARTICLE_NONE = ITEMS.register("particle_none", () ->
            new Item(new Item.Properties().group(SimplyJetpacks.tabSimplyJetpacks)));
    public static final RegistryObject<Item> PARTICLE_DEFAULT = ITEMS.register("particle_default", () ->
            new Item(new Item.Properties().group(SimplyJetpacks.tabSimplyJetpacks)));
    public static final RegistryObject<Item> PARTICLE_SMOKE = ITEMS.register("particle_smoke", () ->
            new Item(new Item.Properties().group(SimplyJetpacks.tabSimplyJetpacks)));
    public static final RegistryObject<Item> PARTICLE_RAINBOW = ITEMS.register("particle_rainbow", () ->
            new Item(new Item.Properties().group(SimplyJetpacks.tabSimplyJetpacks)));
    
    public static final RegistryObject<JetpackItem> JETPACK_CREATIVE = ITEMS.register("jetpack_creative", () ->
            new JetpackItem(JetpackType.CREATIVE));
    public static final RegistryObject<JetpackItem> JETPACK_CREATIVE_ARMORED = ITEMS.register("jetpack_creative_armored", () ->
            new JetpackItem(JetpackType.CREATIVE_ARMORED));

    // Vanilla:
    public static final RegistryObject<MetaItem> THRUSTER_VANILLA1 = ITEMS.register("thruster_vanilla1", () ->
            new MetaItem("vanilla"));
    public static final RegistryObject<MetaItem> THRUSTER_VANILLA2 = ITEMS.register("thruster_vanilla2", () ->
            new MetaItem("vanilla"));
    public static final RegistryObject<MetaItem> THRUSTER_VANILLA3 = ITEMS.register("thruster_vanilla3", () ->
            new MetaItem("vanilla"));
    public static final RegistryObject<MetaItem> THRUSTER_VANILLA4 = ITEMS.register("thruster_vanilla4", () ->
            new MetaItem("vanilla"));

    public static final RegistryObject<JetpackItem> JETPACK_VANILLA1 = ITEMS.register("jetpack_vanilla1", () ->
            new JetpackItem(JetpackType.VANILLA1));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA1_ARMORED = ITEMS.register("jetpack_vanilla1_armored", () ->
            new JetpackItem(JetpackType.VANILLA1_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA2 = ITEMS.register("jetpack_vanilla2", () ->
            new JetpackItem(JetpackType.VANILLA2));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA2_ARMORED = ITEMS.register("jetpack_vanilla2_armored", () ->
            new JetpackItem(JetpackType.VANILLA2_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA3 = ITEMS.register("jetpack_vanilla3", () ->
            new JetpackItem(JetpackType.VANILLA3));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA3_ARMORED = ITEMS.register("jetpack_vanilla3_armored", () ->
            new JetpackItem(JetpackType.VANILLA3_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA4 = ITEMS.register("jetpack_vanilla4", () ->
            new JetpackItem(JetpackType.VANILLA4));
    public static final RegistryObject<JetpackItem> JETPACK_VANILLA4_ARMORED = ITEMS.register("jetpack_vanilla4_armored", () ->
            new JetpackItem(JetpackType.VANILLA4_ARMORED));

    // Immersive Engineering:
    public static final RegistryObject<MetaItem> THRUSTER_IE1 = ITEMS.register("thruster_ie1", () ->
            new MetaItem("ie"));
    public static final RegistryObject<MetaItem> THRUSTER_IE2 = ITEMS.register("thruster_ie2", () ->
            new MetaItem("ie"));
    public static final RegistryObject<MetaItem> THRUSTER_IE3 = ITEMS.register("thruster_ie3", () ->
            new MetaItem("ie"));
    public static final RegistryObject<MetaItem> ARMORPLATING_IE1 = ITEMS.register("armorplating_ie1", () ->
            new MetaItem("ie"));
    public static final RegistryObject<MetaItem> ARMORPLATING_IE2 = ITEMS.register("armorplating_ie2", () ->
            new MetaItem("ie"));
    public static final RegistryObject<MetaItem> ARMORPLATING_IE3 = ITEMS.register("armorplating_ie3", () ->
            new MetaItem("ie"));

    public static final RegistryObject<JetpackItem> JETPACK_IE1 = ITEMS.register("jetpack_ie1", () ->
            new JetpackItem(JetpackType.IE1));
    public static final RegistryObject<JetpackItem> JETPACK_IE1_ARMORED = ITEMS.register("jetpack_ie1_armored", () ->
            new JetpackItem(JetpackType.IE1_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_IE2 = ITEMS.register("jetpack_ie2", () ->
            new JetpackItem(JetpackType.IE2));
    public static final RegistryObject<JetpackItem> JETPACK_IE2_ARMORED = ITEMS.register("jetpack_ie2_armored", () ->
            new JetpackItem(JetpackType.IE2_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_IE3 = ITEMS.register("jetpack_ie3", () ->
            new JetpackItem(JetpackType.IE3));
    public static final RegistryObject<JetpackItem> JETPACK_IE3_ARMORED = ITEMS.register("jetpack_ie3_armored", () ->
            new JetpackItem(JetpackType.IE3_ARMORED));

    // Mekanism:
    public static final RegistryObject<MetaItem> THRUSTER_MEK1 = ITEMS.register("thruster_mek1", () ->
            new MetaItem("mek"));
    public static final RegistryObject<MetaItem> THRUSTER_MEK2 = ITEMS.register("thruster_mek2", () ->
            new MetaItem("mek"));
    public static final RegistryObject<MetaItem> THRUSTER_MEK3 = ITEMS.register("thruster_mek3", () ->
            new MetaItem("mek"));
    public static final RegistryObject<MetaItem> THRUSTER_MEK4 = ITEMS.register("thruster_mek4", () ->
            new MetaItem("mek"));
    public static final RegistryObject<MetaItem> ARMORPLATING_MEK1 = ITEMS.register("armorplating_mek1", () ->
            new MetaItem("mek"));
    public static final RegistryObject<MetaItem> ARMORPLATING_MEK2 = ITEMS.register("armorplating_mek2", () ->
            new MetaItem("mek"));
    public static final RegistryObject<MetaItem> ARMORPLATING_MEK3 = ITEMS.register("armorplating_mek3", () ->
            new MetaItem("mek"));
    public static final RegistryObject<MetaItem> ARMORPLATING_MEK4 = ITEMS.register("armorplating_mek4", () ->
            new MetaItem("mek"));

    public static final RegistryObject<JetpackItem> JETPACK_MEK1 = ITEMS.register("jetpack_mek1", () ->
            new JetpackItem(JetpackType.MEK1));
    public static final RegistryObject<JetpackItem> JETPACK_MEK1_ARMORED = ITEMS.register("jetpack_mek1_armored", () ->
            new JetpackItem(JetpackType.MEK1_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_MEK2 = ITEMS.register("jetpack_mek2", () ->
            new JetpackItem(JetpackType.MEK2));
    public static final RegistryObject<JetpackItem> JETPACK_MEK2_ARMORED = ITEMS.register("jetpack_mek2_armored", () ->
            new JetpackItem(JetpackType.MEK2_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_MEK3 = ITEMS.register("jetpack_mek3", () ->
            new JetpackItem(JetpackType.MEK3));
    public static final RegistryObject<JetpackItem> JETPACK_MEK3_ARMORED = ITEMS.register("jetpack_mek3_armored", () ->
            new JetpackItem(JetpackType.MEK3_ARMORED));
    public static final RegistryObject<JetpackItem> JETPACK_MEK4 = ITEMS.register("jetpack_mek4", () ->
            new JetpackItem(JetpackType.MEK4));
    public static final RegistryObject<JetpackItem> JETPACK_MEK4_ARMORED = ITEMS.register("jetpack_mek4_armored", () ->
            new JetpackItem(JetpackType.MEK4_ARMORED));
}
