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
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.setup.FuelType;
import tonius.simplyjetpacks.setup.ModCreativeTab;
import tonius.simplyjetpacks.util.NBTHelper;
import tonius.simplyjetpacks.util.SJStringHelper;

import java.util.List;

public class ItemJetpack extends ItemArmor implements ISpecialArmor, IEnergyContainerItem, IFluidContainerItem {

	private static final String TAG_ENERGY = "Energy";
	private static final String TAG_FLUID = "Fluid";
	protected static final String TAG_ON = "PackOn";

	public String fuelFluid = null;

	public String name;
	public boolean showTier = true;
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
		switch(this.fuelType) {
			case ENERGY:
			default:
				return this.getEnergyStored(stack);
			case FLUID:
				FluidStack stored = this.getFluid(stack);
				return stored != null ? stored.amount : 0;
		}
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		if(this.fuelType != FuelType.ENERGY) {
			return 0;
		}
		return NBTHelper.getInt(container, TAG_ENERGY);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return 0;
	}

	@Override
	public FluidStack getFluid(ItemStack container) {
		if(this.fuelType != FuelType.FLUID || this.fuelFluid == null) {
			return null;
		}
		int amount = NBTHelper.getInt(container, TAG_FLUID);
		return amount > 0 ? new FluidStack(FluidRegistry.getFluid(this.fuelFluid), amount) : null;
	}

	@Override
	public int getCapacity(ItemStack container) {
		return 0;
	}

	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
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
