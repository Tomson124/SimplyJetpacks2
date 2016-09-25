package tonius.simplyjetpacks.client;

import tonius.simplyjetpacks.CommonProxy;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.handler.ClientTickHandler;
import tonius.simplyjetpacks.client.handler.HUDTickHandler;
import tonius.simplyjetpacks.client.handler.KeyHandler;
import tonius.simplyjetpacks.client.handler.rewrite.KeyTracker;
import tonius.simplyjetpacks.client.util.ParticleUtils;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.Pos3D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;

import java.util.Random;

public class ClientProxy extends CommonProxy {
	private static final Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void registerHandlers() {
		super.registerHandlers();

		MinecraftForge.EVENT_BUS.register(KeyTracker.instance);
		MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
		MinecraftForge.EVENT_BUS.register(new HUDTickHandler());
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(SimplyJetpacks.MODID + ":" + id, "inventory"));
	}

	@Override
	public void showJetpackParticles(World world, EntityLivingBase wearer, ParticleType particle) {
		if (mc.gameSettings.particleSetting == 0 || mc.gameSettings.particleSetting == 1 && mc.theWorld.getTotalWorldTime() % 4L == 0) {

			Random rand = new Random();

			Pos3D playerPos = new Pos3D(wearer).translate(0, 1.7, 0);

			float random = (rand.nextFloat() - 0.5F) * 0.1F;

			Pos3D vLeft = new Pos3D(-0.28D, -0.95D, -038D).rotatePitch(0).rotateYaw(wearer.renderYawOffset);
			Pos3D vRight = new Pos3D(0.28D, -0.95D, -038D).rotatePitch(0).rotateYaw(wearer.renderYawOffset);
			Pos3D vCenter = new Pos3D((rand.nextFloat() - 0.5F) * 0.25F, -0.95D, -0.38D).rotatePitch(0).rotateYaw(wearer.renderYawOffset);

			Pos3D rLeft = vLeft.scale(random);
			Pos3D rRight = vRight.scale(random);

			Pos3D mLeft = vLeft.scale(0.2).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));
			Pos3D mRight = vRight.scale(0.2).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));
			Pos3D mCenter = vCenter.scale(0.2).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));

			mLeft = mLeft.translate(rLeft);
			mRight = mRight.translate(rRight);

			Pos3D v = playerPos.translate(vLeft).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));
			ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, mLeft.xCoord, mLeft.yCoord, mLeft.zCoord);

			v = playerPos.translate(vRight).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));
			ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, mRight.xCoord, mRight.yCoord, mRight.zCoord);

			v = playerPos.translate(vCenter).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));
			ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, mCenter.xCoord, mCenter.yCoord, mCenter.zCoord);
		}
	}

	@Override
	public void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
		KeyTracker.updateCustomKeybinds(flyKeyName, descendKeyName);
	}

	@Override
	public String getPackGUIKey() {
		int keyCode = KeyHandler.keyOpenPackGUI.getKeyCode();
		if (keyCode == 0) {
			return null;
		}
		return GameSettings.getKeyDisplayString(keyCode);
	}
}