package tomson124.simplyjetpacks.setup;

public enum FuelType
{
	ENERGY(" RF"),
	FLUID(" mB"); //TODO: Remove

	public final String suffix;

	private FuelType(String suffix)
	{
		this.suffix = suffix;
	}
}
