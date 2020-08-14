package tonius.simplyjetpacks.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Don't instantiate this or call these methods in any way. Use the methods in {@link ItemHelper}.
 *
 * @author King Lemming
 */
public class OreDictionaryProxy {

    public ItemStack getOre(String oreName) {
        if (!oreNameExists(oreName)) {
            return null;
        }
        return ItemHelper.cloneStack(OreDictionary.getOres(oreName).get(0), 1);
    }

    public int getPrimaryOreID(ItemStack stack) {
        int[] oreIDs = OreDictionary.getOreIDs(stack);
        return oreIDs.length > 0 ? oreIDs[0] : -1;
    }

    public String getPrimaryOreName(ItemStack stack) {
        return getOreName(getPrimaryOreID(stack));
    }

    public List<Integer> getOreIDs(ItemStack stack) {
        int[] ids = OreDictionary.getOreIDs(stack);
        ArrayList<Integer> oreIDs = new ArrayList<Integer>();
        for (int id : ids) {
            oreIDs.add(id);
        }
        return oreIDs;
    }

    public int getOreID(String oreName) {
        return OreDictionary.getOreID(oreName);
    }

    public List<String> getOreNames(ItemStack stack) {
        int[] oreIDs = OreDictionary.getOreIDs(stack);
        ArrayList<String> oreNames = new ArrayList<String>();
        for (int oreID : oreIDs) {
            oreNames.add(OreDictionary.getOreName(oreID));
        }
        return oreNames;
    }

    public String getOreName(int oreID) {
        return OreDictionary.getOreName(oreID);
    }

    public boolean isOreIDEqual(ItemStack stack, int oreID) {
        int[] oreIDs = OreDictionary.getOreIDs(stack);
        for (int id : oreIDs) {
            if (oreID == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isOreNameEqual(ItemStack stack, String oreName) {
        List<String> oreNames = getOreNames(stack);
        for (String name : oreNames) {
            if (oreName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean oreNameExists(String oreName) {
        return OreDictionary.doesOreNameExist(oreName);
    }
}
