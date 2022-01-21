package stormedpanda.simplyjetpacks.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.item.JetpackItem;

import java.util.ArrayList;
import java.util.List;

public class SJTextUtil {

    private static final ITextComponent on = translate("misc", "enabled", TextFormatting.GREEN);
    private static final ITextComponent off = translate("misc", "disabled", TextFormatting.RED);
    private static final ITextComponent notAvailable = translate("misc", "notAvailable", TextFormatting.DARK_GRAY);

    private static final String ENERGY_FORMAT = "%,d";

    public static ITextComponent translate(String prefix, String suffix, TextFormatting style, Object... params) {
        String key = String.format("%s.%s.%s", prefix, SimplyJetpacks.MODID, suffix);
        return new TranslationTextComponent(key, params).withStyle(style);
    }

    public static ITextComponent translate(String prefix, String suffix, Object... params) {
        String key = String.format("%s.%s.%s", prefix, SimplyJetpacks.MODID, suffix);
        return new TranslationTextComponent(key, params);
    }

    public static ITextComponent energy(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energy", s1);
    }

    public static ITextComponent energyPerTick(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energyPerTick", TextFormatting.WHITE, s1);
    }

    public static ITextComponent energyWithMax(int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", "energyWithMax", s1, s2);
    }

    public static ITextComponent fluidWithMax(IFluidHandler fluidHandler, int tank) {
        FluidStack stack = fluidHandler.getFluidInTank(tank);
        return fluidWithMax(stack, fluidHandler.getTankCapacity(tank));
    }

    public static ITextComponent fluidWithMax(FluidStack stack, int tankCapacity) {
        ITextComponent fluidName = stack.getDisplayName();
        String s1 = String.format(ENERGY_FORMAT, stack.getAmount());
        String s2 = String.format(ENERGY_FORMAT, tankCapacity);
        return translate("misc", "fluidWithMax", fluidName, s1, s2);
    }

    public static ITextComponent getShiftText() {
        return translate("tooltip", "showDetails", new StringTextComponent("Shift").withStyle(TextFormatting.GOLD));
    }

    public static void addBaseInfo(ItemStack stack, List<ITextComponent> list) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        list.add(translate("tooltip", "tier", jetpack.tier));
        if (jetpack.isCreative()) {
            list.add(translate("tooltip", "infiniteEnergy", TextFormatting.LIGHT_PURPLE));
        } else {
            list.add(energyWithMax(jetpack.getEnergy(stack), jetpack.getCapacity(stack)));
        }
    }

    public static void addShiftInfo(ItemStack stack, List<ITextComponent> list) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        list.add(translate("tooltip", "itemJetpack.engine", TextFormatting.GOLD, jetpack.isEngineOn(stack) ? on : off));
        list.add(translate("tooltip", "itemJetpack.hover", TextFormatting.GOLD, jetpack.isHoverOn(stack) ? on : off));
