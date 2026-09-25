package tomson124.simplyjetpacks.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.config.SimplyJetpacksConfig;
import tomson124.simplyjetpacks.item.JetpackItem;

import java.util.ArrayList;
import java.util.List;

public class SJTextUtil {

    private static final Component on = translate("misc", "enabled", ChatFormatting.GREEN);
    private static final Component off = translate("misc", "disabled", ChatFormatting.RED);
    private static final Component notAvailable = translate("misc", "notAvailable", ChatFormatting.DARK_GRAY);

    private static final String ENERGY_FORMAT = "%,d";

    public static Component translate(String prefix, String suffix, ChatFormatting style, Object... params) {
        String key = String.format("%s.%s.%s", prefix, SimplyJetpacks.MODID, suffix);
        return Component.translatable(key, params).withStyle(style);
    }

    public static Component translate(String prefix, String suffix, Object... params) {
        String key = String.format("%s.%s.%s", prefix, SimplyJetpacks.MODID, suffix);
        return Component.translatable(key, params);
    }

    public static Component energy(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energy", s1);
    }

    public static Component energyPerTick(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energyPerTick", ChatFormatting.WHITE, s1);
    }

    public static Component energyWithMax(int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", "energyWithMax", s1, s2);
    }

    public static Component fluidWithMax(IFluidHandler fluidHandler, int tank) {
        FluidStack stack = fluidHandler.getFluidInTank(tank);
        return fluidWithMax(stack, fluidHandler.getTankCapacity(tank));
    }

    public static Component fluidWithMax(FluidStack stack, int tankCapacity) {
        Component fluidName = stack.getHoverName();
        String s1 = String.format(ENERGY_FORMAT, stack.getAmount());
        String s2 = String.format(ENERGY_FORMAT, tankCapacity);
        return translate("misc", "fluidWithMax", fluidName, s1, s2);
    }

    public static Component getShiftText() {
        return translate("tooltip", "showDetails", Component.literal("Shift").withStyle(ChatFormatting.GOLD));
    }

    public static void addBaseInfo(ItemStack stack, List<Component> list) {
        var jetpack = JetpackUtil.getJetpack(stack);
        list.add(translate("tooltip", "tier", jetpack.tier));
        if (jetpack.creative) {
            list.add(translate("tooltip", "infiniteEnergy", ChatFormatting.LIGHT_PURPLE));
        } else {
            list.add(energyWithMax(JetpackUtil.getEnergyStorage(stack).getEnergyStored(), JetpackUtil.getEnergyStorage(stack).getMaxEnergyStored()));
        }
    }

    public static void addShiftInfo(ItemStack stack, List<Component> list) {
        JetpackItem item = (JetpackItem) stack.getItem();
        var jetpack = JetpackUtil.getJetpack(stack);
        list.add(translate("tooltip", "itemJetpack.engine", ChatFormatting.GOLD, item.isEngineOn(stack) ? on : off));
        if (jetpack.hoverMode) {
            list.add(translate("tooltip", "itemJetpack.hover", ChatFormatting.GOLD, item.isHoverOn(stack) ? on : off));
        }
        if (jetpack.emergencyHoverMode) {
            list.add(translate("tooltip", "itemJetpack.emergencyHover", ChatFormatting.GOLD, item.isEHoverOn(stack) ? on : off));
        }
        if (jetpack.chargerMode) {
            list.add(translate("tooltip", "itemJetpack.charger", ChatFormatting.GOLD, item.isChargerOn(stack) ? on : off));
        }
        if (!jetpack.creative) {
            list.add(translate("tooltip", "itemJetpack.energyUsage", ChatFormatting.GOLD, energyPerTick(item.getEnergyUsage(stack))));
        }
        Component particle = translate("tooltip", "particle." + JetpackItem.getParticleId(stack), ChatFormatting.WHITE);
        list.add(translate("tooltip", "itemJetpack.particleType", ChatFormatting.GOLD, particle));

        list.add(translate("tooltip", "itemJetpack.throttle", ChatFormatting.GOLD, throttleText(stack)));
    }

    private static Component throttleText(ItemStack stack) {
        return Component.literal(Math.round(JetpackUtil.getThrottle(stack) * 100D) + "%").withStyle(ChatFormatting.WHITE);
    }

    public static void addHUDInfoText(ItemStack stack, List<Component> list) {
        if (SimplyJetpacksConfig.showThrottle.get()) {
            if (stack.getItem() instanceof JetpackItem) {
                list.add(translate("hud", "throttle", throttleText(stack)));
            }
        }
        list.add(getEnergyText(stack));
        list.add(getHUDStates(stack));
    }

    public static Component getEnergyText(ItemStack stack) {
        if (JetpackUtil.getJetpack(stack).creative) {
            return translate("hud", "energyDisplay", translate("hud", "infiniteEnergy", ChatFormatting.LIGHT_PURPLE));
        }
        int percent = (int) Math.ceil((double) JetpackUtil.getEnergyStorage(stack).getEnergyStored() / (double) JetpackUtil.getEnergyStorage(stack).getMaxEnergyStored() * 100D);
        Component percentageText = getColoredPercent(percent);
        Component exactText = energy(JetpackUtil.getEnergyStorage(stack).getEnergyStored());
        if (SimplyJetpacksConfig.showExactEnergy.get()) {
            return translate("hud", "energyDisplayExtra", percentageText, exactText);
        } else {
            return translate("hud", "energyDisplay", percentageText);
        }
    }

    public static Component getColoredPercent(int percent) {
        if (percent > 70) {
            return Component.literal(String.format("%s%%", percent)).withStyle(ChatFormatting.GREEN);
        } else if (percent > 40) {
            return Component.literal(String.format("%s%%", percent)).withStyle(ChatFormatting.YELLOW);
        } else if (percent > 10) {
            return Component.literal(String.format("%s%%", percent)).withStyle(ChatFormatting.GOLD);
        } else if (percent > 0) {
            return Component.literal(String.format("%s%%", percent)).withStyle(ChatFormatting.RED);
        } else {
            return translate("hud", "energyDepleted", ChatFormatting.RED);
        }
    }

    public static Component getHUDStates(ItemStack stack) {
        JetpackItem item = (JetpackItem) stack.getItem();
        var jetpack = JetpackUtil.getJetpack(stack);
        ArrayList<Component> statesTexts = new ArrayList<>();
        int stateCount = 1;
        ChatFormatting on = ChatFormatting.GREEN;
        ChatFormatting off = ChatFormatting.RED;
        statesTexts.add(translate("hud", "engine", item.isEngineOn(stack) ? on : off));
        if (SimplyJetpacksConfig.showHoverState.get() && jetpack.hoverMode) {
            statesTexts.add(translate("hud", "hover", item.isHoverOn(stack) ? on : off));
            stateCount++;
        }
        if (SimplyJetpacksConfig.showEHoverState.get() && jetpack.emergencyHoverMode) {
            statesTexts.add(translate("hud", "eHover", item.isEHoverOn(stack) ? on : off));
            stateCount++;
        }
        if (SimplyJetpacksConfig.showChargerState.get() && jetpack.chargerMode) {
            statesTexts.add(translate("hud", "charger", item.isChargerOn(stack) ? on : off));
            stateCount++;
        }
        return translate("hud", "jetpackStates." + stateCount, statesTexts.toArray());
    }

    public static Component getStateToggle(String state, boolean value) {
        return translate("chat", "itemJetpack." + state, value ? on : off);
    }

    public static Component getEmergencyText() {
        return translate("chat", "itemJetpack.emergencyHoverModeActivated", ChatFormatting.RED);
    }
}