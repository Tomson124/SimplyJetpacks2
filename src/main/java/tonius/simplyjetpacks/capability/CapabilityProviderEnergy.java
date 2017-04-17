package tonius.simplyjetpacks.capability;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class CapabilityProviderEnergy<HANDLER> implements ICapabilityProvider {

	protected final HANDLER instance;

	protected final Capability<HANDLER> capability;

	protected final EnumFacing facing;

	public CapabilityProviderEnergy(final Capability<HANDLER> capability, @Nullable final EnumFacing facing) {
		this(capability.getDefaultInstance(), capability, facing);
	}

	public CapabilityProviderEnergy(final HANDLER instance, Capability<HANDLER> capability, @Nullable EnumFacing facing) {
		this.instance = instance;
		this.capability = capability;
		this.facing = facing;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == getCapability();
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == getCapability()) {
			return getCapability().cast(getInstance());
		}

		return null;
	}

	public final Capability<HANDLER> getCapability() {
		return capability;
	}

	@Nullable
	public EnumFacing getFacing() {
		return facing;
	}

	public final HANDLER getInstance() {
		return instance;
	}
}
