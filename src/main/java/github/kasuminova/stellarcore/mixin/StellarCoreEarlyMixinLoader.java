package github.kasuminova.stellarcore.mixin;

import github.kasuminova.stellarcore.common.config.StellarCoreConfig;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.BooleanSupplier;

@SuppressWarnings("unused")
public class StellarCoreEarlyMixinLoader implements IFMLLoadingPlugin, IEarlyMixinLoader {
    public static final Logger LOG = LogManager.getLogger("STELLAR_CORE");
    public static final String LOG_PREFIX = "[STELLAR_CORE]" + ' ';
    private static final Map<String, BooleanSupplier> MIXIN_CONFIGS = new LinkedHashMap<>();

    static {
        addMixinCFG("mixins.stellar_core_minecraft_chunk.json",         () -> StellarCoreConfig.PERFORMANCE.vanilla.blockPos2ValueMap);
        addMixinCFG("mixins.stellar_core_minecraft_longnbtkiller.json", () -> StellarCoreConfig.BUG_FIXES.vanilla.longNBTKiller);
        addMixinCFG("mixins.stellar_core_minecraft_nnlist.json",        () -> StellarCoreConfig.PERFORMANCE.vanilla.nonNullList);
        addMixinCFG("mixins.stellar_core_minecraft_noglerror.json",     () -> StellarCoreConfig.PERFORMANCE.vanilla.noGlError);
        addMixinCFG("mixins.stellar_core_minecraft_world.json",         () -> StellarCoreConfig.PERFORMANCE.vanilla.capturedBlockSnapshots);
        addMixinCFG("mixins.stellar_core_forge.json",                   () -> StellarCoreConfig.PERFORMANCE.customLoadingScreen.splashProgress);
    }

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>(MIXIN_CONFIGS.keySet());
    }

    @Override
    public boolean shouldMixinConfigQueue(final String mixinConfig) {
        BooleanSupplier supplier = MIXIN_CONFIGS.get(mixinConfig);
        if (supplier == null) {
            LOG.warn(LOG_PREFIX + "Mixin config {} is not found in config map! It will never be loaded.", mixinConfig);
            return false;
        }
        return supplier.getAsBoolean();
    }

    private static void addMixinCFG(final String mixinConfig) {
        MIXIN_CONFIGS.put(mixinConfig, () -> true);
    }

    private static void addMixinCFG(final String mixinConfig, final BooleanSupplier conditions) {
        MIXIN_CONFIGS.put(mixinConfig, conditions);
    }

    // Noop

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(final Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
