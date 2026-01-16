package tomson124.simplyjetpacks.item;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.registries.DeferredRegister;
import tomson124.simplyjetpacks.SimplyJetpacks;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import static net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion.MOD_ID;

public class JetpackArmorMaterial {

	//PILOT_GOGGLES("pilot_goggles", 0, new int[] {0, 0, 0, 0}, 0, () -> Ingredient.of(Items.LEATHER), "item.armor.equip_leather", 0.0f, 0.0f),
	//POTATO("potato", 0, new int[] {0, 0, 0, 0}, 0, () -> Ingredient.of(Items.POTATO), "item.armor.equip_leather", 0.0f, 0.0f),
	//JETPACK("jetpack", 0, new int[] {0, 2, 0, 0}, 10, () -> Ingredient.of(Items.IRON_INGOT), "item.armor.equip_iron", 0.0f, 0.0f),
	//JETPACK_ARMORED("jetpack_armored", 0, new int[] {0, 4, 0, 0}, 10, () -> Ingredient.of(Items.IRON_INGOT), "item.armor.equip_iron", 0.0f, 0.0f),
	//JETPLATE("jetplate", 0, new int[] {0, 12, 0, 0}, 10, () -> Ingredient.of(Items.IRON_INGOT), "item.armor.equip_iron", 3.0f, 3.0f);

