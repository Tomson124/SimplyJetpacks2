package tonius.simplyjetpacks.setup;

public enum FuelType {

	ENERGY(" RF"),
	// TODO: Remove FLUID
	FLUID(" mB");

	public final String suffix;

	FuelType(String suffix) {
		this.suffix = suffix;
	}
}
