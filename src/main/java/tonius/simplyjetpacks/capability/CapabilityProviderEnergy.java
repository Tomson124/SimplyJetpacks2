package tonius.simplyjetpacks.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.IntSupplier;

public class CapabilityProviderEnergy<HANDLER> implements ICapabilityProvider {

	private final ItemForgeEnergy energyItem;
	private final LazyOptional<ItemForgeEnergy> energyCapability;

	public CapabilityProviderEnergy(ItemStack stack, IntSupplier energyCapacity) {
		this.energyItem = new ItemForgeEnergy(stack,energyCapacity);
		this.energyCapability = LazyOptional.of(() -> energyItem);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		return cap == CapabilityEnergy.ENERGY ? energyCapability.cast() : LazyOptional.empty();
	}
}
