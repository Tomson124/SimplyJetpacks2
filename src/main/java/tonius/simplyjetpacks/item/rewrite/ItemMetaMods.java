package tonius.simplyjetpacks.item.rewrite;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import tonius.simplyjetpacks.SimplyJetpacks;
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

import javax.annotation.Nullable;
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
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		return "item.simplyjetpacks." + MetaItemsMods.values()[i].getName();
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack)
	{
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		if (MetaItemsMods.values()[i].getRarity() != null) {
			return MetaItemsMods.values()[i].getRarity();
		}
		return super.getRarity(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		if(MetaItemsMods.values()[i].getKeyTooltip() != null)
		{
			if(SJStringHelper.canShowDetails())
			{
				SJStringHelper.addDescriptionLines(tooltip, MetaItemsMods.values()[i].getKeyTooltip(), TextFormatting.GRAY.toString());
			}
			else
			{
				tooltip.add(SJStringHelper.getShiftText());
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack)
	{
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		if (MetaItemsMods.values()[i].getGlow())
		{
			return true;
		}
		return super.hasEffect(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubItems(CreativeTabs tab, NonNullList list)
	{
		for(int i = 0; i < numItems; i++)
		{
			list.add(new ItemStack(this, 1, i));
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
