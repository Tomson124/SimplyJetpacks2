package tonius.simplyjetpacks.config;

public class Section {

    public final boolean client;
    public final String key;
    public final String id;
    public final String name;

    public Section(boolean client, String key) {
        this.client = client;
        this.key = key;
        this.id = key;
        this.name = "";
        Config.configSections.add(this);
    }

    public Section(boolean client, String key, String id) {
        this.client = client;
        this.key = key;
        this.id = id;
        this.name = "";
        Config.configSections.add(this);
    }

    public Section(boolean client, String key, String id, String name) {
        this.client = client;
        this.key = key;
        this.id = id;
        this.name = name;
        Config.configSections.add(this);
    }
}