package stormedpanda.simplyjetpacks.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.Random;

public class ParticleRainbow extends SmokeParticle {

    private ParticleRainbow(ClientLevel world, double posX, double posY, double posZ, double velX, double velY, double velZ, SpriteSet sprite, float red, float green, float blue) {
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
    public void render(VertexConsumer vertexBuilder, Camera renderInfo, float partialTicks) {
        if (age > 0) {
            super.render(vertexBuilder, renderInfo, partialTicks);
        }
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(@Nonnull SimpleParticleType type, @Nonnull ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            Color color = new Color(Color.HSBtoRGB(new Random().nextFloat() * 360, new Random().nextFloat() * 0.15F + 0.8F, 0.85F));
            float red = color.getRed() / 255.0F;
            float green = color.getGreen() / 255.0F;
            float blue = color.getBlue() / 255.0F;
            return new ParticleRainbow(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet, red, green, blue);
        }
    }
}
