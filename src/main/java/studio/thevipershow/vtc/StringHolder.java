package studio.thevipershow.vtc;

import org.jetbrains.annotations.NotNull;

/**
 * This interface represents a string data.
 */
@FunctionalInterface
public interface StringHolder {

    /**
     * This method will get the stored data.
     * The data will always be of the same type
     * annotated by this interface and should
     * never be null.
     *
     * @return The data.
     */
    @NotNull
    String getStringData();
}
