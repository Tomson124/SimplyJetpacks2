package tonius.simplyjetpacks.util;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import tonius.simplyjetpacks.SimplyJetpacks;

public class AdvancementUtil {

    public static void unlockAdvancement(EntityPlayer player, String name) {
        if (player instanceof EntityPlayerMP) {
            PlayerAdvancements advancements = ((EntityPlayerMP) player).getAdvancements();
            AdvancementManager manager = player.getEntityWorld().getMinecraftServer().getAdvancementManager();
            Advancement advancement = manager.getAdvancement(new ResourceLocation(SimplyJetpacks.MODID, name));
            if (advancement != null) {
                advancements.grantCriterion(advancement, "code_trigger");
            }
        }
    }
}
