package stormedpanda.simplyjetpacks.util;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

public class AdvancementUtil {

    public static void unlockAdvancement(Player player, String name) {
        if (player instanceof ServerPlayer) {
            PlayerAdvancements advancements = ((ServerPlayer)player).getAdvancements();
            ServerAdvancementManager manager = ((ServerPlayer) player).getLevel().getServer().getAdvancements();
            Advancement advancement = manager.getAdvancement(new ResourceLocation(SimplyJetpacks.MODID, name));
            if (advancement != null) {
                advancements.award(advancement, "code_trigger");
            }
        }
    }
}
