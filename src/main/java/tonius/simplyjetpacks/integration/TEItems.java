package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tonius.simplyjetpacks.SimplyJetpacks;

public abstract class TEItems {

	// Capacitors
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:capacitor", meta = 0)
	public static ItemStack capacitorBasic = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:capacitor", meta = 1)
	public static ItemStack capacitorHardened = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:capacitor", meta = 2)
	public static ItemStack capacitorReinforced = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:capacitor", meta = 4)
	public static ItemStack capacitorResonant = null;

	// Cells
	@GameRegistry.ItemStackHolder("thermalexpansion:cell")
	public static ItemStack cellBasic = null;
	@GameRegistry.ItemStackHolder("thermalexpansion:cell")
	public static ItemStack cellReinforced = null;
	@GameRegistry.ItemStackHolder("thermalexpansion:cell")
	public static ItemStack cellResonant;

	// Dynamos
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:dynamo", meta = 1)
	public static ItemStack dynamoMagmatic = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:dynamo", meta = 3)
	public static ItemStack dynamoReactant = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:dynamo", meta = 4)
	public static ItemStack dynamoEnervation = null;
	@GameRegistry.ItemStackHolder(value = "thermalexpansion:dynamo", meta = 5)
	public static ItemStack dynamoNumismatic = null;

	// Crafting Materials
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
		if (cellResonant != null) {
			cellResonant.setTagCompound(new NBTTagCompound());
			cellResonant.getTagCompound().setByte("Level", (byte) 4);
		}
	}

	public static void initFluids() {
		Fluid redstone = FluidRegistry.getFluid("redstone");
		bucketRedstone = FluidUtil.getFilledBucket(new FluidStack(redstone, Fluid.BUCKET_VOLUME));
	}
}
