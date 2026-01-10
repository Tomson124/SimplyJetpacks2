package tomson124.simplyjetpacks.network.payloads;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.handlers.CommonJetpackHandler;

public record PayloadUpdateInput(boolean invert, boolean up, boolean down, boolean forwards, boolean backwards, boolean left, boolean right) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<PayloadUpdateInput> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "my_data"));

    public static final StreamCodec<ByteBuf, PayloadUpdateInput> STREAM_CODEC = StreamCodec.of(PayloadUpdateInput::encode, PayloadUpdateInput::decode);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private static PayloadUpdateInput decode(ByteBuf buffer) {
        return new PayloadUpdateInput(
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean()
        );
    }

    private static void encode(ByteBuf buffer, PayloadUpdateInput payload) {
        buffer.writeBoolean(payload.invert);
        buffer.writeBoolean(payload.up);
        buffer.writeBoolean(payload.down);
        buffer.writeBoolean(payload.forwards);
        buffer.writeBoolean(payload.backwards);
        buffer.writeBoolean(payload.left);
        buffer.writeBoolean(payload.right);
    }

    public static void handleServer(PayloadUpdateInput payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            var player = ctx.player();
            if (player != null) {
                CommonJetpackHandler.update(player, payload.invert, payload.up, payload.down, payload.forwards, payload.backwards, payload.left, payload.right);
            }
        });
    }
}