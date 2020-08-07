package stormedpanda.simplyjetpacks.capability;

import net.minecraft.item.ItemStack;

public interface IEnergyContainerItem {

    int receiveEnergy(ItemStack container, int maxReceive, boolean simulate);

    int extractEnergy(ItemStack container, int maxExtract, boolean simulate);

    int getEnergyStored(ItemStack container);

    int getMaxEnergyStored(ItemStack container);
}