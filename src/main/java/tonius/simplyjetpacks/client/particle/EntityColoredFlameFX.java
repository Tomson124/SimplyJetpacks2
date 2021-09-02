package tonius.simplyjetpacks.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import tonius.simplyjetpacks.util.math.MathHelper;

import javax.annotation.Nonnull;
import java.awt.*;

public class EntityColoredFlameFX extends ParticleFlame {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public EntityColoredFlameFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ, float red, float green, float blue) {
        super(world, posX, posY, posZ, velX, velY, velZ);
        this.particleRed = red;
        this.particleGreen = green;
        this.particleBlue = blue;
    }

    public static EntityColoredFlameFX getSoulFlame(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        // TODO: work on the colour.
        Color color = new Color(Color.HSBtoRGB(MathHelper.RANDOM.nextInt(40) + 180, MathHelper.RANDOM.nextInt(100), 0.95F));
        float red = color.getRed();
        float green = color.getGreen();
        float blue = color.getBlue();
        return new EntityColoredFlameFX(world, posX, posY, posZ, velX, velY, velZ, red, green, blue);
    }

    @Override
    public int getBrightnessForRender(float brightness) {
        return 190 + (int) (20F * (1.0F - mc.gameSettings.gammaSetting));
    }

    @Override
    public void renderParticle(@Nonnull BufferBuilder buffer, @Nonnull Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        if (this.particleAge > 0) {
            super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        }
    }
}