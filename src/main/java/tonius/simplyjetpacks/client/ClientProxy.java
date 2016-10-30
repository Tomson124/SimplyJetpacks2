package tonius.simplyjetpacks.client;

import tonius.simplyjetpacks.CommonProxy;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.handler.ClientTickHandler;
import tonius.simplyjetpacks.client.handler.HUDTickHandler;
import tonius.simplyjetpacks.client.handler.KeyTracker;
import tonius.simplyjetpacks.client.handler.deprecated.KeyHandler;
import tonius.simplyjetpacks.client.util.ParticleUtils;
import tonius.simplyjetpacks.handler.SyncHandler;
import tonius.simplyjetpacks.network.message.MessageAbstract;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.math.Pos3D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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

			double random = (rand.nextDouble() - 0.5D) * 0.01D;

			Pos3D vLeft = new Pos3D(-0.28, -0.95, -0.38).rotatePitch(0).rotateYaw(wearer.renderYawOffset);
			Pos3D vRight = new Pos3D(0.28, -0.95, -0.38).rotatePitch(0).rotateYaw(wearer.renderYawOffset);
			Pos3D vCenter = new Pos3D((rand.nextFloat() - 0.5F) * 0.25F, -0.95, -0.38).rotatePitch(0).rotateYaw(wearer.renderYawOffset);

			/*Pos3D mLeft = vLeft.scale(0.4).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));
			Pos3D mRight = vRight.scale(0.4).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));
			Pos3D mCenter = vCenter.scale(0.4).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));

			if (SyncHandler.isFlyKeyDown(wearer)) {
				Pos3D v = playerPos.translate(vLeft).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ).scale(0.5));
				ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, mLeft.xCoord, -0.2D, mLeft.zCoord);

				v = playerPos.translate(vRight).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ).scale(0.5));
				ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, mRight.xCoord, -0.2D, mRight.zCoord);

				v = playerPos.translate(vCenter).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ).scale(0.5));
				ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, mCenter.xCoord, -0.2D, mCenter.zCoord);
			}*/
			Pos3D v = playerPos.translate(vLeft).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ).scale(0.5));
			ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, random, -0.2D, random);

			v = playerPos.translate(vRight).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ).scale(0.5));
			ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, random, -0.2D, random);

			v = playerPos.translate(vCenter).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ).scale(0.5));
			ParticleUtils.spawnParticle(particle, world, v.xCoord, v.yCoord, v.zCoord, random, -0.2D, random);
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

	@Override
	public <T extends MessageAbstract<T>> void handleMessage(T message, MessageContext messageContext) {
		if (messageContext.side.isServer()) {
			super.handleMessage(message, messageContext);
		} else {
			ClientProxy.mc.addScheduledTask(() -> message.onClientReceived(ClientProxy.mc, message, ClientProxy.mc.thePlayer, messageContext));
		}
	}
}