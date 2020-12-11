package studio.thevipershow.vtc;

import org.jetbrains.annotations.NotNull;

/**
 * This interface represents a data older of generic type.
 * It must be implemented and should not return null
 * in any case.
 *
 * @param <T> The type of the data to hold.
 */
@FunctionalInterface
public interface DataHolder<T> {

    /**
     * This method will get the stored data.
     * The data will always be of the same type
     * annotated by this interface and should
     * never be null.
     *
     * @return The data.
     */
    @NotNull
    T getData();
}
