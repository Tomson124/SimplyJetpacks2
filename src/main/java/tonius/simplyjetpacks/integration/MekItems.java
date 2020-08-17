package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class MekItems {
    // Universal Cables
    @GameRegistry.ItemStackHolder(value = "mekanism:transmitter", nbt = "{tier:0}")
    public static ItemStack cableBasic;
    @GameRegistry.ItemStackHolder(value = "mekanism:transmitter", nbt = "{tier:1}")
    public static ItemStack cableAdvanced;
    @GameRegistry.ItemStackHolder(value = "mekanism:transmitter", nbt = "{tier:2}")
    public static ItemStack cableElite;
    @GameRegistry.ItemStackHolder(value = "mekanism:transmitter", nbt = "{tier:3}")
    public static ItemStack cableUltimate;

    // Energy Cubes
    @GameRegistry.ItemStackHolder(value = "mekanism:energycube", nbt = "{tier:0}")
    public static ItemStack cubeBasic;
    @GameRegistry.ItemStackHolder(value = "mekanism:energycube", nbt = "{tier:1}")
    public static ItemStack cubeAdvanced;
    @GameRegistry.ItemStackHolder(value = "mekanism:energycube", nbt = "{tier:2}")
    public static ItemStack cubeElite;
    @GameRegistry.ItemStackHolder(value = "mekanism:energycube", nbt = "{tier:3}")
    public static ItemStack cubeUltimate;
    // Circuits
    @GameRegistry.ItemStackHolder(value = "mekanism:controlcircuit", meta = 0)
    public static ItemStack circuitBasic;
    @GameRegistry.ItemStackHolder(value = "mekanism:controlcircuit", meta = 1)
    public static ItemStack circuitAdvanced;
    @GameRegistry.ItemStackHolder(value = "mekanism:controlcircuit", meta = 2)
    public static ItemStack circuitElite;
    @GameRegistry.ItemStackHolder(value = "mekanism:controlcircuit", meta = 3)
    public static ItemStack circuitUltimate;
    // Alloys
    @GameRegistry.ItemStackHolder("mekanism:enrichedalloy")
    public static ItemStack alloyEnriched;
    @GameRegistry.ItemStackHolder("mekanism:reinforcedalloy")
    public static ItemStack alloyReinforced;
    @GameRegistry.ItemStackHolder("mekanism:atomicalloy")
    public static ItemStack alloyAtomic;
}