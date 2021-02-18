package stormedpanda.simplyjetpacks.integration;

import top.theillusivec4.curios.api.type.capability.ICurio;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class CurioJetpack implements ICurio {

	protected final ItemStack jetpack;

	public CurioJetpack(ItemStack jetpack) {
		this.jetpack = jetpack;
	}

	@Override
	public void curioTick(String identifier, int index, LivingEntity livingEntity) {
		if (livingEntity instanceof PlayerEntity) {
			jetpack.onArmorTick(livingEntity.world, (PlayerEntity) livingEntity);
		}
	}

}
