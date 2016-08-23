package tonius.simplyjetpacks.item.rewrite;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.item.IHUDInfoProvider;
import tonius.simplyjetpacks.item.ItemPack;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;

import javax.annotation.Nullable;
import java.util.List;

public class ItemJetpack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem, IHUDInfoProvider {

	private static final String TAG_ENERGY = "Energy";
	protected static final String TAG_ON = "PackOn";

	public String name;
	public boolean showTier = true;
	public boolean hasFuelIndicator = true;
	public boolean hasStateIndicators = true;
	public FuelType fuelType = FuelType.ENERGY;
	public boolean usesFuel = true;

	private final int numItems;

	public ItemJetpack(String name) {
		super(ArmorMaterial.IRON, 2, EntityEquipmentSlot.CHEST);
		this.name = name;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(ModCreativeTab.instance);
		this.setRegistryName(name);

		numItems = Jetpack.values().length;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List<ItemStack> List) {
		for (int j = 0; j < numItems; ++j) {
			List.add(new ItemStack(item, 1, j));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, numItems - 1);
		return Jetpack.values()[i].unlocalisedName;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		information(stack, this, tooltip);
		if(SJStringHelper.canShowDetails()) {
			shiftInformation(stack, tooltip);
		}
		else {
			tooltip.add(SJStringHelper.getShiftText());
		}
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void information(ItemStack stack, ItemJetpack item, List list) {
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, numItems - 1);
		if(this.showTier) {
			list.add(SJStringHelper.getTierText(Jetpack.values()[i].getTier()));
		}
		list.add(SJStringHelper.getFuelText(this.fuelType, item.getFuelStored(stack), Jetpack.values()[i].getFuelCapacity(), !this.usesFuel));
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void shiftInformation(ItemStack stack, List list) {
		list.add(SJStringHelper.getStateText(this.isOn(stack)));
		String key = SimplyJetpacks.proxy.getPackGUIKey();
		if(key != null) {
			list.add(SJStringHelper.getPackGUIText(key));
		}
	}

	public boolean isOn(ItemStack stack) {
		return NBTHelper.getBooleanFallback(stack, TAG_ON, true);
	}

	// fuel
	public int getFuelStored(ItemStack stack) {
		return this.getEnergyStored(stack);
	}

	public int getMaxFuelStored(ItemStack stack) {
		return this.getMaxEnergyStored(stack);
	}

	public int addFuel(ItemStack stack, int maxAdd, boolean simulate) {
		int energy = this.getEnergyStored(stack);
		int energyReceived = Math.min(this.getMaxEnergyStored(stack) - energy, maxAdd);
		if(!simulate) {
			energy += energyReceived;
			NBTHelper.setInt(stack, TAG_ENERGY, energy);
		}
		return energyReceived;
	}

	public int useFuel(ItemStack stack, int maxUse, boolean simulate) {
		int energy = this.getEnergyStored(stack);
		int energyExtracted = Math.min(energy, maxUse);
		if(!simulate) {
			energy -= energyExtracted;
			NBTHelper.setInt(stack, TAG_ENERGY, energy);
		}
		return energyExtracted;
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		int i = MathHelper.clamp_int(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyReceived = Math.min(this.getMaxEnergyStored(container) - energy, Math.min(maxReceive, Jetpack.values()[i].getFuelPerTickIn()));
		if(!simulate) {
			energy += energyReceived;
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		int i = MathHelper.clamp_int(container.getItemDamage(), 0, numItems - 1);
		int energy = this.getEnergyStored(container);
		int energyExtracted = Math.min(energy, Math.min(maxExtract, Jetpack.values()[i].getFuelPerTickOut()));
		if(!simulate) {
			energy -= energyExtracted;
			NBTHelper.setInt(container, TAG_ENERGY, energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return NBTHelper.getInt(container, TAG_ENERGY);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		int i = MathHelper.clamp_int(container.getItemDamage(), 0, numItems - 1);
		return Jetpack.values()[i].getFuelCapacity();
	}

	// HUD info
	@Override
	@SideOnly(Side.CLIENT)
	public void addHUDInfo(List<String> list, ItemStack stack, boolean showFuel, boolean showState) {
		if(showFuel && this.hasFuelIndicator) {
			list.add(this.getHUDFuelInfo(stack, this));
		}
		if(showState && this.hasStateIndicators) {
			list.add(this.getHUDStatesInfo(stack, this));
		}
	}

	@SideOnly(Side.CLIENT)
	public String getHUDFuelInfo(ItemStack stack, ItemJetpack item)
	{
		int fuel = item.getFuelStored(stack);
		int maxFuel = item.getMaxFuelStored(stack);
		int percent = (int) Math.ceil((double) fuel / (double) maxFuel * 100D);
		return SJStringHelper.getHUDFuelText(this.name, percent, this.fuelType, fuel);
	}

	@SideOnly(Side.CLIENT)
	public String getHUDStatesInfo(ItemStack stack, ItemJetpack item)
	{
		return null;
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, 1, 0);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
	}

	public void registerItemModel() {
		for(int i = 0; i < numItems; i++) {
			SimplyJetpacks.proxy.registerItemRenderer(this, i, Jetpack.getTypeFromMeta(i).getBaseName());
		}
	}
}
