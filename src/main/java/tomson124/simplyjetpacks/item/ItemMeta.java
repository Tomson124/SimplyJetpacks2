package tomson124.simplyjetpacks.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.util.SJStringHelper;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMeta extends ItemRegistered {

	private final int numItems;

	public ItemMeta(String registryName) {
		super(registryName);

		this.setUnlocalizedName(SimplyJetpacks.PREFIX + registryName);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(SimplyJetpacks.creativeTab);

		numItems = MetaItems.values().length;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		return "item.simplyjetpacks." + MetaItems.values()[i].getName();
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		if (MetaItems.values()[i].getRarity() != null) {
			return MetaItems.values()[i].getRarity();
		}
		return super.getRarity(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		if (MetaItems.values()[i].getKeyTooltip() != null) {
			if (SJStringHelper.canShowDetails()) {
				SJStringHelper.addDescriptionLines(tooltip, MetaItems.values()[i].getKeyTooltip(), TextFormatting.GRAY.toString());
			} else {
				tooltip.add(SJStringHelper.getShiftText());
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubItems(CreativeTabs creativeTabs, NonNullList list) {
		if (isInCreativeTab(creativeTabs)) {
			for (int i = 0; i < numItems; i++) {
				list.add(new ItemStack(this, 1, i));
			}
		}
	}

	public void registerItemModel() {
		for (int i = 0; i < numItems; i++) {
			SimplyJetpacks.proxy.registerItemRenderer(this, i, MetaItems.getTypeFromMeta(i).getName());
		}
	}
}
