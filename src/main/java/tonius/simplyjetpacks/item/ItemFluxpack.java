package tonius.simplyjetpacks.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;

import java.util.List;

public class ItemFluxpack extends ItemPack implements IHUDInfoProvider {

	public static final String TAG_ENERGY = "Energy";

	public ItemFluxpack(Item.Properties properties) {
		super(ArmorMaterial.IRON, EquipmentSlotType.CHEST, properties);
	}

	/*@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if(this.isOn(stack)) {
			this.chargeInventory(player, stack, this);
		}
	}

	public void toggleState(boolean on, ItemStack stack, String type, String tag, EntityPlayer player, boolean showState) {
		NBTHelper.setBoolean(stack, tag, !on);

		if (player != null && showState) {
			type = type != null && !type.equals("") ? "chat." + this.name + "." + type : "chat." + this.name + ".on";
			ITextComponent state = SJStringHelper.localizeNew(on ? "chat.disabled" : "chat.enabled");
			if (on) {
				state.setStyle(new Style().setColor(TextFormatting.RED));
			}
			else {
				state.setStyle(new Style().setColor(TextFormatting.GREEN));
			}
			ITextComponent msg = SJStringHelper.localizeNew(type, state);
			player.sendStatusMessage(msg, true);
		}
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void shiftInformation(ItemStack stack, List list) {
		list.add(SJStringHelper.getStateText(this.isOn(stack)));
		list.add(SJStringHelper.getEnergySendText(Packs.getTypeFromName(name).getFuelPerTickOut()));
		if (Packs.getTypeFromName(name).getFuelPerTickIn() > 0) {
			list.add(SJStringHelper.getEnergyReceiveText(Packs.getTypeFromName(name).getFuelPerTickIn()));
		}
		SJStringHelper.addDescriptionLines(list, "fluxpack", TextFormatting.GREEN.toString());
	}

	@SideOnly(Side.CLIENT)
	public String getHUDStatesInfo(ItemStack stack) {
		return SJStringHelper.getHUDStateText(this.isOn(stack), null, null);
	}*/
}
