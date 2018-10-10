package tonius.simplyjetpacks.item;

import cofh.redstoneflux.api.IEnergyContainerItem;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.capability.CapabilityProviderEnergy;
import tonius.simplyjetpacks.capability.EnergyConversionStorage;
import tonius.simplyjetpacks.client.model.PackModelType;
import tonius.simplyjetpacks.client.util.RenderUtils;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModEnchantments;
import tonius.simplyjetpacks.setup.ModItems;
import tonius.simplyjetpacks.util.EquipmentSlotHelper;
import tonius.simplyjetpacks.util.ItemHelper;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFluxpack extends ItemPack implements ISpecialArmor, IEnergyContainerItem, IHUDInfoProvider {

	public static final String TAG_ENERGY = "Energy";
	public static final String TAG_ON = "PackOn";

	public String name;

	public ItemFluxpack(String name) {
		super(EnumHelper.addArmorMaterial("JETPACK_SJ", "jetpack", 0, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0), 2, EntityEquipmentSlot.CHEST, name);
	}

	@Override
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
	}
}
