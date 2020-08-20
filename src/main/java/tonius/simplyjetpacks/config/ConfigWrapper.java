package tonius.simplyjetpacks.config;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import tonius.simplyjetpacks.SimplyJetpacks;

import javax.annotation.Nullable;
import java.io.File;

public class ConfigWrapper extends Configuration {

    private static final String keyPrefix = "config." + SimplyJetpacks.PREFIX;
    private static final String commentSuffix = ".comment";

    public ConfigWrapper(File file, String configVersion, boolean caseSensitive) {
        super(file, configVersion, caseSensitive);
    }

    public void addCategory(String categoryName) {
        String categoryKey = keyPrefix + categoryName;
        String commentKey = categoryKey + commentSuffix;
        String comment = I18n.format(commentKey);
        setCategoryComment(categoryName, comment);
        setCategoryLanguageKey(categoryName, categoryKey);
    }

    public boolean getBooleanS(String category, String name, @Nullable String bonus, boolean defaultValue, boolean requiresRestart) {
        String langKey = keyPrefix + category + '.' + name;
        if (bonus != null) {
            if (bonus.equals("tuning"))
            langKey = keyPrefix + "tuning." + name;
        }
        String commentKey = langKey + commentSuffix;
        String comment = I18n.format(commentKey);

        Property prop = get(category, name, defaultValue, comment);
        prop.setRequiresMcRestart(requiresRestart);
        prop.setLanguageKey(langKey);
        return prop.getBoolean(defaultValue);
    }

    public String getStringS(String category, String name, @Nullable String bonus, String defaultValue, boolean requiresRestart) {
        String langKey = keyPrefix + category + '.' + name;
        if (bonus != null) {
            if (bonus.equals("tuning"))
                langKey = keyPrefix + "tuning." + name;
        }
        String commentKey = langKey + commentSuffix;
        String comment = I18n.format(commentKey);

        Property prop = get(category, name, defaultValue, comment);
        prop.setRequiresMcRestart(requiresRestart);
        prop.setLanguageKey(langKey);
        return prop.getString();
    }

    public float getFloatS(String category, String name, @Nullable String bonus, float defaultValue, @Nullable Float minValue, @Nullable Float maxValue, boolean requiresRestart) {
        String langKey = keyPrefix + category + '.' + name;
        if (bonus != null) {
            if (bonus.equals("tuning"))
                langKey = keyPrefix + "tuning." + name;
        }
        String commentKey = langKey + commentSuffix;
        String comment = I18n.format(commentKey);

        Property prop = get(category, name, defaultValue, comment);
        if (minValue != null) prop.setMinValue(minValue);
        if (maxValue != null) prop.setMaxValue(maxValue);

        prop.setRequiresMcRestart(requiresRestart);
        prop.setLanguageKey(langKey);
        return prop.getInt((int) defaultValue);
    }

    public int getIntS(String category, String name, @Nullable String bonus, int defaultValue, @Nullable Integer minValue, @Nullable Integer maxValue, boolean requiresRestart) {
        String langKey = keyPrefix + category + '.' + name;
        if (bonus != null) {
            if (bonus.equals("tuning"))
                langKey = keyPrefix + "tuning." + name;
        }
        String commentKey = langKey + commentSuffix;
        String comment = I18n.format(commentKey);

        Property prop = get(category, name, defaultValue, comment);
        if (minValue != null) prop.setMinValue(minValue);
        if (maxValue != null) prop.setMaxValue(maxValue);

        prop.setRequiresMcRestart(requiresRestart);
        prop.setLanguageKey(langKey);
        return prop.getInt(defaultValue);
    }

    public double getDoubleS(String category, String name, @Nullable String bonus, double defaultValue, @Nullable Double minValue, @Nullable Double maxValue, boolean requiresRestart) {
        String langKey = keyPrefix + category + '.' + name;
        if (bonus != null) {
            if (bonus.equals("tuning"))
                langKey = keyPrefix + "tuning." + name;
        }
        String commentKey = langKey + commentSuffix;
        String comment = I18n.format(commentKey);

        Property prop = get(category, name, defaultValue, comment);
        if (minValue != null) prop.setMinValue(minValue);
        if (maxValue != null) prop.setMaxValue(maxValue);

        prop.setRequiresMcRestart(requiresRestart);
        prop.setLanguageKey(langKey);
        return prop.getDouble(defaultValue);
    }

    public String getStringListS(String category, String name, @Nullable String bonus, String defaultValue, String[] validValues, boolean requiresRestart) {
        String langKey = keyPrefix + category + '.' + name;
        String commentKey = langKey + commentSuffix;
        String comment = I18n.format(commentKey);

        Property prop = get(category, name, defaultValue, comment);
        prop.setValidValues(validValues);
        prop.setRequiresMcRestart(requiresRestart);
        prop.setLanguageKey(langKey);
        return prop.getString();
    }

}
