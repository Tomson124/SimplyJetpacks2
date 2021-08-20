package stormedpanda.simplyjetpacks.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class ParticleRainbow extends SmokeParticle {

    private ParticleRainbow(ClientWorld world, double posX, double posY, double posZ, double velX, double velY, double velZ, IAnimatedSprite sprite, float red, float green, float blue) {
        super(world, posX, posY, posZ, velX, velY, velZ, 1.0F, sprite);
        this.rCol = red;
        this.gCol = green;
        this.bCol = blue;
    }

/*    @Override
    public int getBrightnessForRender(float partialTick) {
        return 190 + (int) (20F * (1.0F - Minecraft.getInstance().options.gamma));
    }*/

    @Override
    public void render(@Nonnull IVertexBuilder vertexBuilder, @Nonnull ActiveRenderInfo renderInfo, float partialTicks) {
        if (age > 0) {
            super.render(vertexBuilder, renderInfo, partialTicks);
        }
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {

        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(@Nonnull BasicParticleType type, @Nonnull ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            Color color = new Color(Color.HSBtoRGB(new Random().nextFloat() * 360, new Random().nextFloat() * 0.15F + 0.8F, 0.85F));
            float red = color.getRed() / 255.0F;
            float green = color.getGreen() / 255.0F;
            float blue = color.getBlue() / 255.0F;
            return new ParticleRainbow(world, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet, red, green, blue);
        }
    }
}