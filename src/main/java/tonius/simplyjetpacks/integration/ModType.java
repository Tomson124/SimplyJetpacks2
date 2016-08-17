package tonius.simplyjetpacks.integration;

import net.minecraftforge.fml.common.Loader;
import tonius.simplyjetpacks.SimplyJetpacks;

public enum ModType
{
	SIMPLY_JETPACKS("", SimplyJetpacks.MODID),
	ENDER_IO(".eio", "EnderIO"),
	BUILDCRAFT(".bc", "BuildCraft|Core");

	public final String suffix;
	public final String[] modids;
	public final boolean loaded;

	private ModType(String suffix, String... modids)
	{
		this.suffix = suffix;
		this.modids = modids;

		for(String s : this.modids)
		{
			if(!Loader.isModLoaded(s))
			{
				this.loaded = false;
				return;
			}
		}
		this.loaded = true;
	}
}