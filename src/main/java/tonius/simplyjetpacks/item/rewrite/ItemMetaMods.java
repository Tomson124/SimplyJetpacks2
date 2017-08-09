package tonius.simplyjetpacks.item.rewrite;

import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.SJStringHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMetaMods extends ItemMeta {

	private final int numItems;

	public ItemMetaMods(String registryName) {
		super(registryName);

		numItems = MetaItemsMods.values().length;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		return "item.simplyjetpacks." + MetaItemsMods.values()[i].getName();
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		if (MetaItemsMods.values()[i].getRarity() != null) {
			return MetaItemsMods.values()[i].getRarity();
		}
		return super.getRarity(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		if(MetaItemsMods.values()[i].getKeyTooltip() != null)
		{
			if(SJStringHelper.canShowDetails())
			{
				SJStringHelper.addDescriptionLines(list, MetaItemsMods.values()[i].getKeyTooltip(), TextFormatting.GRAY.toString());
			}
			else
			{
				list.add(SJStringHelper.getShiftText());
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		if (MetaItemsMods.values()[i].getGlow())
		{
			return true;
		}
		return super.hasEffect(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		if (ModItems.integrateEIO) {
			for (MetaItemsMods itemsMods : MetaItemsMods.ITEMS_EIO) {
				list.add(new ItemStack(item, 1, itemsMods.ordinal()));
			}
		}
		if (ModItems.integrateTE) {
			for (MetaItemsMods itemsMods : MetaItemsMods.ITEMS_TE) {
				list.add(new ItemStack(item, 1, itemsMods.ordinal()));
			}
		}
	}

	public void registerItemModel()
	{
		for(int i = 0; i < numItems; i++)
		{
			SimplyJetpacks.proxy.registerItemRenderer(this, i, MetaItemsMods.getTypeFromMeta(i).getName());
		}
	}
}
