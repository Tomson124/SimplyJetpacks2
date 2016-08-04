package tonius.simplyjetpacks.item;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IHUDInfoProvider {
    
    @SideOnly(Side.CLIENT)
    public void addHUDInfo(List<String> list, ItemStack stack, boolean showFuel, boolean showState);
    
}
