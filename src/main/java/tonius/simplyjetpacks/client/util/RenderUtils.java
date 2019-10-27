package tonius.simplyjetpacks.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import tonius.simplyjetpacks.client.model.ModelFluxPack;
import tonius.simplyjetpacks.client.model.ModelJetpack;
import tonius.simplyjetpacks.item.Packs;

public abstract class RenderUtils {
	private static final Minecraft mc = Minecraft.getInstance();

	public enum HUDPositions {
		TOP_LEFT,
		TOP_CENTER,
		TOP_RIGHT,
		LEFT,
		RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT
	}

	public static BipedModel getArmorModel(Packs pack, LivingEntity entity) {
		BipedModel model = null;
		switch (pack.armorModel) {
			case JETPACK:
				model = ModelJetpack.INSTANCE;
				break;
			case FLUX_PACK:
				model = ModelFluxPack.INSTANCE;
				break;
			default:
				break;
		}
		if (model == null) {
			return null;
		}
		model.isSneak = entity.isSneaking();
		model.isSitting = entity.isRidingOrBeingRiddenBy(entity);
		model.isChild = entity.isChild();

		return model;
	}
}