package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class EIOItems {

	//capacitor banks
	@GameRegistry.ItemStackHolder(value = "enderio:block_cap_bank", meta = 1)
	public static ItemStack capacitorBankBasic;
	@GameRegistry.ItemStackHolder(value = "enderio:block_cap_bank", meta = 2)
	public static ItemStack capacitorBank;
	@GameRegistry.ItemStackHolder(value = "enderio:block_cap_bank", meta = 3)
	public static ItemStack capacitorBankVibrant;

	//conduits
	@GameRegistry.ItemStackHolder("enderio:item_redstone_conduit")
	public static ItemStack redstoneConduit;
	@GameRegistry.ItemStackHolder(value = "enderio:item_power_conduit", meta = 0)
	public static ItemStack energyConduit1;
	@GameRegistry.ItemStackHolder(value = "enderio:item_power_conduit", meta = 1)
	public static ItemStack energyConduit2;
	@GameRegistry.ItemStackHolder(value = "enderio:item_power_conduit", meta = 2)
	public static ItemStack energyConduit3;

	//capacitor items
	@GameRegistry.ItemStackHolder(value = "enderio:item_basic_capacitor", meta = 0)
	public static ItemStack basicCapacitor;
	@GameRegistry.ItemStackHolder(value = "enderio:item_basic_capacitor", meta = 1)
	public static ItemStack doubleCapacitor;
	@GameRegistry.ItemStackHolder(value = "enderio:item_basic_capacitor", meta = 2)
	public static ItemStack octadicCapacitor;

	//crafting materials
	@GameRegistry.ItemStackHolder(value = "enderio:item_material", meta = 1)
	public static ItemStack machineChassis;
	@GameRegistry.ItemStackHolder(value = "enderio:item_material", meta = 9)
	public static ItemStack basicGear;
	@GameRegistry.ItemStackHolder(value = "enderio:item_material", meta = 14)
	public static ItemStack pulsatingCrystal;
	@GameRegistry.ItemStackHolder(value = "enderio:item_material", meta = 15)
	public static ItemStack vibrantCrystal;
	@GameRegistry.ItemStackHolder(value = "enderio:item_material", meta = 16)
	public static ItemStack enderCrystal;

}