package stormedpanda.simplyjetpacks.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.config.SimplyJetpacksConfig;
import stormedpanda.simplyjetpacks.items.JetpackItem;

import java.util.List;

public class SJTextUtil {

    private static final Component on = translate("misc", "enabled", ChatFormatting.GREEN);
    private static final Component off = translate("misc", "disabled", ChatFormatting.RED);
    private static final Component notAvailable = translate("misc", "notAvailable", ChatFormatting.DARK_GRAY);

    private static final String ENERGY_FORMAT = "%,d";

    public static Component translate(String prefix, String suffix, ChatFormatting style, Object... params) {
        String key = String.format("%s.%s.%s", prefix, SimplyJetpacks.MODID, suffix);
        return new TranslatableComponent(key, params).withStyle(style);
    }

    public static Component translate(String prefix, String suffix, Object... params) {
        String key = String.format("%s.%s.%s", prefix, SimplyJetpacks.MODID, suffix);
        return new TranslatableComponent(key, params);
    }

    public static Component energy(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energy", s1);
    }

    public static Component energyPerTick(int amount) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        return translate("misc", "energyPerTick", s1);
    }

    public static Component energyWithMax(int amount, int max) {
        String s1 = String.format(ENERGY_FORMAT, amount);
        String s2 = String.format(ENERGY_FORMAT, max);
        return translate("misc", "energyWithMax", s1, s2);
    }

    public static Component getShiftText() {
        return translate("tooltip", "showDetails", new TextComponent("Shift").withStyle(ChatFormatting.GOLD));
    }

    public static void addBaseInfo(ItemStack stack, List<Component> list) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        list.add(translate("tooltip", "tier", jetpack.tier));
        if (jetpack.isCreative()) {
            list.add(translate("tooltip", "infiniteEnergy"));
        } else {
            list.add(energyWithMax(jetpack.getEnergyStored(stack), jetpack.getMaxEnergyStored(stack)));
        }
    }

    public static void addShiftInfo(ItemStack stack, List<Component> list) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        list.add(translate("tooltip", "itemJetpack.engine", ChatFormatting.GOLD, jetpack.isEngineOn(stack) ? on : off));
        list.add(translate("tooltip", "itemJetpack.hover", ChatFormatting.GOLD, jetpack.isHoverOn(stack) ? on : off));
/*        if (jetpack.getType().canEHover()) {
            list.add(translate("tooltip", "itemJetpack.emergencyHover", SJStylesUtil.GOLD, jetpack.isEHoverOn(stack) ? on : off));
        }*/
        if (jetpack.getType().canCharge()) {
            list.add(translate("tooltip", "itemJetpack.charger", ChatFormatting.GOLD, jetpack.isChargerOn(stack) ? on : off));
        }
        if (!jetpack.isCreative()) {
            list.add(translate("tooltip", "itemJetpack.energyUsage", ChatFormatting.GOLD, energyPerTick(jetpack.getEnergyUsage(stack))));
        }
        Component particle = translate("tooltip", "particle." + jetpack.getType().getParticleType(stack).ordinal(), ChatFormatting.WHITE);
        list.add(translate("tooltip", "itemJetpack.particleType", ChatFormatting.GOLD, particle));
    }

    public static void addHUDInfoText(ItemStack stack, List<Component> list) {
        list.add(getEnergyText(stack));
        list.add(getHUDStates(stack));
    }

    public static Component getEnergyText(ItemStack stack) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        if (jetpack.isCreative()) {
            return translate("hud", "energyDisplay", translate("hud", "infiniteEnergy", ChatFormatting.WHITE));
        }
        int percent = (int) Math.ceil((double) jetpack.getEnergyStored(stack) / (double) jetpack.getCapacity() * 100D);
        Component percentageText = getColoredPercent(percent);
        Component exactText = energy(jetpack.getEnergyStored(stack));
        if (SimplyJetpacksConfig.CLIENT.showExactEnergy.get()) {
            return translate("hud", "energyDisplayExtra", percentageText, exactText);
        } else {
            return translate("hud", "energyDisplay", percentageText);
        }
    }

    public static Component getColoredPercent(int percent) {
        if (percent > 70) {
            return new TextComponent(String.format("%s%%", percent)).withStyle(ChatFormatting.GREEN);
        } else if (percent > 40) {
            return new TextComponent(String.format("%s%%", percent)).withStyle(ChatFormatting.YELLOW);
        } else if (percent > 10) {
            return new TextComponent(String.format("%s%%", percent)).withStyle(ChatFormatting.GOLD);
        } else if (percent > 0) {
            return new TextComponent(String.format("%s%%", percent)).withStyle(ChatFormatting.RED);
        } else {
            return translate("hud", "energyDepleted", ChatFormatting.RED);
        }
    }

    public static Component getHUDStates(ItemStack stack) {
        JetpackItem jetpack = (JetpackItem) stack.getItem();
        ChatFormatting on = ChatFormatting.GREEN;
        ChatFormatting off = ChatFormatting.RED;
        ChatFormatting notAvailable = ChatFormatting.DARK_GRAY;
        Component engineState = translate("hud", "engine", jetpack.isEngineOn(stack) ? on : off);
        Component hoverState = translate("hud", "hover", jetpack.isHoverOn(stack) ? on : off);
        Component eHoverState = translate("hud", "eHover", jetpack.getType().canEHover() ? (jetpack.isEHoverOn(stack) ? on : off) : notAvailable);
        Component chargerState = translate("hud", "charger", jetpack.getType().canCharge() ? (jetpack.isChargerOn(stack) ? on : off) : notAvailable);
        return translate("hud", "jetpackStates", engineState, hoverState, eHoverState, chargerState);
    }

    public static Component getStateToggle(String state, boolean value) {
        return translate("chat", "itemJetpack." + state, value ? on : off);
    }

    public static Component getEmergencyText() {
        return translate("chat", "itemJetpack.emergencyHoverModeActivated", ChatFormatting.RED);
    }
}
