package tonius.simplyjetpacks.item.rewrite;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.ItemRegistered;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.util.SJStringHelper;

public class ItemMetaVanilla extends ItemRegistered {
	
	private final int numItems;

	public ItemMetaVanilla(String registryName)
	{
		super(registryName);

		this.setUnlocalizedName(SimplyJetpacks.PREFIX + registryName);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(ModCreativeTab.instance);

		numItems = MetaItemsVanilla.values().length;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		return "item.simplyjetpacks." + MetaItemsVanilla.values()[i].getName();
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		if (MetaItemsVanilla.values()[i].getRarity() != null) {
			return MetaItemsVanilla.values()[i].getRarity();
		}
		return super.getRarity(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		if(MetaItemsVanilla.values()[i].getKeyTooltip() != null)
		{
			if(SJStringHelper.canShowDetails())
			{
				SJStringHelper.addDescriptionLines(list, MetaItemsVanilla.values()[i].getKeyTooltip(), TextFormatting.GRAY.toString());
			}
			else
			{
				list.add(SJStringHelper.getShiftText());
			}
		}
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
			SimplyJetpacks.proxy.registerItemRenderer(this, i, MetaItemsVanilla.getTypeFromMeta(i).getName());
		}
	}
}
