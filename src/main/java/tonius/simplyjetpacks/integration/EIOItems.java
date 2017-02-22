package tonius.simplyjetpacks.integration;

import tonius.simplyjetpacks.SimplyJetpacks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class EIOItems {
	public static ItemStack capacitorBankOld;
	public static ItemStack capacitorBankBasic;
	public static ItemStack capacitorBank;
	public static ItemStack capacitorBankVibrant;
	public static ItemStack redstoneConduit;
	public static ItemStack energyConduit1;
	public static ItemStack energyConduit2;
	public static ItemStack energyConduit3;
	public static ItemStack basicCapacitor;
	public static ItemStack doubleCapacitor;
	public static ItemStack octadicCapacitor;
	public static ItemStack machineChassis;
	public static ItemStack basicGear;
	public static ItemStack pulsatingCrystal;
	public static ItemStack vibrantCrystal;
	public static ItemStack enderCrystal;

	public static void init() {
		SimplyJetpacks.logger.info("Stealing Ender IO's items");

		capacitorBankOld = new ItemStack(Block.REGISTRY.getObject(new ResourceLocation("enderio", "blockCapacitorBank")));

		Block capBankBlock = Block.REGISTRY.getObject(new ResourceLocation("enderio", "blockCapBank"));
		capacitorBankBasic = new ItemStack(capBankBlock, 1, 1);
		capacitorBank = new ItemStack(capBankBlock, 1, 2);
		capacitorBankVibrant = new ItemStack(capBankBlock, 1, 3);

		Item redstoneConduitItem = Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemRedstoneConduit"));
		if (redstoneConduitItem != null) {
			redstoneConduit = new ItemStack(redstoneConduitItem, 1, 0);
		}

		Item energyConduitItem = Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemPowerConduit"));
		if (energyConduitItem != null) {
			energyConduit1 = new ItemStack(energyConduitItem, 1, 0);
			energyConduit2 = new ItemStack(energyConduitItem, 1, 1);
			energyConduit3 = new ItemStack(energyConduitItem, 1, 2);
		}

		Item capacitorItem = Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemBasicCapacitor"));
		if (capacitorItem != null) {
			basicCapacitor = new ItemStack(capacitorItem, 1, 0);
			doubleCapacitor = new ItemStack(capacitorItem, 1, 1);
			octadicCapacitor = new ItemStack(capacitorItem, 1, 2);
		}

		Item machinePartItem = Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemMachinePart"));
		if (machinePartItem != null) {
			machineChassis = new ItemStack(machinePartItem, 1, 0);
			basicGear = new ItemStack(machinePartItem, 1, 1);
		}

		Item materialsItem = Item.REGISTRY.getObject(new ResourceLocation("enderio", "itemMaterial"));
		if (materialsItem != null) {
			pulsatingCrystal = new ItemStack(materialsItem, 1, 5);
			vibrantCrystal = new ItemStack(materialsItem, 1, 6);
			enderCrystal = new ItemStack(materialsItem, 1, 8);
		}
	}
}