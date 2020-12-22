package stormedpanda.simplyjetpacks.integration;

import net.minecraftforge.fml.ModList;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public enum ModType {

    SIMPLY_JETPACKS(SimplyJetpacks.MODID),
    MEKANISM("mekanism"),
    IMMERSIVE_ENGINEERING("immersiveengineering"),
    ENDER_IO("enderio"),
    THERMAL_FOUNDATION("thermal"),
    THERMAL_EXPANSION("thermal_expansion"),
    THERMAL_DYNAMICS("thermal_dynamics");

    public final String[] modids;
    public final boolean loaded;

    ModType(String... modids) {
        this.modids = modids;

        for (String s : this.modids) {
            if (!ModList.get().isLoaded(s)) {
                this.loaded = false;
                return;
            }
        }
        this.loaded = true;
    }
}
