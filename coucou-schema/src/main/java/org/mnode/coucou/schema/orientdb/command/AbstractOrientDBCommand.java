package org.mnode.coucou.schema.orientdb.command;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;

public abstract class AbstractOrientDBCommand {

    private final ODatabaseDocument session;

    public AbstractOrientDBCommand(ODatabaseDocument session) {
        this.session = session;
    }

    public ODatabaseDocument getSession() {
        return session;
    }
}