/*        if (jetpack.getType().canEHover()) {
            list.add(translate("tooltip", "itemJetpack.emergencyHover", TextFormatting.GOLD, jetpack.isEHoverOn(stack) ? on : off));
        }*/
        if (jetpack.getJetpackType().getChargerMode()) {
            list.add(translate("tooltip", "itemJetpack.charger", TextFormatting.GOLD, jetpack.isChargerOn(stack) ? on : off));
        }
        if (!jetpack.isCreative()) {
            list.add(translate("tooltip", "itemJetpack.energyUsage", TextFormatting.GOLD, energyPerTick(jetpack.getEnergyUsage(stack))));
        }
        ITextComponent particle = translate("tooltip", "particle." + JetpackItem.getParticleId(stack), TextFormatting.WHITE);
        list.add(translate("tooltip", "itemJetpack.particleType", TextFormatting.GOLD, particle));

        ITextComponent throttle = new StringTextComponent(jetpack.getThrottle(stack) + "%").withStyle(TextFormatting.WHITE);
        list.add(translate("tooltip", "itemJetpack.throttle", TextFormatting.GOLD, throttle));
    }

    public static void addHUDInfoText(ItemStack stack, List<ITextComponent> list) {
        if (SimplyJetpacksConfig.showThrottle.get()) {
            JetpackItem jetpack = (JetpackItem) stack.getItem();
            list.add(translate("hud", "throttle", jetpack.getThrottle(stack)));
        }
        list.add(getEnergyText(stack));
        list.add(getHUDStates(stack));
    }

    public static ITextComponent getEnergyText(ItemStack stack) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        if (jetpack.isCreative()) {
            return translate("hud", "energyDisplay", translate("hud", "infiniteEnergy", TextFormatting.LIGHT_PURPLE));
        }
        int percent = (int) Math.ceil((double) jetpack.getEnergy(stack) / (double) jetpack.getCapacity(stack) * 100D);
        ITextComponent percentageText = getColoredPercent(percent);
        ITextComponent exactText = energy(jetpack.getEnergy(stack));
        if (SimplyJetpacksConfig.showExactEnergy.get()) {
            return translate("hud", "energyDisplayExtra", percentageText, exactText);
        } else {
            return translate("hud", "energyDisplay", percentageText);
        }
    }

    public static ITextComponent getColoredPercent(int percent) {
        if (percent > 70) {
            return new StringTextComponent(String.format("%s%%", percent)).withStyle(TextFormatting.GREEN);
        } else if (percent > 40) {
            return new StringTextComponent(String.format("%s%%", percent)).withStyle(TextFormatting.YELLOW);
        } else if (percent > 10) {
            return new StringTextComponent(String.format("%s%%", percent)).withStyle(TextFormatting.GOLD);
        } else if (percent > 0) {
            return new StringTextComponent(String.format("%s%%", percent)).withStyle(TextFormatting.RED);
        } else {
            return translate("hud", "energyDepleted", TextFormatting.RED);
        }
    }

    public static ITextComponent getHUDStates(ItemStack stack) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        ArrayList<ITextComponent> statesTexts = new ArrayList<>();
        int stateCount = 1;
        TextFormatting on = TextFormatting.GREEN;
        TextFormatting off = TextFormatting.RED;
        TextFormatting notAvailable = TextFormatting.DARK_GRAY;
        ITextComponent engineState = translate("hud", "engine", jetpack.isEngineOn(stack) ? on : off);
        ITextComponent hoverState = translate("hud", "hover", jetpack.isHoverOn(stack) ? on : off);
        ITextComponent eHoverState = translate("hud", "eHover", jetpack.getJetpackType().getEmergencyHoverMode() ? (jetpack.isEHoverOn(stack) ? on : off) : notAvailable);
        ITextComponent chargerState = translate("hud", "charger", jetpack.getJetpackType().getChargerMode() ? (jetpack.isChargerOn(stack) ? on : off) : notAvailable);
        statesTexts.add(engineState);
        if (SimplyJetpacksConfig.showHoverState.get() && jetpack.getJetpackType().getHoverMode()) {
            statesTexts.add(hoverState);
            stateCount++;
        }
        if (SimplyJetpacksConfig.showEHoverState.get() && jetpack.getJetpackType().getEmergencyHoverMode()) {
            statesTexts.add(eHoverState);
            stateCount++;
        }
        if (SimplyJetpacksConfig.showChargerState.get() && jetpack.getJetpackType().getChargerMode()) {
            statesTexts.add(chargerState);
            stateCount++;
        }
        return translate("hud", "jetpackStates." + stateCount, statesTexts.toArray());
    }

    public static ITextComponent getStateToggle(String state, boolean value) {
        return translate("chat", "itemJetpack." + state, value ? on : off);
    }

    public static ITextComponent getEmergencyText() {
        return translate("chat", "itemJetpack.emergencyHoverModeActivated", TextFormatting.RED);
    }
}