package stormedpanda.simplyjetpacks.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import stormedpanda.simplyjetpacks.SimplyJetpacks;

import java.io.File;

@Mod.EventBusSubscriber
public class NewConfig {

    private static final ForgeConfigSpec.Builder server_builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec server_config;

    private static final ForgeConfigSpec.Builder client_builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec client_config;

    static {
        NewJetpackConfig.init(server_builder, client_builder);

        server_config = server_builder.build();
        client_config = client_builder.build();
    }

    public static void LoadConfig(ForgeConfigSpec configSpec, String path) {
        SimplyJetpacks.LOGGER.info("loading config: " + path);
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        SimplyJetpacks.LOGGER.info("build config: " + path);
        file.load();
        SimplyJetpacks.LOGGER.info("loaded config: " + path);
        configSpec.setConfig(file);
    }
}
