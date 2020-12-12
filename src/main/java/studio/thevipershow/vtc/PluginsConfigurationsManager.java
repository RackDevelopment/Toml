package studio.thevipershow.vtc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class PluginsConfigurationsManager {

    private static final PluginsConfigurationsManager instance = new PluginsConfigurationsManager();

    synchronized public static PluginsConfigurationsManager getInstance() {
        return instance;
    }

    @Getter
    private final Map<JavaPlugin, PluginConfigurationsData<?>> pluginConfigDataMap = new HashMap<>();

    /**
     * This method loads your configurations data using a config list enum.
     *
     * @param yourPlugin  Your plugin instance.
     * @param configsEnum Your class listing all of the registered configs.
     * @param <P>         Your plugin type.
     * @param <T>         The enum config type.
     */
    public final <P extends JavaPlugin, T extends Enum<T> & ClassHolder<? extends TomlSectionConfiguration<P, ?>>> void loadPluginData(@NotNull P yourPlugin, @NotNull Class<T> configsEnum) {
        var pluginData = new PluginConfigurationsData<P>(Objects.requireNonNull(yourPlugin, "Your plugin instance was null!"));
        pluginData.loadAllConfigs(configsEnum, yourPlugin);
        this.pluginConfigDataMap.put(yourPlugin, pluginData);
    }

    /**
     * Get your registered plugin configs data.
     *
     * @param yourPlugin Your plugin instance.
     * @param <P>        The type of your plugin.
     * @return Your data if loaded, null otherwise.
     */
    @Nullable
    public final <P extends JavaPlugin> PluginConfigurationsData<P> getPluginData(@NotNull P yourPlugin) {
        if (this.pluginConfigDataMap.containsKey(Objects.requireNonNull(yourPlugin, "Your plugin instance was null!")))
            return (PluginConfigurationsData<P>) this.pluginConfigDataMap.get(yourPlugin);
        else
            return null;
    }
}
