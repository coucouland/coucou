package org.mnode.coucou.schema;

/**
 * Add the specified entity/document.
 * @param <T>
 */
public interface AddCommand<T> {

    boolean add(T input);
}
