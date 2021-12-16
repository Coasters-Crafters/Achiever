package nl.iobyte.achiever.generic.registry;

import java.util.LinkedHashMap;

public class ClassMap<K> extends LinkedHashMap<Class<? extends K>, K> {

    /**
     * Get value from key
     * @param clazz Class<T>
     * @param <T> ? extends K
     * @return T
     */
    @SuppressWarnings("unchecked")
    public <T extends K> T get(Class<T> clazz) {
        if(clazz == null)
            return null;

        return (T) super.get(clazz);
    }

    /**
     * Remove value from map
     * @param clazz Class<T>
     * @param <T> ? extends K
     * @return T
     */
    @SuppressWarnings("unchecked")
    public <T extends K> T remove(Class<T> clazz) {
        if(clazz == null)
            return null;

        return (T) super.remove(clazz);
    }

}