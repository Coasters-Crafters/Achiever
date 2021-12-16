package nl.iobyte.achiever.generic.registry;

import java.util.Collection;

public class ImplementationRegistry<K> {

    private final ClassMap<K> classes = new ClassMap<>();

    /**
     * Register implementation for clazz
     * @param clazz Class<T>
     * @param value R
     * @param <T> ? extends K
     * @param <R> ? extends T
     * @return T
     */
    public <T extends K, R extends T> ImplementationRegistry<K> register(Class<T> clazz, R value) {
        if(clazz == null || value == null)
            return null;

        if(classes.containsKey(clazz))
            return null;

        classes.put(clazz, value);
        return this;
    }

    /**
     * Register implementation for clazz
     * @param clazz Class<T>
     * @param value Class<R>
     * @param <T> ? extends K
     * @param <R> ? extends T
     * @return T
     */
    public <T extends K, R extends T> ImplementationRegistry<K> register(Class<T> clazz, Class<R> value) {
        if(value == null)
            return null;

        R instance;
        try {
            instance = value.getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }

        return register(clazz, instance);
    }

    /**
     * Register single clazz
     * @param clazz Class<T>
     * @param <T> ? extends K
     * @return ImplementationRegistry<K>
     */
    public <T extends K> ImplementationRegistry<K> register(Class<T> clazz) {
        T instance;
        try {
            instance = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }

        return register(clazz, instance);
    }

    /**
     * Check if contains implementation
     * @param clazz Class<T>
     * @param <T> ? extends K
     * @return T
     */
    public <T extends K> boolean has(Class<T> clazz) {
        return classes.containsKey(clazz);
    }

    /**
     * Get implementation for clazz
     * @param clazz Class<T>
     * @param <T> ? extends K
     * @return T
     */
    public <T extends K> T get(Class<T> clazz) {
        return classes.get(clazz);
    }

    /**
     * Remove implementation from registry
     * @param clazz Class<T>
     * @param <T> ? extends K
     * @return T
     */
    public <T extends K> T remove(Class<T> clazz) {
        return classes.remove(clazz);
    }

    /**
     * Get all implementations
     * @return Collection<? extends K>
     */
    public Collection<? extends K> getValues() {
        return classes.values();
    }

}