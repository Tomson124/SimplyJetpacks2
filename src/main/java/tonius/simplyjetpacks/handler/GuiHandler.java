package tonius.simplyjetpacks.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public abstract class GuiHandler implements IGuiHandler
{
	public static final int PACK = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
			case PACK:
				ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
				if(chestplate != null && chestplate.getItem() instanceof ItemPack)
				{
					ItemPack packItem = (ItemPack) chestplate.getItem();
					PackBase pack = packItem.getPack(chestplate);
					if(pack != null)
					{
						return new ContainerPack(chestplate, packItem, pack);
					}
				}
		}
		return null;
	}

    /*@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
        case PACK:
            ItemStack chestplate = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            if (chestplate != null && chestplate.getItem() instanceof ItemPack) {
                ItemPack packItem = (ItemPack) chestplate.getItem();
                PackBase pack = packItem.getPack(chestplate);
                if (pack != null) {
                    return new GuiPack(new ContainerPack(chestplate, packItem, pack));
                }
            }
        }
        return null;
    } TODO: Readd GUIs */
}