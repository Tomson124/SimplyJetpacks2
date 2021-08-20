package stormedpanda.simplyjetpacks.item;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.handlers.CommonJetpackHandler;
import stormedpanda.simplyjetpacks.sound.SJSounds;
import stormedpanda.simplyjetpacks.util.KeyboardUtil;
import stormedpanda.simplyjetpacks.util.NBTUtil;
import stormedpanda.simplyjetpacks.util.SJTextUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

// TODO: re-implement Potato Jetpack.
public class PotatoJetpackItem extends JetpackItem {

    private static final String TAG_FIRED = "JetpackPotatoFired";
    private static final String TAG_ROCKET_TIMER = "JetpackPotatoRocketTimer";
    private static final String TAG_ROCKET_TIMER_SET = "JetpackPotatoRocketTimerSet";
    
    public PotatoJetpackItem() {
        super(JetpackType.POTATO);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(SJTextUtil.translate("tooltip", "jetpack_potato"));
        if (KeyboardUtil.isHoldingShift()) {
            tooltip.add(SJTextUtil.translate("tooltip", "jetpack_potato.warning", TextFormatting.RED));
        } else {
            tooltip.add(SJTextUtil.getShiftText());
        }
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
        this.flyUser(player, stack, this, true);
    }

    @Override
    public void flyUser(PlayerEntity player, ItemStack stack, JetpackItem item, Boolean force) {
        if (this.isFired(stack)) {
            super.flyUser(player, stack, item, true);
            player.yHeadRot += 37.5F;
            if (item.getEnergy(stack) <= 0) {
                player.inventory.setItem(player.inventory.findSlotMatchingItem(stack), ItemStack.EMPTY);
                if (!player.level.isClientSide()) {
                    player.level.explode(player, player.getX(), player.getY(), player.getZ(), 4.0F, Explosion.Mode.NONE);
                }
                for (int i = 0; i <= new Random().nextInt(3) + 4; i++) {
                    //ItemStack firework = FireworksHelper.getRandomFireworks(0, 1, new Random().nextInt(6) + 1, 1);
                    SimplyJetpacks.LOGGER.info("SJ2: CREATING FIREWORKS!!!");
                    //player.level.createFireworks(new ProjectileImpactEvent.FireworkRocket(player.level, player.getX() + new Random().nextDouble() * 6.0D - 3.0D, player.getY(), player.getZ() + new Random().nextDouble() * 6.0D - 3.0D, firework));
                }
                player.hurt(new EntityDamageSource("jetpack_potato", player), 100F);
                player.drop(new ItemStack(Items.BAKED_POTATO), false);
            }
        } else {
            if (force || CommonJetpackHandler.isHoldingUp(player)) {
                if (this.isTimerSet(stack)) {
                    this.decrementTimer(stack, player);
                } else {
                    this.setTimer(stack, 50);
                }
            }
        }
    }

    private boolean isFired(ItemStack itemStack) {
        return NBTUtil.getBoolean(itemStack, TAG_FIRED);
    }

    private void setFired(ItemStack itemStack) {
        NBTUtil.setBoolean(itemStack, TAG_FIRED, true);
    }

    private boolean isTimerSet(ItemStack itemStack) {
        return NBTUtil.getBoolean(itemStack, TAG_ROCKET_TIMER_SET);
    }

    private void setTimer(ItemStack itemStack, int timer) {
        NBTUtil.setInt(itemStack, TAG_ROCKET_TIMER, timer);
        NBTUtil.setBoolean(itemStack, TAG_ROCKET_TIMER_SET, true);
    }

    private void decrementTimer(ItemStack itemStack, PlayerEntity player) {
        int timer = NBTUtil.getInt(itemStack, TAG_ROCKET_TIMER);
        timer = timer > 0 ? timer - 1 : 0;
        NBTUtil.setInt(itemStack, TAG_ROCKET_TIMER, timer);
        if (timer == 0) {
            this.setFired(itemStack);
            player.level.playSound(player, player, SJSounds.ROCKET, SoundCategory.PLAYERS, 1F, 1F);
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
