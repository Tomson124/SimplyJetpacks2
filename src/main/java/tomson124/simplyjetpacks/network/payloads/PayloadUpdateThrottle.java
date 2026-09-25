package tomson124.simplyjetpacks.network.payloads;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.util.JetpackUtil;

/** Throttle expressed as a whole percentage (0-100), as shown on the jetpack screen's slider. */
public record PayloadUpdateThrottle(int throttlePercent) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<PayloadUpdateThrottle> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "update_throttle"));

    public static final StreamCodec<ByteBuf, PayloadUpdateThrottle> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            PayloadUpdateThrottle::throttlePercent,
            PayloadUpdateThrottle::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleServer(PayloadUpdateThrottle payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            var player = ctx.player();
            if (player == null) {
                return;
            }
            var stack = JetpackUtil.getFromBothSlots(player);
            if (stack.getItem() instanceof JetpackItem) {
                // Stored as a 0..1 multiplier; clamped server-side so a crafted packet can't exceed 100%.
                JetpackUtil.setThrottle(stack, payload.throttlePercent / 100.0D);
            }
        });
    }
}
