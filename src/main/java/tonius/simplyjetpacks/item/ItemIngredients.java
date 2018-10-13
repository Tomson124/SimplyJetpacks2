package tonius.simplyjetpacks.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.util.SJStringHelper;

import javax.annotation.Nullable;
import java.util.List;

public class ItemIngredients extends ItemRegistered {

	public ItemIngredients(String registryName) {
		super(registryName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (ItemsSJ2.getFromName(name).getKeyTooltip() != null) {
			if (SJStringHelper.canShowDetails()) {
				SJStringHelper.addDescriptionLines(tooltip, ItemsSJ2.getFromName(name).getKeyTooltip(), TextFormatting.GRAY.toString());
			} else {
				tooltip.add(SJStringHelper.getShiftText());
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack) {
		if (ItemsSJ2.getFromName(name).getGlow()) {
			return true;
		}
		return super.hasEffect(itemStack);
	}
}
