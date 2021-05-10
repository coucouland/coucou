package org.mnode.coucou.schema;

/**
 * Update the specified entity/document.
 * @param <T>
 */
public interface UpdateCommand<T> {

    boolean update(T input);
}
