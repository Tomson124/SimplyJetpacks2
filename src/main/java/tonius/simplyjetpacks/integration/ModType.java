package tonius.simplyjetpacks.integration;

import net.minecraftforge.fml.common.Loader;
import tonius.simplyjetpacks.SimplyJetpacks;

public enum ModType {
	SIMPLY_JETPACKS(SimplyJetpacks.MODID),
	ENDER_IO("EnderIO"),
	THERMAL_EXPANSION("thermalexpansion"),
	REDSTONE_ARSENAL("redstonearsenal"),
	THERMAL_DYNAMICS("thermaldynamics");

	public final String[] modids;
	public final boolean loaded;

	private ModType(String... modids) {
		this.modids = modids;

		for (String s : this.modids) {
			if (!Loader.isModLoaded(s)) {
				this.loaded = false;
				return;
			}
		}
		this.loaded = true;
	}
}