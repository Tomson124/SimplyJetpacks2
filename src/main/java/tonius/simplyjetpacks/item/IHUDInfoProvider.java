package tonius.simplyjetpacks.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public interface IHUDInfoProvider {
	@SideOnly(Side.CLIENT)
	public void addHUDInfo(RenderGameOverlayEvent.Text event, ItemStack stack, boolean showFuel, boolean showState);
}