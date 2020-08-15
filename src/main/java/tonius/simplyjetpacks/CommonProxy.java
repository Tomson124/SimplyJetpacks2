package tonius.simplyjetpacks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.oredict.OreDictionary;
import tonius.simplyjetpacks.crafting.PlatingReturnHandler;
import tonius.simplyjetpacks.handler.EntityInteractHandler;
import tonius.simplyjetpacks.handler.LivingTickHandler;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.sound.SJSoundRegistry;

public class CommonProxy {

    public static NonNullList<ItemStack> oresListParticles = null;

    public void registerHandlers() {
        SimplyJetpacks.logger.info("Registering Handlers...");
        // TODO: Re-add GUIs
        //NetworkRegistry.INSTANCE.registerGuiHandler(SimplyJetpacks.instance, new GuiHandler());
        FMLCommonHandler.instance().bus().register(new SyncHandler());
        FMLCommonHandler.instance().bus().register(new PlatingReturnHandler());
        MinecraftForge.EVENT_BUS.register(new EntityInteractHandler());
        MinecraftForge.EVENT_BUS.register(new LivingTickHandler());
    }

    public void showJetpackParticles(World world, EntityLivingBase wearer, ParticleType particle) {
    }

    public void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
    }

    public String getPackGUIKey() {
        return null;
    }

    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public void init() {
        SimplyJetpacks.logger.info("Registering Sounds...");
        SJSoundRegistry.init();
        oresListParticles = OreDictionary.getOres("particleCustomizer");
    }

    public EntityPlayer getPlayer(MessageContext context) {
        return context.getServerHandler().player;
    }

    public void initKeys() {
    }
}
