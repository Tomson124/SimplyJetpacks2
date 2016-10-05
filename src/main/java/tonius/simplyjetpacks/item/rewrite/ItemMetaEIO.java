package tonius.simplyjetpacks.item.rewrite;

import tonius.simplyjetpacks.Log;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.util.SJStringHelper;
import tonius.simplyjetpacks.util.StringHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class ItemMetaEIO extends ItemMeta {

	private final int numItems;

	public ItemMetaEIO(String registryName) {
		super(registryName);

		numItems = MetaItemsEIO.values().length;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		return "item.simplyjetpacks." + MetaItemsEIO.values()[i].getName();
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		if (MetaItemsEIO.values()[i].getRarity() != null) {
			return MetaItemsEIO.values()[i].getRarity();
		}
		return super.getRarity(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		if(MetaItemsEIO.values()[i].getKeyTooltip() != null)
		{
			if(SJStringHelper.canShowDetails())
			{
				SJStringHelper.addDescriptionLines(list, MetaItemsEIO.values()[i].getKeyTooltip(), StringHelper.LIGHT_GRAY);
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
		if (MetaItemsEIO.values()[i].getGlow())
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
		for(int i = 0; i < numItems; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	public void registerItemModel()
	{
		for(int i = 0; i < numItems; i++)
		{
			SimplyJetpacks.proxy.registerItemRenderer(this, i, MetaItemsEIO.getTypeFromMeta(i).getName());
		}
	}
}
