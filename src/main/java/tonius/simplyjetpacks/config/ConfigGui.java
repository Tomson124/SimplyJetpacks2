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
        super(parentScreen, getConfigElements(parentScreen), SimplyJetpacks.MODID, false, false, SJStringUtil.localize("config.", ".title"));
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parentScreen) {
        List<IConfigElement> list = new ArrayList<>();
        ConfigCategory category;
        String prefix = "config." + SimplyJetpacks.PREFIX;

        for (Section configSection : Config.configSections) {
            if (configSection.client) {
                category = Config.configClient.getCategory(configSection.key).setLanguageKey(prefix + configSection.id);
            } else {
                category = Config.configCommon.getCategory(configSection.key).setLanguageKey(prefix + configSection.id);
            }
            category.setComment(prefix + configSection.id + ".tooltip");
            if (!category.isChild()) {
                list.add(new ConfigElement(category));
            }
        }
        return list;
    }
}
