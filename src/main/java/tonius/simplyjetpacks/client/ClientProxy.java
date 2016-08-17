package tonius.simplyjetpacks.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import tonius.simplyjetpacks.CommonProxy;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.handler.ClientTickHandler;
import tonius.simplyjetpacks.client.handler.HUDTickHandler;
import tonius.simplyjetpacks.client.handler.KeyHandler;

public class ClientProxy extends CommonProxy
{
	private static final Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void registerHandlers()
	{
		super.registerHandlers();

		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
		FMLCommonHandler.instance().bus().register(new KeyHandler());
		FMLCommonHandler.instance().bus().register(new HUDTickHandler());
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(SimplyJetpacks.MODID + ":" + id, "inventory"));
	}

    /*@Override
	public void showJetpackParticles(World world, EntityLivingBase wearer, ParticleType particle) {
        if (mc.gameSettings.particleSetting == 0 || mc.gameSettings.particleSetting == 1 && mc.theWorld.getTotalWorldTime() % 4L == 0) {
            Vec3d userPos = Vec4b.createVectorHelper(wearer.posX, wearer.posY, wearer.posZ);
            
            if (!wearer.equals(mc.thePlayer)) {
                userPos = userPos.addVector(0, 1.6D, 0);
            }
            
            Random rand = MathHelper.RANDOM;
            
            Vec3 vLeft = Vec3.createVectorHelper(-0.28D, -0.95D, -0.38D);
            vLeft.rotateAroundY((float) Math.toRadians(-wearer.renderYawOffset));
            
            Vec3 vRight = Vec3.createVectorHelper(0.28D, -0.95D, -0.38D);
            vRight.rotateAroundY((float) Math.toRadians(-wearer.renderYawOffset));
            
            Vec3 vCenter = Vec3.createVectorHelper((rand.nextFloat() - 0.5F) * 0.25F, -0.95D, -0.38D);
            vCenter.rotateAroundY((float) Math.toRadians(-wearer.renderYawOffset));
            
            vLeft = vLeft.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            vRight = vRight.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            vCenter = vCenter.addVector(-wearer.motionX * 0.2D, -wearer.motionY * 0.2D, -wearer.motionZ * 0.2D);
            
            Vec3 v = userPos.addVector(vLeft.xCoord, vLeft.yCoord, vLeft.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, rand.nextDouble() * 0.05D - 0.025D, -0.2D, rand.nextDouble() * 0.05D - 0.025D);
            
            v = userPos.addVector(vRight.xCoord, vRight.yCoord, vRight.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, rand.nextDouble() * 0.05D - 0.025D, -0.2D, rand.nextDouble() * 0.05D - 0.025D);
            
            v = userPos.addVector(vCenter.xCoord, vCenter.yCoord, vCenter.zCoord);
            ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, rand.nextDouble() * 0.05D - 0.025D, -0.2D, rand.nextDouble() * 0.05D - 0.025D);
        }
    }*/

	@Override
	public void updateCustomKeybinds(String flyKeyName, String descendKeyName)
	{
		KeyHandler.updateCustomKeybinds(flyKeyName, descendKeyName);
	}

	@Override
	public String getPackGUIKey()
	{
		int keyCode = KeyHandler.keyOpenPackGUI.getKeyCode();
		if(keyCode == 0)
		{
			return null;
		}
		return GameSettings.getKeyDisplayString(keyCode);
	}
}