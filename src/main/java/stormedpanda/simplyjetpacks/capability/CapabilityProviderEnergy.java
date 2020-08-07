package stormedpanda.simplyjetpacks.capability;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

public class CapabilityProviderEnergy<HANDLER> implements ICapabilityProvider {

    protected IEnergyStorage instance;

    public CapabilityProviderEnergy(IEnergyStorage instance) {
        this.instance = instance;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction direction) {
        return CapabilityEnergy.ENERGY.orEmpty(capability, LazyOptional.of(() -> this.instance));
    }
}