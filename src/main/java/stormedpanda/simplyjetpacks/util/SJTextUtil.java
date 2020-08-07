package stormedpanda.simplyjetpacks.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.items.JetpackItem;

import java.util.List;

public abstract class SJTextUtil {

    private static final ITextComponent on = translate("misc", "enabled", SJStylesUtil.GREEN);
    private static final ITextComponent off = translate("misc", "disabled", SJStylesUtil.RED);
    private static final ITextComponent notAvailable = translate("misc", "notAvailable", SJStylesUtil.DARK_GRAY);

    private static final String ENERGY_FORMAT = "%,d";

    public static ITextComponent translate(String prefix, String suffix, Style style, Object... params) {
        String key = String.format("%s.%s.%s", prefix, SimplyJetpacks.MODID, suffix);
        return new TranslationTextComponent(key, params).setStyle(style);
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
        return translate("misc", "energyPerTick", s1);
    }
    public static ITextComponent energyWithMax(int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", "energyWithMax", s1, s2);
    }

    public static ITextComponent getShiftText() {
        return translate("tooltip", "showDetails", new StringTextComponent("Shift").setStyle(SJStylesUtil.GOLD));
    }

    public static void addBaseInfo(ItemStack stack, List<ITextComponent> list) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        list.add(translate("tooltip", "tier", jetpack.tier));
        if (jetpack.isCreative()) {
            list.add(translate("tooltip", "infiniteEnergy"));
        } else {
            list.add(energyWithMax(jetpack.getEnergyStored(stack), jetpack.getMaxEnergyStored(stack)));
        }
    }

    public static void addShiftInfo(ItemStack stack, List<ITextComponent> list) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        list.add(translate("tooltip", "itemJetpack.engine", SJStylesUtil.GOLD, jetpack.isEngineOn(stack) ? on : off));
        list.add(translate("tooltip", "itemJetpack.hover", SJStylesUtil.GOLD, jetpack.isHoverOn(stack) ? on : off));
/*        if (jetpack.getType().canEHover()) {
            list.add(translate("tooltip", "itemJetpack.emergencyHover", SJStylesUtil.GOLD, jetpack.isEHoverOn(stack) ? on : off));
        }*/
        if (jetpack.getType().canCharge()) {
            list.add(translate("tooltip", "itemJetpack.charger", SJStylesUtil.GOLD, jetpack.isChargerOn(stack) ? on : off));
        }
        if (!jetpack.isCreative()) {
            list.add(translate("tooltip", "itemJetpack.energyUsage", SJStylesUtil.GOLD, energyPerTick(jetpack.getEnergyUsage(stack))));
        }
        ITextComponent particle = translate("tooltip", "particle." + jetpack.getType().getParticleType(stack).ordinal(), SJStylesUtil.WHITE);
        list.add(translate("tooltip", "itemJetpack.particleType", SJStylesUtil.GOLD, particle));
    }

    public static void addHUDInfoText(ItemStack stack, List<ITextComponent> list) {
        list.add(getEnergyText(stack));
        list.add(getHUDStates(stack));
    }

    public static ITextComponent getEnergyText(ItemStack stack) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        if (jetpack.isCreative()) {
            return translate("hud", "energyDisplay", translate("hud", "infiniteEnergy", SJStylesUtil.WHITE));
        }
        int percent = (int) Math.ceil((double) jetpack.getEnergyStored(stack) / (double) jetpack.getCapacity() * 100D);
        ITextComponent percentageText = getColoredPercent(percent);
        ITextComponent exactText = energy(jetpack.getEnergyStored(stack));
        if (SimplyJetpacksConfig.CLIENT.showExactEnergy.get()) {
            return translate("hud", "energyDisplayExtra", percentageText, exactText);
        } else {
            return translate("hud", "energyDisplay", percentageText);
        }
    }

    public static ITextComponent getColoredPercent(int percent) {
        if (percent > 70) {
            return new StringTextComponent(String.format("%s%%", percent)).setStyle(SJStylesUtil.GREEN);
        } else if (percent > 40) {
            return new StringTextComponent(String.format("%s%%", percent)).setStyle(SJStylesUtil.YELLOW);
        } else if (percent > 10) {
            return new StringTextComponent(String.format("%s%%", percent)).setStyle(SJStylesUtil.GOLD);
        } else if (percent > 0) {
            return new StringTextComponent(String.format("%s%%", percent)).setStyle(SJStylesUtil.RED);
        } else {
            return translate("hud", "energyDepleted", SJStylesUtil.RED);
        }
    }

    public static ITextComponent getHUDStates(ItemStack stack) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        Style on = SJStylesUtil.GREEN;
        Style off = SJStylesUtil.RED;
        Style notAvailable = SJStylesUtil.DARK_GRAY;
        ITextComponent engineState = translate("hud", "engine", jetpack.isEngineOn(stack) ? on : off);
        ITextComponent hoverState = translate("hud", "hover", jetpack.isHoverOn(stack) ? on : off);
        ITextComponent eHoverState = translate("hud", "eHover", jetpack.getType().canEHover() ? (jetpack.isEHoverOn(stack) ? on : off) : notAvailable);
        ITextComponent chargerState = translate("hud", "charger", jetpack.getType().canCharge() ? (jetpack.isChargerOn(stack) ? on : off) : notAvailable);
        return translate("hud", "jetpackStates", engineState, hoverState, eHoverState, chargerState);
    }

    public static ITextComponent getStateToggle(String state, boolean value) {
        return translate("chat", "itemJetpack." + state, value ? on : off);
    }

    public static ITextComponent getEmergencyText() {
        return translate("chat", "itemJetpack.emergencyHoverModeActivated", SJStylesUtil.RED);
    }
}
