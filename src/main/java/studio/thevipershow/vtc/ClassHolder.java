package studio.thevipershow.vtc;

/**
 * This interface represents a partially generic data holder.
 * It will return a class reference of generic type.
 *
 * @param <T> The type of the class to hold.
 */
@FunctionalInterface
public interface ClassHolder<T> extends DataHolder<Class<T>> {
}
