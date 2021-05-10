package org.mnode.coucou.schema

import com.syncleus.ferma.ext.orientdb.impl.OrientTransactionFactoryImpl
import com.syncleus.ferma.tx.Tx
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import org.mnode.coucou.schema.model.User
import spock.lang.Specification

class UserTest extends Specification {

    OrientGraphFactory graphFactory = ["memory:test"]

    def 'test persistence'() {
        given: 'an in-memory database'
        OrientGraph graph = new OrientTransactionFactoryImpl(graphFactory, true,
                'org.mnode.coucou.schema')

        when: 'a new text node is created'
        Tx tx = graph.tx()
        User user = tx.getGraph().addFramedVertex(User)
        user.name = "Test"
        tx.success()
        tx.close()

        then: 'the note is persisted'
        Tx tx2 = graph.tx()
        tx2.getGraph().getFramedVertices(User).hasNext()
    }
}
