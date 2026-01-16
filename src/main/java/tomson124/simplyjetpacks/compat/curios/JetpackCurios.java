package tomson124.simplyjetpacks.compat.curios;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import tomson124.simplyjetpacks.config.SimplyJetpacksConfig;
import tomson124.simplyjetpacks.util.JetpackUtil;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public record JetpackCurios(ItemStack stack) implements ICurio {
    @Override
    public ItemStack getStack() {
        return this.stack;
    }

    @Override
    public boolean canEquip(SlotContext context) {
        return this.isCurioJetpack();
    }

    @Override
    public boolean canEquipFromUse(SlotContext context) {
        return this.isCurioJetpack();
    }

    @Override
    public List<Component> getSlotsTooltip(List<Component> tooltips, Item.TooltipContext context) {
        return this.isCurioJetpack() ? tooltips : List.of();
    }

    private boolean isCurioJetpack() {
        return SimplyJetpacksConfig.enableCuriosIntegration.get() && JetpackUtil.getJetpack(this.stack).curios;
    }
}