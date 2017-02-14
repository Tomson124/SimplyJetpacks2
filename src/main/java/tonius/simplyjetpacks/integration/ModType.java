package tonius.simplyjetpacks.integration;

import tonius.simplyjetpacks.SimplyJetpacks;
import net.minecraftforge.fml.common.Loader;

public enum ModType
{
	SIMPLY_JETPACKS("", SimplyJetpacks.MODID),
	ENDER_IO(".eio", "EnderIO", "enderio");

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