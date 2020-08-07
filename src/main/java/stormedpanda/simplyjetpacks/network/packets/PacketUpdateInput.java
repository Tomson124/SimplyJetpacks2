package stormedpanda.simplyjetpacks.network.packets;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import stormedpanda.simplyjetpacks.handlers.SyncHandler;

import java.util.function.Supplier;

public class PacketUpdateInput {
    private final boolean up;
    private final boolean down;
    private final boolean forwards;
    private final boolean backwards;
    private final boolean left;
    private final boolean right;

    public PacketUpdateInput(boolean up, boolean down, boolean forwards, boolean backwards, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.forwards = forwards;
        this.backwards = backwards;
        this.left = left;
        this.right = right;
    }

    public static PacketUpdateInput fromBytes(PacketBuffer buffer) {
        return new PacketUpdateInput(buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean());
    }

    public static void toBytes(PacketUpdateInput message, PacketBuffer buffer) {
        buffer.writeBoolean(message.up);
        buffer.writeBoolean(message.down);
        buffer.writeBoolean(message.forwards);
        buffer.writeBoolean(message.backwards);
        buffer.writeBoolean(message.left);
        buffer.writeBoolean(message.right);
    }

    public static void handle(PacketUpdateInput message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = ctx.get().getSender();
            if (player != null) {
                SyncHandler.update(player, message.up, message.down, message.forwards, message.backwards, message.left, message.right);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}