package studio.thevipershow.vtc;

import org.jetbrains.annotations.NotNull;

/**
 * Generic implementation of ClassHolder
 */
@SuppressWarnings("rawtypes")
public interface GenericClassHolder extends ClassHolder {

    @Override
    @NotNull Class<?> getClassData();
}
