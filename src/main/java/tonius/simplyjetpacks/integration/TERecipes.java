package tonius.simplyjetpacks.integration;

import net.minecraft.item.Item;
import tonius.simplyjetpacks.SimplyJetpacks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public abstract class TERecipes {

	private static final String input = "input";
	private static final String input2 = "input2";
	private static final String output = "output";
	private static final String output2 = "output2";

	public static void addSmelterRecipe(int energy, Item primaryInput, ItemStack secondaryInput, Item primaryOutput, Item secondaryOutput, int chance) {
		SimplyJetpacks.logger.info("Registering TE Induction Smelter recipe");

		NBTTagCompound toSend = new NBTTagCompound();

		toSend.setInteger("energy", energy);
		toSend.setTag(input, new NBTTagCompound());
		toSend.setTag(input2, new NBTTagCompound());
		toSend.setTag(output, new NBTTagCompound());
		toSend.setTag(output2, new NBTTagCompound());

		new ItemStack(primaryInput).writeToNBT(toSend.getCompoundTag(input));
		secondaryInput.writeToNBT(toSend.getCompoundTag(input2));
		new ItemStack(primaryOutput).writeToNBT(toSend.getCompoundTag(output));
		if (secondaryOutput != null) {
			new ItemStack(secondaryOutput).writeToNBT(toSend.getCompoundTag(output2));
			toSend.setInteger("chance", chance);
		}

		FMLInterModComms.sendMessage("thermalexpansion", "AddSmelterRecipe", toSend);
	}

	public static void addTransposerFill(int energy, Item fluidInput, Item fluidOutput, FluidStack fluid, boolean reversible) {
		SimplyJetpacks.logger.info("Registering TE Fluid Transposer fill recipe");

		NBTTagCompound toSend = new NBTTagCompound();

		toSend.setInteger("energy", energy);
		toSend.setTag(input, new NBTTagCompound());
		toSend.setTag(output, new NBTTagCompound());
		toSend.setTag("fluid", new NBTTagCompound());

		new ItemStack(fluidInput).writeToNBT(toSend.getCompoundTag(input));
		new ItemStack(fluidOutput).writeToNBT(toSend.getCompoundTag(output));
		toSend.setBoolean("reversible", reversible);
		fluid.writeToNBT(toSend.getCompoundTag("fluid"));

		FMLInterModComms.sendMessage("thermalexpansion", "AddTransposerFillRecipe", toSend);
	}
}
