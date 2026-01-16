package tomson124.simplyjetpacks.item;

import com.google.common.base.Stopwatch;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import tomson124.simplyjetpacks.SimplyJetpacks;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class JetpackRegistry {
    private static final JetpackRegistry INSTANCE = new JetpackRegistry();
    private final Map<ResourceLocation, Jetpack> jetpacks = new LinkedHashMap<>();
    private final ArrayList<Integer> tiers = new ArrayList<>();
    private int lowestTier = Integer.MAX_VALUE;
    private boolean isErrored = false;

    @SubscribeEvent
    public void onDatapackSync(OnDatapackSyncEvent event) {
        var payload = new SyncJetpacksPayload(this.getJetpacks());
        var player = event.getPlayer();

        if (player != null) {
            PacketDistributor.sendToPlayer(player, payload);
        } else {
            PacketDistributor.sendToAllPlayers(payload);
        }
    }

    public void register(Jetpack jetpack) {
        if (this.jetpacks.containsKey(jetpack.getId())) {
            this.isErrored = true;
            throw new RuntimeException(String.format("Tried to register multiple jetpacks with the same name: %s", jetpack.name));
        }

        this.jetpacks.put(jetpack.getId(), jetpack);

        if (jetpack.tier > -1 && !this.tiers.contains(jetpack.tier)) {
            this.tiers.add(jetpack.tier);
            this.tiers.sort(Integer::compareTo);
        }

        if (jetpack.tier > -1 && jetpack.tier < this.lowestTier) {
            this.lowestTier = jetpack.tier;
        }
    }

    public List<Jetpack> getJetpacks() {
        return new ArrayList<>(this.jetpacks.values());
    }

    public List<Integer> getAllTiers() {
        return this.tiers;
    }

    public Integer getLowestTier() {
        return this.lowestTier;
    }

    public Jetpack getJetpackById(ResourceLocation id) {
        return this.jetpacks.getOrDefault(id, Jetpack.UNDEFINED);
    }

    public Item getCoilForTier(int tier) {
        float tiers = this.tiers.size();
        float index = this.tiers.indexOf(tier);

        if (index / tiers > 0.75F)
            return ModItems.ULTIMATE_COIL.get();

        if (index / tiers > 0.5F)
            return ModItems.ELITE_COIL.get();

        if (index / tiers > 0.25F)
            return ModItems.ADVANCED_COIL.get();

        return ModItems.BASIC_COIL.get();
    }

    public boolean isErrored() {
        return this.isErrored;
    }

    public void writeToBuffer(FriendlyByteBuf buffer) {
        buffer.writeVarInt(this.jetpacks.size());

        this.jetpacks.forEach((id, jetpack) -> {
            jetpack.write(buffer);
        });
    }

    public List<Jetpack> readFromBuffer(FriendlyByteBuf buffer) {
        List<Jetpack> jetpacks = new ArrayList<>();

        int size = buffer.readVarInt();

        for (int i = 0; i < size; i++) {
            Jetpack jetpack = Jetpack.read(buffer);

            jetpacks.add(jetpack);
        }

        return jetpacks;
    }

    public void loadJetpacks(SyncJetpacksPayload payload) {
        this.jetpacks.clear();

        for (var jetpack : payload.jetpacks()) {
            this.jetpacks.put(jetpack.getId(), jetpack);
        }

        SimplyJetpacks.LOGGER.info("Loaded {} jetpacks from the server", this.jetpacks.size());
    }

    public void loadJetpacks() {
        var stopwatch = Stopwatch.createStarted();
        var dir = FMLPaths.CONFIGDIR.get().resolve("simplyjetpacks/jetpacks").toFile();

        this.jetpacks.clear();

        this.registerJetpacks();

        stopwatch.stop();

        SimplyJetpacks.LOGGER.info("Loaded {} jetpack type(s) in {} ms", this.jetpacks.size(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private void registerJetpacks() {
        List<Jetpack> jetpacks = new ArrayList<>();

        for (var jetpack_type : JetpackType.JETPACK_ALL) {
            Jetpack jetpack = null;
            jetpack = Jetpack.fromEnum(jetpack_type);

            if (jetpack != null && !jetpack.disabled) {
                jetpacks.add(jetpack);
            }
        }

        jetpacks.sort(Comparator.comparingInt(Jetpack::getTier));

        for (var jetpack : jetpacks) {
            this.register(jetpack);
        }
    }

    public static JetpackRegistry getInstance() {
        return INSTANCE;
    }
}
