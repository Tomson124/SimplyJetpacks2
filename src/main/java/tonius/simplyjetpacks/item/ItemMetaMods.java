package tonius.simplyjetpacks.item;

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
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.SJStringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemMetaMods extends ItemMeta {

	private final int numItems;

	public ItemMetaMods(String registryName) {
		super(registryName);
		numItems = MetaItemsMods.values().length;
	}

	@Nonnull
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		return "item.simplyjetpacks." + MetaItemsMods.values()[i].getName();
	}

	@Nonnull
	@Override
	public EnumRarity getRarity(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		if (MetaItemsMods.values()[i].getRarity() != null) {
			return MetaItemsMods.values()[i].getRarity();
		}
		return super.getRarity(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		if (MetaItemsMods.values()[i].getKeyTooltip() != null) {
			if (SJStringUtil.canShowDetails()) {
				SJStringUtil.addDescriptionLines(tooltip, MetaItemsMods.values()[i].getKeyTooltip(), TextFormatting.GRAY.toString());
			} else {
				tooltip.add(SJStringUtil.getShiftText());
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack) {
		int i = MathHelper.clamp(itemStack.getItemDamage(), 0, numItems - 1);
		return MetaItemsMods.values()[i].getGlow() || super.hasEffect(itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(@Nonnull CreativeTabs creativeTabs, @Nonnull NonNullList<ItemStack> list) {
		if (isInCreativeTab(creativeTabs)) {
			if (ModItems.integrateVanilla){
				for (MetaItemsMods item : MetaItemsMods.ITEMS_VANILLA) {
					ItemStack stack;
					stack = new ItemStack(this, 1, item.ordinal());
					list.add(stack);
				}
			}
			if (ModItems.integrateEIO) {
				for (MetaItemsMods item : MetaItemsMods.ITEMS_EIO) {
					ItemStack stack;
					stack = new ItemStack(this, 1, item.ordinal());
					list.add(stack);
				}
			}
			if (ModItems.integrateMek) {
				for (MetaItemsMods item : MetaItemsMods.ITEMS_MEK) {
					ItemStack stack;
					stack = new ItemStack(this, 1, item.ordinal());
					list.add(stack);
				}
			}
			if (ModItems.integrateIE) {
				for (MetaItemsMods item : MetaItemsMods.ITEMS_IE) {
					ItemStack stack;
					stack = new ItemStack(this, 1, item.ordinal());
					list.add(stack);
				}
			}
			if (ModItems.integrateTE) {
				if (ModItems.integrateRA) {
					for (MetaItemsMods item : MetaItemsMods.ITEMS_TE_RA) {
						ItemStack stack;
						stack = new ItemStack(this, 1, item.ordinal());
						list.add(stack);
					}
				} else {
					for (MetaItemsMods item : MetaItemsMods.ITEMS_TE) {
						ItemStack stack;
						stack = new ItemStack(this, 1, item.ordinal());
						list.add(stack);
					}
				}
			}
		}
	}

	public void registerItemModel() {
		for (int i = 0; i < numItems; i++) {
			SimplyJetpacks.proxy.registerItemRenderer(this, i, MetaItemsMods.getTypeFromMeta(i).getName());
		}
	}
}
