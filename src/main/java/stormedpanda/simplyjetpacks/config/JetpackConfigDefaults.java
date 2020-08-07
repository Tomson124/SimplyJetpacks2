package stormedpanda.simplyjetpacks.config;

import java.util.HashMap;
import java.util.Map;

public class JetpackConfigDefaults {
    
    private static final Map<String, JetpackConfigDefaults> DEFAULTS = new HashMap<>();
    //public final Section section;

    // Base
    public int energyCapacity;
    public int energyUsage;
    public int energyPerTickIn;
    public int energyPerTickOut;
    public int armorReduction;
    public int armorEnergyPerHit;
    public int enchantability;

    // Jetpack
    public double speedVertical;
    public double accelVertical;
    public double speedVerticalHover;
    public double speedVerticalHoverSlow;
    public double speedSideways;
    public double sprintSpeedModifier;
    public double sprintEnergyModifier;
    public boolean emergencyHoverMode;
    public boolean chargerMode;

    public JetpackConfigDefaults(String key, String sectionTitle) {
        //this.section = new Section(false, "Tuning - " + sectionTitle, "tuning." + key);
        DEFAULTS.put(key, this);
    }

    public static JetpackConfigDefaults get(String key)
    {
        return DEFAULTS.get(key);
    }

    // TODO: add other jetpack values back in
    static {
        // Simply Jetpacks
        JetpackConfigDefaults d = new JetpackConfigDefaults("jetpackPotato", "Potato Jetpack");
        d.energyCapacity = 1200;
        d.energyUsage = 45;
        d.speedVertical = 0.9D;
        d.accelVertical = 0.5D;

        d = new JetpackConfigDefaults("jetpackCreative", "Creative Jetpack");
        d.energyCapacity = 0;
        d.energyPerTickOut = 0;
        d.energyPerTickIn = 0;
        d.armorReduction = 12;
        d.enchantability = 20;
        d.speedVertical = 0.9D;
        d.accelVertical = 0.15D;
        d.speedVerticalHover = 0.45D;
        d.speedVerticalHoverSlow = 0.0D;
        d.speedSideways = 0.21D;
        d.sprintSpeedModifier = 2.5D;
        d.emergencyHoverMode = true;
        d.chargerMode = true;

        if(true) {
            d = new JetpackConfigDefaults("jetpack1", "Tier 1 Jetpack");
            d.energyCapacity = 80000;
            d.energyUsage = 32;
            d.energyPerTickIn = 400;
            d.armorReduction = 5;
            d.armorEnergyPerHit = 80;
            d.enchantability = 4;
            d.speedVertical = 0.22D;
            d.accelVertical = 0.1D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.14D;
            d.speedSideways = 0.0D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new JetpackConfigDefaults("jetpack2", "Tier 2 Jetpack");
            d.energyCapacity = 400000;
            d.energyUsage = 50;
            d.energyPerTickIn = 2000;
            d.armorReduction = 6;
            d.armorEnergyPerHit = 100;
            d.enchantability = 8;
            d.speedVertical = 0.3D;
            d.accelVertical = 0.12D;
            d.speedVerticalHover = 0.18D;
            d.speedVerticalHoverSlow = 0.1D;
            d.speedSideways = 0.08D;
            d.sprintSpeedModifier = 1.0D;
            d.sprintEnergyModifier = 1.0D;
            d.emergencyHoverMode = false;
            d.chargerMode = false;

            d = new JetpackConfigDefaults("jetpack3", "Tier 3 Jetpack");
            d.energyCapacity = 4000000;
            d.energyUsage = 200;
            d.energyPerTickIn = 20000;
            d.armorReduction = 7;
            d.armorEnergyPerHit = 120;
            d.enchantability = 13;
            d.speedVertical = 0.48D;
            d.accelVertical = 0.13D;
            d.speedVerticalHover = 0.34D;
            d.speedVerticalHoverSlow = 0.03D;
            d.speedSideways = 0.14D;
            d.sprintSpeedModifier = 1.3D;
            d.sprintEnergyModifier = 2.5D;
            d.emergencyHoverMode = true;
            d.chargerMode = false;

            d = new JetpackConfigDefaults("jetpack4", "Tier 4 Jetpack");
            d.energyCapacity = 20000000;
            d.energyUsage = 450;
            d.energyPerTickIn = 50000;
            d.armorReduction = 8;
            d.armorEnergyPerHit = 160;
            d.enchantability = 17;
            d.speedVertical = 0.8D;
            d.accelVertical = 0.14D;
            d.speedVerticalHover = 0.4D;
            d.speedVerticalHoverSlow = 0.005D;
            d.speedSideways = 0.19D;
            d.sprintSpeedModifier = 1.8D;
            d.sprintEnergyModifier = 4.0D;
            d.emergencyHoverMode = true;
            d.chargerMode = true;

            d = new JetpackConfigDefaults("jetpack5", "Tier 5 Jetpack");
            d.energyCapacity = 60000000;
            d.energyUsage = 850;
            d.energyPerTickIn = 200000;
            d.energyPerTickOut = 32000;
            d.armorReduction = 12;
            d.armorEnergyPerHit = 240;
            d.enchantability = 20;
            d.speedVertical = 0.9D;
            d.accelVertical = 0.15D;
            d.speedVerticalHover = 0.45D;
            d.speedVerticalHoverSlow = 0.0D;
            d.speedSideways = 0.21D;
            d.sprintSpeedModifier = 2.4D;
            d.sprintEnergyModifier = 6.0D;
            d.emergencyHoverMode = true;
            d.chargerMode = true;
        }
    }
}