	private final String name;
	private final int durability;
	private final int[] damageReductionAmounts;
	private int enchantability;
	private final Lazy<Ingredient> repairIngredient;
	private final String equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private static final int[] max_damage_array = new int[] {13, 15, 16, 11};

	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, SimplyJetpacks.MODID);

	JetpackArmorMaterial(String name, int durability, int[] defense, int enchantability, Supplier<Ingredient> repairIngredient, String equipSound, float toughness, float knockbackResistance) {
		this.name = name;
		this.durability = durability;
		this.damageReductionAmounts = defense;
		this.enchantability = enchantability;
		this.repairIngredient = Lazy.of(repairIngredient);
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
	}

	public static final Holder<ArmorMaterial> POTATO =
			ARMOR_MATERIALS.register("potato", () -> new ArmorMaterial(
					// Determines the defense value of this armor material, depending on what armor piece it is.
					Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
						map.put(ArmorItem.Type.BOOTS, 0);
						map.put(ArmorItem.Type.LEGGINGS, 0);
						map.put(ArmorItem.Type.CHESTPLATE, 0);
						map.put(ArmorItem.Type.HELMET, 1);
						map.put(ArmorItem.Type.BODY, 0);
					}),
					15, SoundEvents.ARMOR_EQUIP_LEATHER,  () -> Ingredient.of(Tags.Items.LEATHERS),
					// Determines the texture locations of the armor to apply when rendering
					// This can also be specified by overriding 'IItemExtension#getArmorTexture' on your item if the armor texture needs to be more dynamic
					List.of(
							// Creates a new armor texture that will be located at:
							// - 'assets/mod_id/textures/models/armor/copper_layer_1.png' for the outer texture
							// - 'assets/mod_id/textures/models/armor/copper_layer_2.png' for the inner texture (only legs)
							new ArmorMaterial.Layer(
									ResourceLocation.fromNamespaceAndPath(MOD_ID, "copper")
							),
							// Creates a new armor texture that will be rendered on top of the previous at:
							// - 'assets/mod_id/textures/models/armor/copper_layer_1_overlay.png' for the outer texture
							// - 'assets/mod_id/textures/models/armor/copper_layer_2_overlay.png' for the inner texture (only legs)
							// 'true' means that the armor material is dyeable; however, the item must also be added to the 'minecraft:dyeable' tag
							new ArmorMaterial.Layer(
									ResourceLocation.fromNamespaceAndPath(MOD_ID, "copper"), "_overlay", true
							)
					), 0, 0
			));

	public static final Holder<ArmorMaterial> PILOT_GOGGLES =
			ARMOR_MATERIALS.register("pilot_goggles", () -> new ArmorMaterial(
					// Determines the defense value of this armor material, depending on what armor piece it is.
					Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
						map.put(ArmorItem.Type.BOOTS, 0);
						map.put(ArmorItem.Type.LEGGINGS, 0);
						map.put(ArmorItem.Type.CHESTPLATE, 0);
						map.put(ArmorItem.Type.HELMET, 1);
						map.put(ArmorItem.Type.BODY, 0);
					}),
					15, SoundEvents.ARMOR_EQUIP_LEATHER,  () -> Ingredient.of(Tags.Items.LEATHERS),
					// Determines the texture locations of the armor to apply when rendering
					// This can also be specified by overriding 'IItemExtension#getArmorTexture' on your item if the armor texture needs to be more dynamic
					List.of(
							// Creates a new armor texture that will be located at:
							// - 'assets/mod_id/textures/models/armor/copper_layer_1.png' for the outer texture
							// - 'assets/mod_id/textures/models/armor/copper_layer_2.png' for the inner texture (only legs)
							new ArmorMaterial.Layer(
									ResourceLocation.fromNamespaceAndPath(MOD_ID, "copper")
							),
							// Creates a new armor texture that will be rendered on top of the previous at:
							// - 'assets/mod_id/textures/models/armor/copper_layer_1_overlay.png' for the outer texture
							// - 'assets/mod_id/textures/models/armor/copper_layer_2_overlay.png' for the inner texture (only legs)
							// 'true' means that the armor material is dyeable; however, the item must also be added to the 'minecraft:dyeable' tag
							new ArmorMaterial.Layer(
									ResourceLocation.fromNamespaceAndPath(MOD_ID, "copper"), "_overlay", true
							)
					), 0, 0
			));

	public static final Holder<ArmorMaterial> JETPACK =
			ARMOR_MATERIALS.register("jetpack_Only", () -> new ArmorMaterial(
					// Determines the defense value of this armor material, depending on what armor piece it is.
					Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
						map.put(ArmorItem.Type.BOOTS, 0);
						map.put(ArmorItem.Type.LEGGINGS, 0);
						map.put(ArmorItem.Type.CHESTPLATE, 2);
						map.put(ArmorItem.Type.HELMET, 1);
						map.put(ArmorItem.Type.BODY, 0);
					}),
					15, SoundEvents.ARMOR_EQUIP_IRON,  () -> Ingredient.of(Tags.Items.INGOTS_IRON),
					// Determines the texture locations of the armor to apply when rendering
					// This can also be specified by overriding 'IItemExtension#getArmorTexture' on your item if the armor texture needs to be more dynamic
					List.of(
							// Creates a new armor texture that will be located at:
							// - 'assets/mod_id/textures/models/armor/copper_layer_1.png' for the outer texture
							// - 'assets/mod_id/textures/models/armor/copper_layer_2.png' for the inner texture (only legs)
							new ArmorMaterial.Layer(
									ResourceLocation.fromNamespaceAndPath(MOD_ID, "copper")
							),
							// Creates a new armor texture that will be rendered on top of the previous at:
							// - 'assets/mod_id/textures/models/armor/copper_layer_1_overlay.png' for the outer texture
							// - 'assets/mod_id/textures/models/armor/copper_layer_2_overlay.png' for the inner texture (only legs)
							// 'true' means that the armor material is dyeable; however, the item must also be added to the 'minecraft:dyeable' tag
							new ArmorMaterial.Layer(
									ResourceLocation.fromNamespaceAndPath(MOD_ID, "copper"), "_overlay", true
							)
					), 0, 0
			));

	public static final Holder<ArmorMaterial> JETPACK_ARMORED =
			ARMOR_MATERIALS.register("jetpack_ARMORED", () -> new ArmorMaterial(
					// Determines the defense value of this armor material, depending on what armor piece it is.
					Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
						map.put(ArmorItem.Type.BOOTS, 0);
						map.put(ArmorItem.Type.LEGGINGS, 0);
						map.put(ArmorItem.Type.CHESTPLATE, 6);
						map.put(ArmorItem.Type.HELMET, 0);
						map.put(ArmorItem.Type.BODY, 0);
					}),
					15, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(Tags.Items.INGOTS_IRON),
					// Determines the texture locations of the armor to apply when rendering
					// This can also be specified by overriding 'IItemExtension#getArmorTexture' on your item if the armor texture needs to be more dynamic
					List.of(
							// Creates a new armor texture that will be located at:
							// - 'assets/mod_id/textures/models/armor/copper_layer_1.png' for the outer texture
							// - 'assets/mod_id/textures/models/armor/copper_layer_2.png' for the inner texture (only legs)
							new ArmorMaterial.Layer(
									ResourceLocation.fromNamespaceAndPath(MOD_ID, "copper")
							),
							// Creates a new armor texture that will be rendered on top of the previous at:
							// - 'assets/mod_id/textures/models/armor/copper_layer_1_overlay.png' for the outer texture
							// - 'assets/mod_id/textures/models/armor/copper_layer_2_overlay.png' for the inner texture (only legs)
							// 'true' means that the armor material is dyeable; however, the item must also be added to the 'minecraft:dyeable' tag
							new ArmorMaterial.Layer(
									ResourceLocation.fromNamespaceAndPath(MOD_ID, "copper"), "_overlay", true
							)
					), 0, 0
			));

	// Outdated: uses non-both slot logic
	public static void setStats(JetpackArmorMaterial armor, boolean isArmored, int enchant, int defense) {
		defense = isArmored ? defense : (defense - 1) / 2;
		armor.enchantability = enchant;
		armor.damageReductionAmounts[EquipmentSlot.CHEST.getIndex()] = defense;
	}
}