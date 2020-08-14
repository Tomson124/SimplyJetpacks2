package tonius.simplyjetpacks.setup;

public enum FuelType {

	// TODO: Remove FLUID
	ENERGY(" RF"),
	FLUID(" mB");

	public final String suffix;

	FuelType(String suffix) {
		this.suffix = suffix;
	}
}
