package tonius.simplyjetpacks.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public interface IHUDInfoProvider {
	@OnlyIn(Dist.CLIENT)
	public void addHUDInfo(List<String> list, ItemStack stack, boolean showFuel, boolean showState);
}