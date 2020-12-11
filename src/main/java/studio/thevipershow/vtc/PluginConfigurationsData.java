package studio.thevipershow.vtc;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Your plugin configurations data.
 * MUST BE ANNOTATED WITH YOUR MAIN PLUGIN TYPE.
 * @param <P> Your plugin type.
 */
@RequiredArgsConstructor
@Getter
public final class PluginConfigurationsData<P extends JavaPlugin> {

    private final P javaPlugin;
    private final Map<ClassHolder<? extends TomlSectionConfiguration<P, ?>>, TomlSectionConfiguration<P, ?>> loadedTomlConfigs = new HashMap<>();

    /**
     *
     * @param enumClass
     * @param javaPlugin
     * @param <T>
     */
    public final <T extends Enum<T> & ClassHolder<? extends TomlSectionConfiguration<P, ?>>> void loadAllConfigs(@NotNull Class<T> enumClass, @NotNull P javaPlugin) {
        var logger = javaPlugin.getLogger();

        for (final T configTypes : enumClass.getEnumConstants()) {
            var start = System.currentTimeMillis();
            var configName = configTypes.name();

            logger.info("Loading config " + configName);

            try {
                this.loadedTomlConfigs.put(configTypes, configTypes.getClassData().getConstructor(javaPlugin.getClass()).newInstance(javaPlugin));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                logger.warning("Could not load config: " + configName);
                e.printStackTrace();
            }

            logger.info(String.format("Finished loading config in %d milliseconds.", System.currentTimeMillis() - start));
        }
    }

    /**
     *
     * @param sectionEnum
     * @param <T>
     * @param <S>
     * @return
     */
    @Nullable
    public final <T extends TomlSectionConfiguration<P, ?>, S extends Enum<S> & ClassHolder<? extends TomlSectionConfiguration<P, ?>>> T getConfig(@NotNull S sectionEnum) {
        return (T) this.loadedTomlConfigs.get(sectionEnum);
    }
}
