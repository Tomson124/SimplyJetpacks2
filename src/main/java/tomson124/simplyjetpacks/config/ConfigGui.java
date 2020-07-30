package tomson124.simplyjetpacks.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.util.SJStringHelper;

import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiConfig
{
	public ConfigGui(GuiScreen parentScreen)
	{
		super(parentScreen, getConfigElements(parentScreen), SimplyJetpacks.MODID, false, false, SJStringHelper.localize("config.title"));
	}

	private static List<IConfigElement> getConfigElements(GuiScreen parent)
	{
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		String prefix = SimplyJetpacks.PREFIX + "config.";

		for(Section configSection : Config.configSections)
		{
			if(configSection.client)
			{
				list.add(new ConfigElement(Config.configClient.getCategory(configSection.toLowerCase()).setLanguageKey(prefix + configSection.id)));
			}
			else
			{
				list.add(new ConfigElement(Config.config.getCategory(configSection.toLowerCase()).setLanguageKey(prefix + configSection.id)));
			}
		}

		return list;
	}
}