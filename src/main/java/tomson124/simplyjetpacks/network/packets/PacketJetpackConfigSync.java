package tomson124.simplyjetpacks.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import tomson124.simplyjetpacks.SimplyJetpacks;
import tomson124.simplyjetpacks.item.JetpackType;

import java.util.Optional;
import java.util.function.Supplier;

public class PacketJetpackConfigSync {

    private final String configKey;

    private final int energyCapacity;
    private final int energyUsage;
    private final int energyPerTickIn;
    private final int energyPerTickOut;
    private final int armorReduction;
    private final int armorEnergyPerHit;
    private final int enchantability;

    private final double speedVertical;
    private final double accelVertical;
    private final double speedVerticalHover;
    private final double speedVerticalHoverSlow;
    private final double speedSideways;
    private final double sprintSpeedModifier;
    private final double sprintEnergyModifier;
    private final boolean hoverMode;
    private final boolean emergencyHoverMode;
    private final boolean chargerMode;

    public PacketJetpackConfigSync(JetpackType jetpack) {
        this.configKey = jetpack.getConfigKey();

        this.energyCapacity = jetpack.getEnergyCapacity();
        this.energyUsage = jetpack.getEnergyUsage();
        this.energyPerTickIn = jetpack.getEnergyPerTickIn();
        this.energyPerTickOut = jetpack.getEnergyPerTickOut();
        this.armorReduction = jetpack.getArmorReduction();
        this.armorEnergyPerHit = jetpack.getArmorEnergyPerHit();
        this.enchantability = jetpack.getEnchantability();
        this.speedVertical = jetpack.getSpeedVertical();
        this.accelVertical = jetpack.getAccelVertical();
        this.speedVerticalHover = jetpack.getSpeedVerticalHover();
        this.speedVerticalHoverSlow = jetpack.getSpeedVerticalHoverSlow();
        this.speedSideways = jetpack.getSpeedSideways();
        this.sprintSpeedModifier = jetpack.getSprintSpeedModifier();
        this.sprintEnergyModifier = jetpack.getSprintEnergyModifier();
        this.hoverMode = jetpack.getHoverMode();
        this.emergencyHoverMode = jetpack.getEmergencyHoverMode();
        this.chargerMode = jetpack.getChargerMode();
    }

    public PacketJetpackConfigSync(FriendlyByteBuf buf) {
        this.configKey = buf.readUtf();
        this.energyCapacity = buf.readInt();
        this.energyUsage = buf.readInt();
        this.energyPerTickIn = buf.readInt();
        this.energyPerTickOut = buf.readInt();
        this.armorReduction = buf.readInt();
        this.armorEnergyPerHit = buf.readInt();
        this.enchantability = buf.readInt();
        this.speedVertical = buf.readDouble();
        this.accelVertical = buf.readDouble();
        this.speedVerticalHover = buf.readDouble();
        this.speedVerticalHoverSlow = buf.readDouble();
        this.speedSideways = buf.readDouble();
        this.sprintSpeedModifier = buf.readDouble();
        this.sprintEnergyModifier = buf.readDouble();
        this.hoverMode = buf.readBoolean();
        this.emergencyHoverMode = buf.readBoolean();
        this.chargerMode = buf.readBoolean();
    }

    public static PacketJetpackConfigSync fromBytes(FriendlyByteBuf buffer) {
        return new PacketJetpackConfigSync(buffer);
    }

    public static void toBytes(PacketJetpackConfigSync packet, FriendlyByteBuf buffer) {
        buffer.writeUtf(packet.configKey);
        buffer.writeInt(packet.energyCapacity);
        buffer.writeInt(packet.energyUsage);
        buffer.writeInt(packet.energyPerTickIn);
        buffer.writeInt(packet.energyPerTickOut);
        buffer.writeInt(packet.armorReduction);
        buffer.writeInt(packet.armorEnergyPerHit);
        buffer.writeInt(packet.enchantability);
        buffer.writeDouble(packet.speedVertical);
        buffer.writeDouble(packet.accelVertical);
        buffer.writeDouble(packet.speedVerticalHover);
        buffer.writeDouble(packet.speedVerticalHoverSlow);
        buffer.writeDouble(packet.speedSideways);
        buffer.writeDouble(packet.sprintSpeedModifier);
        buffer.writeDouble(packet.sprintEnergyModifier);
        buffer.writeBoolean(packet.hoverMode);
        buffer.writeBoolean(packet.emergencyHoverMode);
        buffer.writeBoolean(packet.chargerMode);
    }

    public static void handle(PacketJetpackConfigSync message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                // This is not ideal but would require a pretty significant rewrite to make this better.
                Optional<JetpackType> jetpackTypeOptional = JetpackType.JETPACK_ALL.stream().filter(jetpack -> jetpack.getConfigKey().equals(message.configKey)).findFirst();

                if(jetpackTypeOptional.isPresent()) {
                    JetpackType jetpack = jetpackTypeOptional.get();
                    jetpack.setEnergyCapacity(message.energyCapacity);
                    jetpack.setEnergyUsage(message.energyUsage);
                    jetpack.setEnergyPerTickIn(message.energyPerTickIn);
                    jetpack.setEnergyPerTickOut(message.energyPerTickOut);
                    jetpack.setArmorReduction(message.armorReduction);
                    jetpack.setArmorEnergyPerHit(message.armorEnergyPerHit);
                    jetpack.setEnchantability(message.enchantability);
                    jetpack.setSpeedVertical(message.speedVertical);
                    jetpack.setAccelVertical(message.accelVertical);
                    jetpack.setSpeedVerticalHover(message.speedVerticalHover);
                    jetpack.setSpeedVerticalHoverSlow(message.speedVerticalHoverSlow);
                    jetpack.setSpeedSideways(message.speedSideways);
                    jetpack.setSprintSpeedModifier(message.sprintSpeedModifier);
                    jetpack.setSprintEnergyModifier(message.sprintEnergyModifier);
                    jetpack.setHoverMode(message.hoverMode);
                    jetpack.setEmergencyHoverMode(message.emergencyHoverMode);
                    jetpack.setChargerMode(message.chargerMode);
                } else {
                    SimplyJetpacks.LOGGER.error("Attempted to update config for unknown config key {}." +
                            " Please make sure you have to correct version of the mod.", message.configKey);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
