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

	//capacitors
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:capacitor", meta = 0)
	public static ItemStack capacitorBasic = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:capacitor", meta = 1)
	public static ItemStack capacitorHardened = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:capacitor", meta = 2)
	public static ItemStack capacitorReinforced = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:capacitor", meta = 4)
	public static ItemStack capacitorResonant = null;

	//cells
	@GameRegistry.ItemStackHolder("thermalexpansion:cell")
	public static ItemStack cellBasic = null;
	@GameRegistry.ItemStackHolder("thermalexpansion:cell")
	public static ItemStack cellReinforced = null;
	@GameRegistry.ItemStackHolder("thermalexpansion:cell")
	public static ItemStack cellResonant;

	//dynamos
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:dynamo", meta = 0)
	public static ItemStack dynamoReactant = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:dynamo", meta = 1)
	public static ItemStack dynamoMagmatic = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:dynamo", meta = 3)
	public static ItemStack dynamoSteam = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:dynamo", meta = 4)
	public static ItemStack dynamoEnervation = null;

	//materials
	@GameRegistry.ItemStackHolder(value = "thermalfoundation:glass_alloy", meta = 5)
	public static ItemStack signalumGlass = null;
	@GameRegistry.ItemStackHolder(value = "thermalfoundation:material", meta = 513)
	public static ItemStack powerCoilGold = null;
	@GameRegistry.ItemStackHolder(value = "thermalfoundation:material", meta = 515)
	public static ItemStack powerCoilElectrum = null;

	public static ItemStack bucketRedstone = null;


	public static void init() {
		SimplyJetpacks.logger.info("Stealing Thermal Expansion's items");

		if (cellBasic != null) {
			cellBasic.setTagCompound(new NBTTagCompound());
			cellBasic.getTagCompound().setByte("Level", (byte) 0);
		}
		if (cellReinforced != null) {
			cellReinforced.setTagCompound(new NBTTagCompound());
			cellReinforced.getTagCompound().setByte("Level", (byte) 2);
		}
		if (cellResonant != null){
			cellResonant.setTagCompound(new NBTTagCompound());
			cellResonant.getTagCompound().setByte("Level", (byte) 4);
		}
	}

	public static void initFluids() {
		Fluid redstone = FluidRegistry.getFluid("redstone");
		bucketRedstone = FluidUtil.getFilledBucket(new FluidStack(redstone, Fluid.BUCKET_VOLUME));
	}
}
