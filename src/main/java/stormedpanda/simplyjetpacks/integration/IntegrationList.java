package stormedpanda.simplyjetpacks.integration;

import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;

public class IntegrationList {

    public static boolean integrateVanilla;
    public static boolean integrateImmersiveEngineering;
    public static boolean integrateMekanism;
    public static boolean integrateEnderIO;
    public static boolean integrateThermalExpansion;
    public static boolean integrateThermalDynamics;

    public static void init() {
        integrateVanilla = ModType.SIMPLY_JETPACKS.loaded && SimplyJetpacksConfig.COMMON.enableIntegrationVanilla.get();
        integrateImmersiveEngineering = ModType.IMMERSIVE_ENGINEERING.loaded && SimplyJetpacksConfig.COMMON.enableIntegrationImmersiveEngineering.get();
        integrateMekanism = ModType.MEKANISM.loaded && SimplyJetpacksConfig.COMMON.enableIntegrationMekanism.get();
        integrateEnderIO = ModType.ENDER_IO.loaded && SimplyJetpacksConfig.COMMON.enableIntegrationEnderIO.get();
        integrateThermalExpansion = ModType.THERMAL_EXPANSION.loaded && SimplyJetpacksConfig.COMMON.enableIntegrationThermalExpansion.get();
        integrateThermalDynamics = ModType.THERMAL_DYNAMICS.loaded && SimplyJetpacksConfig.COMMON.enableIntegrationThermalDynamics.get();
    }
}
