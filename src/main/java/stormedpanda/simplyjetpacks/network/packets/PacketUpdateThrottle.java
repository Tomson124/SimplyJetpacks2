package stormedpanda.simplyjetpacks.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import stormedpanda.simplyjetpacks.item.JetpackItem;
import stormedpanda.simplyjetpacks.util.JetpackUtil;

import java.util.function.Supplier;

public class PacketUpdateThrottle {

    private final int throttleValue;

    public PacketUpdateThrottle(int throttleValue) {
        this.throttleValue = throttleValue;
    }

    public static PacketUpdateThrottle fromBytes(FriendlyByteBuf  buffer) {
        return new PacketUpdateThrottle(buffer.readInt());
    }

    public static void toBytes(PacketUpdateThrottle message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.throttleValue);
    }

    public static void handle(PacketUpdateThrottle message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = JetpackUtil.getFromBothSlots(player);
                Item item = stack.getItem();
                if (item instanceof JetpackItem) {
                    JetpackItem jetpack = (JetpackItem) item;
                    jetpack.setThrottle(stack, message.throttleValue);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}