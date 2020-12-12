package studio.thevipershow.vtc;

import org.jetbrains.annotations.NotNull;

/**
 * This interface represents a partially generic data holder.
 * It will return a class reference of generic type.
 */
@FunctionalInterface
public interface ClassHolder<T> {

    /**
     * This method will get the stored data.
     * The data will always be of the same type
     * annotated by this interface and should
     * never be null.
     *
     * @return The data.
     */
    @NotNull Class<? extends T> getClassData();
}
