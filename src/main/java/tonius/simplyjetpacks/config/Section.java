package tonius.simplyjetpacks.config;

public class Section {

    public final boolean client;
    public final String category;
    public final String name;
    public final String key;

    public Section(boolean client, String category) {
        this.client = client;
        this.category = category;
        this.name = "";
        this.key = "";
        Config.configSections.add(this);
    }

    public Section(boolean client, String category, String name, String key) {
        this.client = client;
        this.category = category; // category name
        this.name = name; // field name
        this.key = key; // lang key
        Config.configSections.add(this);
    }
}