package tonius.simplyjetpacks.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import tonius.simplyjetpacks.SimplyJetpacks;

public abstract class EIORecipes
{
	public static void addAlloySmelterRecipe(String name, int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack tertiaryInput, ItemStack output)
	{
		SimplyJetpacks.logger.info("Registering EIO Alloy Smelter recipe");

		StringBuilder toSend = new StringBuilder();

		toSend.append("<enderio:recipes xmlns:enderio=\"http://enderio.com/recipes\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://enderio.com/recipes recipes.xsd \">");
		{
			toSend.append("<recipe name=\"" + name + "\">");
			{
				toSend.append("<alloying energy=\"" + energy + "\"" + " exp=\"1\">");
				{
					if (primaryInput != null) {
						toSend.append("<input ");
						{
							appendItemStack(toSend, primaryInput);
						}
						toSend.append(" />");
					}

					if (secondaryInput != null) {
						toSend.append("<input ");
						{
							appendItemStack(toSend, secondaryInput);
						}
						toSend.append(" />");
					}

					if (tertiaryInput != null) {
						toSend.append("<input ");
						{
							appendItemStack(toSend, tertiaryInput);
						}
						toSend.append(" />");
					}

					toSend.append("<output ");
					{
						appendItemStack(toSend, output);
					}
					toSend.append(" />");
				}
				toSend.append("</alloying>");
			}
			toSend.append("</recipe>");
		}
		toSend.append("</enderio:recipes>");

		System.out.println(toSend.toString());
		System.out.println("F25");
		FMLInterModComms.sendMessage("enderio", "recipe:xml", toSend.toString());
	}

	private static void appendItemStack(StringBuilder sb, ItemStack stack)
	{
		if(stack != null)
		{
			sb.append(" name=\"" + stack.getItem().getRegistryName() + ":" + stack.getItemDamage() + "\" amount=\"" + stack.getCount() + "\"");
		}
	}

	public static void addSoulBinderRecipe(String recipeID, int energy, int xp, String soulTypes, ItemStack input, ItemStack output)
	{
		SimplyJetpacks.logger.info("Registering EIO Soul Binder recipe");

		NBTTagCompound toSend = new NBTTagCompound();

		toSend.setString("recipeUID", recipeID);
		toSend.setInteger("requiredEnergyRF", energy);
		toSend.setInteger("requiredXP", xp);
		toSend.setString("entityTypes", soulTypes);
		writeItemStack(toSend, "inputStack", input);
		writeItemStack(toSend, "outputStack", output);

		FMLInterModComms.sendMessage("enderio", "recipe:soulbinder", toSend);
	}

	private static void writeItemStack(NBTTagCompound nbt, String tagName, ItemStack stack)
	{
		if(stack != null)
		{
			NBTTagCompound stackTag = new NBTTagCompound();
			stack.writeToNBT(stackTag);
			nbt.setTag(tagName, stackTag);
		}
	}
}