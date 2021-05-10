package org.mnode.coucou.schema;

import java.util.List;

public interface NodeController<T, Q> {

    boolean add(T input);

    List<T> get(Q query);

    List<T> remove(Q query);

    boolean update(T input);
}
