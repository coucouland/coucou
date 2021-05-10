package org.mnode.coucou.schema;

import java.util.List;

/**
 * Get the specified entity/document.
 * @param <Q> query input
 */
public interface GetCommand<Q, R> {

    List<R> get(Q input);
}
