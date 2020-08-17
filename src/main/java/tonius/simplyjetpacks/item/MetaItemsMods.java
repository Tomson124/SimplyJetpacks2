package tonius.simplyjetpacks.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import tonius.simplyjetpacks.setup.ModItems;

import javax.annotation.Nonnull;
import java.util.EnumSet;

public enum MetaItemsMods {

    // Vanilla
    THRUSTER_VANILLA_1("thruster_vanilla1", null, EnumRarity.COMMON),
    THRUSTER_VANILLA_2("thruster_vanilla2", null, EnumRarity.UNCOMMON),
    THRUSTER_VANILLA_3("thruster_vanilla3", null, EnumRarity.RARE),

    // EnderIO
    ARMOR_PLATING_EIO_1("armorplating_eio1", null, EnumRarity.COMMON),
    ARMOR_PLATING_EIO_2("armorplating_eio2", null, EnumRarity.COMMON),
    ARMOR_PLATING_EIO_3("armorplating_eio3", null, EnumRarity.COMMON),
    ARMOR_PLATING_EIO_4("armorplating_eio4", null, EnumRarity.COMMON),
    THRUSTER_EIO_1("thruster_eio1", null, EnumRarity.COMMON),
    THRUSTER_EIO_2("thruster_eio2", null, EnumRarity.COMMON),
    THRUSTER_EIO_3("thruster_eio3", null, EnumRarity.UNCOMMON),
    THRUSTER_EIO_4("thruster_eio4", null, EnumRarity.RARE),
    THRUSTER_EIO_5("thruster_eio5", null, EnumRarity.EPIC),
    INGOT_DARK_SOULARIUM("ingot_dark_soularium", null, EnumRarity.UNCOMMON, true),
    REINFORCED_GLIDER_WING("reinforced_glider_wing", null, EnumRarity.UNCOMMON),
    UNIT_FLIGHT_CONTROL_EMPTY("unit_flight_control_empty", null, EnumRarity.COMMON),
    UNIT_FLIGHT_CONTROL("unit_flight_control", null, EnumRarity.UNCOMMON),

    // Thermal Expansion
    ARMOR_PLATING_TE_1("armorplating_te1", null, EnumRarity.COMMON),
    ARMOR_PLATING_TE_2("armorplating_te2", null, EnumRarity.COMMON),
    ARMOR_PLATING_TE_3("armorplating_te3", null, EnumRarity.COMMON),
    ARMOR_PLATING_TE_4("armorplating_te4", null, EnumRarity.COMMON),
    THRUSTER_TE_1("thruster_te1", null, EnumRarity.COMMON),
    THRUSTER_TE_2("thruster_te2", null, EnumRarity.COMMON),
    THRUSTER_TE_3("thruster_te3", null, EnumRarity.UNCOMMON),
    THRUSTER_TE_4("thruster_te4", null, EnumRarity.RARE),
    THRUSTER_TE_5("thruster_te5", null, EnumRarity.EPIC),
    FLUX_PLATE("flux_plate", null, EnumRarity.UNCOMMON),
    FLUX_ARMOR_PLATING("flux_armorplating", null, EnumRarity.UNCOMMON),
    UNIT_GLOWSTONE_EMPTY("unit_glowstone_empty", null, EnumRarity.COMMON),
    UNIT_GLOWSTONE("unit_glowstone", null, EnumRarity.UNCOMMON),
    UNIT_CRYOTHEUM_EMPTY("unit_cryotheum_empty", null, EnumRarity.COMMON),
    UNIT_CRYOTHEUM("unit_cryotheum", null, EnumRarity.UNCOMMON),

    // Mekanism
    ARMOR_PLATING_MEK_1("armorplating_mek1", null, EnumRarity.COMMON),
    ARMOR_PLATING_MEK_2("armorplating_mek2", null, EnumRarity.COMMON),
    ARMOR_PLATING_MEK_3("armorplating_mek3", null, EnumRarity.COMMON),
    ARMOR_PLATING_MEK_4("armorplating_mek4", null, EnumRarity.COMMON),
    THRUSTER_MEK_1("thruster_mek1", null, EnumRarity.COMMON),
    THRUSTER_MEK_2("thruster_mek2", null, EnumRarity.COMMON),
    THRUSTER_MEK_3("thruster_mek3", null, EnumRarity.UNCOMMON),
    THRUSTER_MEK_4("thruster_mek4", null, EnumRarity.RARE),

    // Immersive Engineering
    ARMOR_PLATING_IE_1("armorplating_ie1", null, EnumRarity.COMMON),
    ARMOR_PLATING_IE_2("armorplating_ie2", null, EnumRarity.COMMON),
    ARMOR_PLATING_IE_3("armorplating_ie3", null, EnumRarity.COMMON),
    THRUSTER_IE_1("thruster_ie1", null, EnumRarity.COMMON),
    THRUSTER_IE_2("thruster_ie2", null, EnumRarity.COMMON),
    THRUSTER_IE_3("thruster_ie3", null, EnumRarity.UNCOMMON);

    private final String name;
    private final String keyTooltip;
    private final EnumRarity rarity;
    private boolean glow;

    public static final EnumSet<MetaItemsMods> ITEMS_VANILLA = EnumSet.range(THRUSTER_VANILLA_1, THRUSTER_VANILLA_3);
    public static final EnumSet<MetaItemsMods> ITEMS_EIO = EnumSet.range(ARMOR_PLATING_EIO_1, UNIT_FLIGHT_CONTROL);
    public static final EnumSet<MetaItemsMods> ITEMS_TE = EnumSet.range(ARMOR_PLATING_TE_1, UNIT_CRYOTHEUM);
    public static final EnumSet<MetaItemsMods> ITEMS_TE_RA = EnumSet.range(THRUSTER_TE_5, UNIT_CRYOTHEUM);
    public static final EnumSet<MetaItemsMods> ITEMS_MEK = EnumSet.range(ARMOR_PLATING_MEK_1, THRUSTER_MEK_4);
    public static final EnumSet<MetaItemsMods> ITEMS_IE = EnumSet.range(ARMOR_PLATING_IE_1, THRUSTER_IE_3);

    MetaItemsMods(String name, String keyTooltip, EnumRarity rarity) {
        this.name = name;
        this.keyTooltip = keyTooltip;
        this.rarity = rarity;
    }

    MetaItemsMods(String name, String keyTooltip, EnumRarity rarity, boolean glow) {
        this(name, keyTooltip, rarity);
        this.glow = glow;
    }

    public static MetaItemsMods getFromName(String s) {
        for (MetaItemsMods meta : values()) {
            if (meta.name.toLowerCase().equals(s.toLowerCase())) {
                return meta;
            }
        }
        return null;
    }

    @Nonnull
    public static MetaItemsMods getTypeFromMeta(int meta) {
        return values()[meta >= 0 && meta < values().length + MetaItemsMods.values().length ? meta : 0];
    }

    @Nonnull
    public ItemStack getStackMetaItem() {
        return getStackMetaItem(1);
    }

    @Nonnull
    public ItemStack getStackMetaItem(int size) {
        return new ItemStack(ModItems.metaItemMods, size, ordinal());
    }

    public String getName() {
        return name;
    }

    public String getKeyTooltip() {
        return keyTooltip;
    }

    public EnumRarity getRarity() {
        return rarity;
    }

    public boolean getGlow() {
        return glow;
    }
}
