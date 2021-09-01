package tonius.simplyjetpacks.integration;

import net.minecraftforge.fml.common.Loader;
import tonius.simplyjetpacks.SimplyJetpacks;

public enum ModType {
	SIMPLY_JETPACKS(SimplyJetpacks.MODID),
	ENDER_IO("enderio"),
	THERMAL_EXPANSION("thermalexpansion"),
	THERMAL_DYNAMICS("thermaldynamics"),
	REDSTONE_ARSENAL("redstonearsenal"),
	REDSTONE_REPOSITORY("redstonerepository"),
	MEKANISM("mekanism"),
	IMMERSIVE_ENGINEERING("immersiveengineering"),
	BAUBLES("baubles");

	public final String[] modids;
	public final boolean loaded;

	ModType(String... modids) {
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