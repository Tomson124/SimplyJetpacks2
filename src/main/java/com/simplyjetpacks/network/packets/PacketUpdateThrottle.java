package com.simplyjetpacks.network.packets;

import com.simplyjetpacks.item.JetpackItem;
import com.simplyjetpacks.util.JetpackUtil;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateThrottle {

    private final int throttleValue;

    public PacketUpdateThrottle(int throttleValue) {
        this.throttleValue = throttleValue;
    }

    public static PacketUpdateThrottle fromBytes(PacketBuffer buffer) {
        return new PacketUpdateThrottle(buffer.readInt());
    }

    public static void toBytes(PacketUpdateThrottle message, PacketBuffer buffer) {
        buffer.writeInt(message.throttleValue);
    }

    public static void handle(PacketUpdateThrottle message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
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