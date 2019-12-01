package tonius.simplyjetpacks.item;

import net.minecraft.item.Item;

public class ItemIngredients extends ItemRegistered {

	private final String keyTooltip;

	public ItemIngredients(String registryName, String keyTooltip, Item.Properties builder) {
		super(builder);
		this.keyTooltip = keyTooltip;
	}

	/*@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (this.keyTooltip != null) {
			if (SJStringHelper.canShowDetails()) {
				SJStringHelper.addDescriptionLines(tooltip, this.keyTooltip, TextFormatting.GRAY.toString());
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
	}*/
}
