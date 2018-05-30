package thundr.redstonerepository.api;

import cofh.redstoneflux.api.IEnergyContainerItem;
import net.minecraft.item.ItemStack;

public interface IArmorEnderium extends IEnergyContainerItem {

	/**
	 * Allows armor to be calculated as part of a set of Gelid Enderium Armor
	 * Requires implementation of IEnergyContainerItem for energy manipulation
	 *
	 * @param stack
	 *          In case instances of the class can or cannot be IArmorEnderium, allow for
	 *          custom logic to be implemented on a passed stack.
	 *
	 * @return Whether or not the piece of armor is a part of a Gelid set.
	 */
	boolean isEnderiumArmor(ItemStack stack);

}
