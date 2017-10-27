package tonius.simplyjetpacks.integration;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.*;
import tonius.simplyjetpacks.Log;
import tonius.simplyjetpacks.SimplyJetpacks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class TEItems {

	public static Item capacitor = null;
	public static Block dynamo = null;
	public static Item material = null;
	public static Item cell = null;
	public static ItemStack capacitorBasic = null;
	public static ItemStack capacitorHardened = null;
	public static ItemStack capacitorReinforced = null;
	public static ItemStack capacitorResonant = null;
	public static ItemStack cellBasic = null;
	public static ItemStack cellReinforced = null;
	public static ItemStack bucketRedstone = null;
	public static ItemStack dynamoReactant = null;
	public static ItemStack dynamoMagmatic = null;
	public static ItemStack dynamoEnervation = null;
	public static ItemStack dynamoSteam = null;
	public static ItemStack frameIlluminator = null;
	public static ItemStack pneumaticServo = null;
	public static ItemStack powerCoilElectrum = null;
	public static ItemStack powerCoilGold = null;
	public static ItemStack cellResonant;

	public static void init() {
		SimplyJetpacks.logger.info("Stealing Thermal Expansion's items");

		capacitor = Item.REGISTRY.getObject(new ResourceLocation("thermalexpansion", "capacitor"));
		if (capacitor != null) {
			capacitorBasic = new ItemStack(capacitor, 1, 0);
			capacitorHardened = new ItemStack(capacitor, 1, 1);
			capacitorReinforced = new ItemStack(capacitor, 1, 2);
			capacitorResonant = new ItemStack(capacitor, 1, 4);
		}

		dynamo = Block.REGISTRY.getObject(new ResourceLocation("thermalexpansion", "dynamo"));
		if (dynamo != null) {
			dynamoSteam = new ItemStack(dynamo, 1, 0);
			dynamoMagmatic = new ItemStack(dynamo, 1, 1);
			dynamoReactant = new ItemStack(dynamo, 1, 3);
			dynamoEnervation = new ItemStack(dynamo, 1, 4);
		}

		material = Item.REGISTRY.getObject(new ResourceLocation("thermalfoundation", "material"));
		if (material != null) {
			powerCoilGold = new ItemStack(material, 1, 513);
			powerCoilElectrum = new ItemStack(material, 1, 515);
		}

		cell = Item.REGISTRY.getObject(new ResourceLocation("thermalexpansion", "cell"));
		if (cell != null) {
			cellBasic = new ItemStack(cell);
			cellBasic.setTagCompound(new NBTTagCompound());
			cellBasic.getTagCompound().setByte("Level", (byte) 0);

			cellReinforced = new ItemStack(cell);
			cellReinforced.setTagCompound(new NBTTagCompound());
			cellReinforced.getTagCompound().setByte("Level", (byte) 2);

			cellResonant = new ItemStack(cell);
			cellResonant.setTagCompound(new NBTTagCompound());
			cellResonant.getTagCompound().setByte("Level", (byte) 4);
		}

		/*
		frameIlluminator = GameRegistry.findItemStack("ThermalExpansion", "frameIlluminator", 1);
		pneumaticServo = GameRegistry.findItemStack("ThermalExpansion", "pneumaticServo", 1);
		*/
	}

	public static void initFluids() {
		Fluid redstone = FluidRegistry.getFluid("redstone");
		bucketRedstone = FluidUtil.getFilledBucket(new FluidStack(redstone, Fluid.BUCKET_VOLUME));
	}
}
