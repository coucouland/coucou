package org.mnode.coucou.schema;

import java.util.List;

/**
 * Add the specified entity/document.
 * @param <Q> query input
 */
public interface RemoveCommand<Q, R> {

    List<R> remove(Q input);
}
