package tomson124.simplyjetpacks.network.payloads;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.item.JetpackItem;
import tomson124.simplyjetpacks.util.JetpackUtil;

public record PayloadToggleHover() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<PayloadToggleHover> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "my_data"));

    public static final StreamCodec<ByteBuf, PayloadToggleHover> STREAM_CODEC = StreamCodec.unit(new PayloadToggleHover());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleServer(PayloadToggleHover payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            var player = ctx.player();
            if (player != null) {
                var stack = JetpackUtil.getFromBothSlots(player);
                var item = stack.getItem();
                if (item instanceof JetpackItem) {
                    JetpackItem jetpack = (JetpackItem) item;
                    jetpack.toggleHover(stack, player);
                }
            }
        });
    }
}