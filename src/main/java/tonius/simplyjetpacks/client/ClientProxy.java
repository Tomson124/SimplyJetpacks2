package tonius.simplyjetpacks.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import tonius.simplyjetpacks.CommonProxy;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.client.handler.ClientTickHandler;
import tonius.simplyjetpacks.client.handler.HUDTickHandler;
import tonius.simplyjetpacks.client.handler.KeybindHandler;
import tonius.simplyjetpacks.client.util.ParticleUtils;
import tonius.simplyjetpacks.setup.ParticleType;
import tonius.simplyjetpacks.util.math.Pos3D;

import java.util.Random;

public class ClientProxy extends CommonProxy {

	private static final Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void registerHandlers() {
		super.registerHandlers();
		MinecraftForge.EVENT_BUS.register(new KeybindHandler());
		MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
		MinecraftForge.EVENT_BUS.register(new HUDTickHandler());
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(SimplyJetpacks.MODID + ":" + id, "inventory"));
	}

	@Override
	public void showJetpackParticles(World world, EntityLivingBase wearer, ParticleType particle) {
		if (mc.gameSettings.particleSetting == 0 || mc.gameSettings.particleSetting == 1 && mc.world.getTotalWorldTime() % 4L == 0) {

			Random rand = new Random();
			float random = (rand.nextFloat() - 0.5F) * 0.1F;

			Pos3D playerPos = new Pos3D(wearer).translate(0, 1.5, 0);

			Pos3D vLeft = new Pos3D(-0.28, -0.95, -0.35).rotatePitch(0).rotateYaw(wearer.renderYawOffset);
			Pos3D vRight = new Pos3D(0.28, -0.95, -0.35).rotatePitch(0).rotateYaw(wearer.renderYawOffset);
			Pos3D vCenter = new Pos3D((rand.nextFloat() - 0.5F) * 0.25F, -0.95, -0.38).rotatePitch(0).rotateYaw(wearer.renderYawOffset);

			Pos3D v = playerPos.translate(vLeft).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));
			ParticleUtils.spawnParticle(particle, world, v.x, v.y, v.z, random, -0.2D, random);

			v = playerPos.translate(vRight).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));
			ParticleUtils.spawnParticle(particle, world, v.x, v.y, v.z, random, -0.2D, random);

			v = playerPos.translate(vCenter).translate(new Pos3D(wearer.motionX, wearer.motionY, wearer.motionZ));
			ParticleUtils.spawnParticle(particle, world, v.x, v.y, v.z, random, -0.2D, random);
		}
	}

	@Override
	public void updateCustomKeybinds(String flyKeyName, String descendKeyName) {
		KeybindHandler.updateCustomKeybinds(flyKeyName, descendKeyName);
	}

	@Override
	public void initKeys() {
		KeybindHandler.setup();
	}
}