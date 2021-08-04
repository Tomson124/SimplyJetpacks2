package stormedpanda.simplyjetpacks.client.hud;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IHUDInfoProvider {
    void addHUDInfo(ItemStack stack, List<MutableComponent> list);
}
