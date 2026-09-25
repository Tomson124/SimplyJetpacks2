package tomson124.simplyjetpacks.network.payloads;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.item.Jetpack;
import tomson124.simplyjetpacks.item.JetpackRegistry;

import java.util.List;

public record PayloadJetpackConfigSync(List<Jetpack> jetpacks) implements CustomPacketPayload {

    public static final Type<PayloadJetpackConfigSync> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(SimplyJetpacks.MODID, "sync_jetpacks"));

    public static final StreamCodec<FriendlyByteBuf, PayloadJetpackConfigSync> STREAM_CODEC = StreamCodec.composite(
            Jetpack.STREAM_CODEC.apply(ByteBufCodecs.list()),
            PayloadJetpackConfigSync::jetpacks,
            PayloadJetpackConfigSync::new
    );

    @Override
    public Type<PayloadJetpackConfigSync> type() {
        return TYPE;
    }

    public static void handleClient(PayloadJetpackConfigSync payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            JetpackRegistry.getInstance().loadJetpacks(payload);
        });
    }
}
