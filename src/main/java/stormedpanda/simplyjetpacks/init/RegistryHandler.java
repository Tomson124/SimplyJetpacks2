package stormedpanda.simplyjetpacks.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.crafting.JetpackSpecialRecipe;
import stormedpanda.simplyjetpacks.enchantment.EnchantmentFuelEfficiency;
import stormedpanda.simplyjetpacks.energy.TestEnergyItem;
import stormedpanda.simplyjetpacks.energy.TestEnergyItemTwo;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.item.JetpackType;
import stormedpanda.simplyjetpacks.item.PilotGogglesItem;

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

    // Recipes:
    public static final RegistryObject<SpecialRecipeSerializer<JetpackSpecialRecipe>> JETPACK_SPECIAL_RECIPE = RECIPE_SERIALIZERS.register("jetpack_special_recipe",() -> new SpecialRecipeSerializer<>(JetpackSpecialRecipe::new));
    //public static final RegistryObject<IRecipeSerializer<JetpackRecipe>> JETPACK_SHAPED_RECIPE = RECIPE_SERIALIZERS.register("jetpack_shaped_recipe", new JetpackRecipe<>.Serializer());

    // Enchantments:
    public static final RegistryObject<EnchantmentFuelEfficiency> FUEL_EFFICIENCY = ENCHANTMENTS.register("fuel_efficiency", EnchantmentFuelEfficiency::new);

    // Simply Jetpacks:
    public static final RegistryObject<Item> PILOT_GOGGLES = ITEMS.register("pilot_goggles", PilotGogglesItem::new);
    public static final RegistryObject<Item> LEATHER_STRAP = ITEMS.register("leather_strap", () ->
            new Item(new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks)));
    public static final RegistryObject<Item> JETPACK_POTATO = ITEMS.register("jetpack_potato", () ->
            new Item(new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks)));
    public static final RegistryObject<Item> PARTICLE_NONE = ITEMS.register("particle_none", () ->
            new Item(new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks)));
    public static final RegistryObject<Item> PARTICLE_DEFAULT = ITEMS.register("particle_default", () ->
            new Item(new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks)));
    public static final RegistryObject<Item> PARTICLE_SMOKE = ITEMS.register("particle_smoke", () ->
            new Item(new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks)));
    public static final RegistryObject<Item> PARTICLE_RAINBOW = ITEMS.register("particle_rainbow", () ->
            new Item(new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks)));
    
    public static final RegistryObject<JetpackItem> JETPACK_CREATIVE = ITEMS.register("jetpack_creative", () -> new JetpackItem(JetpackType.CREATIVE));
    public static final RegistryObject<JetpackItem> JETPACK_CREATIVE_ARMORED = ITEMS.register("jetpack_creative_armored", () -> new JetpackItem(JetpackType.CREATIVE_ARMORED));

    // testing:
    public static final RegistryObject<TestEnergyItem> TEST_ENERGY_ITEM = ITEMS.register("test_energy_item", TestEnergyItem::new);
    public static final RegistryObject<TestEnergyItemTwo> TEST_ENERGY_ITEM_TWO = ITEMS.register("test_energy_item_two",
            () -> new TestEnergyItemTwo(new Item.Properties().tab(SimplyJetpacks.tabSimplyJetpacks), 1000, 10));
}
