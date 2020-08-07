package stormedpanda.simplyjetpacks.util;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class AdvancementUtil {

    public static void unlockAdvancement(PlayerEntity player, String name) {
        if (player instanceof ServerPlayerEntity) {
            PlayerAdvancements advancements = ((ServerPlayerEntity)player).getAdvancements();
            AdvancementManager manager = ((ServerWorld)player.getEntityWorld()).getServer().getAdvancementManager();
            Advancement advancement = manager.getAdvancement(new ResourceLocation(SimplyJetpacks.MODID, name));
            if (advancement != null) {
                advancements.grantCriterion(advancement, "code_trigger");
            }
        }
    }
}
