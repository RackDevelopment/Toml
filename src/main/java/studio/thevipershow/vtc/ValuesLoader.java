package studio.thevipershow.vtc;


import org.jetbrains.annotations.NotNull;

/**
 * Interface used to implement values loading
 * and multi values loading.
 *
 * @param <T> The type of values that will be loaded.
 */
public interface ValuesLoader<T> {

    /**
     * Load all of the currently available values.
     */
    void loadAllValues();

    /**
     * Load a single value.
     *
     * @param value The value to load.
     */
    void loadValue(@NotNull T value);
}
