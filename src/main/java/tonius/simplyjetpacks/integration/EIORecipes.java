package tonius.simplyjetpacks.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import tonius.simplyjetpacks.SimplyJetpacks;

public abstract class EIORecipes {

    public static void addAlloySmelterRecipe(String name, int energy, int experience, ItemStack primaryInput, ItemStack secondaryInput, ItemStack tertiaryInput, ItemStack output) {
        SimplyJetpacks.logger.info(String.format("Registering EIO Recipe: %s", name));

        StringBuilder toSend = new StringBuilder();
        boolean required = true;

        toSend.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        toSend.append("<enderio:recipes xmlns:enderio=\"http://enderio.com/recipes\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://enderio.com/recipes recipes.xsd \">");
        {
            toSend.append("<recipe name=\"").append(name).append("\" required=\"").append(required).append("\">");
            {
                toSend.append("<alloying energy=\"").append(energy).append("\" exp=\"").append(experience).append("\">");
                {
                    toSend.append("<input name=\"").append(createItemStackString(primaryInput)).append(" />");
                    toSend.append("<input name=\"").append(createItemStackString(secondaryInput)).append(" />");
                    if (tertiaryInput != null) {
                        toSend.append("<input name=\"").append(createItemStackString(tertiaryInput)).append(" />");
                    }
                    toSend.append("<output name=\"").append(createItemStackString(output)).append(" />");
                }
                toSend.append("</alloying>");
            }
            toSend.append("</recipe>");
        }
        toSend.append("</enderio:recipes>");

        SimplyJetpacks.logger.info(toSend.toString());
        FMLInterModComms.sendMessage("enderio", "recipe:xml", toSend.toString());
    }

    public static void addSoulBinderRecipe(String name, boolean required, int energy, int levels, String soul, ItemStack input, ItemStack output) {
        SimplyJetpacks.logger.info(String.format("Registering EIO Recipe: %s", name));

        StringBuilder toSend = new StringBuilder();

        toSend.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        toSend.append("<enderio:recipes xmlns:enderio=\"http://enderio.com/recipes\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://enderio.com/recipes recipes.xsd \">");
        {
            toSend.append("<recipe name=\"").append(name).append("\" required=\"").append(required).append("\">");
            {
                toSend.append("<soulbinding energy=\"").append(energy).append("\" levels=\"").append(levels).append("\">");
                {
                    toSend.append("<input name=\"").append(createItemStackString(input)).append(" />");
                    toSend.append("<soul name=\"").append(soul).append("\" />");
                    toSend.append("<output name=\"").append(createItemStackString(output)).append(" />");
                }
                toSend.append("</soulbinding>");
            }
            toSend.append("</recipe>");
        }
        toSend.append("</enderio:recipes>");

        SimplyJetpacks.logger.info(toSend.toString());
        FMLInterModComms.sendMessage("enderio", "recipe:xml", toSend.toString());
    }

    // TODO: Make this append to the StringBuilder
    private static String createItemStackString(ItemStack stack) {
        if (stack != null) {
            String string = "item:" + stack.getItem().getRegistryName() + ":" + stack.getItemDamage() + "\"";
            if (stack.getCount() > 1) {
                string += " amount=\"" + stack.getCount() + "\"";
            }
            return string;
        } else return "";
    }

    private static String createItemStackStringOld(ItemStack stack) {
        return stack != null ? ("item:" + stack.getItem().getRegistryName() + ":" + stack.getItemDamage()) : "";
    }

    private static void appendItemStack(StringBuilder sb, ItemStack stack) {
        if (stack != null) {
            sb.append(" name=\"item:").append(stack.getItem().getRegistryName()).append(":").append(stack.getItemDamage()).append("\" amount=\"").append(stack.getCount()).append("\"");
        }
    }
}