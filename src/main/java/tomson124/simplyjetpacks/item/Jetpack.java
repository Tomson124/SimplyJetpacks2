package tomson124.simplyjetpacks.item;

import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.locale.Language;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.commons.lang3.StringUtils;
import tomson124.simplyjetpacks.SimplyJetpacks;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Jetpack {
    private static final ResourceLocation ATTRIBUTE_ID = ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID,"armor.jetpack");

    public static final Jetpack UNDEFINED = new Jetpack("undefined", 0, 0, 0, "null", 0F, 0F).setCurios(false);

    public static final StreamCodec<FriendlyByteBuf, Jetpack> STREAM_CODEC = StreamCodec.of(Jetpack::encode, Jetpack::read);

    private final ResourceLocation id;
    public String name;
    public String displayName;
    public int tier;
    public int armorPoints;
    public int enchantablilty;
    public String craftingMaterialString;
    private Ingredient craftingMaterial;
    public JetpackItem item;
    public boolean creative = false;
    public boolean disabled = false;
    public Rarity rarity = Rarity.COMMON;
    public float toughness;
    public float knockbackResistance;
    public boolean curios = true;

    public int capacity;
    public int usage;
    public double speedVert;
    public double accelVert;
    public double speedSide;
    public double speedHover;
    public double speedHoverSlow;
    public double sprintSpeedModifier;
    public double sprintEnergyModifier;

    public Jetpack(String name, int tier, int armorPoints, int enchantability, String craftingMaterialString, float toughness, float knockbackResistance) {
        this.id = ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID,name);
        this.name = name;
        this.displayName = this.makeDisplayName();
        this.tier = tier;
        this.armorPoints = armorPoints;
        this.enchantablilty = enchantability;
        this.craftingMaterialString = craftingMaterialString;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
    }

    public void setStats(int capacity, int usage, double speedVert, double accelVert, double speedSide, double speedHover, double speedHoverSlow, double sprintSpeedModifier, double sprintEnergyModifier) {
        this.capacity = capacity;
        this.usage = usage;
        this.speedVert = speedVert;
        this.accelVert = accelVert;
        this.speedSide = speedSide;
        this.speedHover = speedHover;
        this.speedHoverSlow = speedHoverSlow;
        this.sprintSpeedModifier = sprintSpeedModifier;
        this.sprintEnergyModifier = sprintEnergyModifier;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public Jetpack setCreative() {
        this.creative = true;
        this.tier = -1;
        this.rarity = Rarity.EPIC;

        return this;
    }

    public Jetpack setCreative(boolean set) {
        if (set) this.setCreative();
        return this;
    }

    public Jetpack setDisabled() {
        this.disabled = true;
        return this;
    }

    public Jetpack setDisabled(boolean set) {
        if (set) this.setDisabled();
        return this;
    }

    public Jetpack setRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public Jetpack setCurios(boolean curios) {
        this.curios = curios;
        return this;
    }

    public int getTier() {
        return this.tier;
    }

    public Ingredient getCraftingMaterial() {
        if (this.craftingMaterial == null) {
            this.craftingMaterial = Ingredient.EMPTY;

            if (!this.craftingMaterialString.equalsIgnoreCase("null")) {
                var parts = craftingMaterialString.split(":");
                if (parts.length >= 3 && this.craftingMaterialString.startsWith("tag:")) {
                    var tag = ItemTags.create(ResourceLocation.fromNamespaceAndPath(parts[1], parts[2]));
                    this.craftingMaterial = Ingredient.of(tag);
                } else if (parts.length >= 2) {
                    BuiltInRegistries.ITEM.getOptional(ResourceLocation.fromNamespaceAndPath(parts[0], parts[1]))
                            .ifPresent(value -> this.craftingMaterial = Ingredient.of(value));
                }
            }
        }

        return this.craftingMaterial;
    }

    public Component getDisplayName() {
        var key = String.format("jetpack.%s.name", this.name.replaceAll(" ", "_"));
        if (Language.getInstance().has(key)) {
            return Component.translatable(key);
        }

        return Component.literal(this.displayName);
    }

    public ItemAttributeModifiers createAttributeModifiers() {
        var modifiers = ItemAttributeModifiers.builder();

        modifiers.add(Attributes.ARMOR, new AttributeModifier(ATTRIBUTE_ID, this.armorPoints, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);
        modifiers.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ATTRIBUTE_ID, this.toughness, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);

        if (this.knockbackResistance > 0) {
            modifiers.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ATTRIBUTE_ID, this.knockbackResistance, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);
        }

        return modifiers.build();
    }

    private String makeDisplayName() {
        var parts = this.name.replaceAll(" ", "_").split("_");
        return Arrays.stream(parts).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public void write(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.name);
        buffer.writeBoolean(this.disabled);
        buffer.writeVarInt(this.tier);
        buffer.writeVarInt(this.armorPoints);
        buffer.writeVarInt(this.enchantablilty);
        buffer.writeUtf(this.craftingMaterialString);
        buffer.writeBoolean(this.creative);
        buffer.writeVarInt(this.rarity.ordinal());
        buffer.writeFloat(this.toughness);
        buffer.writeFloat(this.knockbackResistance);

        buffer.writeVarInt(this.capacity);
        buffer.writeVarInt(this.usage);
        buffer.writeDouble(this.speedVert);
        buffer.writeDouble(this.accelVert);
        buffer.writeDouble(this.speedSide);
        buffer.writeDouble(this.speedHover);
        buffer.writeDouble(this.speedHoverSlow);
        buffer.writeDouble(this.sprintSpeedModifier);
        buffer.writeDouble(this.sprintEnergyModifier);
    }

    public static void encode(FriendlyByteBuf buffer, Jetpack jetpack) {
        jetpack.write(buffer);
    }

    public static Jetpack read(FriendlyByteBuf buffer) {
        var name = buffer.readUtf();
        var disabled = buffer.readBoolean();
        var tier = buffer.readVarInt();
        var armorPoints = buffer.readVarInt();
        var enchantability = buffer.readVarInt();
        var craftingMaterialString = buffer.readUtf();
        var creative = buffer.readBoolean();
        var rarity = Rarity.values()[buffer.readVarInt()];
        var toughness = buffer.readFloat();
        var knockbackResistance = buffer.readFloat();

        var jetpack = new Jetpack(name, tier, armorPoints, enchantability, craftingMaterialString, toughness, knockbackResistance)
                .setRarity(rarity)
                .setCreative(creative)
                .setDisabled(disabled);

        var capacity = buffer.readVarInt();
        var usage = buffer.readVarInt();
        var speedVert = buffer.readDouble();
        var accelVert = buffer.readDouble();
        var speedSide = buffer.readDouble();
        var speedHover = buffer.readDouble();
        var speedHoverSlow = buffer.readDouble();
        var sprintSpeed = buffer.readDouble();
        var sprintFuel = buffer.readDouble();

        jetpack.setStats(capacity, usage, speedVert, accelVert, speedSide, speedHover, speedHoverSlow, sprintSpeed, sprintFuel);

        return jetpack;
    }

    public static Jetpack fromEnum(JetpackType type) {
        var name = type.getName();
        var disable = type.isDisabled();
        var tier = type.getTier();
        var armorPoints = 0; // TEST
        var enchantability = 0; // TEST
        var craftingMaterialString = type.getCraftingMaterial();
        var creative = type.isCreative();
        var rarity = type.getRarity();
        var toughness = type.getToughness();
        var knockbackResistance = type.getKnockback();

        var jetpack = new Jetpack(name, tier, armorPoints, enchantability, craftingMaterialString, toughness, knockbackResistance)
                .setRarity(rarity)
                .setCreative(creative)
                .setDisabled(disable);

        var capacity = type.getEnergyCapacity();
        var usage = type.getEnergyUsage();
        var speedVert = type.getSpeedVertical();
        var accelVert = type.getAccelVertical();
        var speedSide = type.getSpeedSideways();
        var speedHover = type.getSpeedVerticalHover();
        var speedHoverSlow = type.getSpeedVerticalHoverSlow();
        var sprintSpeedModifier = type.getSprintSpeedModifier();
        var sprintEnergyModifier = type.getSprintSpeedModifier();

        jetpack.setStats(capacity, usage, speedVert, accelVert, speedSide, speedHover, speedHoverSlow, sprintSpeedModifier, sprintEnergyModifier);

        return jetpack;
    }
}
