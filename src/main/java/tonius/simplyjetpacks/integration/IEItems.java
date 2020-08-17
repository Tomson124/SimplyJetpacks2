package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class IEItems {
    // Material
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:material", meta = 5)
    public static ItemStack hempFabric;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:material", meta = 8)
    public static ItemStack componentIron;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:material", meta = 26)
    public static ItemStack electronTube;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:material", meta = 27)
    public static ItemStack circuitBoard;
    // Wire/Coil
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:wirecoil", meta = 0)
    public static ItemStack wireCoilLv;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:wirecoil", meta = 1)
    public static ItemStack wireCoilMv;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:wirecoil", meta = 2)
    public static ItemStack wireCoilHv;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:metal_decoration0", meta = 0)
    public static ItemStack coilCopper;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:metal_decoration0", meta = 1)
    public static ItemStack coilElectrum;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:metal_decoration0", meta = 2)
    public static ItemStack coilSteel;
    // Device
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:metal_device1", meta = 1)
    public static ItemStack furnace_heater;
}