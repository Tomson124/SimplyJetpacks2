package tonius.simplyjetpacks.util.math;

import java.util.Random;

public final class MathHelper {

    public static final Random RANDOM = new Random();

    private MathHelper() {
    }

    public static int clampI(int var0, int var1, int var2) {
        return var0 < var1 ? var1 : (Math.min(var0, var2));
    }
}
