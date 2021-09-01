package tonius.simplyjetpacks.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import tonius.simplyjetpacks.util.math.MathHelper;

import javax.annotation.Nonnull;
import java.awt.*;

public class EntityColoredSmokeFX extends ParticleSmokeNormal {

	private static final Minecraft mc = Minecraft.getMinecraft();

	public EntityColoredSmokeFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ, float red, float green, float blue) {
		super(world, posX, posY, posZ, velX, velY, velZ, 1.0F);
		this.particleRed = red;
		this.particleGreen = green;
		this.particleBlue = blue;
	}

	public static EntityColoredSmokeFX getRainbowSmoke(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
		Color color = new Color(Color.HSBtoRGB(MathHelper.RANDOM.nextFloat() * 360, MathHelper.RANDOM.nextFloat() * 0.15F + 0.8F, 0.85F));
		float red = color.getRed() / 255.0F;
		float green = color.getGreen() / 255.0F;
		float blue = color.getBlue() / 255.0F;
		return new EntityColoredSmokeFX(world, posX, posY, posZ, velX, velY, velZ, red, green, blue);
	}

	@Override
	public int getBrightnessForRender(float p_70013_1_) {
		return 190 + (int) (20F * (1.0F - mc.gameSettings.gammaSetting));
	}

	@Override
	public void renderParticle(@Nonnull BufferBuilder buffer, @Nonnull Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		if (this.particleAge > 0) {
			super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
		}
	}
}