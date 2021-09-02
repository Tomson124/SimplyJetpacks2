package tonius.simplyjetpacks.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.setup.ParticleType;

import java.text.DecimalFormat;
import java.util.List;

import static net.minecraft.util.text.translation.I18n.translateToLocal;
import static net.minecraft.util.text.translation.I18n.translateToLocalFormatted;

public abstract class SJStringUtil {

    private static final String on = TextFormatting.GREEN + localize("tooltip.", ".on");
    private static final String off = TextFormatting.RED + localize("tooltip.", ".off");
    private static final String enabled = TextFormatting.GREEN + localize("tooltip.", ".enabled");
    private static final String disabled = TextFormatting.RED + localize("tooltip.", ".disabled");
    private static final String notAvailable = TextFormatting.DARK_GRAY + localize("tooltip.", ".not_available");

    private static final DecimalFormat formatter = new DecimalFormat("###,###");

    public static String getFormattedNumber(int number) {
        return formatter.format(number);
    }

    public static String getTierText(int tier) {
        return localize("tooltip.", ".tier", tier);
    }

    public static String getEnergyText(int amount, int max, boolean infinite) {
        if (infinite) {
            return TextFormatting.LIGHT_PURPLE + localize("tooltip.", ".energy.infinite");
        }
        return TextFormatting.GRAY + localize("tooltip.", ".energyWithMax", getFormattedNumber(amount), getFormattedNumber(max));
    }

    public static String getStateText(boolean state) {
        String onOrOff = state ? on : off;
        return TextFormatting.GOLD + localize("tooltip.", ".state") + ": " + onOrOff;
    }

    public static String getHoverModeText(boolean state) {
        String enabledOrDisabled = state ? enabled : disabled;
        return TextFormatting.GOLD + localize("tooltip.", ".hover_mode") + ": " + enabledOrDisabled;
    }

    public static String getChargerStateText(boolean state) {
        String onOrOff = state ? on : off;
        return TextFormatting.GOLD + localize("tooltip.", "chargerState") + ": " + onOrOff;
    }

    public static String getEnergyUsageText(int usage) {
        String usageText = localize("tooltip.", ".energyPerTick", getFormattedNumber(usage));
        return TextFormatting.GOLD + localize("tooltip.", ".energyUsage") + ": " + TextFormatting.GRAY + usageText;
    }

    public static String getChargerRateText(int rate) {
        String rateText = rate > 0 ? localize("tooltip.", ".energyPerTick", getFormattedNumber(rate)) : localize("tooltip.", ".energy.none");
        return TextFormatting.GOLD + localize("tooltip.", ".chargerRate") + ": " + TextFormatting.GRAY + rateText;
    }

    public static String getEnergySendText(int send) {
        return TextFormatting.GOLD + localize("tooltip.", ".energySend") + ": " + TextFormatting.GRAY + localize("tooltip.", ".energyPerTick", getFormattedNumber(send));
    }

    public static String getEnergyReceiveText(int receive) {
        String usageText = receive < Integer.MAX_VALUE ? localize("tooltip.", ".energyPerTick", getFormattedNumber(receive)) : localize("tooltip.", ".energy.none");
        return TextFormatting.GOLD + localize("tooltip.", ".energyReceive") + ": " + TextFormatting.GRAY + usageText;
    }

    public static String getParticlesText(ParticleType particle) {
        return TextFormatting.GOLD + localize("tooltip.", ".particles") + ": " + TextFormatting.GRAY + localize("tooltip.", ".particles." + particle.ordinal());
    }

    public static String getHUDEnergyText(String packType, int percent, int energy) {
        String text = "";
        if (!Config.minimalEnergyHUD) {
            text += localize("gui.", ".hud." + packType + ".energy") + ": ";
        }
        if (percent > 0) {
            text += getColoredPercent(percent) + "%";
        } else {
            text += TextFormatting.RED + localize("gui.", ".hud.energy.depleted");
        }
        if (Config.showExactEnergyInHUD) {
            text += " (" + getFormattedNumber(energy) + " RF" + ")";
        }
        return text;
    }

    public static String getHUDStateText(Boolean engine, Boolean hover, Boolean charger, Boolean eHover) {
        String text = "";
        if (engine != null) {
            text += (engine ? TextFormatting.GREEN : TextFormatting.RED) + localize("gui.", ".hud.state.engine") + TextFormatting.RESET;
        }
        if (hover != null) {
            if (engine != null) {
                text += TextFormatting.GRAY + " - ";
            }
            text += (hover ? TextFormatting.GREEN : TextFormatting.RED) + localize("gui.", ".hud.state.hover") + TextFormatting.RESET;
        }
        if (charger != null) {
            if (hover != null || engine != null) {
                text += TextFormatting.GRAY + " - ";
            }
            text += (charger ? TextFormatting.GREEN : TextFormatting.RED) + localize("gui.", ".hud.state.charger");
        }
        if (eHover != null) {
            if (hover != null || engine != null || charger != null) {
                text += TextFormatting.GRAY + " - ";
            }
            text += (eHover ? TextFormatting.GREEN : TextFormatting.RED) + localize("gui.", ".hud.state.eHover");
        }
        return text;
    }

    public static String getColoredPercent(int percent) {
        if (percent > 70) {
            return TextFormatting.GREEN.toString() + percent;
        } else if (percent > 40) {
            return TextFormatting.YELLOW.toString() + percent;
        } else if (percent > 10) {
            return TextFormatting.GOLD.toString() + percent;
        } else {
            return TextFormatting.RED.toString() + percent;
        }
    }

    public static String getShiftText() {
        return TextFormatting.GRAY + localize("tooltip.", ".holdShift", TextFormatting.YELLOW.toString() + TextFormatting.ITALIC + localize("tooltip.", ".holdShift.shift") + TextFormatting.RESET + TextFormatting.GRAY);
    }

    public static boolean canShowDetails() {
        return !Config.holdShiftForDetails || KeyboardUtil.isShiftKeyDown();
    }

    public static String localize(String prefix, String suffix, Object... args) {
        String toLocalize = prefix + SimplyJetpacks.MODID + suffix;
        if (args != null && args.length > 0) {
            return translateToLocalFormatted(toLocalize, args);
        } else {
            return translateToLocal(toLocalize);
        }
    }

    // TODO: Add prefix and suffix translation to this.
    public static ITextComponent localizeNew(String prefix, String suffix, Object... args) {
        String toLocalize = prefix + SimplyJetpacks.MODID + suffix;
        if (args != null && args.length > 0) {
            return new TextComponentTranslation(toLocalize, args);
        } else {
            return new TextComponentTranslation(toLocalize);
        }
    }

    public static ITextComponent localizeNew(String unlocalized, Object... args) {
        String toLocalize = SimplyJetpacks.PREFIX + unlocalized;
        if (args != null && args.length > 0) {
            return new TextComponentTranslation(toLocalize, args);
        } else {
            return new TextComponentTranslation(toLocalize);
        }
    }

    public static void addDescriptionLines(List<String> list, String base, String color) {
        int i = 1;
        while (true) {
            String unlocalized = "tooltip." + SimplyJetpacks.PREFIX + base + ".description." + i;
            String localized = translateToLocal(unlocalized);
            if (unlocalized.equals(localized)) {
                break;
            }
            list.add(color + localized);
            i++;
        }
    }

    public static String getEnderiumBonusText() {
        return TextFormatting.BLUE + localize("tooltip.", ".enderium_energy_bonus", Config.gelidEnderiumEnergyUsageBonus);
    }
}
