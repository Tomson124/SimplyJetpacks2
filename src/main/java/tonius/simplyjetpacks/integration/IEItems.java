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
    // Wire
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:wirecoil", meta = 0)
    public static ItemStack coilLv;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:wirecoil", meta = 1)
    public static ItemStack coilMv;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:wirecoil", meta = 2)
    public static ItemStack coilHv;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:material", meta = 20)
    public static ItemStack wireCopper;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:material", meta = 21)
    public static ItemStack wireElectrum;
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:material", meta = 23)
    public static ItemStack wireSteel;
    // Device
    @GameRegistry.ItemStackHolder(value = "immersiveengineering:metal_device1", meta = 1)
    public static ItemStack furnace_heater;
}