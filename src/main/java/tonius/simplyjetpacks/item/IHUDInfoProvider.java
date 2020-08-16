package tonius.simplyjetpacks.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public interface IHUDInfoProvider {
	@SideOnly(Side.CLIENT)
	void addHUDInfo(List<String> list, ItemStack stack, boolean showFuel, boolean showState);
}