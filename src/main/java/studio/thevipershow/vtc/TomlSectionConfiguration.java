package studio.thevipershow.vtc;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Objects;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * This is an implementation of AbstractTomlConfiguration that allows you to read, store, and modify parsed values.
 * It uses a enum dependency approach so you can simply write the nodes in an enum with their type, and you will
 * be able to automatically load them.
 *
 * @param <T> Your plugin type.
 * @param <S> The enum type with all of your config sections.
 */
public abstract class TomlSectionConfiguration<T extends JavaPlugin, S extends Enum<S> & ConfigurationEntryAndType<?>> extends AbstractTomlConfiguration<T> implements ValuesLoader<S> {

    protected final Class<S> enumTypeClass;
    protected final EnumSet<S> enumClassValues;
    @Getter
    private final EnumMap<S, Object> configurationValues;

    /**
     * Constructor for abstract class:
     *
     * @param javaPlugin            Your plugin instance.
     * @param configurationFilename The name of the configuration, including extension.
     * @param enumTypeClass         The class of the Enum containing all of the sections for this config.
     */
    public TomlSectionConfiguration(@NotNull T javaPlugin, @NotNull String configurationFilename, @NotNull Class<S> enumTypeClass) {
        super(javaPlugin, configurationFilename);
        this.enumTypeClass = Objects.requireNonNull(enumTypeClass, "Provided an invalid section enum class for this config.");
        this.enumClassValues = EnumSet.allOf(enumTypeClass);
        this.configurationValues = new EnumMap<>(enumTypeClass);
    }

    /**
     * Use the sections in the specified enum
     * to load all of the values from the config.
     * Null values will throw NPEs.
     */
    @Override
    public void loadAllValues() {
        this.enumClassValues.forEach(this::loadValue);
    }

    /**
     * Load a single value.
     *
     * @param s The value to load.
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public void loadValue(@NotNull S s) {
        var stringData = s.getStringData();
        var read = Objects.requireNonNull(tomlParseResult.get(stringData), String.format("Reading field %s from config %s returned null.", stringData, super.configurationFilename));
        if (read != null) {
            this.configurationValues.put(s, read);
        }
    }
}
