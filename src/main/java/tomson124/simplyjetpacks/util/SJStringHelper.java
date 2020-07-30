package tomson124.simplyjetpacks.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import tomson124.simplyjetpacks.config.Config;
import tomson124.simplyjetpacks.setup.FuelType;
import tomson124.simplyjetpacks.setup.ParticleType;
import tomson124.simplyjetpacks.SimplyJetpacks;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidRegistry;

import java.text.DecimalFormat;
import java.util.List;

import static net.minecraft.util.text.translation.I18n.translateToLocal;
import static net.minecraft.util.text.translation.I18n.translateToLocalFormatted;

public abstract class SJStringHelper {
	private static final DecimalFormat formatter = new DecimalFormat("###,###");

	public static String getFormattedNumber(int number) {
		return formatter.format(number);
	}

	public static String getTierText(int tier) {
		return localize("tooltip.tier", tier);
	}

	public static String getFuelText(FuelType fuelType, int amount, int max, boolean infinite) {
		if (infinite) {
			return TextFormatting.GRAY + localize("tooltip.fuel.infinite." + fuelType.toString().toLowerCase());
		}
		return TextFormatting.GRAY + getFormattedNumber(amount) + " / " + getFormattedNumber(max) + fuelType.suffix;
	}

	public static String getStateText(boolean state) {
		String onOrOff = state ? TextFormatting.GREEN + localize("tooltip.on") : TextFormatting.RED + localize("tooltip.off");
		return TextFormatting.GOLD + localize("tooltip.state") + ": " + onOrOff;
	}

	public static String getHoverModeText(boolean state) {
		String enabledOrDisabled = state ? TextFormatting.GREEN + localize("tooltip.enabled") : TextFormatting.RED + localize("tooltip.disabled");
		return TextFormatting.GOLD + localize("tooltip.hoverMode") + ": " + enabledOrDisabled;
	}

	public static String getChargerStateText(boolean state) {
		String onOrOff = state ? TextFormatting.GREEN + localize("tooltip.enabled") : TextFormatting.RED + localize("tooltip.disabled");
		return TextFormatting.GOLD + localize("tooltip.chargerState") + ": " + onOrOff;
	}

	public static String getFuelFluidText(String fluidName) {
		fluidName = localize(FluidRegistry.getFluid(fluidName).getUnlocalizedName(), false, (Object[]) null);
		return TextFormatting.GOLD + localize("tooltip.fuelFluid") + ": " + TextFormatting.GRAY + fluidName;
	}

	public static String getFuelUsageText(FuelType fuelType, int usage) {
		String usageText = getFormattedNumber(usage) + fuelType.suffix + "/t";
		return TextFormatting.GOLD + localize("tooltip.fuelUsage") + ": " + TextFormatting.GRAY + usageText;
	}

	public static String getChargerRateText(int rate) {
		String rateText = rate > 0 ? getFormattedNumber(rate) + " RF/t" : localize("tooltip.energy.none");
		return TextFormatting.GOLD + localize("tooltip.chargerRate") + ": " + TextFormatting.GRAY + rateText;
	}

	public static String getEnergySendText(int send) {
		return TextFormatting.GOLD + localize("tooltip.energySend") + ": " + TextFormatting.GRAY + getFormattedNumber(send) + " RF/t";
	}

	public static String getEnergyReceiveText(int receive) {
		String usageText = receive < Integer.MAX_VALUE ? getFormattedNumber(receive) + " RF/t" : localize("tooltip.energy.none");
		return TextFormatting.GOLD + localize("tooltip.energyReceive") + ": " + TextFormatting.GRAY + usageText;
	}

	public static String getParticlesText(ParticleType particle) {
		return TextFormatting.GOLD + localize("tooltip.particles") + ": " + TextFormatting.GRAY + localize("tooltip.particles." + particle.ordinal());
	}

	public static String getPackGUIText(String key) {
		return TextFormatting.AQUA.toString() + TextFormatting.ITALIC + localize("tooltip.packGUIKey", key);
	}

	public static String getHUDFuelText(String prefix, int percent, FuelType fuelType, int fuel) {
		String text = "";
		if (!Config.minimalFuelHUD) {
			text += localize("gui.hud." + prefix + ".fuel") + ": ";
		}
		if (percent > 0) {
			text += getColoredPercent(percent) + "%";
		} else {
			text += TextFormatting.DARK_RED + localize("gui.hud.fuel.depleted");
		}
		if (Config.showExactFuelInHUD) {
			text += " (" + getFormattedNumber(fuel) + fuelType.suffix + ")";
		}
		return text;
	}

	public static String getHUDStateText(Boolean engine, Boolean hover, Boolean charger) {
		String text = "";
		if (engine != null) {
			text += (engine ? TextFormatting.GREEN : TextFormatting.DARK_RED) + localize("gui.hud.state.engine") + TextFormatting.RESET;
		}
		if (hover != null) {
			if (engine != null) {
				text += TextFormatting.GRAY + " - ";
			}
			text += (hover ? TextFormatting.GREEN : TextFormatting.DARK_RED) + localize("gui.hud.state.hover") + TextFormatting.RESET;
		}
		if (charger != null) {
			if (hover != null || engine != null) {
				text += TextFormatting.GRAY + " - ";
			}
			text += (charger ? TextFormatting.GREEN : TextFormatting.DARK_RED) + localize("gui.hud.state.charger");
		}
		return text;
	}

	public static String getColoredPercent(int percent) {
		if (percent > 70) {
			return TextFormatting.GREEN.toString() + percent;
		} else if (percent > 40 && percent <= 70) {
			return TextFormatting.YELLOW.toString() + percent;
		} else if (percent > 10 && percent <= 40) {
			return TextFormatting.GOLD.toString() + percent;
		} else {
			return TextFormatting.RED.toString() + percent;
		}
	}

	public static String getShiftText() {
		return TextFormatting.GRAY + localize("tooltip.holdShift", TextFormatting.YELLOW.toString() + TextFormatting.ITALIC + localize("tooltip.holdShift.shift") + TextFormatting.RESET + TextFormatting.GRAY);
	}

	public static boolean canShowDetails() {
		return !Config.holdShiftForDetails || StringHelper.isShiftKeyDown();
	}

	public static String localize(String unlocalized, Object... args) {
		return localize(unlocalized, true, args);
	}

	public static String localize(String unlocalized, boolean prefix, Object... args) {
		String toLocalize = (prefix ? SimplyJetpacks.PREFIX : "") + unlocalized;
		if(args != null && args.length > 0)
		{
			return translateToLocalFormatted(toLocalize, args);
		}
		else
		{
			return translateToLocal(toLocalize);
		}
	}

	public static ITextComponent localizeNew(String unlocalized, Object... args) {
		String toLocalize = SimplyJetpacks.PREFIX + unlocalized;
		if(args != null && args.length > 0)
		{
			return new TextComponentTranslation(toLocalize, args);
		}
		else
		{
			return new TextComponentTranslation(toLocalize);
		}
	}

	public static void addDescriptionLines(List<String> list, String base, String color) {
		int i = 1;
		while (true) {
			String unlocalized = SimplyJetpacks.PREFIX + "tooltip." + base + ".description." + i;
			String localized = translateToLocal(unlocalized);
			if (unlocalized.equals(localized)) {
				break;
			}
			list.add(color + localized);
			i++;
		}
	}

	public static void addDescriptionLines(List<String> list, String base) {
		addDescriptionLines(list, base, "");
	}
}
