package tonius.simplyjetpacks.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.util.SJStringHelper;

import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), SimplyJetpacks.MODID, false, false, SJStringHelper.localize("config.title"));
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        List<IConfigElement> list = new ArrayList<>();
        String prefix = SimplyJetpacks.PREFIX + "config.";

        for (Section configSection : Config.configSections) {
            if (configSection.client) {
                list.add(new ConfigElement(Config.configClient.getCategory(configSection.toLowerCase()).setLanguageKey(prefix + configSection.id)));
            } else {
                list.add(new ConfigElement(Config.config.getCategory(configSection.toLowerCase()).setLanguageKey(prefix + configSection.id)));
            }
        }
        return list;
    }
}