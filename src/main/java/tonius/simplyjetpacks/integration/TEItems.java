package tonius.simplyjetpacks.integration;

import tonius.simplyjetpacks.SimplyJetpacks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class TEItems {

	public static Item capacitor = null;
	public static Item dynamo = null;
	public static Item material = null;
	public static ItemStack capacitorBasic = null;
	public static ItemStack capacitorHardened = null;
	public static ItemStack capacitorReinforced = null;
	public static ItemStack capacitorResonant = null;
	public static ItemStack cellBasic = null;
	public static ItemStack bucketRedstone = null;
	public static ItemStack dynamoReactant = null;
	public static ItemStack dynamoMagmatic = null;
	public static ItemStack dynamoEnervation = null;
	public static ItemStack dynamoSteam = null;
	public static ItemStack frameCellReinforcedFull = null;
	public static ItemStack frameIlluminator = null;
	public static ItemStack pneumaticServo = null;
	public static ItemStack powerCoilElectrum = null;
	public static ItemStack powerCoilGold = null;

	public static void init() {
		SimplyJetpacks.logger.info("Stealing Thermal Expansion's items");

		capacitor = Item.REGISTRY.getObject(new ResourceLocation("ThermalExpansion", "capacitor"));
		if (capacitor != null) {
			capacitorBasic = new ItemStack(capacitor, 1, 0);
			capacitorHardened = new ItemStack(capacitor, 1, 1);
			capacitorReinforced = new ItemStack(capacitor, 1, 2);
			capacitorResonant = new ItemStack(capacitor, 1, 4);
		}

		dynamo = Item.REGISTRY.getObject(new ResourceLocation("ThermalExpansion", "dynamo"));
		if (dynamo != null) {
			dynamoSteam = new ItemStack(dynamo, 1, 0);
			dynamoMagmatic = new ItemStack(dynamo, 1, 1);
			dynamoReactant = new ItemStack(dynamo, 1, 3);
			dynamoEnervation = new ItemStack(dynamo, 1, 4);
		}

		material = Item.REGISTRY.getObject(new ResourceLocation("ThermalFoundation", "material"));
		if (material != null) {
			powerCoilGold = new ItemStack(material, 1, 513);
			powerCoilElectrum = new ItemStack(material, 1, 515);
		}

		Fluid redstone = FluidRegistry.getFluid("redstone");
		bucketRedstone = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, redstone);

		/*
		cellBasic = GameRegistry.findItemStack("ThermalExpansion", "cellBasic", 1);
		frameCellReinforcedFull = GameRegistry.findItemStack("ThermalExpansion", "frameCellReinforcedFull", 1);
		frameIlluminator = GameRegistry.findItemStack("ThermalExpansion", "frameIlluminator", 1);
		pneumaticServo = GameRegistry.findItemStack("ThermalExpansion", "pneumaticServo", 1);
		*/
	}
}
