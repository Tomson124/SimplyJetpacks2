package tonius.simplyjetpacks.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.util.SJStringUtil;

import java.util.ArrayList;
import java.util.List;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(), SimplyJetpacks.MODID, false, false, SJStringUtil.localize("config.", ".title"));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<>();
        ConfigCategory category;
        String prefix = "config." + SimplyJetpacks.PREFIX;

        for (Section configSection : Config.configSections) {
            String langKey = prefix + configSection.category;
            if (configSection.name.equals("x")) {
                langKey = configSection.key;
            }
            if (configSection.client) {
                category = Config.configClient.getCategory(configSection.category);
            } else {
                category = Config.configCommon.getCategory(configSection.category);
            }

            category.setLanguageKey(langKey);

            if (!category.isChild()) {
                list.add(new ConfigElement(category));
            }
        }
        return list;
    }
}
