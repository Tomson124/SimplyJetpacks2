package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class MekItems {
    // TODO: Add NBT data to ItemStackHolders
    // Universal Cables
    @GameRegistry.ItemStackHolder(value = "mekanism:transmitter", meta = 0, nbt = "")
    public static ItemStack hempFabric;
    @GameRegistry.ItemStackHolder(value = "mekanism:transmitter", meta = 0, nbt = "")
    public static ItemStack componentIron;
    @GameRegistry.ItemStackHolder(value = "mekanism:transmitter", meta = 0, nbt = "")
    public static ItemStack electronTube;
    @GameRegistry.ItemStackHolder(value = "mekanism:transmitter", meta = 0, nbt = "")
    public static ItemStack circuitBoard;
    // Energy Cubes
    @GameRegistry.ItemStackHolder(value = "mekanism:energycube", meta = 0, nbt = "")
    public static ItemStack wirecoilCopper;
    @GameRegistry.ItemStackHolder(value = "mekanism:energycube", meta = 0, nbt = "")
    public static ItemStack wirecoilElectrum;
    @GameRegistry.ItemStackHolder(value = "mekanism:energycube", meta = 0, nbt = "")
    public static ItemStack wirecoilSteel;
    @GameRegistry.ItemStackHolder(value = "mekanism:energycube", meta = 0, nbt = "")
    public static ItemStack coilLv;
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