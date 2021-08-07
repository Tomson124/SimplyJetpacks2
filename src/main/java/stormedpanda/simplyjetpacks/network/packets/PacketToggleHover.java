package stormedpanda.simplyjetpacks.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import stormedpanda.simplyjetpacks.items.JetpackItem;

import java.util.function.Supplier;

public class PacketToggleHover {

    public PacketToggleHover(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public PacketToggleHover() {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
                Item item = stack.getItem();
                if (item instanceof JetpackItem jetpack) {
                    jetpack.toggleHover(stack, player);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
