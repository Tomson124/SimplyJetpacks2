package stormedpanda.simplyjetpacks.item;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.energy.EnergyStorageImpl;
import stormedpanda.simplyjetpacks.energy.IEnergyContainer;
import stormedpanda.simplyjetpacks.hud.IHUDInfoProvider;
import stormedpanda.simplyjetpacks.model.JetpackModel;
import stormedpanda.simplyjetpacks.util.Constants;
import stormedpanda.simplyjetpacks.util.JetpackUtil;
import stormedpanda.simplyjetpacks.util.NBTUtil;
import stormedpanda.simplyjetpacks.util.SJTextUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

// TODO: re-implement Potato Jetpack.
public class PotatoJetpackItem extends JetpackItem {

    public PotatoJetpackItem() {
        super(JetpackType.POTATO);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(SJTextUtil.translate("tooltip", "jetpack_potato"));
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return new ResourceLocation(SimplyJetpacks.MODID, "textures/models/armor/jetpack_potato.flat").toString();
    }

    @OnlyIn(Dist.CLIENT)
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return null;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (!player.isSpectator() && stack == JetpackUtil.getFromBothSlots(player)) {
            //flyUser(player, stack, this);
        }
    }

    /* IHUDInfoProvider start */

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addHUDInfo(ItemStack stack, List<ITextComponent> list) {
        list.add(SJTextUtil.getEnergyText(stack));
        // TODO: show "ERROR!" message.
    }

    /* IHUDInfoProvider end */
}
