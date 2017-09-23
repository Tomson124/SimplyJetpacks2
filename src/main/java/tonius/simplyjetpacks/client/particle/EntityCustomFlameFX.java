package tonius.simplyjetpacks.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityCustomFlameFX extends ParticleFlame
{
	private static final Minecraft mc = Minecraft.getMinecraft();

	public EntityCustomFlameFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ)
	{
		super(world, posX, posY, posZ, velX, velY, velZ);
	}

	@Override
	public int getBrightnessForRender(float p_70013_1_)
	{
		return 190 + (int) (20F * (1.0F - mc.gameSettings.gammaSetting));
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
	{
		if(this.particleAge > 0)
		{
			super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
		}
	}
}